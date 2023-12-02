package lk.ijse.driveHub.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.driveHub.model.CustomerModel;
import lk.ijse.driveHub.model.PaymentModel;
import lk.ijse.driveHub.model.ReservationModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    @FXML
    private Label lblCompleteRents;

    @FXML
    private Label lblCustomers;

    @FXML
    private Label lblTransaction;

    @FXML
    private Label lblVehicleCount;
    ReservationModel reservationModel = new ReservationModel();
    CustomerModel customerModel = new CustomerModel();
    PaymentModel paymentModel = new PaymentModel();
    public void setAvailableVehicle() throws SQLException {
        int count = reservationModel.availableVehicle();
        lblVehicleCount.setText(String.valueOf(count));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setAvailableVehicle();
            setCustomerCount();
            setTransaction();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCustomerCount() throws SQLException {
        int count = customerModel.customerCount();
        lblCustomers.setText(String.valueOf(count));
    }
    public void setTransaction() throws SQLException {
        double transaction = paymentModel.transaction();
        lblTransaction.setText(String.valueOf(transaction));
    }
}
