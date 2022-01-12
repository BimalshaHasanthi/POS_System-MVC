package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;
import model.Item;

import java.sql.SQLException;

public class ModifyItemFormController {

    public JFXTextField txtItemCode;
    public JFXTextField txtPackSize;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDiscount;


    public void selectItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemId = txtItemCode.getText();

        Item item1= new ItemController().getItem(itemId);
        if (item1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(item1);
        }
    }

    private void setData(Item item1) {
        txtItemCode.setText(item1.getItemCode());
        txtDescription.setText(item1.getDescription());
        txtPackSize.setText(item1.getPackSize());
        txtUnitPrice.setText(String.valueOf(item1.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(item1.getQtyOnHand()));
        txtDiscount.setText(String.valueOf(item1.getDiscountPercentage()));
    }


    public void modifyItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item item1 = new Item(txtItemCode.getText(),txtDescription.getText(),txtPackSize.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnHand.getText()),Double.parseDouble(txtDiscount.getText()));
        if (new ItemController().modifyItem(item1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();

    }


}
