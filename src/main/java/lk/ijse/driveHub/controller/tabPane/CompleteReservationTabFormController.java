package lk.ijse.driveHub.controller.tabPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.driveHub.dto.CustomerDto;
import lk.ijse.driveHub.dto.ReservationDto;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.tableDto.CompleteReservationTableDto;
import lk.ijse.driveHub.dto.tableDto.ReservationTableDto;
import lk.ijse.driveHub.model.CustomerModel;
import lk.ijse.driveHub.model.ReservationModel;
import lk.ijse.driveHub.model.VehicleModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CompleteReservationTabFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> customerNameColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> registeredNumberColumn;

    @FXML
    private TableColumn<?, ?> reservationDateColumn;

    @FXML
    private TableView<CompleteReservationTableDto> completeReservationTable;

    @FXML
    private TableColumn<?, ?> returnDateColumn;

    @FXML
    private TableColumn<?, ?> vehicleIdColumn;

    @FXML
    private TableColumn<?, ?> vehicleModelColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    CustomerModel customerModel = new CustomerModel();
    ReservationTableDto reservationTableDto = new ReservationTableDto();
    VehicleModel vehicleModel = new VehicleModel();
    ReservationModel reservationModel = new ReservationModel();

    public void setCellValueFactory() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ReservationId"));
        vehicleIdColumn.setCellValueFactory(new PropertyValueFactory<>("VehicleId"));
        vehicleModelColumn.setCellValueFactory(new PropertyValueFactory<>("VehicleModel"));
        registeredNumberColumn.setCellValueFactory(new PropertyValueFactory<>("RegisteredNumber"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        reservationDateColumn.setCellValueFactory(new PropertyValueFactory<>("ReservationDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));
    }


    public void getAllReservation() {
        ObservableList<CompleteReservationTableDto> observableList = FXCollections.observableArrayList();
        try {
            List<CompleteReservationTableDto> completeReservation = reservationModel.getCompleteReservation();
            observableList.addAll(completeReservation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        completeReservationTable.setItems(observableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAllReservation();
        setCellValueFactory();
    }
}
