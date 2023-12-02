package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.OwnerDetailsFormController;
import lk.ijse.driveHub.controller.tabPane.OwnerDetailsTabController;
import lk.ijse.driveHub.dto.VehicleOwnerDto;
import lk.ijse.driveHub.model.VehicleOwnerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateOwnerFormController implements Initializable {
    @FXML
    private JFXButton cancelBtn;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtMobileNumber;

    @FXML
    private TextField txtNic;

    @FXML
    private JFXButton updateBtn;
    VehicleOwnerDto vehicleOwnerDto = new VehicleOwnerDto();
    VehicleOwnerModel vehicleOwnerModel = new VehicleOwnerModel();



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
    void updateBtnOnAction(ActionEvent event) throws SQLException {
        vehicleOwnerDto.setId(OwnerDetailsTabController.ownerTableDto.getId());
        vehicleOwnerDto.setFirstName(txtFirstName.getText());
        vehicleOwnerDto.setLastName(txtLastName.getText());
        vehicleOwnerDto.setAddress(txtAddress.getText());
        vehicleOwnerDto.setNic(txtNic.getText());
        vehicleOwnerDto.setMobileNumber(txtMobileNumber.getText());
        vehicleOwnerDto.setEmail(txtEmail.getText());

        boolean isUpdateOwner = vehicleOwnerModel.updateOwner(vehicleOwnerDto);
        if (isUpdateOwner) {
            new Alert(Alert.AlertType.INFORMATION,"Update Successful").show();
            Stage stage = (Stage) updateBtn.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtFirstName.setText(OwnerDetailsTabController.ownerTableDto.getFirstName());
        txtLastName.setText(OwnerDetailsTabController.ownerTableDto.getLastName());
        txtAddress.setText(OwnerDetailsTabController.ownerTableDto.getAddress());
        txtNic.setText(OwnerDetailsTabController.ownerTableDto.getNic());
        txtMobileNumber.setText(OwnerDetailsTabController.ownerTableDto.getMobileNumber());
        txtEmail.setText(OwnerDetailsTabController.ownerTableDto.getEmail());
    }
}
