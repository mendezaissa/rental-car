package com.talentpath.springrentalcar.daos;

import com.talentpath.springrentalcar.exceptions.BookingDaoException;
import com.talentpath.springrentalcar.exceptions.NoCarFoundException;
import com.talentpath.springrentalcar.exceptions.NoTransactionFoundException;
import com.talentpath.springrentalcar.models.Book;
import com.talentpath.springrentalcar.models.Car;
import com.talentpath.springrentalcar.models.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("daotesting")
public class PostgresRentalCarTest {

    @Autowired
    PostgresCarDao daoToTest;

    @BeforeEach
    void setUp() { daoToTest.reset(); }

    @Test
    void getAllCars() {
        List<Car> allCars = daoToTest.getAllCars();
        assertEquals(0, allCars.size() );

    }

    @Test
    void getAllTransactions(){
        List<Transaction> allTransactions = daoToTest.getAllTransactions();
        assertEquals( 0 , allTransactions.size() );
    }

    @Test
    void getCarById() throws NoCarFoundException {
        Car carById = new Car();
        carById.setCarId(1);
        carById.setMake("Ford");
        carById.setModel("Mustang");
        carById.setRate(500);

        Car car = daoToTest.getById( carById.getCarId() );
        assertEquals( 1, car.getCarId() );
    }

    @Test
    void getCarByBadId(){

        try{
            Car carById = new Car();
            carById.setCarId(100);
            carById.setMake("does not");
            carById.setModel("exist");
            carById.setRate(300);

            Car car = daoToTest.getById( carById.getCarId() );
            fail("Should throw NoCarFoundException");
        }catch( NoCarFoundException ex){
            //should reach here
        }
    }

    @Test
    void getCarBookingsById() throws NoCarFoundException {
        Car car = daoToTest.getById(1);

        List<Transaction> transactionsByCarId = daoToTest.getCarBookings( car.getCarId() );
        assertEquals(0, transactionsByCarId.size() );
    }

    @Test
    void getCarBookingsByBadId() {
        try{
            List<Transaction> transactions = daoToTest.getCarBookings(100);
            fail("Should throw NoCarFound Exception");
        }catch(NoCarFoundException ex){
            //should reach here
        }
    }

    @Test
    void deleteById() throws NoTransactionFoundException, BookingDaoException {

            Book book = new Book();
            book.setCarId(1);
            book.setCustomerId(1);

            Date from = new Date();
            Date to = new Date();

            book.setFrom( from );
            book.setTo( to );

            daoToTest.addTransaction(book);
            daoToTest.deleteByTransactionId(1);
    }

    @Test
    void deleteByBadId() throws BookingDaoException {

        try{
            Book book = new Book();
            book.setCarId(1);
            book.setCustomerId(1);

            Date from = new Date();
            Date to = new Date();

            book.setFrom( from );
            book.setTo( to );

            daoToTest.addTransaction(book);
            daoToTest.deleteByTransactionId(100);
            fail("should throw NoTransactionFoundException");

        }catch(NoTransactionFoundException ex){
            //should reach here
        }
    }

    @Test
    void deleteByNullId(){
        try{
            daoToTest.deleteByTransactionId(null);
            fail("should throw NoTransactionFoundException");
        }catch(NoTransactionFoundException ex){
            //should reach here
        }
    }

}
