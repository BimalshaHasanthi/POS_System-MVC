package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CashierDashBoardFormController {
    public AnchorPane dashBoardContext2;
    public AnchorPane loadDetailContext;
    public AnchorPane loadSearchFormContext;
    public AnchorPane loadSearchFormContext1;

    public void manageOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardContext2.getChildren().clear();
        dashBoardContext2.getChildren().add(load);
    }

    public void customerOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MakeCustomerOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardContext2.getChildren().clear();
        dashBoardContext2.getChildren().add(load);
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LogOutConfermation2Form.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardContext2.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
