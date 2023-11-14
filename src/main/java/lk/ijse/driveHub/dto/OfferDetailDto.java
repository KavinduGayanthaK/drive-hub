package lk.ijse.driveHub.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class OfferDetailDto {
    private int id;
    private int vehicleId;
    private long offerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String code;


}
