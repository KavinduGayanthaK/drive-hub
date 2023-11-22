DROP DATABASE IF EXISTS driveHub;
CREATE DATABASE driveHub;
USE driveHub;
CREATE TABLE `users` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
     type varchar(20) NOT NULL,
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
    brand varchar(100) NOT NULL,
    model varchar(150) NOT NULL ,
    vehicleTypeId int NOT NULL ,
    isCollectedBookCopy varchar(20) NOT NULL,
    manufactureYear varchar(20) NOT NULL,
    registeredNumber varchar(150) NOT NULL ,
    transMissionType varchar(20) NOT NULL,
    perDayRate double(8,2) NOT NULL ,
    perDayKm double(8,2) NOT NULL ,
    perAdditionalKmRate double(8,2) NOT NULL ,
    ownerId int NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (ownerId) REFERENCES vehicleOwner(id),
    FOREIGN KEY (vehicleTypeId) REFERENCES vehicleType(id)
);

CREATE TABLE vehicleLicenseDetails(
    id int NOT NULL AUTO_INCREMENT,
    vehicleId int NOT NULL,
    licenseNumber varchar(100) NOT NULL,
    issueDate varchar(20) NOT NULL,
    expiryDate varchar(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (vehicleId) REFERENCES vehicle(id)
);

CREATE TABLE vehicleInsuranceDetails(
    id int NOT NULL AUTO_INCREMENT,
    vehicleId int NOT NULL,
    insuranceNumber varchar(100) NOT NULL,
    issueDate varchar(20) NOT NULL,
    expiryDate varchar(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (vehicleId) REFERENCES vehicle(id)

);

CREATE TABLE vehicleMaintainsDetails(
    id int NOT NULL AUTO_INCREMENT,
    vehicleId int NOT NULL,
    currentMileage int NOT NULL,
    nextServiceMileage int NOT NULL,
    ServiceDate varchar(20) NOT NULL,
    servicePrice int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (vehicleId) REFERENCES vehicle(id)
);

CREATE TABLE customer (
    id int NOT NULL AUTO_INCREMENT,
    firstName varchar(100) NOT NULL,
    lastName varchar(100) NOT NULL,
    address varchar(100) NOT NULL,
    number varchar(100) NOT NULL,
    nic varchar(100) NOT NULL,
    email varchar(150) NOT NULL ,
    isUtilityBillSoftCopy varchar(10) NOT NULL,
    isNicSoftCopy varchar(10) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE reservation (
    id int NOT NULL AUTO_INCREMENT,
    vehicleId int NOT NULL,
    customerId int NOT NULL,
    startDate varchar(20) NOT NULL,
    endDate varchar(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (vehicleId) REFERENCES vehicle(id) ,
    FOREIGN KEY (customerId) REFERENCES customer(id)
);

CREATE TABLE payment (
    id int NOT NULL AUTO_INCREMENT,
    reservationId int NOT NULL,
    depositAmount double NOT NULL ,
    amount double NOT NULL,
    type varchar(20) NOT NULL,
    about TEXT NOT NULL ,
    date varchar(20) NOT NULL ,leDto
    PRIMARY KEY (id),
    FOREIGN KEY (reservationId) REFERENCES reservation(id)
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
    startDate varchar(20) NOT NULL ,
    endDate varchar(20) NOT NULL ,
    code varchar(150) NOT NULL ,
    FOREIGN KEY (vehicleId) REFERENCES vehicle(id) ,
    FOREIGN KEY (offerId) REFERENCES offers(id) ,
    PRIMARY KEY (id)

);

INSERT INTO users VALUE (1,'1','1','gayanthaKavindu3@gmail.com','ADMIN');
INSERT INTO users VALUE (2,'2','2','gayantha3@gmail.com','CASHIER');
INSERT INTO vehicleType(id,name) VALUES(1,'Sedan'),
                                       (2,'SUV'),
                                       (3,'HatchBack'),
                                       (4,'Jeep'),
                                       (5,'Minivan'),
                                       (6,'Coupe'),
                                       (7,'UTE');



select * from customer where nic = '200117802995';

select * from vehicleOwner;

select * from vehicle;
select * from vehicleLicenseDetails;

SELECT
    v.id AS vehicle_id,
    vt.name AS vehicle_type,
    v.model,
    v.perDayRate,
    v.perAdditionalKmRate
FROM
    vehicle v
        JOIN
    vehicleType vt ON v.vehicleTypeId = vt.id
        LEFT JOIN
    reservation r ON v.id = r.vehicleId
        AND (
                             ('2023-10-20' BETWEEN r.startDate AND r.endDate)
                             OR ('2023-10-30' BETWEEN r.startDate AND r.endDate)
                             OR (r.startDate BETWEEN '2023-10-20' AND '2023-10-30')
                         )
WHERE
    r.id IS NULL
   OR r.startDate IS NULL;


INSERT INTO reservation(id, vehicleId, customerId, startDate, endDate)  VALUES
 drop table payment;                                                                                    (1,'1','1','2023-10-15','2023-10-25');

select * from reservation;

select * from payment;