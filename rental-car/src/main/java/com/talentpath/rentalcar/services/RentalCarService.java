package com.talentpath.rentalcar.services;

import com.talentpath.rentalcar.daos.RentalCarDao;
import com.talentpath.rentalcar.models.Car;
import com.talentpath.rentalcar.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalCarService {

    RentalCarDao dao;

    @Autowired
    public RentalCarService( RentalCarDao dao ) { this.dao = dao; }

    public List<Car> getCars() {

        List<Car> allCars = dao.getAllCars();
        return allCars.stream().collect(Collectors.toList());
    }


    public List<Transaction> getTransactions() {

        List<Transaction> allTransactions = dao.getAllTransactions();
        return allTransactions.stream().collect(Collectors.toList());
    }
}
