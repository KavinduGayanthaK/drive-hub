DROP DATABASE IF EXISTS driveHub;
CREATE DATABASE driveHub;
USE driveHub;
CREATE TABLE `users` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE vehicleOwner (
    id int NOT NULL AUTO_INCREMENT,
    firstName varchar(100) NOT NULL,
    lastName varchar(100) NOT NULL,
    address varchar(100) NOT NULL,
    nic varchar(100) NOT NULL,
    number varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE vehicleType(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE vehicle(
    id int NOT NULL AUTO_INCREMENT,
    manufactureYear varchar(100) NOT NULL,
    brand varchar(100) NOT NULL,
    isColletedBookCopy Enum('Yes','No') NOT NULL,
    registeredNumber varchar(150) NOT NULL ,
    transMissionType Enum('auto','manual') NOT NULL,
    model varchar(150) NOT NULL ,
    vehicleTypeId int NOT NULL ,
    ownerId int NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (ownerId) REFERENCES vehicleOwner(id)  on delete cascade on update cascade,
    FOREIGN KEY (vehicleTypeId) REFERENCES vehicleType(id)  on delete cascade on update cascade
);

CREATE TABLE vehicleLicenseDetails(
    id int NOT NULL AUTO_INCREMENT,
    vehicleId int NOT NULL,
    licenseNumber varchar(100) NOT NULL,
    issueDate date NOT NULL,
    expiryDate date NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (vehicleId) REFERENCES vehicle(id)  on delete cascade on update cascade
);

CREATE TABLE vehicleInsuranceDetails(
    id int NOT NULL AUTO_INCREMENT,
    vehicleId int NOT NULL,
    insuranceNumber varchar(100) NOT NULL,
    issueDate date NOT NULL,
    expiryDate date NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (vehicleId) REFERENCES vehicle(id) on delete cascade on update cascade

);

CREATE TABLE vehicleMaintainsDetails(
    id int NOT NULL AUTO_INCREMENT,
    vehicleId int NOT NULL,
    currentMileage int NOT NULL,
    nextServiceMileage int NOT NULL,
    ServiceDate date NOT NULL,
    servicePrice int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (vehicleId) REFERENCES vehicle(id)  on delete cascade on update cascade
);

CREATE TABLE customer (
    id int NOT NULL AUTO_INCREMENT,
    nic varchar(100) NOT NULL,
    firstName varchar(100) NOT NULL,
    lastName varchar(100) NOT NULL,
    address varchar(100) NOT NULL,
    number varchar(100) NOT NULL,
    email varchar(150) NOT NULL ,
    isUtilityBillSoftCopy boolean NOT NULL,
    isNicSoftCopy boolean NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE reservation (
    id int NOT NULL AUTO_INCREMENT,
    vehicleId int NOT NULL,
    customerId int NOT NULL,
    reservationDate date NOT NULL,
    returnDate date NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (vehicleId) REFERENCES vehicle(id)  on delete cascade on update cascade,
    FOREIGN KEY (customerId) REFERENCES customer(id)  on delete cascade on update cascade
);

CREATE TABLE payment (
    id int NOT NULL AUTO_INCREMENT,
    reservationId int NOT NULL,
    amount int NOT NULL,
    type Enum('full','advanced') NOT NULL,
    about TEXT NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (reservationId) REFERENCES reservation(id)  on delete cascade on update cascade
);
CREATE TABLE offers(
    id int NOT NULL AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABlE offerDetail(
    id int NOT NULL AUTO_INCREMENT,
    vehicleId int NOT NULL ,
    offerId int NOT NULL ,
    startDate date NOT NULL ,
    endDate date NOT NULL ,
    code varchar(150) NOT NULL ,
    FOREIGN KEY (vehicleId) REFERENCES vehicle(id) on delete cascade on update cascade ,
    FOREIGN KEY (offerId) REFERENCES offers(id) on delete cascade on update cascade,
    PRIMARY KEY (id)

);

INSERT INTO users VALUE (1,'kavi12','k123','gayanthaKavindu3@gmail.com');
