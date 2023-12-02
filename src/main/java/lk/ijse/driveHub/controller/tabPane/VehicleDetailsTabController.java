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
import lk.ijse.driveHub.controller.openWindow.PopupWindows;
import lk.ijse.driveHub.controller.popupWindowVehicle.AddVehicleFormController;
import lk.ijse.driveHub.controller.popupWindowVehicle.UpdateVehiclesFormController;
import lk.ijse.driveHub.controller.popupWindowVehicle.VehicleOwnerFormController;
import lk.ijse.driveHub.dto.tableDto.VehicleTableDto;
import lk.ijse.driveHub.model.VehicleInsuranceModel;
import lk.ijse.driveHub.model.VehicleLicenseModel;
import lk.ijse.driveHub.model.VehicleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class VehicleDetailsTabController implements Initializable {

    @FXML
    private TableColumn<?, ?> bookColumn;
    @FXML
    private TableColumn<?, ?> brandColumn;
    @FXML
    private JFXButton createVehicleBtn;
    @FXML
    private TableColumn<?, ?> idColumn;
    @FXML
    private TableColumn<?, ?> manufactureYearColumn;
    @FXML
    private TableColumn<?, ?> modelColumn;
    @FXML
    private TableColumn<?, ?> registerNumberColumn;
    @FXML
    private TableColumn<?, ?> transMissionTypeColumn;
    @FXML
    private TableColumn<?, ?> typeColumn;
    @FXML
    private TableView<VehicleTableDto> vehicletable;
    @FXML
    private JFXButton vehicleOnboardingBtn;



    VehicleModel vehicleModel = new VehicleModel();
    public static VehicleTableDto vehicleTableDto = new VehicleTableDto();
    VehicleLicenseModel vehicleLicenseModel = new VehicleLicenseModel();
    VehicleInsuranceModel vehicleInsuranceModel = new VehicleInsuranceModel();
    AddVehicleFormController addVehicleFormController = new AddVehicleFormController();

    public VehicleDetailsTabController() throws SQLException {
    }

    @FXML
    void createVehicleBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/popupWindowVehicle/addVehicle_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("License Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        addVehicleFormController.add(stage);
    }

    @FXML
    void vehicleOnboardingBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/popupWindowVehicle/vehicleOwner_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Register Owner");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();

        VehicleOwnerFormController.add(stage);

    }

    private void getAllVehicle() throws SQLException {
        ObservableList<VehicleTableDto> observableList = FXCollections.observableArrayList();
        List<VehicleTableDto> vehicleDtoList = vehicleModel.getVehicleTableDto();
        observableList.addAll(vehicleDtoList);

        vehicletable.setItems(observableList);
    }

    private void setCellValueFactory() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("BookCopy"));
        manufactureYearColumn.setCellValueFactory(new PropertyValueFactory<>("ManufactureYear"));
        registerNumberColumn.setCellValueFactory(new PropertyValueFactory<>("RegisteredNumber"));
        transMissionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("TransmissionType"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateButtonToTable();
        deleteButtonToTable();
        setCellValueFactory();
        try {
            getAllVehicle();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateButtonToTable() {
        TableColumn<VehicleTableDto, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<VehicleTableDto, Void>, TableCell<VehicleTableDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<VehicleTableDto, Void> call(final TableColumn<VehicleTableDto, Void> param) {
                final TableCell<VehicleTableDto, Void> cell = new TableCell<VehicleTableDto, Void>() {
                    private final JFXButton btn = new JFXButton("Update");
                    {
                        btn.setOnAction((ActionEvent event) -> {

                            VehicleTableDto data = getTableView().getItems().get(getIndex());
                            vehicleTableDto.setId(data.getId());
                            vehicleTableDto.setBrand(data.getBrand());
                            vehicleTableDto.setModel(data.getModel());
                            vehicleTableDto.setType(data.getType());
                            vehicleTableDto.setBookCopy(data.getBookCopy());
                            vehicleTableDto.setManufactureYear(data.getManufactureYear());
                            vehicleTableDto.setRegisteredNumber(data.getRegisteredNumber());
                            vehicleTableDto.setTransmissionType(data.getTransmissionType());

                            boolean isUpdated = updateBtnOnAction(data,btn);
                            if (isUpdated) {

                            }

                        });
                    }
                    private boolean updateBtnOnAction(VehicleTableDto data, Button btn) {

                            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

                            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,
                                    "Are you want to change details ?", yes, no).showAndWait();
                            if (type.orElse(no) == yes) {

                                URL resource = this.getClass().getResource("/view/popupWindowVehicle/updateVehicles_form.fxml");
                                FXMLLoader fxmlLoader = new FXMLLoader(resource);
                                Parent load = null;
                                try {
                                    load = fxmlLoader.load();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                Stage stage = new Stage();
                                stage.setTitle("Vehicle Form");
                                stage.setScene(new Scene(load));
                                stage.centerOnScreen();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.setResizable(false);
                                stage.show();

                            }
                        return false;
                    }


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            btn.setCursor(Cursor.HAND);
                            btn.setStyle("-fx-background-color: #42c923");
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        vehicletable.getColumns().add(colBtn);
    }


    private void deleteButtonToTable() {
        TableColumn<VehicleTableDto, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<VehicleTableDto, Void>, TableCell<VehicleTableDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<VehicleTableDto, Void> call(final TableColumn<VehicleTableDto, Void> param) {
                final TableCell<VehicleTableDto, Void> cell = new TableCell<VehicleTableDto, Void>() {
                    private final JFXButton btn = new JFXButton("delete");
                    {
                        btn.setOnAction((ActionEvent event) -> {

                            VehicleTableDto data = getTableView().getItems().get(getIndex());
                            boolean isDelete = deleteBtnOnAction(data,btn);
                            if (isDelete) {

                            }

                        });
                    }
                    private boolean deleteBtnOnAction(VehicleTableDto data, Button btn) {
                            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

                            Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION,
                                    "Are you want to delete vehicle ?", yes, no).showAndWait();
                            if (type.orElse(no) == yes) {
                                try {
                                    boolean isInsuranceDelete = vehicleInsuranceModel.deleteVehicle(data.getId());
                                    if (isInsuranceDelete) {
                                        boolean isLicenseDelete = vehicleLicenseModel.deleteVehicle(data.getId());
                                        if (isLicenseDelete) {
                                            boolean isVehicleDelete = vehicleModel.deleteVehicle(data.getId());
                                            if (isVehicleDelete) {
                                                vehicletable.refresh();
                                                new Alert(Alert.AlertType.INFORMATION,"Delete successfully").show();
                                            }
                                        }
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
                            btn.setCursor(Cursor.HAND);
                            btn.setStyle("-fx-background-color: #cc0808");
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        vehicletable.getColumns().add(colBtn);
    }
}
