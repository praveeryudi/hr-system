/*DROP TABLE IF EXISTS oc_fee;
commit;

CREATE TABLE oc_fee (
  fee_id BIGINT NOT NULL,
  flat_number VARCHAR(10) NOT NULL,
  actual_payment DOUBLE,
  payment_mode VARCHAR(20) NOT NULL,
  PRIMARY KEY (fee_id)
);

INSERT INTO oc_fee values(1,'307', 10000, 'Gpay');
INSERT INTO oc_fee values(2,'216', 10000, 'Gpay');

COMMIT;*/

--  SRIVEN MAINTENANCE DB TABLES
--DROP TABLE IF EXISTS maintenance_txn;
--DROP TABLE IF EXISTS flat_maintenance_lookup;
--commit;

/*CREATE TABLE IF NOT EXISTS flat_maintenance_lookup (
  flat_number VARCHAR(10) NOT NULL,
  owner_name VARCHAR(200) NOT NULL,
  expected_maintenance DOUBLE,
  PRIMARY KEY (flat_number)
);*/

-- Ground Floor Residents
/*INSERT INTO flat_maintenance_lookup values('001','Abhishek Rathore', 4300);
INSERT INTO flat_maintenance_lookup values('003','Priyansh Saxena', 4400);
INSERT INTO flat_maintenance_lookup values('005','Kavya Sree', 4300);
INSERT INTO flat_maintenance_lookup values('006','Anuradha Hazarika', 4300);
INSERT INTO flat_maintenance_lookup values('007','Raja', 4300);
INSERT INTO flat_maintenance_lookup values('008','Anita Tripathi', 4300);
INSERT INTO flat_maintenance_lookup values('009','Naresh K', 4300);
INSERT INTO flat_maintenance_lookup values('010','Ananth', 4800);
INSERT INTO flat_maintenance_lookup values('011','Sailesh Ramayanam', 4400);
INSERT INTO flat_maintenance_lookup values('012','Jagadeesh', 4800);
INSERT INTO flat_maintenance_lookup values('013','Sathish', 4300);
INSERT INTO flat_maintenance_lookup values('014','Sohel Abdulajij Momin', 4300);
INSERT INTO flat_maintenance_lookup values('015','Shekhar', 4150);
INSERT INTO flat_maintenance_lookup values('016','Debashish Behera', 4300);*/

-- 1st Floor residents
/*INSERT INTO flat_maintenance_lookup values('101','Nilesh More', 4300);
INSERT INTO flat_maintenance_lookup values('102','Pradeepta Swain', 4800);
INSERT INTO flat_maintenance_lookup values('103','Kaushik', 4400);
INSERT INTO flat_maintenance_lookup values('104','Nirmal Paramban', 4800);
INSERT INTO flat_maintenance_lookup values('105','Sonit Patel', 4300);
INSERT INTO flat_maintenance_lookup values('106','Dhanendra Jain', 4300);
INSERT INTO flat_maintenance_lookup values('107','Swathi', 4300);
INSERT INTO flat_maintenance_lookup values('108','Deepak Kumar', 4300);
INSERT INTO flat_maintenance_lookup values('109','Aakash Gulati', 4300);
INSERT INTO flat_maintenance_lookup values('110','Rohit Chakraborty', 4800);
INSERT INTO flat_maintenance_lookup values('111','Ambuj', 4400);
INSERT INTO flat_maintenance_lookup values('112','Ashish Jena', 4800);
INSERT INTO flat_maintenance_lookup values('113','Indraneel Baul', 4300);
INSERT INTO flat_maintenance_lookup values('114','Mahesh Gopalakrishnan', 4300);
INSERT INTO flat_maintenance_lookup values('115','Ajith M', 4300);
INSERT INTO flat_maintenance_lookup values('116','Deepak', 4300);*/

