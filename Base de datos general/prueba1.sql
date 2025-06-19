-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-10-2024 a las 02:52:48
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `prueba1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `Categoria_ID` int(11) NOT NULL,
  `Peso` varchar(15) DEFAULT NULL,
  `Ancho` varchar(15) DEFAULT NULL,
  `Largo` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`Categoria_ID`, `Peso`, `Ancho`, `Largo`) VALUES
(1, '0.1 - 1 kg', '10 - 30 cm', '10 - 50 cm'),
(4, '1 - 5 kg', '30 - 60 cm', '50 - 100 cm'),
(5, '5 - 20 kg', '60 - 100 cm', '100 - 150 cm'),
(6, '20 - 50 kg', '100 - 150 cm', '150 - 200 cm'),
(7, '50 - ... kg', '150 - ... cm', '200 - ... cm');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `Cliente_ID` int(11) NOT NULL,
  `NombreCliente` varchar(100) DEFAULT NULL,
  `Direccion` varchar(50) DEFAULT NULL,
  `Correo` varchar(50) DEFAULT NULL,
  `Codigo_Ubigeo` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`Cliente_ID`, `NombreCliente`, `Direccion`, `Correo`, `Codigo_Ubigeo`) VALUES
(4, 'Lola', 'Calle', 'asdads@gmail.com', NULL),
(5, 'Jose Anthonio', 'cale asdaf', 'gsdga@gmail.com', NULL),
(6, 'Matias', 'Calle uasjfba', 'ASDAFS@hotmail.com', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datos_personales`
--

