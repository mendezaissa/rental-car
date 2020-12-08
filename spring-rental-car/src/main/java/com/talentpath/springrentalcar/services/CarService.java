package com.talentpath.springrentalcar.services;

import com.talentpath.springrentalcar.daos.CarDao;
import com.talentpath.springrentalcar.exceptions.BookingDaoException;
import com.talentpath.springrentalcar.exceptions.InvalidBookingException;
import com.talentpath.springrentalcar.exceptions.NoCarFoundException;
import com.talentpath.springrentalcar.models.Book;
import com.talentpath.springrentalcar.models.Car;
import com.talentpath.springrentalcar.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.tree.TreeNode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    CarDao dao;

    @Autowired
    public CarService ( CarDao dao ) {
        this.dao = dao;
    }

    public List<Car> getCars() {
        List<Car> allCars = dao.getAllCars();
        return allCars.stream().collect(Collectors.toList());
    }

    public Car getById( Integer carId ) throws NoCarFoundException {
        Car car = dao.getById( carId );
        return car;
    }

    public int bookCar(Book toBook) throws BookingDaoException, NoCarFoundException {

        List<Transaction> bookedDates = dao.getCarBookings(toBook.getCarId());

        for(int i = 0; i < bookedDates.size(); i++)
        {
            //System.out.println(bookedDates.get(i).getFrom() + " " + toBook.getFrom());

            if( !toBook.getFrom().equals( bookedDates.get(i).getFrom() )) {
                if( !toBook.getTo().equals( bookedDates.get(i).getTo() )){
                    dao.addTransaction(toBook);
                    List<Transaction> allTransactions = dao.getAllTransactions();
                    return allTransactions.size();
                }
            }
        }

        List<Transaction> allTransactions = dao.getAllTransactions();
        return allTransactions.size();
    }

    public List<Transaction> getBookingsByCardId(Integer id) throws NoCarFoundException {
        List<Transaction> bookedDates = dao.getCarBookings(id);
        return bookedDates;
    }

    public void getBookingByTransactionId(Integer id) {
        dao.deleteByTransactionId(id);
    }

    public List<Transaction> getAllBookings() {
        return dao.getAllTransactions();
    }

    public void updateTransaction(Transaction transaction) {

        Transaction toUpdate = new Transaction();
        toUpdate.setTransactionId(transaction.getTransactionId());
        toUpdate.setCarId(transaction.getCarId());
        toUpdate.setCustomerId(transaction.getCustomerId());
        toUpdate.setFrom(transaction.getFrom());
        toUpdate.setTo(transaction.getTo());

        System.out.println(toUpdate.getFrom());
        System.out.print(toUpdate.getTo());

        dao.updateTransaction(toUpdate);
    }
}
