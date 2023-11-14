package lk.ijse.driveHub.controller.popupWindowReservation;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.closeWindow.CloseWindow;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;
import lk.ijse.driveHub.controller.tabPane.CustomerDetailsTabController;
import lk.ijse.driveHub.dto.CustomerDto;
import lk.ijse.driveHub.model.CustomerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerFormController {

    @FXML
    private JFXButton addBtn;
    @FXML
    private CheckBox chbNicCopy;
    @FXML
    private CheckBox chbUtilityBill;
    @FXML
    private JFXButton closeBtn;
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


    private String chbUtilityBillValue = "No";
    private String chbNicCopyValue = "No";


    @FXML
    void addBtnOnAction(ActionEvent event) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(0);
        customerDto.setFirstName(txtFirstName.getText());
        customerDto.setLastName(txtLastName.getText());
        customerDto.setAddress(txtAddress.getText());
        customerDto.setNumber(txtMobileNumber.getText());
        customerDto.setNic(txtNic.getText());
        customerDto.setEmail(txtEmail.getText());
        customerDto.setIsUtilityBillSoftCopy(chbUtilityBillValue);
        customerDto.setIsNicSoftCopy(chbNicCopyValue);


        CustomerModel customerModel = new CustomerModel();
        try {
            boolean isSaved = customerModel.saveCustomer(customerDto);
            if (isSaved) {

                new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved!").show();
                Stage stage = (Stage) addBtn.getScene().getWindow();
                stage.close();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void chbNicCopyOnAction(ActionEvent event) {
//      (chbNicCopy.isSelected()) ? chbNicCopyValue = "Yes" :chbNicCopyValue = "No";
        if (chbNicCopy.isSelected()) {
            chbNicCopyValue = "Yes";
        } else {
            chbNicCopyValue = "No";
        }
    }

    @FXML
    void chbUtilityBillOnAction(ActionEvent event) {
//      (chbUtilityBill.isSelected()) ? chbUtilityBillValue ="Yes" :chbUtilityBillValue="No";
        if (chbUtilityBill.isSelected()) {
            chbUtilityBillValue = "Yse";
        } else {
            chbUtilityBillValue = "No";
        }
    }

    @FXML
    void closeBtnOnAction(ActionEvent event) {

    }
}



