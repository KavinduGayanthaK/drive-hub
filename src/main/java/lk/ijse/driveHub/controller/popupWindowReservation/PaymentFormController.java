package lk.ijse.driveHub.controller.popupWindowReservation;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lk.ijse.driveHub.controller.closeWindow.CloseWindow;

public class PaymentFormController {
    @FXML
    private JFXButton closeBtn;

    @FXML
    private JFXButton finishBtn;

    @FXML
    void closeBtnOnAction(ActionEvent event) {

    }

    @FXML
    void finishBtnOnAction(ActionEvent event) {
        CloseWindow closeWindow = new CloseWindow();
        closeWindow.closeWindow(finishBtn);
    }
}
