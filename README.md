# LabClinica_BD
# Script Tablas Oracle

prompt PL/SQL Developer Export Tables for user JROJAS@XE
prompt Created by jitor on jueves, 13 de julio de 2023
set feedback off
set define off

prompt Dropping TBL_PACIENTES...
drop table TBL_PACIENTES cascade constraints;
prompt Dropping TBL_CITAS...
drop table TBL_CITAS cascade constraints;
prompt Creating TBL_PACIENTES...
create table TBL_PACIENTES
(
  pac_id            NUMBER(5) not null,
  pac_cedula        VARCHAR2(30),
  pac_nombre        VARCHAR2(15),
  pac_papellido     VARCHAR2(15),
  pac_sapellido     VARCHAR2(15),
  pac_direccion     VARCHAR2(100),
  pac_fecnacimiento DATE
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_PACIENTES
  add constraint PK_ID primary key (PAC_ID)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating TBL_CITAS...
create table TBL_CITAS
(
  cit_idpaciente NUMBER(5) not null,
  cit_fecha      DATE not null,
  cit_hora       NUMBER(2) not null
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_CITAS
  add constraint PK_IDPACIENTE primary key (CIT_IDPACIENTE, CIT_FECHA, CIT_HORA)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_CITAS
  add constraint FK_PACXCITA foreign key (CIT_IDPACIENTE)
  references TBL_PACIENTES (PAC_ID);

prompt Disabling triggers for TBL_PACIENTES...
alter table TBL_PACIENTES disable all triggers;
prompt Disabling triggers for TBL_CITAS...
alter table TBL_CITAS disable all triggers;
prompt Disabling foreign key constraints for TBL_CITAS...
alter table TBL_CITAS disable constraint FK_PACXCITA;
prompt Loading TBL_PACIENTES...
insert into TBL_PACIENTES (pac_id, pac_cedula, pac_nombre, pac_papellido, pac_sapellido, pac_direccion, pac_fecnacimiento)
values (1, '117990469', 'Jorge', 'Rojas', 'Mena', 'Perez Zeledon, Sinaí...', to_date('15-05-2010', 'dd-mm-yyyy'));
insert into TBL_PACIENTES (pac_id, pac_cedula, pac_nombre, pac_papellido, pac_sapellido, pac_direccion, pac_fecnacimiento)
values (2, '12345678', 'Hairol', 'Romero', 'Sandí', 'Perez Zeledon', to_date('07-05-1977', 'dd-mm-yyyy'));
insert into TBL_PACIENTES (pac_id, pac_cedula, pac_nombre, pac_papellido, pac_sapellido, pac_direccion, pac_fecnacimiento)
values (3, '12121212', 'Ignacio', 'Artaviva', 'Salado', 'Abrojo, el bajillo donde apuñalan', to_date('11-05-2007', 'dd-mm-yyyy'));
commit;
prompt 3 records loaded
prompt Loading TBL_CITAS...
insert into TBL_CITAS (cit_idpaciente, cit_fecha, cit_hora)
values (1, to_date('24-05-2024', 'dd-mm-yyyy'), 2);
insert into TBL_CITAS (cit_idpaciente, cit_fecha, cit_hora)
values (2, to_date('20-07-2023', 'dd-mm-yyyy'), 10);
insert into TBL_CITAS (cit_idpaciente, cit_fecha, cit_hora)
values (3, to_date('21-06-2023', 'dd-mm-yyyy'), 5);
commit;
prompt 3 records loaded
prompt Enabling foreign key constraints for TBL_CITAS...
alter table TBL_CITAS enable constraint FK_PACXCITA;
prompt Enabling triggers for TBL_PACIENTES...
alter table TBL_PACIENTES enable all triggers;
prompt Enabling triggers for TBL_CITAS...
alter table TBL_CITAS enable all triggers;

set feedback on
set define on
prompt Done
