package lk.ijse.driveHub.dto.tableDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationCalenderDto {
    private String status;
    private String registerNumber;
    private String startDate;
    private String endDate;

}
