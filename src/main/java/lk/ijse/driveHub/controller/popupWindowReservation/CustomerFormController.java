package lk.ijse.driveHub.controller.popupWindowReservation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.closeWindow.CloseWindow;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;

import java.io.IOException;
import java.net.URL;

public class CustomerFormController {
    @FXML
    private JFXCheckBox customerCheckBox;
    @FXML
    private JFXButton closeBtn;

    @FXML
    private JFXButton nextBtn;


    static PopupWindows popupWindow = new PopupWindows();
    static CloseWindow closeWindow = new CloseWindow();

    @FXML
    void customerCheckBoxOnAction(ActionEvent event) {

    }

    @FXML
    void closeBtnOnAction(ActionEvent event) {

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException {
        popupWindow.window("/view/popupWindowReservation/vehicle_form.fxml","Select Vehicle");
        Stage stage= (Stage) nextBtn.getScene().getWindow();
        stage.close();
        closeWindow.closeWindow(nextBtn);

    }
}
