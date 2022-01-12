package controller;

import model.Item;

import java.sql.SQLException;

public interface ItemService {
    public boolean saveItem(Item c) throws SQLException, ClassNotFoundException;
    public boolean modifyItem(Item c) throws SQLException, ClassNotFoundException;
    public Item getItem(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

}
