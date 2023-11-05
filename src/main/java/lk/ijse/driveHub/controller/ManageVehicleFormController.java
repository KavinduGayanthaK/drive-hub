package lk.ijse.driveHub.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.popupWindowVehicle.VehicleOwnerFormController;

import java.io.IOException;
import java.net.URL;

public class ManageVehicleFormController {
    @FXML
    void createBtnOnAction(ActionEvent event) throws IOException {
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

    }

}
