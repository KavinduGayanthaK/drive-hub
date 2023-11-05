package lk.ijse.driveHub.controller.popupWindowReservation;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lk.ijse.driveHub.controller.closeWindow.CloseWindow;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;

import java.io.IOException;

public class VehicleFormController {
    @FXML
    private JFXButton closeBtn;

    @FXML
    private JFXButton nextBtn;
    static PopupWindows popupWindows = new PopupWindows();
    static CloseWindow closeWindow = new CloseWindow();

    @FXML
    void closeBtnOnAction(ActionEvent event) throws IOException {

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException {
        popupWindows.window("/view/popupWindowReservation/payment_form.fxml","Payment Form");
        closeWindow.closeWindow(nextBtn);


    }
}
