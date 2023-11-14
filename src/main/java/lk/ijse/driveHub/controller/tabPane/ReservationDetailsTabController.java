package lk.ijse.driveHub.controller.tabPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;

import java.io.IOException;

public class ReservationDetailsTabController {

    @FXML
    private TableView<?> customerTable;
    public void createReservationBtnOnAction(ActionEvent actionEvent) throws IOException {
        PopupWindows popupWindows = new PopupWindows();
        popupWindows.window("/view/popupWindowReservation/customer_form.fxml","Customer Form");
    }
}
