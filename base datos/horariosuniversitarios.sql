-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-06-2015 a las 04:51:49
-- Versión del servidor: 5.5.32
-- Versión de PHP: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `horariosuniversitarios`
--
CREATE DATABASE IF NOT EXISTS `horariosuniversitarios` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `horariosuniversitarios`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bloquehorario`
--

CREATE TABLE IF NOT EXISTS `bloquehorario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imprimir` varchar(45) DEFAULT NULL,
  `hora_inicio` int(11) NOT NULL,
  `hora_fin` int(11) NOT NULL,
  `dia_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_bloqueHorario_hora1` (`hora_inicio`),
  KEY `fk_bloqueHorario_hora2` (`hora_fin`),
  KEY `fk_bloqueHorario_dia1` (`dia_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=181 ;

--
-- Volcado de datos para la tabla `bloquehorario`
--

INSERT INTO `bloquehorario` (`id`, `imprimir`, `hora_inicio`, `hora_fin`, `dia_id`) VALUES
(1, NULL, 1, 1, 1),
(2, NULL, 1, 2, 1),
(3, NULL, 1, 3, 1),
(4, NULL, 2, 2, 1),
(5, NULL, 2, 3, 1),
(6, NULL, 2, 4, 1),
(7, NULL, 3, 3, 1),
(8, NULL, 3, 4, 1),
(9, NULL, 3, 5, 1),
(10, NULL, 4, 4, 1),
(11, NULL, 4, 5, 1),
(12, NULL, 4, 6, 1),
(13, NULL, 5, 5, 1),
(14, NULL, 5, 6, 1),
(15, NULL, 5, 7, 1),
(16, NULL, 6, 6, 1),
(17, NULL, 6, 7, 1),
(18, NULL, 6, 8, 1),
(19, NULL, 7, 7, 1),
(20, NULL, 7, 8, 1),
(21, NULL, 7, 9, 1),
(22, NULL, 8, 8, 1),
(23, NULL, 8, 9, 1),
(24, NULL, 8, 10, 1),
(25, NULL, 9, 9, 1),
(26, NULL, 9, 10, 1),
(27, NULL, 9, 11, 1),
(28, NULL, 10, 10, 1),
(29, NULL, 10, 11, 1),
(30, NULL, 10, 12, 1),
(31, NULL, 11, 11, 1),
(32, NULL, 11, 12, 1),
(33, NULL, 11, 13, 1),
(34, NULL, 12, 12, 1),
(35, NULL, 12, 13, 1),
(36, NULL, 13, 13, 1),
(37, NULL, 1, 1, 2),
(38, NULL, 1, 2, 2),
(39, NULL, 1, 3, 2),
(40, NULL, 2, 2, 2),
(41, NULL, 2, 3, 2),
(42, NULL, 2, 4, 2),
(43, NULL, 3, 3, 2),
(44, NULL, 3, 4, 2),
(45, NULL, 3, 5, 2),
(46, NULL, 4, 4, 2),
(47, NULL, 4, 5, 2),
(48, NULL, 4, 6, 2),
(49, NULL, 5, 5, 2),
(50, NULL, 5, 6, 2),
(51, NULL, 5, 7, 2),
(52, NULL, 6, 6, 2),
(53, NULL, 6, 7, 2),
(54, NULL, 6, 8, 2),
(55, NULL, 7, 7, 2),
(56, NULL, 7, 8, 2),
(57, NULL, 7, 9, 2),
(58, NULL, 8, 8, 2),
(59, NULL, 8, 9, 2),
(60, NULL, 8, 10, 2),
(61, NULL, 9, 9, 2),
(62, NULL, 9, 10, 2),
(63, NULL, 9, 11, 2),
(64, NULL, 10, 10, 2),
(65, NULL, 10, 11, 2),
(66, NULL, 10, 12, 2),
(67, NULL, 11, 11, 2),
(68, NULL, 11, 12, 2),
(69, NULL, 11, 13, 2),
(70, NULL, 12, 12, 2),
(71, NULL, 12, 13, 2),
(72, NULL, 13, 13, 2),
(73, NULL, 1, 1, 3),
(74, NULL, 1, 2, 3),
(75, NULL, 1, 3, 3),
(76, NULL, 2, 2, 3),
(77, NULL, 2, 3, 3),
(78, NULL, 2, 4, 3),
(79, NULL, 3, 3, 3),
(80, NULL, 3, 4, 3),
(81, NULL, 3, 5, 3),
(82, NULL, 4, 4, 3),
(83, NULL, 4, 5, 3),
(84, NULL, 4, 6, 3),
(85, NULL, 5, 5, 3),
(86, NULL, 5, 6, 3),
(87, NULL, 5, 7, 3),
(88, NULL, 6, 6, 3),
(89, NULL, 6, 7, 3),
(90, NULL, 6, 8, 3),
(91, NULL, 7, 7, 3),
(92, NULL, 7, 8, 3),
(93, NULL, 7, 9, 3),
(94, NULL, 8, 8, 3),
(95, NULL, 8, 9, 3),
(96, NULL, 8, 10, 3),
(97, NULL, 9, 9, 3),
(98, NULL, 9, 10, 3),
(99, NULL, 9, 11, 3),
(100, NULL, 10, 10, 3),
(101, NULL, 10, 11, 3),
(102, NULL, 10, 12, 3),
(103, NULL, 11, 11, 3),
(104, NULL, 11, 12, 3),
(105, NULL, 11, 13, 3),
(106, NULL, 12, 12, 3),
(107, NULL, 12, 13, 3),
(108, NULL, 13, 13, 3),
(109, NULL, 1, 1, 4),
(110, NULL, 1, 2, 4),
(111, NULL, 1, 3, 4),
(112, NULL, 2, 2, 4),
(113, NULL, 2, 3, 4),
(114, NULL, 2, 4, 4),
(115, NULL, 3, 3, 4),
(116, NULL, 3, 4, 4),
(117, NULL, 3, 5, 4),
(118, NULL, 4, 4, 4),
(119, NULL, 4, 5, 4),
(120, NULL, 4, 6, 4),
(121, NULL, 5, 5, 4),
(122, NULL, 5, 6, 4),
(123, NULL, 5, 7, 4),
(124, NULL, 6, 6, 4),
(125, NULL, 6, 7, 4),
(126, NULL, 6, 8, 4),
(127, NULL, 7, 7, 4),
(128, NULL, 7, 8, 4),
(129, NULL, 7, 9, 4),
(130, NULL, 8, 8, 4),
(131, NULL, 8, 9, 4),
(132, NULL, 8, 10, 4),
(133, NULL, 9, 9, 4),
(134, NULL, 9, 10, 4),
(135, NULL, 9, 11, 4),
(136, NULL, 10, 10, 4),
(137, NULL, 10, 11, 4),
(138, NULL, 10, 12, 4),
(139, NULL, 11, 11, 4),
(140, NULL, 11, 12, 4),
(141, NULL, 11, 13, 4),
(142, NULL, 12, 12, 4),
(143, NULL, 12, 13, 4),
(144, NULL, 13, 13, 4),
(145, NULL, 1, 1, 5),
(146, NULL, 1, 2, 5),
(147, NULL, 1, 3, 5),
(148, NULL, 2, 2, 5),
(149, NULL, 2, 3, 5),
(150, NULL, 2, 4, 5),
(151, NULL, 3, 3, 5),
(152, NULL, 3, 4, 5),
(153, NULL, 3, 5, 5),
(154, NULL, 4, 4, 5),
(155, NULL, 4, 5, 5),
(156, NULL, 4, 6, 5),
(157, NULL, 5, 5, 5),
(158, NULL, 5, 6, 5),
(159, NULL, 5, 7, 5),
(160, NULL, 6, 6, 5),
(161, NULL, 6, 7, 5),
(162, NULL, 6, 8, 5),
(163, NULL, 7, 7, 5),
(164, NULL, 7, 8, 5),
(165, NULL, 7, 9, 5),
(166, NULL, 8, 8, 5),
(167, NULL, 8, 9, 5),
(168, NULL, 8, 10, 5),
(169, NULL, 9, 9, 5),
(170, NULL, 9, 10, 5),
(171, NULL, 9, 11, 5),
(172, NULL, 10, 10, 5),
(173, NULL, 10, 11, 5),
(174, NULL, 10, 12, 5),
(175, NULL, 11, 11, 5),
(176, NULL, 11, 12, 5),
(177, NULL, 11, 13, 5),
(178, NULL, 12, 12, 5),
(179, NULL, 12, 13, 5),
(180, NULL, 13, 13, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrera`
--

CREATE TABLE IF NOT EXISTS `carrera` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `carrera`
--

INSERT INTO `carrera` (`id`, `nombre`) VALUES
(1, 'Informatica'),
(2, 'Mecanica'),
(3, 'Electronica'),
(4, 'Industrial'),
(5, 'Ambiental'),
(6, 'Produccion Animal'),
(7, 'Agronomica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `choqueshorarios`
--

CREATE TABLE IF NOT EXISTS `choqueshorarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `choque` int(11) DEFAULT NULL,
  `bloqueHorario_id` int(11) DEFAULT NULL,
  `bloqueHorario_id1` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_choquesHorarios_bloqueHorario1` (`bloqueHorario_id`),
  KEY `fk_choquesHorarios_bloqueHorario2` (`bloqueHorario_id1`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

CREATE TABLE IF NOT EXISTS `departamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `departamento`
--

INSERT INTO `departamento` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Informatica', 'Este es el departamento de informatica'),
(3, 'Industrial', NULL),
(4, 'Matematica', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dia`
--

CREATE TABLE IF NOT EXISTS `dia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dia_numero` int(11) DEFAULT NULL,
  `dia_letra` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `dia`
--

INSERT INTO `dia` (`id`, `dia_numero`, `dia_letra`) VALUES
(1, 1, 'Lunes'),
(2, 2, 'Martes'),
(3, 3, 'Miercoles'),
(4, 4, 'Jueves'),
(5, 5, 'Viernes');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hora`
--

CREATE TABLE IF NOT EXISTS `hora` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Volcado de datos para la tabla `hora`
--

INSERT INTO `hora` (`id`, `nombre`) VALUES
(1, '07'),
(2, '08'),
(3, '09'),
(4, '10'),
(5, '11'),
(6, '12'),
(7, '13'),
(8, '14'),
(9, '15'),
(10, '16'),
(11, '17'),
(12, '18'),
(13, '19');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE IF NOT EXISTS `horario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(128) DEFAULT NULL,
  `choques` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `horario`
--

INSERT INTO `horario` (`id`, `nombre`, `descripcion`, `choques`) VALUES
(1, 'prueba', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horariodetalle`
--

CREATE TABLE IF NOT EXISTS `horariodetalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `salon_id` int(11) NOT NULL,
  `profesor_id` int(11) NOT NULL,
  `horario_id` int(11) NOT NULL,
  `bloqueHorario_id` int(11) NOT NULL,
  `seccion_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_horarioDetalle_salon1` (`salon_id`),
  KEY `fk_horarioDetalle_profesor1` (`profesor_id`),
  KEY `fk_horarioDetalle_horario1` (`horario_id`),
  KEY `fk_horarioDetalle_bloqueHorario1` (`bloqueHorario_id`),
  KEY `fk_horarioDetalle_seccion1` (`seccion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horasxmateria`
--

CREATE TABLE IF NOT EXISTS `horasxmateria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unidades_credito` int(11) NOT NULL,
  `horas_semana` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `horasxmateria`
--

INSERT INTO `horasxmateria` (`id`, `unidades_credito`, `horas_semana`) VALUES
(1, 1, 2),
(2, 2, 3),
(3, 3, 5),
(4, 4, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia`
--

CREATE TABLE IF NOT EXISTS `materia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `codigo` varchar(45) NOT NULL,
  `unidades_credito` int(11) NOT NULL,
  `departamento_id` int(11) DEFAULT NULL,
  `requiere_laboratorio` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_materia_horasXmateria1` (`unidades_credito`),
  KEY `fk_materia_departamento1` (`departamento_id`),
  KEY `fk_materia_tipoMateria1` (`requiere_laboratorio`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Volcado de datos para la tabla `materia`
--

INSERT INTO `materia` (`id`, `nombre`, `codigo`, `unidades_credito`, `departamento_id`, `requiere_laboratorio`) VALUES
(25, 'Matematica I', 'CO787444', 1, 1, 1),
(26, 'Fisica I', 'CO214474', 2, 1, 1),
(27, 'Matematica III', 'CO78747', 4, 3, 1),
(28, 'Multimedia', 'Co000012', 3, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nodisponibilidadprofesor`
--

CREATE TABLE IF NOT EXISTS `nodisponibilidadprofesor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profesor_id` int(11) DEFAULT NULL,
  `bloqueHorario_id` int(11) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_noDisponibilidadProfesor_profesor1` (`profesor_id`),
  KEY `fk_noDisponibilidadProfesor_bloqueHorario1` (`bloqueHorario_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=171 ;

--
-- Volcado de datos para la tabla `nodisponibilidadprofesor`
--

INSERT INTO `nodisponibilidadprofesor` (`id`, `profesor_id`, `bloqueHorario_id`, `title`, `start`, `end`) VALUES
(161, 1, NULL, 'No disponible', '2015-05-20 16:00:00', '2015-05-20 16:00:00'),
(165, 1, NULL, 'No disponible', '2015-05-19 11:00:00', '2015-05-19 14:00:00'),
(169, 1, NULL, 'No disponible', '2015-05-18 10:00:00', '2015-05-18 11:00:00'),
(170, 1, NULL, 'No disponible', '2015-05-26 09:00:00', '2015-05-26 13:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parametrosgenetico`
--

CREATE TABLE IF NOT EXISTS `parametrosgenetico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(128) NOT NULL,
  `generacion_inicio` int(11) NOT NULL,
  `generacion_fin` int(11) NOT NULL,
  `hijos_generacion` int(11) NOT NULL,
  `tiempo_maximo` int(11) NOT NULL,
  `mejora_aceptable` float NOT NULL,
  `porcentaje_lista_elite` float NOT NULL,
  `probabilidad_cruce` float NOT NULL,
  `probabilidad_mutacion` float NOT NULL,
  `probabilidad_tabu` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Volcado de datos para la tabla `parametrosgenetico`
--

INSERT INTO `parametrosgenetico` (`id`, `nombre`, `descripcion`, `generacion_inicio`, `generacion_fin`, `hijos_generacion`, `tiempo_maximo`, `mejora_aceptable`, `porcentaje_lista_elite`, `probabilidad_cruce`, `probabilidad_mutacion`, `probabilidad_tabu`) VALUES
(1, 'Optimo', 'El algoritmo tomara un tiempo considerable en arrojar un respuesta, pero dicha sera mas trabajada', 100, 150, 10, 2323, 32, 0.3, 0.1, 0.1, 0.1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parametrostabu`
--

CREATE TABLE IF NOT EXISTS `parametrostabu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(128) NOT NULL,
  `generacion_inicio` int(11) NOT NULL,
  `generacion_fin` int(11) NOT NULL,
  `hijos_generacion` int(11) NOT NULL,
  `tiempo_maximo` int(11) NOT NULL,
  `mejora_aceptable` float NOT NULL,
  `porcentaje_lista_elite` float NOT NULL,
  `tope_lista_tabu` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Volcado de datos para la tabla `parametrostabu`
--

INSERT INTO `parametrostabu` (`id`, `nombre`, `descripcion`, `generacion_inicio`, `generacion_fin`, `hijos_generacion`, `tiempo_maximo`, `mejora_aceptable`, `porcentaje_lista_elite`, `tope_lista_tabu`) VALUES
(15, 'Genesis', 'me gusta demasiado, mas que la libertad.... mentira!', 1, 1, 1, 33, 3, 3, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE IF NOT EXISTS `profesor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `cedula` varchar(45) DEFAULT NULL,
  `departamento_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_profesor_departamento` (`departamento_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`id`, `nombre`, `cedula`, `departamento_id`) VALUES
(1, 'Isaac Ramirez', '19521141-4', 1),
(2, 'Genesis Gelves :D', '255615641', 3),
(12, 'David Cardenas', '14785963', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salon`
--

CREATE TABLE IF NOT EXISTS `salon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(128) DEFAULT NULL,
  `tipoSalon_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_salon_tipoSalon1` (`tipoSalon_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `salon`
--

INSERT INTO `salon` (`id`, `nombre`, `descripcion`, `tipoSalon_id`) VALUES
(1, 'C01', 'en el c', 1),
(2, 'C04', 'en el C', 1),
(3, '19A', 'en el A', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seccion`
--

CREATE TABLE IF NOT EXISTS `seccion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(11) NOT NULL,
  `materia_id` int(11) NOT NULL,
  `carrera_id` int(11) DEFAULT NULL,
  `es_grupo` int(11) DEFAULT '0',
  `semestre` int(11) NOT NULL,
  `profesor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_seccion_materia1` (`materia_id`),
  KEY `fk_seccion_carrera1` (`carrera_id`),
  KEY `fk_seccion_profesor1` (`profesor_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Volcado de datos para la tabla `seccion`
--

INSERT INTO `seccion` (`id`, `numero`, `materia_id`, `carrera_id`, `es_grupo`, `semestre`, `profesor_id`) VALUES
(9, 3, 25, 2, 1, 2, 2),
(12, 5, 25, 4, 1, 5, 2),
(13, 4, 26, 2, 1, 2, 12),
(14, 7, 26, 4, 0, 1, 2),
(15, 1, 26, 1, 1, 2, 1),
(16, 10, 27, 4, 1, 8, 1),
(17, 11, 27, 3, 0, 4, 1),
(19, 2, 28, 2, 0, 6, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipomateria`
--

CREATE TABLE IF NOT EXISTS `tipomateria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `tipomateria`
--

INSERT INTO `tipomateria` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Teorica', 'No'),
(2, 'Teorico/practica', 'Si');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiposalon`
--

CREATE TABLE IF NOT EXISTS `tiposalon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `tiposalon`
--

INSERT INTO `tiposalon` (`id`, `nombre`) VALUES
(1, 'Laboratorio'),
(2, 'Aula');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bloquehorario`
--
ALTER TABLE `bloquehorario`
  ADD CONSTRAINT `fk_bloqueHorario_dia1` FOREIGN KEY (`dia_id`) REFERENCES `dia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_bloqueHorario_hora1` FOREIGN KEY (`hora_inicio`) REFERENCES `hora` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_bloqueHorario_hora2` FOREIGN KEY (`hora_fin`) REFERENCES `hora` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `choqueshorarios`
--
ALTER TABLE `choqueshorarios`
  ADD CONSTRAINT `fk_choquesHorarios_bloqueHorario1` FOREIGN KEY (`bloqueHorario_id`) REFERENCES `bloquehorario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_choquesHorarios_bloqueHorario2` FOREIGN KEY (`bloqueHorario_id1`) REFERENCES `bloquehorario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `horariodetalle`
--
ALTER TABLE `horariodetalle`
  ADD CONSTRAINT `fk_horarioDetalle_bloqueHorario1` FOREIGN KEY (`bloqueHorario_id`) REFERENCES `bloquehorario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_horarioDetalle_horario1` FOREIGN KEY (`horario_id`) REFERENCES `horario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_horarioDetalle_profesor1` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_horarioDetalle_salon1` FOREIGN KEY (`salon_id`) REFERENCES `salon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_horarioDetalle_seccion1` FOREIGN KEY (`seccion_id`) REFERENCES `seccion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `materia`
--
ALTER TABLE `materia`
  ADD CONSTRAINT `fk_materia_departamento1` FOREIGN KEY (`departamento_id`) REFERENCES `departamento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_materia_horasXmateria1` FOREIGN KEY (`unidades_credito`) REFERENCES `horasxmateria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_materia_tipoMateria1` FOREIGN KEY (`requiere_laboratorio`) REFERENCES `tipomateria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `nodisponibilidadprofesor`
--
ALTER TABLE `nodisponibilidadprofesor`
  ADD CONSTRAINT `fk_noDisponibilidadProfesor_bloqueHorario1` FOREIGN KEY (`bloqueHorario_id`) REFERENCES `bloquehorario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_noDisponibilidadProfesor_profesor1` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD CONSTRAINT `fk_profesor_departamento` FOREIGN KEY (`departamento_id`) REFERENCES `departamento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `salon`
--
ALTER TABLE `salon`
  ADD CONSTRAINT `fk_salon_tipoSalon1` FOREIGN KEY (`tipoSalon_id`) REFERENCES `tiposalon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `seccion`
--
ALTER TABLE `seccion`
  ADD CONSTRAINT `fk_seccion_carrera1` FOREIGN KEY (`carrera_id`) REFERENCES `carrera` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_seccion_materia1` FOREIGN KEY (`materia_id`) REFERENCES `materia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_seccion_profesor1` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
