-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2017 at 04:21 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `heart_disease`
--

-- --------------------------------------------------------

--
-- Table structure for table `bobot`
--

CREATE TABLE `bobot` (
  `id_bobot` int(11) NOT NULL,
  `bobot` double DEFAULT NULL,
  `layer` enum('hidden','output','','') NOT NULL,
  `indeks_awal` int(11) NOT NULL,
  `indeks_akhir` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `preprocessed_data`
--

CREATE TABLE `preprocessed_data` (
  `id_data` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `sex` int(11) NOT NULL,
  `cp` int(11) NOT NULL,
  `trestbps` int(11) NOT NULL,
  `chol` int(11) NOT NULL,
  `fbs` int(11) NOT NULL,
  `restecg` int(11) NOT NULL,
  `thalach` int(11) NOT NULL,
  `exang` int(11) NOT NULL,
  `oldpeak` int(11) NOT NULL,
  `slope` int(11) NOT NULL,
  `ca` int(11) NOT NULL,
  `thal` int(11) NOT NULL,
  `num` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `raw_patient`
--

CREATE TABLE `raw_patient` (
  `id_patient` int(11) NOT NULL,
  `age` float DEFAULT NULL,
  `sex` float DEFAULT NULL,
  `cp` float DEFAULT NULL,
  `trestbps` float DEFAULT NULL,
  `chol` float DEFAULT NULL,
  `fbs` float DEFAULT NULL,
  `restecg` float DEFAULT NULL,
  `thalach` float DEFAULT NULL,
  `exang` float DEFAULT NULL,
  `oldpeak` float DEFAULT NULL,
  `slope` float DEFAULT NULL,
  `ca` float DEFAULT NULL,
  `thal` float DEFAULT NULL,
  `num` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bobot`
--
ALTER TABLE `bobot`
  ADD PRIMARY KEY (`id_bobot`);

--
-- Indexes for table `preprocessed_data`
--
ALTER TABLE `preprocessed_data`
  ADD PRIMARY KEY (`id_data`),
  ADD KEY `id_patient` (`id_patient`);

--
-- Indexes for table `raw_patient`
--
ALTER TABLE `raw_patient`
  ADD PRIMARY KEY (`id_patient`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bobot`
--
ALTER TABLE `bobot`
  MODIFY `id_bobot` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `preprocessed_data`
--
ALTER TABLE `preprocessed_data`
  MODIFY `id_data` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `raw_patient`
--
ALTER TABLE `raw_patient`
  MODIFY `id_patient` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `preprocessed_data`
--
ALTER TABLE `preprocessed_data`
  ADD CONSTRAINT `preprocessed_data_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `raw_patient` (`id_patient`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
