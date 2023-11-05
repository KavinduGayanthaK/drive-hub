package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;

import java.io.IOException;
import java.net.URL;

public class VehicleOwnerFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton nextBtn;

    @FXML
    void cancelBtnOnAction(ActionEvent event) throws IOException {
        CancelFormController cancelFormController = new CancelFormController();
        cancelFormController.cancel();
    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException {
        PopupWindows popupWindows = new PopupWindows();
        popupWindows.window("/view/popupWindowVehicle/vehicle_form.fxml","Vehicle Owner");

        Stage stage = (Stage) nextBtn.getScene().getWindow();
        stage.close();
    }
}
