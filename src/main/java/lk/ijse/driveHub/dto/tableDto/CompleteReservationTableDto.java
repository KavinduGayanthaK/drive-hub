package lk.ijse.driveHub.dto.tableDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompleteReservationTableDto {
    private int reservationId;
    private int vehicleId;
    private String vehicleModel;
    private String registeredNumber;
    private String customerName;
    private String  reservationDate;
    private String returnDate;
    private String status;
}
