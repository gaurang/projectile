SELECT * FROM tb_excelmap  order by colIndex;

update tb_excelmap set columnname = 'stock#' where colindex =1;
update tb_excelmap set colindex = 32 where columnname = 'CA';
update tb_excelmap set colindex = 14 where columnname = 'certId';

SELECT * FROM tb_stockmaster t;

insert into tb_excelmap (FileID, columnName, ExcelColName, colIndex) values (1,'FLC',null,11);
insert into tb_excelmap (FileID, columnName, ExcelColName, colIndex) values (1,'PA',null,33)
;
delete from tb_excelmap where columnname = 'updateDate';


update tb_excelmap set columnname = 'FLS' where columnname = 'FL';




//
update tb_stockprp sp inner join tb_stockmaster s on sp.pktid = s.id and sp.grpid = 1 set SD = 'NBNG' , SD_so = 500 where upper(s.comment) like 'NO BROWN NO GREEN' ;

update tb_stockprp sp inner join tb_stockmaster s on sp.pktid = s.id and sp.grpid = 1 set SD = 'VLGY' , SD_so = 510 where upper(s.comment) like 'VERY LIGHT GREENISH YELLOW' 
;
update tb_stockprp sp inner join tb_stockmaster s on sp.pktid = s.id and sp.grpid = 1 set SD = 'VLG' , SD_so = 520 where upper(s.comment) like 'VERY LIGHT GREEN' 
;
update tb_stockprp sp inner join tb_stockmaster s on sp.pktid = s.id and sp.grpid = 1 set SD = 'LG' , SD_so = 530 where upper(s.comment) like 'LIGHT GREEN' 
;

update tb_stockprp sp inner join tb_stockmaster s on sp.pktid = s.id and sp.grpid = 1 set SD = 'GG' , SD_so = 540 where upper(s.comment) like 'GREYISH GREEN' 
;

update tb_stockprp sp inner join tb_stockmaster s on sp.pktid = s.id and sp.grpid = 1 set SD = 'FBR' , SD_so = 550 where upper(s.comment) like 'FAINT BROWN' 
;


update tb_stockprp sp inner join tb_stockmaster s on sp.pktid = s.id and sp.grpid = 1 set SD = 'BR' , SD_so = 560 where upper(s.comment) like 'BROWN' 
;

update tb_stockprp sp inner join tb_stockmaster s on sp.pktid = s.id and sp.grpid = 1 set SD = 'FB' , SD_so = 570 where upper(s.comment) like 'FAINT BLUE' 
;

update tb_stockprp sp inner join tb_stockmaster s on sp.pktid = s.id and sp.grpid = 1 set SD = 'FP' , SD_so = 580 where upper(s.comment) like 'FAINT PINK' 
;


--arun 05-01-2012
delete from hridhesh.tb_orderstatusmaster;
insert into hridhesh.tb_orderstatusmaster values('1', 'Pending');
insert into hridhesh.tb_orderstatusmaster values('2', 'Approved');
insert into hridhesh.tb_orderstatusmaster values('3','Rejected');
insert into hridhesh.tb_orderstatusmaster values('4','General');
insert into hridhesh.tb_orderstatusmaster values('5','Confirm/Invoice');
insert into hridhesh.tb_orderstatusmaster values('6','Transfer');
insert into hridhesh.tb_orderstatusmaster values('7','Laser Inscription');
insert into hridhesh.tb_orderstatusmaster values('8','Certification Issue');
insert into hridhesh.tb_orderstatusmaster values('9','Export Memo');
insert into hridhesh.tb_orderstatusmaster values('10','Consignment Memo');


