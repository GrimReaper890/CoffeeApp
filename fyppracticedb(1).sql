-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 08, 2017 at 09:28 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fyppracticedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--

CREATE TABLE `administrator` (
  `name` varchar(30) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `type` int(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`name`, `phone`, `username`, `password`, `type`) VALUES
('death', '12346', 'grim', '1234', 1),
('aslam', '123', 'salam', '124', 0),
('maria', '9999', 'qibtiya', 'mughal', 1);

-- --------------------------------------------------------

--
-- Table structure for table `bscs_reg_8th`
--

CREATE TABLE `bscs_reg_8th` (
  `Name` varchar(50) NOT NULL,
  `Roll no.` varchar(50) NOT NULL,
  `contact` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bscs_reg_8th`
--

INSERT INTO `bscs_reg_8th` (`Name`, `Roll no.`, `contact`) VALUES
('Horiya Sarfraz', 'BSCSF13E063', '03110730231'),
('Waqar Ahmad khan ', 'BSCSF13E068', '03327054752'),
('Fazila Arshad', 'BSCSF13E075', '03067064003'),
('Hafiza Fareeha Anwar', 'BSCSF13E076', '03317976495'),
('Zeeshan Haider', 'BSCSF13E90', '03006567006');

-- --------------------------------------------------------

--
-- Table structure for table `bscs_ss1_8th`
--

CREATE TABLE `bscs_ss1_8th` (
  `Name` varchar(50) NOT NULL,
  `Roll no.` varchar(50) NOT NULL,
  `Contact` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bscs_ss1_8th`
--

INSERT INTO `bscs_ss1_8th` (`Name`, `Roll no.`, `Contact`) VALUES
('Saman Iqbal', 'BSCSF13E079', '03076720852'),
('Shah Fahad', 'BSCSF13E101', '03078793875'),
('Maria Qibtiya', 'BSCSF13E115', '03069873932'),
('Faraz Hussain', 'BSCSF13E122', '03126679999'),
('Fareena Mumtaz', 'BSCSF13E91', '03069514131');

-- --------------------------------------------------------

--
-- Table structure for table `bscs_ss2_8th`
--

CREATE TABLE `bscs_ss2_8th` (
  `Name` varchar(50) NOT NULL,
  `Roll no.` varchar(50) NOT NULL,
  `Contact` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bscs_ss2_8th`
--

INSERT INTO `bscs_ss2_8th` (`Name`, `Roll no.`, `Contact`) VALUES
('Roshnick Munir', 'BSCSF13E088', '03046889729'),
('Zainab Batool', 'BSCSF13E102', '03227130908'),
('M Mubashir Asmlam', 'BSCSF13E107', '03035100228'),
('Ali Armghan', 'BSCSF13E111', '03455080658'),
('Hafiza Seemab ', 'BSCSF13E125', '03015246622');

-- --------------------------------------------------------

--
-- Table structure for table `bs_class_details`
--

CREATE TABLE `bs_class_details` (
  `Semester` varchar(50) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Program` varchar(50) NOT NULL,
  `Session` varchar(50) NOT NULL,
  `cr_name` varchar(50) NOT NULL,
  `cr_pass` varchar(50) NOT NULL,
  `id` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bs_class_details`
--

INSERT INTO `bs_class_details` (`Semester`, `Type`, `Program`, `Session`, `cr_name`, `cr_pass`, `id`) VALUES
('8th', 'BS', 'CS', '2013-2017', 'Aslam', 'BirdFree', 2);

-- --------------------------------------------------------

--
-- Table structure for table `teacher_details`
--

CREATE TABLE `teacher_details` (
  `Name` varchar(50) NOT NULL,
  `Contact` varchar(50) NOT NULL,
  `Department` varchar(100) NOT NULL,
  `Subject` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher_details`
--

INSERT INTO `teacher_details` (`Name`, `Contact`, `Department`, `Subject`) VALUES
('Amir Zia', '03057617666', 'CS & IT', 'OOP'),
('Abid Rafique', '03334887792', 'CS & IT', 'HCI'),
('Bilal Ilyas', '03156702020', 'CS & IT', 'Web Development'),
('Salman Tiwana', '03235766660', 'CS & IT', 'ICT'),
('Fakhr-u-llah Mangla', '03008704967', 'CS & IT', 'Theory of Automata');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bscs_reg_8th`
--
ALTER TABLE `bscs_reg_8th`
  ADD PRIMARY KEY (`Roll no.`);

--
-- Indexes for table `bscs_ss1_8th`
--
ALTER TABLE `bscs_ss1_8th`
  ADD PRIMARY KEY (`Roll no.`);

--
-- Indexes for table `bscs_ss2_8th`
--
ALTER TABLE `bscs_ss2_8th`
  ADD PRIMARY KEY (`Roll no.`);

--
-- Indexes for table `bs_class_details`
--
ALTER TABLE `bs_class_details`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bs_class_details`
--
ALTER TABLE `bs_class_details`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
