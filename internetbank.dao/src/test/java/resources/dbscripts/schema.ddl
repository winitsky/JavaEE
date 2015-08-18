CREATE TABLE  `accounts` (
  `account` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `balance` int(11) NOT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `archive` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `operation_id` int(11) DEFAULT NULL,
  `sum` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  `operations` (
  `id` int(11) NOT NULL ,
  `name` varchar(255) DEFAULT NULL,
  `account` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


CREATE TABLE  `role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE  `users` (
  `id` int(11) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
