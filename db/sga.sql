-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2019 at 03:55 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sga`
--

-- --------------------------------------------------------

--
-- Table structure for table `avions`
--

CREATE TABLE `avions` (
  `Num` int(11) NOT NULL,
  `Capacite` int(11) NOT NULL,
  `model` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avions`
--

INSERT INTO `avions` (`Num`, `Capacite`, `model`) VALUES
(1, 150, 'Boeing');

-- --------------------------------------------------------

--
-- Table structure for table `passagers`
--

CREATE TABLE `passagers` (
  `CIN` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `adresse` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `passagers`
--

INSERT INTO `passagers` (`CIN`, `nom`, `prenom`, `adresse`) VALUES
(13465885, 'Salah Eddine', 'Ben Yahya', 'Mounastir Tunisie'),
(13465895, 'Amine', 'Ben Yedder', 'Djerba Tunisie');

-- --------------------------------------------------------

--
-- Table structure for table `pilotes`
--

CREATE TABLE `pilotes` (
  `license` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pilotes`
--

INSERT INTO `pilotes` (`license`, `nom`, `prenom`) VALUES
(512012, 'Ben Salah', 'Mohamed');

-- --------------------------------------------------------

--
-- Table structure for table `vols`
--

CREATE TABLE `vols` (
  `id` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `license` int(11) NOT NULL,
  `destination` varchar(20) NOT NULL,
  `depart` date NOT NULL,
  `cout` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vols`
--

INSERT INTO `vols` (`id`, `num`, `license`, `destination`, `depart`, `cout`) VALUES
(1, 1, 512012, 'Tunis Tunisie', '2019-06-06', 100);

-- --------------------------------------------------------

--
-- Table structure for table `vol_passager`
--

CREATE TABLE `vol_passager` (
  `cin` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vol_passager`
--

INSERT INTO `vol_passager` (`cin`, `id`) VALUES
(13465895, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `avions`
--
ALTER TABLE `avions`
  ADD PRIMARY KEY (`Num`);

--
-- Indexes for table `passagers`
--
ALTER TABLE `passagers`
  ADD PRIMARY KEY (`CIN`);

--
-- Indexes for table `pilotes`
--
ALTER TABLE `pilotes`
  ADD PRIMARY KEY (`license`);

--
-- Indexes for table `vols`
--
ALTER TABLE `vols`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
