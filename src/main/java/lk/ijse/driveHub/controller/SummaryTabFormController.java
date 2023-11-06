package lk.ijse.driveHub.controller;

import javafx.event.ActionEvent;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;

import java.io.IOException;

public class SummaryTabFormController {
    public void createNewBtnOnAction(ActionEvent actionEvent) throws IOException {
        PopupWindows popupWindows = new PopupWindows();
        popupWindows.window("/view/popupWindowVehicle/VehicleOwner_form.fxml","Register Vehicle");
    }
}
