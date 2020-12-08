package com.talentpath.springrentalcar.daos;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
