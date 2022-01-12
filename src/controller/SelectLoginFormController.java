package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SelectLoginFormController {
    public JFXTextField txtUserName;
    public AnchorPane logInContext;

    public void logInOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().toString().equals("admin")) {
            URL resource = getClass().getResource("../view/AdminDashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) logInContext.getScene().getWindow();
            window.setScene(new Scene(load));
        }else if (txtUserName.getText().toString().equals("cashier")){
            URL resource = getClass().getResource("../view/CashierDashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) logInContext.getScene().getWindow();
            window.setScene(new Scene(load));

        }else {
            URL resource = getClass().getResource("../view/SelectLoginForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) logInContext.getScene().getWindow();
            window.setScene(new Scene(load));

        }

    }
}
