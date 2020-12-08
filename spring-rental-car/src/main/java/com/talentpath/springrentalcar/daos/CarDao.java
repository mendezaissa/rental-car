package com.talentpath.springrentalcar.daos;

import com.talentpath.springrentalcar.exceptions.BookingDaoException;
import com.talentpath.springrentalcar.exceptions.NoCarFoundException;
import com.talentpath.springrentalcar.models.Book;
import com.talentpath.springrentalcar.models.Car;
import com.talentpath.springrentalcar.models.Transaction;

import java.util.List;

public interface CarDao {

    void reset();

    List<Car> getAllCars();

    Car getById(Integer carId) throws NoCarFoundException;

    List<Transaction> getCarBookings(Integer carId);

    void addTransaction(Book toBook) throws BookingDaoException;

    List<Transaction> getAllTransactions();

    void deleteByTransactionId(Integer id);

    void updateTransaction(Transaction transaction);
}
