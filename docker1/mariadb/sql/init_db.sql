SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS `desarrollo`;
CREATE DATABASE `desarrollo` ;
USE `desarrollo`;

DROP TABLE IF EXISTS `departamento`;
CREATE TABLE `departamento` (
  `id` varchar(36) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `jefeActual` varchar(36) NOT NULL,
  `presupuesto` decimal (20) NOT NULL,
  `presupuestoAnual` decimal (20) NOT NULL,

  PRIMARY KEY (`id`),
  FOREIGN KEY (`jefeActual`) REFERENCES programador(`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla de Departamentos';

INSERT INTO `departamento` (`id`, `nombre`, `jefeActual`,`presupuesto`,`presupuestoAnual`) VALUES
('5318aec6-e474-4478-8882-0495657e4218', 'Finanzas', '53ece7ae-3ed0-49c0-b50d-0db04f50d367', 250000.50, 145000),
('e167f3d0-d333-4276-801d-50cfd29179e4', 'Desarrollo Web', '070f4608-0af7-4920-8e22-4ee3759517b9', 450000.75,  375000),
('7c0ea8ff-5da2-4991-8495-74814fad9634', 'Recursos humanos', 'be30c85d-324c-4c79-bad8-96f220fd1355', 125000, 75000),
('77e07771-35dc-4052-8d15-6f4622a7251f', 'Aplicaciones Moviles', 'db53e2ff-6ab3-4830-ba3e-5ccd09d5650e', 500500, 425000);


DROP TABLE IF EXISTS `proyecto`;
CREATE TABLE `proyecto` (
  `id` varchar(36) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `jefeProyecto` varchar(36) NOT NULL,
  `presupuesto` decimal NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date,
  
  PRIMARY KEY (`nombre`),
   FOREIGN KEY (`jefeProyecto`) REFERENCES `programador` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla de proyectos';

INSERT INTO `proyecto` (`id`,`nombre`, `jefeProyecto`, `presupuesto`, `fechaInicio`, `fechaFin`) VALUES
('0e04d847-444d-4d39-9f0a-024a7191c193','Declaración de la renta', '7f784746-5819-gr43-ace7-978bcd3a9be1',	29000 ,'2020-10-01',	'2020-10-01'),
('45d64a82-7ebf-40ab-8278-fa3c3813c5ed','Balance económico',   '7f784746-5819-gr43-ace7-978bcd3a9be1',	30000,'2019-10-01',	'2021-11-01'),
('3b923a39-9f94-4978-b415-17e3bbac617d','Estudio de mercado', '7f784746-5819-gr43-ace7-978bcd3a9be1',	 28000, '2021-08-01',	'2021-11-01'),
('6374ad58-ac34-4331-8ff1-a33f2f047107','Oportunidades nicho', '7f784746-5819-gr43-ace7-978bcd3a9be1', 27000  , '2021-09-02',	null),
('2ce6a8fb-d9cc-4c5e-94a7-f5739edaf1e6','Presupuestos 2022', '7f784746-5819-gr43-ace7-978bcd3a9be1', 31000, '2021-11-01',	null),
('327f9d8b-e42b-4998-8522-b77282c39ddd','Web Santander', '503e1433-1f98-449c-b602-72ffde821e62',	  125000, '2021-10-02',	'2021-11-01'),
('e85905a3-d518-4f4d-8af7-b8392427e304','Wordpress Prisa', '503e1433-1f98-449c-b602-72ffde821e62',	 130000, '2021-10-02',	null),
('98452ba4-3442-49a1-b204-d09b97972706','Reestructuracion Google', '503e1433-1f98-449c-b602-72ffde821e62', 120000, '2021-10-02',	null),
('794b8cb2-3093-416d-87db-4d09ca5e5a68','Jornadas de acogida de nuevos trabajadores', '638ab226-b150-4e48-90fa-7190b9f47da1',	38000,  '2021-10-02',	'2020-10-01'),
('80121180-9b3f-46b9-9e78-012dfe455a78','Reestructuración de personal', '638ab226-b150-4e48-90fa-7190b9f47da1',	37000, '2021-10-02','2020-10-01'),
('65521399-1fc0-4085-bc18-f0f43e211508','App Restaurantes', 'af5ee0c4-eff0-40f0-9eff-e588a51504d7',	  85000 ,  '2021-10-02',	'2020-10-01'),
('c17a5b25-ea70-4c8a-9b84-2d5d78e1cb37','Servicio NH', 'af5ee0c4-eff0-40f0-9eff-e588a51504d7', 90000 , '2021-10-02',	'2020-10-01'),
('dbd80140-0035-4e1e-9e79-05dab9186dce','Transportes Ministerio', 'af5ee0c4-eff0-40f0-9eff-e588a51504d7',	80000, '2021-10-02',	'2020-10-01'),
('50f4f6e5-f543-4d8d-a6bb-f7d1320f79a8','App EMT', 'af5ee0c4-eff0-40f0-9eff-e588a51504d7', 84000,  '2021-10-02',	null),
('e5a62f7a-5fe1-45a8-a389-d07411ae76b8','Organización datos DB', 'af5ee0c4-eff0-40f0-9eff-e588a51504d7',	 86000, '2021-10-02',	null);

DROP TABLE IF EXISTS `programador`;
CREATE TABLE `programador` (
 `id` varchar(36) NOT NULL,
 `nombre` varchar(60) NOT NULL,
 `fechaAlta` date NOT NULL,
 `departamento` varchar(36) NOT NULL,
 `salario` decimal NOT NULL,
 `password` varchar(255) NOT NULL,

 PRIMARY KEY (`id`),
 FOREIGN KEY (`departamento`) REFERENCES `departamento` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla de programadores';

INSERT INTO `programador` (`id`,`nombre`,`fechaAlta`,`departamento`,`salario`,`password`) VALUES
('af5ee0c4-eff0-40f0-9eff-e588a51504d7', 'Mario', '2001-01-01','77e07771-35dc-4052-8d15-6f4622a7251f',2500, 'kugbfjhdzbgf'),
('638ab226-b150-4e48-90fa-7190b9f47da1', 'Andrea', '2002-02-02','7c0ea8ff-5da2-4991-8495-74814fad9634',2500, 'sduat7683r'),
('503e1433-1f98-449c-b602-72ffde821e62', 'Javi', '2003-03-03','e167f3d0-d333-4276-801d-50cfd29179e4',3000, '287ytbdsghfs'),
('7f784746-5819-gr43-ace7-978bcd3a9be1', 'Dani', '2004-04-04','5318aec6-e474-4478-8882-0495657e4218', 1750, 'fgt768a3'),

('7f784746-5819-b5e2-ace7-978bcd3a9be1', 'Currito1', '2004-04-04','5318aec6-e474-4478-8882-0495657e4218', 1000, 'fgt74564a3'),
('7f784746-5819-b543-ace7-978bcd3a9be1', 'Currito2', '2004-04-04','e167f3d0-d333-4276-801d-50cfd29179e4', 1000, 'fgt7fhgca3'),
('7f784746-5819-b7tf-ace7-978bcd3a9be1', 'Currito3', '2004-04-04','7c0ea8ff-5da2-4991-8495-74814fad9634', 1000, 'fgtaq3'),
('7f784746-5819-8f4d-ace7-978bcd3a9be1', 'Currito4', '2004-04-04','77e07771-35dc-4052-8d15-6f4622a7251f', 1000, 'fgtdfrta3'),

('53ece7ae-3ed0-49c0-b50d-0db04f50d367', 'Jefazo1', '2004-04-04','5318aec6-e474-4478-8882-0495657e4218', 3500, 'esrthgs'),
('070f4608-0af7-4920-8e22-4ee3759517b9', 'Jefazo2', '2004-04-04','e167f3d0-d333-4276-801d-50cfd29179e4', 3500, '65wsts'),
('be30c85d-324c-4c79-bad8-96f220fd1355', 'Jefazo3', '2004-04-04','7c0ea8ff-5da2-4991-8495-74814fad9634', 3500, 'daf876bt45a'),
('db53e2ff-6ab3-4830-ba3e-5ccd09d5650e', 'Jefazo4', '2004-04-04','77e07771-35dc-4052-8d15-6f4622a7251f', 3500, '4asebhst');


DROP TABLE IF EXISTS `tecnologia`;
CREATE TABLE `tecnologia` (
  `id` varchar(36) NOT NULL,
 `nombre` varchar(60) NOT NULL,

 PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla de tecnologías';

INSERT INTO `tecnologia` (`id`, `nombre`) VALUES
('8a388767-ddc2-42be-8dff-39bfb9a18e6a','kotlin'),
('1738e8c6-99eb-477d-9ac8-422d465c3c66','github'),
('642d04b0-d155-4af1-93c8-859915809d77','csharp'),
('d6565144-392a-4958-869f-e92f0e6045e2','gitkraken'),
('327c6580-a0c0-43ed-8f3a-753e5378f75b','javascript'),
('92a28f44-b3a4-44d7-ba77-581113631779','python'),
('a84b4021-df74-4900-8d97-97110969877f','html'),
('07c5606c-8627-4e20-b6fa-9d0815d927e0','css'),
('2956959f-b116-46a8-bd59-968e5a9a9b43','wordpress');



DROP TABLE IF EXISTS `programadores_proyectos`;
CREATE TABLE `programadores_proyectos`
(
    `id` varchar(36)  NOT NULL,
    `proyectoid`  varchar(36) NOT NULL ,
    `programadorid` varchar(36) NOT NULL,
    PRIMARY KEY (`id`)

)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla de programadores_proyectos';

INSERT INTO `programadores_proyectos` (`id`, `proyectoid`,`programadorid`) VALUES
('d6ecce95-776d-4615-adcb-8f197fcfce8b', '7f784746-5819-b5e2-ace7-978bcd3a9be1','6374ad58-ac34-4331-8ff1-a33f2f047107'),
('edaebb82-b4e4-46b5-bf70-7aabc0b62891', '7f784746-5819-b5e2-ace7-978bcd3a9be1','2ce6a8fb-d9cc-4c5e-94a7-f5739edaf1e6'),
('a3f78dc8-739f-4d38-87d0-914ece3c12ea', '7f784746-5819-b543-ace7-978bcd3a9be1','327f9d8b-e42b-4998-8522-b77282c39ddd'),
('d6c28478-ed1c-4157-a1c5-b8511bb2c020', '7f784746-5819-b543-ace7-978bcd3a9be1','98452ba4-3442-49a1-b204-d09b97972706'),
('37d570d0-2351-4f50-8ad6-45aeabddf7fe', '7f784746-5819-b7tf-ace7-978bcd3a9be1','794b8cb2-3093-416d-87db-4d09ca5e5a68'),
('68453f87-6fd9-4fdc-a844-87aa98b68474', '7f784746-5819-b7tf-ace7-978bcd3a9be1','80121180-9b3f-46b9-9e78-012dfe455a78'),
('bd21ca17-4c20-4fd8-b6b1-4774c659c707', '7f784746-5819-8f4d-ace7-978bcd3a9be1','50f4f6e5-f543-4d8d-a6bb-f7d1320f79a8'),
('32d8cbbd-6501-45fc-8354-3a5b6a8d25be', '7f784746-5819-8f4d-ace7-978bcd3a9be1','e5a62f7a-5fe1-45a8-a389-d07411ae76b8');