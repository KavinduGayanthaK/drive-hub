package lk.ijse.driveHub.controller.popupWindowReservation;

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
import lk.ijse.driveHub.dto.ReservationDto;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.tableDto.CustomerTableDto;
import lk.ijse.driveHub.dto.tableDto.ReservationVehicleTableDto;
import lk.ijse.driveHub.dto.tableDto.VehicleTableDto;
import lk.ijse.driveHub.model.ReservationModel;
import lk.ijse.driveHub.model.VehicleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationVehicleFormController implements Initializable {
    @FXML
    private JFXButton closeBtn;

    @FXML
    private JFXButton nextBtn;
    @FXML
    private JFXButton backBtn;
    @FXML
    private ComboBox<String> cmbVehicleName;
    @FXML
    private DatePicker txtPickUpdate;
    @FXML
    private DatePicker txtReturnDate;
    @FXML
    private TableColumn<?, ?> VehicleModelColumn;
    @FXML
    private TableColumn<?, ?> additionalKmRateColumn;
    @FXML
    private TableColumn<?, ?> perDayeColumn;
    @FXML
    private TableColumn<?, ?> vehicleBrandColumn;
    @FXML
    private TableColumn<?, ?> vehicleIdColumn;
    @FXML
    private TableView<ReservationVehicleTableDto> vehiclePriceTable;
    @FXML
    private TableColumn<?, ?> vehicleTypeColumn;

    static Stage reservationVehicleFormStage;
    CustomerFormController customerFormController = new CustomerFormController();
    static ReservationDto reservationDto = new ReservationDto();
    static ReservationVehicleTableDto reservationVehicleTableDto = new ReservationVehicleTableDto();


    @FXML
    void cmbVehicleNameOnAction(ActionEvent event) {

    }

    @FXML
    void closeBtnOnAction(ActionEvent event) throws IOException {

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException {
        setValues();
        URL resource = this.getClass().getResource("/view/popupWindowReservation/customer_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Customer Form");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();

        reservationVehicleFormStage.close();
        customerFormController.add(stage, reservationVehicleFormStage);
    }

    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {

    }

    public static void add(Stage stage) {
        reservationVehicleFormStage = stage;

    }

    private void setValues() {
        reservationDto.setVehicleId(reservationVehicleTableDto.getVehicleId());
        reservationDto.setId(0);
        reservationDto.setStartDate(txtPickUpdate.getValue());
        reservationDto.setEndDate(txtReturnDate.getValue());
        reservationDto.setStatus("On Renting");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void searchBtnOnAction(ActionEvent event) {
        ReservationModel reservationModel = new ReservationModel();
        reservationDto.setStartDate(txtPickUpdate.getValue());
        reservationDto.setEndDate(txtReturnDate.getValue());

        ObservableList<ReservationVehicleTableDto> observableList = FXCollections.observableArrayList();
        try {
            List<ReservationVehicleTableDto> reservationVehicleTableDtoList = reservationModel.searchVehicle(reservationDto);
            observableList.addAll(reservationVehicleTableDtoList);
            vehiclePriceTable.setItems(observableList);
            setCellValueFactory();
            selectToTable();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        vehicleIdColumn.setCellValueFactory(new PropertyValueFactory<>("VehicleId"));
        vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("VehicleType"));
        vehicleBrandColumn.setCellValueFactory(new PropertyValueFactory<>("VehicleBrand"));
        VehicleModelColumn.setCellValueFactory(new PropertyValueFactory<>("VehicleModel"));
        perDayeColumn.setCellValueFactory(new PropertyValueFactory<>("PerDayRate"));
        additionalKmRateColumn.setCellValueFactory(new PropertyValueFactory<>("AdditionalKmRate"));
    }

    private void selectToTable() {
        TableColumn<ReservationVehicleTableDto, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<ReservationVehicleTableDto, Void>, TableCell<ReservationVehicleTableDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<ReservationVehicleTableDto, Void> call(final TableColumn<ReservationVehicleTableDto, Void> param) {
                final TableCell<ReservationVehicleTableDto, Void> cell = new TableCell<ReservationVehicleTableDto, Void>() {
                    private  JFXButton btn = new JFXButton("SELECT");
                    {
                        btn.setOnAction((ActionEvent event) -> {

                            ReservationVehicleTableDto data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);

                            reservationVehicleTableDto.setVehicleId(data.getVehicleId());
                            reservationVehicleTableDto.setVehicleType(data.getVehicleType());
                            reservationVehicleTableDto.setVehicleBrand(data.getVehicleBrand());
                            reservationVehicleTableDto.setVehicleModel(data.getVehicleModel());
                            reservationVehicleTableDto.setPerDayRate(data.getPerDayRate());
                            reservationVehicleTableDto.setAdditionalKmRate(data.getAdditionalKmRate());

                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            btn.setCursor(Cursor.HAND);
                            btn.setStyle("-fx-background-color: #ff8b33");

                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        vehiclePriceTable.getColumns().add(colBtn);
    }
}


