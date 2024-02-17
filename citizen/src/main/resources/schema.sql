CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE identification_type AS ENUM ('DNI', 'PASSPORT', 'DRIVER_LICENSE');
CREATE TYPE citizen_status AS ENUM ('ALIVE', 'DEAD', 'TRANSFERRED');

CREATE TABLE IF NOT EXISTS services (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    premium BOOLEAN NOT NULL,
    createdAt DATE,
    updatedAt DATE,
    deletedAt DATE
    );

CREATE TABLE IF NOT EXISTS citizens (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    identification VARCHAR(255) NOT NULL,
    identificationType identification_type NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    secondName VARCHAR(255),
    lastName VARCHAR(255) NOT NULL,
    secondLastName VARCHAR(255),
    address TEXT,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(50),
    status citizen_status NOT NULL,
    createdAt DATE,
    updatedAt DATE,
    deletedAt DATE
    );

CREATE TABLE IF NOT EXISTS document_types (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    type VARCHAR(255) NOT NULL,
    createdAt DATE,
    updatedAt DATE,
    deletedAt DATE
    );

CREATE TABLE IF NOT EXISTS documents (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    citizenId UUID REFERENCES citizens(id),
    documentTypeId UUID REFERENCES document_types(id),
    title VARCHAR(255) NOT NULL,
    url TEXT NOT NULL,
    metadata JSONB,
    createdAt DATE,
    updatedAt DATE,
    deletedAt DATE
    );

CREATE TABLE IF NOT EXISTS services_citizens (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    citizenId UUID REFERENCES citizens(id),
    serviceId UUID REFERENCES services(id),
    affiliationDate DATE,
    dischargeDate DATE,
    createdAt DATE,
    updatedAt DATE,
    deletedAt DATE
    );

CREATE TABLE IF NOT EXISTS transfers (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    citizenId UUID REFERENCES citizens(id),
    operatorId UUID,  -- Assuming there's an operators table to reference
    transferDate DATE NOT NULL,
    createdAt DATE,
    updatedAt DATE,
    deletedAt DATE
    );
