SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
);
INSERT INTO `role` VALUES ('1', 'Administrator');
INSERT INTO `role` VALUES ('2', 'User');

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `roleId` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL
);
INSERT INTO `role_permission` VALUES ('1', 'admin:*');
INSERT INTO `role_permission` VALUES ('2', 'user:read');

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
);
INSERT INTO `users` VALUES ('1', 'admin', 'admin');
INSERT INTO `users` VALUES ('2', 'user', '123');

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `userId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL
);
INSERT INTO `users_roles` VALUES ('1', '1', 'Administrator');
INSERT INTO `users_roles` VALUES ('2', '2', 'User');