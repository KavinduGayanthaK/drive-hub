package lk.ijse.driveHub.controller.popupWindowReservation;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lk.ijse.driveHub.controller.closeWindow.CloseWindow;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;

import java.io.IOException;

public class PaymentFormController {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton closeBtn;

    @FXML
    private JFXButton submitBtn;

    static CloseWindow closeWindow = new CloseWindow();
    static PopupWindows popupWindows = new PopupWindows();
    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        popupWindows.window("/view/popupWindowReservation/vehicle_form.fxml","Vehicle Form");
        closeWindow.closeWindow(backBtn);

    }

    @FXML
    void closeBtnOnAction(ActionEvent event) {

    }

    @FXML
    void submitBtnOnAction(ActionEvent event) {
        closeWindow.closeWindow(submitBtn);
    }
}
