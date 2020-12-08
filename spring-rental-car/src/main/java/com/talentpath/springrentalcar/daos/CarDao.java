package com.talentpath.springrentalcar.daos;

import com.talentpath.springrentalcar.exceptions.BookingDaoException;
import com.talentpath.springrentalcar.exceptions.NoCarFoundException;
import com.talentpath.springrentalcar.exceptions.NoTransactionFoundException;
import com.talentpath.springrentalcar.models.Book;
import com.talentpath.springrentalcar.models.Car;
import com.talentpath.springrentalcar.models.Transaction;

import java.util.List;

public interface CarDao {

    void reset();

    List<Car> getAllCars(); //complete

    Car getById(Integer carId) throws NoCarFoundException; //complete

    List<Transaction> getCarBookings(Integer carId) throws NoCarFoundException; //complete

    void addTransaction(Book toBook) throws BookingDaoException;

    List<Transaction> getAllTransactions(); //complete

    void deleteByTransactionId(Integer id) throws NoTransactionFoundException; //complete

    void updateTransaction(Transaction transaction);
}