-- 2nd Floor residents
/*INSERT INTO flat_maintenance_lookup values('201','Anurag Hathimare', 4300);
INSERT INTO flat_maintenance_lookup values('202','Hardik', 4800);
INSERT INTO flat_maintenance_lookup values('203','Smita Pattanaik', 4400);
INSERT INTO flat_maintenance_lookup values('204','Dhivya Arumugam', 4800);
INSERT INTO flat_maintenance_lookup values('205','Nabil N Sayyad', 4300);
INSERT INTO flat_maintenance_lookup values('206','Subhranshu', 4300);
INSERT INTO flat_maintenance_lookup values('207','Rini Namratha', 4300);
INSERT INTO flat_maintenance_lookup values('208','Dhinesh Kumar', 4300);
INSERT INTO flat_maintenance_lookup values('209','Amjad Ali Khan Pathan', 4300);
INSERT INTO flat_maintenance_lookup values('210','Sonima Dev', 4800);
INSERT INTO flat_maintenance_lookup values('211','Harish', 4400);
INSERT INTO flat_maintenance_lookup values('212','Lalatendu Das', 4800);
INSERT INTO flat_maintenance_lookup values('213','Jeet Kaushik', 4300);
INSERT INTO flat_maintenance_lookup values('214','Gurusiddesh Kudiker', 4300);
INSERT INTO flat_maintenance_lookup values('215','Himanshu Gupta', 4300);
INSERT INTO flat_maintenance_lookup values('216','Gopichand', 4300);*/

-- 3rd Floor residents
/*INSERT INTO flat_maintenance_lookup values('301','Chetan Yamger', 4300);
INSERT INTO flat_maintenance_lookup values('302','Piyush Jain', 4800);
INSERT INTO flat_maintenance_lookup values('303','Sujit', 4400);
INSERT INTO flat_maintenance_lookup values('304','Layeequr Rahman', 4800);
INSERT INTO flat_maintenance_lookup values('305','Rahul Raina', 4300);
INSERT INTO flat_maintenance_lookup values('306','Dhaval', 4300);
INSERT INTO flat_maintenance_lookup values('307','Praveer', 4300);
INSERT INTO flat_maintenance_lookup values('308','Dhruthi', 4300);
INSERT INTO flat_maintenance_lookup values('309','Tarun Bhandary', 4300);
INSERT INTO flat_maintenance_lookup values('310','Jyotideep', 4800);
INSERT INTO flat_maintenance_lookup values('311','Prabhat', 4400);
INSERT INTO flat_maintenance_lookup values('312','Vicknesh Ranganath', 4800);
INSERT INTO flat_maintenance_lookup values('313','PR Gopal', 4300);
INSERT INTO flat_maintenance_lookup values('314','Sitesh', 4300);
INSERT INTO flat_maintenance_lookup values('315','Krishan Bhat', 4300);
INSERT INTO flat_maintenance_lookup values('316','Dipayan Choudhury', 4300);*/

-- 4th Floor residents
/*INSERT INTO flat_maintenance_lookup values('401','Rajkumar', 4300);
INSERT INTO flat_maintenance_lookup values('402','Ramesh', 4800);
INSERT INTO flat_maintenance_lookup values('403','Shambu Suman', 4400);
INSERT INTO flat_maintenance_lookup values('404','Satish Golukonda', 4800);
INSERT INTO flat_maintenance_lookup values('405','Abhishek Kumar', 4300);
INSERT INTO flat_maintenance_lookup values('406','Arindam Ghosh', 4300);
INSERT INTO flat_maintenance_lookup values('407','Yathartha Kulshreshta', 4300);
INSERT INTO flat_maintenance_lookup values('408','Sunil', 4300);
INSERT INTO flat_maintenance_lookup values('409','Praveen VV', 4300);
INSERT INTO flat_maintenance_lookup values('410','Sunil Ganatra', 4800);
INSERT INTO flat_maintenance_lookup values('411','Abilash EP', 4400);
INSERT INTO flat_maintenance_lookup values('412','Ajay Dev', 4800);
INSERT INTO flat_maintenance_lookup values('413','Mayank Gupta', 4300);
INSERT INTO flat_maintenance_lookup values('414','Vivek Chabbi', 4300);
INSERT INTO flat_maintenance_lookup values('415','Abhishek Srivastava', 4300);
INSERT INTO flat_maintenance_lookup values('416','Jatin Bajaj', 4300);*/

--commit;

/*CREATE TABLE IF NOT EXISTS maintenance_txn (
  txn_id BIGINT NOT NULL,
  flat_number VARCHAR(10) NOT NULL,
  txn_date DATE NOT NULL,
  month VARCHAR(20) NOT NULL,
  year VARCHAR(5) NOT NULL,
  actual_payment DOUBLE,
  payment_mode VARCHAR(20) NOT NULL,
  balance DOUBLE,
  PRIMARY KEY (txn_id)
);*/