--Arun 01-02-2012
ALTER TABLE `tb_stockprp` COLLATE = latin1_bin , ADD COLUMN `job_no` VARCHAR(45) NULL  AFTER `rapPriceLab` , ADD COLUMN `ctrl_no` VARCHAR(45) NULL  AFTER `job_no` , ADD COLUMN `DD` VARCHAR(10) NULL  AFTER `ctrl_no` , ADD COLUMN `PU_ST` VARCHAR(10) BINARY NULL  AFTER `DD` , ADD COLUMN `C_DESC` VARCHAR(45) NULL  AFTER `PU_ST` , ADD COLUMN `LH` VARCHAR(10) NULL  AFTER `C_DESC` , ADD COLUMN `PAINT` VARCHAR(10) NULL  AFTER `LH` , ADD COLUMN `PROP` VARCHAR(45) NULL  AFTER `PAINT` , ADD COLUMN `PAINT_COMM` VARCHAR(45) NOT NULL  AFTER `PROP` , ADD COLUMN `KEY2SYMB` VARCHAR(45) NOT NULL  AFTER `PAINT_COMM` , ADD COLUMN `INSCRIPTION` VARCHAR(45) NOT NULL  AFTER `KEY2SYMB` , ADD COLUMN `GD_PER` VARCHAR(45) NULL  AFTER `INSCRIPTION` , ADD COLUMN `SYNTH_IND` VARCHAR(45) NULL  AFTER `GD_PER` , ADD COLUMN `REPORT_COMM` VARCHAR(150) NULL  AFTER `SYNTH_IND` , CHANGE COLUMN `BI` `BI` VARCHAR(25) NULL DEFAULT NULL  AFTER `TI_so` , CHANGE COLUMN `FL_so` `FL_so` DECIMAL(12,2) NULL DEFAULT NULL  AFTER `PO` ;
ALTER TABLE `tb_stockprp` CHANGE COLUMN `PAINT_COMM` `PAINT_COMM` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL  , CHANGE COLUMN `KEY2SYMB` `KEY2SYMB` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL  , CHANGE COLUMN `INSCRIPTION` `INSCRIPTION` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL  ;

UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='job_no', `ExcelColName`='Job Number' WHERE `FileID`='1' and`colIndex`='0';
UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='ctrl_no', `ExcelColName`='Control Number' WHERE `FileID`='1' and`colIndex`='1';
UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='DD', `ExcelColName`='Diamond Description' WHERE `FileID`='1' and`colIndex`='2';
UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='PU_ST', `ExcelColName`='Purity/Clarity Status' WHERE `FileID`='1' and`colIndex`='3';
UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='C_DESC', `ExcelColName`='Color Description' WHERE `FileID`='1' and`colIndex`='4';
UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='LH', `ExcelColName`='Lr HALF' WHERE `FileID`='1' and`colIndex`='5';
UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='PAINT', `ExcelColName`='Painting' WHERE `FileID`='1' and`colIndex`='6';
UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='PROP', `ExcelColName`='Proportion' WHERE `FileID`='1' and`colIndex`='7';
UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='PAINT_COMM', `ExcelColName`='Paint Comm' WHERE `FileID`='1' and`colIndex`='8';
UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='KEY2SYMB', `ExcelColName`='Key to Symbols' WHERE `FileID`='1' and`colIndex`='9';
UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='INSCRIPTION', `ExcelColName`='Inscription' WHERE `FileID`='1' and`colIndex`='10';
UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='GD_PER', `ExcelColName`='Girdle %' WHERE `FileID`='1' and`colIndex`='11';
UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='SYNTH_IND', `ExcelColName`='Synthetic Indicator' WHERE `FileID`='1' and`colIndex`='12';
UPDATE `hridhesh`.`tb_excelmap` SET `FileID`=0, `columnName`='REPORT_COMM', `ExcelColName`='Report Comments' WHERE `FileID`='1' and`colIndex`='13';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Issue Date' WHERE `FileID`='0' and`colIndex`='0';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Packet Code' WHERE `FileID`='0' and`colIndex`='1';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Shape' WHERE `FileID`='0' and`colIndex`='3';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Carats/Weight' WHERE `FileID`='0' and`colIndex`='4';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Color' WHERE `FileID`='0' and`colIndex`='5';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Purity/Clarity' WHERE `FileID`='0' and`colIndex`='6';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Cut' WHERE `FileID`='0' and`colIndex`='7';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Polish' WHERE `FileID`='0' and`colIndex`='8';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Symmetry' WHERE `FileID`='0' and`colIndex`='9';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Flourescence' WHERE `FileID`='0' and`colIndex`='10';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Flourescence Intensity' WHERE `FileID`='0' and`colIndex`='11';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Measurement' WHERE `FileID`='0' and`colIndex`='12';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Fancy' WHERE `FileID`='0' and`colIndex`='21';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Fancy Intensity' WHERE `FileID`='0' and`colIndex`='22';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Fancy Overtone' WHERE `FileID`='0' and`colIndex`='23';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Depth (%)' WHERE `FileID`='0' and`colIndex`='24';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Table' WHERE `FileID`='0' and`colIndex`='25';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Girdle (Thin)' WHERE `FileID`='0' and`colIndex`='26';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Girdle (Thick)' WHERE `FileID`='0' and`colIndex`='27';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Girdle' WHERE `FileID`='0' and`colIndex`='28';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Girdle Condition' WHERE `FileID`='0' and`colIndex`='29';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Culet' WHERE `FileID`='0' and`colIndex`='30';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Culet Condition' WHERE `FileID`='0' and`colIndex`='31';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Crown Height' WHERE `FileID`='0' and`colIndex`='32';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Crown Angle' WHERE `FileID`='0' and`colIndex`='33';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Pavillion Depth' WHERE `FileID`='0' and`colIndex`='34';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Pavillion Angle' WHERE `FileID`='0' and`colIndex`='35';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Laser Inscription' WHERE `FileID`='0' and`colIndex`='36';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Comment' WHERE `FileID`='0' and`colIndex`='37';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Country' WHERE `FileID`='0' and`colIndex`='38';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='NATTS' WHERE `FileID`='0' and`colIndex`='49';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Lab' WHERE `FileID`='0' and`colIndex`='13';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Certificate Number' WHERE `FileID`='0' and`colIndex`='14';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Treatment' WHERE `FileID`='0' and`colIndex`='15';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Price($)/CTS' WHERE `FileID`='0' and`colIndex`='16';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Cash Discount' WHERE `FileID`='0' and`colIndex`='17';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Cash Price' WHERE `FileID`='0' and`colIndex`='18';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Rapnet Price' WHERE `FileID`='0' and`colIndex`='20';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Cash Discount' WHERE `FileID`='0' and`colIndex`='19';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='State' WHERE `FileID`='0' and`colIndex`='39';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='City' WHERE `FileID`='0' and`colIndex`='40';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='' WHERE `FileID`='0' and`colIndex`='41';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Pair Stock' WHERE `FileID`='0' and`colIndex`='42';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Allow Rap Feed' WHERE `FileID`='0' and`colIndex`='43';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Parcel Number' WHERE `FileID`='0' and`colIndex`='44';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Lab Website' WHERE `FileID`='0' and`colIndex`='45';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Diamond Image' WHERE `FileID`='0' and`colIndex`='46';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='D3 Image' WHERE `FileID`='0' and`colIndex`='47';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Trade Show' WHERE `FileID`='0' and`colIndex`='48';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Base Rate' WHERE `FileID`='0' and`colIndex`='50';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Root Packet' WHERE `FileID`='0' and`colIndex`='51';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Vendor ID' WHERE `FileID`='0' and`colIndex`='52';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Issue Date' WHERE `FileID`='0' and`colIndex`='53';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Remarks' WHERE `FileID`='0' and`colIndex`='54';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Total' WHERE `FileID`='0' and`colIndex`='55';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Value' WHERE `FileID`='0' and`colIndex`='56';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Pair Number' WHERE `FileID`='0' and`colIndex`='57';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Packet Code 2' WHERE `FileID`='0' and`colIndex`='58';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Rapnet Code' WHERE `FileID`='0' and`colIndex`='59';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Vendor Stock Code' WHERE `FileID`='0' and`colIndex`='60';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Availability' WHERE `FileID`='0' and`colIndex`='61';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Sarin File' WHERE `FileID`='0' and`colIndex`='62';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Gem File' WHERE `FileID`='0' and`colIndex`='63';
UPDATE `hridhesh`.`tb_excelmap` SET `ExcelColName`='Client Row' WHERE `FileID`='0' and`colIndex`='64';


