package com.talentpath.rentalcar.controllers;

import com.talentpath.rentalcar.models.Car;
import com.talentpath.rentalcar.models.Transaction;
import com.talentpath.rentalcar.services.RentalCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalCarController {

    @Autowired
    RentalCarService service;

    //gets all cars
    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return service.getCars();
    }

    //gets all transactions
    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(){
        return service.getTransactions();
    }
}
