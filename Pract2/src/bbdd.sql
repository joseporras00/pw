-- phpMyAdmin SQL Dump
-- version 2.7.0-pl2
-- http://www.phpmyadmin.net
-- 
-- Servidor: oraclepr.uco.es
-- Versión del servidor: 5.1.73
-- Versión de PHP: 5.3.3
-- 
-- Base de datos: `in1rosaj`
-- 

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `User`
-- 

DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` (
  `Nombre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Apellidos` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `F. Nacimiento` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Tags` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `Anuncios`;
CREATE TABLE IF NOT EXISTS `Anuncios` (  
  `Id` int(11) NOT NULL,
  `Titulo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Propietario` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Destinatarios` int(11) DEFAULT NULL,
  `Cuerpo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Fase` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Tag` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `F. Comienzo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `F. Fin` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `Tags`;
CREATE TABLE IF NOT EXISTS `Tags` (
  `Id` int(11) NOT NULL
  `Tipo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 
-- Volcar la base de datos para la tabla `User`
-- 

INSERT INTO `User` VALUES (1, 'Angola', 'Juan', 15);
INSERT INTO `User` VALUES (2, 'Becerra', 'Francisco', 23);
INSERT INTO `User` VALUES (3, 'Carmona', 'José', 43);
INSERT INTO `User` VALUES (4, 'Davide', 'Giovanni', 31);
INSERT INTO `User` VALUES (5, 'Esteban', 'Pedro', 24);
INSERT INTO `User` VALUES (6, 'Ferrero', 'Manuel', 56);
