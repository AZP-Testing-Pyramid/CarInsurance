DROP database IF EXISTS car_insurance;
CREATE database car_insurance;

USE car_insurance;

CREATE TABLE `coverage`
(
    `id`                 INT(11)        NOT NULL,
    `VALID_FROM`         DATE           NOT NULL,
    `VALID_TO`           DATE           NOT NULL,
    `MIN_PREMIUM`        DECIMAL(20, 6) NOT NULL DEFAULT '0.000000',
    `MAX_PREMIUM`        DECIMAL(20, 6) NOT NULL DEFAULT '0.000000',
    `PERCENTAGE_PREMIUM` DECIMAL(20, 6) NOT NULL DEFAULT '0.000000',
    `DESCRIPTION`        VARCHAR(50)    NOT NULL DEFAULT '0'
);
