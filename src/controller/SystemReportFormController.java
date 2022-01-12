package controller;

import com.jfoenix.controls.JFXComboBox;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.OrderDetail;
import model.Record;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SystemReportFormController {
    public AnchorPane dashBoardContext2;
    public AnchorPane loadSearchFormContext;
    public Label lblMostMItem;
    public Label lblLeastMItem;
    public AnchorPane lblCustwiseIncome;
    public JFXComboBox cmbCustomer;
    public Label lblDaily;
    public Label lblAnnual;
    public Label lblMonthly;
    public JFXComboBox cmbMonthly;
    public JFXComboBox cmbAnnualy;
    public JFXComboBox cmbDaily;
    public Label lblCustwiseIncome1;
    public Label lblMItem;
    public JFXComboBox cmbMoveableItem;
    public JFXComboBox cmbMovableItem;

    public void initialize() throws SQLException, ClassNotFoundException {

        loadCustomerIds();
        loadYears();
        loadMonths();
        loadDates();

        cmbCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ArrayList<Record> CustomerIncomeDetails = new ArrayList<>();
            try {

                CustomerIncomeDetails = new OrderController().getCustomerIncome((String) newValue);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            calculateCustomerWiseIncome(CustomerIncomeDetails);

        });


        cmbAnnualy.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ArrayList<Record> yearlyDetails = new ArrayList<>();
            try {

                yearlyDetails = new OrderController().getYearlyDetails((String) newValue);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            calculateAnnualIncome(yearlyDetails);


        });

        cmbMonthly.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ArrayList<Record> MonthlyDetails = new ArrayList<>();
            try {
                MonthlyDetails = new OrderController().getMonthlyDetails((String) newValue);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            calculateMonthIncome(MonthlyDetails);

        });

        cmbDaily.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ArrayList<Record> DailyDetails = new ArrayList<>();
            try {

                DailyDetails = new OrderController().getDailyDetails(newValue);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            calculateDailyIncome(DailyDetails);

        });


        ArrayList<String> MovaleList = new ArrayList<>();
        MovaleList.add("MostMovableItem");
        MovaleList.add("LeastMovableItem");

        for (String Movable : MovaleList) {
            cmbMoveableItem.getItems().add(Movable);
        }

        cmbMoveableItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ArrayList<Record> CustomerIncomeDetails = new ArrayList<>();


            try {
                if(newValue=="MostMovableItem"){
                    String a=getMost(newValue);
                    lblMItem.setText(a);
                }else{
                    String a=getLeast(newValue);
                    lblMItem.setText(a);
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });








    }

    private String getLeast(Object newValue) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT itemCode,COUNT(itemCode) FROM OrderDetail GROUP BY itemCode ORDER BY itemCode ASC LIMIT 1");
        ResultSet rst = stm.executeQuery();

        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    private String getMost(Object itemCode) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT itemCode,COUNT(itemCode) FROM OrderDetail GROUP BY itemCode ORDER BY itemCode DESC LIMIT 1");
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void calculateCustomerWiseIncome(ArrayList<Record> temp) {
        double tPrice=0.0;
        for(Record r : temp){
            tPrice+= r.getOrderCost();
        }
        lblCustwiseIncome1.setText(String.valueOf(tPrice)+" /=");

    }

    private void calculateDailyIncome(ArrayList<Record> temp){
        double tPrice=0.0;
        for(Record r : temp){
            tPrice+= r.getOrderCost();
        }
        lblDaily.setText(String.valueOf(tPrice)+" /=");
    }

    private void calculateMonthIncome(ArrayList<Record> temp) {
        double tPrice=0.0;
        for(Record r : temp){
            tPrice+= r.getOrderCost();
        }
        lblMonthly.setText(String.valueOf(tPrice)+" /=");
    }

    private void calculateAnnualIncome(ArrayList<Record> temp) {
        double tPrice=0.0;
        for(Record r : temp){
            tPrice+= r.getOrderCost();
        }
        lblAnnual.setText(String.valueOf(tPrice)+" /=");

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void loadDates() throws SQLException, ClassNotFoundException {
        List<String> day = new OrderController().getDay();
        cmbDaily.getItems().addAll(day);
    }

    private void loadMonths() throws SQLException, ClassNotFoundException {
        List<String> months = new OrderController().getMonth();
        cmbMonthly.getItems().addAll(months);
    }

    private void loadYears() throws SQLException, ClassNotFoundException {
        List<String> years = new OrderController().getYears();
        cmbAnnualy.getItems().addAll(years);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomer.getItems().addAll(customerIds);
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LogOutConfermationForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardContext2.getScene().getWindow();
        window.setScene(new Scene(load));
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void dailyIncomeOnAction(ActionEvent actionEvent) {
    }

    public void annualIncomeOnAction(ActionEvent actionEvent) {
    }

    public void monthlyIncomeOnAction(ActionEvent actionEvent) {
    }

    public void custwiseIncomeOnAction(ActionEvent actionEvent) {
    }
}
