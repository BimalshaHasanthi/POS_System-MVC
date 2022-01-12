package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;
import model.Item;

import java.sql.SQLException;

public class AddNewItemFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtPackSize;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDiscount;

    public void addItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item item1 = new Item(txtItemCode.getText(),txtDescription.getText(),txtPackSize.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnHand.getText()),Double.parseDouble(txtDiscount.getText()));

        if(new ItemController().saveItem(item1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

    }
}
