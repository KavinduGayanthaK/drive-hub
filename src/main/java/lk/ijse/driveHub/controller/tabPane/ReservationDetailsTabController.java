package lk.ijse.driveHub.controller.tabPane;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import lk.ijse.driveHub.controller.popupWindowReservation.ReservationVehicleFormController;
import lk.ijse.driveHub.dto.CustomerDto;
import lk.ijse.driveHub.dto.ReservationDto;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.tableDto.OwnerTableDto;
import lk.ijse.driveHub.dto.tableDto.ReservationTableDto;
import lk.ijse.driveHub.model.CustomerModel;
import lk.ijse.driveHub.model.ReservationModel;
import lk.ijse.driveHub.model.VehicleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
    @FXML
    private TableColumn<?, ?> statusColumn;

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
            ObservableList<ReservationTableDto> observableList = FXCollections.observableArrayList();
            List<ReservationTableDto> reservationTableDtos = reservationModel.getOnRentingReservation();
            observableList.addAll(reservationTableDtos);
            reservationTable.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAllCustomer();
        getAllVehicle();
        getAllReservation();
        setCellValueFactory();
        deleteButtonToTable();
        completeBtnToTable();
    }

    private void deleteButtonToTable() {
        TableColumn<ReservationTableDto, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<ReservationTableDto, Void>, TableCell<ReservationTableDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<ReservationTableDto, Void> call(final TableColumn<ReservationTableDto, Void> param) {
                final TableCell<ReservationTableDto, Void> cell = new TableCell<ReservationTableDto, Void>() {
                    private JFXButton button = new JFXButton("delete");
                    {
                        button.setOnAction((ActionEvent event) -> {

                            ReservationTableDto data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            boolean deleted = deleteBtnOnAction(data.getReservationId(),button);
                            if (deleted) {
                            }
                        });
                    }
                    public boolean deleteBtnOnAction(int id, JFXButton button) {

                            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

                            Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION,
                                    "Are you want to delete owner ?", yes, no).showAndWait();
                            if (type.orElse(no) == yes) {
                                try {
                                    boolean isDeleteReservation = reservationModel.deleteReservation(id);
                                    if (isDeleteReservation) {
                                        new Alert(Alert.AlertType.INFORMATION,"Delete successfully").show();
                                    }
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }

                        return true;
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            button.setCursor(Cursor.HAND);
                            button.setStyle("-fx-background-color: #ff3737;-fx-text-fill: #FFFFFF");


                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        reservationTable.getColumns().add(colBtn);
    }

    private void completeBtnToTable() {
        TableColumn<ReservationTableDto, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<ReservationTableDto, Void>, TableCell<ReservationTableDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<ReservationTableDto, Void> call(final TableColumn<ReservationTableDto, Void> param) {
                final TableCell<ReservationTableDto, Void> cell = new TableCell<ReservationTableDto, Void>() {
                    private JFXButton button = new JFXButton("complete");
                    {
                        button.setOnAction((ActionEvent event) -> {

                            ReservationTableDto data = getTableView().getItems().get(getIndex());

                            boolean complete = completeBtnOnAction(data.getReservationId(),button);
                            if (complete) {
                            }
                        });
                    }
                    public boolean completeBtnOnAction(int id, JFXButton button) {

                        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

                        Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are you want to delete owner ?", yes, no).showAndWait();
                        if (type.orElse(no) == yes) {
                            try {
                                boolean isComplete = reservationModel.statusUpdate(id,"Completed");
                                if (isComplete) {

                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                        return true;
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            button.setCursor(Cursor.HAND);
                            button.setStyle("-fx-background-color: #2fff00;-fx-text-fill: #FFFFFF");


                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        reservationTable.getColumns().add(colBtn);
    }

}
