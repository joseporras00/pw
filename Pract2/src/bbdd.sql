-- phpMyAdmin SQL Dump
-- version 2.7.0-pl2
-- http://www.phpmyadmin.net
-- 
-- Servidor: oraclepr.uco.es
-- Versión del servidor: 5.1.73
-- Versión de PHP: 5.3.3
-- 
-- Base de datos: `i82poroj`
-- 

-- --------------------------------------------------------

-- 
-- Estructura de tablas
-- 

DROP TABLE IF EXISTS `Usuarios`;
CREATE TABLE IF NOT EXISTS `Usuarios` (
  `Nombre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Apellidos` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `F. Nacimiento` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Tags` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Email`),
  FOREIGN KEY (Tags) REFERENCES Tags (Id)
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
  PRIMARY KEY (`Id`),
  FOREIGN KEY (Propietario) REFERENCES Usuarios (Email),
  FOREIGN KEY (Destinatarios) REFERENCES Usuarios (Email),
  FOREIGN KEY (Tag) REFERENCES Tags (Id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `Tags`;
CREATE TABLE IF NOT EXISTS `Tags` (
  `Id` int(11) NOT NULL
  `Tipo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 
-- Volcar la base de datos para la tabla `Tags`
-- 

INSERT INTO `Tags` VALUES (1, 'Actualidad');
INSERT INTO `Tags` VALUES (2, 'Alimentacion');
INSERT INTO `Tags` VALUES (3, 'Deportes');
INSERT INTO `Tags` VALUES (4, 'Diversion');
INSERT INTO `Tags` VALUES (5, 'Moda');
INSERT INTO `Tags` VALUES (6, 'Musica');
INSERT INTO `Tags` VALUES (7, 'Tecnologias');

INSERT INTO `Usuarios` VALUES ('Jose Manuel','Porras Rosales','i82poroj', '09/04/2000','4 1');
INSERT INTO `Usuarios` VALUES ('Juan,Valverde Santiago','i82vasaj', '12/05/2000','6 7');

