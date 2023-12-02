package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.VehicleInsuranceDto;
import lk.ijse.driveHub.dto.VehicleLicenseDto;
import lk.ijse.driveHub.dto.VehicleOwnerDto;
import lk.ijse.driveHub.model.VehicleInsuranceModel;
import lk.ijse.driveHub.model.VehicleLicenseModel;
import lk.ijse.driveHub.model.VehicleModel;
import lk.ijse.driveHub.model.VehicleOwnerModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.driveHub.controller.popupWindowVehicle.LicenseFormController.licenseFormController;

public class InsuranceFormController implements Initializable {
    @FXML
    private JFXButton backBtn;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private JFXButton submitBtn;
    @FXML
    private TextField txtInsuranceNumber;
    @FXML
    private DatePicker txtIssueDate;
    @FXML
    private DatePicker txtExpiryDate;

    static Stage insuranceFormController = new Stage();
    VehicleInsuranceDto vehicleInsuranceDto = new VehicleInsuranceDto();
    VehicleInsuranceModel vehicleInsuranceModel = new VehicleInsuranceModel();
    VehicleLicenseModel vehicleLicenseModel = new VehicleLicenseModel();
    VehicleModel vehicleModel = new VehicleModel();
    VehicleOwnerModel vehicleOwnerModel = new VehicleOwnerModel();

    @FXML
    void backBtnOnAction(ActionEvent event) {
        setValues();
        insuranceFormController.close();
        licenseFormController.show();

    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,
                "Are you want to cancel add vehicle ?", yes, no).showAndWait();
        if (type.orElse(no) == yes) {
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void submitBtnOnAction(ActionEvent event) {
        try {
            vehicleOwnerModel.saveOwner(VehicleOwnerFormController.vehicleOwnerDto);
            if (VehicleOwnerFormController.vehicleOwnerDto.getId() != 0) {
                VehicleFormController.vehicleDto.setOwnerId(VehicleOwnerFormController.vehicleOwnerDto.getId());
                VehicleOwnerFormController.clearDto();

                vehicleModel.saveVehicle(VehicleFormController.vehicleDto);
                if (VehicleFormController.vehicleDto.getId() != 0) {
                    LicenseFormController.vehicleLicenseDto.setVehicleId(VehicleFormController.vehicleDto.getId());

                    vehicleLicenseModel.saveLicense(LicenseFormController.vehicleLicenseDto);
                    if (LicenseFormController.vehicleLicenseDto.getId() != 0) {
                        vehicleInsuranceDto.setId(0);
                        vehicleInsuranceDto.setVehicleId(VehicleFormController.vehicleDto.getId());
                        vehicleInsuranceDto.setInsuranceNumber(txtInsuranceNumber.getText());
                        vehicleInsuranceDto.setIssueDate(txtIssueDate.getValue());
                        vehicleInsuranceDto.setExpiryDate(txtExpiryDate.getValue());
                        vehicleInsuranceDto.setVehicleId(VehicleFormController.vehicleDto.getId());

                        vehicleInsuranceModel.saveInsurance(vehicleInsuranceDto);
                        if (vehicleInsuranceDto.getId() != 0) {
                            ButtonType ok = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,
                                    "Saved successfully", ok).showAndWait();
                            if (type.isPresent()) {
                                VehicleFormController.clearDto();
                                LicenseFormController.clearDto();
                                clearDto();

                                Stage stage = (Stage) submitBtn.getScene().getWindow();
                                stage.close();
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private void setValues() {
        vehicleInsuranceDto.setId(0);
        vehicleInsuranceDto.setVehicleId(VehicleFormController.vehicleDto.getId());
        vehicleInsuranceDto.setInsuranceNumber(txtInsuranceNumber.getText());
        vehicleInsuranceDto.setIssueDate(txtIssueDate.getValue());
        vehicleInsuranceDto.setExpiryDate(txtExpiryDate.getValue());
    }
    public void add(Stage licenseFormController,Stage insuranceFormController){
        LicenseFormController.licenseFormController = licenseFormController;
        this.insuranceFormController = insuranceFormController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtInsuranceNumber.setText(vehicleInsuranceDto.getInsuranceNumber());
        txtIssueDate.setValue(vehicleInsuranceDto.getIssueDate());
        txtExpiryDate.setValue(vehicleInsuranceDto.getExpiryDate());
    }
    public void clearDto() {
        vehicleInsuranceDto.setId(0);
        vehicleInsuranceDto.setVehicleId(0);
        vehicleInsuranceDto.setInsuranceNumber(null);
        vehicleInsuranceDto.setIssueDate(null);
        vehicleInsuranceDto.setExpiryDate(null);
    }
}
