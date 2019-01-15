CREATE TABLE account (
    account_ID INTEGER NOT NULL,
    username nvarchar2(100) NOT NULL UNIQUE,
    password nvarchar2(100) NOT NULL,
    email nvarchar2(100) NOT NULL UNIQUE,
    role integer NOT NULL,
    full_name nvarchar2(100) NOT NULL,
    status integer NOT NULL,
    date_created nvarchar2(50) NOT NULL,
    last_access nvarchar2(50),
    phone nvarchar2(11) NOT NULL UNIQUE,
    notes nvarchar2(1000),
    PRIMARY KEY (account_ID)
);
CREATE TABLE apply_profile (
    apply_profile_ID integer NOT NULL,
    account_ID integer NOT NULL,
    job_ID integer NOT NULL,
    cv nvarchar2(100) NOT NULL,
    notes NCLOB,
    status integer NOT NULL,
    PRIMARY KEY (apply_profile_ID)
);
CREATE TABLE category_job (
    category_job_ID integer NOT NULL,
    category_name nvarchar2(100) NOT NULL,
    status integer NOT NULL,
    PRIMARY KEY (category_job_ID)
);
CREATE TABLE category_news (
    category_news_ID integer NOT NULL,
    category_name nvarchar2(100) NOT NULL,
    status integer NOT NULL,
    PRIMARY KEY (category_news_ID)
);
CREATE TABLE city (
    city_ID integer NOT NULL,
    city_name nvarchar2(100) NOT NULL,
    PRIMARY KEY (city_ID)
);
CREATE TABLE job_detail (
    job_ID integer NOT NULL,
    category_job_ID integer NOT NULL,
    job_name nvarchar2(200) NOT NULL,
    description nvarchar2(1000) NOT NULL,
    salary number(20) NOT NULL,
    thumbnail nvarchar2(100) NOT NULL,
    content NCLOB NOT NULL,
    address nvarchar2(100) NOT NULL,
    interest NCLOB NOT NULL,
    job_request NCLOB NOT NULL,
    expiration_date nvarchar2(50) NOT NULL,
    date_submitted nvarchar2(50) NOT NULL,
    working_form integer NOT NULL,
    hot integer NOT NULL,
    status integer NOT NULL,
    city_ID integer NOT NULL,
    PRIMARY KEY (job_ID)
);
CREATE TABLE news_detail (
    news_ID integer NOT NULL,
    category_news_ID integer NOT NULL,
    title nvarchar2(200) NOT NULL,
    description nvarchar2(1000) NOT NULL,
    content NCLOB NOT NULL,
    date_submitted nvarchar2(50) NOT NULL,
    thumbnail nvarchar2(100) NOT NULL,
    status integer NOT NULL,
    hot integer NOT NULL,
    account_ID integer NOT NULL,
    PRIMARY KEY (news_ID)
);
CREATE TABLE setting (
    setting_ID integer NOT NULL,
    company_name nvarchar2(100) NOT NULL,
    logo nvarchar2(50) NOT NULL,
    address nvarchar2(50) NOT NULL,
    phone nvarchar2(50) NOT NULL,
    facebook nvarchar2(50),
    twitter nvarchar2(50),
    header nvarchar2(50) NOT NULL,
    footer nvarchar2(50) NOT NULL,
    video nvarchar2(50),
    PRIMARY KEY (setting_ID)
);
CREATE TABLE slider(
    slider_ID integer NOT NULL,
    image nvarchar2(50) NOT NULL,
    link nvarchar2(100) NOT NULL,
    rank integer NOT NULL,
    PRIMARY KEY (slider_ID)
);
CREATE TABLE user_info (
    user_ID integer NOT NULL,
    avatar nvarchar2(100),
    skype nvarchar2(50),
    facebook nvarchar2(50),
    native_land nvarchar2(50),
    education nvarchar2(100),
    specialize nvarchar2(50),
    school nvarchar2(100),
    graduation_year integer,
    skill nvarchar2(1000),
    work_experience nvarchar2(1000),
    account_ID integer NOT NULL UNIQUE,
    PRIMARY KEY (user_ID)
);
ALTER TABLE apply_profile ADD CONSTRAINT Apply_Profile_Admin
    FOREIGN KEY (account_ID)
    REFERENCES account (account_ID);
ALTER TABLE apply_profile ADD CONSTRAINT Apply_Profile_Job_Detail
    FOREIGN KEY (job_ID)
    REFERENCES job_detail (job_ID);
ALTER TABLE job_detail ADD CONSTRAINT Job_Detail_Category_Job
    FOREIGN KEY (category_job_ID)
    REFERENCES category_job (category_job_ID);
ALTER TABLE job_detail ADD CONSTRAINT Job_Detail_City
    FOREIGN KEY (city_ID)
    REFERENCES city (city_ID);
ALTER TABLE news_detail ADD CONSTRAINT New_Detail_Admin
    FOREIGN KEY (account_ID)
    REFERENCES account (account_ID);
ALTER TABLE news_detail ADD CONSTRAINT News_Detail_Category_News
    FOREIGN KEY (category_news_ID)
    REFERENCES category_news (category_news_ID);
ALTER TABLE user_info ADD CONSTRAINT User_Account
    FOREIGN KEY (account_ID)
    REFERENCES account (account_ID);
--account
CREATE SEQUENCE seq_account
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

--apply_profile
CREATE SEQUENCE seq_apply_profile
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

--category_job
CREATE SEQUENCE seq_category_job
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

--category_news
CREATE SEQUENCE seq_category_news
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

--job_detail
CREATE SEQUENCE seq_job_detail
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

--news_detail
CREATE SEQUENCE seq_news_detail
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

--user
CREATE SEQUENCE seq_user_info
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

insert into setting values(1,'name','LOGO','address','phone','facebook','twitter','header','footer','video');

insert into slider values(1,'image1','link1',1);
insert into slider values(2,'image2','link2',2);
insert into slider values(3,'image3','link3',3);

--insert into city values(1,'Hà Nội');
--insert into city values(2,'Đà Nẵng');
--insert into city values(3,'Hồ Chí Minh');
--insert into city values(4,'Hải Phòng');