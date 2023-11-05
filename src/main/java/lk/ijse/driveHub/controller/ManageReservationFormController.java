package lk.ijse.driveHub.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;

import java.io.IOException;

public class ManageReservationFormController {
    @FXML
    void createBtnOnAction(ActionEvent event) throws IOException {
        PopupWindows popupWindows = new PopupWindows();
        popupWindows.window("/view/popupWindowReservation/customer_form.fxml","Add Customer");
    }
}
