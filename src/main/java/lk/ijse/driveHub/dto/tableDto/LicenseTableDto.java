package lk.ijse.driveHub.dto.tableDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LicenseTableDto {
    private int id;
    private int vehicleId;
    private String licenseNumber;
    private String issueDate;
    private String expiryDate;
}
