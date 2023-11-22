package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.driveHub.controller.tabPane.VehicleDetailsTabController;

public class UpdateVehicleFormController {
    @FXML
    private JFXButton cancelBtn;

    @FXML
    private CheckBox chbVehicleBook;

    @FXML
    private ComboBox<String> cmbTransmissionType;

    @FXML
    private ComboBox<?> cmbVehicleBrand;

    @FXML
    private ComboBox<?> cmbVehicleModel;

    @FXML
    private ComboBox<?> cmbVehicleType;

    @FXML
    private DatePicker dateManufactureYear;

    @FXML
    private TextField txtPerAdditionalKmRate;

    @FXML
    private TextField txtPerDateRate;

    @FXML
    private TextField txtPerDayKm;

    @FXML
    private TextField txtRegisterNumber;

    @FXML
    private JFXButton updateBtn;

    @FXML
    void cancelBtnOnAction(ActionEvent event) {

    }

    @FXML
    void chbVehicleBookCopyOnAction(ActionEvent event) {

    }

    @FXML
    void cmbVehicleBrandOnAction(ActionEvent event) {

    }

    @FXML
    void cmbVehicleModelOnAction(ActionEvent event) {

    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {

    }


    public void setData() {
       // cmbVehicleType.setValue(VehicleDetailsTabController.vehicleTableDto.getType());
    }
}
