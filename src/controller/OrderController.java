package controller;

import db.DbConnection;
import model.OrderDetail;
import model.Orders;
import model.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1").executeQuery();
        if (rst.next()){
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<9){
                return "O-00"+tempId;
            }else if(tempId<99){
                return "O-0"+tempId;
            }else{
                return "O-"+tempId;
            }
        }else{
            return "O-001";
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET qtyOnHand=(qtyOnHand-" + qty + ") WHERE ItemCode='" + itemCode + "'");
        return stm.executeUpdate()>0;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean placeOrder(Orders orders) {
        Connection con=null;
        try {
            con= DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm =con.prepareStatement("INSERT INTO Orders VALUES(?,?,?,?,?)");


            stm.setObject(1, orders.getOrderId());
            stm.setObject(2, orders.getOrderDate());
            stm.setObject(3, orders.getOrderTime());
            stm.setObject(4, orders.getCustomerId());
            stm.setObject(5, orders.getOrderCost());

            if (stm.executeUpdate() > 0){
                if (saveOrderDetail(orders.getOrderId(), orders.getItems())){
                    con.commit();
                    return true;
                }else{
                    con.rollback();
                    return false;
                }
            }else{
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {

                con.setAutoCommit(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean saveOrderDetail(String orderId, ArrayList<OrderDetail> items) throws SQLException, ClassNotFoundException {
        for (OrderDetail  temp : items
        ) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO OrderDetail VALUES(?,?,?,?,?,?)");

            stm.setObject(1, orderId);
            stm.setObject(2, temp.getItemCode());
            stm.setObject(3, temp.getUnitPrice());
            stm.setObject(4, temp.getOrderQty());
            stm.setObject(5, temp.getDiscount());
            stm.setObject(6, temp.getPrice());
            if (stm.executeUpdate() > 0) {

                if (updateQty(temp.getItemCode(), temp.getOrderQty())){

                }else{
                    return false;
                }

            } else {
                return false;
            }
        }
        return true;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    public Orders getOrders(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Orders WHERE orderId=?");
        stm.setObject(1, id);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Orders(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5)
            );

        } else {
            return null;
        }
    }

    public boolean modifyOrder(Orders item1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Orders SET orderDate=?,custId=?,time=?,cost=? WHERE orderId=?");
        stm.setObject(1, item1.getOrderDate());
        stm.setObject(2, item1.getCustomerId());
        stm.setObject(3, item1.getOrderTime());
        stm.setObject(4, item1.getOrderCost());
        stm.setObject(5, item1.getOrderId());
        return stm.executeUpdate() > 0;
    }

    public boolean removeOrder(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Orders WHERE orderId='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public List<String> getYears() throws SQLException, ClassNotFoundException {
        ArrayList<String> year = new ArrayList<>();
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT YEAR(orderDate) FROM Orders");

        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            if(isYearExists(rst.getString(1),year)){

            }else {
                year.add(rst.getString(1));
            }
        }
        return year;
    }

    private boolean isYearExists(String string, ArrayList<String> year) {
        for(String y : year){
            if(y.equals(string)){
                return true;
            }
        }
        return false;
    }

    public List<String> getMonth() throws SQLException, ClassNotFoundException {
        ArrayList<String>month= new ArrayList<>();
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT MONTH(orderDate) FROM Orders");

        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            if(isYearExists(rst.getString(1),month)){

            }else {
                month.add(rst.getString(1));
            }
        }
        return month;
    }

    public List<String> getDay() throws SQLException, ClassNotFoundException {
        ArrayList<String>dates= new ArrayList<>();
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT DATE (orderDate) FROM Orders");

        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            if(isYearExists(rst.getString(1),dates)){

            }else {
                dates.add(rst.getString(1));
            }
        }
        return dates;
    }

    public ArrayList<Record> getYearlyDetails(String year) throws SQLException, ClassNotFoundException {
        ArrayList<Record> yearlyDetails = new ArrayList<>();

        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Orders WHERE YEAR(orderDate) ='"+year+"'");

        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            Record s = new Record(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    Double.parseDouble(rst.getString(5))
            );
            yearlyDetails.add(s);
        }
        return yearlyDetails;
    }

    public ArrayList<Record> getMonthlyDetails(String month) throws SQLException, ClassNotFoundException {
        ArrayList<Record> MonthlyDetails= new ArrayList<>();

        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Orders WHERE MONTH (orderDate) ='"+month+"'");

        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            Record s = new Record(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    Double.parseDouble(rst.getString(5))
            );
            MonthlyDetails.add(s);
        }
        return MonthlyDetails;
    }

    public ArrayList<Record> getDailyDetails(Object month) throws SQLException, ClassNotFoundException {
        ArrayList<Record> DailyDetails= new ArrayList<>();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Orders WHERE DATE (orderDate) ='"+month+"'");

        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            Record s = new Record(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    Double.parseDouble(rst.getString(5))
            );
            DailyDetails.add(s);
        }
        return DailyDetails;

    }

    public ArrayList<Record> getCustomerIncome(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Record> CustomerWiseIncomeDetails= new ArrayList<>();
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Orders WHERE custId ='"+id+"'");

        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            Record s = new Record(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    Double.parseDouble(rst.getString(5))

            );
            CustomerWiseIncomeDetails.add(s);
        }
        return CustomerWiseIncomeDetails;
    }
}
