package controller;

import model.Customer;

import java.sql.SQLException;

public interface CustomerService {
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException;
}
