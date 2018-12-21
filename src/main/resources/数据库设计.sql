/*
SQLyog Ultimate v13.0.1 (64 bit)
MySQL - 5.7.19 : Database - data_manage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`data_manage` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `data_manage`;

/*Table structure for table `share_data` */

DROP TABLE IF EXISTS `share_data`;

CREATE TABLE `share_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资料id',
  `groupName` varchar(20) NOT NULL COMMENT '小组名称',
  `dataName` varchar(255) NOT NULL COMMENT '资料名称',
  `ip` varchar(20) NOT NULL COMMENT 'ip地址',
  `upName` varchar(8) NOT NULL COMMENT '上传人姓名',
  `dataSummary` varchar(500) DEFAULT NULL COMMENT '资料简介',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `updateDate` datetime NOT NULL COMMENT '更新时间',
  `browseCount` int(11) unsigned DEFAULT '0' COMMENT '浏览次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `share_data` */

insert  into `share_data`(`id`,`groupName`,`dataName`,`ip`,`upName`,`dataSummary`,`createDate`,`updateDate`,`browseCount`) values 
(1,'308','s8王者教程','10.2.132.70','袁大哥','NB到极点的王者上分教程','2018-11-07 12:05:11','2018-11-10 12:24:44',59),
(2,'按摩店','盲人按摩教程','192.168.3.1','朱兴','NB到极点的按摩教程666','2018-11-01 21:39:08','2018-12-09 15:04:33',45),
(3,'川农大雕团','马克思主义与哲学修养','10.2.130.95','任大大','NB到极点的哲学知识,厉害了','2018-10-17 22:41:37','2018-11-27 10:12:22',72),
(4,'309','wcnm','10.2.132.71','小行星','NB到极点的行星知识','2018-10-19 12:06:06','2018-11-09 09:06:12',26),
(5,'308','nbdjd','10.2.132.70','袁洪','NB到极点sss修改资料','2018-11-17 12:10:58','2018-12-09 02:58:13',63);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL COMMENT '学号',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `password` varchar(40) NOT NULL COMMENT '密码',
  `studentClass` varchar(20) NOT NULL COMMENT '班级',
  `contact` varchar(20) NOT NULL COMMENT '联系方式',
  `groupName` varchar(20) NOT NULL COMMENT '小组名称',
  `type` varchar(20) DEFAULT NULL COMMENT '账号类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`password`,`studentClass`,`contact`,`groupName`,`type`) values 
(201603753,'朱兴','8187f65ab99ece1dae6618726495a177','计科4班','17345339658','308','admin'),
(201603756,'袁洪','da597ac25e64cad5e331f45f73f391e4','计科4班','18608212510','308','admin'),
(201603757,'杨忠学','da597ac25e64cad5e331f45f73f391e4','计科201604','110','308','student'),
(201603759,'吴世龙','da597ac25e64cad5e331f45f73f391e4','计科4班','110','308','student');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
