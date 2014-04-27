-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.13-log - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              8.3.0.4766
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- Дамп данных таблицы ratest.divisions: ~9 rows (приблизительно)
/*!40000 ALTER TABLE `divisions` DISABLE KEYS */;
INSERT IGNORE INTO `divisions` (`id`, `name`) VALUES
	(11, 'Analytics'),
	(12, 'Financial'),
	(10, 'Guard'),
	(4, 'HR'),
	(7, 'IT'),
	(6, 'Management'),
	(5, 'Sales'),
	(9, 'Security'),
	(8, 'Support');
/*!40000 ALTER TABLE `divisions` ENABLE KEYS */;

-- Дамп данных таблицы ratest.employees: ~20 rows (приблизительно)
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT IGNORE INTO `employees` (`id`, `active`, `birthDate`, `firstName`, `lastName`, `salary`, `division_id`) VALUES
	(4, b'1', '1959-12-09', 'Ivan', 'Ivamov', '100.00', 9),
	(5, b'0', '1982-04-12', 'Petr', 'Sidorov', '200.00', 4),
	(6, b'1', '1990-12-01', 'Maria', 'Ivanoca', '250.00', 6),
	(7, b'1', '1974-05-30', 'Sidorova', 'Irina', '230.00', 11),
	(8, b'1', '1981-11-04', 'Alexey', 'Bobrov', '180', 10),
	(9, b'1', '1977-08-13', 'Pistoletov', 'Ilya', '179.00', 9),
	(10, b'1', '1968-04-11', 'Tatyana', 'Nazarova', '220.00', 5),
	(11, b'1', '1961-12-10', 'Slava', 'Egorov', '234.00', 12),
	(12, b'1', '1951-05-09', 'Igor', 'Lapikov', '301.50', 4),
	(13, b'0', '1981-03-11', 'Sivakova', 'Irina', '250', 12),
	(14, b'1', '1985-06-28', 'Anastasiya', 'Kuzmina', '281.00', 8),
	(15, b'0', '1962-12-29', 'Darya', 'Bogatireva', '215.50', 11),
	(16, b'1', '1988-05-14', 'Ivan', 'Gushin', '245.00', 8),
	(17, b'0', '1971-07-30', 'Petr', 'Kozlov', '315.00', 5),
	(18, b'1', '1988-10-25', 'Mariya', 'Petrova', '210.50', 5),
	(19, b'0', '1969-02-28', 'Alexandr', 'Ivicev', '185.00', 10),
	(20, b'1', '1969-01-19', 'Stanislav', 'Nistrob', '305.00', 7),
	(21, b'0', '1982-06-28', 'Anna', 'Noskova', '314.00', 7),
	(22, b'0', '1976-11-18', 'Vitaliy', 'Melekhov', '294.50', 9),
	(23, b'0', '1969-09-02', 'Gleb', 'Egorov', '211.00', 12);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
