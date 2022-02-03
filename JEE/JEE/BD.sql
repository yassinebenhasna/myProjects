-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 12 déc. 2019 à 10:09
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet1`
--

-- --------------------------------------------------------

--
-- Structure de la table `choix_module`
--

DROP TABLE IF EXISTS `choix_module`;
CREATE TABLE IF NOT EXISTS `choix_module` (
  `id_choix` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PERS` int(11) DEFAULT NULL,
  `ID_MODULE1` int(11) DEFAULT NULL,
  `ID_MODULE2` int(11) DEFAULT NULL,
  `ID_MODULE3` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_choix`),
  KEY `ID_PERS` (`ID_PERS`),
  KEY `ID_MODULE1` (`ID_MODULE1`),
  KEY `ID_MODULE2` (`ID_MODULE2`),
  KEY `ID_MODULE3` (`ID_MODULE3`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `choix_module`
--

INSERT INTO `choix_module` (`id_choix`, `ID_PERS`, `ID_MODULE1`, `ID_MODULE2`, `ID_MODULE3`) VALUES
(9, 27, 58, 59, 61),
(10, 28, 58, 59, 61);

-- --------------------------------------------------------

--
-- Structure de la table `departement`
--

DROP TABLE IF EXISTS `departement`;
CREATE TABLE IF NOT EXISTS `departement` (
  `ID_DEP` int(11) NOT NULL AUTO_INCREMENT,
  `nom_dep` varchar(50) CHARACTER SET latin1 NOT NULL,
  `ID_CHEF` int(11) DEFAULT NULL,
  `ID_ADJ` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_DEP`),
  KEY `ID_CHEF` (`ID_CHEF`),
  KEY `ID_ADJ` (`ID_ADJ`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `departement`
--

INSERT INTO `departement` (`ID_DEP`, `nom_dep`, `ID_CHEF`, `ID_ADJ`) VALUES
(1, 'informatique', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `etat`
--

DROP TABLE IF EXISTS `etat`;
CREATE TABLE IF NOT EXISTS `etat` (
  `id_etat` int(11) NOT NULL AUTO_INCREMENT,
  `etat` varchar(15) NOT NULL,
  PRIMARY KEY (`id_etat`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etat`
--

INSERT INTO `etat` (`id_etat`, `etat`) VALUES
(1, 'active'),
(2, 'desactive');

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

DROP TABLE IF EXISTS `filiere`;
CREATE TABLE IF NOT EXISTS `filiere` (
  `ID_FIL` int(11) NOT NULL AUTO_INCREMENT,
  `fil` varchar(30) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`ID_FIL`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `filiere`
--

INSERT INTO `filiere` (`ID_FIL`, `fil`) VALUES
(1, 'SMI'),
(2, 'SMA'),
(3, 'M2I'),
(4, 'MTN'),
(5, 'SMP');

-- --------------------------------------------------------

--
-- Structure de la table `fonction`
--

DROP TABLE IF EXISTS `fonction`;
CREATE TABLE IF NOT EXISTS `fonction` (
  `ID_fct` int(11) NOT NULL AUTO_INCREMENT,
  `nom_fct` varchar(25) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`ID_fct`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `fonction`
--

INSERT INTO `fonction` (`ID_fct`, `nom_fct`) VALUES
(1, 'enseignant chercheur'),
(2, 'ingénieur'),
(3, 'contractuel'),
(4, 'vacataire');

-- --------------------------------------------------------

--
-- Structure de la table `grade`
--

DROP TABLE IF EXISTS `grade`;
CREATE TABLE IF NOT EXISTS `grade` (
  `ID_GRADE` int(11) NOT NULL AUTO_INCREMENT,
  `NOM_GRADE` varchar(25) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`ID_GRADE`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `grade`
--

INSERT INTO `grade` (`ID_GRADE`, `NOM_GRADE`) VALUES
(1, 'ingénieur'),
(2, 'contactuel'),
(3, 'PA'),
(4, 'PH'),
(5, 'PES');

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

DROP TABLE IF EXISTS `module`;
CREATE TABLE IF NOT EXISTS `module` (
  `ID_MODULE` int(11) NOT NULL AUTO_INCREMENT,
  `nom_mod` varchar(52) CHARACTER SET latin1 NOT NULL,
  `ID_RESP` int(11) DEFAULT NULL,
  `ID_ENS_CRS` int(11) DEFAULT NULL,
  `ID_ENS_TP` int(11) DEFAULT NULL,
  `ID_ENS_TD` int(11) DEFAULT NULL,
  `ID_SEM` int(11) DEFAULT NULL,
  `ID_TYPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_MODULE`),
  KEY `ID_RESP` (`ID_RESP`),
  KEY `ID_SEM` (`ID_SEM`),
  KEY `ID_ENS_TD` (`ID_ENS_TD`),
  KEY `ID_ENS_CRS` (`ID_ENS_CRS`),
  KEY `ID_ENS_TP` (`ID_ENS_TP`),
  KEY `ID_TYPE` (`ID_TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `module`
--

INSERT INTO `module` (`ID_MODULE`, `nom_mod`, `ID_RESP`, `ID_ENS_CRS`, `ID_ENS_TP`, `ID_ENS_TD`, `ID_SEM`, `ID_TYPE`) VALUES
(53, 'JEE', 26, NULL, NULL, NULL, 1, 3),
(58, 'crypto', 28, NULL, 28, 27, 1, 3),
(59, 'java', 30, 28, 27, NULL, 5, 3),
(60, 'algebre', 29, NULL, NULL, NULL, 1, 2),
(61, 'c++', 27, 27, NULL, 28, 5, 3);

-- --------------------------------------------------------

--
-- Structure de la table `mod_fil`
--

DROP TABLE IF EXISTS `mod_fil`;
CREATE TABLE IF NOT EXISTS `mod_fil` (
  `id_mod_fil` int(11) NOT NULL AUTO_INCREMENT,
  `ID_MODULE` int(11) NOT NULL,
  `ID_FIL` int(11) NOT NULL,
  PRIMARY KEY (`id_mod_fil`),
  KEY `mod_fil_ibfk_1` (`ID_FIL`),
  KEY `mod_fil_ibfk_2` (`ID_MODULE`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `mod_fil`
--

INSERT INTO `mod_fil` (`id_mod_fil`, `ID_MODULE`, `ID_FIL`) VALUES
(14, 53, 3),
(17, 58, 3),
(18, 59, 1),
(19, 61, 2),
(20, 60, 1);

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `ID_PERS` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) CHARACTER SET latin1 NOT NULL,
  `prenom` varchar(50) CHARACTER SET latin1 NOT NULL,
  `CIN` varchar(12) NOT NULL,
  `email` varchar(50) CHARACTER SET latin1 NOT NULL,
  `tel` varchar(15) CHARACTER SET latin1 NOT NULL,
  `password` varchar(255) CHARACTER SET latin1 NOT NULL,
  `role` varchar(10) DEFAULT 'user',
  `ID_fct` int(11) DEFAULT NULL,
  `ID_GRADE` int(11) DEFAULT NULL,
  `id_etat` int(11) DEFAULT '1',
  PRIMARY KEY (`ID_PERS`),
  UNIQUE KEY `CIN` (`CIN`),
  KEY `ID_fct` (`ID_fct`),
  KEY `ID_GRADE` (`ID_GRADE`),
  KEY `personne_ibfk_3` (`id_etat`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`ID_PERS`, `nom`, `prenom`, `CIN`, `email`, `tel`, `password`, `role`, `ID_fct`, `ID_GRADE`, `id_etat`) VALUES
(25, 'daoudi', 'mostapha', 'S00001', 'daoudi@2020.com', '0622114455', '21232f297a57a5a743894a0e4a801fc3', 'admin', 1, 4, 1),
(26, 'lakhouaja', 'abdelhak', 'S00002', 'lakhouaja@hotmail.com', '0654781293', '21232f297a57a5a743894a0e4a801fc3', 'admin', 1, 3, 1),
(27, 'jaara', 'mokhtar', 'S00003', 'jaara@tesst.fr', '0214587984', 'ee11cbb19052e40b07aac0ca060c23ee', 'user', 1, 1, 1),
(28, 'azizi', 'mostapha', 'S00004', 'azizi@hotmail.com', '0321456987', 'ee11cbb19052e40b07aac0ca060c23ee', 'user', 3, 2, 1),
(29, 'chellali', 'mostafa', 'fr785496', 'chellali@hotmail.com', '0124578963', 'ee11cbb19052e40b07aac0ca060c23ee', 'user', 1, 1, 2),
(30, 'gabli', 'mohammed', 'fr74851', 'med@yahoo.fr', '0321456987', 'ee11cbb19052e40b07aac0ca060c23ee', 'user', 1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `semestre`
--

DROP TABLE IF EXISTS `semestre`;
CREATE TABLE IF NOT EXISTS `semestre` (
  `ID_SEM` int(11) NOT NULL AUTO_INCREMENT,
  `nom_sem` varchar(11) NOT NULL,
  PRIMARY KEY (`ID_SEM`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `semestre`
--

INSERT INTO `semestre` (`ID_SEM`, `nom_sem`) VALUES
(1, 'S1'),
(2, 'S2'),
(3, 'S3'),
(4, 'S4'),
(5, 'S5'),
(6, 'S6');

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

DROP TABLE IF EXISTS `type`;
CREATE TABLE IF NOT EXISTS `type` (
  `ID_TYPE` int(11) NOT NULL AUTO_INCREMENT,
  `type_module` varchar(30) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`ID_TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `type`
--

INSERT INTO `type` (`ID_TYPE`, `type_module`) VALUES
(1, 'CTDTP'),
(2, 'CTD'),
(3, 'CTP');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `choix_module`
--
ALTER TABLE `choix_module`
  ADD CONSTRAINT `choix_module_ibfk_1` FOREIGN KEY (`ID_PERS`) REFERENCES `personne` (`ID_PERS`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `choix_module_ibfk_2` FOREIGN KEY (`ID_MODULE1`) REFERENCES `module` (`ID_MODULE`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `choix_module_ibfk_3` FOREIGN KEY (`ID_MODULE2`) REFERENCES `module` (`ID_MODULE`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `choix_module_ibfk_4` FOREIGN KEY (`ID_MODULE3`) REFERENCES `module` (`ID_MODULE`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `departement`
--
ALTER TABLE `departement`
  ADD CONSTRAINT `departement_ibfk_1` FOREIGN KEY (`ID_CHEF`) REFERENCES `personne` (`ID_PERS`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `departement_ibfk_2` FOREIGN KEY (`ID_ADJ`) REFERENCES `personne` (`ID_PERS`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `module`
--
ALTER TABLE `module`
  ADD CONSTRAINT `module_ibfk_1` FOREIGN KEY (`ID_RESP`) REFERENCES `personne` (`ID_PERS`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `module_ibfk_2` FOREIGN KEY (`ID_ENS_CRS`) REFERENCES `personne` (`ID_PERS`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `module_ibfk_3` FOREIGN KEY (`ID_ENS_TP`) REFERENCES `personne` (`ID_PERS`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `module_ibfk_4` FOREIGN KEY (`ID_ENS_TD`) REFERENCES `personne` (`ID_PERS`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `module_ibfk_5` FOREIGN KEY (`ID_SEM`) REFERENCES `semestre` (`ID_SEM`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `module_ibfk_6` FOREIGN KEY (`ID_TYPE`) REFERENCES `type` (`ID_TYPE`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `mod_fil`
--
ALTER TABLE `mod_fil`
  ADD CONSTRAINT `mod_fil_ibfk_1` FOREIGN KEY (`ID_FIL`) REFERENCES `filiere` (`ID_FIL`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mod_fil_ibfk_2` FOREIGN KEY (`ID_MODULE`) REFERENCES `module` (`ID_MODULE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `personne`
--
ALTER TABLE `personne`
  ADD CONSTRAINT `personne_ibfk_1` FOREIGN KEY (`ID_fct`) REFERENCES `fonction` (`ID_fct`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `personne_ibfk_2` FOREIGN KEY (`ID_GRADE`) REFERENCES `grade` (`ID_GRADE`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `personne_ibfk_3` FOREIGN KEY (`id_etat`) REFERENCES `etat` (`id_etat`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
