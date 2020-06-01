/*DROP TABLE IF EXISTS jobs;
CREATE TABLE jobs (
  job_id VARCHAR(20) NOT NULL,
  job_title VARCHAR(150) NOT NULL,
  min_salary BIGINT,
  max_salary BIGINT,
  PRIMARY KEY (job_id)
);*/

/*INSERT INTO jobs values("AD_PRES","President", 20080, 40000);
INSERT INTO jobs values("AD_VP","Administration Vice President", 15000, 30000);
INSERT INTO jobs values("AD_ASST","Administration Assistant", 3000, 6000);
INSERT INTO jobs values("FI_MGR","Finance Manager", 8200, 16000);
INSERT INTO jobs values("FI_ACCOUNT","Accountant", 4200, 9000);
INSERT INTO jobs values("AC_MGR","Accounting Manager", 8200, 16000);
INSERT INTO jobs values("AC_ACCOUNT","Public Accountant", 4200, 9000);
INSERT INTO jobs values("SA_MAN","Sales Manager", 10000, 20080);
INSERT INTO jobs values("SA_REP","Sales Representative", 6000, 12008);
INSERT INTO jobs values("PU_MAN","Purchasing Manager", 8000, 15000);
INSERT INTO jobs values("PU_CLERK","Purchasing Clerk", 2500, 5500);
INSERT INTO jobs values("ST_MAN","Stock Manager", 5500, 8500);
INSERT INTO jobs values("ST_CLERK","Stock Clerk", 2008, 5000);
INSERT INTO jobs values("SH_CLERK","Shipping Clerk", 2500, 5500);
INSERT INTO jobs values("IT_PROG","Programmer", 4000, 10000);
INSERT INTO jobs values("MK_MAN","Marketing Manager", 9000, 15000);
INSERT INTO jobs values("MK_REP","Marketing Representative", 4000, 9000);
INSERT INTO jobs values("HR_REP","Human Resources Representative", 4000, 9000);
INSERT INTO jobs values("PR_REP","Public Relations Representative", 4500, 10500);*/

/*DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
  id INTEGER NOT NULL,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  phone_number VARCHAR(100) NOT NULL,
  hire_date DATE NOT NULL,
  job_id VARCHAR(100) NOT NULL,
  salary DECIMAL NOT NULL,
  commission_pct DECIMAL,
  manager_id INTEGER,
  department_id INTEGER NOT NULL,
  PRIMARY KEY (id)
);*/

/*INSERT INTO employees(id, first_name, last_name, email, phone_number, hire_date, job_id, salary, department_id)
values(100, "Steven", "King", "SKING", "515.123.4567", "2003-06-03", "AD_PRES", 24000, 90);
INSERT INTO employees(id, first_name, last_name, email, phone_number, hire_date, job_id, salary, manager_id, department_id)
values(101, "Neena", "Kochhar", "NKOCHHAR", "515.123.4568", "2005-09-21", "AD_VP", 17000, 100, 90);

COMMIT;*/
/*INSERT INTO employee(empId,empName)values("emp002","emp2");
INSERT INTO employee(empId,empName)values("emp003","emp3");*/

DROP TABLE IF EXISTS flat_maintenance_lookup;

CREATE TABLE flat_maintenance_lookup (
  flat_number VARCHAR(10) NOT NULL,
  owner_name VARCHAR(200) NOT NULL,
  expected_maintenance DOUBLE,
  PRIMARY KEY (flat_number)
);

-- Ground Floor Residents
INSERT INTO flat_maintenance_lookup values("001","Abhishek Rathore", 4300);
INSERT INTO flat_maintenance_lookup values("003","Priyansh Saxena", 4400);
INSERT INTO flat_maintenance_lookup values("005","Kavya Sree", 4300);
INSERT INTO flat_maintenance_lookup values("006","Anuradha Hazarika", 4300);
INSERT INTO flat_maintenance_lookup values("007","Raja", 4300);
INSERT INTO flat_maintenance_lookup values("008","Anita Tripathi", 4300);
INSERT INTO flat_maintenance_lookup values("009","Naresh K", 4300);
INSERT INTO flat_maintenance_lookup values("010","Ananth", 4800);
INSERT INTO flat_maintenance_lookup values("011","Sailesh Ramayanam", 4400);
INSERT INTO flat_maintenance_lookup values("012","Jagadeesh", 4800);
INSERT INTO flat_maintenance_lookup values("013","Sathish", 4300);
INSERT INTO flat_maintenance_lookup values("014","Sohel Abdulajij Momin", 4300);
INSERT INTO flat_maintenance_lookup values("015","Shekhar", 4150);
INSERT INTO flat_maintenance_lookup values("016","Debashish Behera", 4300);

-- 2nd Floor residents
INSERT INTO flat_maintenance_lookup values("213","Jeet Kaushik", 4300);

DROP TABLE IF EXISTS maintenance_txn;
CREATE TABLE maintenance_txn (
  txn_id BIGINT NOT NULL,
  flat_number VARCHAR(10) NOT NULL,
  txn_date DATE NOT NULL,
  month VARCHAR(20) NOT NULL,
  year INT NOT NULL,
  actual_payment DOUBLE,
  payment_mode VARCHAR(20) NOT NULL,
  balance DOUBLE,
  PRIMARY KEY (txn_id),
  FOREIGN KEY(flat_number) REFERENCES flat_maintenance_lookup
);

INSERT INTO maintenance_txn values(101,"007", "2020-05-06", "MAY", 2020, 4500, 0);
INSERT INTO maintenance_txn values(102,"213", "2020-05-06", "MAY", 2020, 4300, 0);

commit;


