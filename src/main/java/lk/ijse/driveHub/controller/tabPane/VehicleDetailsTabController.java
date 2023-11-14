package lk.ijse.driveHub.controller.tabPane;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.closeWindow.CloseWindow;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;
import lk.ijse.driveHub.controller.popupWindowVehicle.VehicleOwnerFormController;

import java.io.IOException;
import java.net.URL;

public class VehicleDetailsTabController {
    @FXML
    private JFXButton createVehicleBtn;

    @FXML
    private JFXButton vehicleOnboardingBtn;
    static PopupWindows popupWindows = new PopupWindows();

    @FXML
    void createVehicleBtnOnAction(ActionEvent event) throws IOException {
        popupWindows.window("/view/popupWindowVehicle/addVehicle_form.fxml","Vehicle Form");
    }

    @FXML
    void vehicleOnboardingBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/popupWindowVehicle/vehicleOwner_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Register Owner");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();

        VehicleOwnerFormController.add(stage);


    }
}
