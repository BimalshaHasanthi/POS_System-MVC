package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Item;

import java.sql.SQLException;

public class RemoveItemFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtPackSize;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDiscount;

    public void removeItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new ItemController().deleteItem(txtItemCode.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void searchItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String ItemId = txtItemCode.getText();

        Item item1= new ItemController().getItem(ItemId);
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
}
