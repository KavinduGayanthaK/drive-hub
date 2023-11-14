package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.closeWindow.CloseWindow;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;

import java.io.IOException;

public class AddVehicleFormController {
    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXCheckBox chbVehicleBook;

    @FXML
    private ComboBox<?> cmbTransmissionType;

    @FXML
    private ComboBox<?> cmbvehicleType;

    @FXML
    private JFXButton nextBtn;

    @FXML
    private DatePicker txtManufactureYear;

    @FXML
    private TextField txtRegisterNumber;

    @FXML
    private TextField txtVehicleBrand;

    @FXML
    private TextField txtVehicleModel;

    @FXML
    void cancelBtnOnAction(ActionEvent event) {

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) {

    }
}
