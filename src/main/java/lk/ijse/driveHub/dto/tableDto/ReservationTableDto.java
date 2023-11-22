package lk.ijse.driveHub.dto.tableDto;

import lombok.*;

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
    private LocalDate reservationDate;
    private LocalDate returnDate;

}
