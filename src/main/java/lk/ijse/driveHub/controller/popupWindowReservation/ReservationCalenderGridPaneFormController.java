package lk.ijse.driveHub.controller.popupWindowReservation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.tableDto.ReservationCalenderDto;
import lk.ijse.driveHub.model.ReservationModel;
import lk.ijse.driveHub.model.VehicleModel;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationCalenderGridPaneFormController implements Initializable {
    public static int x;//
    @FXML
    private Label lblEndDate;

    @FXML
    private Label lblRegisterNumber;

    @FXML
    private Label lblStartDate;

    @FXML
    private Label lblrenting;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ReservationModel reservationModel = new ReservationModel();//
        List<ReservationCalenderDto> reservationCalenderDtos = reservationModel.getReservation();//
        lblrenting.setText(reservationCalenderDtos.get(x).getStatus());
        lblEndDate.setText(reservationCalenderDtos.get(x).getEndDate());
        lblStartDate.setText(reservationCalenderDtos.get(x).getStartDate());
        lblRegisterNumber.setText(reservationCalenderDtos.get(x).getRegisterNumber());

    }
}
