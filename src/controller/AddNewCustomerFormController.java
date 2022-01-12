package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import model.Customer;

import java.sql.SQLException;

public class AddNewCustomerFormController {
    public AnchorPane addNewCustomerContext;
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerTitle;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerCity;
    public JFXTextField txtCustomerProvince;
    public JFXTextField txtCPostalCode;

    public void AddCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1 = new Customer(
                txtCustomerId.getText(),txtCustomerTitle.getText(), txtCustomerName.getText(),txtCustomerAddress.getText(),txtCustomerCity.getText(),txtCustomerProvince.getText(),txtCPostalCode.getText()
        );

        if(new CustomerController().saveCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

    }
}
