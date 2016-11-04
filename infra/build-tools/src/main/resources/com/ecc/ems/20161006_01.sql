
DROP TABLE emp_contact;

TRUNCATE TABLE contact;
ALTER TABLE contact ADD COLUMN contact_details varchar(50);
ALTER TABLE contact ADD COLUMN emp_id SERIAL REFERENCES employee;

INSERT INTO contact (emp_id, contact_type, contact_details) VALUES
    ((SELECT emp_id FROM employee WHERE firstname = 'John'), 'Mobile', '09161645157'),
    ((SELECT emp_id FROM employee WHERE firstname = 'John'), 'Landline', '1234567'),
    ((SELECT emp_id FROM employee WHERE firstname = 'John'), 'Email', 'jncdominguez@exist.com');
