package lk.ijse.driveHub.dto.tableDto;

import lombok.*;

import java.awt.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationTableDto {
    private int reservationId;
    private int vehicleId;
    private String vehicleModel;
    private String registeredNumber;
    private String customerName;
    private String reservationDate;
    private String returnDate;
    private String status;


}
