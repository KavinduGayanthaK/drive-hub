package lk.ijse.driveHub.controller.tabPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;
import lk.ijse.driveHub.controller.popupWindowReservation.ReservationCalenderGridPaneFormController;
import lk.ijse.driveHub.dto.ReservationDto;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.tableDto.ReservationCalenderDto;
import lk.ijse.driveHub.dto.tableDto.ReservationTableDto;
import lk.ijse.driveHub.model.CustomerModel;
import lk.ijse.driveHub.model.ReservationModel;
import lk.ijse.driveHub.model.VehicleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationCalenderTabController implements Initializable {

    @FXML
    private GridPane gridpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int column = 0 ;
        int row = 0 ;
        ReservationModel reservationModel = new ReservationModel();//
        List<ReservationCalenderDto> reservationCalenderDtos = null;//
        try {
            reservationCalenderDtos = reservationModel.getReservation();//
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < reservationCalenderDtos.size(); i++) {
            ReservationCalenderGridPaneFormController.x = i ;
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("/view/reservationCalenderGridPane_form.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            gridpane.add(parent,column,row++);
            GridPane.setMargin(parent,new Insets(5,5,5,5));
        }

    }
}
