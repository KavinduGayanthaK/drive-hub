package lk.ijse.driveHub.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VehicleLicenseDto {
    private int id;
    private int vehicleId;
    private String licenseNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
}
