-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2023 at 10:55 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `localflexible`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `BenfitToDay` (IN `date` DATE, OUT `totalBenifit` DECIMAL(10,2))   BEGIN SELECT SUM((p.sellprice - p.costprice) * dp.quantity) INTO totalBenifit FROM Sale_detail dp JOIN products p ON dp.productcode = p.productcode JOIN salesinfo j ON dp.salesid = j.salesid WHERE DATE(j.date) = date;  
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Product_less_then` (IN `input_quantity` INT)   BEGIN
    SELECT *
    FROM products
    WHERE quantity < input_quantity;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SaleAmount` (IN `dateSalle` DATE, OUT `SaleAmount` INT)   BEGIN SELECT COUNT(*) INTO SaleAmount FROM salesinfo WHERE DATE(date) = dateSalle;  
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TopProduct` (IN `input_count` INT)   BEGIN

SELECT p.*
    FROM products p
    INNER JOIN (
        SELECT productcode
        FROM salledata
        GROUP BY productcode
        ORDER BY SUM(quantity) DESC
        LIMIT input_count
    ) AS top ON p.productcode = top.productcode ;
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TotalPayee` (IN `date` DATE, OUT `totalPaye` DECIMAL(10,2))   BEGIN SELECT SUM(total_paye) INTO totalPaye FROM salesinfo WHERE DATE(date) = date;  
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `caisse`
--

CREATE TABLE `caisse` (
  `caisseid` int(11) NOT NULL,
  `date` varchar(22) NOT NULL,
  `total_paye` double NOT NULL,
  `soldby` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `caisse`
--
 
-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `catid` int(11) NOT NULL,
  `catName` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customercode` varchar(45) NOT NULL,
  `fullname` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customercode`, `fullname`, `location`, `phone`) VALUES
('test', 'test', 'test', '000000'),
('inconue', 'inconue', 'inconue', '123456789');

-- --------------------------------------------------------

--
-- Stand-in structure for view `devisdata`
-- (See below for the actual view)
--
CREATE TABLE `devisdata` (
`salesid` int(11)
,`date` varchar(45)
,`customercode` varchar(45)
,`total_paye` double
,`recu` double
,`changeMony` double
,`soldby` varchar(45)
,`productcode` varchar(45)
,`quantity` double
,`isLoan` varchar(22)
,`detaild` int(11)
,`sellprice` int(33)
);

-- --------------------------------------------------------

--
-- Table structure for table `payloan`
--

