package com.talentpath.springrentalcar.daos;

import com.talentpath.springrentalcar.exceptions.BookingDaoException;
import com.talentpath.springrentalcar.exceptions.InvalidBookingException;
import com.talentpath.springrentalcar.exceptions.NoCarFoundException;
import com.talentpath.springrentalcar.exceptions.NoTransactionFoundException;
import com.talentpath.springrentalcar.models.Book;
import com.talentpath.springrentalcar.models.Car;
import com.talentpath.springrentalcar.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Profile(  { "production" , "daotesting" } )
public class PostgresCarDao implements CarDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public void reset(){
        template.update("TRUNCATE \"transaction\" RESTART IDENTITY " );
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> allCars = template.query("select * from \"car\" order by \"carId\"", new CarMapper() );
        return allCars;
    }

    @Override
    public Car getById(Integer carId) throws NoCarFoundException {

        if(carId == null){
            throw new NoCarFoundException("no car found with id null");
        }

        try{
            Car carRetrieved = template.queryForObject("select * from \"car\" where \"carId\" = '" + carId + "'", new CarMapper());
            return carRetrieved;
        }catch(DataAccessException ex){
            throw new NoCarFoundException("no car found with id: " + carId);
        }
    }

    @Override
    public List<Transaction> getCarBookings(Integer carId) throws NoCarFoundException {

        try{
            List<Transaction> transactions = template.query("select * from \"transaction\" where \"carId\" = '" + carId + "'", new TransactionMapper());

            if(transactions.size() == 0){
                throw new NoCarFoundException("could not find car with id: " + carId + " in the list of transactions");
            }

            return transactions;
        }catch( DataAccessException ex){
            throw new NoCarFoundException("Could not retrieve transactions for car with id: " + carId );
        }

    }

    @Override
    public void addTransaction(Book toBook) throws BookingDaoException {

        try{
            int rowsAffected = template.update("insert into transaction (\"carId\", \"customerId\", \"from\", \"to\")\n" +
                    "values ('"+toBook.getCarId()+"', '"+toBook.getCustomerId()+"', '"+toBook.getFrom()+"', '"+toBook.getTo()+"')");

            if(rowsAffected == 0){
                throw new InvalidBookingException("Invalid Booking please check dates");
            }

        }catch(DataAccessException | InvalidBookingException ex){
            throw new BookingDaoException("Error while adding booking/transaction" + ex.getMessage() );
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> allTransactions = template.query("select * from \"transaction\" order by \"transactionId\"", new TransactionMapper() );
        return allTransactions;
    }

    @Override
    public void deleteByTransactionId(Integer id) throws NoTransactionFoundException {

        List<Transaction> transactionList = template.query("select * from \"transaction\"", new TransactionMapper() );
        int size = transactionList.size();

        if( id == null ){
            throw new NoTransactionFoundException("could not find transaction with id null");
        }

            template.execute("delete from \"transaction\" where \"transactionId\" = '" +id+ "'" );

            List<Transaction> transactionList2 = template.query("select * from \"transaction\"", new TransactionMapper() );
            int sizeAfterDelete = transactionList2.size();

            if(size == sizeAfterDelete){
                throw new NoTransactionFoundException("no transaction was deleted");
            }
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        template.update("update transaction \n" +
                "set \"from\" = '"+transaction.getFrom()+"', \"to\" = '"+transaction.getTo()+"' where \"transactionId\" = '"+transaction.getTransactionId()+"'");
    }


    class CarMapper implements RowMapper<Car> {
        @Override
        public Car mapRow(ResultSet resultSet, int i ) throws SQLException {
            Car toReturn = new Car();
            toReturn.setCarId( resultSet.getInt("carId") );
            toReturn.setMake( resultSet.getString( "make") );
            toReturn.setModel( resultSet.getString("model") );
            toReturn.setRate( resultSet.getInt("rate") );
            return toReturn;
        }
    }

    class TransactionMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
            Transaction toReturn = new Transaction();
            toReturn.setTransactionId(resultSet.getInt("transactionId"));
            toReturn.setCustomerId(resultSet.getInt("customerId"));
            toReturn.setCarId(resultSet.getInt("carId"));
            toReturn.setFrom(resultSet.getDate("from"));
            toReturn.setTo(resultSet.getDate("to"));
            return toReturn;
        }
    }

}
