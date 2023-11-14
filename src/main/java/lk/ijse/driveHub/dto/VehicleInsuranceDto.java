package lk.ijse.driveHub.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class VehicleInsuranceDto {
    private int id;
    private int vehicleId;
    private String insuranceNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
}
