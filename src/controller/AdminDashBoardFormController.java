package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminDashBoardFormController {
    public AnchorPane loadDetailContext;
    public AnchorPane loadSearchFormContext;
    public AnchorPane dashBoardContext;
    public AnchorPane dashBoardContext2;

    public void systemReportOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SystemReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardContext2.getChildren().clear();
        dashBoardContext2.getChildren().add(load);
    }

    public void manageItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardContext2.getChildren().clear();
        dashBoardContext2.getChildren().add(load);

        // dashBoardContext2.getChildren().clear();
       // dashBoardContext2.getChildren().add(FXMLLoader.load(getClass().getResource("../view/ManageItemForm.fxml")));


    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LogOutConfermationForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardContext2.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
