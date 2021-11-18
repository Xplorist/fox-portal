/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2020/5/21 13:46:55                           */
/*==============================================================*/


drop table ACTIVE_USER cascade constraints;

drop table REGISTER_USER cascade constraints;

drop table SYSTEM_INFO cascade constraints;

/*==============================================================*/
/* Table: ACTIVE_USER                                           */
/*==============================================================*/
create table ACTIVE_USER 
(
   ID                   varchar2(50)         not null,
   SYSTEM_INFO_ID       varchar2(50)         not null,
   EMP_ID               varchar2(50)         not null,
   EMP_NAME             varchar2(50),
   ACTIVE_DATE          date                 not null,
   CREATE_DATE          date                 default SYSDATE not null,
   constraint PK_ACTIVE_USER primary key (ID)
);

comment on table ACTIVE_USER is
'活跃用户表';

comment on column ACTIVE_USER.ID is
'id';

comment on column ACTIVE_USER.SYSTEM_INFO_ID is
'系统信息表id';

comment on column ACTIVE_USER.EMP_ID is
'工号';

comment on column ACTIVE_USER.EMP_NAME is
'姓名';

comment on column ACTIVE_USER.ACTIVE_DATE is
'活跃日期';

comment on column ACTIVE_USER.CREATE_DATE is
'创建时间';

/*==============================================================*/
/* Table: REGISTER_USER                                         */
/*==============================================================*/
create table REGISTER_USER 
(
   ID                   varchar2(50)         not null,
   SYSTEM_INFO_ID       varchar2(50)         not null,
   EMP_ID               varchar2(50)         not null,
   EMP_NAME             varchar2(50),
   REGISTER_DATE        date                 not null,
   CREATE_TIME          date                 default SYSDATE not null,
   constraint PK_REGISTER_USER primary key (ID)
);

comment on table REGISTER_USER is
'注册用户表';

comment on column REGISTER_USER.ID is
'id';

comment on column REGISTER_USER.SYSTEM_INFO_ID is
'系统信息表id';

comment on column REGISTER_USER.EMP_ID is
'工号';

comment on column REGISTER_USER.EMP_NAME is
'姓名';

comment on column REGISTER_USER.REGISTER_DATE is
'注册日期';

comment on column REGISTER_USER.CREATE_TIME is
'创建时间';

/*==============================================================*/
/* Table: SYSTEM_INFO                                           */
/*==============================================================*/
create table SYSTEM_INFO 
(
   ID                   VARCHAR2(50)         not null,
   NAME                 VARCHAR2(50)         not null,
   URL                  VARCHAR2(250),
   PIC_SRC              VARCHAR2(250),
   SUMMARY              VARCHAR2(500),
   LIST_ORDER           NUMBER               not null,
   CREATE_TIME          DATE                 default SYSDATE not null,
   UPDATE_TIME          DATE,
   VALID_FLAG           NUMBER               default 1 not null,
   constraint PK_SYSTEM_INFO primary key (ID)
);

comment on table SYSTEM_INFO is
'系统信息表';

comment on column SYSTEM_INFO.ID is
'id';

comment on column SYSTEM_INFO.NAME is
'名称';

comment on column SYSTEM_INFO.URL is
'系统URL';

comment on column SYSTEM_INFO.PIC_SRC is
'图标地址';

comment on column SYSTEM_INFO.SUMMARY is
'系统简介';

comment on column SYSTEM_INFO.LIST_ORDER is
'列表序号';

comment on column SYSTEM_INFO.CREATE_TIME is
'创建时间';

comment on column SYSTEM_INFO.UPDATE_TIME is
'修改时间';

comment on column SYSTEM_INFO.VALID_FLAG is
'有效标志(0:无效，1：有效)';

