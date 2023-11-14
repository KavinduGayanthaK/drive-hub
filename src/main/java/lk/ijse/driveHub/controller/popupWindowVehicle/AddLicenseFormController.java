package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.driveHub.controller.closeWindow.CloseWindow;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;

import java.io.IOException;

public class AddLicenseFormController {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXButton nextBtn;

    @FXML
    private DatePicker txtExpiryDate;

    @FXML
    private DatePicker txtIssueDate;
    @FXML
    private TextField txtLicenseNumber;

    @FXML
    void backBtnOnAction(ActionEvent event) {

    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) {

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) {

    }
}
