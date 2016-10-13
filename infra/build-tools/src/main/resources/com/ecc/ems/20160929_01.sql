
DROP DATABASE emsDB;
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
