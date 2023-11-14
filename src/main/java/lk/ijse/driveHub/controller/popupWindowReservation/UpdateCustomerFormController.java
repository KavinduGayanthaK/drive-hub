package lk.ijse.driveHub.controller.popupWindowReservation;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import lk.ijse.driveHub.dto.CustomerDto;
import lk.ijse.driveHub.dto.tableDto.CustomerTableDto;
import lk.ijse.driveHub.model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateCustomerFormController implements Initializable {

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

    @FXML
    private JFXButton updateBtn;

    private String chbUtilityBillValue = "No";
    private String chbNicCopyValue = "No";


    CustomerDto customerDto = new CustomerDto();
    CustomerModel customerModel = new CustomerModel();
    CustomerTableDto row;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        txtFirstName.setText(row.getFirstName());
//        txtLastName.setText(row.getLastName());
//        txtAddress.setText(row.getAddress());
//        txtNic.setText(row.getNic());
//        txtMobileNumber.setText(row.getMobileNumber());
//        txtEmail.setText(row.getEmail());
    }

    @FXML
    void chbNicCopyOnAction(ActionEvent event) {
        if (chbNicCopy.isSelected()) {
            chbNicCopyValue = "Yes";
        }else {
            chbNicCopyValue = "No";
        }
    }

    @FXML
    void chbUtilityBillOnAction(ActionEvent event) {
        if (chbUtilityBill.isSelected()) {
            chbUtilityBillValue = "yes";
        }else {
            chbUtilityBillValue = "No";
        }
    }

    @FXML
    void closeBtnOnAction(ActionEvent event) {

    }


//this method not complete
    @FXML
    void updateBtnOnAction(ActionEvent event) {
       customerDto.setId(customerDto.getId());
       customerDto.setFirstName(txtFirstName.getText());
       customerDto.setLastName(txtLastName.getText());
       customerDto.setAddress(txtAddress.getText());
       customerDto.setNumber(txtMobileNumber.getText());
       customerDto.setNic(txtNic.getText());
       customerDto.setEmail(txtEmail.getText());
       customerDto.setIsUtilityBillSoftCopy(chbUtilityBillValue);
       customerDto.setIsNicSoftCopy(chbNicCopyValue);

        try {
            boolean isUpdated = customerModel.updateCustomer(customerDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION,"Update successfully!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void catchIndex(int index) {
        customerDto.setId(index);
    }

    public  void setData(CustomerTableDto row) {
      this.row = row;
    }
}
