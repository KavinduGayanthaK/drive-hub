package lk.ijse.driveHub.controller.popupWindowReservation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.driveHub.dto.CustomerDto;

import java.io.IOException;
import java.net.URL;

public class CustomerFormController {
    @FXML
    private JFXCheckBox chbNicCopy;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXCheckBox chbUtilityBilCopy;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXCheckBox customerCheckBox;

    @FXML
    private JFXButton nextBtn;

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

    static CustomerDto customerDto = new CustomerDto();
    PaymentFormController paymentFormController = new PaymentFormController();
    private String chbUtilityBillCopyValue;
    private String chbNicCopyValue;
    static Stage  customerFormController;



    @FXML
    void backBtnOnAction(ActionEvent event) {
        ReservationVehicleFormController.reservationVehicleFormStage.show();
        customerFormController.close();
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
    void chbUtilityBillCopyOnAction(ActionEvent event) {
        if (chbUtilityBilCopy.isSelected()) {
            chbUtilityBillCopyValue = "Yes";
        }else {
            chbUtilityBillCopyValue = "No";
        }
    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) {

    }

    @FXML
    void customerCheckBoxOnAction(ActionEvent event) {

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException {
        setValues();
        URL resource = this.getClass().getResource("/view/popupWindowReservation/payment_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Vehicle Form");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();

        customerFormController.close();
        paymentFormController.add(stage,customerFormController);

    }

    private void setValues() {
        customerDto.setId(0);
        customerDto.setFirstName(txtFirstName.getText());
        customerDto.setLastName(txtLastName.getText());
        customerDto.setAddress(txtAddress.getText());
        customerDto.setNic(txtNic.getText());
        customerDto.setNumber(txtMobileNumber.getText());
        customerDto.setEmail(txtEmail.getText());
        customerDto.setIsNicSoftCopy(chbNicCopyValue);
        customerDto.setIsUtilityBillSoftCopy(chbUtilityBillCopyValue);
    }

    public void add(Stage stage,Stage reservationVehicleFormController) {
        ReservationVehicleFormController.reservationVehicleFormStage = reservationVehicleFormController;
        customerFormController = stage;
    }
}

