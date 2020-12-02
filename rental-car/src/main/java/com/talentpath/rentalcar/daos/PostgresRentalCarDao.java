package com.talentpath.rentalcar.daos;

import com.talentpath.rentalcar.models.Car;
import com.talentpath.rentalcar.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Profile( { "production", "daotesting" } )
public class PostgresRentalCarDao implements RentalCarDao{

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Car> getAllCars() {

        List<Car> allCars = template.query("select * from \"car\"", new CarMapper() );

        return allCars;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    class CarMapper implements RowMapper<Car>{
        @Override
        public Car mapRow(ResultSet resultSet, int i ) throws SQLException {
            Car toReturn = new Car();
            toReturn.setCarId( resultSet.getInt("carId") );
            toReturn.setMake( resultSet.getString( "make") );
            toReturn.setModel( resultSet.getString("model") );
            toReturn.setRate( resultSet.getInt( "rate") );
            return toReturn;
        }
    }






}
