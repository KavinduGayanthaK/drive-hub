package lk.ijse.driveHub.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VehicleMaintainsDto {
    private int id;
    private int vehicleId;
    private int currentMileage;
    private int nextServiceMileage;
    private LocalDate serviceDate;
    private double price;


}
