package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
import java.util.ResourceBundle;

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
    private DatePicker txtxExpiryDate;


    //static Stage licenseFormController;
    static Stage insuranceFormController;
    VehicleInsuranceDto vehicleInsuranceDto = new VehicleInsuranceDto();
    VehicleInsuranceModel vehicleInsuranceModel = new VehicleInsuranceModel();
    VehicleLicenseModel vehicleLicenseModel = new VehicleLicenseModel();
    VehicleModel vehicleModel = new VehicleModel();
    VehicleOwnerModel vehicleOwnerModel = new VehicleOwnerModel();
    VehicleFormController vehicleFormController = new VehicleFormController();


    @FXML
    void backBtnOnAction(ActionEvent event) {
        setValues();
        LicenseFormController.licenseFormController.show();
        insuranceFormController.close();
    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) {

    }

    @FXML
    void submitBtnOnAction(ActionEvent event) {
        try {
            vehicleOwnerModel.saveOwner(VehicleOwnerFormController.vehicleOwnerDto);
            if (VehicleOwnerFormController.vehicleOwnerDto.getId() != 0) {
                VehicleFormController.vehicleDto.setOwnerId(VehicleOwnerFormController.vehicleOwnerDto.getId());
                vehicleModel.saveVehicle(VehicleFormController.vehicleDto);
                if (VehicleFormController.vehicleDto.getId() != 0) {
                    LicenseFormController.vehicleLicenseDto.setVehicleId(VehicleFormController.vehicleDto.getId());
                    vehicleLicenseModel.saveLicense(LicenseFormController.vehicleLicenseDto);

                    if (LicenseFormController.vehicleLicenseDto.getId() != 0) {
                        vehicleInsuranceDto.setId(0);
                        vehicleInsuranceDto.setVehicleId(VehicleFormController.vehicleDto.getId());
                        vehicleInsuranceDto.setInsuranceNumber(txtInsuranceNumber.getText());
                        vehicleInsuranceDto.setIssueDate(txtIssueDate.getValue());
                        vehicleInsuranceDto.setExpiryDate(txtxExpiryDate.getValue());
                        vehicleInsuranceDto.setVehicleId(VehicleFormController.vehicleDto.getId());
                        vehicleInsuranceModel.saveInsurance(vehicleInsuranceDto);
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
        vehicleInsuranceDto.setExpiryDate(txtxExpiryDate.getValue());
    }
    public void add(Stage licenseFormController,Stage insuranceFormController){
        LicenseFormController.licenseFormController = licenseFormController;
        this.insuranceFormController = insuranceFormController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtInsuranceNumber.setText(vehicleInsuranceDto.getInsuranceNumber());
        txtIssueDate.setValue(vehicleInsuranceDto.getIssueDate());
        txtxExpiryDate.setValue(vehicleInsuranceDto.getExpiryDate());
    }
}
