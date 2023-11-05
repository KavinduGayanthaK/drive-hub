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

public class LicenseFormController {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton closeBtn;

    @FXML
    private JFXButton nextBtn;
    static PopupWindows popupWindows = new PopupWindows();
    static CloseWindow closeWindow = new CloseWindow();

    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        popupWindows.window("/view/popupWindowVehicle/vehicle_form.fxml","Vehicle Form");
        closeWindow.closeWindow(closeBtn);


    }

    @FXML
    void closeBtnOnAction(ActionEvent event) throws IOException {
        CancelFormController cancelFormController = new CancelFormController();
        cancelFormController.cancel();
    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException {
        popupWindows.window("/view/popupWindowVehicle/insurance_form.fxml","Insurance Form");
        closeWindow.closeWindow(nextBtn);

    }
}
