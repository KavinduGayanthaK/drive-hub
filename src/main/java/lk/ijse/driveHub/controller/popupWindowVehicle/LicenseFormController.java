package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;
import lk.ijse.driveHub.dto.VehicleLicenseDto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static lk.ijse.driveHub.controller.popupWindowVehicle.VehicleFormController.vehicleFormController;

public class LicenseFormController implements Initializable {

    @FXML
    private DatePicker txtExpiryDate;
    @FXML
    private DatePicker txtIssueDate;
    @FXML
    private TextField txtLicenseNumber;


    PopupWindows popupWindows = new PopupWindows();
    static Stage licenseFormController;
    static VehicleLicenseDto vehicleLicenseDto = new VehicleLicenseDto();
    static InsuranceFormController insuranceFormController = new InsuranceFormController();

    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException, SQLException {
        setValues();
        licenseFormController.close();
        vehicleFormController.show();
    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) throws IOException {
        popupWindows.window("/view/popupWindowVehicle/cancel_form.fxml","Cancel Form");
    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException, SQLException {
        setValues();
        URL resource = this.getClass().getResource("/view/popupWindowVehicle/insurance_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Insurance Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        insuranceFormController.add(licenseFormController,stage);
        licenseFormController.close();
    }

    public void add(Stage vehicleFormController,Stage licenseFormController ) {
        VehicleFormController.vehicleFormController = vehicleFormController;
        this.licenseFormController = licenseFormController;
    }

    public void setValues() throws SQLException {
        vehicleLicenseDto.setVehicleId(VehicleFormController.vehicleDto.getId());
        vehicleLicenseDto.setId(0);
        vehicleLicenseDto.setLicenseNumber(txtLicenseNumber.getText());
        vehicleLicenseDto.setIssueDate(txtIssueDate.getValue());
        vehicleLicenseDto.setExpiryDate(txtExpiryDate.getValue());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtLicenseNumber.setText(vehicleLicenseDto.getLicenseNumber());
        txtIssueDate.setValue(vehicleLicenseDto.getIssueDate());
        txtExpiryDate.setValue(vehicleLicenseDto.getExpiryDate());
    }
}
