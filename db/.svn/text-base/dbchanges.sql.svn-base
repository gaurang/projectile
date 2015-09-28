

--2 may 2011
insert into tb_module_prp (SELECT prpId,'DEMAND',flag,fieldDisplayType,sortId,dispDesc FROM tb_module_prp where module = 'MEMO')


CREATE TABLE `tb_demand` (

  `id` bigint(20) NOT NULL,
  `partyAccId` int(11) NOT NULL,
  `dmdType` varchar(5) NOT NULL,
  `fromDate` date NOT NULL,
  `toDate` date NOT NULL,
  `sendMail` tinyint(4) DEFAULT NULL,
  `autoMemo` tinyint(4) DEFAULT NULL,
  `priceFrom` decimal(12,2) DEFAULT NULL,
  `priceTo` decimal(12,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1

CREATE TABLE  `tb_demandDetails` (
  `demandId` bigint(20) NOT NULL,
  `prpId` int(11) NOT NULL,
  `numFr` decimal(12,2) DEFAULT NULL,
  `numTo` decimal(12,2) DEFAULT NULL,
  `valueFr` varchar(30) DEFAULT NULL,
  `valueTo` varchar(30) DEFAULT NULL,
  `prpIn` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`demandId`,`prpId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1

--14-1-2011

ALTER TABLE `tb_module_prp` ADD COLUMN `sortId` DECIMAL  AFTER `FieldDisplayType`;
update tb_module_prp m,tb_prpmaster p set m.sortId = p.prpSortId  where module ='MEMO' and p.id=m.prpId


--16-2-2011

ALTER TABLE `tb_prpsectionlist` ADD COLUMN `prp` VARCHAR(50)  NULL AFTER `sectionId`,
 DROP PRIMARY KEY,
 ADD PRIMARY KEY  USING BTREE(`PrpId`, `SectionId`);
 

  ALTER TABLE `tb_termmaster` ADD COLUMN `interest` DECIMAL(12,3)  AFTER `termDesc`;
 ALTER TABLE `tb_termmaster` ADD COLUMN `rap` DECIMAL(12,3)  AFTER `interest`;
 
 
 
 
 
 
 
CREATE TABLE `tb_currencies` (
  `currAbrev` char(3) NOT NULL DEFAULT '',
  `currency` varchar(60) NOT NULL DEFAULT '',
  `currSymbol` varchar(10) NOT NULL DEFAULT '',
  `country` varchar(100) NOT NULL DEFAULT '',
  `hundredsName` varchar(15) NOT NULL DEFAULT '',
  `autoUpdate` tinyint(1) NOT NULL DEFAULT '1',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`currAbrev`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1


CREATE TABLE  `tb_bank_trans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` smallint(6) DEFAULT NULL,
  `transNo` int(11) DEFAULT NULL,
  `bankAccId` int(11) NOT NULL DEFAULT 0,
  `ref` varchar(40) DEFAULT NULL,
  `transDate` date NOT NULL DEFAULT '0000-00-00',
  `amount` double DEFAULT NULL,
  `userId` int(11) NOT NULL DEFAULT '0',
  `reconciled` date DEFAULT NULL,
  `description` tinytext NOT NULL,	
  PRIMARY KEY (`id`)
  
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1


CREATE TABLE  `tb_gl_trans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` smallint(6) NOT NULL DEFAULT '0',
  `transNo` bigint(16) NOT NULL DEFAULT '1',
  `transDate` date NOT NULL DEFAULT '0000-00-00',
  `accountCode` varchar(15) NOT NULL DEFAULT '',
  `description` tinytext NOT NULL,
  `amount` double NOT NULL DEFAULT '0',
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Type_and_Number` (`type`,`transNo`),
  KEY `tran_date` (`transDate`),
  KEY `accountCode_and_tranDate` (`accountCode`,`transDate`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=latin1

CREATE TABLE  `tb_refs` (
  `id` int(11) NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL DEFAULT '0',
  `ref` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`,`type`),
  KEY `Type_and_Ref` (`type`,`ref`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

CREATE TABLE  `tb_angadia_trans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` smallint(6) NOT NULL DEFAULT '0',
  `transNo` bigint(16) NOT NULL DEFAULT '1',
  `transDate` date NOT NULL DEFAULT '0000-00-00',
  `angadiaId` varchar(15) NOT NULL DEFAULT '',
  `description` tinytext NOT NULL,
  `amount` double NOT NULL DEFAULT '0',
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Type_and_Number` (`type`,`transNo`),
  KEY `tranDate` (`transDate`),
  KEY `angadiaId_and_transDate` (`angadiaId`,`transDate`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=latin1


CREATE TABLE  `hridhesh`.`tb_vendor_trans` (
  `transNo` int(11) unsigned NOT NULL DEFAULT '0',
  `type` smallint(6) unsigned NOT NULL DEFAULT '0',
  `vendorId` int(11) unsigned DEFAULT NULL,
  `reference` tinytext NOT NULL,
  `vendorReference` varchar(60) NOT NULL DEFAULT '',
  `transDate` date NOT NULL DEFAULT '0000-00-00',
  `dueDate` date NOT NULL DEFAULT '0000-00-00',
  `ovAmount` double NOT NULL DEFAULT '0',
  `ovDiscount` double NOT NULL DEFAULT '0',
  `gst` double NOT NULL DEFAULT '0',
  `rate` double NOT NULL DEFAULT '1',
  `alloc` double NOT NULL DEFAULT '0',
  `taxIncluded` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`type`,`transNo`),
  KEY `vendorId` (`vendorId`),
  KEY `vendorId_2` (`vendorId`,`vendorReference`),
  KEY `type` (`type`),
  KEY `tranDate` (`tranDate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

ALTER TABLE `tb_bank_trans` ADD COLUMN `exRate` DOUBLE  AFTER `description`;


CREATE TABLE  `tb_sysPrefs` (
  `name` varchar(35) NOT NULL DEFAULT '',
  `category` varchar(30) DEFAULT NULL,
  `type` varchar(20) NOT NULL DEFAULT '',
  `length` smallint(6) DEFAULT NULL,
  `value` tinytext,
  `compaanyId` integer,	
  PRIMARY KEY (`name`),
  KEY `category` (`category`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1

CREATE TABLE  `tb_angadiaMaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `angadiaCoName` varchar(200) DEFAULT NULL,    
  `address` varchar(300) DEFAULT NULL,
  `phoneNo` varchar(100) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `dsc` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `opBalance` decimal(20,2) DEFAULT NULL,
  `currBalance` decimal(20,2) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `modifiedBy` int(11) DEFAULT NULL,
  `accCode` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1
----------DB v1.0
ALTER TABLE `tb_gl_trans` ADD COLUMN `personTypeId` TINYINT  DEFAULT NULL AFTER `userId`;
ALTER TABLE `tb_angadiaMaster` ADD COLUMN `accCode` VARCHAR(50)  NOT NULL AFTER `modifiedBy`;
ALTER TABLE `tb_payment` ADD COLUMN `transNo` INTEGER ;
ALTER TABLE `tb_payment` ADD COLUMN `type` INTEGER  AFTER `transNo`;
ALTER TABLE `tb_payment` ADD COLUMN `dsc` TINYTEXT  AFTER `type`;

ALTER TABLE `tb_purchasemaster` ADD COLUMN `amount` DOUBLE  AFTER `modifiedOn`,
 ADD COLUMN `discount` DOUBLE  AFTER `amount`,
 ADD COLUMN `tax` DOUBLE  AFTER `discount`;

 
 CREATE TABLE `tb_trans_type` (
  `id` INTEGER  NOT NULL,
  `name` VARCHAR(150)  NOT NULL,
  `code` VARCHAR(150)  NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = MyISAM;
--Default data for syspref
INSERT INTO `tb_sysPrefs` VALUES  ('coy_name','setup.company','varchar',60,'basync',-1),
 ('gst_no','setup.company','varchar',25,'9876543',-1),
 ('coy_no','setup.company','varchar',25,'123456789',-1),
 ('tax_prd','setup.company','int',11,'1',-1),
 ('tax_last','setup.company','int',11,'1',-1),
 ('postal_address','setup.company','tinytext',0,'Address 1\nAddress 2\nAddress 3',-1),
 ('phone','setup.company','varchar',30,'(222) 111.222.333',-1),
 ('fax','setup.company','varchar',30,'',-1),
 ('email','setup.company','varchar',100,'delta@delta.com',-1),
 ('coy_logo','setup.company','varchar',100,'logo_frontaccounting.jpg',-1),
 ('domicile','setup.company','varchar',55,'',-1),
 ('curr_default','setup.company','char',3,'USD',-1),
 ('use_dimension','setup.company','tinyint',1,'1',-1),
 ('f_year','setup.company','int',11,'4',-1),
 ('no_item_list','setup.company','tinyint',1,'0',-1),
 ('no_customer_list','setup.company','tinyint',1,'0',-1),
 ('no_supplier_list','setup.company','tinyint',1,'0',-1),
 ('base_sales','setup.company','int',11,'1',-1),
 ('time_zone','setup.company','tinyint',1,'0',-1),
 ('add_pct','setup.company','int',5,'-1',-1),
 ('round_to','setup.company','int',5,'1',-1),
 ('login_tout','setup.company','smallint',6,'600',-1),
 ('past_due_days','glsetup.general','int',11,'30',-1),
 ('profit_loss_year_act','glsetup.general','varchar',15,'9990',-1),
 ('retained_earnings_act','glsetup.general','varchar',15,'3590',-1),
 ('bank_charge_act','glsetup.general','varchar',15,'5690',-1),
 ('exchange_diff_act','glsetup.general','varchar',15,'4450',-1),
 ('default_credit_limit','glsetup.customer','int',11,'1000',-1),
 ('accumulate_shipping','glsetup.customer','tinyint',1,'0',-1),
 ('legal_text','glsetup.customer','tinytext',0,'',-1),
 ('freight_act','glsetup.customer','varchar',15,'4430',-1),
 ('debtors_act','glsetup.sales','varchar',15,'1200',-1),
 ('default_sales_act','glsetup.sales','varchar',15,'4010',-1),
 ('default_sales_discount_act','glsetup.sales','varchar',15,'4510',-1),
 ('default_prompt_payment_act','glsetup.sales','varchar',15,'4500',-1),
 ('default_delivery_required','glsetup.sales','smallint',6,'1',-1),
 ('default_dim_required','glsetup.dims','int',11,'20',-1),
 ('pyt_discount_act','glsetup.purchase','varchar',15,'5060',-1),
 ('creditors_act','glsetup.purchase','varchar',15,'2100',-1),
 ('po_over_receive','glsetup.purchase','int',11,'10',-1),
 ('po_over_charge','glsetup.purchase','int',11,'10',-1),
 ('allow_negative_stock','glsetup.inventory','tinyint',1,'0',-1),
 ('default_inventory_act','glsetup.items','varchar',15,'1510',-1),
 ('default_cogs_act','glsetup.items','varchar',15,'5010',-1),
 ('default_adj_act','glsetup.items','varchar',15,'5040',-1),
 ('default_inv_sales_act','glsetup.items','varchar',15,'4010',-1),
 ('default_assembly_act','glsetup.items','varchar',15,'1530',-1),
 ('default_workorder_required','glsetup.manuf','int',11,'20',-1),
 ('version_id','system','varchar',11,'2.3rc',-1),
 ('auto_curr_reval','setup.company','smallint',6,'1',-1),
 ('grn_clearing_act','glsetup.purchase','varchar',15,'1550',-1),
 ('copy.perpage','setup.company','int',3,'2',-1),
 ('pairs_xl_view','setup.company','int',3,'1',-1); --added later
  
UNLOCK TABLES;


CREATE TABLE  `tb_bankAccounts` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `accountCode` varchar(15) NOT NULL DEFAULT '',
  `accountType` smallint(6) NOT NULL DEFAULT '0' COMMENT '0-saving account, 1-chequing Account, 2-Credit Account, 3 -Cash Account',
  `bankAccountName` varchar(60) NOT NULL DEFAULT '',
  `bankAccountNumber` varchar(100) NOT NULL DEFAULT '',
  `bankName` varchar(60) NOT NULL DEFAULT '',
  `bankAddress` tinytext,
  `bankCurrCode` char(3) NOT NULL DEFAULT '',
  `dfltCurrAct` tinyint(1) NOT NULL DEFAULT '0',
  `lastReconciledDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endingReconcileBalance` double NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `partyAccId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bankAccountName` (`bankAccountName`),
  KEY `bankAccountNumber` (`bankAccountNumber`),
  KEY `accountCode` (`accountCode`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1



--insert into hridhesh.tb_sysPrefs  (select *, -1  from accounting.0_sys_prefs )//data
--insert into hridhesh.tb_acc_glacc (code,accName,accGroupId,status) select  account_code, account_name,account_type,1 from accounting.0_chart_master c  //data

---DB version 1.0.1
ALTER TABLE `tb_stockmaster` ADD COLUMN `pktCode2` VARCHAR(25)  AFTER `pairNo`;

ALTER TABLE `tb_stockmaster` ADD COLUMN `rapCode` VARCHAR(25)  AFTER `pktCode2`,
 ADD COLUMN `vendorStockCode` VARCHAR(25)  AFTER `rapCode`,
 ADD COLUMN `availabilty` VARCHAR(5)  AFTER `vendorStockCode`,
 ADD COLUMN `sarinFIle` VARCHAR(100)  AFTER `availabilty`,
 ADD COLUMN `gemFile` VARCHAR(100)  AFTER `sarinFIle`,
 ADD COLUMN `clientRow` VARCHAR(100)  AFTER `gemFile`;
 
 insert into tb_excelmap values (0, 'value',null,56),(0, 'pairNo',null,57),(0, 'pktCode2',null,58),(0, 'rapCode',null,59),(0, 'vendorStockCode',null,60),(0, 'availabilty',null,61),(0, 'sarinFile',null,62),(0, 'gemFile',null,63) ,(0,'clientRow',null,64);

 
 ALTER TABLE `tb_excelfile` ADD COLUMN `sort` INTEGER  AFTER `FileProcess`;
 update tb_excelfile set sort = id*10;
  update tb_excelfile set sort = 0 where fileName= 'MMK';
 
 
insert into tb_prpdetail values (1, 'B', 'Round', 10, 0, now()),(1, 'M', 'Marquise', 50, 0, now()),( 1, 'P', 'Pear', 30, 0, now()),(1, 'CMB', 'Cusion Modified', 60, 0, now()),(1, 'SQE', 'Square Em.', 130, 0, now()),(1, 'E', 'Emerald', 120, 0, now()),(1, 'H', 'Heart', 20, 0, now()),(1, 'C', 'Cushion', 60, 0, now()),(1, 'R', 'Radiant', 90, 0, now()),(1, 'SQR', 'Sq. Radiant', 100, 0, now()),(1, 'O', 'Oval', 80, 0, now()),(1, 'TRI', 'Triangle', 140, 0, now()),(1, 'T', 'Trilliant', 150, 0, now());

update `tb_prpmaster` set prpSortId = 440 where id = 42;
update `tb_prpmaster` set prpSortId = 420 where id = 43;
update `tb_prpmaster` set prpSortId = 430 where id = 44;

update `tb_module_prp` set sortId = 440 where prpid = 42 and sortId is not null;
update `tb_module_prp` set sortId = 420 where prpid = 43 and sortId is not null;
update `tb_module_prp` set sortId = 430 where prpid = 44 and sortId is not null;

update `tb_prpdetail` set prpValue = 'FANCY INTENSE' where prpid = 43 and sortId =560 and  prpValue = 'FANCY INTENSITY';
update `tb_prpdetail` set prpValue = 'VERY LIGHT' where prpid = 43 and sortId =510 and  prpValue = 'V.LIGHT';
ALTER TABLE `tb_excelfile` ADD COLUMN `sort` INTEGER  AFTER `FileProcess`;
ALTER TABLE `tb_excelfile` ADD COLUMN `rapFormat` INTEGER  AFTER `sort`;

#For Prp optional;

 delete from tb_prpdetail where prpid in (45,46);
 insert into tb_prpdetail values (45, 'Extr. thin', 'Extra Thin', 500, 1, now()),(45, 'Very Thin', 'Very Thin', 510, 1, now()) ,(45, 'Thin', 'Thin', 520, 1, now()),(45, 'Slightly Thin', 'Slightly Thin', 530, 1, now()),(45, 'Medium', 'Medium', 540, 1, now()) ,(45, 'Slightly Thick', 'Slightly Thick', 550, 1, now()) ,(45, 'Thick', 'Thick', 560, 1, now()) ,(45, 'Very Thick', 'Very Thick', 570, 1, now()) ,(45, 'Extr. Thick', 'Extr. Thick', 580, 1, now());

 insert into tb_prpdetail values (46, 'Extr. thin', 'Extra Thin', 500, 1, now()),(46, 'Very Thin', 'Very Thin', 510, 1, now()) ,(46, 'Thin', 'Thin', 520, 1, now()),(46, 'Slightly Thin', 'Slightly Thin', 530, 1, now()),(46, 'Medium', 'Medium', 540, 1, now()) ,(46, 'Slightly Thick', 'Slightly Thick', 550, 1, now()) ,(46, 'Thick', 'Thick', 560, 1, now()) ,(46, 'Very Thick', 'Very Thick', 570, 1, now()) ,(46, 'Extr. Thick', 'Extr. Thick', 580, 1, now());
 insert into tb_prpdetail values (45,'M','M',540,0,'2010-09-25 17:33:39'),(45,'MED','MED',540,0,'2010-09-25 17:35:19'),(45,'STK','STK',550,0,'2010-09-25 17:33:38'),(45,'STN','STN',530,0,'2010-09-25 17:33:44'),(45,'THK','THK',560,0,'2010-09-25 17:35:17'),(45,'THN','THN',520,0,'2010-09-25 17:35:21'),(45,'TK','TK',560,0,'2010-09-25 17:33:36'),(45,'TN','TN',520,0,'2010-09-25 17:33:42'),(45,'VTK','VTK',570,0,'2010-09-25 17:33:34'),(45,'VTN','VTN',510,0,'2010-09-25 17:33:45'),(45,'XTK','XTK',580,0,'2010-09-25 17:33:33'),(45,'XTN','XTK',500,0,'2010-09-25 17:33:48');

 insert into tb_prpdetail values (46,'M','M',540,0,'2010-09-25 17:33:39'),(46,'MED','MED',540,0,'2010-09-25 17:35:19'),(46,'STK','STK',550,0,'2010-09-25 17:33:38'),(46,'STN','STN',530,0,'2010-09-25 17:33:44'),(46,'THK','THK',560,0,'2010-09-25 17:35:17'),(46,'THN','THN',520,0,'2010-09-25 17:35:21'),(46,'TK','TK',560,0,'2010-09-25 17:33:36'),(46,'TN','TN',520,0,'2010-09-25 17:33:42'),(46,'VTK','VTK',570,0,'2010-09-25 17:33:34'),(46,'VTN','VTN',510,0,'2010-09-25 17:33:46'),(46,'XTK','XTK',580,0,'2010-09-25 17:33:33'),(46,'XTN','XTK',500,0,'2010-09-25 17:33:48');

 delete from tb_prpdetail where prpid in (47,31);

insert into tb_prpdetail values (47, 'Pointed', 'Pointed', 500, 1, now()),(47, 'Chipped', 'Chipped', 510, 1, now()) ,(47, 'Abraded', 'Abraded', 520, 1, now());
 insert into tb_prpdetail values (31,'L','L',540,0,'2010-09-25 17:42:38'),(31,'M','M',530,0,'2010-09-25 17:42:39'),(31,'N','N',500,0,'2010-09-25 17:42:45'),(31,'S','S',520,0,'2010-09-25 17:42:41'),(31,'VL','VL',550,0,'2010-09-25 17:42:36'),(31,'VS','VS',510,0,'2010-09-25 17:42:42');

 insert into tb_prpdetail values (31,'None','None',500,1,'2010-09-25 17:42:38'),(31,'Very Small','Very Small',510,1,'2010-09-25 17:42:39'),(31,'Small','Small',520,1,'2010-09-25 17:42:41'),(31,'Medium','Medium',530,1,'2010-09-25 17:42:45'),(31,'Large','Large',540,1,'2010-09-25 17:42:36'),
(31,'Very Large','Very Large',550,1,'2010-09-25 17:42:36');


ALTER TABLE `tb_usertable` ADD COLUMN `partyAccId` INTEGER  AFTER `registrationId`;
ALTER TABLE `tb_partyMaster` MODIFY COLUMN `phoneNo1` BIGINT  DEFAULT NULL,
 MODIFY COLUMN `phoneNo2` BIGINT  DEFAULT NULL,
 MODIFY COLUMN `fax` BIGINT  DEFAULT NULL,
 MODIFY COLUMN `tradeRefMobile1` BIGINT  DEFAULT NULL,
 MODIFY COLUMN `tradeRefPhone1` BIGINT  DEFAULT NULL,
 MODIFY COLUMN `tradeRefMobile2` BIGINT  DEFAULT NULL,
 MODIFY COLUMN `tradeRefPhone2` BIGINT  DEFAULT NULL;
 
ALTER TABLE `tb_partyAddMaster` MODIFY COLUMN `cellNo` BIGINT  DEFAULT NULL,
 MODIFY COLUMN `phone` BIGINT  DEFAULT NULL,
 MODIFY COLUMN `fax` BIGINT  DEFAULT NULL;
 
ALTER TABLE `tb_partyAddMaster` MODIFY COLUMN `designation` VARCHAR(50)  CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL;
ALTER TABLE `tb_partyMaster` ADD COLUMN `registrationId` INTEGER  AFTER `designation`;

#v 1.1
ALTER TABLE `tb_stockprp` ADD COLUMN `lastUpdateDate` DATETIME  AFTER `NT_so`;
ALTER TABLE `tb_stockprp` ADD COLUMN `updateBy` int  AFTER `lastUpdateDate`;

ALTER TABLE `tb_stockprp` ADD COLUMN `certLabId` varchar(512)  AFTER `GRPID`;
ALTER TABLE `tb_stockprp` ADD COLUMN `certLabUrl` varchar(512)  AFTER `certLabId`;

ALTER TABLE `tb_stockmaster` ADD COLUMN `lastUpdateDate` DATETIME  AFTER `clientRow`;
ALTER TABLE `tb_stockmaster` ADD COLUMN `updateBy` int  AFTER `lastUpdateDate`;

ALTER TABLE `tb_stockmaster` ADD COLUMN `rapnetFlag` TINYINT  AFTER `updateBy`,
 ADD COLUMN `webSiteFlag` TINYINT  AFTER `rapnetFlag`;

 

 
 ALTER TABLE `tb_ordermaster` ADD COLUMN `accType` VARCHAR(5)  AFTER `shipCharges`;
ALTER TABLE `k` MODIFY COLUMN `C` VARCHAR(25)  CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL;


#v 1.1.2 krishna
ALTER TABLE `tb_orderdetail` ADD COLUMN `addDisc` DECIMAL(10,2)  AFTER `cts`;
create table tb_orderdetailHistory as select * from tb_orderdetail;
truncate table tb_orderdetailHistory;
ALTER TABLE `tb_invoiceDetail` ADD COLUMN `addDisc` DECIMAL(6,2)  AFTER `rap`;
ALTER TABLE `tb_ordermaster` ADD COLUMN `modifiedBy` INTEGER  AFTER `accType`;
ALTER TABLE `tb_orderdetailHistory` ADD COLUMN `updatedate` DATETIME  AFTER `addDisc`;


ALTER TABLE `tb_invoiceDetail` MODIFY COLUMN `rate` DECIMAL(20,2)  DEFAULT NULL,
 MODIFY COLUMN `totalRate` DECIMAL(20,2)  DEFAULT NULL,
 MODIFY COLUMN `tax` DECIMAL(10,2)  DEFAULT NULL,
 MODIFY COLUMN `discount` DECIMAL(10,2)  DEFAULT NULL,
 MODIFY COLUMN `finalRate` DECIMAL(20,2)  DEFAULT NULL;

ALTER TABLE `tb_invoiceMaster` ADD COLUMN `createDate` DATETIME  AFTER `status`,
 ADD COLUMN `updateDate` DATETIME  AFTER `createDate`;

insert into tb_invoiceMaster (partyAccId, invoiceDate, dueDate, invType,  totalAmount, tax, discount, shipCharges, expences, invStatus, memoOrderId, brokerId,  brokrage, exportStatus,finalAmount, userId, consigneePartyId,consignmentCode, expRefNo,othRefNo,destination,preCarrierPartyId,placeOfPreCarrier,vesselFlight,portOfLoad,portOfDischarge,consigneeName, status, createDate )  SELECT om.partyAccId, om.orderDate, om.dueDate, om.accType , ROUND(sum(od.cts*od.rate),2),
				om.tax, om.discount, om.expences, om.shipCharges, 'UNPAID', om.id, om.brokerId, om.brokrage, 'TBE' , ROUND(sum(od.cts*od.rate) + (sum(od.cts*od.rate)*IFNULL(om.tax,0)/100) - (sum(od.cts*od.rate)* IFNULL(om.discount,0)/100) +  IFNULL(om.expences,0),2), om.userId,  -1, null, "", "", "", -1, "N.A.", "By Air", "Mumbai", "", "", 1, now()  FROM tb_ordermaster om inner join  tb_orderdetail od on od.orderid = om.id and od.status =2 inner join tb_stockprp s on s.pktId =od.pktId and s.grpid =1 inner join tb_stockmaster sm on s.pktId = sm.id inner join  tb_termmaster tm on tm.id= om.termId  where om.id  in ((select id from tb_ordermaster where orderType ='Confirm' and status =2 and id not in (select distinct memoOrderId from tb_invoiceMaster where memoOrderId is not null))) and om.status = 2 and sm.status = 4 group by om.id;

ALTER TABLE `tb_stockmaster` ADD COLUMN `askingPriceDisc` DECIMAL(10,2)  AFTER `webSiteFlag`;

insert into tb_invoiceDetail (invId, pktId, rate, totalRate , tax, discount, finalRate, status, pcs,cts, rap ) SELECT i.id, od.pktId, od.rate, ROUND(od.cts*od.rate,2), null, null, ROUND(od.cts*od.rate,2), 2 ,od.pcs, od.cts, od.rap  from tb_ordermaster om inner join  tb_orderdetail od on od.orderid = om.id and od.status =2 inner join tb_stockprp s on s.pktId =od.pktId and s.grpid =1 inner join tb_stockmaster sm on sm.id = s.pktid inner join tb_invoiceMaster i on i.memoOrderId = om.id where om.id  in (select id from tb_ordermaster where orderType ='Confirm' and status =2 and id not in (select distinct memoOrderId from tb_invoiceMaster where memoOrderId is not null and createDate is null ))  and od.status =2 ;

ALTER TABLE `tb_stockmaster` ADD COLUMN `askingPriceDisc` DECIMAL(10,2)  AFTER `webSiteFlag`;
	insert into tb_sysPrefs values ('askingPrice','setup.company','double',10,'5',-1); 
	insert into tb_excelmap values (0,'askingPriceDisc','Asking Price',65); 
	
CREATE TABLE  `tb_orderdetailHistory` (
  `orderId` int(10) unsigned NOT NULL,
  `PktId` int(10) unsigned NOT NULL,
  `BaseRate` decimal(12,2) DEFAULT NULL,
  `Rate` decimal(12,2) NOT NULL,
  `IssueDateTime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ReturnDateTime` datetime DEFAULT NULL,
  `status` int(10) unsigned NOT NULL DEFAULT '1',
  `rap` decimal(6,2) DEFAULT NULL,
  `pcs` int(11) DEFAULT NULL,
  `cts` decimal(10,3) DEFAULT NULL,
  `addDisc` decimal(10,2) DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
			 

alter table tb_termmaster add column sort decimal(8,2);
			 
#v 1.1.3 gaurang
 insert into tb_prpdetail values (23,'NBNG','no brown no green',500,1,now()),(23,'VLGY','very light greenish yellow',510,1,now()), (23,'VLG','very light green',520,1,now()),(23,'LG','light green',530,1,now()),
(23,'GG','grayish green',540,1,now()),(23,'FBR','faint brown',550,1,now()),
(23,'BR','brown',560,1,now()),(23,'FB','faint blue',570,1,now()),
(23,'FP','faint pink',580,1,now()); 

CREATE TABLE `tb_parcelMaster` (
  `id` INTEGER  NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(50)  NOT NULL,
  `baseRate` DOUBLE(20,5) ,
  `rate` DOUBLE(20,5) ,
  `colFr` VARCHAR(20) ,
  `colFr_so` DOUBLE ,
  `colTo` VARCHAR(20) ,
  `colTo_so` DOUBLE ,
  `puFr` VARCHAR(20) ,
  `puFr_so` DOUBLE ,
  `puTo` VARCHAR(20) ,
  `puTo_so` DOUBLE ,
  `rootPkt` VARCHAR(30) ,
  `roughPkt` VARCHAR(30) ,
  `costRate` DECIMAL(20,2) ,
  PRIMARY KEY (`id`)
)
ENGINE = MyISAM;

ALTER TABLE `tb_parcelMaster` ADD COLUMN `shFr` VARCHAR(30)  AFTER `costRate`,
 ADD COLUMN `shFr_so` DECIMAL(20,2)  AFTER `shFr`,
 ADD COLUMN `shTo` VARCHAR(30)  AFTER `shFr_so`,
 ADD COLUMN `shTo_so` DECIMAL(20,2)  AFTER `shTo`;
 
 ALTER TABLE `tb_parcelMaster` ADD COLUMN `parcelType` VARCHAR(30)  AFTER `shTo_so`;
ALTER TABLE `tb_parcelMaster` ADD COLUMN `cts` DECIMAL(20,2)  AFTER `parcelType`,
 ADD COLUMN `totalCts` DECIMAL(20,2)  AFTER `cts`;

 
 
 ALTER TABLE `tb_parcelMaster` DROP COLUMN `id`,
 DROP PRIMARY KEY,
 ADD PRIMARY KEY  USING BTREE(`code`);

 
 ALTER TABLE `tb_stockprp` ADD COLUMN `shFr` VARCHAR(30)  AFTER `updateBy`,
 ADD COLUMN `shFr_so` DECIMAL(10,2)  AFTER `shFr`,
 ADD COLUMN `shTo` VARCHAR(30)  AFTER `shFr_so`,
 ADD COLUMN `shTo_so` DECIMAL(10,2)  AFTER `shTo`;

 ALTER TABLE `tb_stockprp` ADD COLUMN `puFr` VARCHAR(30)  AFTER `shTo_so`,
 ADD COLUMN `puFr_so` DECIMAL(10,2)  AFTER `puFr`,
 ADD COLUMN `puTo` VARCHAR(30)  AFTER `puFr_so`,
 ADD COLUMN `puTo_so` DECIMAL(10,2)  AFTER `puTo`;

 ALTER TABLE `tb_stockprp` ADD COLUMN `cFr` VARCHAR(30)  AFTER `puTo_so`,
 ADD COLUMN `cFr_so` DECIMAL(10,2)  AFTER `cFr`,
 ADD COLUMN `cTo` VARCHAR(30)  AFTER `cFr_so`,
 ADD COLUMN `cTo_so` DECIMAL(10,2)  AFTER `cTo`;
 ALTER TABLE `tb_stockprp` MODIFY COLUMN `SH` VARCHAR(25)  CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL;

  ALTER TABLE `tb_stockprp`
 ADD COLUMN `AVGCTS` decimal(10,2) AFTER `cTo_so`,
 ADD COLUMN `SIEVE` varchar(30) after `AVGCTS`,
 ADD COLUMN `SIEVE_so` decimal(10,2) after `SIEVE`;
 ALTER TABLE `tb_stockprp` ADD COLUMN `PPC` DECIMAL(10,2)  AFTER `SIEVE_so`;

ALTER TABLE `tb_stockprp` ADD COLUMN `PTYP` VARCHAR(20)  AFTER `PPC`,
 ADD COLUMN `PTYP_so` DECIMAL(10,2)  AFTER `PTYP`;
 
 #TempSQL---------NOT TO RUN INSTEAD RUN DATA
insert into tb_module_prp (select prpId, 'MEMO_MX', flag, fieldDisplayType, sortId, dispDesc from tb_module_prp where module ='MEMO');
update tb_module_prp set flag = 0 where module = 'MEMO_MX';
#insert 4 prp in master, module and section
insert into tb_prpsectionlist (SELECT prpId,4,prp, validFlag,WebHdr,WebDesc,sortId,subSectionId,DisplayDataType,width FROM tb_prpsectionlist where sectionid = 3);


#--------------------
ALTER TABLE `tb_stockmaster` MODIFY COLUMN `Pcs` DECIMAL(12,2)  DEFAULT 1;
ALTER TABLE `tb_stockmaster` MODIFY COLUMN `totalPcs` DECIMAL(10,2)  DEFAULT NULL;
ALTER TABLE `tb_stockmaster` ADD COLUMN `issueCts` DECIMAL(10,2)  DEFAULT NULL AFTER `askingPriceDisc`;
ALTER TABLE `tb_orderdetail` MODIFY COLUMN `pcs` DECIMAL(10,2)  DEFAULT NULL;
ALTER TABLE `tb_orderdetail` ADD COLUMN `rejCts` DECIMAL(10,2)  AFTER `addDisc`;
ALTER TABLE `tb_invoiceDetail` ADD COLUMN `rejCts` DECIMAL(10,2)  AFTER `addDisc`;
ALTER TABLE `tb_parcelMaster` ADD COLUMN `status` integer  DEFAULT 1 AFTER `totalCts`;

CREATE TABLE `tb_pktProcessHistory` (
  `pktId` INTEGER  NOT NULL,
  `status` INTEGER ,
  `cts` DECIMAL(10,3) ,
  `rate` DECIMAL(20,2) ,
  `process` VARCHAR(10) ,
  `updateDateTime` DATETIME ,
  `updatedBy` INTEGER 
)
ENGINE = MyISAM;
ALTER TABLE `tb_pktProcessHistory` ADD COLUMN `refPktId` INTEGER  AFTER `updatedBy`;
ALTER TABLE `tb_stockmaster` ADD COLUMN `mixType` VARCHAR(10)  AFTER `issueCts`;
CREATE TABLE `tb_pktProcess` (
  `id` INTEGER  NOT NULL AUTO_INCREMENT,
  `processName` VARCHAR(20) ,
  `refPktId` INTEGER ,
  `status` TINYINT ,
  PRIMARY KEY (`id`)
)
ENGINE = MyISAM;
ALTER TABLE `tb_pktProcess` ADD COLUMN `updateDate` DATETIME  AFTER `status`,
 ADD COLUMN `updatedBy` INTEGER  AFTER `updateDate`;
 
ALTER TABLE `tb_priceHistory` ADD COLUMN `rapPrice` decimal(15,3) AFTER `userId`;
ALTER TABLE `tb_priceHistory` ADD COLUMN `section` VARCHAR(50) AFTER `rapPrice`;

ALTER TABLE `tb_purchasemaster` ADD COLUMN `expenses` DOUBLE  AFTER `tax`,
 ADD COLUMN `exRate` DOUBLE  AFTER `expenses`,
 ADD COLUMN `currency` VARCHAR(10)  AFTER `exRate`,
 ADD COLUMN `paymentTerm` VARCHAR(15)  AFTER `currency`;
 
 CREATE TABLE `tb_version` (
  `dbName` VARCHAR(30) ,
  `version` VARCHAR(30) ,
  `versionNum` DECIMAL(10,2) 
)
ENGINE = MyISAM;
insert into tb_version (dbName,version,versionNum)values('projectile','v1.1.3',111);
#----Data----
 INSERT INTO `tb_prpmaster` VALUES 
(50,'RAPPRICE',0,500,0,'Rap Price',0,'0.00','1000000.00'),
 (51,'COL',1,510,0,'Parcel Color',0,'0.00','0.00'),
 (52,'PPC',0,520,0,'PPC',0,'0.00','1000000.00'),
 (53,'TOTAL',0,530,0,'Total',0,'0.00','10000000.00'),
 (54,'PTYP',1,540,0,'Parcel Type',0,'0.00','0.00'),
 (55,'SIEVE',1,550,0,'Sieve',0,NULL,NULL);


# DB VERSION -1.1.3 build 2

 
 ALTER TABLE `tb_stockprp` ADD COLUMN `rateLab` DECIMAL(12,2)  DEFAULT NULL AFTER PTYP_so,
 ADD COLUMN `rapLab` DECIMAL(6,2)  DEFAULT NULL AFTER rateLab;
ALTER TABLE `tb_stockprp` ADD COLUMN `rapPriceLab` DECIMAL(12,2)  AFTER `rapLab`;
ALTER TABLE `tb_partyAcc` ADD COLUMN `currency` VARCHAR(25)  AFTER `activeFlag`;
ALTER TABLE `tb_bankAccounts` ADD COLUMN `branchName` VARCHAR(50)  AFTER `partyAccId`;
ALTER TABLE `tb_payment` ADD COLUMN `chequeDate` DATETIME  AFTER `dsc`;

ALTER TABLE `tb_payment` ADD COLUMN `chequeNo` VARCHAR(50)  AFTER `chequeDate`,
 ADD COLUMN `currency` VARCHAR(30)  AFTER `chequeNo`;


 #----DB 1.1.3 build 3 starts 
ALTER TABLE `tb_payment` ADD COLUMN `clearDate` DATETIME  AFTER `currency`,
 ADD COLUMN `clearStatus` TINYINT  AFTER `clearDate`;
 
 ALTER TABLE `tb_paymentDetails` ADD COLUMN `chequeDate` DATETIME  AFTER `id`,
 ADD COLUMN `chequeNo` VARCHAR(50)  AFTER `chequeDate`,
 ADD COLUMN `clearDate` DATETIME  AFTER `chequeNo`,
 ADD COLUMN `clearStatus` TINYINT  AFTER `clearDate`,
 ADD COLUMN `bank` VARCHAR(50)  AFTER `clearStatus`,
 ADD COLUMN `bankAccNo` VARCHAR(50)  AFTER `bank`;
ALTER TABLE `tb_paymentDetails` ADD COLUMN `dsc` TEXT  AFTER `bankAccNo`;

ALTER TABLE `tb_payment` DROP COLUMN `chequeDate`,
 DROP COLUMN `chequeNo`,
 DROP COLUMN `clearDate`,
 DROP COLUMN `clearStatus`;
ALTER TABLE `tb_paymentDetails` MODIFY COLUMN `payType` VARCHAR(15)  CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL;
ALTER TABLE `tb_paymentDetails` MODIFY COLUMN `amount` DECIMAL(20,2)  DEFAULT NULL;

CREATE TABLE  `tb_memostatus` (
  `id` int(11) NOT NULL,
  `description` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE  `tb_memotype` (
  `id` int(11) NOT NULL,
  `description` varchar(25) DEFAULT NULL,
  `shortcode` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


ALTER TABLE `tb_version` MODIFY COLUMN `version` VARCHAR(15)  CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
 CHANGE COLUMN `versionNum` `build` DECIMAL(10,2)  DEFAULT NULL,
 ADD COLUMN `dbVersion` VARCHAR(15)  AFTER `build`;
	 
ALTER TABLE `tb_invoiceMaster` ADD COLUMN `cstVat` varchar(50)  AFTER `updateDate`,
 ADD COLUMN `pan` varchar(50)  AFTER `cstVat`;

 #v 1.1.3 build 3 ends -------

 
ALTER TABLE `tb_invoiceDetail` ADD COLUMN `grandTotal` DECIMAL(20,2)  AFTER `rejCts`; #refers to amount including 
ALTER TABLE `tb_invoiceMaster` ADD COLUMN `grandTotal` DECIMAL(20,2)  AFTER `pan`; #refers to amount including 
#terms update byrComm1 for HRC
update tb_invoiceMaster set finalAmount =  finalAmount/(term -byrComm1/100), grandTotal = finalAmount ; 
update tb_invoiceDetail set finalAmount =  finalAmount/(term -byrComm1/100), grandTotal = finalAmount ; 


 #------DB v 1.1.3 build 4
 ALTER TABLE `tb_excelfile` ADD COLUMN `lab` VARCHAR(25) NULL  AFTER `rapFormat` ;
 
 #---Arun 06-02-2012
 ALTER TABLE `tb_ordermaster` ADD COLUMN `lab` VARCHAR(45) NULL  AFTER `modifiedBy` ;

#---Arun 14-02-2012'Valentine's day ...:)'
 ALTER TABLE `tb_purchaseDetails` ADD COLUMN `totalrate` DECIMAL(20,3) NULL  AFTER `wt` , ADD COLUMN `finalrate` DECIMAL(20,3) NULL  AFTER `totalrate` , ADD COLUMN `fixedexp` DECIMAL(10,3) NULL  AFTER `finalrate` , ADD COLUMN `percentexp` VARCHAR(45) NULL  AFTER `fixedexp` ;
 ALTER TABLE `tb_purchaseDetails` CHANGE COLUMN `percentexp` `percentexp` DECIMAL(10,3) NULL DEFAULT NULL  ;

 #---Arun 15-02-2012
ALTER TABLE `tb_parcelMaster` ADD COLUMN `deleteflag` INT NULL  AFTER `status` , ADD COLUMN `ID` INT NOT NULL AUTO_INCREMENT  AFTER `code` 
, DROP PRIMARY KEY 
, ADD PRIMARY KEY (`ID`) ;

#---Arun 16-02-2012
ALTER TABLE `tb_parcelMaster` ADD COLUMN `PurchaseParcel` TINYINT NULL  AFTER `deleteflag` ;
update tb_purchasemaster set VendorId=1;


CREATE  TABLE `tb_fixedStockPkt` (
  `pktId` VARCHAR(15) NOT NULL ,
  `deleteFlag` TINYINT NULL ,
  `fixedFlag` TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`pktId`) )
ENGINE = MyISAM;


#----Arun 27-02-2012 built 6


CREATE  TABLE `tb_ParcelHistory` (

  `Code` VARCHAR(10) NULL ,
  `pktcode` VARCHAR(10) NULL ,
  `cts` VARCHAR(10) NOT NULL 
  );


  #----Arvind 29-02-2012
  ALTER TABLE `tb_excelmap` CHANGE COLUMN `FileID` `FileID` INT(10) NULL  , CHANGE COLUMN `colIndex` `colIndex` INT(11) NULL  
, DROP PRIMARY KEY ;


ALTER TABLE `tb_partyMaster` ADD COLUMN `ownerName` VARCHAR(50)  AFTER `registrationId`,
ADD COLUMN `ownerMobNo` BIGINT  AFTER `ownerName`;


  
  CREATE  TABLE tb_dateFormatMaster (
  `name` VARCHAR(10) NOT NULL ,
  `value` VARCHAR(45) NULL ,
  PRIMARY KEY (`name`) );
  

ALTER TABLE `tb_partyMaster` MODIFY COLUMN `ownerMobNo` BIGINT  DEFAULT NULL;

delimiter $$
CREATE FUNCTION `dateformat`(oldDate VARCHAR(20)) RETURNS varchar(20) CHARSET latin1
    READS SQL DATA
BEGIN
    DECLARE format VARCHAR(10);    
    DECLARE newDate varchar(20);    
    
    select value into format from tb_dateFormatMaster where name in (SELECT value
    FROM tb_sysPrefs
    WHERE name = 'date_format');
    
    SELECT DATE_FORMAT(oldDate, format) INTO newDate ;    
    RETURN(newDate);
END
#-------Arun 15-03-2012---------

CREATE  TABLE `tb_dashBoard` (
  `query` VARCHAR(700) NOT NULL ,
  `status` TINYINT NULL ,
  `type` VARCHAR(45) NULL ,
  `params` INT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`query`) )
ENGINE = InnoDB;

#----Arun 17-03-2012-----
ALTER TABLE `tb_dashBoard` ADD COLUMN `divPosition` TINYINT NULL  AFTER `description` ;


 
ALTER TABLE `tb_purchaseDetails` CHANGE COLUMN `PKTID` `pktcode` VARCHAR(25) NOT NULL  
, DROP PRIMARY KEY 
, ADD PRIMARY KEY (`purchaseId`, `pktcode`) ;
 
 
 CREATE  TABLE `tb_parcelHistory` (
  `Code` VARCHAR(45) NOT NULL ,
  `pktCode` VARCHAR(45) NOT NULL ,
  `cts` VARCHAR(45) NULL ,
  `purchaseId` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`code`, `pktCode`, `purchaseId`) );

#--------Arun 27-03-2012--------

ALTER TABLE `tb_excelfile` ADD COLUMN `pktType` VARCHAR(10) NULL  AFTER `lab` ;


#-----------version  bild 9
ALTER TABLE `tb_angadiaMaster` ADD COLUMN `currCode` VARCHAR(50)  AFTER `accCode`;


#---------Arun--------
ALTER TABLE `tb_paymentDetails` ADD COLUMN `USD` DECIMAL(20,2) NULL  AFTER `dsc` ;
ALTER TABLE `tb_payment` DROP COLUMN `exRate` ;
ALTER TABLE `tb_paymentDetails` ADD COLUMN `exRate` DECIMAL(10,2) NULL  AFTER `USD` ;
UPDATE `tb_bankaccounts` SET `bankAccountName`='Checking Account' WHERE `id`='3';
ALTER TABLE `tb_paymentDetails` ADD COLUMN `actualEnteredAmt` DECIMAL(20,2) NULL  AFTER `exRate` ;

ALTER TABLE `tb_purchasemaster` ADD COLUMN `paidStatus` INT(1) NULL  AFTER `paymentTerm` ,
ADD COLUMN `paidAmount` DECIMAL(20,2) NULL  AFTER `paidStatus` ;

ALTER TABLE `tb_invoiceMaster` CHANGE COLUMN `paidAmt` `paidAmt` DECIMAL(15,2) NULL DEFAULT '0'  ;
ALTER TABLE `tb_purchasemaster` ADD COLUMN `glTransNo` INT(11) NULL DEFAULT -1  AFTER `paidAmount` ;
UPDATE `tb_currencies` SET `currAbrev`='QAR' WHERE `currAbrev`='QTR';
ALTER TABLE `tb_purchaseDetails` ADD COLUMN `oldrate` DECIMAL(15,3) NULL 
DEFAULT NULL  AFTER `percentexp` , ADD COLUMN `lastModified` DATETIME NULL 
DEFAULT NULL  AFTER `oldrate` , ADD COLUMN `modifiedBy` INT(11) NULL DEFAULT NULL  AFTER `lastModified` ;
ALTER TABLE `tb_purchasedetails` ADD COLUMN `parcelId` INT(11) NULL DEFAULT NULL  AFTER `pktcode` ;
ALTER TABLE `tb_purchasemaster` ADD COLUMN `partyAccId` INT(11) NULL DEFAULT -1  AFTER `userId` ;

'tar' -xvf projectile19052012.tar.gz

#delete from excelMap
delete from  `tb_excelmap` where fileID=0;
delete from  `tb_excelmap` where fileID=26;
#insert into excelMap
INSERT INTO `tb_excelmap` VALUES 
(0,'pktCode','Packet Code',1),
 (0,'ownerCode','REFNO',2),
 (0,'SH','Shape',3),
 (0,'CTS','Carats/Weight',4),
 (0,'C','Color',5),
 (0,'PU','Purity/Clarity',6),
 (0,'CT','Cut',7),
 (0,'PO','Polish',8),
 (0,'SY','Symmetry',9),
 (0,'FL','Flourescence',10),
 (0,'FLS','Flourescence Intensity',11),
 (0,'MD-XD-D','Measurement',12),
 (0,'LAB','Lab',13),
 (0,'certLabId','Certificate Number',14),
 (0,'treatment','Treatment',15),
 (0,'rate','Price($)/CTS',16),
 (0,'rap','Cash Discount',17),
 (0,'cashPrice','Cash Price',18),
 (0,'cashDiscount','Cash Discount',19),
 (0,'rapPrice','Rapnet Price',20),
 (0,'FNC','Fancy',21),
 (0,'FNCI','Fancy Intensity',22),
 (0,'FNCO','Fancy Overtone',23),
 (0,'DP','Depth (%)',24),
 (0,'T','Table',25),
 (0,'GTN','Girdle (Thin)',26),
 (0,'GTH','Girdle (Thick)',27),
 (0,'GD','Girdle',28),
 (0,'GC','Girdle Condition',29),
 (0,'CU','Culet',30),
 (0,'CC','Culet Condition',31),
 (0,'CH','Crown Height',32),
 (0,'CA','Crown Angle',33),
 (0,'PD','Pavillion Depth',34),
 (0,'PA','Pavillion Angle',35),
 (0,'LI','Laser Inscription',36),
 (0,'comment','Comment',37),
 (0,'country','Country',38),
 (0,'state','State',39),
 (0,'city','City',40),
 (0,'isMatchedSep','',41),
 (0,'pairStock','Pair Stock',42),
 (0,'allowRapFeed','Allow Rap Feed',43),
 (0,'parcelNum','Parcel Number',44),
 (0,'certLabUrl','Lab Website',45),
 (0,'diamondImage','Diamond Image',46),
 (0,'d3Image','D3 Image',47),
 (0,'tradeShow','Trade Show',48),
 (0,'NT','NATTS',49),
 (0,'baseRate','Base Rate',50),
 (0,'rootPkt','Root Packet',51),
 (0,'vendorId','Vendor ID',52),
 (0,'issueDate','Issue Date',53),
 (0,'remark','Remarks',54),
 (0,'total','Total',55),
 (0,'value','Value',56),
 (0,'pairNo','Pair Number',57),
 (0,'pktCode2','Packet Code 2',58),
 (0,'rapCode','Rapnet Code',59),
 (0,'vendorStockCode','Vendor Stock Code',60),
 (0,'availabilty','Availability',61),
 (0,'sarinFile','Sarin File',62),
 (0,'gemFile','Gem File',63),
 (0,'clientRow','Client Row',64),
 (0,'askingPriceDisc','Asking Price',65),
 (0,'ctrl_no','Control Number',66),
 (0,'DD','Diamond Description',67),
 (0,'PU_ST','Clarity Status',68),
 (0,'C_DESC','Color Description',69),
 (0,'LH','Lr half',70),
 (0,'PAINT','Painting',71),
 (0,'PROP','Properties',72),
 (0,'PAINT_COMM','Paint Comments ',73),
 (0,'KEY2SYMB','Key To Symbol',74),
 (0,'INSCRIPTION','Inscription',75),
 (0,'GD_PER','Girdle Percentage',76),
 (0,'SYNTH_IND','Synthetic Indicator',77),
 (0,'REPORT_COMM','Report Comments',78),
 (0,'SL','Star Length',79),
 (0,'job_no','Issue Date',80),
 (0,'issueDate','Issue Date',81),
 (0,'shTo','shTo',82),
 (0,'shFr','shFr',83),
 (0,'AVGCTS','Cts(Avg)',84),
 (0,'cFr','ColorFrom',85),
 (0,'cTo','ColorTo',86),
 (0,'puTo','ClarityTo',87),
 (0,'puFr','ClarityFrom',88),
 (0,'PTYP','ParcelType',89),
 (0,'sieveFr','SieveFrom',90),
 (0,'sieveTo','SieveTo',91),
 (0,'ppc','ppc',92),
 (0,'SIEVE','SIEVE',93),
 (26,'pktCode','Pkt',0),
 (26,'parcelNum','Parcelnum',1),
 (26,'PTYP','ParcelType',2),
 (26,'sieveFr','SieveFrom',3),
 (26,'sieveTo','SieveTo',4),
 (26,'shFr','ShFrom',5),
 (26,'shTo','ShTo',6),
 (26,'ppc','Pcs',7),
 (26,'AVGCTS','Cts(Avg)',8),
 (26,'CTS','TotalCts',9),
 (26,'rate','Rate',10),
 (26,'rootPkt','Root',11),
 (26,'puFr','ClarityFrom',12),
 (26,'puTo','ClarityTo',13),
 (26,'cFr','ColorFrom',14),
 (26,'cTo','ColorTo',15),
 (26,'total','Cost Price/Cts',16);


UPDATE `tb_prpmaster` SET `Prp`='SIEVE', `PrpDesc`='SIEVE' WHERE `ID`='55';
DELETE FROM `tb_prpmaster` WHERE `ID`='56';

ALTER TABLE `tb_stockprp` ADD COLUMN `sieveFr` VARCHAR(30) NULL , ADD COLUMN `sieveFr_so` DECIMAL(10,2) NULL  AFTER `sieveFr` , ADD COLUMN `sieveTo` VARCHAR(30) NULL  AFTER `sieveFr_so` , ADD COLUMN `sieveTo_so` DECIMAL(10,2) NULL  AFTER `sieveTo` ;


#-----Arun----13-06-2012------
ALTER TABLE `hridhesh`.`tb_purchasemaster` ADD COLUMN `glTransNo` INT(11) NULL DEFAULT -1  AFTER `paidAmount`;
#------Arvind ----27-06-2012----
CREATE TABLE tb_rapnetMaster (
  `id` INT  NOT NULL AUTO_INCREMENT,
  `rapUserName` VARCHAR(50)  NOT NULL,
  `rapPassword` BLOB  NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = MyISAM;
insert into tb_sysPrefs values ("default_sales_tax", "glsetup.sales","varchar",15,4,-1);

insert into tb_sysPrefs values ("default_sales_tax_account", "glsetup.sales","varchar",15,2150,-1);

DROP TABLE IF EXISTS `hridhesh`.`tb_activityMaster`;
CREATE TABLE  tb_activityMaster (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ActivityName` varchar(50) DEFAULT NULL,
  `ActivityDesc` varchar(100) DEFAULT NULL,
  `ParentActivityId` int(11) NOT NULL,
  `ParentActivityName` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hridhesh`.`tb_activitymaster`
--

/*!40000 ALTER TABLE `tb_activitymaster` DISABLE KEYS */;
LOCK TABLES `tb_activitymaster` WRITE;
INSERT INTO tb_activityMaster VALUES  (1,'memo','memo',1,'sales'),
 (2,'sale_mix','sale_mix',1,'sales'),
 (3,'memo_list','memolist',1,'sales'),
 (4,'web_memo','web_memo',1,'sales'),
 (5,'bar_code','bar_code',1,'sales'),
 (6,'Invoice/Memo Return','Invoice/Memo Return',1,'sales'),
 (7,'Sell_PriceEditable','If Sale Price editable It must Active',1,'sales'),
 (8,'Upload_Edit(Big)','Upload or Edit Big',2,'Purchase'),
 (9,'Pending_Stock','Pending_Stock',2,'Purchase'),
 (10,'Invalid_Stock','Invalid_Stock',2,'Purchase'),
 (11,'Available','Available',2,'Purchase'),
 (12,'Mix_Group(Small)','Mix_Group(Small)',2,'Purchase'),
 (13,'Cost_Price','Cost_Price',2,'Purchase'),
 (14,'Small_Upload','Small_Upload',2,'Purchase'),
 (15,'Small_Merge','Small_Merge',2,'Purchase'),
 (16,'Small_Split','Small_Split',2,'Purchase'),
 (17,'Lab','Lab',2,'Purchase'),
 (18,'certUpload','Certification Uploading',2,'Purchase'),
 (19,'Cost_PriceEditable','If Cost Price editable It must Active',2,'Purchase'),
 (20,'PartyList','PartyList',3,'Parties'),
 (21,'AddNew_Party','AddNew_Party',3,'Parties'),
 (22,'Web_Users','Web_Users',3,'Parties'),
 (23,'Terms','Terms',4,'Utility'),
 (24,'File_Mappings','File_Mappings',4,'Utility'),
 (25,'AddFile_Mappings','AddFile_Mappings',4,'Utility'),
 (26,'ChangePassword','ChangePassword',4,'Utility'),
 (27,'UserManager','UserManager',4,'Utility'),
 (28,'RoleManager','RoleManager',4,'Utility'),
 (29,'Currencies','Currencies',4,'Utility'),
 (30,'Receivable','Receivable',5,'Account'),
 (31,'Payable','Payable',5,'Account'),
 (32,'Journal_Entry','Journal_Entry',5,'Account'),
 (33,'BankTransfer','BankTransfer',5,'Account'),
 (34,'BankAccount','BankAccount',5,'Account'),
 (35,'GlAccount','GlAccount',5,'Account'),
 (36,'GlGroup','GlGroup',5,'Account'),
 (37,'GlTypes','GlTypes',5,'Account'),
 (38,'Angadia','Angadia',5,'Account'),
 (39,'Price_History','Price_History',6,'Reports'),
 (40,'Memo_Report','Memo_Report',6,'Reports'),
 (41,'Invoice_Report','Invoice_Report',6,'Reports'),
 (42,'Payment_Report','Payment_Report',6,'Reports'),
 (43,'Stock_Report','Stock_Report',6,'Reports'),
 (44,'Stock_Checking_Report','Stock_Checking_Report',6,'Reports'),
 (45,'Purchase_Report','Purchase_Report',6,'Reports'),
 (46,'SaleReportPartyWise','SaleReportPartyWise',6,'Reports'),
 (47,'Sale_Report','Sale_Report',6,'Reports'),
 (48,'BankStatement_Report','BankStatement_Report',6,'Reports'),
 (49,'Angadia_Report','Angadia_Report',6,'Reports'),
 (50,'Profit/Loss_Report','Profit/Loss_Report',6,'Reports'),
 (51,'PurchaseWise_Profit/Loss_Report','PurchaseWise_Profit/Loss_Report',6,'Reports'),
 (52,'Gl_Report','Gl_Report',6,'Reports'),
 (53,'CustomerBalance_Report','CustomerBalance_Report',6,'Reports'),
 (54,'SupplierBalance_Report','SupplierBalance_Report',6,'Reports'),
 (55,'Payment_Report','Payment_Report',6,'Reports'),
 (56,'Tax_Report','Tax_Report',6,'Reports'),
 (57,'BalanceSheet','BalanceSheet',6,'Reports'),
 (58,'Brokerage_Report','Brokerage_Report',6,'Reports'),
 (59,'All_Branch','View all Branches Reports',6,'Reports');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tb_activitymaster` ENABLE KEYS */;


--
-- Definition of table `hridhesh`.`tb_useractivity`
--

DROP TABLE IF EXISTS `hridhesh`.`tb_userActivity`;
CREATE TABLE  `hridhesh`.`tb_userActivity` (
  `activityId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `accessFlag` varchar(25) DEFAULT NULL,
  `roleId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`activityId`,`userId`,`roleId`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hridhesh`.`tb_useractivity`
--

/*!40000 ALTER TABLE `tb_useractivity` DISABLE KEYS */;
LOCK TABLES `tb_userActivity` WRITE;
INSERT INTO `hridhesh`.`tb_userActivity` VALUES  (1,0,'0',1),
 (1,0,'0',2),
 (1,0,'0',3),
 (1,0,'0',4),
 (1,0,'1',5),
 (1,0,'0',6),
 (1,0,'0',7),
 (2,0,'0',1),
 (2,0,'0',2),
 (2,0,'0',3),
 (2,0,'0',4),
 (2,0,'1',5),
 (2,0,'0',6),
 (2,0,'0',7),
 (3,0,'0',1),
 (3,0,'0',2),
 (3,0,'0',3),
 (3,0,'0',4),
 (3,0,'1',5),
 (3,0,'0',6),
 (3,0,'0',7),
 (4,0,'0',1),
 (4,0,'0',2),
 (4,0,'0',3),
 (4,0,'0',4),
 (4,0,'1',5),
 (4,0,'0',6),
 (4,0,'0',7),
 (5,0,'0',1),
 (5,0,'0',2),
 (5,0,'0',3),
 (5,0,'0',4),
 (5,0,'1',5),
 (5,0,'0',6),
 (5,0,'0',7),
 (6,0,'0',1),
 (6,0,'0',2),
 (6,0,'0',3),
 (6,0,'0',4),
 (6,0,'1',5),
 (6,0,'0',6),
 (6,0,'0',7),
 (7,0,'0',1),
 (7,0,'0',2),
 (7,0,'0',3),
 (7,0,'0',4),
 (7,0,'1',5),
 (7,0,'0',6),
 (7,0,'0',7),
 (8,0,'0',1),
 (8,0,'0',2),
 (8,0,'0',3),
 (8,0,'0',4),
 (8,0,'1',5),
 (8,0,'0',6),
 (8,0,'0',7),
 (9,0,'0',1),
 (9,0,'0',2),
 (9,0,'0',3),
 (9,0,'0',4),
 (9,0,'1',5),
 (9,0,'0',6),
 (9,0,'0',7),
 (10,0,'0',1),
 (10,0,'0',2),
 (10,0,'0',3),
 (10,0,'0',4),
 (10,0,'1',5),
 (10,0,'0',6),
 (10,0,'0',7),
 (11,0,'0',1),
 (11,0,'0',2),
 (11,0,'0',3),
 (11,0,'0',4),
 (11,0,'1',5),
 (11,0,'0',6),
 (11,0,'0',7),
 (12,0,'0',1),
 (12,0,'0',2),
 (12,0,'0',3),
 (12,0,'0',4),
 (12,0,'1',5),
 (12,0,'0',6),
 (12,0,'0',7),
 (13,0,'0',1),
 (13,0,'0',2),
 (13,0,'0',3),
 (13,0,'0',4),
 (13,0,'1',5),
 (13,0,'0',6),
 (13,0,'0',7),
 (14,0,'0',1),
 (14,0,'0',2),
 (14,0,'0',3),
 (14,0,'0',4),
 (14,0,'1',5),
 (14,0,'0',6),
 (14,0,'0',7),
 (15,0,'0',1),
 (15,0,'0',2),
 (15,0,'0',3),
 (15,0,'0',4),
 (15,0,'1',5),
 (15,0,'0',6),
 (15,0,'0',7),
 (16,0,'0',1),
 (16,0,'0',2),
 (16,0,'0',3),
 (16,0,'0',4),
 (16,0,'1',5),
 (16,0,'0',6),
 (16,0,'0',7),
 (17,0,'0',1),
 (17,0,'0',2),
 (17,0,'0',3),
 (17,0,'0',4),
 (17,0,'1',5),
 (17,0,'0',6),
 (17,0,'0',7),
 (18,0,'0',1),
 (18,0,'0',2),
 (18,0,'0',3),
 (18,0,'0',4),
 (18,0,'1',5),
 (18,0,'0',6),
 (18,0,'0',7),
 (19,0,'0',1),
 (19,0,'0',2),
 (19,0,'0',3),
 (19,0,'0',4),
 (19,0,'1',5),
 (19,0,'0',6),
 (19,0,'0',7),
 (20,0,'0',1),
 (20,0,'0',2),
 (20,0,'0',3),
 (20,0,'0',4),
 (20,0,'1',5),
 (20,0,'0',6),
 (20,0,'0',7),
 (21,0,'0',1),
 (21,0,'0',2),
 (21,0,'0',3),
 (21,0,'0',4),
 (21,0,'1',5),
 (21,0,'0',6),
 (21,0,'0',7),
 (22,0,'0',1),
 (22,0,'0',2),
 (22,0,'0',3),
 (22,0,'0',4),
 (22,0,'1',5),
 (22,0,'0',6),
 (22,0,'0',7),
 (23,0,'0',1),
 (23,0,'0',2),
 (23,0,'0',3),
 (23,0,'0',4),
 (23,0,'1',5),
 (23,0,'0',6),
 (23,0,'0',7),
 (24,0,'0',1),
 (24,0,'0',2),
 (24,0,'0',3),
 (24,0,'0',4),
 (24,0,'1',5),
 (24,0,'0',6),
 (24,0,'0',7),
 (25,0,'0',1),
 (25,0,'0',2),
 (25,0,'0',3),
 (25,0,'0',4),
 (25,0,'1',5),
 (25,0,'0',6),
 (25,0,'0',7),
 (26,0,'0',1),
 (26,0,'0',2),
 (26,0,'0',3),
 (26,0,'0',4),
 (26,0,'1',5),
 (26,0,'0',6),
 (26,0,'0',7),
 (27,0,'0',1),
 (27,0,'0',2),
 (27,0,'0',3),
 (27,0,'0',4),
 (27,0,'1',5),
 (27,0,'0',6),
 (27,0,'0',7),
 (28,0,'0',1),
 (28,0,'0',2),
 (28,0,'0',3),
 (28,0,'0',4),
 (28,0,'1',5),
 (28,0,'0',6),
 (28,0,'0',7),
 (29,0,'0',1),
 (29,0,'0',2),
 (29,0,'0',3),
 (29,0,'0',4),
 (29,0,'1',5),
 (29,0,'0',6),
 (29,0,'0',7),
 (30,0,'0',1),
 (30,0,'0',2),
 (30,0,'0',3),
 (30,0,'0',4),
 (30,0,'1',5),
 (30,0,'0',6),
 (30,0,'0',7),
 (31,0,'0',1),
 (31,0,'0',2),
 (31,0,'0',3),
 (31,0,'0',4),
 (31,0,'1',5),
 (31,0,'0',6),
 (31,0,'0',7),
 (32,0,'0',1),
 (32,0,'0',2),
 (32,0,'0',3),
 (32,0,'0',4),
 (32,0,'1',5),
 (32,0,'0',6),
 (32,0,'0',7),
 (33,0,'0',1),
 (33,0,'0',2),
 (33,0,'0',3),
 (33,0,'0',4),
 (33,0,'1',5),
 (33,0,'0',6),
 (33,0,'0',7),
 (34,0,'0',1),
 (34,0,'0',2),
 (34,0,'0',3),
 (34,0,'0',4),
 (34,0,'1',5),
 (34,0,'0',6),
 (34,0,'0',7),
 (35,0,'0',1),
 (35,0,'0',2),
 (35,0,'0',3),
 (35,0,'0',4),
 (35,0,'1',5),
 (35,0,'0',6),
 (35,0,'0',7),
 (36,0,'0',1),
 (36,0,'0',2),
 (36,0,'0',3),
 (36,0,'0',4),
 (36,0,'1',5),
 (36,0,'0',6),
 (36,0,'0',7),
 (37,0,'0',1),
 (37,0,'0',2),
 (37,0,'0',3),
 (37,0,'0',4),
 (37,0,'1',5),
 (37,0,'0',6),
 (37,0,'0',7),
 (38,0,'0',1),
 (38,0,'0',2),
 (38,0,'0',3),
 (38,0,'0',4),
 (38,0,'1',5),
 (38,0,'0',6),
 (38,0,'0',7),
 (39,0,'0',1),
 (39,0,'0',2),
 (39,0,'0',3),
 (39,0,'0',4),
 (39,0,'1',5),
 (39,0,'0',6),
 (39,0,'0',7),
 (40,0,'0',1),
 (40,0,'0',2),
 (40,0,'0',3),
 (40,0,'0',4),
 (40,0,'1',5),
 (40,0,'0',6),
 (40,0,'0',7),
 (41,0,'0',1),
 (41,0,'0',2),
 (41,0,'0',3),
 (41,0,'0',4),
 (41,0,'1',5),
 (41,0,'0',6),
 (41,0,'0',7),
 (42,0,'0',1),
 (42,0,'0',2),
 (42,0,'0',3),
 (42,0,'0',4),
 (42,0,'1',5),
 (42,0,'0',6),
 (42,0,'0',7),
 (43,0,'0',1),
 (43,0,'0',2),
 (43,0,'0',3),
 (43,0,'0',4),
 (43,0,'1',5),
 (43,0,'0',6),
 (43,0,'0',7),
 (44,0,'0',1),
 (44,0,'0',2),
 (44,0,'0',3),
 (44,0,'0',4),
 (44,0,'1',5),
 (44,0,'0',6),
 (44,0,'0',7),
 (45,0,'0',1),
 (45,0,'0',2),
 (45,0,'0',3),
 (45,0,'0',4),
 (45,0,'1',5),
 (45,0,'0',6),
 (45,0,'0',7),
 (46,0,'0',1),
 (46,0,'0',2),
 (46,0,'0',3),
 (46,0,'0',4),
 (46,0,'1',5),
 (46,0,'0',6),
 (46,0,'0',7),
 (47,0,'0',1),
 (47,0,'0',2),
 (47,0,'0',3),
 (47,0,'0',4),
 (47,0,'1',5),
 (47,0,'0',6),
 (47,0,'0',7),
 (48,0,'0',1),
 (48,0,'0',2),
 (48,0,'0',3),
 (48,0,'0',4),
 (48,0,'1',5),
 (48,0,'0',6),
 (48,0,'0',7),
 (49,0,'0',1),
 (49,0,'0',2),
 (49,0,'0',3),
 (49,0,'0',4),
 (49,0,'1',5),
 (49,0,'0',6),
 (49,0,'0',7),
 (50,0,'0',1),
 (50,0,'0',2),
 (50,0,'0',3),
 (50,0,'0',4),
 (50,0,'1',5),
 (50,0,'0',6),
 (50,0,'0',7),
 (51,0,'0',1),
 (51,0,'0',2),
 (51,0,'0',3),
 (51,0,'0',4),
 (51,0,'1',5),
 (51,0,'0',6),
 (51,0,'0',7),
 (52,0,'0',1),
 (52,0,'0',2),
 (52,0,'0',3),
 (52,0,'0',4),
 (52,0,'1',5),
 (52,0,'0',6),
 (52,0,'0',7),
 (53,0,'0',1),
 (53,0,'0',2),
 (53,0,'0',3),
 (53,0,'0',4),
 (53,0,'1',5),
 (53,0,'0',6),
 (53,0,'0',7),
 (54,0,'0',1),
 (54,0,'0',2),
 (54,0,'0',3),
 (54,0,'0',4),
 (54,0,'1',5),
 (54,0,'0',6),
 (54,0,'0',7),
 (55,0,'0',1),
 (55,0,'0',2),
 (55,0,'0',3),
 (55,0,'0',4),
 (55,0,'1',5),
 (55,0,'0',6),
 (55,0,'0',7),
 (56,0,'0',1),
 (56,0,'0',2),
 (56,0,'0',3),
 (56,0,'0',4),
 (56,0,'1',5),
 (56,0,'0',6),
 (56,0,'0',7),
 (57,0,'0',1),
 (57,0,'0',2),
 (57,0,'0',3),
 (57,0,'0',4),
 (57,0,'1',5),
 (57,0,'0',6),
 (57,0,'0',7),
 (58,0,'0',1),
 (58,0,'0',2),
 (58,0,'0',3),
 (58,0,'0',4),
 (58,0,'1',5),
 (58,0,'0',6),
 (58,0,'0',7),
 (59,0,'0',1),
 (59,0,'0',2),
 (59,0,'0',3),
 (59,0,'0',4),
 (59,0,'1',5),
 (59,0,'0',6),
 (59,0,'0',7);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tb_useractivity` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

ALTER TABLE `hridhesh`.`tb_partyAcc` ADD COLUMN `userId` INT  NOT NULL AFTER `currency`;


ALTER TABLE `tb_purchaseDetails` ADD COLUMN `oldRate` DECIMAL(15,3)  AFTER `percentexp`,
ADD COLUMN `lastModified` DATETIME  AFTER `oldRate`,

ADD COLUMN `modifiedBy` INTEGER  AFTER `lastModified`;


insert into tb_prpdetail values (23, 'VLGW', 'very light greenish white', 590, 1, now()),(23, 'VLBW', 'very light bluish white ', 600, 1, now())=======
ADD COLUMN `modifiedBy` INTEGER  AFTER `lastModified`;

insert into tb_prpmaster values (56,'LI',1,  560,0, 'Laser Inscription',0,0,1);

insert into tb_prpdetail values 
(56,'Y','Yes',1,1,now()),
(56,'N','No',0,1,now())


ALTER TABLE `tb_stockprp` ADD COLUMN `sieveFr` VARCHAR(45)  AFTER `REPORT_COMM`,
 ADD COLUMN `sieveFr_so` DECIMAL(6,2)  AFTER `sieveFr`,
 ADD COLUMN `sieveTo` VARCHAR(45)  AFTER `sieveFr_so`,
 ADD COLUMN `sieveTo_so` DECIMAL(6,2)  AFTER `sieveTo`;
 
 ALTER TABLE `tb_stockprp` ADD COLUMN `comment1` VARCHAR(150)  AFTER `sieveTo_so`,
 ADD COLUMN `comment2` VARCHAR(150)  AFTER `comment1`,
 ADD COLUMN `comment3` VARCHAR(150)  AFTER `comment2`;

 insert into tb_excelmap  values (0, 'comment1', 'comment1', 90),(0, 'comment2', 'comment2', 91),(0, 'comment3', 'comment3', 92)
 
 /* Changed by Arvind 11 sep 2012*/
 
 ALTER TABLE `hridhesh`.`tb_purchasemaster` ADD COLUMN `paidStatus` INT  AFTER `paymentTerm`,
 ADD COLUMN `paidAmount` DECIMAL(20,2)  AFTER `paidStatus`,
 ADD COLUMN `glTransNo` INTEGER  NOT NULL AFTER `paidAmount`;
 
 ALTER TABLE `hridhesh`.`tb_gl_trans` ADD COLUMN `currency` VARCHAR(10)  AFTER `personTypeId`,
 ADD COLUMN `exRate` DECIMAL(10,2)  AFTER `currency`,
 ADD COLUMN `partyAccId` INTEGER  AFTER `exRate`,
 ADD COLUMN `localAmt` DECIMAL(40,2)  AFTER `partyAccId`;

 
 /*End Here*/
