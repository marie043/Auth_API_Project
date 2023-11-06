create database `ssafyauth`;

use ssafyauth;

create table `dev`(
	`id` varchar(100),
    `pw` varchar(500),
    primary key(id)
);

create table `dev_crypt`(
	`id` varchar(100),
    `key` varchar(500),
    primary key(id)
);

create table `member`(
	`id` varchar(500),
    `pw` varchar(500),
    `name` varchar(500),
    `email` varchar(100),
    `birthday` date,
    primary key(id)
);

create table `member_crypt`(
	`id` varchar(500),
    `key` varchar(500),
    `salt` varchar(500),
    primary key(id)
);
