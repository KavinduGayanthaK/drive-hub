package lk.ijse.driveHub.dto.tableDto;

import lombok.*;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReservationVehicleTableDto {
    private int vehicleId;
    private String vehicleType;
    private String vehicleBrand;
    private String vehicleModel;
    private double perDayRate;
    private double additionalKmRate;

}
