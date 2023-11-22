package lk.ijse.driveHub.dto;

import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class VehicleDto {
    private int id;
    private String brand;
    private String model;
    private int vehicleTypeId;
    private String isCollectedBookCopy;
    private LocalDate manufactureYear;
    private String registerNumber;
    private String transmissionType;
    private double perDayRate;
    private double perDayKm;
    private double perAdditionalKmRate;
    private int ownerId;


}

