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
    status varchar(20) NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE reservation (
    id int NOT NULL AUTO_INCREMENT,
    vehicleId int NOT NULL,
    customerId int NOT NULL,
    startDate varchar(20) NOT NULL,
    endDate varchar(20) NOT NULL,
    status varchar(50) NOT NULL ,
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
    date varchar(20) NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (reservationId) REFERENCES reservation(id)
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



SELECT registeredNumber
FROM vehicle
WHERE id NOT IN (SELECT DISTINCT vehicleId FROM reservation);

SELECT COUNT(*) AS numberOfVehiclesNotInReservation
FROM vehicle
         LEFT JOIN reservation ON vehicle.id = reservation.vehicleId
WHERE reservation.id IS NULL;

SELECT COUNT(*) AS numberOfVehiclesNotInReservation
FROM vehicle
         LEFT JOIN reservation ON vehicle.id = reservation.vehicleId
WHERE reservation.id IS NULL OR (reservation.startDate > CURDATE() OR reservation.endDate < CURDATE());

SELECT COUNT(*) AS numberOfCustomer FROM customer;

SELECT SUM(amount) AS totalTransaction FROM payment;

DROP TABLE  reservation;
DROP TABLE  payment;

DELETE FROM payment WHERE id = 2;

insert into customer values(3,'kavindu','gayantha','maggona','075647382','2329309093023','djkhdsjkadja','yes','yes','complete');
insert into reservation values(5,1,3,'2023-12-01','2023-12-10','Completed');
insert into reservation values(6,2,3,'2023-12-01','2023-12-10','On Renting');
SELECT
    r.id AS reservationId,
    r.vehicleId,
    v.model AS vehicleModel,
    v.registeredNumber,
    CONCAT(c.firstName, ' ', c.lastName) AS customerName,
    r.startDate AS reservationDate,
    r.endDate AS returnDate
FROM
    reservation rSELECT
    r.status AS reservationStatus,
    v.registeredNumber,
    r.startDate,
    r.endDate
FROM
    reservation r
    JOIN
    vehicle v ON r.vehicleId = v.id
WHERE
    r.status = 'On Renting'
        JOIN
    vehicle v ON r.vehicleId = v.id
        JOIN
    customer c ON r.customerId = c.id
WHERE
        r.status = 'Completed';

delete from reservation where id = 5;
