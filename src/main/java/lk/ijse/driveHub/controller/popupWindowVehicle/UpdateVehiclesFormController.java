package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.tabPane.VehicleDetailsTabController;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.VehicleInsuranceDto;
import lk.ijse.driveHub.dto.VehicleLicenseDto;
import lk.ijse.driveHub.dto.VehicleTypeDto;
import lk.ijse.driveHub.model.VehicleInsuranceModel;
import lk.ijse.driveHub.model.VehicleLicenseModel;
import lk.ijse.driveHub.model.VehicleModel;
import lk.ijse.driveHub.model.VehicleTypeModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateVehiclesFormController implements Initializable {

    @FXML
    private JFXButton cancelBtn1;
    @FXML
    private JFXCheckBox chbVehicleBookCopy;
    @FXML
    private ComboBox<String> cmbTransmissionType;
    @FXML
    private ComboBox<String> cmbVehicleBrand;
    @FXML
    private ComboBox<String> cmbVehicleModel;
    @FXML
    private ComboBox<String> cmbVehicleType;
    @FXML
    private DatePicker dateManufactureYear;
    @FXML
    private DatePicker txtExpiryDateInsurance;
    @FXML
    private DatePicker txtExpiryDateLicense;
    @FXML
    private TextField txtInsuranceNumber;
    @FXML
    private DatePicker txtIssueDateInsurance;
    @FXML
    private DatePicker txtIssueDateLicense;
    @FXML
    private TextField txtLicenseNumber;
    @FXML
    private TextField txtPerAdditionalKmRate;
    @FXML
    private TextField txtPerDateRate;
    @FXML
    private TextField txtPerDayKm;
    @FXML
    private TextField txtRegisterNumber;
    @FXML
    private JFXButton updateBtn1;
    private String chbVehicleBookValue;

    VehicleModel vehicleModel = new VehicleModel();
    VehicleInsuranceModel vehicleInsuranceModel = new VehicleInsuranceModel();
    VehicleLicenseModel vehicleLicenseModel = new VehicleLicenseModel();
    VehicleTypeModel vehicleTypeModel = new VehicleTypeModel();
    VehicleDto vehicleDto = new VehicleDto();
    VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();
    VehicleInsuranceDto vehicleInsuranceDto = new VehicleInsuranceDto();
    VehicleLicenseDto vehicleLicenseDto = new VehicleLicenseDto();

    @FXML
    void cancelBtnOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,
                "Are you want to cancel change ?", yes, no).showAndWait();
        if (type.orElse(no) == yes) {
            Stage stage = (Stage) cancelBtn1.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void chbVehicleBookCopyOnAction(ActionEvent event) {
        if (chbVehicleBookCopy.isSelected()) {
            chbVehicleBookValue = "Yes";
        }else {
            chbVehicleBookValue = "No";
        }
    }

    @FXML
    void cmbTransmissionTypeOnAction(ActionEvent event) {

    }

    @FXML
    void cmbVehicleBrandOnAction(ActionEvent event) {

    }

    @FXML
    void cmbVehicleModelOnAction(ActionEvent event) {

    }

    @FXML
    void cmbVehicleTypeOnAction(ActionEvent event) {

    }

    @FXML
    void updateBtnOnAction(ActionEvent event) throws SQLException {
        List<VehicleTypeDto> typeDtoList = vehicleTypeModel.getAllVehicleType();
        for (VehicleTypeDto vehicleTypeDtoList:typeDtoList) {
            if (cmbVehicleType.getValue().equals(vehicleTypeDtoList.getName())) {
                vehicleTypeDto.setId(vehicleTypeDtoList.getId());
                vehicleTypeDto.setName(vehicleTypeDtoList.getName());
            }

        }
        vehicleDto.setId(VehicleDetailsTabController.vehicleTableDto.getId());
        vehicleDto.setVehicleTypeId(vehicleTypeDto.getId());
        vehicleDto.setBrand(cmbVehicleBrand.getValue());
        vehicleDto.setModel(cmbVehicleModel.getValue());
        vehicleDto.setTransmissionType(cmbTransmissionType.getValue());
        vehicleDto.setManufactureYear(dateManufactureYear.getValue());
        vehicleDto.setIsCollectedBookCopy(chbVehicleBookValue);
        vehicleDto.setRegisterNumber(txtRegisterNumber.getText());
        vehicleDto.setPerDayKm(Double.parseDouble(txtPerDayKm.getText()));
        vehicleDto.setPerDayRate(Double.parseDouble(txtPerDateRate.getText()));
        vehicleDto.setPerAdditionalKmRate(Double.parseDouble(txtPerAdditionalKmRate.getText()));

        boolean updateVehicle = vehicleModel.updateVehicle(vehicleDto);
        if (updateVehicle) {
            vehicleLicenseDto.setVehicleId(vehicleDto.getId());
            vehicleLicenseDto.setLicenseNumber(txtLicenseNumber.getText());
            vehicleLicenseDto.setIssueDate(txtIssueDateLicense.getValue());
            vehicleLicenseDto.setExpiryDate(txtExpiryDateLicense.getValue());

            boolean isUpdateLicense = vehicleLicenseModel.updateLicenseDetails(vehicleLicenseDto);
            if (isUpdateLicense) {
                vehicleInsuranceDto.setVehicleId(vehicleDto.getId());
                vehicleInsuranceDto.setInsuranceNumber(txtInsuranceNumber.getText());
                vehicleInsuranceDto.setIssueDate(txtIssueDateInsurance.getValue());
                vehicleInsuranceDto.setExpiryDate(txtExpiryDateInsurance.getValue());

                boolean isUpdateInsurance = vehicleInsuranceModel.updateInsuranceDetails(vehicleInsuranceDto);
                if (isUpdateInsurance) {
                    ButtonType ok = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,
                            "Update successfully", ok).showAndWait();
                    if (type.isPresent()) {
                        Stage stage = (Stage) updateBtn1.getScene().getWindow();
                        stage.close();
                    }
                }
            }
        }
    }

    public void setPrice() throws SQLException {
        List<VehicleDto> vehicleDtoList = vehicleModel.getAllVehicle();
        for (VehicleDto vehicle: vehicleDtoList) {
            if (VehicleDetailsTabController.vehicleTableDto.getId() == vehicle.getId()) {
                txtPerDateRate.setText(String.valueOf(vehicle.getPerDayRate()));
                txtPerDayKm.setText(String.valueOf(vehicle.getPerDayKm()));
                txtPerAdditionalKmRate.setText(String.valueOf(vehicle.getPerAdditionalKmRate()));
            }
        }
    }

    public void setData() {
        cmbVehicleType.setValue(VehicleDetailsTabController.vehicleTableDto.getType());
        cmbVehicleBrand.setValue(VehicleDetailsTabController.vehicleTableDto.getBrand());
        dateManufactureYear.setValue(LocalDate.parse(VehicleDetailsTabController.vehicleTableDto.getManufactureYear()));
        cmbTransmissionType.setValue(VehicleDetailsTabController.vehicleTableDto.getTransmissionType());
        cmbVehicleModel.setValue(VehicleDetailsTabController.vehicleTableDto.getModel());
        txtRegisterNumber.setText(VehicleDetailsTabController.vehicleTableDto.getRegisteredNumber());
    }

    public void setInsuranceDetails() {
        try {
            VehicleInsuranceDto vehicleInsuranceDtoList = vehicleInsuranceModel.getVehicleInsuranceDetails(VehicleDetailsTabController.vehicleTableDto.getId());
            if (vehicleInsuranceDtoList.getVehicleId() == VehicleDetailsTabController.vehicleTableDto.getId()) {
                txtInsuranceNumber.setText(vehicleInsuranceDtoList.getInsuranceNumber());
                txtExpiryDateInsurance.setValue(vehicleInsuranceDtoList.getExpiryDate());
                txtIssueDateInsurance.setValue(vehicleInsuranceDtoList.getIssueDate());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLicenseDetails() {
        try {
            VehicleLicenseDto vehicleLicenseDto = vehicleLicenseModel.getVehicleLicenseDetails(VehicleDetailsTabController.vehicleTableDto.getId());
            if (vehicleLicenseDto.getVehicleId() == VehicleDetailsTabController.vehicleTableDto.getId()) {
                txtLicenseNumber.setText(vehicleLicenseDto.getLicenseNumber());
                txtIssueDateLicense.setValue(vehicleLicenseDto.getIssueDate());
                txtExpiryDateLicense.setValue(vehicleLicenseDto.getExpiryDate());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllVehicleType();
        setData();
        try {
            setPrice();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setInsuranceDetails();
        setLicenseDetails();
        cmbTransmissionType.setItems(FXCollections.observableArrayList("Auto","Manual"));
        cmbVehicleBrand.setItems(FXCollections.observableArrayList("TOYOTA","AUDI","HONDA"));
        cmbVehicleBrand.setOnAction(e-> {
            if (cmbVehicleBrand.getSelectionModel().getSelectedItem().equals("TOYOTA")) {
                setCmbVehicleModelToyota();
            }
            if (cmbVehicleBrand.getSelectionModel().getSelectedItem().equals("HONDA")) {
                setCmbVehicleModelHonda();
            }
            if (cmbVehicleBrand.getSelectionModel().getSelectedItem().equals("AUDI")) {
                setCmbVehicleModelAudi();
            }
        });

    }

    public ObservableList<String> setCmbVehicleModelToyota() {
        cmbVehicleModel.setItems(FXCollections.observableArrayList(
                "Corolla HatchBack",
                "CHR",
                "Vitz",
                "Yaris ATIV",
                "Wigo",
                "AQUA",
                "PRIUS",
                "Corolla Sedan",
                "AXIO",
                "Allion"));
        return null;
    }


    private ObservableList<String> setCmbVehicleModelHonda() {
        cmbVehicleModel.setItems(FXCollections.observableArrayList(
                "Fit GE6",
                "Fit GP1",
                "Fit GP5",
                "Fit GP1",
                "FIT Shuttle",
                "Insight",
                "Grace EX",
                "Grace",
                "Civic FB1",
                "Dio"));
        return null;
    }

    private ObservableList<String> setCmbVehicleModelAudi() {
        cmbVehicleModel.setItems(FXCollections.observableArrayList(
                "A3 Sedan",
                "A4 Sedan",
                "A6 Saloon",
                "A8 L",
                "Q2",
                "Q3",
                "Q5",
                "Q7",
                "TT Coupe",
                "TTS Coupe",
                "A5 Sportback",
                "A7 Sportback"));
        return null;
    }
    private void loadAllVehicleType() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            List<VehicleTypeDto> vehicleType = vehicleTypeModel.getAllVehicleType();
            for (VehicleTypeDto vehicleTypeDto: vehicleType) {
                observableList.add(vehicleTypeDto.getName());
            }
            cmbVehicleType.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
