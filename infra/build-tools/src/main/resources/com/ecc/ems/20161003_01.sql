
CREATE DATABASE emsDB;

CREATE TABLE IF NOT EXISTS employee(
    emp_id SERIAL PRIMARY KEY,
    firstname character varying [30] default NULL,
    middlename character varying [30] default NULL,
    lastname character varying [30] default NULL,
    suffix character varying [5] default NULL,
    title character varying [5] default NULL,
    street character varying [30] default NULL,
    brgy character varying [30] default NULL,
    municpality character varying [30] default NULL,
    zipcode character varying [30] default NULL,
    country character varying [30] default NULL,
    bdate date default NULL,
    gwa double precision default NULL,
    date_hired date default NULL,
    emp_status boolean default true
);

CREATE TABLE IF NOT EXISTS role(
    role_id SERIAL PRIMARY KEY,
    role_name character varying [20] NOT NULL,
    role_status boolean default true    
);

CREATE TABLE IF NOT EXISTS contact(
    contact_id SERIAL PRIMARY KEY,
    contact_type character varying [20] PRIMARY KEY,
    contact_status boolean default true
);

CREATE TABLE IF NOT EXISTS emp_role(
    emp_id SERIAL REFERENCES employee,
    role_id SERIAL REFERENCES role,
    emp_role_status boolean default true
);


CREATE TABLE IF NOT EXISTS emp_contact(
    emp_id SERIAL REFERENCES employee,
    contact_id SERIAL REFERENCES contact,
    details character varying [30] NOT NULL,
    emp_contact_status boolean default true
);

INSERT INTO role (role_name) VALUES 
    ('President'),
    ('Vice President of Engineering'),
    ('Vice President of Product Innovation'),
    ('Human Resources'),
    ('Accountant'),
    ('Software Developer'),
    ('Mentor'),
    ('Trainee');

INSERT INTO contact (contact_type) VALUES 
    ('Mobile'),
    ('Landline'),
    ('Email');
    
INSERT INTO employee (firstname, middlename, lastname, suffix, title, street, brgy, municipality, zipcode, country, bdate, gwa, date_hired) VALUES 
    ('Juan', 'Cortez', 'Dela Cruz', 'III', 'Mr.', '2573 M. Dela Cruz St.', 'Brgy. 133', 'Pasay City', '1303', 'Philippines', NOW(), '1.56', NOW());
    
INSERT INTO emp_role (emp_id, role_id) VALUES 
    ((SELECT emp_id FROM employee WHERE firstname = 'Juan'),
    (SELECT role_id FROM role WHERE role_name = 'Software Developer')),
    ((SELECT emp_id FROM employee WHERE firstname = 'Juan'),
    (SELECT role_id FROM role WHERE role_name = 'Trainee'));
    
INSERT INTO emp_contact (emp_id, contact_id, details) VALUES 
    ((SELECT emp_id FROM employee WHERE firstname = 'Juan'),
    (SELECT contact_id FROM contact WHERE role_name = 'Mobile'),
    '09161645157'),
    ((SELECT emp_id FROM employee WHERE firstname = 'Juan'),
    (SELECT contact_id FROM contact WHERE role_name = 'Email'),
    'jdelacruz@exist.com');    
