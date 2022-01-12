package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Item;
import model.OrderDetail;
import model.Orders;
import view.tm.CartTM;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MakeCustomerOrderFormController {
    public AnchorPane dashBoardContext2;
    public AnchorPane loadDetailContext;
    public AnchorPane loadSearchFormContext;
    public Label lblOId;
    public Label lblDate;
    public Label lblTime;
    public JFXComboBox cmbCustomerId;
    public JFXTextField txtCustomerTitle;
    public JFXTextField txtCustomerAddress;
    public JFXComboBox cmbItemCode;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtQTY;
    public JFXTextField txtCustomerCity;
    public JFXTextField txtCustomerProvince;
    public JFXTextField txtCustomerPostalCode;
    public JFXTextField txtCustomerName;
    public JFXTextField txtDiscount;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQTY;
    public TableColumn colDiscount;
    public TableColumn colPrice;
    public Label lblTotalPrice;
    public TableView tblPlaceOrder;
    public TableColumn colOrderId;


    ObservableList<CartTM> obList= FXCollections.observableArrayList();

    int cartSelectedRowForRemove = -1;



   /* public void initialize() {
        //----------------------Set Date & time--------------------------------------------------------------
        try {
            new Timer(1000, e -> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss aa");
                String time = simpleDateFormat.format(new Date());
                String date = new SimpleDateFormat("MMM/dd/YYYY", Locale.ENGLISH).format(new Date());
                lblDate.setText(date);
                lblTime.setText(time);
            }).start();
        } catch (Exception exception) {
        }
    }

    */


    public void initialize() {

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        loadDateAndTime();
        setOrderId();

        try {
            loadCustomerId();
            loadItemId();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    try {
                        setCustomerData((String) newValue);
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData((String) newValue);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        tblPlaceOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });
    }

 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {

        Item i1 = new ItemController().getItem(itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
            txtDiscount.setText(String.valueOf(i1.getDiscountPercentage()));
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        Customer c1 = new CustomerController().getCustomer(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtCustomerTitle.setText(c1.getCustomerTitle());
            txtCustomerName.setText(c1.getCustomerName());
            txtCustomerAddress.setText(c1.getCustomerAddress());
            txtCustomerCity.setText(c1.getCustomerCity());
            txtCustomerProvince.setText(c1.getCustomerProvince());
            txtCustomerPostalCode.setText(c1.getCustomerPostalCode());
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void loadItemId() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemController().getAllItemIds();
        cmbItemCode.getItems().addAll(itemIds);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void loadCustomerId() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomerId.getItems().addAll(customerIds);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void setOrderId() {
        try {
            lblOId.setText(new OrderController().getOrderId());

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour() + " : " + currentTime.getMinute() + " : " + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void clearOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblPlaceOrder.refresh();
        }
    }

    private void calculateCost() {
        double ttl=0;
        for (CartTM tm:obList) {
            ttl+=tm.getPrice();
        }
        lblTotalPrice.setText(ttl+" /=");
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addToCartOnAction(ActionEvent actionEvent) {
        String orderId = lblOId.getText();
        String itemCode = (String) cmbItemCode.getSelectionModel().getSelectedItem();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        int orderQty = Integer.parseInt(txtQTY.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double discount = Double.parseDouble(txtDiscount.getText());
        double total = (orderQty * unitPrice) - discount;

        if (qtyOnHand < orderQty) {
            new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
            return;
        }

        CartTM tm = new CartTM(orderId,itemCode,unitPrice,orderQty, discount, total);

        int rowNumber=isExists(tm);

        if (rowNumber==-1){
            obList.add(tm);
        }else {
            CartTM temp = obList.get(rowNumber);
            CartTM newTm = new CartTM(temp.getOrderId(), temp.getItemCode(), temp.getUnitPrice(), temp.getOrderQty() + orderQty, temp.getDiscount(), total + temp.getPrice());

            obList.remove(rowNumber);
            obList.add(newTm);

        }

        tblPlaceOrder.setItems(obList);
        calculateCost();

    }

    private int isExists(CartTM tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItemCode().equals(obList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
        ArrayList<OrderDetail> items= new ArrayList<>();
        double total=0;
        for (CartTM tempTm:obList) {
            total+=tempTm.getPrice();
            items.add(new OrderDetail(tempTm.getOrderId(),tempTm.getItemCode(),tempTm.getUnitPrice(), tempTm.getOrderQty(),tempTm.getDiscount(),tempTm.getPrice()));
        }

        Orders orders = new Orders(lblOId.getText(), lblDate.getText(), lblTime.getText(), (String) cmbCustomerId.getValue(), total, items);
        if (new OrderController().placeOrder(orders)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void addNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddNewCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loadSearchFormContext.getChildren().clear();
        loadSearchFormContext.getChildren().add(load);
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LogOutConfermation2Form.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardContext2.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
