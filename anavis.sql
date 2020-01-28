-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Gen 28, 2020 alle 20:16
-- Versione del server: 10.4.11-MariaDB
-- Versione PHP: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `anavis`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `data`
--

CREATE TABLE `data` (
  `ID` int(11) NOT NULL,
  `data` varchar(10) NOT NULL,
  `ID_sede_avis` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `data`
--

INSERT INTO `data` (`ID`, `data`, `ID_sede_avis`) VALUES
(1, '07/02/2020', 1),
(2, '08/02/2020', 1),
(3, '10/02/2020', 2),
(4, '11/02/2020', 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `distanza`
--

CREATE TABLE `distanza` (
  `ID` int(11) NOT NULL,
  `ID_pronto_soccorso` int(11) NOT NULL,
  `ID_donatore` varchar(50) NOT NULL,
  `distanza` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `distanza`
--

INSERT INTO `distanza` (`ID`, `ID_pronto_soccorso`, `ID_donatore`, `distanza`) VALUES
(1, 1, 'francesca.verdi@unicam.it', 0),
(2, 1, 'luigi.bianchi@unicam.it', 28),
(3, 1, 'mario.rossi@unicam.it', 69),
(4, 2, 'francesca.verdi@unicam.it', 28),
(5, 2, 'luigi.bianchi@unicam.it', 0),
(6, 2, 'mario.rossi@unicam.it', 49);

-- --------------------------------------------------------

--
-- Struttura della tabella `donatore`
--

CREATE TABLE `donatore` (
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `sesso` varchar(2) NOT NULL,
  `via` varchar(50) DEFAULT NULL,
  `citta` varchar(50) NOT NULL,
  `cap` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `gruppo_sanguigno` varchar(3) NOT NULL,
  `disponibilita` int(11) NOT NULL,
  `ultima_prenotazione` varchar(10) DEFAULT NULL,
  `attivo` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `donatore`
--

INSERT INTO `donatore` (`nome`, `cognome`, `sesso`, `via`, `citta`, `cap`, `email`, `password`, `gruppo_sanguigno`, `disponibilita`, `ultima_prenotazione`, `attivo`) VALUES
('Francesca', 'Verdi', 'F', 'Pirandello, 23', 'Civitanova', 62012, 'francesca.verdi@unicam.it', 'francescaverdi', '0+', 1, '07/07/2019', 1),
('Luigi', 'Bianchi', 'M', 'Circonvallazione, 6', 'Macerata', 62100, 'luigi.bianchi@unicam.it', 'luigibianchi', 'B-', 0, '', 1),
('Mario', 'Rossi', 'M', 'Le Mosse, 11', 'Camerino', 62032, 'mario.rossi@unicam.it', 'mariorossi', 'A+', 1, '11/01/2020', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `ora`
--

CREATE TABLE `ora` (
  `ID` int(11) NOT NULL,
  `ora` varchar(10) NOT NULL,
  `prenotata` tinyint(1) NOT NULL DEFAULT 0,
  `ID_data` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `ora`
--

INSERT INTO `ora` (`ID`, `ora`, `prenotata`, `ID_data`) VALUES
(1, '14:30', 0, 1),
(2, '18:00', 0, 1),
(3, '09:30', 0, 4),
(4, '11:00', 0, 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `prenotazione`
--

CREATE TABLE `prenotazione` (
  `ID` int(11) NOT NULL,
  `ID_ora` int(11) NOT NULL,
  `ID_utente` varchar(50) NOT NULL,
  `eseguita` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `prenotazione`
--

INSERT INTO `prenotazione` (`ID`, `ID_ora`, `ID_utente`, `eseguita`) VALUES
(1, 1, 'francesca.verdi@unicam.it', 0),
(2, 2, 'mario.rossi@unicam.it', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `pronto_soccorso`
--

CREATE TABLE `pronto_soccorso` (
  `ID` int(11) NOT NULL,
  `nome_ospedale` varchar(50) NOT NULL,
  `via` varchar(50) NOT NULL,
  `citta` varchar(30) NOT NULL,
  `cap` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `attivo` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `pronto_soccorso`
--

INSERT INTO `pronto_soccorso` (`ID`, `nome_ospedale`, `via`, `citta`, `cap`, `email`, `password`, `attivo`) VALUES
(1, 'Pronto Soccorso Civitanova Marche', 'P. Ginevri, 1', 'Civitanova Marche', 62012, 'PS.civitanovamarche@unicam.it', 'PScivitanovamarche', 1),
(2, 'Pronto Soccorso Macerata', 'Santa Lucia, 2', 'Macerata', 62100, 'PS.macerata@unicam.it', 'PSmacerata', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `sede_avis`
--

CREATE TABLE `sede_avis` (
  `ID` int(11) NOT NULL,
  `nome_sede` varchar(50) NOT NULL,
  `via` varchar(50) NOT NULL,
  `citta` varchar(30) NOT NULL,
  `cap` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `attivo` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `sede_avis`
--

INSERT INTO `sede_avis` (`ID`, `nome_sede`, `via`, `citta`, `cap`, `email`, `password`, `attivo`) VALUES
(1, 'AVIS Macerata', 'G. Falcone, 8', 'Macerata', 62100, 'AVIS.macerata@unicam.it', 'AVISmacerata', 1),
(2, 'AVIS Civitanova Marche', 'P. Borsellino, 35', 'Civitanova Marche', 62012, 'AVIS.civitanovamarche@unicam.it', 'AVIScivitanovamarche', 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `appartiene` (`ID_sede_avis`);

--
-- Indici per le tabelle `distanza`
--
ALTER TABLE `distanza`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `pronto_soccorso` (`ID_pronto_soccorso`),
  ADD KEY `donatore` (`ID_donatore`);

--
-- Indici per le tabelle `donatore`
--
ALTER TABLE `donatore`
  ADD PRIMARY KEY (`email`);

--
-- Indici per le tabelle `ora`
--
ALTER TABLE `ora`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_data` (`ID_data`);

--
-- Indici per le tabelle `prenotazione`
--
ALTER TABLE `prenotazione`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `prenota` (`ID_ora`),
  ADD KEY `ha` (`ID_utente`);

--
-- Indici per le tabelle `pronto_soccorso`
--
ALTER TABLE `pronto_soccorso`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `sede_avis`
--
ALTER TABLE `sede_avis`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `data`
--
ALTER TABLE `data`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT per la tabella `distanza`
--
ALTER TABLE `distanza`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `ora`
--
ALTER TABLE `ora`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT per la tabella `prenotazione`
--
ALTER TABLE `prenotazione`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `pronto_soccorso`
--
ALTER TABLE `pronto_soccorso`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `sede_avis`
--
ALTER TABLE `sede_avis`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `data`
--
ALTER TABLE `data`
  ADD CONSTRAINT `appartiene` FOREIGN KEY (`ID_sede_avis`) REFERENCES `sede_avis` (`ID`);

--
-- Limiti per la tabella `distanza`
--
ALTER TABLE `distanza`
  ADD CONSTRAINT `donatore` FOREIGN KEY (`ID_donatore`) REFERENCES `donatore` (`email`),
  ADD CONSTRAINT `pronto_soccorso` FOREIGN KEY (`ID_pronto_soccorso`) REFERENCES `pronto_soccorso` (`ID`);

--
-- Limiti per la tabella `ora`
--
ALTER TABLE `ora`
  ADD CONSTRAINT `ora_ibfk_1` FOREIGN KEY (`ID_data`) REFERENCES `data` (`ID`);

--
-- Limiti per la tabella `prenotazione`
--
ALTER TABLE `prenotazione`
  ADD CONSTRAINT `ha` FOREIGN KEY (`ID_utente`) REFERENCES `donatore` (`email`),
  ADD CONSTRAINT `prenota` FOREIGN KEY (`ID_ora`) REFERENCES `ora` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
