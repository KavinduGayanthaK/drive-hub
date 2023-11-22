package lk.ijse.driveHub.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationDto {
    private int id;
    private int vehicleId;
    private int customerId;
    private LocalDate startDate;
    private LocalDate endDate;

}
