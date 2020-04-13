drop database if exists Working;
create database Working;
use Working;
drop table if exists Document;

drop table if exists Job;

drop table if exists User;

drop table if exists department;

drop table if exists employee;

drop table if exists facial_register;

drop table if exists notice;

/*==============================================================*/
/* Table: Document                                              */
/*==============================================================*/
create table Document
(
   document_id          int not null,
   document_title       varchar(50) not null,
   document_filename    varchar(300) not null,
   document_remark      varchar(300),
   document_create_time timestamp not null,
   document_user_id     int not null,
   primary key (document_id)
);

/*==============================================================*/
/* Table: Job                                                   */
/*==============================================================*/
create table Job
(
   Job_id               int not null,
   Job_name             varchar(50) not null,
   Job_remark           varchar(300) not null,
   primary key (Job_id)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   user_id              int not null auto_increment,
   Job_id               int,
   Loginname            varchar(20) not null,
   password             varchar(32) not null,
   Status               int not null default 1,
   Createdate           datetime not null default CURRENT_TIMESTAMP,
   UserName             varchar(20) not null,
   Faceurl              varchar(255) not null,
   Facepath             varchar(255) not null,
   primary key (user_id)
);

/*==============================================================*/
/* Table: department                                            */
/*==============================================================*/
create table department
(
   depart_id            int not null,
   depart_name          varchar(50) not null,
   depart_remark        varchar(300) not null,
   primary key (depart_id)
);

/*==============================================================*/
/* Table: employee                                              */
/*==============================================================*/
create table employee
(
   user_id              int not null,
   employee_depart_id   int,
   employee_job_id      int,
   employee_name        varchar(20),
   employee_crad_id     varchar(18),
   employee_adddress    varchar(50),
   employee_post_code   varchar(50),
   employee_tel         varchar(16),
   employee_phone       varchar(11),
   employee_qq          varchar(10),
   employee_email       varchar(50),
   employee_sex         int,
   employee_party       varchar(10),
   employee_bir         datetime,
   employee_race        varchar(100),
   employee_edu         varchar(10),
   employee_spe         varchar(20),
   employee_hobby       varchar(100),
   employee_remark      varchar(500),
   create_time          datetime,
   primary key (user_id)
);

/*==============================================================*/
/* Table: facial_register                                       */
/*==============================================================*/
create table facial_register
(
   ID                   int not null,
   user_id              int,
   AppID                varchar(32) not null,
   apikey               varchar(32) not null,
   secretKey            varchar(32) not null,
   threshold            int not null,
   primary key (ID)
);

/*==============================================================*/
/* Table: notice                                                */
/*==============================================================*/
create table notice
(
   notice_id            int not null,
   notice_title         varchar(50) not null,
   notice_content       varchar(50) not null,
   notice_create_date   timestamp not null,
   notice_user_id       int not null,
   primary key (notice_id)
);

alter table Document add constraint FK_Relationship_8 foreign key (document_user_id)
      references User (user_id) on delete restrict on update cascade;
alter table User add constraint FK_Relationship_5 foreign key (Job_id)
      references Job (Job_id) on delete set null on update cascade;

alter table employee add constraint FK_Relationship_4 foreign key (user_id)
      references User (user_id) on delete restrict on update restrict;

alter table employee add constraint FK_Relationship_6 foreign key (employee_depart_id)
      references department (depart_id) on delete restrict on update restrict;

alter table facial_register add constraint FK_Relationship_1 foreign key (user_id)
      references User (user_id) on delete cascade on update cascade;

alter table notice add constraint FK_Relationship_7 foreign key (notice_user_id)
      references User (user_id) on delete restrict on update cascade;