CREATE TABLE `datos_personales` (
  `Dni_ID` varchar(7) NOT NULL,
  `Nombre` varchar(35) DEFAULT NULL,
  `Apellido` varchar(35) DEFAULT NULL,
  `Direccion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `datos_personales`
--

INSERT INTO `datos_personales` (`Dni_ID`, `Nombre`, `Apellido`, `Direccion`) VALUES
('1234567', 'Jose', 'Perez', 'Jiron'),
('7658469', 'Fernanda', 'Gomez', 'Calle 456');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresas`
--

CREATE TABLE `empresas` (
  `Empresa_ID` int(11) NOT NULL,
  `Nombre_Empresa` varchar(65) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empresas`
--

INSERT INTO `empresas` (`Empresa_ID`, `Nombre_Empresa`) VALUES
(1, 'The Coca-Cola Company'),
(2, 'Disney');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encomiendas`
--

CREATE TABLE `encomiendas` (
  `Encomienda_ID` int(11) NOT NULL,
  `Categoria_ID` int(11) DEFAULT NULL,
  `Empresa_ID` int(11) DEFAULT NULL,
  `Destino` varchar(50) DEFAULT NULL,
  `Estado_ID` int(11) DEFAULT NULL,
  `Fecha` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `encomiendas`
--

INSERT INTO `encomiendas` (`Encomienda_ID`, `Categoria_ID`, `Empresa_ID`, `Destino`, `Estado_ID`, `Fecha`) VALUES
(6, 4, 1, 'Ancón', 5, '2024-10-22 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `envio`
--

CREATE TABLE `envio` (
  `Envio_ID` int(11) NOT NULL,
  `Encomienda_ID` int(11) DEFAULT NULL,
  `Fecha_Envio` datetime DEFAULT NULL,
  `Fecha_Entrega` datetime DEFAULT NULL,
  `Estado_ID` int(11) DEFAULT NULL,
  `Repartidor_ID` int(11) DEFAULT NULL,
  `Cliente_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `envio`
--

INSERT INTO `envio` (`Envio_ID`, `Encomienda_ID`, `Fecha_Envio`, `Fecha_Entrega`, `Estado_ID`, `Repartidor_ID`, `Cliente_ID`) VALUES
(1, 6, '2024-10-25 00:00:00', '2024-11-06 00:00:00', 4, 9, 4),
(2, 6, '2024-10-24 00:00:00', '2024-10-27 00:00:00', 5, 9, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados`
--

CREATE TABLE `estados` (
  `Estado_ID` int(11) NOT NULL,
  `Nombre_Estado` varchar(50) DEFAULT NULL,
  `Descripcion_Encomienda` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estados`
--

INSERT INTO `estados` (`Estado_ID`, `Nombre_Estado`, `Descripcion_Encomienda`) VALUES
(1, 'En almacén', 'La encomienda se encuentra almacenada, en espera de su motorizado'),
(2, 'Asignado a Motorizado', 'Se le esta haciendo entrega de la encomienda al Motorizado para que la lleve al destino.'),
(3, 'En Ruta', 'El Motorizado ya tiene la encomienda y esta en camino.'),
(4, 'Entregado', 'El Motorizado hizo entrega de su encomienda sin inconvenientes.'),
(5, 'Camino al almacén', 'La encomienda esta siendo trasladada al almacén para comenzar con el procedimiento.'),
(6, 'Inconveniente en el envio', 'La encomienda ha tenido un inconveniente en el proceso de envió, por lo que tardará un poco más de lo esperado.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `Pago_ID` int(11) NOT NULL,
  `Cliente_ID` int(11) DEFAULT NULL,
  `Precio` decimal(10,2) DEFAULT NULL,
  `Tipo_Pago` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pagos`
--

INSERT INTO `pagos` (`Pago_ID`, `Cliente_ID`, `Precio`, `Tipo_Pago`) VALUES
(4, 4, 39.65, 'Yape'),
(5, 5, 57.04, 'POS'),
(6, 5, 98.90, 'Transferencia'),
(7, 4, 101.20, 'POS'),
(9, 6, 60.18, 'Yape');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `repartidores`
--

CREATE TABLE `repartidores` (
  `Repartidor_ID` int(11) NOT NULL,
  `Dni_ID` varchar(7) DEFAULT NULL,
  `Telefono` varchar(10) DEFAULT NULL,
  `Vehiculo_Placa` varchar(10) DEFAULT NULL,
  `Codigo_Ubigeo` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `repartidores`
--

INSERT INTO `repartidores` (`Repartidor_ID`, `Dni_ID`, `Telefono`, `Vehiculo_Placa`, `Codigo_Ubigeo`) VALUES
(9, '1234567', '465879456', 'RS6-45A', '150104');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `Rol_ID` int(11) NOT NULL,
  `Nom_rol` varchar(50) DEFAULT NULL,
  `Descripcion` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`Rol_ID`, `Nom_rol`, `Descripcion`) VALUES
(1, 'Administrador', 'Se encarga de supervisar el programa en general'),
(2, 'Coordinador', 'Se encarga de gestionar los paquetes y envios.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ubigeo`
--

CREATE TABLE `ubigeo` (
  `UbiGEO_ID` int(11) NOT NULL,
  `Departamento` varchar(50) NOT NULL,
  `Provincia` varchar(50) NOT NULL,
  `Distrito` varchar(50) NOT NULL,
  `Codigo_Ubigeo` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ubigeo`
--

INSERT INTO `ubigeo` (`UbiGEO_ID`, `Departamento`, `Provincia`, `Distrito`, `Codigo_Ubigeo`) VALUES
(1, 'Lima', 'Lima', 'Cercado de Lima', '150101'),
(2, 'Lima', 'Lima', 'Ancón', '150102'),
(3, 'Lima', 'Lima', 'Ate', '150103'),
(4, 'Lima', 'Lima', 'Barranco', '150104'),
(5, 'Lima', 'Lima', 'Breña', '150105'),
(6, 'Lima', 'Lima', 'Carabayllo', '150106'),
(7, 'Lima', 'Lima', 'Chaclacayo', '150107'),
(8, 'Lima', 'Lima', 'Chorrillos', '150108'),
(9, 'Lima', 'Lima', 'Cieneguilla', '150109'),
(10, 'Lima', 'Lima', 'Comas', '150110'),
(11, 'Lima', 'Lima', 'El Agustino', '150111'),
(12, 'Lima', 'Lima', 'Independencia', '150112'),
(13, 'Lima', 'Lima', 'Jesús María', '150113'),
(14, 'Lima', 'Lima', 'La Molina', '150114'),
(15, 'Lima', 'Lima', 'La Victoria', '150115'),
(16, 'Lima', 'Lima', 'Lince', '150116'),
(17, 'Lima', 'Lima', 'Los Olivos', '150117'),
(18, 'Lima', 'Lima', 'Lurigancho', '150118'),
(19, 'Lima', 'Lima', 'Lurín', '150119'),
(20, 'Lima', 'Lima', 'Magdalena del Mar', '150120'),
(21, 'Lima', 'Lima', 'Miraflores', '150121'),
(22, 'Lima', 'Lima', 'Pachacamac', '150122'),
(23, 'Lima', 'Lima', 'Pucusana', '150123'),
(24, 'Lima', 'Lima', 'Pueblo Libre', '150124'),
(25, 'Lima', 'Lima', 'Puente Piedra', '150125'),
(26, 'Lima', 'Lima', 'Punta Hermosa', '150126'),
(27, 'Lima', 'Lima', 'Punta Negra', '150127'),
(28, 'Lima', 'Lima', 'Rímac', '150128'),
(29, 'Lima', 'Lima', 'San Bartolo', '150129'),
(30, 'Lima', 'Lima', 'San Borja', '150130'),
(31, 'Lima', 'Lima', 'San Isidro', '150131'),
(32, 'Lima', 'Lima', 'San Juan de Lurigancho', '150132'),
(33, 'Lima', 'Lima', 'San Juan de Miraflores', '150133'),
(34, 'Lima', 'Lima', 'San Luis', '150134'),
(35, 'Lima', 'Lima', 'San Martín de Porres', '150135'),
(36, 'Lima', 'Lima', 'San Miguel', '150136'),
(37, 'Lima', 'Lima', 'Santa Anita', '150137'),
(38, 'Lima', 'Lima', 'Santa María del Mar', '150138'),
(39, 'Lima', 'Lima', 'Santa Rosa', '150139'),
(40, 'Lima', 'Lima', 'Santiago de Surco', '150140'),
(41, 'Lima', 'Lima', 'Surquillo', '150141'),
(42, 'Lima', 'Lima', 'Villa El Salvador', '150142'),
(43, 'Lima', 'Lima', 'Villa María del Triunfo', '150143'),
(44, 'Lima', 'Barranca', 'Barranca', '150201'),
(45, 'Lima', 'Barranca', 'Paramonga', '150202'),
(46, 'Lima', 'Barranca', 'Pativilca', '150203'),
(47, 'Lima', 'Barranca', 'Supe', '150204'),
(48, 'Lima', 'Barranca', 'Supe Puerto', '150205'),
(49, 'Lima', 'Cajatambo', 'Cajatambo', '150301'),
(50, 'Lima', 'Cajatambo', 'Copa', '150302'),
(51, 'Lima', 'Cajatambo', 'Gorgor', '150303'),
(52, 'Lima', 'Cajatambo', 'Huancapon', '150304'),
(53, 'Lima', 'Cajatambo', 'Manas', '150305'),
(54, 'Lima', 'Canta', 'Canta', '150401'),
(55, 'Lima', 'Canta', 'Arahuay', '150402'),
(56, 'Lima', 'Canta', 'Huamantanga', '150403'),
(57, 'Lima', 'Canta', 'Huaros', '150404'),
(58, 'Lima', 'Canta', 'Lachaqui', '150405'),
(59, 'Lima', 'Canta', 'San Buenaventura', '150406'),
(60, 'Lima', 'Canta', 'Santa Rosa de Quives', '150407'),
(61, 'Lima', 'Cañete', 'San Vicente de Cañete', '150501'),
(62, 'Lima', 'Cañete', 'Asia', '150502'),
(63, 'Lima', 'Cañete', 'Calango', '150503'),
(64, 'Lima', 'Cañete', 'Cerro Azul', '150504'),
(65, 'Lima', 'Cañete', 'Chilca', '150505'),
(66, 'Lima', 'Cañete', 'Coayllo', '150506'),
(67, 'Lima', 'Cañete', 'Imperial', '150507'),
(68, 'Lima', 'Cañete', 'Lunahuana', '150508'),
(69, 'Lima', 'Cañete', 'Mala', '150509'),
(70, 'Lima', 'Cañete', 'Nuevo Imperial', '150510'),
(71, 'Lima', 'Cañete', 'Pacaran', '150511'),
(72, 'Lima', 'Cañete', 'Quilmana', '150512'),
(73, 'Lima', 'Cañete', 'San Antonio', '150513'),
(74, 'Lima', 'Cañete', 'San Luis', '150514'),
(75, 'Lima', 'Huaral', 'Huaral', '150601'),
(76, 'Lima', 'Huaral', 'Atavillos Alto', '150602'),
(77, 'Lima', 'Huaral', 'Atavillos Bajo', '150603'),
(78, 'Lima', 'Huaral', 'Aucallama', '150604'),
(79, 'Lima', 'Huaral', 'Chancay', '150605'),
(80, 'Lima', 'Huaral', 'Ihuari', '150606'),
(81, 'Lima', 'Huaral', 'Lampian', '150607'),
(82, 'Lima', 'Huaral', 'Pacaraos', '150608'),
(83, 'Lima', 'Huaral', 'San Miguel de Acos', '150609'),
(84, 'Lima', 'Huaral', 'Santa Cruz de Andamarca', '150610'),
(85, 'Lima', 'Huaral', 'Sumbilca', '150611'),
(86, 'Lima', 'Huaral', 'Veintisiete de Noviembre', '150612'),
(87, 'Lima', 'Huarochirí', 'Matucana', '150701'),
(88, 'Lima', 'Huarochirí', 'Antioquía', '150702'),
(89, 'Lima', 'Huarochirí', 'Callahuanca', '150703'),
(90, 'Lima', 'Huarochirí', 'Carampoma', '150704'),
(91, 'Lima', 'Huarochirí', 'Chicla', '150705'),
(92, 'Lima', 'Huarochirí', 'Cuenca', '150706'),
(93, 'Lima', 'Huarochirí', 'Huachupampa', '150707'),
(94, 'Lima', 'Huarochirí', 'Huanza', '150708'),
(95, 'Lima', 'Huarochirí', 'Huarochirí', '150709'),
(96, 'Lima', 'Huarochirí', 'Lahuaytambo', '150710'),
(97, 'Lima', 'Huarochirí', 'Langa', '150711'),
(98, 'Lima', 'Huarochirí', 'Laraos', '150712'),
(99, 'Lima', 'Huarochirí', 'Mariatana', '150713'),
(100, 'Lima', 'Huarochirí', 'Ricardo Palma', '150714'),
(101, 'Lima', 'Huarochirí', 'San Andrés de Tupicocha', '150715'),
(102, 'Lima', 'Huarochirí', 'San Antonio de Chaclla', '150716'),
(103, 'Lima', 'Huarochirí', 'San Bartolomé', '150717'),
(104, 'Lima', 'Huarochirí', 'San Damian', '150718'),
(105, 'Lima', 'Huarochirí', 'San Juan de Iris', '150719'),
(106, 'Lima', 'Huarochirí', 'San Juan de Tantaranche', '150720'),
(107, 'Lima', 'Huarochirí', 'San Lorenzo de Quinti', '150721'),
(108, 'Lima', 'Huarochirí', 'San Mateo', '150722'),
(109, 'Lima', 'Huarochirí', 'San Mateo de Otao', '150723'),
(110, 'Lima', 'Huarochirí', 'San Pedro de Casta', '150724'),
(111, 'Lima', 'Huarochirí', 'San Pedro de Huancayre', '150725'),
(112, 'Lima', 'Huarochirí', 'Sangallaya', '150726'),
(113, 'Lima', 'Huarochirí', 'Santa Cruz de Cocachacra', '150727'),
(114, 'Lima', 'Huarochirí', 'Santa Eulalia', '150728'),
(115, 'Lima', 'Huarochirí', 'Santiago de Anchucaya', '150729'),
(116, 'Lima', 'Huarochirí', 'Santiago de Tuna', '150730'),
(117, 'Lima', 'Huarochirí', 'Santo Domingo de Los Olleros', '150731'),
(118, 'Lima', 'Huarochirí', 'Surco', '150732'),
(119, 'Lima', 'Huaura', 'Huacho', '150801'),
(120, 'Lima', 'Huaura', 'Ambar', '150802'),
(121, 'Lima', 'Huaura', 'Caleta de Carquin', '150803'),
(122, 'Lima', 'Huaura', 'Checras', '150804'),
(123, 'Lima', 'Huaura', 'Hualmay', '150805'),
(124, 'Lima', 'Huaura', 'Huaura', '150806'),
(125, 'Lima', 'Huaura', 'Leoncio Prado', '150807'),
(126, 'Lima', 'Huaura', 'Paccho', '150808'),
(127, 'Lima', 'Huaura', 'Santa Leonor', '150809'),
(128, 'Lima', 'Huaura', 'Santa María', '150810'),
(129, 'Lima', 'Huaura', 'Sayan', '150811'),
(130, 'Lima', 'Huaura', 'Vegueta', '150812'),
(131, 'Lima', 'Oyón', 'Oyón', '150901'),
(132, 'Lima', 'Oyón', 'Andajes', '150902'),
(133, 'Lima', 'Oyón', 'Caujul', '150903'),
(134, 'Lima', 'Oyón', 'Cochamarca', '150904'),
(135, 'Lima', 'Oyón', 'Navan', '150905'),
(136, 'Lima', 'Oyón', 'Pachangara', '150906'),
(137, 'Lima', 'Yauyos', 'Yauyos', '151001'),
(138, 'Lima', 'Yauyos', 'Alis', '151002'),
(139, 'Lima', 'Yauyos', 'Allauca', '151003'),
(140, 'Lima', 'Yauyos', 'Ayauca', '151004'),
(141, 'Lima', 'Yauyos', 'Ayaviri', '151005'),
(142, 'Lima', 'Yauyos', 'Azángaro', '151006'),
(143, 'Lima', 'Yauyos', 'Cacra', '151007'),
(144, 'Lima', 'Yauyos', 'Carania', '151008'),
(145, 'Lima', 'Yauyos', 'Catahuasi', '151009'),
(146, 'Lima', 'Yauyos', 'Chocos', '151010'),
(147, 'Lima', 'Yauyos', 'Cochas', '151011'),
(148, 'Lima', 'Yauyos', 'Colonia', '151012'),
(149, 'Lima', 'Yauyos', 'Hongos', '151013'),
(150, 'Lima', 'Yauyos', 'Huampara', '151014'),
(151, 'Lima', 'Yauyos', 'Huancaya', '151015'),
(152, 'Lima', 'Yauyos', 'Huangascar', '151016'),
(153, 'Lima', 'Yauyos', 'Huantan', '151017'),
(154, 'Lima', 'Yauyos', 'Huañec', '151018'),
(155, 'Lima', 'Yauyos', 'Laraos', '151019'),
(156, 'Lima', 'Yauyos', 'Lincha', '151020'),
(157, 'Lima', 'Yauyos', 'Madean', '151021'),
(158, 'Lima', 'Yauyos', 'Miraflores', '151022'),
(159, 'Lima', 'Yauyos', 'Omas', '151023'),
(160, 'Lima', 'Yauyos', 'Putinza', '151024'),
(161, 'Lima', 'Yauyos', 'Quinches', '151025'),
(162, 'Lima', 'Yauyos', 'Quinocay', '151026'),
(163, 'Lima', 'Yauyos', 'San Joaquín', '151027'),
(164, 'Lima', 'Yauyos', 'San Pedro de Pilas', '151028'),
(165, 'Lima', 'Yauyos', 'Tanta', '151029'),
(166, 'Lima', 'Yauyos', 'Tauripampa', '151030'),
(167, 'Lima', 'Yauyos', 'Tomas', '151031'),
(168, 'Lima', 'Yauyos', 'Tupe', '151032'),
(169, 'Lima', 'Yauyos', 'Viñac', '151033'),
(170, 'Lima', 'Yauyos', 'Vitis', '151034');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `Usuario_ID` int(11) NOT NULL,
  `Dni_ID` varchar(7) DEFAULT NULL,
  `Rol_ID` int(11) DEFAULT NULL,
  `Telefono` varchar(50) DEFAULT NULL,
  `Contraseña` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`Usuario_ID`, `Dni_ID`, `Rol_ID`, `Telefono`, `Contraseña`) VALUES
(1, '7658469', 1, '919465876', '123456');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`Categoria_ID`),
  ADD UNIQUE KEY `Categoria_ID` (`Categoria_ID`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`Cliente_ID`),
  ADD KEY `FK_Cliente_UbiGEO` (`Codigo_Ubigeo`);

--
-- Indices de la tabla `datos_personales`
--
ALTER TABLE `datos_personales`
  ADD PRIMARY KEY (`Dni_ID`);

--
-- Indices de la tabla `empresas`
--
ALTER TABLE `empresas`
  ADD PRIMARY KEY (`Empresa_ID`);

--
-- Indices de la tabla `encomiendas`
--
ALTER TABLE `encomiendas`
  ADD PRIMARY KEY (`Encomienda_ID`),
  ADD KEY `FK_Encomienda_Estado` (`Estado_ID`),
  ADD KEY `FK_Encomienda_Categoria` (`Categoria_ID`),
  ADD KEY `FK_Encomienda_Empresa` (`Encomienda_ID`),
  ADD KEY `fk_empresa` (`Empresa_ID`);

--
-- Indices de la tabla `envio`
--
ALTER TABLE `envio`
  ADD PRIMARY KEY (`Envio_ID`),
  ADD KEY `FK_Envio_Encomienda` (`Encomienda_ID`),
  ADD KEY `FK_Envio_Estado` (`Estado_ID`),
  ADD KEY `FK_Envio_Repartidor` (`Repartidor_ID`),
  ADD KEY `FK_Envio_Cliente` (`Cliente_ID`);

--
-- Indices de la tabla `estados`
--
ALTER TABLE `estados`
  ADD PRIMARY KEY (`Estado_ID`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`Pago_ID`),
  ADD KEY `FK_Pago_Cliente` (`Cliente_ID`);

--
-- Indices de la tabla `repartidores`
--
ALTER TABLE `repartidores`
  ADD PRIMARY KEY (`Repartidor_ID`),
  ADD KEY `FK_Repartidor_Datos_personales` (`Dni_ID`),
  ADD KEY `FK_Repartidor_UbiGEO` (`Codigo_Ubigeo`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`Rol_ID`);

--
-- Indices de la tabla `ubigeo`
--
ALTER TABLE `ubigeo`
  ADD PRIMARY KEY (`UbiGEO_ID`),
  ADD UNIQUE KEY `Codigo_Ubigeo` (`Codigo_Ubigeo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`Usuario_ID`),
  ADD KEY `FK_Usuario_Datos_personales` (`Dni_ID`),
  ADD KEY `FK_Usuario_Rol` (`Rol_ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `Categoria_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `Cliente_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `empresas`
--
ALTER TABLE `empresas`
  MODIFY `Empresa_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `encomiendas`
--
ALTER TABLE `encomiendas`
  MODIFY `Encomienda_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `envio`
--
ALTER TABLE `envio`
  MODIFY `Envio_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `Pago_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `repartidores`
--
ALTER TABLE `repartidores`
  MODIFY `Repartidor_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `ubigeo`
--
ALTER TABLE `ubigeo`
  MODIFY `UbiGEO_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=171;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `Usuario_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `FK_Cliente_UbiGEO` FOREIGN KEY (`Codigo_Ubigeo`) REFERENCES `ubigeo` (`Codigo_Ubigeo`);

--
-- Filtros para la tabla `encomiendas`
--
ALTER TABLE `encomiendas`
  ADD CONSTRAINT `FK_Encomienda_Categoria` FOREIGN KEY (`Categoria_ID`) REFERENCES `categoria` (`Categoria_ID`),
  ADD CONSTRAINT `FK_Encomienda_Estado` FOREIGN KEY (`Estado_ID`) REFERENCES `estados` (`Estado_ID`),
  ADD CONSTRAINT `fk_empresa` FOREIGN KEY (`Empresa_ID`) REFERENCES `empresas` (`Empresa_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `envio`
--
ALTER TABLE `envio`
  ADD CONSTRAINT `FK_Envio_Cliente` FOREIGN KEY (`Cliente_ID`) REFERENCES `clientes` (`Cliente_ID`),
  ADD CONSTRAINT `FK_Envio_Encomienda` FOREIGN KEY (`Encomienda_ID`) REFERENCES `encomiendas` (`Encomienda_ID`),
  ADD CONSTRAINT `FK_Envio_Estado` FOREIGN KEY (`Estado_ID`) REFERENCES `estados` (`Estado_ID`),
  ADD CONSTRAINT `FK_Envio_Repartidor` FOREIGN KEY (`Repartidor_ID`) REFERENCES `repartidores` (`Repartidor_ID`);

--
-- Filtros para la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD CONSTRAINT `FK_Pago_Cliente` FOREIGN KEY (`Cliente_ID`) REFERENCES `clientes` (`Cliente_ID`);

--
-- Filtros para la tabla `repartidores`
--
ALTER TABLE `repartidores`
  ADD CONSTRAINT `FK_Repartidor_Datos_personales` FOREIGN KEY (`Dni_ID`) REFERENCES `datos_personales` (`Dni_ID`),
  ADD CONSTRAINT `FK_Repartidor_UbiGEO` FOREIGN KEY (`Codigo_Ubigeo`) REFERENCES `ubigeo` (`Codigo_Ubigeo`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK_Usuario_Datos_personales` FOREIGN KEY (`Dni_ID`) REFERENCES `datos_personales` (`Dni_ID`),
  ADD CONSTRAINT `FK_Usuario_Rol` FOREIGN KEY (`Rol_ID`) REFERENCES `rol` (`Rol_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
