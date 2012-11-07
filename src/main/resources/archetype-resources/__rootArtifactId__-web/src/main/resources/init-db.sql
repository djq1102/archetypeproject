-- This script initializes the database by creating tables and inserting data
-- Use only for a proof of concept
drop TABLE DEMO_BIZ;


-- Create table
create table DEMO_BIZ
(
  ID          NUMBER(19) not null,
  NAME        VARCHAR2(32) not null,
  BIZ_TYPE    NUMBER(3),
  MOBILE      VARCHAR2(20) not null,
  ADDRESS     VARCHAR2(128),
  TEL         VARCHAR2(40),
  EMAIL       VARCHAR2(32),
  PARENT      NUMBER(19),
  COMMENTS    VARCHAR2(64),
  CREATE_TIME VARCHAR2(20),
  UPDATE_TIME VARCHAR2(20),
  STATUS      NUMBER(3),
  AREA        VARCHAR2(10) default '3301'
)
-- Add comments to the columns 
comment on column DEMO_BIZ.ID
  is 'ID for biz,
internal use only.';
comment on column DEMO_BIZ.BIZ_TYPE
  is 'refer to role in credential.
2 for general merchants.
3 for agents.
4 for 3rd party cooperators.
5 for internal business.
6 for others.
0 for unknown.';
comment on column DEMO_BIZ.MOBILE
  is 'cell phone number for Biz.
can be used for login ID.';
comment on column DEMO_BIZ.TEL
  is 'telephone of merchant';
comment on column DEMO_BIZ.EMAIL
  is 'email';
comment on column DEMO_BIZ.PARENT
  is 'parent merchant';
comment on column DEMO_BIZ.CREATE_TIME
  is 'create time for current record';
-- Create/Recreate primary, unique and foreign key constraints 
alter table DEMO_BIZ
  add primary key (ID);


