package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;
import lk.ijse.driveHub.dto.VehicleOwnerDto;
import lk.ijse.driveHub.model.VehicleOwnerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;


public class VehicleOwnerFormController {

    @FXML
    private JFXButton closeBtn;
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

    static Stage vehicleOwnerFormController;
    VehicleFormController vehicleFormController = new VehicleFormController();
    VehicleOwnerModel vehicleOwnerModel = new VehicleOwnerModel();
    static VehicleOwnerDto vehicleOwnerDto = new VehicleOwnerDto();

    @FXML
    void cancelBtnOnAction(ActionEvent event) throws IOException {

        PopupWindows popupWindows = new PopupWindows();
        popupWindows.window("/view/popupWindowVehicle/cancel_form.fxml","Cancel Form");

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException, SQLException {

        vehicleOwnerDto.setId(0);
        vehicleOwnerDto.setFirstName(txtFirstName.getText());
        vehicleOwnerDto.setLastName(txtLastName.getText());
        vehicleOwnerDto.setAddress(txtAddress.getText());
        vehicleOwnerDto.setNic(txtNic.getText());
        vehicleOwnerDto.setMobileNumber(txtNumber.getText());
        vehicleOwnerDto.setEmail(txtMail.getText());
       // vehicleOwnerModel.saveOwner(vehicleOwnerDto);

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
    }
    @FXML
    void ownerCheckBoxOnAction(ActionEvent event) {

    }
    public static void add(Stage stage) {
        vehicleOwnerFormController = stage;

    }



}
