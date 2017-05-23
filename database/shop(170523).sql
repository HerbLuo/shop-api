/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-23 23:18:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `address` varchar(255) NOT NULL,
  `post_code` char(6) NOT NULL,
  `receiver_name` varchar(12) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `is_default` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '1', '中国大陆浙江省宁波市鄞州区高桥镇大红鹰学院', '315100', '罗怀博', '10086666666', '1', '1');
INSERT INTO `address` VALUES ('2', '1', '地址二', '000000', '收货人姓名', '电话号码', '1', '0');
INSERT INTO `address` VALUES ('3', '1', '地址三', '123456', '测试', '000', '2', '0');
INSERT INTO `address` VALUES ('4', '1', '地址四', '887788', '罗怀博', '45678675455', '1', '0');

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` int(10) unsigned NOT NULL,
  `version_code` int(10) unsigned NOT NULL,
  `version_name` varchar(16) NOT NULL,
  `android_download_url` varchar(255) NOT NULL,
  `app_entrance_version` varchar(12) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES ('1', '1', '1.0', 'http://', 'high-v1');

-- ----------------------------
-- Table structure for app_block
-- ----------------------------
DROP TABLE IF EXISTS `app_block`;
CREATE TABLE `app_block` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(16) NOT NULL,
  `title` char(5) NOT NULL,
  `columnType` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_block_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_block
-- ----------------------------
INSERT INTO `app_block` VALUES ('1', 'jiyoujia', '我淘我家', '2');

-- ----------------------------
-- Table structure for app_entrance
-- ----------------------------
DROP TABLE IF EXISTS `app_entrance`;
CREATE TABLE `app_entrance` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `enabled` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `index` tinyint(3) unsigned NOT NULL,
  `name` char(6) NOT NULL,
  `img` varchar(255) NOT NULL DEFAULT 'http://closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/',
  `link` varchar(255) NOT NULL DEFAULT 'wxshop://black',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_entrance
-- ----------------------------
INSERT INTO `app_entrance` VALUES ('1', '1', '0', '天猫', 'http://closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/26296458982d1396e14698be8f059ccef9db0917.png', 'wxshop://black');
INSERT INTO `app_entrance` VALUES ('2', '1', '1', '聚划算', 'http://closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/d7b907b22952ac44f9e2d1029f8d364ac4560a52.png', 'wxshop://black');
INSERT INTO `app_entrance` VALUES ('3', '1', '2', '天猫国际', 'http://closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/18224c9afd17fcf3e15bb03ea95c244e469a452d.png', 'wxshop://black');
INSERT INTO `app_entrance` VALUES ('4', '1', '3', '外卖', 'http://closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/992bc9a571c9418bd7e6feb8f49c6e9ab4aa44da.png', 'wxshop://black');
INSERT INTO `app_entrance` VALUES ('5', '1', '4', '天猫超市', 'http://closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/d0cb8b15a7fbc2ed7e1dd58a3fc5b0ae4cf423a5.png', 'wxshop://black');
INSERT INTO `app_entrance` VALUES ('6', '1', '5', '充值中心', 'http://closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/2fcb04d9c79bcf97d1d7e4d9ac1c15627b3f8c13.png', 'wxshop://black');
INSERT INTO `app_entrance` VALUES ('7', '1', '6', '飞猪旅行', 'http://closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/d2bf542dc0a4c015e16d0f5426abff9d17973979.png', 'wxshop://black');
INSERT INTO `app_entrance` VALUES ('8', '1', '7', '淘金币', 'http://closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/e9f60973ac470de8c2aebb77fc634abad9e49cb8.png', 'wxshop://black');
INSERT INTO `app_entrance` VALUES ('9', '1', '8', '到家', 'http://closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/a9737f07a51d5fe46a198dcd167674c5d70564e7.png', 'wxshop://black');
INSERT INTO `app_entrance` VALUES ('10', '1', '9', '更多', 'http://closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/55164ce1a4ca4db3ff00b729575162a56cd05c30.png', 'wxshop://black');

-- ----------------------------
-- Table structure for app_hotbar
-- ----------------------------
DROP TABLE IF EXISTS `app_hotbar`;
CREATE TABLE `app_hotbar` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type_text` char(2) NOT NULL,
  `title_text` varchar(20) NOT NULL,
  `type` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_hotbar
-- ----------------------------
INSERT INTO `app_hotbar` VALUES ('1', '热评', 'AMD立大功，Intel又着急了', '0');
INSERT INTO `app_hotbar` VALUES ('2', '科技', '小米6只是开胃菜，雷军真正的大招是这个', '0');
INSERT INTO `app_hotbar` VALUES ('3', '热议', '首款双屏机，从5千跌至5百，无人问津!', '0');
INSERT INTO `app_hotbar` VALUES ('4', '热评', '仅用500元，让你的旧电脑飞起来。', '0');
INSERT INTO `app_hotbar` VALUES ('5', '热议', '三年不换手机号的人，一定有以下特点', '0');
INSERT INTO `app_hotbar` VALUES ('6', '围观', '又一国产全面屏手机，售价不足千元', '0');

-- ----------------------------
-- Table structure for app_jiyoujia
-- ----------------------------
DROP TABLE IF EXISTS `app_jiyoujia`;
CREATE TABLE `app_jiyoujia` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `index` tinyint(4) NOT NULL,
  `img` varchar(255) NOT NULL,
  `link` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_jiyoujia
