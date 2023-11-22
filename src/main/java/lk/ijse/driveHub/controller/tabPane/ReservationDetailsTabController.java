package lk.ijse.driveHub.controller.tabPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.popupWindowReservation.ReservationVehicleFormController;
import lk.ijse.driveHub.dto.CustomerDto;
import lk.ijse.driveHub.dto.ReservationDto;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.tableDto.ReservationTableDto;
import lk.ijse.driveHub.model.CustomerModel;
import lk.ijse.driveHub.model.ReservationModel;
import lk.ijse.driveHub.model.VehicleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationDetailsTabController  implements Initializable {

    @FXML
    private TableColumn<?, ?> customerNameColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> registeredNumberColumn;

    @FXML
    private TableColumn<?, ?> reservationDateColumn;

    @FXML
    private TableView<ReservationTableDto> reservationTable;

    @FXML
    private TableColumn<?, ?> returnDateColumn;

    @FXML
    private TableColumn<?, ?> vehicleIdColumn;

    @FXML
    private TableColumn<?, ?> vehicleModelColumn;

    CustomerModel customerModel = new CustomerModel();
    ReservationTableDto reservationTableDto = new ReservationTableDto();
    VehicleModel vehicleModel = new VehicleModel();
    ReservationModel reservationModel = new ReservationModel();


    public void createReservationBtnOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/popupWindowReservation/reservationVehicle_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Vehicle Form");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();

        ReservationVehicleFormController.add(stage);

    }

    public void getAllCustomer() {
        try {
            List<CustomerDto> customerDtoList = customerModel.getAllCustomer();
            for (CustomerDto customerDto : customerDtoList) {
                reservationTableDto.setCustomerName(customerDto.getFirstName()+" "+customerDto.getLastName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAllVehicle() {
        try {
            List<VehicleDto> vehicleDtoList = vehicleModel.getAllVehicle();
            for (VehicleDto vehicleDto: vehicleDtoList) {
                reservationTableDto.setVehicleId(vehicleDto.getId());
                reservationTableDto.setVehicleModel(vehicleDto.getModel());
                reservationTableDto.setRegisteredNumber(vehicleDto.getRegisterNumber());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAllReservation() {
        try {
            ReservationDto reservationDto = reservationModel.getAllReservation();
            reservationTableDto.setReservationId(reservationDto.getId());
            reservationTableDto.setReservationDate(reservationDto.getStartDate());
            reservationTableDto.setReturnDate(reservationDto.getEndDate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setTableValue() {
        ObservableList<ReservationTableDto> observableList = FXCollections.observableArrayList();
        observableList.add(reservationTableDto);
        reservationTable.setItems(observableList);
    }
    public void setCellValueFactory() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ReservationId"));
        vehicleIdColumn.setCellValueFactory(new PropertyValueFactory<>("VehicleId"));
        vehicleModelColumn.setCellValueFactory(new PropertyValueFactory<>("VehicleModel"));
        registeredNumberColumn.setCellValueFactory(new PropertyValueFactory<>("RegisteredNumber"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        reservationDateColumn.setCellValueFactory(new PropertyValueFactory<>("ReservationDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAllCustomer();
        getAllVehicle();
        getAllReservation();
        setTableValue();
        setCellValueFactory();
    }
}
