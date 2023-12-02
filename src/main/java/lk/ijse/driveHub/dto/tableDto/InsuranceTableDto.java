package lk.ijse.driveHub.dto.tableDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InsuranceTableDto {
    private int id;
    private int vehicleId;
    private String insuranceNumber;
    private String issueDate;
    private String expiryDate;
}
