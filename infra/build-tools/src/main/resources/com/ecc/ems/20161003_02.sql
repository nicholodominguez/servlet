
CREATE DATABASE emsdb;

CREATE TABLE IF NOT EXISTS employee(
    emp_id SERIAL PRIMARY KEY,
    firstname varchar(30) default NULL,
    middlename varchar(30) default NULL,
    lastname varchar(30) default NULL,
    suffix varchar(5) default NULL,
    title varchar(5) default NULL,
    street varchar(30) default NULL,
    brgy varchar(30) default NULL,
    municipality varchar(30) default NULL,
    zipcode varchar(30) default NULL,
    country varchar(30) default NULL,
    bdate date default NULL,
    gwa double precision default NULL,
    date_hired date default NULL,
    emp_status boolean default true
);

CREATE TABLE IF NOT EXISTS role(
    role_id SERIAL PRIMARY KEY,
    role_name varchar(100) NOT NULL,
    role_status boolean default true    
);

CREATE TABLE IF NOT EXISTS contact(
    contact_id SERIAL PRIMARY KEY,
    contact_type varchar(20) NOT NULL,
    contact_status boolean default true
);

CREATE TABLE IF NOT EXISTS emp_role(
    emp_role_id SERIAL PRIMARY KEY,
    emp_id SERIAL REFERENCES employee,
    role_id SERIAL REFERENCES role,
    emp_role_status boolean default true
);


CREATE TABLE IF NOT EXISTS emp_contact(
    emp_contact_id SERIAL PRIMARY KEY,
    emp_id SERIAL REFERENCES employee,
    contact_id SERIAL REFERENCES contact,
    details varchar(20) NOT NULL,
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
    (SELECT contact_id FROM contact WHERE contact_type = 'Mobile'),
    '09161645157'),
    ((SELECT emp_id FROM employee WHERE firstname = 'Juan'),
    (SELECT contact_id FROM contact WHERE contact_type = 'Email'),
    'jdelacruz@exist.com');    
