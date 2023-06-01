-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 03, 2023 at 03:07 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecounselling`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_login`
--

CREATE TABLE `admin_login` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `name` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `password` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `last_login` varchar(255) COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `admin_login`
--

INSERT INTO `admin_login` (`id`, `email`, `name`, `password`, `last_login`) VALUES
(1, 'subhrajyoti.dey@gmail.com', 'Subhrajyoti Dey', '827ccb0eea8a706c4c34a16891f84e7b', 'April 20, 2014, 10:00 am IST'),
(2, 'arnabnandy2@gmail.com', 'Arnab Nandy', '827ccb0eea8a706c4c34a16891f84e7b', ''),
(3, 'ssarkar445@gmail.com', 'Suman Sarkar', '827ccb0eea8a706c4c34a16891f84e7b', ''),
(4, 'nsinha57@gmail.com', 'Nilotpol Sinha', '21232f297a57a5a743894a0e4a801fc3', 'May 3, 2023, 6:07 pm IST');

-- --------------------------------------------------------

--
-- Table structure for table `candidate_details`
--

CREATE TABLE `candidate_details` (
  `id` int(11) NOT NULL,
  `candidate_id` int(50) NOT NULL,
  `candidate_name` varchar(50) NOT NULL,
  `rank` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(40) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `last_login` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `candidate_details`
--

INSERT INTO `candidate_details` (`id`, `candidate_id`, `candidate_name`, `rank`, `email`, `password`, `phone`, `address`, `last_login`) VALUES
(3, 985954604, 'SOUMYA SARKAR', '84', 'saikat.banj@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', '+919433947924', 'dddd', ''),
(4, 190676841, 'Arnab Nandy', '90', 'arnabnandy2@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Madhyamgram, Bankimpally(E)', ''),
(5, 985954604, 'MEGHALEE DEY', '1', 'subhrajyoti_sd-1@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(6, 985954606, 'SHUBHRADEEP BHADRA', '2', 'subhrajyoti_sd-2@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(7, 985954607, 'BRATATI BERA', '3', 'subhrajyoti_sd-3@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(8, 985954608, 'NIVEDITA ROYCHOWDHURY', '4', 'subhrajyoti_sd-4@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(9, 985954609, 'RANAJIT SAHA', '5', 'subhrajyoti_sd-5@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(10, 985954610, 'SOURAV GHOSH', '6', 'subhrajyoti_sd-6@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(11, 985954611, 'POOJA GUPTA', '7', 'subhrajyoti_sd-7@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(12, 985954612, 'ARINDAM CHATTERJEE', '8', 'subhrajyoti_sd-8@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(13, 985954613, 'KOUSTAV MAITRA', '9', 'subhrajyoti_sd-9@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(14, 985954614, 'NILADRI LAHIRY', '10', 'subhrajyoti_sd-10@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(15, 985954615, 'ABHIJIT GHOSH', '11', 'subhrajyoti_sd-11@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(16, 985954616, 'NILABHRO DATTA', '12', 'subhrajyoti_sd-12@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(17, 985954617, 'SRIMAN SHARANYA SARKAR', '13', 'subhrajyoti_sd-13@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(18, 985954618, 'SRITAM HAZRA', '14', 'subhrajyoti_sd-14@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(19, 985954619, 'ANKITA SINGH', '15', 'subhrajyoti_sd-15@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(20, 985954620, 'ASHES RANJAN NASKAR', '16', 'subhrajyoti_sd-16@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(21, 985954621, 'SHATABDI MONDAL', '17', 'subhrajyoti_sd-17@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(22, 985954622, 'SHRAMANA THAKUR', '18', 'subhrajyoti_sd-18@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(23, 985954623, 'SANCHARI DAS', '19', 'subhrajyoti_sd-19@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(24, 985954624, 'NITESH KUMAR JHA', '20', 'subhrajyoti_sd-20@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(25, 985954625, 'PRASUN CHAKRABORTY', '21', 'subhrajyoti_sd-21@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(26, 985954626, 'CHANDAN ROY', '22', 'subhrajyoti_sd-22@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(27, 985954627, 'DEBENDRA NATH RANA', '23', 'subhrajyoti_sd-23@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(28, 985954628, 'ABDUL AZIZ', '24', 'subhrajyoti_sd-24@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(29, 985954629, 'YOGESH AGARWAL', '25', 'arnabtech1-25@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013888999', 'Kolkata', ''),
(30, 985954630, 'ARIJIT DAS', '26', 'arnabtech1-26@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(31, 985954631, 'AVIK GUHA', '27', 'arnabtech1-27@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(32, 985954632, 'SOMNATH MUKHERJEE', '28', 'arnabtech1-28@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(33, 985954633, 'RUCHIRA DE', '29', 'arnabtech1-29@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(34, 985954634, 'POULAMI BAL', '30', 'arnabtech1-30@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(35, 985954635, 'ANKITA SARKAR', '31', 'arnabtech1-31@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(36, 985954636, 'AVIK CHATTOPADHYAY', '32', 'arnabtech1-32@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(37, 985954637, 'SUBHRAJYOTI SANTRA', '33', 'arnabtech1-33@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(38, 985954638, 'LOPAMUDRA BANERJEE', '34', 'arnabtech1-34@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(39, 985954639, 'ANINDITA GHOSH', '35', 'arnabtech1-35@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(40, 985954640, 'TAMOJIT BISWAS', '36', 'arnabtech1-36@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(41, 985954641, 'SHWETA GHOSH', '37', 'arnabtech1-37@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(42, 985954642, 'RUMPA SADHUKHAN', '38', 'arnabtech1-38@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(43, 985954643, 'ANJISHNU MONDAL', '39', 'arnabtech1-39@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(44, 985954644, 'ANUJA NATH', '40', 'arnabtech1-40@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(45, 985954645, 'SUMANA SUR', '41', 'arnabtech1-41@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(46, 985954646, 'SAYAK DAS', '42', 'arnabtech1-42@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(47, 985954647, 'GUNJAN SEKHON', '43', 'arnabtech1-43@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(48, 985954648, 'SHAMIM AKTAR', '44', 'arnabtech1-44@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(49, 985954649, 'DEBARGHYA MUKHERJEE', '45', 'arnabtech1-45@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(50, 985954650, 'RUHIDAS ROY', '46', 'arnabtech1-46@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(51, 985954651, 'JUHI BAROI', '47', 'arnabtech1-47@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(52, 985954652, 'PRIYA BHAKAT', '48', 'arnabtech1-48@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(53, 985954653, 'ARNAB KOLEY', '49', 'arnabtech1-49@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', ''),
(54, 985954654, 'SHIVAM DAS', '50', 'arnabtech1-50@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '+918013635767', 'Kolkata', '');

-- --------------------------------------------------------

--
-- Table structure for table `candidate_preferences`
--

CREATE TABLE `candidate_preferences` (
  `rank` int(30) NOT NULL,
  `pref_1` int(30) NOT NULL,
  `pref_2` int(30) NOT NULL,
  `pref_3` int(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `candidate_preferences`
--

INSERT INTO `candidate_preferences` (`rank`, `pref_1`, `pref_2`, `pref_3`) VALUES
(1, 1, 2, 3),
(2, 6, 10, 3),
(3, 1, 3, 6),
(4, 6, 10, 1),
(5, 1, 6, 10),
(6, 11, 10, 1),
(7, 25, 10, 1),
(8, 11, 10, 2),
(9, 10, 1, 3),
(10, 3, 6, 25),
(11, 1, 2, 25),
(12, 25, 26, 1),
(13, 7, 1, 26),
(14, 3, 17, 24),
(15, 3, 2, 1),
(16, 4, 3, 7),
(17, 1, 4, 8),
(18, 9, 7, 25),
(19, 2, 3, 6),
(20, 1, 6, 10),
(21, 2, 26, 16),
(22, 6, 25, 9),
(23, 6, 3, 2),
(24, 10, 2, 3),
(25, 3, 14, 11),
(26, 3, 5, 7),
(27, 8, 1, 12),
(28, 10, 27, 9),
(29, 13, 15, 17),
(30, 6, 7, 18),
(31, 21, 15, 9),
(32, 19, 12, 11),
(33, 14, 10, 5),
(34, 4, 9, 17),
(35, 9, 10, 17),
(36, 19, 17, 4),
(37, 9, 3, 6),
(38, 7, 8, 9),
(39, 19, 15, 16),
(40, 12, 14, 5),
(41, 9, 10, 11),
(42, 17, 11, 14),
(43, 5, 8, 13),
(44, 10, 11, 12),
(45, 19, 25, 18),
(46, 14, 15, 16),
(47, 7, 8, 9),
(48, 12, 13, 14),
(49, 9, 12, 18),
(50, 11, 13, 15),
(90, 1, 3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `candidate_reg_log_check`
--

CREATE TABLE `candidate_reg_log_check` (
  `email` varchar(50) NOT NULL,
  `password` varchar(40) NOT NULL,
  `activation_code` varchar(50) NOT NULL,
  `chk_flg` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `candidate_reg_log_check`
--

INSERT INTO `candidate_reg_log_check` (`email`, `password`, `activation_code`, `chk_flg`) VALUES
('subhrajyoti_sd-2@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('saikat.banj@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', 'taCLi', 1),
('arnabnandy2@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', 'IJC0S', 1),
('subhrajyoti_sd-1@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-3@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-4@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-5@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-6@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-7@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-8@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-9@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-10@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-11@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-12@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-13@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-14@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-15@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-16@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-17@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-18@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-19@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-20@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-21@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-22@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-23@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('subhrajyoti_sd-24@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-25@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-26@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-27@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-28@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-29@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-30@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-31@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-32@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-33@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-34@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-35@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-36@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-37@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-38@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-39@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-40@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-41@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-42@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-43@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-44@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-45@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-46@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-47@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-48@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-49@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1),
('arnabtech1-50@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `closingrank`
--

CREATE TABLE `closingrank` (
  `id` int(11) NOT NULL,
  `instname` varchar(100) NOT NULL,
  `opening` varchar(10) NOT NULL,
  `closing` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `closingrank`
--

INSERT INTO `closingrank` (`id`, `instname`, `opening`, `closing`) VALUES
(7, 'BENGAL COLLEGE OF ENGINEERING & TECHNOLOGY', '289', '882'),
(2, 'TECHNO INDIA - HOOGHLY', '302', '2220'),
(3, 'COMPUTER APPLICATION CENTRE, HERITAGE INSTITUTE OF TECHNOLOGY', '159', '548'),
(4, 'INSTITUTE OF SCIENCE AND TECHNOLOGY', '1976', '3598 (V)'),
(5, 'PAILAN COLLEGE OF MANAGEMENT & TECHNOLOGY', '301', '1977'),
(6, 'TECHNO INDIA', '1', '281'),
(1, 'BENGAL COLLEGE OF ENGINEERING & TECHNOLOGY', '80', '3063'),
(8, 'DSMS BUSINESS SCHOOL', '1076', '3525'),
(9, 'NARULA INSTITUTE OF TECHNOLOGY', '496', '1538'),
(10, 'RCC INSTITUTE OF INFORMATION TECHNOLOGY', '5', '147'),
(11, 'HALDIA INSTITUTE OF TECHNOLOGY', '394', '1954'),
(12, 'SABITA DEVI EDUCATION TRUST - BRAINWARE GROUP OF INSTITUTION', '383', '3184'),
(13, 'NETAJI SUBHASH ENGINEERING COLLEGE', '177', '491'),
(14, 'DR. B. C. ROY ENGINEERING COLLEGE, DURGAPUR', '395', '1988'),
(15, 'BANKURA UNNAYANI INSTITUTE OF ENGINEERING', '1274', '3638 (V)'),
(16, 'FUTURE INSTITUTE OF ENGINEERING AND MANAGEMENT', '149', '1268'),
(17, 'CAMELLIA INSTITUTE OF TECHNOLOGY', '874', '2922'),
(18, 'TECHNO INDIA COLLEGE OF TECHNOLOGY', '315', '1113'),
(19, 'REGENT EDUCATION & RESEARCH FOUNDATION GROUP OF INSTITUTION', '2121', '3031 (V)'),
(20, 'SEACOM ENGINEERING COLLEGE', '978', '3607'),
(21, 'ACADEMY OF TECHNOLOGY', '906', '2496'),
(22, 'ASANSOL ENGINEERING COLLEGE', '560', '2523'),
(23, 'GURUNANAK INSTITUTE OF TECHNOLOGY', '1170', '1978'),
(24, 'B.P. PODDAR INSTITUTE OF MANAGEMENT & TECHNOLOGY', '326', '867'),
(25, 'MEGHNAD SAHA INSTITUTE OF TECHNOLOGY', '341', '891'),
(26, 'KALYANI GOVERNMENT ENGINEERING COLLEGE', '62', '264'),
(27, 'MCKV INSTITUTE OF ENGINEERING', '382', '1473'),
(28, 'GREATER KOLKATA COLLEGE OF ENGINEERING & MANAGEMENT', '1967', '3593 (V)'),
(29, 'SILIGURI INSTITUTE OF TECHNOLOGY', '791', '3539'),
(30, 'CALCUTTA INSTITUTE OF TECHNOLOGY', '352', '2884'),
(31, 'JIS COLLEGE OF ENGINEERING', '306', '1830'),
(32, 'MANAGEMENT INSTITUTE OF DURGAPUR', '1730', '3129 (V)'),
(33, 'ST. MARY''S TECHNICAL CAMPUS KOLKATA', '589', '3156 (V)'),
(34, 'Burdwan University', '134', '344'),
(35, 'Calcutta University', '3', '146'),
(36, 'Jadavpur University', '2', '61'),
(37, 'Kalyani University', '314', '605'),
(38, 'North Bengal University', '82', '1592'),
(39, 'Vidyasagar University', '87', '940'),
(7, 'BENGAL COLLEGE OF ENGINEERING & TECHNOLOGY', '289', '882'),
(2, 'TECHNO INDIA - HOOGHLY', '302', '2220'),
(3, 'COMPUTER APPLICATION CENTRE, HERITAGE INSTITUTE OF TECHNOLOGY', '159', '548'),
(4, 'INSTITUTE OF SCIENCE AND TECHNOLOGY', '1976', '3598 (V)'),
(5, 'PAILAN COLLEGE OF MANAGEMENT & TECHNOLOGY', '301', '1977'),
(6, 'TECHNO INDIA', '1', '281'),
(1, 'BENGAL COLLEGE OF ENGINEERING & TECHNOLOGY', '80', '3063'),
(8, 'DSMS BUSINESS SCHOOL', '1076', '3525'),
(9, 'NARULA INSTITUTE OF TECHNOLOGY', '496', '1538'),
(10, 'RCC INSTITUTE OF INFORMATION TECHNOLOGY', '5', '147'),
(11, 'HALDIA INSTITUTE OF TECHNOLOGY', '394', '1954'),
(12, 'SABITA DEVI EDUCATION TRUST - BRAINWARE GROUP OF INSTITUTION', '383', '3184'),
(13, 'NETAJI SUBHASH ENGINEERING COLLEGE', '177', '491'),
(14, 'DR. B. C. ROY ENGINEERING COLLEGE, DURGAPUR', '395', '1988'),
(15, 'BANKURA UNNAYANI INSTITUTE OF ENGINEERING', '1274', '3638 (V)'),
(16, 'FUTURE INSTITUTE OF ENGINEERING AND MANAGEMENT', '149', '1268'),
(17, 'CAMELLIA INSTITUTE OF TECHNOLOGY', '874', '2922'),
(18, 'TECHNO INDIA COLLEGE OF TECHNOLOGY', '315', '1113'),
(19, 'REGENT EDUCATION & RESEARCH FOUNDATION GROUP OF INSTITUTION', '2121', '3031 (V)'),
(20, 'SEACOM ENGINEERING COLLEGE', '978', '3607'),
(21, 'ACADEMY OF TECHNOLOGY', '906', '2496'),
(22, 'ASANSOL ENGINEERING COLLEGE', '560', '2523'),
(23, 'GURUNANAK INSTITUTE OF TECHNOLOGY', '1170', '1978'),
(24, 'B.P. PODDAR INSTITUTE OF MANAGEMENT & TECHNOLOGY', '326', '867'),
(25, 'MEGHNAD SAHA INSTITUTE OF TECHNOLOGY', '341', '891'),
(26, 'KALYANI GOVERNMENT ENGINEERING COLLEGE', '62', '264'),
(27, 'MCKV INSTITUTE OF ENGINEERING', '382', '1473'),
(28, 'GREATER KOLKATA COLLEGE OF ENGINEERING & MANAGEMENT', '1967', '3593 (V)'),
(29, 'SILIGURI INSTITUTE OF TECHNOLOGY', '791', '3539'),
(30, 'CALCUTTA INSTITUTE OF TECHNOLOGY', '352', '2884'),
(31, 'JIS COLLEGE OF ENGINEERING', '306', '1830'),
(32, 'MANAGEMENT INSTITUTE OF DURGAPUR', '1730', '3129 (V)'),
(33, 'ST. MARY''S TECHNICAL CAMPUS KOLKATA', '589', '3156 (V)'),
(34, 'Burdwan University', '134', '344'),
(35, 'Calcutta University', '3', '146'),
(36, 'Jadavpur University', '2', '61'),
(37, 'Kalyani University', '314', '605'),
(38, 'North Bengal University', '82', '1592'),
(39, 'Vidyasagar University', '87', '940');

-- --------------------------------------------------------

--
-- Table structure for table `college_details`
--

CREATE TABLE `college_details` (
  `college_cuid` int(7) NOT NULL,
  `college_name` varchar(60) NOT NULL,
  `clgtype` varchar(20) NOT NULL,
  `university_name` varchar(50) NOT NULL,
  `location_address` text NOT NULL,
  `intake` int(3) NOT NULL,
  `seat1` int(3) NOT NULL,
  `seat2` int(11) NOT NULL,
  `phone1` varchar(20) NOT NULL,
  `phone2` varchar(20) NOT NULL,
  `website` varchar(30) NOT NULL,
  `email` varchar(40) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `college_details`
--

INSERT INTO `college_details` (`college_cuid`, `college_name`, `clgtype`, `university_name`, `location_address`, `intake`, `seat1`, `seat2`, `phone1`, `phone2`, `website`, `email`) VALUES
(1, 'BENGAL COLLEGE OF ENGINEERING & TECHNOLOGY ', 'self', 'West Bengal University of Technology', 'SAHID SUKUMAR BANERJEE SARANI, BIDHAN NAGAR, DURGAPUR - 713 212, DIST. BURDWAN, WEST BENGAL', 4, 4, 4, '2147483648', '2147483648', 'www.bcetdgp.ac.in', 'admission@sksgi.com'),
(2, 'TECHNO INDIA - HOOGHLY', 'self', 'West Bengal University of Technology', 'DHARAMPUR, SHANTINIKETAN ON G.T. ROAD, CHINSURAH, HOOGHLY', 4, 4, 4, '2147483649', '0', 'www.technoindiahooghly.org', 'info@technoinfdiahooghly.org'),
(3, 'COMPUTER APPLICATION CENTRE, HERITAGE INSTITUTE OF TECHNOLOG', 'self', 'West Bengal University of Technology', '994, MADURDAHA, CHOWBAGA ROAD, ANANDAPUR, P.O. EAST KOLKATA TOWNSHIP', 4, 4, 4, '2147483647', '0', 'www.heritageit.edu', 'info@heritageit.edu'),
(4, 'INSTITUTE OF SCIENCE AND TECHNOLOGY', 'self', 'West Bengal University of Technology', 'DHURABILA, DHAMKURIA, CHANDRAKONA TOWN, PASCHIM MEDINIPUR', 4, 4, 4, '2147483647', '123456', 'istonline.org.in', 'admission@istonline.org.in'),
(5, 'PAILAN COLLEGE OF MANAGEMENT & TECHNOLOGY', 'self', 'West Bengal University of Technology', 'BENGAL PAILAN PARK, PHASE I, AMGACHIA ROAD, JOKA (OFF DIAMOND HOURBOUR ROAD), KOLKATA - 700 104', 4, 4, 4, '2147483647', '0', 'www.pcmt-india.net', 'pcmt@pcmt-india.net'),
(6, 'TECHNO INDIA', 'self', 'West Bengal University of Technology', 'EM - 4/1, SECTO - V, SALT LAKE, KOLKATA - 91', 4, 4, 4, '2147483647', '0', 'www.ticollege.ac.in', 'admission@technoindiagroup.com'),
(7, 'BENGAL INSTITUTE OF TECHNOLOGY', 'self', 'West Bengal University of Technology', 'BAMUNGHATA, ON BASANTI HIGHWAY, KOLKATA - 700 150', 4, 4, 4, '2147483647', '0', 'www.bitcollege.org', 'admission@technoindiagroup.com'),
(8, 'DSMS BUSINESS SCHOOL', 'self', 'West Bengal University of Technology', 'SHAHID SUKUMAR BANERJEE SARANI, BIDHANNAGAR', 4, 4, 4, '2147483647', '0', 'www.dsmsindia.com', 'admission@dsmsindia.com'),
(9, 'NARULA INSTITUTE OF TECHNOLOGY', 'self', 'West Bengal University of Technology', '81 NILGUNJ ROAD, AGARPARA, KOLKATA - 109 ', 4, 4, 4, '2147483647', '0', 'www.nit.ac.in', 'principal_nit@jisgroup.org'),
(10, 'RCC INSTITUTE OF INFORMATION TECHNOLOGY', 'semi_govt', 'West Bengal University of Technology', 'CANAL SOUTH ROAD, BELIAGHATA, KOLKATA - 700015,WEST BENGAL', 4, 4, 4, '2147483647', '0', 'www.rcciit.in', 'admission@rcciit.in'),
(11, 'HALDIA INSTITUTE OF TECHNOLOGY', 'self', 'West Bengal University of Technology', 'ICARE COMPLEX, HIT CAMPUS', 4, 4, 4, '2147483647', '0', 'www.hithaldia.in', 'admin@hithaldia.in'),
(12, 'SABITA DEVI EDUCATION TRUST - BRAINWARE GROUP OF INSTITUTION', 'self', 'West Bengal University of Technology', '398 RAMKRISHNAPUR ROAD, P.S. BARASAT', 4, 4, 4, '2147483647', '0', 'www.brainwaretechnologies.org', 'nfo@brainwaretechnologies.org'),
(13, 'NETAJI SUBHASH ENGINEERING COLLEGE', 'self', 'West Bengal University of Technology', 'TECHNO CITY, GARIA, KOLKATA - 152', 4, 4, 4, '2147483647', '0', 'www.nsecollege.org', 'director_nsec@yahoo.com'),
(14, 'DR. B. C. ROY ENGINEERING COLLEGE, DURGAPUR', 'self', 'West Bengal University of Technology', 'JEMUA ROAD, FULJHORE, DURGAPUR - 713 206', 4, 4, 4, '2147483647', '0', 'www.bcrec.ac.in', 'info@bcrec.ac.in'),
(15, 'BANKURA UNNAYANI INSTITUTE OF ENGINEERING', 'self', 'West Bengal University of Technology', 'AT : POHABAGAN, BHAGABANDH', 4, 4, 4, '2147483647', '0', 'www.buieonline.info', 'buie@in.com'),
(16, 'FUTURE INSTITUTE OF ENGINEERING AND MANAGEMENT', 'self', 'West Bengal University of Technology', 'SONARPUR STATION ROAD, DIST.  SOUTH 24 PRGS, KOLKATA', 4, 4, 4, '2147483647', '2147483647', 'www.futureengineering.in', 'info@futureengineering.in'),
(17, 'CAMELLIA INSTITUTE OF TECHNOLOGY', 'self', 'West Bengal University of Technology', 'DIGBERIA, BADU ROAD, MADHYAMGRAM, KOLKATA - 700 129', 4, 4, 4, '2147483647', '0', 'www.camelliait.ac.in', 'director@camelliait.ac.in'),
(18, 'TECHNO INDIA COLLEGE OF TECHNOLOGY', 'self', 'West Bengal University of Technology', 'MEGA CITY, NEW TOWN, KOLKATA', 4, 4, 4, '2147483647', '0', 'www.tictcollege.in', 'info@tictcollege.in'),
(19, 'REGENT EDUCATION & RESEARCH FOUNDATION GROUP  OF INSTITUTION', 'self', 'West Bengal University of Technology', 'BARA KANTHALIA, P.O: SEWLI TELINI PARA, P.S.: TITAGARH, NORTH 24 PARGANAS', 4, 4, 4, '2147483647', '0', 'www.rerf.in', 'rerfkolkata@gmail.com'),
(20, 'SEACOM ENGINEERING COLLEGE', 'self', 'West Bengal University of Technology', 'JALADHULAGOR, SANKRAIL, PO: ANDUL MOUR, HOWRAH-711302', 4, 4, 4, '2147483647', '2147483647', 'www.seacomengineering.org', 'principal@seacomengineering.org'),
(21, 'ACADEMY OF TECHNOLOGY', 'self', 'West Bengal University of Technology', 'G.T. ROAD, ADISAPTAGRAM, P.O: AEDCONAGAR, DIST-HOOGHLY, WEST BENGAL', 4, 4, 4, '2147483647', '0', 'www.aot.edu.in', 'academy@aot.edu.in'),
(22, 'ASANSOL ENGINEERING COLLEGE', 'self', 'West Bengal University of Technology', 'VIVEKANANDA SARANI, KANYAPUR', 4, 4, 4, '2147483647', '0', 'www.aecwb.net', 'aec@aecwb.edu.in'),
(23, 'GURUNANAK INSTITUTE OF TECHNOLOGY', 'self', 'West Bengal University of Technology', '157/F, NILGUNJ ROAD, PANIHATI, SODEPUR, KOLKATA-700114', 4, 4, 4, '2147483647', '0', 'www.gnit.ac.in', 'info@jisgroup.org'),
(24, 'B.P. PODDAR INSTITUTE OF MANAGEMENT & TECHNOLOGY', 'self', 'West Bengal University of Technology', '137, V.I.P. ROAD, KOLKATA - 700052', 4, 4, 4, '2147483647', '0', 'www.bppimt.ac.in', 'info@bppimt.ac.in'),
(25, 'MEGHNAD SAHA INSTITUTE OF TECHNOLOGY', 'self', 'West Bengal University of Technology', 'TECHNO COMPLEX, MADURDAHA, BESIDE NRI COMPLEX, UCHHEPOTA, KOLKATA-700150', 4, 4, 4, '2147483647', '0', 'www.msitcollege.org', 'info@msitcollege.org'),
(26, 'KALYANI GOVERNMENT ENGINEERING COLLEGE', 'self', 'West Bengal University of Technology', 'KALYANI UNIVERSITY CAMPUS KALYANI-741235, DIST. NADIA, WEST BENGAL, INDIA', 4, 4, 4, '2147483647', '0', 'www.kgec.edu.in', 'admission@kgec.edu.in'),
(27, 'MCKV INSTITUTE OF ENGINEERING', 'self', 'West Bengal University of Technology', '243, G.T. ROAD (NORTH)', 4, 4, 4, '2147483647', '2147483647', 'www.mckvie.edu.in', 'admission@mckvie.edu.in'),
(28, 'GREATER KOLKATA COLLEGE OF ENGINEERING & MANAGEMENT', 'self', 'West Bengal University of Technology', 'DUDHNAI, RAMNAGAR-II, P.S. BARUIPUR, 24 PARGANAS (SOUTH)', 4, 4, 4, '2147483647', '214748364', 'www.gkcem.ac.in', 'principal_gkcem@jisgroup.org'),
(29, 'SILIGURI INSTITUTE OF TECHNOLOGY', 'self', 'West Bengal University of Technology', 'HILL CART ROAD, SALBARI, SUKNA, SILIGURI, DARJEELING', 4, 4, 4, '2147483647', '2147483647', 'www.sittechno.org', 'sittechno@rediffmail.com'),
(30, 'CALCUTTA INSTITUTE OF TECHNOLOGY', 'self', 'West Bengal University of Technology', 'BANITABLA, ULUBERIA, HOWRAH - 711 316', 4, 4, 4, '2147483647', '2147483647', 'bciedu.org/colleges/cit.php', 'cit@bciedu.co.in'),
(31, 'JIS COLLEGE OF ENGINEERING', 'self', 'West Bengal University of Technology', 'BLOCK - A, PHASE - III, KALYANI, NADIA', 4, 4, 4, '2147483647', '2147483647', 'www.jiscollege.ac.in', 'info@jisgroup.org '),
(32, 'MANAGEMENT INSTITUTE OF DURGAPUR', 'self', 'West Bengal University of Technology', 'G.T. ROAD, RAJBANDH, DURGAPUR - 713 212, DIST. BURDWAN', 4, 4, 4, '2147483647', '2147483647', 'mid.rahul.ac.in', 'admission@rahul.ac.in '),
(33, 'ST. MARY''S TECHNICAL CAMPUS KOLKATA', 'self', 'West Bengal University of Technology', 'SAIBANA VILLAGE, OPP : WEST BENGAL STATE UNIVERSITY, ICHAPUR NILGUNGE GRAM PANCHAYAT, BARASAT, NORTH 24 PARGANAS DISTRICT, KOLKATA - 700 126', 4, 4, 4, '2147483647', '2147483647', 'www.stmarysgroup.com', 'admin@stmarysgroup.com'),
(34, 'Burdwan University', 'govt', 'University of Burdwan', 'Raiganj, Bardhaman, West Bengal 713101', 4, 4, 4, '2147483647', '0', 'www.buruniv.ac.in', 'admin@buruniv.ac.in'),
(35, 'Calcutta University', 'govt', 'Calcutta University', '87 /1, College St, Kolkata, West Bengal 700073', 4, 4, 4, '2147483647', '0', 'www.caluniv.ac.in', 'admin@caluniv.ac.in'),
(36, 'Jadavpur University', 'govt', 'Jadavpur University', 'Plot No.8, Salt Lake Bypass, LB Block, Sector III, Salt Lake City, Kolkata, West Bengal 700098', 4, 4, 4, '2147483647', '0', 'www.jaduniv.edu.in', 'admin@jaduniv.edu.in'),
(37, 'Kalyani University', 'govt', 'Kalyani University', 'Block C, University Of Kalyani, Kalyani, West Bengal 741245', 4, 4, 4, '2147483647', '0', 'www.klyuniv.ac.in', 'admin@klyuniv.ac.in'),
(38, 'North Bengal University', 'govt', 'North Bengal University', 'Post Raja Rammohanpur, Siliguri, Bairatisal, West Bengal 734013', 4, 4, 4, '2147483647', '0', 'www.nbu.ac.in', 'admin@nbu.ac.in'),
(39, 'Vidyasagar University', 'govt', 'Vidyasagar University', 'Vidyasagar University Rd, Rangamati, Medinipur, West Bengal 721102', 4, 4, 4, '2147483647', '0', 'www.vidyasagar.ac.in', 'admin@vidyasagar.ac.in');

-- --------------------------------------------------------

--
-- Table structure for table `college_login`
--

CREATE TABLE `college_login` (
  `college_id` varchar(30) NOT NULL,
  `clg_uid` varchar(255) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `last_login` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `college_login`
--

INSERT INTO `college_login` (`college_id`, `clg_uid`, `email`, `password`, `last_login`) VALUES
('1', 'bcoet_1', 'admission@sksgi.com', '827ccb0eea8a706c4c34a16891f84e7b', 'April 20, 2014, 9:39 pm IST'),
('2', 'ti-h_2', 'info@technoinfdiahooghly.org', '827ccb0eea8a706c4c34a16891f84e7b', 'April 20, 2014, 9:41 pm IST'),
('3', 'cachiot_3', 'info@heritageit.edu', '827ccb0eea8a706c4c34a16891f84e7b', 'April 20, 2014, 1:57 pm IST'),
('4', 'iosat_4', 'admission@istonline.org.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('5', 'pcomt_5', 'pcmt@pcmt-india.net', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('6', 'ti_6', 'admission@technoindiagroup.com', '827ccb0eea8a706c4c34a16891f84e7b', 'May 19, 2014, 6:56 pm IST'),
('7', 'biot_7', 'admission@technoindiagroup.com', '827ccb0eea8a706c4c34a16891f84e7b', 'May 19, 2014, 6:56 pm IST'),
('8', 'dbs_8', 'admission@dsmsindia.com', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('9', 'niot_9', 'principal_nit@jisgroup.org', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('10', 'rioit_10', 'admission@rcciit.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('11', 'hiot_11', 'admin@hithaldia.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('12', 'sdet-bgoi_12', 'nfo@brainwaretechnologies.org', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('13', 'nsec_13', 'director_nsec@yahoo.com', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('14', 'dbcrecd_14', 'info@bcrec.ac.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('15', 'buioe_15', 'buie@in.com', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('16', 'fioeam_16', 'info@futureengineering.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('17', 'ciot_17', 'director@camelliait.ac.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('18', 'ticot_18', 'info@tictcollege.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('19', 'rerfgoi_19', 'rerfkolkata@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('20', 'sec_20', 'principal@seacomengineering.or', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('21', 'aot_21', 'academy@aot.edu.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('22', 'aec_22', 'aec@aecwb.edu.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('23', 'giot_23', 'info@jisgroup.org', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('24', 'bpiomt_24', 'info@bppimt.ac.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('25', 'msiot_25', 'info@msitcollege.org', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('26', 'kgec_26', 'admission@kgec.edu.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('27', 'mioe_27', 'admission@mckvie.edu.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('28', 'gkcoem_28', 'principal_gkcem@jisgroup.org', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('29', 'siot_29', 'sittechno@rediffmail.com', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('30', 'ciot_30', 'cit@bciedu.co.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('31', 'jcoe_31', 'info@jisgroup.org ', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('32', 'miod_32', 'admission@rahul.ac.in ', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('33', 'smtck_33', 'admin@stmarysgroup.com', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('34', 'bu_34', 'admin@buruniv.ac.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('35', 'cu_35', 'admin@caluniv.ac.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('36', 'ju_36', 'admin@jaduniv.edu.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('37', 'ku_37', 'admin@klyuniv.ac.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('38', 'nbu_38', 'admin@nbu.ac.in', '827ccb0eea8a706c4c34a16891f84e7b', ''),
('39', 'vu_39', 'admin@vidyasagar.ac.in', '827ccb0eea8a706c4c34a16891f84e7b', '');

-- --------------------------------------------------------

--
-- Table structure for table `counselling_date`
--

CREATE TABLE `counselling_date` (
  `id` int(11) NOT NULL,
  `event` varchar(255) NOT NULL,
  `event_date` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `counselling_date`
--

INSERT INTO `counselling_date` (`id`, `event`, `event_date`) VALUES
(6, 'Round 1 counselling allotment will be available after', '20th may 2014'),
(8, 'new counselling for MBBS', '10-05-2023'),
(7, 'net councelling', '02/05/2023');

-- --------------------------------------------------------

--
-- Table structure for table `notice`
--

CREATE TABLE `notice` (
  `id` int(11) NOT NULL,
  `title` varchar(200) COLLATE latin1_general_ci NOT NULL,
  `subject` varchar(1000) COLLATE latin1_general_ci NOT NULL,
  `name` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `type` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `size` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `path` varchar(1000) COLLATE latin1_general_ci NOT NULL,
  `date` varchar(255) COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `notice`
--

INSERT INTO `notice` (`id`, `title`, `subject`, `name`, `type`, `size`, `path`, `date`) VALUES
(14, 'REPORTING CENTRES', 'REPORTING CENTRES', 'REPORTING CENTRES.pdf', 'application/pdf', '266898', 'http://admin.ecounselling.tk/files/upload/REPORTING CENTRES.pdf', ''),
(15, 'SEAT ALLOTMENT ALGORITHM', 'SEAT ALLOTMENT ALGORITHM', 'SEAT ALLOTMENT ALGORITHM.pdf', 'application/pdf', '223418', 'http://admin.ecounselling.tk/files/upload/SEAT ALLOTMENT ALGORITHM.pdf', ''),
(16, 'TENTATIVE COUNSELLING SCHEDULE', 'TENTATIVE COUNSELLING SCHEDULE', 'TENTATIVE COUNSELLING SCHEDULE.pdf', 'application/pdf', '176472', 'http://admin.ecounselling.tk/files/upload/TENTATIVE COUNSELLING SCHEDULE.pdf', ''),
(13, 'JECA_Vacancy_Status', 'JECA_Vacancy_Status', 'JECA_Vacancy_Status.pdf', 'application/pdf', '52066', 'http://admin.ecounselling.tk/files/upload/JECA_Vacancy_Status.pdf', ''),
(12, 'JECA_Counselling_Instruction', 'JECA_Counselling_Instruction', 'JECA_Counselling_Instruction.pdf', 'application/pdf', '494757', 'http://admin.ecounselling.tk/files/upload/JECA_Counselling_Instruction.pdf', ''),
(10, 'JECA 2010 Opening and Closing Rank', 'JECA 2010 Opening and Closing Rank', 'JECA 2010 Opening and Closing Rank.pdf', 'application/pdf', '212314', 'http://admin.ecounselling.tk/files/upload/JECA 2010 Opening and Closing Rank.pdf', ''),
(17, 'WITHDRAWAL PROCEDURE', 'WITHDRAWAL PROCEDURE', 'WITHDRAWAL PROCEDURE.pdf', 'application/pdf', '162483', 'http://admin.ecounselling.tk/files/upload/WITHDRAWAL PROCEDURE.pdf', '');

-- --------------------------------------------------------

--
-- Table structure for table `rank_details`
--

CREATE TABLE `rank_details` (
  `rank` decimal(30,0) NOT NULL,
  `enrolment_no` varchar(30) NOT NULL,
  `candidate_name` varchar(50) NOT NULL,
  `dob` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rank_details`
--

INSERT INTO `rank_details` (`rank`, `enrolment_no`, `candidate_name`, `dob`) VALUES
('3', '92603242', 'BRATATI BERA', '1992-10-30'),
('1', '92603404', 'MEGHALEE DEY', '1992-09-12'),
('2', '92606034', 'SHUBHRADEEP BHADRA', '1991-06-18'),
('4', '92603118', 'NIVEDITA ROYCHOWDHURY', '1990-01-12'),
('5', '92602203', 'RANAJIT SAHA', '1990-01-27'),
('6', '92603307', 'SOURAV GHOSH', '1992-11-22'),
('7', '92603112', 'POOJA GUPTA', '1992-07-01'),
('8', '92603109', 'ARINDAM CHATTERJEE', '1991-12-22'),
('9', '92101074', 'KOUSTAV MAITRA', '1991-08-04'),
('10', '92301149', 'NILADRI LAHIRY', '1991-10-04'),
('11', '92605278', 'ABHIJIT GHOSH', '1991-03-27'),
('12', '92603419', 'NILABHRO DATTA', '1992-03-05'),
('13', '92604380', 'SRIMAN SHARANYA SARKAR', '1991-11-23'),
('14', '92603211', 'SRITAM HAZRA', '1991-11-28'),
('15', '92201009', 'ANKITA SINGH', '1991-11-23'),
('16', '92604215', 'ASHES RANJAN NASKAR', '1990-11-27'),
('17', '92604297', 'SHATABDI MONDAL', '1991-12-20'),
('18', '92604349', 'SHRAMANA THAKUR', '1992-12-26'),
('19', '92604068', 'SANCHARI DAS', '1991-01-03'),
('20', '92201109', 'NITESH KUMAR JHA', '1993-10-01'),
('21', '92602077', 'PRASUN CHAKRABORTY', '1991-08-06'),
('22', '92001250', 'CHANDAN ROY', '1992-12-11'),
('23', '92603282', 'DEBENDRA NATH RANA', '1992-08-05'),
('24', '92701071', 'ABDUL AZIZ', '1991-11-16'),
('25', '92601186', 'YOGESH AGARWAL', '1989-12-02'),
('26', '92301294', 'ARIJIT DAS', '1992-10-01'),
('27', '92401288', 'AVIK GUHA', '1992-09-03'),
('28', '92605226', 'SOMNATH MUKHERJEE', '1992-01-06'),
('29', '92604266', 'RUCHIRA DE', '1992-01-16'),
('30', '92602047', 'POULAMI BAL', '1993-04-10'),
('32', '92601036', 'AVIK CHATTOPADHYAY', '1992-01-16'),
('33', '92602187', 'SUBHRAJYOTI SANTRA', '1993-02-19'),
('34', '92603131', 'LOPAMUDRA BANERJEE', '1990-07-14'),
('35', '92602174', 'ANINDITA GHOSH', '1993-03-23'),
('36', '92001290', 'TAMOJIT BISWAS', '1992-01-02'),
('37', '92301073', 'SHWETA GHOSH', '1992-01-19'),
('38', '92602212', 'RUMPA SADHUKHAN', '1992-09-16'),
('39', '92602109', 'ANJISHNU MONDAL', '1991-12-16'),
('40', '92603037', 'ANUJA NATH', '1991-11-19'),
('41', '92606110', 'SUMANA SUR', '1991-10-02'),
('42', '92401244', 'SAYAK DAS', '1992-10-29'),
('43', '92603406', 'GUNJAN SEKHON', '1991-11-04'),
('44', '92602030', 'SHAMIM AKTAR', '1992-09-13'),
('45', '92605200', 'DEBARGHYA MUKHERJEE', '1992-05-02'),
('46', '92603420', 'RUHIDAS ROY', '1992-10-01'),
('47', '92603091', 'JUHI BAROI', '1992-11-29'),
('48', '92605361', 'PRIYA BHAKAT', '1991-11-03'),
('49', '92602307', 'ARNAB KOLEY', '1990-11-09'),
('50', '92601114', 'SHIVAM DAS', '1992-04-13'),
('51', '92701065', 'BIPLAB SINGHA', '1989-01-26'),
('52', '92604216', 'SUSMITA CHAKRABORTY', '1991-09-09'),
('53', '92602005', 'KAUSHIK BISWAS', '1992-10-10'),
('54', '92501016', 'PRAJNA BHUNIA', '1989-12-15'),
('55', '92602204', 'AVIRUP SENGUPTA', '1991-01-29'),
('56', '92604141', 'ZEESHAN ANWAR', '1989-06-10'),
('57', '92001196', 'SHAHNAZ BILQUIS LUCKY', '1991-02-04'),
('58', '92605474', 'AADYA MASKARA', '1992-03-07'),
('59', '92605465', 'SWAGATO DEY', '1991-09-26'),
('60', '92606043', 'KOUSANI BASU', '1993-01-23'),
('61', '92602013', 'KAUSTABH RAHA', '1991-08-18'),
('62', '92605393', 'ANABIL BHATTACHARYA', '1990-12-29'),
('63', '92001064', 'PRITI', '1992-02-18'),
('64', '92605326', 'TRIDWIP DAS', '1990-03-21'),
('65', '92605017', 'TRIPTI DAS', '1991-02-20'),
('66', '92605172', 'AYARNISH SENGUPTA', '1991-08-26'),
('67', '92603017', 'RATHINDRA NATH DUTTA', '1992-12-20'),
('68', '92401246', 'SHWETA BARANWAL', '1992-12-14'),
('69', '92601056', 'MADHUPARNA DAS', '1992-01-22'),
('70', '92603400', 'ARITRA BANERJEE', '1993-02-18'),
('71', '92603407', 'CHIRANJEEB ROY CHOWDHURY', '1992-03-03'),
('72', '92603141', 'SHEIKH JHOHEV', '1992-10-07'),
('73', '92001252', 'SAYANTANI CHAKRABORTY', '1991-05-24'),
('74', '92602306', 'TAMANASH KAR', '1991-02-28'),
('75', '92501036', 'SK AZAHAR UDDIN', '1989-03-22'),
('76', '92605008', 'MD ASIM', '1991-02-20'),
('77', '92605204', 'SUMIT BISWAS', '1990-10-15'),
('78', '92602241', 'RAJARSHI DAS', '1991-11-01'),
('79', '92301221', 'GURINDER PREET SINGH', '1992-03-01'),
('80', '92602197', 'SUMAN KUMBHAKAR', '1993-05-14'),
('81', '92601099', 'SUVADIP ROY', '1993-05-02'),
('82', '92601075', 'AVISHEK ROYCHOUDHURY', '1992-10-18'),
('83', '92101056', 'SRIMOYEE MUKHERJEE', '1992-08-14'),
('84', '92602149', 'SOUMYA SARKAR', '1992-04-24'),
('85', '92301007', 'ATREYI DAS', '1993-03-05'),
('86', '92603274', 'MOURJO SEN', '1992-06-09'),
('87', '92602185', 'HASEM ALI', '1992-10-14'),
('88', '92201103', 'AMIT KUMAR SARKAR', '1992-03-04'),
('89', '92603128', 'SUMANA BISWAS', '1993-04-09'),
('90', '92601079', 'Arnab Nandy', '1990-07-18'),
('91', '92601088', 'Samir Bagchi', '1992-08-19'),
('31', '92603103', 'ANKITA SARKAR', '1991-08-05'),
('31', '92603103', 'ANKITA SARKAR', '1991-08-05');

-- --------------------------------------------------------

--
-- Table structure for table `seat_allotments`
--

CREATE TABLE `seat_allotments` (
  `rank` int(25) NOT NULL,
  `allot_clg_id` int(25) NOT NULL,
  `pref_clg` int(3) NOT NULL,
  `seqnc_no` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `upgrd_sts` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `admited` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `active` varchar(255) COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `seat_allotments`
--

INSERT INTO `seat_allotments` (`rank`, `allot_clg_id`, `pref_clg`, `seqnc_no`, `upgrd_sts`, `admited`, `active`) VALUES
(1, 1, 1, '782204680', 'N', '', ''),
(2, 6, 1, '1961793558', 'N', '', ''),
(3, 1, 1, '511671246', 'N', '', ''),
(4, 6, 1, '1509208298', 'N', '', ''),
(5, 1, 1, '969179856', 'N', '', ''),
(6, 11, 1, '29226411', 'N', '', ''),
(7, 25, 1, '1502859317', 'N', '', ''),
(8, 11, 1, '381832264', 'N', '', ''),
(9, 10, 1, '932589325', 'N', '', ''),
(10, 3, 1, '1897600976', 'N', '', ''),
(11, 1, 1, '383192441', 'N', '', ''),
(12, 25, 1, '312795594', 'N', '', ''),
(13, 7, 1, '789214657', 'N', '', ''),
(14, 3, 1, '1583303126', 'N', '', ''),
(15, 3, 1, '1362870260', 'N', '', ''),
(16, 4, 1, '1994621280', 'N', '', ''),
(17, 4, 2, '219027563', 'Y', '', 'Y'),
(18, 9, 1, '1416916618', 'N', '', ''),
(19, 2, 1, '1366285903', 'N', '', ''),
(20, 6, 2, '1100544984', '', '', 'Y'),
(21, 2, 1, '614078329', 'N', '', ''),
(22, 6, 1, '68009841', 'N', '', ''),
(23, 3, 2, '739256738', '', '', 'Y'),
(24, 10, 1, '706348993', 'N', '', ''),
(25, 14, 2, '726511897', '', '', 'Y'),
(26, 5, 2, '1708053650', '', '', 'Y'),
(27, 8, 1, '1776516747', 'N', '', ''),
(28, 10, 1, '1824032699', 'N', '', ''),
(29, 13, 1, '1081463074', 'N', '', ''),
(30, 7, 2, '428811077', '', '', 'Y'),
(31, 21, 1, '1540989505', 'N', '', ''),
(32, 19, 1, '485088834', 'N', '', ''),
(33, 14, 1, '1273471647', 'N', '', ''),
(34, 4, 1, '40657275', 'N', '', ''),
(35, 9, 1, '2081341524', 'N', '', ''),
(36, 19, 1, '217901864', 'N', '', ''),
(37, 9, 1, '816739619', 'N', '', ''),
(38, 7, 1, '955573316', 'N', '', ''),
(39, 19, 1, '563630085', 'N', '', ''),
(40, 12, 1, '105558857', 'N', '', ''),
(41, 9, 1, '1401850227', 'N', '', ''),
(42, 17, 1, '1719616760', 'N', '', ''),
(43, 5, 1, '2074693591', 'N', '', ''),
(44, 10, 1, '1320757921', 'N', '', ''),
(45, 19, 1, '809689694', 'N', '', ''),
(46, 14, 1, '1824992843', 'N', '', ''),
(47, 7, 1, '2018459986', 'N', '', ''),
(48, 12, 1, '1737085722', 'N', '', ''),
(49, 12, 2, '1737872218', '', '', 'Y'),
(50, 11, 1, '1580066834', 'N', '', ''),
(90, 4, 3, '762090387', '', '', 'Y');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_login`
--
ALTER TABLE `admin_login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `candidate_details`
--
ALTER TABLE `candidate_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `college_details`
--
ALTER TABLE `college_details`
  ADD PRIMARY KEY (`college_cuid`);

--
-- Indexes for table `counselling_date`
--
ALTER TABLE `counselling_date`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notice`
--
ALTER TABLE `notice`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_login`
--
ALTER TABLE `admin_login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `candidate_details`
--
ALTER TABLE `candidate_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;
--
-- AUTO_INCREMENT for table `counselling_date`
--
ALTER TABLE `counselling_date`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `notice`
--
ALTER TABLE `notice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
