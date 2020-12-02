package com.talentpath.rentalcar.daos;

import com.talentpath.rentalcar.models.Car;
import com.talentpath.rentalcar.models.Transaction;

import java.util.List;

public interface RentalCarDao {

    List<Car> getAllCars();

    List<Transaction> getAllTransactions();
}
