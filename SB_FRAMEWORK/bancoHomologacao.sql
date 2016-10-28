-- MySQL dump 10.14  Distrib 5.5.50-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: SBAcessosModel
-- ------------------------------------------------------
-- Server version	5.5.50-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `AcaoDoSistema`
--

DROP TABLE IF EXISTS `AcaoDoSistema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AcaoDoSistema` (
  `tipoAcaoDB` varchar(31) NOT NULL,
  `id` int(11) NOT NULL,
  `cor` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `diretorioBaseArquivos` varchar(255) DEFAULT NULL,
  `iconeAcao` varchar(255) DEFAULT NULL,
  `idDescritivoJira` varchar(255) DEFAULT NULL,
  `nomeAcao` varchar(255) DEFAULT NULL,
  `nomeDominio` varchar(255) DEFAULT NULL,
  `nomeUnico` varchar(255) DEFAULT NULL,
  `precisaPermissao` bit(1) NOT NULL,
  `tipoAcao` varchar(255) DEFAULT NULL,
  `tipoAcaoGenerica` varchar(255) DEFAULT NULL,
  `idMetodo` int(11) DEFAULT NULL,
  `xhtml` varchar(255) DEFAULT NULL,
  `modulo_id` int(11) DEFAULT NULL,
  `acaoPrincipal_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sfvx2rjdm2056ou14murq9wht` (`modulo_id`),
  KEY `FK_f7fug8r6d79c9n5060s08v9rp` (`acaoPrincipal_id`),
  CONSTRAINT `FK_f7fug8r6d79c9n5060s08v9rp` FOREIGN KEY (`acaoPrincipal_id`) REFERENCES `AcaoDoSistema` (`id`),
  CONSTRAINT `FK_sfvx2rjdm2056ou14murq9wht` FOREIGN KEY (`modulo_id`) REFERENCES `ModuloAcaoSistema` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AcaoDoSistema`
--

LOCK TABLES `AcaoDoSistema` WRITE;
/*!40000 ALTER TABLE `AcaoDoSistema` DISABLE KEYS */;
/*!40000 ALTER TABLE `AcaoDoSistema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AssuntoFaleConosco`
--

DROP TABLE IF EXISTS `AssuntoFaleConosco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AssuntoFaleConosco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `assunto` varchar(200) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `dataAlteracao` date DEFAULT NULL,
  `dataInclusao` date DEFAULT NULL,
  `destinatario` varchar(100) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `remetente` varchar(200) NOT NULL,
  `tempoResposta` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AssuntoFaleConosco`
--

LOCK TABLES `AssuntoFaleConosco` WRITE;
/*!40000 ALTER TABLE `AssuntoFaleConosco` DISABLE KEYS */;
/*!40000 ALTER TABLE `AssuntoFaleConosco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FaleConosco`
--

DROP TABLE IF EXISTS `FaleConosco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FaleConosco` (
  `tipoFaleConosco` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `dataAlteracao` date DEFAULT NULL,
  `dataInclusao` date DEFAULT NULL,
  `dataResposta` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `emailDestinatario` varchar(255) DEFAULT NULL,
  `mensagem` varchar(255) DEFAULT NULL,
  `numeroCampanha` int(11) NOT NULL,
  `numeroPedido` int(11) NOT NULL,
  `remetente` varchar(255) DEFAULT NULL,
  `assunto_id` int(11) DEFAULT NULL,
  `usuarioInsercao_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rwx4457m5dwhbm0rrcoffo2hp` (`assunto_id`),
  KEY `FK_o72j51pvlaksbd6o0mgb5qbvs` (`usuarioInsercao_id`),
  CONSTRAINT `FK_o72j51pvlaksbd6o0mgb5qbvs` FOREIGN KEY (`usuarioInsercao_id`) REFERENCES `UsuarioSB` (`id`),
  CONSTRAINT `FK_rwx4457m5dwhbm0rrcoffo2hp` FOREIGN KEY (`assunto_id`) REFERENCES `AssuntoFaleConosco` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FaleConosco`
--

LOCK TABLES `FaleConosco` WRITE;
/*!40000 ALTER TABLE `FaleConosco` DISABLE KEYS */;
/*!40000 ALTER TABLE `FaleConosco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GrupoUsuarioSB`
--

DROP TABLE IF EXISTS `GrupoUsuarioSB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GrupoUsuarioSB` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `XhtmlPaginaInicial` varchar(255) DEFAULT NULL,
  `ativo` bit(1) NOT NULL,
  `dataHoraAlteracao` datetime DEFAULT NULL,
  `dataHoraCriacao` date DEFAULT NULL,
  `dataHoraInsersao` datetime DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `tipoGrupoNativo` bit(1) NOT NULL,
  `usuarioAlteracao_id` int(11) DEFAULT NULL,
  `usuarioInsercao_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3wv40cn2xjarnckhmw50xibgh` (`nome`),
  KEY `FK_qptpj5kond937eh6i524au2ga` (`usuarioAlteracao_id`),
  KEY `FK_p2jlf62aeujlv8nfd75ny23ab` (`usuarioInsercao_id`),
  CONSTRAINT `FK_p2jlf62aeujlv8nfd75ny23ab` FOREIGN KEY (`usuarioInsercao_id`) REFERENCES `UsuarioSB` (`id`),
  CONSTRAINT `FK_qptpj5kond937eh6i524au2ga` FOREIGN KEY (`usuarioAlteracao_id`) REFERENCES `UsuarioSB` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GrupoUsuarioSB`
--

LOCK TABLES `GrupoUsuarioSB` WRITE;
/*!40000 ALTER TABLE `GrupoUsuarioSB` DISABLE KEYS */;
INSERT INTO `GrupoUsuarioSB` VALUES (1,'/site/homeAdministrador.xhtml','',NULL,NULL,NULL,NULL,'GRUPO_USUARIOS','\0',NULL,NULL),(2,'/site/homeAdministrador.xhtml','',NULL,'2016-10-27',NULL,NULL,'GRUPO_USUARIO_AVANCADO','\0',NULL,NULL);
/*!40000 ALTER TABLE `GrupoUsuarioSB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ips`
--

DROP TABLE IF EXISTS `Ips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ips` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `dataAlteracao` date DEFAULT NULL,
  `dns` varchar(255) DEFAULT NULL,
  `finalFaixa` varchar(15) NOT NULL,
  `inicialFaixa` varchar(15) NOT NULL,
  `tipo_id` int(11) DEFAULT NULL,
  `usuarioAlteracao_id` int(11) DEFAULT NULL,
  `usuarioInsercao_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1074a7ooid3slxgsd3kl3y0ye` (`tipo_id`),
  KEY `FK_957n3ouukan1rm7xd9bebpy3t` (`usuarioAlteracao_id`),
  KEY `FK_r3m6gytb2ha2oomronuuaui0i` (`usuarioInsercao_id`),
  CONSTRAINT `FK_r3m6gytb2ha2oomronuuaui0i` FOREIGN KEY (`usuarioInsercao_id`) REFERENCES `UsuarioSB` (`id`),
  CONSTRAINT `FK_1074a7ooid3slxgsd3kl3y0ye` FOREIGN KEY (`tipo_id`) REFERENCES `TipoIp` (`id`),
  CONSTRAINT `FK_957n3ouukan1rm7xd9bebpy3t` FOREIGN KEY (`usuarioAlteracao_id`) REFERENCES `UsuarioSB` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ips`
--

LOCK TABLES `Ips` WRITE;
/*!40000 ALTER TABLE `Ips` DISABLE KEYS */;
/*!40000 ALTER TABLE `Ips` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ModuloAcaoSistema`
--

DROP TABLE IF EXISTS `ModuloAcaoSistema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ModuloAcaoSistema` (
  `id` int(11) NOT NULL,
  `dataHoraCriacao` date DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `iconeDaClasse` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `umModuloNativo` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ModuloAcaoSistema`
--

LOCK TABLES `ModuloAcaoSistema` WRITE;
/*!40000 ALTER TABLE `ModuloAcaoSistema` DISABLE KEYS */;
/*!40000 ALTER TABLE `ModuloAcaoSistema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Negado_Grupos`
--

DROP TABLE IF EXISTS `Negado_Grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Negado_Grupos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acesso_id` int(11) DEFAULT NULL,
  `grupo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_np9687k63gue5roqluqopxuob` (`acesso_id`),
  KEY `FK_nmw508gvi87sx9vh5ygfdl79y` (`grupo_id`),
  CONSTRAINT `FK_nmw508gvi87sx9vh5ygfdl79y` FOREIGN KEY (`grupo_id`) REFERENCES `GrupoUsuarioSB` (`id`),
  CONSTRAINT `FK_np9687k63gue5roqluqopxuob` FOREIGN KEY (`acesso_id`) REFERENCES `PermissaoSB` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Negado_Grupos`
--

LOCK TABLES `Negado_Grupos` WRITE;
/*!40000 ALTER TABLE `Negado_Grupos` DISABLE KEYS */;
/*!40000 ALTER TABLE `Negado_Grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Negado_Usuarios`
--

DROP TABLE IF EXISTS `Negado_Usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Negado_Usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acesso_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_39gufe3ixex80jpg9lshm4y1v` (`usuario_id`,`acesso_id`),
  KEY `FK_n8n1dok7tdvmlwd3jskwwbr8u` (`acesso_id`),
  CONSTRAINT `FK_b4u2dubc251o6gcavfa5k7brk` FOREIGN KEY (`usuario_id`) REFERENCES `UsuarioSB` (`id`),
  CONSTRAINT `FK_n8n1dok7tdvmlwd3jskwwbr8u` FOREIGN KEY (`acesso_id`) REFERENCES `PermissaoSB` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Negado_Usuarios`
--

LOCK TABLES `Negado_Usuarios` WRITE;
/*!40000 ALTER TABLE `Negado_Usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `Negado_Usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PermissaoSB`
--

DROP TABLE IF EXISTS `PermissaoSB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PermissaoSB` (
  `id` int(11) NOT NULL,
  `nomeAcesso` varchar(255) DEFAULT NULL,
  `tipoAutenticacao` int(11) DEFAULT NULL,
  `acaoDoSistema_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7cebweiiue480i3annvtgusi0` (`acaoDoSistema_id`),
  CONSTRAINT `FK_7cebweiiue480i3annvtgusi0` FOREIGN KEY (`acaoDoSistema_id`) REFERENCES `AcaoDoSistema` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PermissaoSB`
--

LOCK TABLES `PermissaoSB` WRITE;
/*!40000 ALTER TABLE `PermissaoSB` DISABLE KEYS */;
/*!40000 ALTER TABLE `PermissaoSB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Permitido_Grupos`
--

DROP TABLE IF EXISTS `Permitido_Grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Permitido_Grupos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acesso_id` int(11) DEFAULT NULL,
  `grupo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n88ui63p76dd87tx34gv6s4g6` (`acesso_id`),
  KEY `FK_9216eklcqg2w59xu8tagt7mc2` (`grupo_id`),
  CONSTRAINT `FK_9216eklcqg2w59xu8tagt7mc2` FOREIGN KEY (`grupo_id`) REFERENCES `GrupoUsuarioSB` (`id`),
  CONSTRAINT `FK_n88ui63p76dd87tx34gv6s4g6` FOREIGN KEY (`acesso_id`) REFERENCES `PermissaoSB` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Permitido_Grupos`
--

LOCK TABLES `Permitido_Grupos` WRITE;
/*!40000 ALTER TABLE `Permitido_Grupos` DISABLE KEYS */;
/*!40000 ALTER TABLE `Permitido_Grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Permitido_Usuarios`
--

DROP TABLE IF EXISTS `Permitido_Usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Permitido_Usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acesso_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9j92iu9201tx0dm010v5fq4wo` (`usuario_id`,`acesso_id`),
  KEY `FK_fk85i2tfymqt0wynyaoexq9tg` (`acesso_id`),
  CONSTRAINT `FK_iwr4xspc3rxmi9d3ny0bv7iyv` FOREIGN KEY (`usuario_id`) REFERENCES `UsuarioSB` (`id`),
  CONSTRAINT `FK_fk85i2tfymqt0wynyaoexq9tg` FOREIGN KEY (`acesso_id`) REFERENCES `PermissaoSB` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Permitido_Usuarios`
--

LOCK TABLES `Permitido_Usuarios` WRITE;
/*!40000 ALTER TABLE `Permitido_Usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `Permitido_Usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoIp`
--

DROP TABLE IF EXISTS `TipoIp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoIp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoIp`
--

LOCK TABLES `TipoIp` WRITE;
/*!40000 ALTER TABLE `TipoIp` DISABLE KEYS */;
/*!40000 ALTER TABLE `TipoIp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UsuarioSB`
--

DROP TABLE IF EXISTS `UsuarioSB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UsuarioSB` (
  `tipoUsuario` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CEP` varchar(255) DEFAULT NULL,
  `apelido` varchar(255) DEFAULT NULL,
  `ativo` bit(1) NOT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `dataCadastro` date DEFAULT NULL,
  `dataHoraAlteracao` datetime DEFAULT NULL,
  `dataHoraInsersao` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `grupo_id` int(11) DEFAULT NULL,
  `usuarioAlteracao_id` int(11) DEFAULT NULL,
  `usuarioInsercao_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m1rxw56li2lkr3guust77ltso` (`email`),
  UNIQUE KEY `UK_d8hre5rs465kuoya4b9epuot7` (`apelido`),
  KEY `FK_gtnjpy9euexr0nh428wnhnj60` (`grupo_id`),
  KEY `FK_4f4coyrx3q04uo7byen68lkej` (`usuarioAlteracao_id`),
  KEY `FK_s0r9y9dy7c637ivd7rpo0d4r0` (`usuarioInsercao_id`),
  CONSTRAINT `FK_s0r9y9dy7c637ivd7rpo0d4r0` FOREIGN KEY (`usuarioInsercao_id`) REFERENCES `UsuarioSB` (`id`),
  CONSTRAINT `FK_4f4coyrx3q04uo7byen68lkej` FOREIGN KEY (`usuarioAlteracao_id`) REFERENCES `UsuarioSB` (`id`),
  CONSTRAINT `FK_gtnjpy9euexr0nh428wnhnj60` FOREIGN KEY (`grupo_id`) REFERENCES `GrupoUsuarioSB` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UsuarioSB`
--

LOCK TABLES `UsuarioSB` WRITE;
/*!40000 ALTER TABLE `UsuarioSB` DISABLE KEYS */;
/*!40000 ALTER TABLE `UsuarioSB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modulos_grupo`
--

DROP TABLE IF EXISTS `modulos_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modulos_grupo` (
  `grupo_id` int(11) NOT NULL,
  `modulo_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ngph303pxo2b2mrtpfkoacmwq` (`grupo_id`,`modulo_id`),
  KEY `FK_hecoy4khp41qh23d2sj3xemja` (`modulo_id`),
  CONSTRAINT `FK_69c04tm7viserl8svuupnf5vp` FOREIGN KEY (`grupo_id`) REFERENCES `GrupoUsuarioSB` (`id`),
  CONSTRAINT `FK_hecoy4khp41qh23d2sj3xemja` FOREIGN KEY (`modulo_id`) REFERENCES `ModuloAcaoSistema` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modulos_grupo`
--

LOCK TABLES `modulos_grupo` WRITE;
/*!40000 ALTER TABLE `modulos_grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `modulos_grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_grupo`
--

DROP TABLE IF EXISTS `usuario_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_grupo` (
  `grupo_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  UNIQUE KEY `usuarioDuplicado` (`usuario_id`,`grupo_id`),
  KEY `FK_5p20y5panoea7wc040qm6eemd` (`grupo_id`),
  CONSTRAINT `FK_5p20y5panoea7wc040qm6eemd` FOREIGN KEY (`grupo_id`) REFERENCES `GrupoUsuarioSB` (`id`),
  CONSTRAINT `FK_m32it4c8rkf6t8nno481k43q4` FOREIGN KEY (`usuario_id`) REFERENCES `UsuarioSB` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_grupo`
--

LOCK TABLES `usuario_grupo` WRITE;
/*!40000 ALTER TABLE `usuario_grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_grupo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-27 14:23:34
