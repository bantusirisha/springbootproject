--create company databse
create database company

-----------------------CREATION of tables-----------------------
--create categories table
create table categories(
    CatCode VARCHAR(10) constraint categories_pk primary key,
    CatName varchar(50) constraint categories_name_nn not null
)

--create departments table
create table departments(
    DeptCode varchar(10) constraint department_pk primary key,
    DeptName varchar(50) constraint department_name_nn not null,
    Hod varchar(50) constraint department_hod_uni unique 
)

--create paymentModes table
create table paymentModes(
    PaymentModeCode varchar(10) constraint payment_pk primary key,
    PaymentName varchar(50) constraint payment_name_nn not null ,
    PaymentRemarks varchar(50) constraint payment_remarks_n null,
)

--create expenditures table
create table expenditures(
    id int identity(1,1) constraint expenditure_pk primary key,
    CatCode varchar(10) constraint expenditure_cc_fk references categories(CatCode) on delete cascade,
    DeptCode varchar(10) constraint expenditure_dc_fk REFERENCES departments(DeptCode) on delete cascade,
    Amount money CONSTRAINT expenditure_am_ch check(Amount>0),
    ExpDate DATE CONSTRAINT expenditure_date_de DEFAULT CONVERT(date,getdate()),
    AuthorizedBy VARCHAR(50) CONSTRAINT expenditure_au_nn not null,
    description VARCHAR(50) CONSTRAINT expenditue_des_n null ,
    PaymentModeCode varchar(10) constraint expenditure_c_fk REFERENCES paymentModes(PaymentModeCode) on delete cascade,
    Remarks VARCHAR(50) CONSTRAINT expenditure_def_remarks DEFAULT 'no remarks',
)


----------------------------INSERTION of data---------------------

INSERT INTO Categories (CatCode, CatName) VALUES
    ('os', 'Office Supplies'),
    ('te', 'Travel Expenses'),
    ('td', 'Training & Development'),
    ('es', 'Employee Salaries'),
    ('ma', 'Marketing & Advertising'),
    ('swl' ,'Software Licenses'),
    ('hwp', 'Hardware Purchases');

INSERT INTO Departments (DeptCode, DeptName, HOD) VALUES
    ('FI101', 'Finance', 'chris'),
    ('RD102', 'Research & Development', 'Sara'),
    ('SM103', 'Sales & Marketing', 'Jennifer'),
    ('FE104', 'Front end Development', 'Smith'),
    ('BE105', 'Back end Development', 'Harry'),
    ('TE106', 'Testing', 'Lisa '),
    ('CS107', 'Cybersecurity', 'Michael'),
    ('DS108', 'Data Science and Analytics', 'larry');

INSERT INTO PaymentModes (PaymentModeCode, PaymentName, PaymentRemarks) VALUES
    ('CC1', 'Credit Card', 'card type: Visa'),
    ('DC2', 'Debit Card', 'card type: MasterCard'),
    ('NB3', 'Net banking', 'bank: SBI'),
    ('UPI4','UPI','UpiId: 1234567@ybl'),
    ('CSH5', 'Cash', 'In-person cash payment'),
    ('CQ6', 'Cheque', 'Payable to: Your Company, Account-12345');

insert into expenditures
       (CatCode,DeptCode,Amount,ExpDate,AuthorizedBy,description,PaymentModeCode,Remarks) VALUES
       ('ma','SM103',200000,'2015-7-27','Riaz','Adevertising for sales and marketing','CC1',NULL),
       ('os','RD102',10000, '2019-10-01', 'David wilson', 'Office supplies for R&D', 'DC2', 'Receipt #12345'),
       ('os','DS108',5000, '2021-10-01', 'David wilson', 'Office supplies for R&D', 'DC2', 'Receipt #12345'),
       ('te','SM103',90000,'2010-1-1','Larry Jones','transport expenses for S&M','CQ6','travelling Expenses'),
       ('td','FE104',80000,'2022-10-25','Mary thomas','training and development for front end development','CSH5',NULL),
       ('td','BE105',100000,'2022-9-18','Srikanth','training and development for back end development','CSH5',NULL),
       ('es','BE105',500000,'2023-5-18','Caroline savlatore','employee salary for back end developers','CC1','salary payment'),
       ('swl','CS107',70000,'2019-9-15','Stacy Milli','Software license for Cyber security','NB3','Union bank of India'),
       ('swl','TE106',70000,'2017-8-29','James roman','Software license for Testing','UPI4',default),
       ('hwp','RD102',90000,'2020-12-7','Elena roman','Hardware purchases for R&D','UPI4',default)



------------------------SELECT statements--------------------

select *from categories
select *from departments
select *from PaymentModes
select *from expenditures


-------------------------TRUNCATE-----------------------

TRUNCATE table categories
TRUNCATE table departments
TRUNCATE table payments
TRUNCATE table expenditures
