package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;
import model.Item;
import view.tm.CartTM;
import view.tm.ItemTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

public class ManageItemFormController{
    public AnchorPane searchContext;
    public AnchorPane loadDetailContext;
    public AnchorPane dashBoardContext;
    public TableView tblManageItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colDiscount;


    ObservableList<ItemTM> obList= FXCollections.observableArrayList();

    /*public void initialize(){

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discountPercentage"));

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        tblManageItem.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {

            //selectedRow = (int)newValue;

        });

    }

     */


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addNewItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddNewItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        searchContext.getChildren().clear();
        searchContext.getChildren().add(load);
    }

    public void removeItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RemoveItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        searchContext.getChildren().clear();
        searchContext.getChildren().add(load);
    }

    public void modifyItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ModifyItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        searchContext.getChildren().clear();
        searchContext.getChildren().add(load);
    }

    public void systemReportOnAction(ActionEvent actionEvent) throws IOException{
        searchContext.getChildren().clear();
        URL resource = getClass().getResource("../view/SystemReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(load);
    }

    public void manageItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(load);
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LogOutConfermation2Form.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
