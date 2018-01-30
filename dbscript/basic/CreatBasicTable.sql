drop table if exists T_USER;
drop table if exists T_USER_REG;
drop table if exists T_EMAIL;
drop table if exists T_EMAIL_ATTACH;


/*==============================================================*/
/* Table: T_USER                                                */
/*==============================================================*/
create table T_USER
(
   USER_ID              int unsigned not null auto_increment,
   USER_NAME            varchar(20) not null,
   REAL_NAME            varchar(400) not null,
   EMAIL                varchar(200) not null,
   MOBILE               varchar(50) not null,
   TEL_PHONE            varchar(50),
   PASSWORD             varchar(32) not null,

   ACCOUNT_NON_EXPIRED  tinyint(1) unsigned default 0 not null,
   ACCOUNT_NON_LOCKED   tinyint(1) unsigned default 0 not null,
   CREDENTIALS_NON_EXPIRED tinyint(1) unsigned default 0 not null,
   ENABLED              tinyint(1) unsigned default 0 not null,
   LOGIN_FAILED_TIMES   tinyint(4) unsigned  default 0 not null,
   CREDENTIALS_VALID_BY datetime not null,
   LOCKED_UNTIL_TO      datetime,

   AUTHORITIES          varchar(800),
   PROFILE              text,

   INSERT_TIME          datetime not null,
   INSERT_USER_ID       int unsigned not null,
   UPDATE_TIME          datetime not null,
   UPDATE_USER_ID       int unsigned not null,
   VER                  int unsigned not null,
   primary key (USER_ID)
);

/*==============================================================*/
/* Index: IDX_T_USER_USER_NAME                                  */
/*==============================================================*/
create unique index IDX_T_USER_USER_NAME on T_USER
(
   USER_NAME
);

/*==============================================================*/
/* Index: IDX_T_USER_EMAIL                                      */
/*==============================================================*/
create unique index IDX_T_USER_EMAIL on T_USER
(
   EMAIL
);

/*==============================================================*/
/* Index: IDX_T_USER_MOBILE                                     */
/*==============================================================*/
create unique index IDX_T_USER_MOBILE on T_USER
(
   MOBILE
);



/*==============================================================*/
/* Table: T_USER_REG                                            */
/*==============================================================*/
create table T_USER_REG
(
   USER_REG_ID          int unsigned not null auto_increment,
   USER_NAME            varchar(20) not null,
   REAL_NAME            varchar(400) not null,
   EMAIL                varchar(200) not null,
   MOBILE               varchar(50) not null,
   TEL_PHONE            varchar(50),
   PASSWORD             varchar(32) not null,
   VERIFY_EMAIL_SEND    tinyint(1) unsigned not null,
   VERIFY_EMAIL_RES     tinyint(1) unsigned comment '1 success 2 failed',

   PROFILE              text,

   INSERT_TIME          datetime not null,
   INSERT_USER_ID       int unsigned not null,
   UPDATE_TIME          datetime not null,
   UPDATE_USER_ID       int unsigned not null,
   primary key (USER_REG_ID)
);
/*==============================================================*/
/* Index: IDX_T_USER_REG_EMAIL                                  */
/*==============================================================*/
create unique index IDX_T_USER_REG_EMAIL on T_USER_REG
(
   EMAIL
);


/*==============================================================*/
/* Table: T_EMAIL                                               */
/*==============================================================*/
create table T_EMAIL
(
   EMAIL_ID             bigint unsigned not null auto_increment,
   SUBJECT              varchar(300) not null,
   TO_LIST              varchar(500) not null,
   CC_LIST              varchar(500),
   BCC_LIST             varchar(500),
   BODY_TLP_NAME        varchar(100) not null comment 'email body build by template engine.',
   BODY_TLP_PARAMS      varchar(2000),
   STATUS               tinyint(1) unsigned not null default 4 comment '1 success, 2 fail, 3 processing, 4 pending ',
   RETRY_TIMES          tinyint(3) unsigned,
   LAST_MSG             varchar(2000),
   PRIORITY             tinyint(1) unsigned not null default 2 comment '1: high priority 2: normal',
   SEND_BY              tinyint(1) unsigned not null comment 'which mail server send the email',

   INSERT_TIME          datetime not null,
   INSERT_USER_ID       int unsigned not null,
   UPDATE_TIME          datetime not null,
   UPDATE_USER_ID       int unsigned not null,
   VER                  int unsigned not null,
   primary key (EMAIL_ID)
);
/*==============================================================*/
/* Table: T_EMAIL_ATTACH                                        */
/*==============================================================*/
create table T_EMAIL_ATTACH
(
   ATTACH_ID            bigint unsigned not null auto_increment,
   EMAIL_ID             bigint unsigned not null,
   FILE_PATH            varchar(200),
   FILE_TYPE            tinyint(1) unsigned not null   comment '1 attachFile,2 inlineFile',
   STATUS               tinyint(1) unsigned not null default 4 comment '1 success, 2 fail, 3 processing, 4 pending ',
   primary key (ATTACH_ID)
);
/*==============================================================*/
/* Index: IDX_T_EMA_ATTA_EMA_ID                                 */
/*==============================================================*/
create unique index IDX_T_EMA_ATTA_EMA_ID on T_EMAIL_ATTACH
(
   EMAIL_ID
);
