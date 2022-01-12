package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.CartTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class ManageOrderFormController {
    public AnchorPane manageOrderContext;
    public AnchorPane OrderDetailContext;
    public AnchorPane loadSearchFormContext;

    public TableView tblOrderDetail;
    public TableColumn colOId;
    public TableColumn colODate;
    public TableColumn colCId;
    public TableColumn colTime;
    public TableColumn colCost;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void initialize() {

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void removeOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RemoveOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loadSearchFormContext.getChildren().clear();
        loadSearchFormContext.getChildren().add(load);
    }

    public void modifyOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ModifyOrderDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loadSearchFormContext.getChildren().clear();
        loadSearchFormContext.getChildren().add(load);
    }

    public void manageOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        manageOrderContext.getChildren().clear();
        manageOrderContext.getChildren().add(load);
    }

    public void customerOrderOnAction(ActionEvent actionEvent) throws IOException {
        OrderDetailContext.getChildren().clear();
        URL resource = getClass().getResource("../view/MakeCustomerOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        manageOrderContext.getChildren().clear();
        manageOrderContext.getChildren().add(load);
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LogOutConfermation2Form.fxml");
        Parent load = FXMLLoader.load(resource);
        manageOrderContext.getChildren().clear();
        manageOrderContext.getChildren().add(load);
    }
}
