package lk.ijse.driveHub.dto.tableDto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class VehicleTableDto {
    private int id;
    private String brand;
    private String model;
    private String type;
    private String bookCopy;
    private String manufactureYear;
    private String registeredNumber;
    private String transmissionType;

}
