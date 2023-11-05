package lk.ijse.driveHub.controller.popupWindowVehicle;

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

import java.io.IOException;
import java.net.URL;

public class VehicleFormController {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton closeBtn;
    @FXML
    private JFXButton nextBtn;
    static CloseWindow closeWindow = new CloseWindow();
    static PopupWindows popupWindows = new PopupWindows();

    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        popupWindows.window("/view/popupWindowVehicle/vehicleOwner_form.fxml","Vehicle Owner");
        closeWindow.closeWindow(backBtn);
    }

    @FXML
    void closeBtnOnAction(ActionEvent event) throws IOException {
        CancelFormController cancelFormController = new CancelFormController();
        cancelFormController.cancel();

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException {
        popupWindows.window("/view/popupWindowVehicle/license_form.fxml","License Details");
        closeWindow.closeWindow(nextBtn);




    }

}