CREATE TABLE `payloan` (
  `id` int(11) NOT NULL,
  `customercode` varchar(22) NOT NULL,
  `total_paye` double NOT NULL,
  `soldby` varchar(22) NOT NULL,
  `date` varchar(22) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payloan`
--
 
-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `productcode` varchar(45) NOT NULL,
  `productname` varchar(45) NOT NULL,
  `costprice` double NOT NULL,
  `quantity` double NOT NULL,
  `sellprice` double NOT NULL,
  `brand` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

 
-- --------------------------------------------------------

--
-- Stand-in structure for view `purchasedata`
-- (See below for the actual view)
--
CREATE TABLE `purchasedata` (
`purchaseID` int(11)
,`supplierName` varchar(45)
,`productname` varchar(45)
,`date` varchar(45)
,`quantity` int(11)
,`totalcost` double
);

-- --------------------------------------------------------

--
-- Table structure for table `purchaseinfo`
--

CREATE TABLE `purchaseinfo` (
  `purchaseID` int(11) NOT NULL,
  `suppliercode` varchar(45) NOT NULL,
  `productcode` varchar(45) NOT NULL,
  `date` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalcost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `salesdevis`
--

CREATE TABLE `salesdevis` (
  `salesid` int(11) NOT NULL,
  `date` varchar(45) NOT NULL,
  `customercode` varchar(45) NOT NULL,
  `total_paye` double NOT NULL,
  `recu` double NOT NULL,
  `changeMony` double NOT NULL,
  `soldby` varchar(45) NOT NULL,
  `isLoan` varchar(22) NOT NULL,
  `isTva` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `salesdevis`
--

 
-- --------------------------------------------------------

--
-- Table structure for table `salesinfo`
--

CREATE TABLE `salesinfo` (
  `salesid` int(11) NOT NULL,
  `date` varchar(45) NOT NULL,
  `customercode` varchar(45) NOT NULL,
  `total_paye` double NOT NULL,
  `recu` double NOT NULL,
  `changeMony` double NOT NULL,
  `soldby` varchar(45) NOT NULL,
  `isLoan` varchar(22) NOT NULL,
  `isTva` varchar(22) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `salesinfo`
--


-- --------------------------------------------------------

--
-- Table structure for table `sale_detail`
--

CREATE TABLE `sale_detail` (
  `salesid` int(11) NOT NULL,
  `productcode` varchar(45) NOT NULL,
  `quantity` double NOT NULL,
  `detaild` int(11) NOT NULL,
  `sellPrice` int(33) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sale_detail`
--


-- --------------------------------------------------------

--
-- Table structure for table `sale_devis_detail`
--

CREATE TABLE `sale_devis_detail` (
  `salesid` int(11) NOT NULL,
  `productcode` varchar(45) NOT NULL,
  `quantity` double NOT NULL,
  `detaild` int(11) NOT NULL,
  `sellprice` int(33) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sale_devis_detail`
--
 
-- --------------------------------------------------------

--
-- Stand-in structure for view `salledata`
-- (See below for the actual view)
--
CREATE TABLE `salledata` (
`salesid` int(11)
,`date` varchar(45)
,`customercode` varchar(45)
,`total_paye` double
,`recu` double
,`changeMony` double
,`soldby` varchar(45)
,`productcode` varchar(45)
,`quantity` double
,`isLoan` varchar(22)
,`detaild` int(11)
,`sellprice` int(33)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `salleraport`
-- (See below for the actual view)
--
CREATE TABLE `salleraport` (
`date` varchar(45)
,`total_paye` double
,`salesid` int(11)
,`soldby` varchar(45)
);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `suppliercode` varchar(45) NOT NULL,
  `fullname` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `mobile` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `userlogs`
--

CREATE TABLE `userlogs` (
  `username` varchar(45) NOT NULL,
  `in_time` varchar(45) NOT NULL,
  `out_time` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(200) NOT NULL,
  `usertype` varchar(45) NOT NULL,
  `canLoan` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `location`, `phone`, `username`, `password`, `usertype`, `canLoan`) VALUES
(1, 'deidine', 'atar', '49619609', 'deidine', 'deidine', 'ADMINISTRATOR', 1);

-- --------------------------------------------------------

--
-- Structure for view `devisdata`
--
DROP TABLE IF EXISTS `devisdata`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `devisdata`  AS SELECT `sin`.`salesid` AS `salesid`, `sin`.`date` AS `date`, `sin`.`customercode` AS `customercode`, `sin`.`total_paye` AS `total_paye`, `sin`.`recu` AS `recu`, `sin`.`changeMony` AS `changeMony`, `sin`.`soldby` AS `soldby`, `sldt`.`productcode` AS `productcode`, `sldt`.`quantity` AS `quantity`, `sin`.`isLoan` AS `isLoan`, `sldt`.`detaild` AS `detaild`, `sldt`.`sellprice` AS `sellprice` FROM (((`salesdevis` `sin` join `sale_devis_detail` `sldt` on(`sin`.`salesid` = `sldt`.`salesid`)) join `products` `p` on(`sldt`.`productcode` = `p`.`productcode`)) join `users` on(`sin`.`soldby` = `users`.`name`))  ;

-- --------------------------------------------------------

--
-- Structure for view `purchasedata`
--
DROP TABLE IF EXISTS `purchasedata`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `purchasedata`  AS SELECT `prcs`.`purchaseID` AS `purchaseID`, `sp`.`fullname` AS `supplierName`, `p`.`productname` AS `productname`, `prcs`.`date` AS `date`, `prcs`.`quantity` AS `quantity`, `prcs`.`totalcost` AS `totalcost` FROM ((`purchaseinfo` `prcs` join `products` `p` on(`prcs`.`productcode` = `p`.`productcode`)) join `suppliers` `sp` on(`prcs`.`suppliercode` = `sp`.`suppliercode`))  ;

-- --------------------------------------------------------

--
-- Structure for view `salledata`
--
DROP TABLE IF EXISTS `salledata`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `salledata`  AS SELECT DISTINCT `sin`.`salesid` AS `salesid`, `sin`.`date` AS `date`, `sin`.`customercode` AS `customercode`, `sin`.`total_paye` AS `total_paye`, `sin`.`recu` AS `recu`, `sin`.`changeMony` AS `changeMony`, `sin`.`soldby` AS `soldby`, `sldt`.`productcode` AS `productcode`, `sldt`.`quantity` AS `quantity`, `sin`.`isLoan` AS `isLoan`, `sldt`.`detaild` AS `detaild`, `sldt`.`sellPrice` AS `sellprice` FROM (((`salesinfo` `sin` join `sale_detail` `sldt` on(`sin`.`salesid` = `sldt`.`salesid`)) join `products` `p` on(`sldt`.`productcode` = `p`.`productcode`)) join `users` on(`sin`.`soldby` = `users`.`name`))  ;

-- --------------------------------------------------------

--
-- Structure for view `salleraport`
--
DROP TABLE IF EXISTS `salleraport`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `salleraport`  AS SELECT `slinf`.`date` AS `date`, `slinf`.`total_paye` AS `total_paye`, `slinf`.`salesid` AS `salesid`, `slinf`.`soldby` AS `soldby` FROM (`salesinfo` `slinf` join `users` on(`slinf`.`soldby` = `users`.`username`))  ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `caisse`
--
ALTER TABLE `caisse`
  ADD PRIMARY KEY (`caisseid`);

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`catid`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customercode`);

--
-- Indexes for table `payloan`
--
ALTER TABLE `payloan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customercode` (`customercode`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`productcode`),
  ADD UNIQUE KEY `productcode_UNIQUE` (`productcode`);

--
-- Indexes for table `purchaseinfo`
--
ALTER TABLE `purchaseinfo`
  ADD PRIMARY KEY (`purchaseID`),
  ADD KEY `productcode` (`productcode`),
  ADD KEY `suppliercode` (`suppliercode`);

--
-- Indexes for table `salesdevis`
--
ALTER TABLE `salesdevis`
  ADD PRIMARY KEY (`salesid`),
  ADD KEY `customercode` (`customercode`);

--
-- Indexes for table `salesinfo`
--
ALTER TABLE `salesinfo`
  ADD PRIMARY KEY (`salesid`),
  ADD KEY `customercode` (`customercode`);

--
-- Indexes for table `sale_detail`
--
ALTER TABLE `sale_detail`
  ADD PRIMARY KEY (`detaild`),
  ADD KEY `productcode` (`productcode`),
  ADD KEY `salesid` (`salesid`);

--
-- Indexes for table `sale_devis_detail`
--
ALTER TABLE `sale_devis_detail`
  ADD PRIMARY KEY (`detaild`),
  ADD KEY `productcode` (`productcode`),
  ADD KEY `salesid` (`salesid`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`suppliercode`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `caisse`
--
ALTER TABLE `caisse`
  MODIFY `caisseid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `catid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payloan`
--
ALTER TABLE `payloan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `purchaseinfo`
--
ALTER TABLE `purchaseinfo`
  MODIFY `purchaseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `salesdevis`
--
ALTER TABLE `salesdevis`
  MODIFY `salesid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT for table `salesinfo`
--
ALTER TABLE `salesinfo`
  MODIFY `salesid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=388;

--
-- AUTO_INCREMENT for table `sale_detail`
--
ALTER TABLE `sale_detail`
  MODIFY `detaild` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT for table `sale_devis_detail`
--
ALTER TABLE `sale_devis_detail`
  MODIFY `detaild` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `payloan`
--
ALTER TABLE `payloan`
  ADD CONSTRAINT `payloan_ibfk_1` FOREIGN KEY (`customercode`) REFERENCES `customers` (`customercode`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `purchaseinfo`
--
ALTER TABLE `purchaseinfo`
  ADD CONSTRAINT `purchaseinfo_ibfk_1` FOREIGN KEY (`productcode`) REFERENCES `products` (`productcode`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `purchaseinfo_ibfk_2` FOREIGN KEY (`suppliercode`) REFERENCES `suppliers` (`suppliercode`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `salesdevis`
--
ALTER TABLE `salesdevis`
  ADD CONSTRAINT `salesdevis_ibfk_1` FOREIGN KEY (`customercode`) REFERENCES `customers` (`customercode`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `salesinfo`
--
ALTER TABLE `salesinfo`
  ADD CONSTRAINT `salesinfo_ibfk_1` FOREIGN KEY (`customercode`) REFERENCES `customers` (`customercode`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sale_detail`
--
ALTER TABLE `sale_detail`
  ADD CONSTRAINT `sale_detail_ibfk_1` FOREIGN KEY (`productcode`) REFERENCES `products` (`productcode`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sale_detail_ibfk_2` FOREIGN KEY (`salesid`) REFERENCES `salesinfo` (`salesid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sale_devis_detail`
--
ALTER TABLE `sale_devis_detail`
  ADD CONSTRAINT `sale_detail_ssibfk_2` FOREIGN KEY (`salesid`) REFERENCES `salesdevis` (`salesid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sale_details_ibfk_1` FOREIGN KEY (`productcode`) REFERENCES `products` (`productcode`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
