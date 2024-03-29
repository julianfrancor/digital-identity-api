CREATE TABLE IF NOT EXISTS `citizen`
(
    `id`                  int AUTO_INCREMENT  PRIMARY KEY       NOT NULL,
    `identification`      BIGINT                                NOT NULL,
    `identification_type` VARCHAR(255)                          NOT NULL,
    `full_name`           VARCHAR(255)                          NOT NULL,
    `address`             VARCHAR(255)                          NOT NULL,
    `email`               VARCHAR(255)                          NOT NULL,
    `phone`               VARCHAR(50)                           NOT NULL,
    `status`              ENUM('ALIVE', 'DEAD', 'TRANSFERRED')  NOT NULL,
    `created_at`          DATE                                  DEFAULT NULL,
    `updated_at`          DATE                                  DEFAULT NULL,
    `deleted_at`          DATE                                  DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `document`
(
    `id`               int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `citizen_id`       int                            NOT NULL,
    `document_type_id` VARCHAR(255)                   NOT NULL,
    `title`            VARCHAR(255)                   NOT NULL,
    `url`              TEXT                           NOT NULL,
    `metadata`         TEXT                           NOT NULL,
    `created_at`       DATE,
    `updated_at`       DATE,
    `deleted_at`       DATE,
    FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`)
);

CREATE TABLE IF NOT EXISTS `digital_identity_services`
(
    `id`         int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `name`       VARCHAR(255)                   NOT NULL,
    `is_premium` BOOLEAN                        NOT NULL,
    `created_at` DATE,
    `updated_at` DATE,
    `deleted_at` DATE
);

CREATE TABLE IF NOT EXISTS `citizen_subscription`
(
    `id`                          int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `digital_identity_service_id` int                            NOT NULL,
    `citizen_id`                  int                            NOT NULL,
    `subscription_date`           DATE                           NOT NULL,
    `unsubscription_date`         DATE,
    `is_active`                   BOOLEAN,
    `created_at`                  DATE,
    `updated_at`                  DATE,
    `deleted_at`                  DATE,
    FOREIGN KEY (`digital_identity_service_id`) REFERENCES `digital_identity_services` (`id`),
    FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`)
);

CREATE TABLE IF NOT EXISTS `transfer`
(
    `id`            int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `citizen_id`    int                            NOT NULL,
    `operator_id`   VARCHAR(255)                   NOT NULL,
    `transfer_date` DATE                           NOT NULL,
    `created_at`    DATE,
    `updated_at`    DATE,
    `deleted_at`    DATE,
    FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`)
);

CREATE TABLE IF NOT EXISTS `external_company`
(
    `id`                          int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `external_company_id`         int                            NOT NULL UNIQUE,
    `digital_identity_service_id` int                            NOT NULL,
    `affiliation_date`            DATE                           NOT NULL,
    `discharge_date`              DATE                           NOT NULL,
    `created_at`                  DATE,
    `updated_at`                  DATE,
    `deleted_at`                  DATE,
    FOREIGN KEY (`digital_identity_service_id`) REFERENCES `digital_identity_services` (`id`)
);

CREATE TABLE IF NOT EXISTS `offered_solution`
(
    `id`                    int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `name`                  VARCHAR(255)                   NOT NULL,
    `external_company_id`   int                            NOT NULL,
    `nit`                   VARCHAR(255)                   NOT NULL,
    `address`               VARCHAR(255)                   NOT NULL,
    `email`                 VARCHAR(255)                   NOT NULL,
    `phone`                 VARCHAR(50)                    NOT NULL,
    `created_at`            DATE,
    `updated_at`            DATE,
    `deleted_at`            DATE,
    FOREIGN KEY (`external_company_id`) REFERENCES `external_company` (`id`)
);
