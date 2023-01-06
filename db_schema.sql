-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 08, 2021 at 05:02 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `raf_msc`
--

-- --------------------------------------------------------

--
-- Table structure for table `articles`
--

CREATE TABLE `articles` (
  `id` int(11) NOT NULL,
  `category` varchar(100) NOT NULL,
  `title` text NOT NULL,
  `text` text NOT NULL,
  `tags` text NOT NULL,
  `visits` int(11) NOT NULL DEFAULT 0,
  `author` text NOT NULL,
  `date` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `articles`
--

INSERT INTO `articles` (`id`, `category`, `title`, `text`, `tags`, `visits`, `author`, `date`) VALUES
(1, 'kategorija27', 'asdasdpsdjlnasdnj ', 'jnjkasdjnadajknsd', '', 5, 'ja sam jas', 1630949754229),
(2, 'kategorija3', 'primeriniji', 'niasdnjadsn', '', 5, 'jovan', 1630949754229),
(3, 'kategorija3', 'naslov', 'ja sam ivan ja ja', '', 1, 'ivan', 1630949754100),
(4, 'kategorija3', 'naslov', 'ja sam ivan ja ja', '', 4, 'ivan', 1630949750000),
(5, 'kategorija3', 'naslov', 'ja sam ivan ja ja', '', 0, 'ivan', 5),
(6, 'kategorija3', 'naslov', 'ja sam ivan ja ja', '', 0, 'ivan', 4),
(7, 'kategorija3', 'naslov', 'ja sam ivan ja ja', '', 0, 'ivan', 6),
(8, 'kategorija3', 'naslov', 'ja sam ivan ja ja', '', 0, 'ivan', 7),
(9, 'kategorija3', 'naslov', 'ja sam ivan ja ja', '', 0, 'ivan', 122),
(10, 'kategorija3', 'naslov', 'ja sam ivan ja ja', '', 0, 'ivan', 120),
(11, 'kategorija3', 'naslov2', 'ja sam ivan ja ja', '', 0, 'ivan', 115),
(12, 'kategorija3', 'naslov2', 'ja sam ivan ja ja', '', 0, 'ivan', 116),
(13, 'kategorija3', 'naslov2', 'ja sam ivan ja ja', '', 0, 'ivan', 321),
(15, 'kategorija3', 'naslov2', 'ja sam ivan ja ja', '', 0, 'ivan', 25),
(16, 'kategorija3', 'naslov2', 'ja sam ivan ja ja', '', 0, 'ivan', 187),
(17, 'kategorija3', 'naslov2', 'ja sam ivan ja ja', '', 0, 'ivan', 13),
(18, '12345', 'Naslov', '12312415', 'ASDDSASADDSA,ASDDSSDA ASDA SSA DASD SA,SPORT', 0, 'jovan', 123);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `name` varchar(100) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`name`, `description`) VALUES
('12345', 'novi opis'),
('asdasddsa', 'saddsas as asd asd'),
('babasbdadasda', 'adsd dasdasd as das dasd '),
('Ivan', '123132'),
('Ivanova Kategorija', 'Ova kategorija je ivanova'),
('kategorija', 'aaaaaaaaaaaaaaaaaaaaaa'),
('kategorija17', 'opis'),
('kategorija27', '1234'),
('kategorija3', 'opis1');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `text` text NOT NULL,
  `author` text NOT NULL,
  `date` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE `tags` (
  `id` int(11) NOT NULL,
  `tagss` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`id`, `tagss`) VALUES
(1, 'asddsasaddsa'),
(2, 'asddssda asda ssa dasd sa'),
(3, 'sadsdaastgaga asd as asd'),
(4, 'agssdg swhg sh hf dhhdffhdfhhdf '),
(5, 'adsasdasdarvwervtwwevt'),
(6, 'sdasdafasgdsgda assds a'),
(7, 'asfgdagdagdgd sadad '),
(8, 'sport'),
(9, 'sport'),
(10, 'sport');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `email` text NOT NULL,
  `name` text NOT NULL,
  `surname` text NOT NULL,
  `type` text NOT NULL,
  `status` tinyint(1) NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`email`, `name`, `surname`, `type`, `status`, `password`) VALUES
('email3', 'jovan', 'zmaj', 'ADMIN', 1, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('email2', 'ivan', 'root', 'CONTENT_CREATOR', 1, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('email3@haha', 'ivan1', 'root', 'CONTENT_CREATOR', 1, 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category` (`category`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD KEY `id` (`id`);

--
-- Indexes for table `tags`
--
ALTER TABLE `tags`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD UNIQUE KEY `email` (`email`) USING HASH;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `articles`
--
ALTER TABLE `articles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `tags`
--
ALTER TABLE `tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `articles`
--
ALTER TABLE `articles`
  ADD CONSTRAINT `articles_ibfk_3` FOREIGN KEY (`category`) REFERENCES `categories` (`name`) ON UPDATE CASCADE;

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`id`) REFERENCES `articles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
