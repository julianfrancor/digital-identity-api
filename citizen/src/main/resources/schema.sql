CREATE TABLE IF NOT EXISTS `citizen`
(
    `id`                  VARCHAR(255) PRIMARY KEY                   NOT NULL,
    `identification`      VARCHAR(255)                              NOT NULL,
    `identification_type` ENUM('DNI', 'PASSPORT', 'DRIVER_LICENSE') NOT NULL,
    `first_name`          VARCHAR(255)                              NOT NULL,
    `second_name`         VARCHAR(255),
    `last_name`           VARCHAR(255)                              NOT NULL,
    `second_last_name`    VARCHAR(255),
    `address`             VARCHAR(255)                              NOT NULL,
    `email`               VARCHAR(255)                              NOT NULL,
    `phone`               VARCHAR(50)                               NOT NULL,
    `status`              ENUM('ALIVE', 'DEAD', 'TRANSFERRED')      NOT NULL,
    `created_at`          DATE                                      DEFAULT NULL,
    `updated_at`          DATE                                      DEFAULT NULL,
    `deleted_at`          DATE                                      DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `document_types`
(
    `id`         VARCHAR(255) PRIMARY KEY NOT NULL,
    `type`       VARCHAR(255)            NOT NULL,
    `created_at` DATE,
    `updated_at` DATE,
    `deleted_at` DATE
);

CREATE TABLE IF NOT EXISTS `documents`
(
    `id`               VARCHAR(255) PRIMARY KEY NOT NULL,
    `citizen_id`       VARCHAR(255)             NOT NULL,
    `document_type_id` VARCHAR(255)             NOT NULL,
    `title`            VARCHAR(255)            NOT NULL,
    `url`              TEXT                    NOT NULL,
    `metadata`         JSON                    NOT NULL,
    `created_at`       DATE,
    `updated_at`       DATE,
    `deleted_at`       DATE,
    FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`),
    FOREIGN KEY (`document_type_id`) REFERENCES `document_types` (`id`)
);

CREATE TABLE IF NOT EXISTS `digital_identity_services`
(
    `id`         VARCHAR(255) PRIMARY KEY NOT NULL,
    `name`       VARCHAR(255)            NOT NULL,
    `premium`    BOOLEAN                 NOT NULL,
    `created_at` DATE,
    `updated_at` DATE,
    `deleted_at` DATE
);

CREATE TABLE IF NOT EXISTS `citizen_services`
(
    `id`                          VARCHAR(255) PRIMARY KEY NOT NULL,
    `digital_identity_service_id` VARCHAR(255)             NOT NULL,
    `citizen_id`                  VARCHAR(255)             NOT NULL,
    `subscription_date`           DATE                    NOT NULL,
    `unsubscription_date`         DATE,
    `created_at`                  DATE,
    `updated_at`                  DATE,
    `deleted_at`                  DATE,
    FOREIGN KEY (`digital_identity_service_id`) REFERENCES `digital_identity_services` (`id`),
    FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`)
);

CREATE TABLE IF NOT EXISTS `transfers`
(
    `id`            VARCHAR(255) PRIMARY KEY NOT NULL,
    `citizen_id`    VARCHAR(255)             NOT NULL,
    `operator_id`   VARCHAR(255)             NOT NULL,
    `transfer_date` DATE                    NOT NULL,
    `created_at`    DATE,
    `updated_at`    DATE,
    `deleted_at`    DATE,
    FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`)
);

CREATE TABLE IF NOT EXISTS `external_companies`
(
    `id`                          VARCHAR(255) PRIMARY KEY NOT NULL,
    `external_company_id`         VARCHAR(255)             NOT NULL,
    `digital_identity_service_id` VARCHAR(255)             NOT NULL,
    `affiliation_date`            DATE                    NOT NULL,
    `discharge_date`              DATE                    NOT NULL,
    `created_at`                  DATE,
    `updated_at`                  DATE,
    `deleted_at`                  DATE,
    FOREIGN KEY (`digital_identity_service_id`) REFERENCES `digital_identity_services` (`id`)
);

CREATE TABLE IF NOT EXISTS `external_companies_services`
(
    `id`                    VARCHAR(255) PRIMARY KEY NOT NULL,
    `name`                  VARCHAR(255)             NOT NULL,
    `external_companies_id` VARCHAR(255)             NOT NULL,
    `nit`                   VARCHAR(255)             NOT NULL,
    `address`               VARCHAR(255)             NOT NULL,
    `email`                 VARCHAR(255)             NOT NULL,
    `phone`                 VARCHAR(50)              NOT NULL,
    `created_at`            DATE,
    `updated_at`            DATE,
    `deleted_at`            DATE,
    FOREIGN KEY (`external_companies_id`) REFERENCES `external_companies` (`id`)
);
