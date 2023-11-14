package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.PopupWindow;
import lk.ijse.driveHub.controller.closeWindow.CloseWindow;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;
import lk.ijse.driveHub.controller.tabPane.OwnerDetailsTabController;
import lk.ijse.driveHub.dto.VehicleOwnerDto;
import lk.ijse.driveHub.model.VehicleOwnerModel;

import java.io.IOException;
import java.sql.SQLException;

public class AddOwnerFormController {
    @FXML
    private TextField txtMobileNumber;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNic;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton closeBtn;

    @FXML
    void addBtnOnAction(ActionEvent event) throws Exception {
        int id = 0;
        String firstName = txtFirstName.getText();
        String lastname = txtLastName.getText();
        String address = txtAddress.getText();
        String nic = txtNic.getText();
        String mobileNumber = txtMobileNumber.getText();
        String email = txtEmail.getText();

        try {
            VehicleOwnerDto vehicleOwnerDto = new VehicleOwnerDto(id,firstName,lastname,address,nic,mobileNumber,email);
            VehicleOwnerModel vehicleOwnerModel = new VehicleOwnerModel();
            VehicleOwnerDto isSaved = vehicleOwnerModel.saveOwner(vehicleOwnerDto);

            if (isSaved != null) {
                CloseWindow closeWindow = new CloseWindow();
                closeWindow.closeWindow(addBtn);

                new Alert(Alert.AlertType.CONFIRMATION, "Successfully saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void closeBtnOnAction(ActionEvent event) throws IOException {
        PopupWindows popupWindows = new PopupWindows();
        popupWindows.window("/view/popupWindowVehicle/cancel_form.fxml","Cancel Form");
    }
}
