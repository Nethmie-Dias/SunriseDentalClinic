DROP DATABASE IF EXISTS sunrise_dental;

CREATE DATABASE sunrise_dental;

USE sunrise_dental;

CREATE TABLE users (

                       user_id INT AUTO_INCREMENT PRIMARY KEY,

                       username VARCHAR(50) NOT NULL UNIQUE,

                       password VARCHAR(100) NOT NULL,

                       role ENUM(
        'ADMIN',
        'RECEPTIONIST',
        'PATIENT'
    ) NOT NULL,

                       patient_id INT NULL

);

CREATE TABLE patients (

                          patient_id INT AUTO_INCREMENT PRIMARY KEY,

                          patient_name VARCHAR(100) NOT NULL,

                          address VARCHAR(255) NOT NULL,

                          contact_number VARCHAR(20) NOT NULL,

                          email VARCHAR(100),

                          date_of_birth DATE,

                          gender VARCHAR(20),

                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);
CREATE TABLE dentists (

                          dentist_id INT AUTO_INCREMENT PRIMARY KEY,

                          dentist_name VARCHAR(100) NOT NULL,

                          specialization VARCHAR(100) NOT NULL,

                          contact_number VARCHAR(20),

                          available BOOLEAN DEFAULT TRUE

);
CREATE TABLE treatments (

                            treatment_id INT AUTO_INCREMENT PRIMARY KEY,

                            treatment_name VARCHAR(100) NOT NULL,

                            description VARCHAR(255),

                            treatment_cost DECIMAL(10,2) NOT NULL,

                            consultation_fee DECIMAL(10,2) NOT NULL,

                            active BOOLEAN DEFAULT TRUE

);
CREATE TABLE appointments (

                              appointment_id INT AUTO_INCREMENT PRIMARY KEY,

                              appointment_number VARCHAR(20) NOT NULL UNIQUE,

                              patient_id INT NOT NULL,

                              dentist_id INT NOT NULL,

                              treatment_id INT NOT NULL,

                              appointment_date DATE NOT NULL,

                              appointment_time TIME NOT NULL,

                              status ENUM(
        'SCHEDULED',
        'COMPLETED',
        'CANCELLED'
    ) DEFAULT 'SCHEDULED',

                              notes VARCHAR(500),

                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                              CONSTRAINT fk_appointment_patient
                                  FOREIGN KEY (patient_id)
                                      REFERENCES patients(patient_id),

                              CONSTRAINT fk_appointment_dentist
                                  FOREIGN KEY (dentist_id)
                                      REFERENCES dentists(dentist_id),

                              CONSTRAINT fk_appointment_treatment
                                  FOREIGN KEY (treatment_id)
                                      REFERENCES treatments(treatment_id),

                              CONSTRAINT unique_dentist_schedule
                                  UNIQUE (
                                          dentist_id,
                                          appointment_date,
                                          appointment_time
                                      )

);

CREATE TABLE bills (

                       bill_id INT AUTO_INCREMENT PRIMARY KEY,

                       appointment_id INT NOT NULL UNIQUE,

                       consultation_fee DECIMAL(10,2) NOT NULL,

                       treatment_cost DECIMAL(10,2) NOT NULL,

                       total_amount DECIMAL(10,2) NOT NULL,

                       payment_status ENUM(
        'UNPAID',
        'PAID'
    ) DEFAULT 'UNPAID',

                       payment_date DATETIME NULL,

                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                       CONSTRAINT fk_bill_appointment
                           FOREIGN KEY (appointment_id)
                               REFERENCES appointments(appointment_id)

);

ALTER TABLE users

    ADD CONSTRAINT fk_user_patient

        FOREIGN KEY (patient_id)

            REFERENCES patients(patient_id);

SHOW DATABASES;

USE sunrise_dental;

SHOW TABLES;

INSERT INTO patients
(patient_name, address, contact_number, email, date_of_birth, gender)
VALUES
    (
        'Nethmie Dias',
        'Colombo 05',
        '0771234567',
        'nethmie@example.com',
        '2002-05-15',
        'Female'
    ),
    (
        'Kasun Fernando',
        'Colombo 07',
        '0712345678',
        'kasun@example.com',
        '1998-08-20',
        'Male'
    ),
    (
        'Sarah Perera',
        'Dehiwala',
        '0759876543',
        'sarah@example.com',
        '2000-03-10',
        'Female'
    );

SELECT * FROM patients;

INSERT INTO dentists
(dentist_name, specialization, contact_number)
VALUES
    (
        'Dr. Fernando',
        'General Dentistry',
        '0711111111'
    ),
    (
        'Dr. Perera',
        'Orthodontics',
        '0722222222'
    ),
    (
        'Dr. Silva',
        'Dental Surgery',
        '0733333333'
    );

SELECT * FROM dentists;

INSERT INTO treatments
(
    treatment_name,
    description,
    treatment_cost,
    consultation_fee
)
VALUES
    (
        'Dental Checkup',
        'Routine dental examination',
        2500.00,
        1000.00
    ),
    (
        'Teeth Cleaning',
        'Professional dental cleaning',
        5000.00,
        1000.00
    ),
    (
        'Dental Filling',
        'Tooth filling treatment',
        7500.00,
        1000.00
    ),
    (
        'Tooth Extraction',
        'Simple tooth extraction',
        10000.00,
        1500.00
    ),
    (
        'Root Canal',
        'Root canal treatment',
        25000.00,
        2000.00
    );

SELECT * FROM treatments;
INSERT INTO users
(username, password, role)
VALUES
    (
        'admin',
        'admin123',
        'ADMIN'
    );
INSERT INTO users
(username, password, role)
VALUES
    (
        'reception',
        'rec123',
        'RECEPTIONIST'
    );
SELECT patient_id, patient_name
FROM patients;

INSERT INTO users
(username, password, role, patient_id)
VALUES
    (
        'patient01',
        'pat123',
        'PATIENT',
        1
    );

SELECT
    user_id,
    username,
    role,
    patient_id
FROM users;

SELECT
    p.patient_name,
    d.dentist_name,
    t.treatment_name,
    t.treatment_cost,
    t.consultation_fee
FROM patients p
         CROSS JOIN dentists d
         CROSS JOIN treatments t
    LIMIT 5;
UNIQUE (
    dentist_id,
    appointment_date,
    appointment_time
)