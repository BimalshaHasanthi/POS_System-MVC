package controller;

import db.DbConnection;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemService{
    @Override
    public boolean saveItem(Item item1) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Item VALUES(?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,item1.getItemCode());
        stm.setObject(2,item1.getDescription());
        stm.setObject(3,item1.getPackSize());
        stm.setObject(4,item1.getUnitPrice());
        stm.setObject(5,item1.getQtyOnHand());
        stm.setObject(6,item1.getDiscountPercentage());
        return stm.executeUpdate()>0;
    }

 //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item").executeQuery();
        List<String> ids= new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Item getItem(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE itemCode=?");
        stm.setObject(1, id);

        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getDouble(6)
            );
        }else{
            return null;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE itemCode='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public boolean modifyItem(Item item1) throws SQLException, ClassNotFoundException {
        /*Connection con= DbConnection.getInstance().getConnection();
        String query="UPDATE Item SET description=?, packSize=?, unitPrice=? , qtyOnHand=? , discountPercentage=? WHERE itemCode=?";
        PreparedStatement stm = con.prepareStatement(query);

         */

        /*PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET description=?, packSize=?, unitPrice=? , qtyOnHand=? , discountPercentage=? WHERE itemCode=?");
        //stm.setObject(1,item1.getItemCode());
        stm.setObject(1,item1.getDescription());
        stm.setObject(2,item1.getPackSize());
        stm.setObject(3,item1.getUnitPrice());
        stm.setObject(4,item1.getQtyOnHand());
        stm.setObject(5,item1.getDiscountPercentage());
        stm.setObject(6,item1.getItemCode());
        return stm.executeUpdate()>0;

         */

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET description=?, packSize=?, unitPrice=? , qtyOnHand=? , discountPercentage=? WHERE itemCode=?");
        stm.setObject(1,item1.getDescription());
        stm.setObject(2,item1.getPackSize());
        stm.setObject(3,item1.getUnitPrice());
        stm.setObject(4,item1.getQtyOnHand());
        stm.setObject(5,item1.getDiscountPercentage());
        stm.setObject(6,item1.getItemCode());
        return stm.executeUpdate()>0;
    }
}
