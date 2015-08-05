-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.22-log - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных internet_banking
CREATE DATABASE IF NOT EXISTS `internet_banking` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `internet_banking`;


-- Дамп структуры для таблица internet_banking.accounts
CREATE TABLE IF NOT EXISTS `accounts` (
  `account` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `balance` int(11) NOT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы internet_banking.accounts: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` (`account`, `user_id`, `balance`) VALUES
	(123456789, 1, 200000),
	(321654987, 3, 3000000),
	(987654321, 2, 5000000);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;


-- Дамп структуры для таблица internet_banking.archive
CREATE TABLE IF NOT EXISTS `archive` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `operation_id` int(11) DEFAULT NULL,
  `sum` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы internet_banking.archive: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `archive` DISABLE KEYS */;
INSERT INTO `archive` (`id`, `user_id`, `operation_id`, `sum`, `date`) VALUES
	(1, 2, 2, 1000, '2015-08-04'),
	(2, 2, 3, 2000, '2015-08-04'),
	(3, 3, 3, 1000, '2015-08-04'),
	(4, 1, 3, 1000, '2015-08-04'),
	(5, 1, 3, 1000, '2015-08-04'),
	(6, 1, 3, 1000, '2015-08-04'),
	(7, 1, 3, 1000, '2015-08-04');
/*!40000 ALTER TABLE `archive` ENABLE KEYS */;


-- Дамп структуры для таблица internet_banking.operations
CREATE TABLE IF NOT EXISTS `operations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `account` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы internet_banking.operations: ~5 rows (приблизительно)
/*!40000 ALTER TABLE `operations` DISABLE KEYS */;
INSERT INTO `operations` (`id`, `name`, `account`, `type`) VALUES
	(1, 'Оплата за телефон', 123, 2),
	(2, 'Оплата за воду', 12300123, 2),
	(3, 'Оплата за интернет', 82300128, 2),
	(4, 'Международный перевод', 77777777, 1),
	(5, 'Пополнения счета', 78965412, 2);
/*!40000 ALTER TABLE `operations` ENABLE KEYS */;


-- Дамп структуры для таблица internet_banking.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы internet_banking.role: ~5 rows (приблизительно)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `user_id`, `role`) VALUES
	(1, 1, 2),
	(2, 2, 2),
	(3, 3, 1),
	(4, 5, 2),
	(5, 6, 1);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- Дамп структуры для таблица internet_banking.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы internet_banking.users: ~5 rows (приблизительно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `login`, `password`, `surname`, `name`) VALUES
	(1, 'petrov@mail.ru', '123', 'Петр', 'Петров'),
	(2, 'ivanov@mail.ru', '123', 'Иван', 'Иванов'),
	(3, 'sidirov@tut.by', '123', 'Дмитрий', 'Сидоров'),
	(5, 'sikorsky@mail.ru', '123', 'Сикорский', 'Степан'),
	(6, 'sikorsky@mail.ru', '123', 'Сикорский', 'Степан');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
