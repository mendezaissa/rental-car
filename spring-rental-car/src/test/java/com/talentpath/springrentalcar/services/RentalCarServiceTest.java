package com.talentpath.springrentalcar.services;

import com.talentpath.springrentalcar.daos.CarDao;
import com.talentpath.springrentalcar.daos.InMemRentalCarDao;
import com.talentpath.springrentalcar.exceptions.NoCarFoundException;
import com.talentpath.springrentalcar.models.Car;
import com.talentpath.springrentalcar.models.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("servicetesting")
public class RentalCarServiceTest {

    @Autowired
    CarService serviceToTest;

    @Autowired
    CarDao dao;

    @BeforeEach
    void setUp(){
        dao = new InMemRentalCarDao();
        serviceToTest = new CarService(dao);
        dao.reset();
    }

    @Test
    void getAllCars(){
        List<Car> allCars = serviceToTest.getCars();
        assertEquals( 1, allCars.size() );
    }

    @Test
    void getAllTransactions(){
        List<Transaction> allTransactions = serviceToTest.getAllBookings();
        assertEquals(1, allTransactions.size() );
    }

    @Test
    void getCarById(){
        try{
            Car carToGet = serviceToTest.getById(1);
            assertEquals(1, carToGet.getCarId() );
        }catch( NoCarFoundException ex ){
            fail("Could not find car with id 1");
        }
    }

    @Test
    void getCarByBadId(){
        try{
            Car carToGet = serviceToTest.getById(-1);
            fail("should throw NoCarFoundException");
        }catch( NoCarFoundException ex ){
            //should reach here
        }
    }

    @Test
    void getCarByNullId(){
        try{
            Car carToGet = serviceToTest.getById(null);
            fail("should throw NoCarFoundException");
        }catch( NoCarFoundException ex ){
            //should reach here
        }
    }

}
