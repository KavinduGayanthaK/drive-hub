package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.closeWindow.CloseWindow;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;
import lk.ijse.driveHub.dto.VehicleInsuranceDto;
import lk.ijse.driveHub.model.VehicleInsuranceModel;
import lk.ijse.driveHub.model.VehicleLicenseModel;
import lk.ijse.driveHub.model.VehicleModel;
import lk.ijse.driveHub.model.VehicleOwnerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddInsuranceFormController implements Initializable {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXButton submitBtn;
    @FXML
    private TextField txtInsuranceNumber;

    @FXML
    private DatePicker txtExpiryDate;

    @FXML
    private DatePicker txtIssueDate;

    static Stage addInsuranceFormController = new Stage();
    VehicleInsuranceDto vehicleInsuranceDto = new VehicleInsuranceDto();
    VehicleInsuranceModel vehicleInsuranceModel = new VehicleInsuranceModel();
    VehicleLicenseModel vehicleLicenseModel = new VehicleLicenseModel();
    VehicleModel vehicleModel = new VehicleModel();
    VehicleOwnerModel vehicleOwnerModel = new VehicleOwnerModel();

    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        setValues();
        addInsuranceFormController.close();
        LicenseFormController.licenseFormController.show();

    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) throws IOException {
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
            vehicleModel.saveVehicle(AddVehicleFormController.vehicleDto);
            if (AddVehicleFormController.vehicleDto.getId() !=0) {
                AddLicenseFormController.vehicleLicenseDto.setVehicleId(AddVehicleFormController.vehicleDto.getId());
                vehicleLicenseModel.saveLicense(AddLicenseFormController.vehicleLicenseDto);
                if (AddLicenseFormController.vehicleLicenseDto.getId() !=0) {
                    setValues();
                    vehicleInsuranceDto.setVehicleId(AddVehicleFormController.vehicleDto.getId());
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
        addInsuranceFormController = insuranceFormController;
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
