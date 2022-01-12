package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import model.OrderDetail;
import model.Orders;
import view.tm.CartTM;
import view.tm.OrderTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifyOrderDetailFormController {
    public JFXTextField txtOrderId;
    public JFXTextField txtCustomerId;
    public JFXTextField txtOrderDate;
    public JFXTextField txtCost;
    public JFXTextField txtTime;

    public void modifyOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        Orders item1 = new Orders(txtOrderId.getText(),txtOrderDate.getText(),txtCustomerId.getText(),txtTime.getText(),Double.parseDouble(txtCost.getText()));
        if (new OrderController().modifyOrder(item1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();


    }

    private void setData(Orders item1) {
        txtOrderId.setText(item1.getOrderId());
        txtOrderDate.setText(item1.getOrderDate());
        txtCustomerId.setText(item1.getCustomerId());
        txtTime.setText(item1.getOrderTime());
        txtCost.setText(String.valueOf(item1.getOrderCost()));

    }


    public void selectOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String orderId = txtOrderId.getText();
        Orders c1= new OrderController().getOrders(orderId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }


    }


    /*
    public void modifyOrderOnAction(ActionEvent actionEvent) {
        Orders item1 = new Orders(txtOrderId.getText(),txtOrderDate.getText(),txtCustomerId.getText(),txtTime.getText(),Double.parseDouble(txtCost.getText()),ArrayList< Orders > items);
        if (new ItemController().modifyItem(item1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
    }

    public void selectOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String orderId = txtOrderId.getText();
        if (orderId==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(orderId);
        }
    }

    private void setData(String orderId) {
        Orders item1= new Orders();
        txtOrderId.setText(item1.getOrderId());
        txtOrderDate.setText(item1.getOrderDate());
        txtCustomerId.setText(item1.getCustomerId());
        txtTime.setText(item1.getOrderTime());
        txtCost.setText(String.valueOf(item1.getOrderCost()));
    }
*/

   /* public Item getOrderId(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Orders WHERE itemCode=?");
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


    */


}