-- ----------------------------
INSERT INTO `app_jiyoujia` VALUES ('1', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/922b2bafacacadf77ab23eed3c89d4481ef24af2.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('2', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/ce626bead9184f9c20bf9131cac5f542af486881.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('3', '2', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/2adf6dd2b31c938a314500977adf7694709a3c05.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('4', '2', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/09fa2487948dd1eb702b3a8bbeaae4fe8071e940.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('5', '2', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/5009469900211335fb9613a8c8e152a7e7f4fabf.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('6', '2', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/f10851f8223bac807ef0cc81d77d3f36cefdeeda.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('7', '3', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/4afa686615dec3d1d839d92eddbd9058dc473bf8.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('8', '3', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/76dfe3b1b159b643b485325784053b4bd584ab9b.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('9', '3', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/5613a2c9fb9e96a2ee46a64a9472c1517e3a6fdb.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('10', '3', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/b2e2a12bfea4bf910d4074aae3897ec8f812ea36.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('11', '4', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/2d57ce513ecd21fa0d1b13e2dc2b6823531228e0.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('12', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/914be64fa616a1989104ddc0e78320b455da7a00.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('13', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/d2da36e2eb9bf2b0b20d509c06f9e440c6f69d8a.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('14', '2', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/0b908aeff1e6e6b450a8fc2beaa57309c6146916.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('15', '2', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/3630b4c535f6c45091a55da071073c1ed85ccc1e.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('16', '2', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/c3824a44a35b1bc2ff4d40a237464307d083b1ec.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('17', '2', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/d1ba140a6d7d73c8df378c07628334d0ca666b61.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('18', '3', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/4b5314dd62cf119a6fc536ba139c8831bdddcb4c.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('19', '3', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/4da584ebf7f3c787a81586cb119d231d3de3f2ae.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('20', '3', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/ab8e3fd1d97536c2d814f63126949331904d71e2.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('21', '3', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/e2f1a53ae497bfa32937590a156d68a06aed788c.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('22', '4', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/0234c4f4a51314a90d7543b51d7189e6584ce440.png', 'wxshop://black');
INSERT INTO `app_jiyoujia` VALUES ('23', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/ea7cdc02ce39fb5967c4b89ed433a6e8b6b03595.png', 'wxshop://black');

-- ----------------------------
-- Table structure for app_jiyoujia_head
-- ----------------------------
DROP TABLE IF EXISTS `app_jiyoujia_head`;
CREATE TABLE `app_jiyoujia_head` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(3) unsigned NOT NULL COMMENT '0: ''left'', 1: ''right'', 2 ''center''',
  `img` varchar(255) NOT NULL,
  `text` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_jiyoujia_head
-- ----------------------------
INSERT INTO `app_jiyoujia_head` VALUES ('1', '0', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/d05fc28759bd6cc2b0ca5e113875e6a4352ed524.jpg', '捷森麦片，让早餐更具鲜活力量');
INSERT INTO `app_jiyoujia_head` VALUES ('2', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/77e8826bcacb93d3d20244c57e946683190e5af9.jpg', '#深夜食堂');
INSERT INTO `app_jiyoujia_head` VALUES ('3', '0', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/787eb4caaf77b683bda59aaa806598179a199511.png', '捷森大麦片，让早餐更具鲜活力量');
INSERT INTO `app_jiyoujia_head` VALUES ('4', '0', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/4751b3be8eae538f9913f02ffa15b7608147cb65.png', '捷森小麦片，让早餐更具鲜活力量');
INSERT INTO `app_jiyoujia_head` VALUES ('5', '0', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/d41af2c6d2ca97ad660894cc3cd165abc7c84a30.png', '捷森高粱片，让早餐更具鲜活力量');
INSERT INTO `app_jiyoujia_head` VALUES ('6', '0', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/e3bae5ca2d46a2001bef7145510003d4aecb7c72.png', '捷森麦片，一顿管饱一天');
INSERT INTO `app_jiyoujia_head` VALUES ('7', '0', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/e58bdc558d1d3423bb7bbc690a8e7683b700e8ef.png', '捷森麦片，让早餐更具鲜活力量');
INSERT INTO `app_jiyoujia_head` VALUES ('8', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/1db13582944688b28b6402291c912adba2478ae0.png', '#包子');
INSERT INTO `app_jiyoujia_head` VALUES ('9', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/5b664d0565753a8594665c0f0259b36f5de4f539.png', '#这是烧卖');
INSERT INTO `app_jiyoujia_head` VALUES ('10', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/51d8b5700102370da9836b3d20ee49a52b40e9ad.png', '#黄焖鸡蛋蛋');

-- ----------------------------
-- Table structure for app_rushbuy
-- ----------------------------
DROP TABLE IF EXISTS `app_rushbuy`;
CREATE TABLE `app_rushbuy` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `img` varchar(255) NOT NULL,
  `index` tinyint(3) unsigned NOT NULL,
  `link` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_rushbuy
-- ----------------------------
INSERT INTO `app_rushbuy` VALUES ('1', null, '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/59f345c1ef5d2f3cecb045a294c842d180f5381a.png', '1', 'wxshop://black');
INSERT INTO `app_rushbuy` VALUES ('2', null, '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/9877fb69904a4156e68e05d600893501e2b0b81a.png', '2', 'wxshop://black');
INSERT INTO `app_rushbuy` VALUES ('3', null, '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/306c8d6f08ce52db68b0acaebce536352a985597.png', '1', 'wxshop://black');
INSERT INTO `app_rushbuy` VALUES ('4', null, '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/0a1216e13f64b80f363677ac3c3f18e75f2dd99c.png', '2', 'wxshop://black');
INSERT INTO `app_rushbuy` VALUES ('5', null, '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/7273c468d0c29feb8e1fe80295570c58d6ed34e0.png', '3', 'wxshop://black');
INSERT INTO `app_rushbuy` VALUES ('6', null, '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/e499a76343b8c8a99a9a925c90d2a4f1ae0f3b2b.png', '4', 'wxshop://black');
INSERT INTO `app_rushbuy` VALUES ('7', null, '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/8b060eb9c83c487880ceca55beb71d1a21c04f01.png', '3', 'wxshop://black');
INSERT INTO `app_rushbuy` VALUES ('8', null, '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/035fc70b6e20b4ec1066a5cfe47bbb07352ebea4.png', '4', 'wxshop://black');
INSERT INTO `app_rushbuy` VALUES ('9', null, '//closx-shop.oss-cn-qingdao.aliyuncs.com/app/v0/93419c22ded0b5a87b1449b3ee9b85a574f7776f.png', '1', 'wxshop://black');

-- ----------------------------
-- Table structure for app_slider
-- ----------------------------
DROP TABLE IF EXISTS `app_slider`;
CREATE TABLE `app_slider` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `imgSrc` varchar(128) NOT NULL,
  `category` int(10) unsigned DEFAULT NULL,
  `itemId` int(10) unsigned DEFAULT NULL,
  `shopId` int(10) unsigned DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL COMMENT 'link 指向的是一个真实连接, 仅当itemId不存在时生效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_slider
-- ----------------------------
INSERT INTO `app_slider` VALUES ('1', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/images/TB2dmzRkYJmpuFjSZFwXXaE4VXa_!!106-0-yamato.jpg_q50.jpg', null, null, null, null);
INSERT INTO `app_slider` VALUES ('2', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/images/TB1CWf9KpXXXXbuXpXXSutbFXXX.jpg_q50.jpg', null, null, null, null);
INSERT INTO `app_slider` VALUES ('3', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/images/TB2lnQFk.hnpuFjSZFEXXX0PFXa_!!88-0-yamato.jpg_q50.jpg', null, null, null, null);
INSERT INTO `app_slider` VALUES ('4', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/images/TB2md5gi9JjpuFjy0FdXXXmoFXa_!!148-0-yamato.jpg_q50.jpg', null, null, null, null);
INSERT INTO `app_slider` VALUES ('5', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/images/TB2P0cDkbBnpuFjSZFGXXX51pXa_!!199-0-yamato.jpg_q50.jpg', null, null, null, null);
INSERT INTO `app_slider` VALUES ('6', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/images/TB2wT4klctnpuFjSZFKXXalFFXa_!!135-0-yamato.jpg_q50.jpg', null, null, null, null);
INSERT INTO `app_slider` VALUES ('7', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/images/TB14ab1KpXXXXclXFXXSutbFXXX.jpg_q50.jpg', null, null, null, null);
INSERT INTO `app_slider` VALUES ('8', '1', '//closx-shop.oss-cn-qingdao.aliyuncs.com/images/TB29B_Pk0RopuFjSZFtXXcanpXa_!!141-0-yamato.jpg_q50.jpg', null, null, null, null);

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `quantity` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '购物车商品数量（以种类计）',
  PRIMARY KEY (`id`),
  KEY `FKaaw0q1kqnkguk4fwqavo9d8t1` (`item_id`),
  CONSTRAINT `FKaaw0q1kqnkguk4fwqavo9d8t1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES ('22', '22', '1', '1');
INSERT INTO `car` VALUES ('23', '22', '1', '1');
INSERT INTO `car` VALUES ('24', '4', '1', '1');
INSERT INTO `car` VALUES ('25', '4', '1', '1');
INSERT INTO `car` VALUES ('26', '22', '1', '1');
INSERT INTO `car` VALUES ('27', '4', '1', '1');

-- ----------------------------
-- Table structure for deliver
-- ----------------------------
DROP TABLE IF EXISTS `deliver`;
CREATE TABLE `deliver` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(64) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `order_a_shop_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `deliver_ibfk_1` (`order_a_shop_id`),
  CONSTRAINT `deliver_ibfk_1` FOREIGN KEY (`order_a_shop_id`) REFERENCES `order_a_shop` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of deliver
-- ----------------------------
INSERT INTO `deliver` VALUES ('1', 'a1237263822', '顺丰', '1');
INSERT INTO `deliver` VALUES ('2', 'a899w8wqwa2', '圆通', '1');
INSERT INTO `deliver` VALUES ('3', 'a1237263822', '顺丰', '1');
INSERT INTO `deliver` VALUES ('4', 'a899w8wqwa2', '圆通', '1');
INSERT INTO `deliver` VALUES ('5', 'a1237263822', '顺丰', '1');
INSERT INTO `deliver` VALUES ('6', 'a899w8wqwa2', '圆通', '1');
INSERT INTO `deliver` VALUES ('7', 'a1237263822', '顺丰', '1');
INSERT INTO `deliver` VALUES ('8', 'a899w8wqwa2', '圆通', '1');
INSERT INTO `deliver` VALUES ('9', 'a1237263822', '顺丰', '3');
INSERT INTO `deliver` VALUES ('10', 'a899w8wqwa2', '圆通', '3');
INSERT INTO `deliver` VALUES ('11', 'a1237263822', '顺丰', '6');
INSERT INTO `deliver` VALUES ('12', 'a899w8wqwa2', '圆通', '6');

-- ----------------------------
-- Table structure for hot
-- ----------------------------
DROP TABLE IF EXISTS `hot`;
CREATE TABLE `hot` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hot_key` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hot
-- ----------------------------
INSERT INTO `hot` VALUES ('1', '宝宝们圣诞快乐');

-- ----------------------------
-- Table structure for integerentity
-- ----------------------------
DROP TABLE IF EXISTS `integerentity`;
CREATE TABLE `integerentity` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of integerentity
-- ----------------------------

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` char(6) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `price` double unsigned NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pic_links_json` varchar(500) DEFAULT NULL,
  `shop_id` int(11) unsigned NOT NULL,
  `detail_div_id` int(11) unsigned DEFAULT NULL,
  `discount` int(11) unsigned DEFAULT NULL COMMENT '降低的价格，非百分比',
  PRIMARY KEY (`id`),
  KEY `name` (`name`),
  KEY `price` (`price`),
  KEY `FKbsswvl04podecnq8d6mbqnblu` (`detail_div_id`),
  KEY `FKso5mqbn1h85iop14ahckkwahh` (`shop_id`),
  CONSTRAINT `FKso5mqbn1h85iop14ahckkwahh` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '1', '2017-03-08 18:52:32', '1', '咱的牛奶不好吃，贵在量多', '100', '1斤新鲜牛奶只要100块', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/9d2db4e02a6777064f4b38722ac23a87.jpg\",\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/39b27aa5ad7a59aa59771d9cb3f20c8e.jpg\",\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/412d6e8ccfb6a187fd919f77b1e3612f.jpg\",\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/dbc80ba3fe16625cae4060cf79918030.jpg\"]', '1', '1', null);
INSERT INTO `item` VALUES ('2', '1', '2017-03-08 18:49:31', '1', '批发牛奶', '50', '好评返现100元', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/b2c203b8e2e1678f766ce2798c18e623.jpg\"]', '1', null, null);
INSERT INTO `item` VALUES ('3', '1', '2017-03-08 18:52:32', '1', '旺仔小馒头，非牛奶', '99.9', '现在不少商家拿牛奶冒充馒头，大家注意啦', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/wangzaixiaomantou.jpg\"]', '1', null, null);
INSERT INTO `item` VALUES ('4', '1', '2017-03-08 18:52:32', '1', '批发牛奶', '25', '新鲜的牛奶', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '2', null, null);
INSERT INTO `item` VALUES ('5', '1', '2017-03-08 18:52:32', '2', '伊利牛奶', '23', '好奶', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '4', null, null);
INSERT INTO `item` VALUES ('6', '1', '2017-03-08 18:52:32', '2', '牛奶', '45', '纯牛奶', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '3', null, null);
INSERT INTO `item` VALUES ('7', '1', '2017-03-08 18:52:32', 'a', '牛奶整箱', '57', '24盒', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '2', null, null);
INSERT INTO `item` VALUES ('8', '1', '2017-03-08 18:52:32', 'a', '牛奶', '78', '进口牛奶', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '3', null, null);
INSERT INTO `item` VALUES ('9', '1', '2017-03-08 18:52:32', 'd', '羊奶，非牛奶', '98', '非常新鲜的羊奶', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '4', null, null);
INSERT INTO `item` VALUES ('10', '1', '2017-03-08 18:52:32', 'a', '饼干', '23', '饼干，搭配牛奶更好吃', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '1', null, null);
INSERT INTO `item` VALUES ('11', '1', '2017-03-08 18:52:32', '1', '羊肉串', '39', '辸的羊肉串', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '3', null, null);
INSERT INTO `item` VALUES ('12', '1', '2017-03-08 18:52:32', 'w', '大包装牛奶', '200', '300ml大包装牛奶', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '2', null, null);
INSERT INTO `item` VALUES ('13', '1', '2017-03-08 18:52:32', 's', '伊利谷粒多谷物牛奶饮品谷物牛奶12*250ml', '44', '伊利谷粒多', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '3', null, null);
INSERT INTO `item` VALUES ('14', '1', '2017-03-08 18:52:32', 'e', '伊利高钙低脂牛奶 纯牛奶250ml*24无菌装', '71.9', '伊利高钙低脂牛奶 纯牛奶250ml*24无菌装', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/gaogainai.jpg\"]', '2', null, null);
INSERT INTO `item` VALUES ('15', '1', '2017-03-08 18:52:32', 'wq', '新希望香蕉牛奶200ml*12盒*2箱 韩国风味早餐奶 整箱果味牛奶', '89', '双十1 爆售 30000箱 生牛乳加 稀奶油调制', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '4', null, null);
INSERT INTO `item` VALUES ('16', '1', '2017-03-08 18:52:32', 'sd', '送瓜子 李子园甜牛奶含乳饮料225ml*24整箱学生饮品江浙沪皖包邮', '40', '送瓜子 李子园甜牛奶含乳饮料225ml*24整箱学生饮品江浙沪皖包邮', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/liziyuan.jpg\"]', '4', null, null);
INSERT INTO `item` VALUES ('17', '1', '2017-03-08 18:52:32', 'q', '甜牛奶', '56', '很甜很甜的牛奶', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '1', null, null);
INSERT INTO `item` VALUES ('18', '1', '2017-03-08 18:52:32', 'w', '酸牛奶', '55', '很酸很酸的牛奶', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/suanniunai.jpg\"]', '1', null, null);
INSERT INTO `item` VALUES ('19', '1', '2017-03-08 18:52:32', 's', '牛奶面包', '3', '新鲜的面包，牛奶味', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '2', null, null);
INSERT INTO `item` VALUES ('20', '1', '2017-03-08 18:52:32', 'd', '鸡蛋面包', '9', '鸡蛋面包', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/38c0266046c2433a6d2558a101efb8ac.jpg\"]', '3', null, null);
INSERT INTO `item` VALUES ('21', '1', '2017-03-08 18:52:32', '1.0', '德芙巧克力，送牛奶', '56', '买三份送一瓶250ml猛牛牛奶', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/qiaokeli.jpg\"]', '1', null, null);
INSERT INTO `item` VALUES ('22', '1', '2017-03-08 18:52:32', '2.0', '光明 利乐砖 纯牛奶促销250ml *24盒/箱 10月产', '61.8', '光明牛奶就是好喝', '[\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/guangmingniunai1.jpg\",\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/guangmingniunai2.jpg\",\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/guangmingniunai3.jpg\",\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/guangmingniunai4.jpg\"]', '1', null, null);

-- ----------------------------
-- Table structure for item_comment
-- ----------------------------
DROP TABLE IF EXISTS `item_comment`;
CREATE TABLE `item_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` int(11) unsigned NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment` varchar(255) DEFAULT NULL,
  `imgs_json` varchar(510) DEFAULT NULL,
  `score` double unsigned NOT NULL DEFAULT '5',
  `user_id` int(11) unsigned NOT NULL,
  `is_anonymous` tinyint(3) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `item_comment_ibfk_1` (`item_id`),
  CONSTRAINT `item_comment_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item_comment
-- ----------------------------
INSERT INTO `item_comment` VALUES ('1', '1', '2016-12-18 16:20:25', '这个东西很好吃', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('2', '1', '2016-12-17 16:20:25', '这个东西不好吃', null, '4', '1', '1');
INSERT INTO `item_comment` VALUES ('3', '1', '2016-12-17 16:21:25', '我买了一锅，不错', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('4', '2', '2016-12-19 17:15:37', '主要是返现', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('5', '2', '2016-12-19 17:16:15', '不得不好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('6', '3', '2016-12-19 17:20:23', '他家的馒头很硬，很小', null, '3', '1', '1');
INSERT INTO `item_comment` VALUES ('7', '3', '2016-12-19 17:21:38', '这馒头，不煮还看得过去，一煮就', null, '2', '1', '1');
INSERT INTO `item_comment` VALUES ('8', '22', '2017-03-04 20:08:46', '一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五', '{\"base\": \"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/customer-comment/\", \"array\": [\"TB2e4CjfCXlpuFjy0FeXXcJbFXa.jpg\", \"TB2GeWvfB0kpuFjSsziXXa.oVXa.jpg\", \"TB2JE.agtBopuFjSZPcXXc9EpXa.jpg\", \"TB2LvMHgxxmpuFjSZFNXXXrRXXa.jpg\", \"TB2P2SIbetTMeFjSZFOXXaTiVXa.jpg\"]}', '5', '1', '1');
INSERT INTO `item_comment` VALUES ('9', '22', '2017-03-05 14:06:39', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('10', '22', '2017-03-05 14:07:00', '一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五', '{\"base\": \"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/customer-comment/\", \"array\": [\"TB2NbTceB0kpuFjSsziXXa.oVXa.jpg\", \"TB2ZIT2X5C9MuFjSZFoXXbUzFXa.jpg\"]}', '5', '1', '1');
INSERT INTO `item_comment` VALUES ('11', '22', '2017-03-05 14:07:04', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('12', '22', '2017-03-05 14:07:49', '这位用户没有评论，默认好评', '{\"base\": \"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/customer-comment/\", \"array\": [\"TB2e4CjfCXlpuFjy0FeXXcJbFXa.jpg\", \"TB2GeWvfB0kpuFjSsziXXa.oVXa.jpg\", \"TB2JE.agtBopuFjSZPcXXc9EpXa.jpg\", \"TB2LvMHgxxmpuFjSZFNXXXrRXXa.jpg\", \"TB2P2SIbetTMeFjSZFOXXaTiVXa.jpg\"]}', '5', '1', '1');
INSERT INTO `item_comment` VALUES ('13', '22', '2017-03-05 14:07:49', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('14', '22', '2017-03-05 14:07:58', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('15', '22', '2017-03-05 14:07:58', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('16', '22', '2017-03-05 14:07:58', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('17', '22', '2017-03-05 14:07:58', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('18', '22', '2017-03-05 14:07:58', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('19', '22', '2017-03-05 14:08:04', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('20', '22', '2017-03-05 14:08:04', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('21', '22', '2017-03-05 14:08:04', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('22', '22', '2017-03-05 14:08:04', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('23', '22', '2017-03-05 14:08:04', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('24', '22', '2017-03-05 14:08:04', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('25', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('26', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('27', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('28', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('29', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('30', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('31', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('32', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('33', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('34', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('35', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('36', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('37', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('38', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('39', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('40', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('41', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('42', '22', '2017-03-05 14:08:24', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('43', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('44', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('45', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('46', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('47', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('48', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('49', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('50', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('51', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('52', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('53', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('54', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('55', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('56', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('57', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('58', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('59', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('60', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('61', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('62', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('63', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('64', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('65', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('66', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('67', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('68', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('69', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('70', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '0');
INSERT INTO `item_comment` VALUES ('71', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('72', '22', '2017-03-05 14:08:34', '这位用户没有评论，默认好评', null, '5', '2', '1');
INSERT INTO `item_comment` VALUES ('73', '22', '2017-03-05 15:08:34', '这位用户没有评论，默认好评', '{\"base\": \"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/customer-comment/\", \"array\": [\"TB2e4CjfCXlpuFjy0FeXXcJbFXa.jpg\", \"TB2GeWvfB0kpuFjSsziXXa.oVXa.jpg\", \"TB2JE.agtBopuFjSZPcXXc9EpXa.jpg\", \"TB2LvMHgxxmpuFjSZFNXXXrRXXa.jpg\", \"TB2P2SIbetTMeFjSZFOXXaTiVXa.jpg\"]}', '5', '3', '1');
INSERT INTO `item_comment` VALUES ('74', '22', '2017-03-05 15:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('75', '22', '2017-03-05 15:08:34', '这位用户没有评论，默认好评', null, '5', '3', '0');
INSERT INTO `item_comment` VALUES ('76', '22', '2017-03-05 15:08:34', '一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五', '{\"base\": \"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/customer-comment/\", \"array\": [\"TB2NbTceB0kpuFjSsziXXa.oVXa.jpg\", \"TB2ZIT2X5C9MuFjSZFoXXbUzFXa.jpg\"]}', '5', '2', '1');
INSERT INTO `item_comment` VALUES ('77', '22', '2017-03-05 15:08:34', '这位用户没有评论，默认好评', null, '5', '1', '1');
INSERT INTO `item_comment` VALUES ('78', '22', '2017-03-05 15:08:34', '一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五', '{\"base\": \"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/customer-comment/\", \"array\": [\"TB2e4CjfCXlpuFjy0FeXXcJbFXa.jpg\", \"TB2GeWvfB0kpuFjSsziXXa.oVXa.jpg\", \"TB2JE.agtBopuFjSZPcXXc9EpXa.jpg\", \"TB2LvMHgxxmpuFjSZFNXXXrRXXa.jpg\", \"TB2P2SIbetTMeFjSZFOXXaTiVXa.jpg\"]}', '5', '1', '0');

-- ----------------------------
-- Table structure for item_detail_describe
-- ----------------------------
DROP TABLE IF EXISTS `item_detail_describe`;
CREATE TABLE `item_detail_describe` (
  `id` int(11) unsigned NOT NULL,
  `describe_json_array` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item_detail_describe
-- ----------------------------
INSERT INTO `item_detail_describe` VALUES ('22', '[\"生产许可证编号: QS110005010001\", \"产品标准号: GB25190\", \"厂名: 北京光明健能乳业有限公司\", \"厂址: 北京市顺义区林河工业开发区内厂家\", \"联系方式: 4006065500\", \"配料表: 纯牛奶\", \"储藏方法: 常温保存\", \"保质期: 180\", \"食品添加剂: 看包装\", \"净含量: 24x1x250ml\", \"包装方式: 包装\", \"包装种类: 箱\", \"同城服务: 同城24小时物流\", \"送货上门品牌: 光明\", \"系列: 100\", \"商品条形码: 6901209302473\", \"饮品种类: 全脂牛奶\", \"产地: 中国大陆\", \"省份: 上海\"]');

-- ----------------------------
-- Table structure for item_detail_html
-- ----------------------------
DROP TABLE IF EXISTS `item_detail_html`;
CREATE TABLE `item_detail_html` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` varchar(10) DEFAULT NULL COMMENT '版本或描述',
  `div` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item_detail_html
-- ----------------------------
INSERT INTO `item_detail_html` VALUES ('1', '展示界面1', '<div>商品展示界面，这里注意尽量防止XSS,CSRF注入，后端就不干了，交给前端做</div>');
INSERT INTO `item_detail_html` VALUES ('2', '展示界面2', '<div>你好，先生or小姐</div>');
INSERT INTO `item_detail_html` VALUES ('3', '展示界面2', '<div>展示界面2</div>');
INSERT INTO `item_detail_html` VALUES ('22', '1', '<div><img src=\"//closx-shop.oss-cn-qingdao.aliyuncs.com/images/TB2yzinX_PC11Bjy1zcXXbTrVXa1037316969.jpg\" style=\"width: 100%\"></div>');

-- ----------------------------
-- Table structure for item_selling_info
-- ----------------------------
DROP TABLE IF EXISTS `item_selling_info`;
CREATE TABLE `item_selling_info` (
  `item_id` int(11) unsigned NOT NULL,
  `quantity` int(11) unsigned NOT NULL COMMENT '库存',
  `in_ordering` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '在预定中的',
  `sales` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '销售了多少',
  `comment_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '评论数量',
  `score` double unsigned NOT NULL DEFAULT '0' COMMENT '评分',
  PRIMARY KEY (`item_id`),
  KEY `sales` (`sales`),
  KEY `score` (`score`) USING BTREE,
  CONSTRAINT `item_selling_info_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item_selling_info
-- ----------------------------
INSERT INTO `item_selling_info` VALUES ('1', '10000', '4065', '4012', '3', '4.666666666666667');
INSERT INTO `item_selling_info` VALUES ('2', '1000', '0', '0', '2', '5');
INSERT INTO `item_selling_info` VALUES ('3', '100000', '325', '290', '2', '2.5');
INSERT INTO `item_selling_info` VALUES ('4', '8888', '7', '6', '0', '1');
INSERT INTO `item_selling_info` VALUES ('5', '1232', '34', '31', '0', '2.3');
INSERT INTO `item_selling_info` VALUES ('6', '10000', '343', '333', '0', '2.4');
INSERT INTO `item_selling_info` VALUES ('7', '233', '232', '222', '0', '1.1');
INSERT INTO `item_selling_info` VALUES ('8', '23', '6', '6', '0', '4.8');
INSERT INTO `item_selling_info` VALUES ('9', '95', '21', '21', '0', '4.6');
INSERT INTO `item_selling_info` VALUES ('10', '100', '84', '84', '0', '4.5');
INSERT INTO `item_selling_info` VALUES ('11', '1200', '12', '12', '0', '4.7');
INSERT INTO `item_selling_info` VALUES ('12', '2200', '35', '35', '0', '4.7');
INSERT INTO `item_selling_info` VALUES ('13', '8900', '75', '75', '0', '4.8');
INSERT INTO `item_selling_info` VALUES ('14', '6700', '0', '0', '0', '3.9');
INSERT INTO `item_selling_info` VALUES ('16', '8700', '12', '12', '0', '4.3');
INSERT INTO `item_selling_info` VALUES ('17', '121', '0', '0', '0', '3.9');
INSERT INTO `item_selling_info` VALUES ('18', '489', '78', '78', '0', '4');
INSERT INTO `item_selling_info` VALUES ('19', '9820', '1', '0', '0', '3.1');
INSERT INTO `item_selling_info` VALUES ('20', '90', '23', '21', '0', '3.9');
INSERT INTO `item_selling_info` VALUES ('21', '110', '9', '9', '0', '4.6');
INSERT INTO `item_selling_info` VALUES ('22', '200000', '13625', '12089', '9941', '5');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `message` varchar(100) NOT NULL,
  `type` char(5) NOT NULL DEFAULT '通知',
  `is_read` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `user_id` int(11) unsigned NOT NULL,
  `pri_data` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '您的订单已发货', '发货信息', '0', '1', null);
INSERT INTO `message` VALUES ('2', '您的订单已发货', '发货信息', '0', '1', null);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) unsigned NOT NULL,
  `is_finished` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `is_paied` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `createTime` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '1', '2016-12-20 15:51:10', '1', '0', '0');
INSERT INTO `order` VALUES ('14', '1', '2016-12-21 14:53:00', '1', '0', '0');
INSERT INTO `order` VALUES ('15', '1', '2016-12-21 14:55:15', '1', '0', '0');
INSERT INTO `order` VALUES ('16', '1', '2016-12-21 14:56:40', '1', '0', '0');
INSERT INTO `order` VALUES ('17', '1', '2016-12-21 15:03:56', '1', '0', '0');
INSERT INTO `order` VALUES ('20', '1', '2016-12-21 15:17:21', '1', '0', '0');
INSERT INTO `order` VALUES ('21', '1', '2016-12-21 15:32:03', '1', '0', '0');
INSERT INTO `order` VALUES ('22', '1', '2016-12-21 15:33:32', '1', '0', '0');
INSERT INTO `order` VALUES ('23', '1', '2016-12-21 15:36:42', '1', '0', '0');
INSERT INTO `order` VALUES ('24', '1', '2016-12-21 15:56:57', '1', '0', '0');
INSERT INTO `order` VALUES ('25', '1', '2016-12-21 15:57:05', '1', '0', '0');
INSERT INTO `order` VALUES ('26', '1', '2016-12-21 16:02:36', '1', '0', '0');
INSERT INTO `order` VALUES ('27', '1', '2016-12-21 16:05:10', '1', '0', '0');
INSERT INTO `order` VALUES ('28', '1', '2016-12-21 16:08:02', '1', '0', '0');
INSERT INTO `order` VALUES ('29', '1', '2016-12-21 16:09:12', '1', '0', '0');
INSERT INTO `order` VALUES ('30', '1', '2016-12-21 17:49:15', '1', '0', '0');
INSERT INTO `order` VALUES ('31', '1', '2016-12-21 18:10:39', '1', '0', '0');
INSERT INTO `order` VALUES ('32', '1', '2016-12-21 18:15:39', '1', '0', '0');
INSERT INTO `order` VALUES ('33', '1', '2016-12-21 18:16:08', '1', '0', '0');
INSERT INTO `order` VALUES ('34', '1', '2016-12-21 18:16:10', '1', '0', '0');
INSERT INTO `order` VALUES ('35', '1', '2016-12-22 21:21:52', '1', '0', '0');
INSERT INTO `order` VALUES ('36', '1', '2016-12-22 21:26:36', '1', '0', '0');
INSERT INTO `order` VALUES ('37', '1', '2016-12-22 21:31:48', '1', '0', '0');
INSERT INTO `order` VALUES ('38', '1', '2016-12-22 21:34:48', '1', '0', '0');
INSERT INTO `order` VALUES ('39', '1', '2016-12-22 21:38:17', '1', '0', '0');
INSERT INTO `order` VALUES ('40', '1', '2016-12-30 19:08:48', '1', '0', '0');
INSERT INTO `order` VALUES ('41', '1', '2017-01-03 00:32:40', '1', '0', '0');
INSERT INTO `order` VALUES ('42', '1', '2017-01-03 08:37:31', '1', '0', '0');
INSERT INTO `order` VALUES ('43', '1', '2017-01-03 22:17:36', '1', '0', '0');
INSERT INTO `order` VALUES ('44', '1', '2017-01-05 15:20:38', '1', '0', '0');
INSERT INTO `order` VALUES ('45', '1', '2017-01-08 16:50:12', '1', '0', '0');
INSERT INTO `order` VALUES ('46', '1', '2017-02-28 14:43:24', '1', '0', '0');
INSERT INTO `order` VALUES ('47', '1', '2017-03-08 17:17:59', '1', '0', '0');

-- ----------------------------
-- Table structure for order_an_item
-- ----------------------------
DROP TABLE IF EXISTS `order_an_item`;
CREATE TABLE `order_an_item` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` int(11) unsigned NOT NULL,
  `quantity` int(11) unsigned NOT NULL,
  `order_a_shop_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpg01unv7ndyd1hthno7cnn5s` (`item_id`),
  KEY `FKl6sbl7l39mcocpcoo08i78vmv` (`order_a_shop_id`),
  CONSTRAINT `FKl6sbl7l39mcocpcoo08i78vmv` FOREIGN KEY (`order_a_shop_id`) REFERENCES `order_a_shop` (`id`),
  CONSTRAINT `FKpg01unv7ndyd1hthno7cnn5s` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_an_item
-- ----------------------------
INSERT INTO `order_an_item` VALUES ('1', '1', '1', '1');
INSERT INTO `order_an_item` VALUES ('2', '2', '999', '1');
INSERT INTO `order_an_item` VALUES ('3', '4', '1', '2');
INSERT INTO `order_an_item` VALUES ('4', '3', '3', '3');
INSERT INTO `order_an_item` VALUES ('5', '1', '1', '12');
INSERT INTO `order_an_item` VALUES ('6', '2', '999', '12');
INSERT INTO `order_an_item` VALUES ('7', '1', '1', '13');
INSERT INTO `order_an_item` VALUES ('8', '2', '999', '13');
INSERT INTO `order_an_item` VALUES ('9', '1', '1', '14');
INSERT INTO `order_an_item` VALUES ('10', '3', '6', '14');
INSERT INTO `order_an_item` VALUES ('11', '1', '1', '15');
INSERT INTO `order_an_item` VALUES ('12', '3', '6', '15');
INSERT INTO `order_an_item` VALUES ('13', '1', '1', '16');
INSERT INTO `order_an_item` VALUES ('14', '3', '6', '16');
INSERT INTO `order_an_item` VALUES ('15', '1', '1', '17');
INSERT INTO `order_an_item` VALUES ('16', '3', '6', '17');
INSERT INTO `order_an_item` VALUES ('17', '1', '1', '18');
INSERT INTO `order_an_item` VALUES ('18', '3', '3', '18');
INSERT INTO `order_an_item` VALUES ('19', '1', '1', '19');
INSERT INTO `order_an_item` VALUES ('20', '3', '3', '19');
INSERT INTO `order_an_item` VALUES ('21', '1', '1', '20');
INSERT INTO `order_an_item` VALUES ('22', '3', '3', '20');
INSERT INTO `order_an_item` VALUES ('23', '1', '1', '21');
INSERT INTO `order_an_item` VALUES ('24', '3', '3', '21');
INSERT INTO `order_an_item` VALUES ('25', '1', '1', '22');
INSERT INTO `order_an_item` VALUES ('26', '3', '3', '22');
INSERT INTO `order_an_item` VALUES ('27', '1', '3', '23');
INSERT INTO `order_an_item` VALUES ('28', '3', '3', '23');
INSERT INTO `order_an_item` VALUES ('29', '1', '3', '24');
INSERT INTO `order_an_item` VALUES ('30', '3', '3', '24');
INSERT INTO `order_an_item` VALUES ('31', '1', '4000', '25');
INSERT INTO `order_an_item` VALUES ('32', '3', '3', '25');
INSERT INTO `order_an_item` VALUES ('33', '1', '4000', '26');
INSERT INTO `order_an_item` VALUES ('34', '3', '3', '26');
INSERT INTO `order_an_item` VALUES ('35', '1', '4000', '27');
INSERT INTO `order_an_item` VALUES ('36', '3', '3', '27');
INSERT INTO `order_an_item` VALUES ('37', '1', '4000', '28');
INSERT INTO `order_an_item` VALUES ('38', '3', '3', '28');
INSERT INTO `order_an_item` VALUES ('39', '1', '400', '29');
INSERT INTO `order_an_item` VALUES ('40', '3', '3', '29');
INSERT INTO `order_an_item` VALUES ('41', '1', '1', '30');
INSERT INTO `order_an_item` VALUES ('42', '3', '6', '30');
INSERT INTO `order_an_item` VALUES ('43', '1', '1', '31');
INSERT INTO `order_an_item` VALUES ('44', '3', '6', '31');
INSERT INTO `order_an_item` VALUES ('45', '22', '1', '32');
INSERT INTO `order_an_item` VALUES ('46', '22', '1', '33');
INSERT INTO `order_an_item` VALUES ('47', '1', '1', '34');
INSERT INTO `order_an_item` VALUES ('48', '3', '1', '34');
INSERT INTO `order_an_item` VALUES ('49', '4', '1', '35');
INSERT INTO `order_an_item` VALUES ('50', '1', '1', '36');
INSERT INTO `order_an_item` VALUES ('51', '3', '6', '36');
INSERT INTO `order_an_item` VALUES ('52', '1', '1', '37');
INSERT INTO `order_an_item` VALUES ('53', '3', '6', '37');
INSERT INTO `order_an_item` VALUES ('54', '1', '1', '38');
INSERT INTO `order_an_item` VALUES ('55', '3', '6', '38');
INSERT INTO `order_an_item` VALUES ('56', '22', '3', '39');
INSERT INTO `order_an_item` VALUES ('57', '1', '1', '40');
INSERT INTO `order_an_item` VALUES ('58', '3', '6', '40');

-- ----------------------------
-- Table structure for order_a_shop
-- ----------------------------
DROP TABLE IF EXISTS `order_a_shop`;
CREATE TABLE `order_a_shop` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '启用？',
  `is_deliver` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否发货',
  `is_receive` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否收货',
  `is_evaluate` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否评价',
  `shop_id` int(11) unsigned NOT NULL COMMENT '店铺id 一对一',
  `address_id` int(11) unsigned NOT NULL COMMENT '地址id 一对一',
  `order_id` int(11) unsigned DEFAULT NULL COMMENT '订单id 多对一',
  `discount` double unsigned DEFAULT NULL,
  `memo` varchar(64) DEFAULT NULL COMMENT '给卖家的留言（备忘）',
  `user_id` int(11) unsigned NOT NULL,
  `receive_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb9plmauddpk7fnbp1hm5fetx9` (`address_id`),
  KEY `FK3huaro5bvry3s00w1beroj3dy` (`shop_id`),
  KEY `FK7asb6r8cn6fcq81f458pcftns` (`order_id`),
  CONSTRAINT `FK3huaro5bvry3s00w1beroj3dy` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `FK7asb6r8cn6fcq81f458pcftns` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  CONSTRAINT `FKb9plmauddpk7fnbp1hm5fetx9` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_a_shop
-- ----------------------------
INSERT INTO `order_a_shop` VALUES ('1', '1', '1', '0', '0', '1', '1', '1', '0', '顺丰顺丰顺丰', '1', null);
INSERT INTO `order_a_shop` VALUES ('2', '1', '0', '0', '0', '2', '1', '1', '1', null, '1', null);
INSERT INTO `order_a_shop` VALUES ('3', '1', '1', '0', '0', '1', '2', '1', '100', null, '1', null);
INSERT INTO `order_a_shop` VALUES ('6', '1', '1', '0', '0', '1', '1', '14', null, '顺丰顺丰顺丰', '1', null);
INSERT INTO `order_a_shop` VALUES ('7', '1', '0', '0', '0', '1', '1', '15', null, '顺丰顺丰顺丰', '1', null);
INSERT INTO `order_a_shop` VALUES ('8', '1', '0', '0', '0', '1', '1', '16', null, '顺丰顺丰顺丰', '1', null);
INSERT INTO `order_a_shop` VALUES ('9', '1', '0', '0', '0', '1', '1', '17', null, '顺丰顺丰顺丰', '1', null);
INSERT INTO `order_a_shop` VALUES ('12', '1', '0', '0', '0', '1', '1', '20', null, '顺丰顺丰顺丰', '1', null);
INSERT INTO `order_a_shop` VALUES ('13', '1', '0', '0', '0', '1', '1', '21', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('14', '1', '0', '0', '0', '1', '1', '22', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('15', '1', '0', '0', '0', '1', '1', '23', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('16', '1', '0', '0', '0', '1', '1', '24', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('17', '1', '0', '0', '0', '1', '1', '25', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('18', '1', '0', '0', '0', '1', '2', '26', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('19', '1', '0', '0', '0', '1', '1', '27', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('20', '1', '0', '0', '0', '1', '1', '28', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('21', '1', '0', '0', '0', '1', '1', '29', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('22', '1', '0', '0', '0', '1', '1', '30', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('23', '1', '0', '0', '0', '1', '1', '31', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('24', '1', '0', '0', '0', '1', '1', '32', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('25', '1', '0', '0', '0', '1', '1', '33', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('26', '1', '0', '0', '0', '1', '1', '34', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('27', '1', '0', '0', '0', '1', '1', '35', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('28', '1', '0', '0', '0', '1', '1', '36', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('29', '1', '0', '0', '0', '1', '1', '37', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('30', '1', '0', '0', '0', '1', '1', '38', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('31', '1', '0', '0', '0', '1', '1', '39', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('32', '1', '0', '0', '0', '1', '1', '40', null, null, '1', null);
INSERT INTO `order_a_shop` VALUES ('33', '1', '0', '0', '0', '1', '1', '41', null, null, '1', null);
INSERT INTO `order_a_shop` VALUES ('34', '1', '0', '0', '0', '1', '1', '42', null, null, '1', null);
INSERT INTO `order_a_shop` VALUES ('35', '1', '0', '0', '0', '2', '1', '42', null, null, '1', null);
INSERT INTO `order_a_shop` VALUES ('36', '1', '0', '0', '0', '1', '1', '43', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('37', '1', '0', '0', '0', '1', '1', '44', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('38', '1', '0', '0', '0', '1', '1', '45', null, '给卖家的留言', '1', null);
INSERT INTO `order_a_shop` VALUES ('39', '1', '0', '0', '0', '1', '1', '46', null, null, '1', null);
INSERT INTO `order_a_shop` VALUES ('40', '1', '0', '0', '0', '1', '1', '47', null, '给卖家的留言', '1', null);

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `name` varchar(16) DEFAULT NULL,
  `seller_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', '1', '哇哈哈牛奶旗舰店', '4');
INSERT INTO `shop` VALUES ('2', '1', '猛牛牛奶', '2');
INSERT INTO `shop` VALUES ('3', '1', '牛奶批发', '3');
INSERT INTO `shop` VALUES ('4', '1', '进口牛奶批发中心', '2');

-- ----------------------------
-- Table structure for shop_detail
-- ----------------------------
DROP TABLE IF EXISTS `shop_detail`;
CREATE TABLE `shop_detail` (
  `id` int(11) unsigned NOT NULL,
  `seller_name` varchar(16) NOT NULL,
  `bail` int(11) unsigned NOT NULL COMMENT '缴纳保证金',
  `credit` int(11) unsigned NOT NULL COMMENT '信用等级',
  `description_score` double unsigned NOT NULL,
  `service_score` double unsigned NOT NULL,
  `logistics_score` double unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_detail
-- ----------------------------
INSERT INTO `shop_detail` VALUES ('1', '哇哈啊哈店长', '1000', '2', '5', '4.9', '5');
INSERT INTO `shop_detail` VALUES ('2', '猛牛总裁', '0', '2', '4.8', '4.9', '4.6');
INSERT INTO `shop_detail` VALUES ('3', '批发小老板', '1000', '3', '5', '4.7', '4.5');
INSERT INTO `shop_detail` VALUES ('4', '进口总代理', '10000', '5', '4.6', '4.3', '4.9');

-- ----------------------------
-- Table structure for shop_detail_html_sidebar
-- ----------------------------
DROP TABLE IF EXISTS `shop_detail_html_sidebar`;
CREATE TABLE `shop_detail_html_sidebar` (
  `id` int(11) unsigned NOT NULL,
  `xss_html` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_detail_html_sidebar
-- ----------------------------

-- ----------------------------
-- Table structure for shop_detail_html_topbar
-- ----------------------------
DROP TABLE IF EXISTS `shop_detail_html_topbar`;
CREATE TABLE `shop_detail_html_topbar` (
  `id` int(11) unsigned NOT NULL,
  `xss_html` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_detail_html_topbar
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL,
  `nickname` varchar(16) DEFAULT NULL,
  `vip` tinyint(4) NOT NULL DEFAULT '0',
  `message_num` int(11) unsigned NOT NULL DEFAULT '0',
  `car_num` int(11) unsigned NOT NULL DEFAULT '0',
  `avatar_src` varchar(100) DEFAULT NULL,
  `credit` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Ghosted', '1', '3', '6', null, '800');
INSERT INTO `user` VALUES ('2', '测试用户', '2', '0', '0', '//closx-shop.oss-cn-qingdao.aliyuncs.com/images/user/TBUISJIJJJIJIJASgetAvatar.jpg', '1000');
INSERT INTO `user` VALUES ('3', '测试顾客', '3', '0', '0', null, '30');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `enabled` tinyint(1) unsigned DEFAULT '1',
  `nickname` varchar(16) DEFAULT NULL,
  `username` varchar(16) NOT NULL,
  `password` char(64) NOT NULL DEFAULT '',
  `phone` char(11) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `authority` varchar(32) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '1', 'ghosted', 'ghosted', '49dc52e6bf2abe5ef6e2bb5b0f1ee2d765b922ae6cc8b95d39dc06c21c848f8c', '12345678901', 'i@closx.com', 'USER,SELLER,ADMIN');
INSERT INTO `users` VALUES ('2', '1', '用户', 'test', 'can not login', '12345678902', null, 'USER');
INSERT INTO `users` VALUES ('3', '1', '顾客', 'test2', '49dc52e6bf2abe5ef6e2bb5b0f1ee2d765b922ae6cc8b95d39dc06c21c848f8c', '12345678903', null, 'USER');
INSERT INTO `users` VALUES ('4', '1', '店主', 'dianzhu', '49dc52e6bf2abe5ef6e2bb5b0f1ee2d765b922ae6cc8b95d39dc06c21c848f8c', '13355597778', null, 'SELLER');

-- ----------------------------
-- View structure for authorities
-- ----------------------------
DROP VIEW IF EXISTS `authorities`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `authorities` AS select `users`.`username` AS `username`,`users`.`authority` AS `authority` from `users` ;
DROP TRIGGER IF EXISTS `updateCarNum`;
DELIMITER ;;
CREATE TRIGGER `updateCarNum` AFTER INSERT ON `car` FOR EACH ROW UPDATE user SET car_num = car_num + 1 WHERE id = NEW.user_id LIMIT 1
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `deleteCarNum`;
DELIMITER ;;
CREATE TRIGGER `deleteCarNum` BEFORE DELETE ON `car` FOR EACH ROW UPDATE user SET car_num = car_num - 1 WHERE id = OLD.user_id LIMIT 1
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `updateCommentNumAndScore`;
DELIMITER ;;
CREATE TRIGGER `updateCommentNumAndScore` AFTER INSERT ON `item_comment` FOR EACH ROW UPDATE item_selling_info SET score = (score*comment_num + NEW.score) / (comment_num + 1), comment_num = comment_num + 1 WHERE item_id = NEW.item_id LIMIT 1
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `addMessageNum`;
DELIMITER ;;
CREATE TRIGGER `addMessageNum` AFTER INSERT ON `message` FOR EACH ROW UPDATE user SET message_num = message_num + 1 WHERE id = NEW.user_id LIMIT 1
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `orderAnItem`;
DELIMITER ;;
CREATE TRIGGER `orderAnItem` AFTER INSERT ON `order_an_item` FOR EACH ROW UPDATE item_selling_info SET in_ordering =  in_ordering + NEW.quantity WHERE item_id = NEW.item_id LIMIT 1
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `onUpdate`;
DELIMITER ;;
CREATE TRIGGER `onUpdate` AFTER UPDATE ON `order_a_shop` FOR EACH ROW BEGIN
#发货通知
SET @oldAreDeliver = OLD.is_deliver; # 旧的是否发货标识
SET @areDeliver = NEW.is_deliver; # 新的是否发货标识
IF @areDeliver = true AND @oldAreDeliver = false THEN # 发货了
# 订单对应的用户id
UPDATE user SET message_num = message_num + 1 WHERE id = 1 LIMIT 1;
END IF;

END
;;
DELIMITER ;
