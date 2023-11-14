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
    private int vehicleTypeId;
    private String brand;
    private LocalDate manufactureYear;
    private String transmissionType;
    private String model;
    private String registerNumber;
    private String isCollectedBookCopy;
    private int ownerId;



}
