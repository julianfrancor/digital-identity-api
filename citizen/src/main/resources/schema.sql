CREATE TABLE IF NOT EXISTS citizens (
    `id` UUID PRIMARY KEY,
    `identification` VARCHAR(255) NOT NULL,
    `identification_type` VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(255) NOT NULL,
    `secondName` VARCHAR(255),
    `lastName` VARCHAR(255) NOT NULL,
    `second_last_name` VARCHAR(255),
    `address` TEXT,
    `email` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(50),
    `status` VARCHAR(255) NOT NULL,
    `created_at` date DEFAULT NULL,
    `updated_at` date DEFAULT NULL,
    `deleted_at` date DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS premium_services (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `premium` BOOLEAN NOT NULL,
    `created_at` date DEFAULT NULL,
    `updated_at` date DEFAULT NULL,
    `deleted_at` date DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS document_types (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `type` VARCHAR(255) NOT NULL,
    `created_at` date DEFAULT NULL,
    `updated_at` date DEFAULT NULL,
    `deleted_at` date DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS documents (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `citizen_id` UUID REFERENCES citizens(`id`),
    `document_type_id` UUID REFERENCES document_types(`id`),
    `title` VARCHAR(255) NOT NULL,
    `url` TEXT NOT NULL,
    `metadata` JSON,
    `created_at` date DEFAULT NULL,
    `updated_at` date DEFAULT NULL,
    `deleted_at` date DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS services_citizens (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `citizen_id` UUID REFERENCES citizens(`id`),
    `service_id` UUID REFERENCES premium_services(`id`),
    `affiliation_date` DATE,
    `discharge_date` DATE,
    `created_at` date DEFAULT NULL,
    `updated_at` date DEFAULT NULL,
    `deleted_at` date DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS transfers (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `citizen_id` UUID REFERENCES citizens(`id`),
    `operator_id` UUID,  -- Assuming there's an operators table to reference
    `transfer_date` DATE NOT NULL,
    `created_at` date DEFAULT NULL,
    `updated_at` date DEFAULT NULL,
    `deleted_at` date DEFAULT NULL
);
