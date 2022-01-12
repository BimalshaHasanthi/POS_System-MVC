package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Orders;

import java.sql.SQLException;

public class RemoveOrderFormController {
    public JFXTextField txtOId;
    public JFXTextField txtCId;
    public JFXTextField txtODate;
    public JFXTextField txtTime;
    public JFXTextField txtCost;

    public void removeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new OrderController().removeOrder(txtOId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    public void selectOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String orderId = txtOId.getText();
        Orders c1= new OrderController().getOrders(orderId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
    }

    private void setData(Orders item1) {
        txtOId.setText(item1.getOrderId());
        txtODate.setText(item1.getOrderDate());
        txtCId.setText(item1.getCustomerId());
        txtTime.setText(item1.getOrderTime());
        txtCost.setText(String.valueOf(item1.getOrderCost()));
    }
}
