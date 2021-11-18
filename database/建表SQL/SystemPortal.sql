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
'��Ծ�û���';

comment on column ACTIVE_USER.ID is
'id';

comment on column ACTIVE_USER.SYSTEM_INFO_ID is
'ϵͳ��Ϣ��id';

comment on column ACTIVE_USER.EMP_ID is
'����';

comment on column ACTIVE_USER.EMP_NAME is
'����';

comment on column ACTIVE_USER.ACTIVE_DATE is
'��Ծ����';

comment on column ACTIVE_USER.CREATE_DATE is
'����ʱ��';

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
'ע���û���';

comment on column REGISTER_USER.ID is
'id';

comment on column REGISTER_USER.SYSTEM_INFO_ID is
'ϵͳ��Ϣ��id';

comment on column REGISTER_USER.EMP_ID is
'����';

comment on column REGISTER_USER.EMP_NAME is
'����';

comment on column REGISTER_USER.REGISTER_DATE is
'ע������';

comment on column REGISTER_USER.CREATE_TIME is
'����ʱ��';

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
'ϵͳ��Ϣ��';

comment on column SYSTEM_INFO.ID is
'id';

comment on column SYSTEM_INFO.NAME is
'����';

comment on column SYSTEM_INFO.URL is
'ϵͳURL';

comment on column SYSTEM_INFO.PIC_SRC is
'ͼ���ַ';

comment on column SYSTEM_INFO.SUMMARY is
'ϵͳ���';

comment on column SYSTEM_INFO.LIST_ORDER is
'�б����';

comment on column SYSTEM_INFO.CREATE_TIME is
'����ʱ��';

comment on column SYSTEM_INFO.UPDATE_TIME is
'�޸�ʱ��';

comment on column SYSTEM_INFO.VALID_FLAG is
'��Ч��־(0:��Ч��1����Ч)';

