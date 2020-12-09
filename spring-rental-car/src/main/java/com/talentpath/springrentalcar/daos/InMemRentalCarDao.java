package com.talentpath.springrentalcar.daos;

import com.talentpath.springrentalcar.exceptions.BookingDaoException;
import com.talentpath.springrentalcar.exceptions.NoCarFoundException;
import com.talentpath.springrentalcar.exceptions.NoTransactionFoundException;
import com.talentpath.springrentalcar.models.Book;
import com.talentpath.springrentalcar.models.Transaction;
import com.talentpath.springrentalcar.models.Car;
import com.talentpath.springrentalcar.models.Transaction;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("servicetesting")
public class InMemRentalCarDao implements CarDao{

    List<Car> allCars;
    List<Transaction> allTransactions;

    public InMemRentalCarDao(){
        reset();
    }

    @Override
    public void reset() {
        allCars = new ArrayList<>();
        allCars.clear();

        Car car1 = new Car();
        car1.setCarId(1);
        car1.setMake("Audi");
        car1.setModel("R8");
        car1.setRate(800);

        allCars.add(car1);

        allTransactions = new ArrayList<>();
        allTransactions.clear();

        Transaction booking1 = new Transaction();
        booking1.setTransactionId(1);
        booking1.setCarId(1);
        booking1.setCustomerId(1);

        Date from = new Date();
        booking1.setFrom( from );

        Date to = new Date();
        booking1.setTo( to );

        allTransactions.add(booking1);
    }

    @Override
    public List<Car> getAllCars() {
        return allCars.stream().map( toCopy -> new Car( toCopy ) ).collect( Collectors.toList() );
    }

    @Override
    public Car getById(Integer carId) throws NoCarFoundException {
        Car toReturn = allCars.stream().filter( g -> g.getCarId() == carId ).findAny().orElse(null);
        if( toReturn != null) { return new Car(toReturn); }
        else{
            throw new NoCarFoundException("no car found");
        }
    }

    @Override
    public List<Transaction> getCarBookings(Integer carId) throws NoCarFoundException {

        List<Transaction> transactionByCarId = new ArrayList<>();

        for(int i = 0; i < allTransactions.size(); i++){
            Transaction transaction = allTransactions.get(i);

            if( transaction.getCarId() == carId ){
                transactionByCarId.add(transaction);
            }
        }

        if( transactionByCarId.size() == 0){
            throw new NoCarFoundException("no bookings found for car id: " + carId);
        }
        else{
            return transactionByCarId;
        }
    }

    @Override
    public void addTransaction(Book toBook) throws BookingDaoException {

    }

    @Override
    public List<Transaction> getAllTransactions() {
        return allTransactions.stream().map( toCopy -> new Transaction( toCopy ) ).collect(Collectors.toList());
    }

    @Override
    public void deleteByTransactionId(Integer id) throws NoTransactionFoundException {

    }

    @Override
    public void updateTransaction(Transaction transaction) {

    }
}
