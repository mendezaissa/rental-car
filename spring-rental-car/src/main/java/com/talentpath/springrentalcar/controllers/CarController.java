package com.talentpath.springrentalcar.controllers;

import com.talentpath.springrentalcar.exceptions.BookingDaoException;
import com.talentpath.springrentalcar.exceptions.InvalidBookingException;
import com.talentpath.springrentalcar.exceptions.NoCarFoundException;
import com.talentpath.springrentalcar.models.Book;
import com.talentpath.springrentalcar.models.Car;
import com.talentpath.springrentalcar.models.Transaction;
import com.talentpath.springrentalcar.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    @Autowired
    CarService service;

    //retrieves all cars
    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return service.getCars();
    }

    //retrieve car details
    @GetMapping("/detail/{id}")
    public Car getById(@PathVariable Integer id) throws NoCarFoundException {
        return service.getById(id);
    }

    //retrieve all bookings
    @GetMapping("/bookings")
    public List<Transaction> getAllTransactions(){
        return service.getAllBookings();
    }

    //add booking
    @PutMapping("/rent")
    public int bookCar (@RequestBody Book book) throws BookingDaoException, InvalidBookingException {
        return service.bookCar(book);
    }

    //gets all bookings by car id
    @GetMapping("/bookings/{id}")
    public List<Transaction> getBookingsByCar (@PathVariable Integer id){
        return service.getBookingsByCardId(id);
    }

    //delete booking by transaction Id
    @DeleteMapping("/delete/{id}")
    public void deleteBooking( @PathVariable Integer id){
        service.getBookingByTransactionId(id);
    }

    //update booking
    @PutMapping("/update")
    public void updateTransaction( @RequestBody Transaction transaction ){
        service.updateTransaction(transaction);
    }

}
