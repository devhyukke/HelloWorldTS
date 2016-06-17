/* Sample Data */
drop table T_SAMPLE;
create table if not exists T_SAMPLE (
    SAMPLE_ID int primary key auto_increment,
    SAMPLE_NAME varchar(50),
    SAMPLE_TYPE varchar(10)
);

/* User Master */
drop table M_USER;
create table if not exists M_USER (
    USER_ID int primary key auto_increment,
    USERNAME varchar(32),
    PASSWORD varchar(60),
    DISPLAY_NAME varchar(64)
);
