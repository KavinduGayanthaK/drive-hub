package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;
import lk.ijse.driveHub.dto.VehicleOwnerDto;
import lk.ijse.driveHub.model.VehicleOwnerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Pattern;


public class VehicleOwnerFormController {

    @FXML
    private JFXButton cancelBtn;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton nextBtn;
    @FXML
    private JFXCheckBox ownerCheckBox;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtMail;
    @FXML
    private TextField txtNic;
    @FXML
    private TextField txtNumber;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblFirstName;
    @FXML
    private Label lblLastName;
    @FXML
    private Label lblMobileNumber;
    @FXML
    private Label lblNic;

    static Stage vehicleOwnerFormController;
    VehicleFormController vehicleFormController = new VehicleFormController();
    static VehicleOwnerDto vehicleOwnerDto = new VehicleOwnerDto();

    @FXML
    void cancelBtnOnAction(ActionEvent event) throws IOException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,
                "Are you want to cancel vehicle onboarding process ?", yes, no).showAndWait();
        if (type.orElse(no) == yes) {
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException, SQLException {
     // boolean isValidate = validateOwner();
        //if (isValidate) {
            vehicleOwnerDto.setId(0);
            vehicleOwnerDto.setFirstName(txtFirstName.getText());
            vehicleOwnerDto.setLastName(txtLastName.getText());
            vehicleOwnerDto.setAddress(txtAddress.getText());
            vehicleOwnerDto.setNic(txtNic.getText());
            vehicleOwnerDto.setMobileNumber(txtNumber.getText());
            vehicleOwnerDto.setEmail(txtMail.getText());

            URL resource = this.getClass().getResource("/view/popupWindowVehicle/vehicle_form.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(resource);
            Parent load = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Vehicle Owner");
            stage.setScene(new Scene(load));
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();

            vehicleOwnerFormController.close();
            vehicleFormController.add(vehicleOwnerFormController,stage);
        //}
    }
    @FXML
    void ownerCheckBoxOnAction(ActionEvent event) {

    }
    public static void add(Stage stage) {
        vehicleOwnerFormController = stage;

    }

    public boolean validateOwner() {
        boolean isNic = Pattern.matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$",txtNic.getText());
        if (!isNic) {
            lblNic.setText("Invalid Nic");
            return false;
        }
        else {
            lblNic.setText("");

        }

        boolean isNumber = Pattern.matches("^(?:7|0|(?:\\+94))[0-9]{9,10}$",txtNumber.getText());
        if (!isNumber) {
            lblMobileNumber.setText("Invalid MobileNumber");
            return false;
        }else {
            lblMobileNumber.setText("");

        }

        boolean isEmail = Pattern.matches("^[a-zA-Z0-9_! #$%&'*+/=?`{|}~^. -]+@[a-zA-Z0-9. -]+$",txtMail.getText());
        if (!isEmail) {
            lblEmail.setText("Invalid Email");
            return false;
        }else {
            lblEmail.setText("");

        }
        return true;
    }

    public static void clearDto() {
        vehicleOwnerDto.setId(0);
        vehicleOwnerDto.setFirstName(null);
        vehicleOwnerDto.setLastName(null);
        vehicleOwnerDto.setAddress(null);
        vehicleOwnerDto.setNic(null);
        vehicleOwnerDto.setMobileNumber(null);
        vehicleOwnerDto.setEmail(null);
    }

}
