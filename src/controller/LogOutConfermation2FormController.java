package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LogOutConfermation2FormController {
    public AnchorPane logoutContext;

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SelectLoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) logoutContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void cancelLogoutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CashierDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) logoutContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
