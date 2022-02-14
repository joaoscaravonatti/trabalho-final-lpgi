-- MySQL Script generated by MySQL Workbench
-- seg 19 nov 2018 20:55:10 -02
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`paciente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `endereco` VARCHAR(45) NULL,
  `sexo` VARCHAR(45) NULL,
  `telefone` VARCHAR(45) NULL,
  `cidade` VARCHAR(45) NULL,
  `bairro` VARCHAR(45) NULL,
  `cep` VARCHAR(45) NULL,
  `dataNascimento` DATE NULL,
  `estadoCivil` VARCHAR(45) NULL,
  `profissao` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `indicacao` VARCHAR(45) NULL,
  `esporte` VARCHAR(45) NULL,
  `calcado` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`problemaClinico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`problemaClinico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`historiaClinica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`historiaClinica` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cirurgia` VARCHAR(45) NULL,
  `medicamento` VARCHAR(45) NULL,
  `alergia` VARCHAR(45) NULL,
  `dor` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`exameFisico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`exameFisico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `unhas` VARCHAR(45) NULL,
  `observacao1` VARCHAR(45) NULL,
  `observacao2` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`problemaFisico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`problemaFisico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`historia_problema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`historia_problema` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idHistoria` INT NULL,
  `idProblemaClinico` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `idProblema_idx` (`idProblemaClinico` ASC),
  INDEX `idHistoria_idx` (`idHistoria` ASC),
  CONSTRAINT `idHistoria`
    FOREIGN KEY (`idHistoria`)
    REFERENCES `mydb`.`historiaClinica` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idProblema`
    FOREIGN KEY (`idProblemaClinico`)
    REFERENCES `mydb`.`problemaClinico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`exame_problema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`exame_problema` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idExame` INT NULL,
  `idProblemaFisico` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `idExame_idx` (`idExame` ASC),
  INDEX `idProblema_idx` (`idProblemaFisico` ASC),
  CONSTRAINT `idExame`
    FOREIGN KEY (`idExame`)
    REFERENCES `mydb`.`exameFisico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idProblemaFisico`
    FOREIGN KEY (`idProblemaFisico`)
    REFERENCES `mydb`.`problemaFisico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`anamnese`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`anamnese` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPaciente` INT NULL,
  `idHistoriaClinica` INT NULL,
  `idExameFisico` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `idPaciente_idx` (`idPaciente` ASC),
  INDEX `idHistoriaClinica_idx` (`idHistoriaClinica` ASC),
  INDEX `idExameFisico_idx` (`idExameFisico` ASC),
  CONSTRAINT `idPaciente`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `mydb`.`paciente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idHistoriaClinica`
    FOREIGN KEY (`idHistoriaClinica`)
    REFERENCES `mydb`.`historiaClinica` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idExameFisico`
    FOREIGN KEY (`idExameFisico`)
    REFERENCES `mydb`.`exameFisico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`tratamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tratamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idAnamnese` INT NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `idAnamnese_idx` (`idAnamnese` ASC),
  CONSTRAINT `idAnamnese`
    FOREIGN KEY (`idAnamnese`)
    REFERENCES `mydb`.`anamnese` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`procedimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`procedimento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `preco` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`procedimento_tratamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`procedimento_tratamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idProcedimento` INT NULL,
  `idTratamento` INT NULL,
  `data` DATE NULL,
  `observacao` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `idProcedimento_idx` (`idProcedimento` ASC),
  INDEX `idTratamento_idx` (`idTratamento` ASC),
  CONSTRAINT `idProcedimento`
    FOREIGN KEY (`idProcedimento`)
    REFERENCES `mydb`.`procedimento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idTratamento`
    FOREIGN KEY (`idTratamento`)
    REFERENCES `mydb`.`tratamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`problemaClinico`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'diabetes');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'colesterol');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'hipertensão');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'cardiopatia');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'osteoporose');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'hepatite');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'trombose');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'artrose');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'hipotireoidismo');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'tabagista');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'etilista');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'hipertireoidismo');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'marcapasso');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'convulsao');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'antecedentes cancerígenos');
INSERT INTO `mydb`.`problemaClinico` (`id`, `nome`) VALUES (DEFAULT, 'problemas circulatórios');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`problemaFisico`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'onicomicose');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'granuloma');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'paroníquea');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'onicogrifose');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'onicolise');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'hematoma subungueal');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'onicocriptose');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'onicofose');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'unhas normais');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'calos');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'calosidades');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'hiperqueratose');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'verruga plantar');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'fissuras');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'úlcera');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'anidrose');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'hiperidrose');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'bromidrose');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'tínea interdigital');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'disidrose');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'psoríase');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'outras');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'bolhas');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'cianose');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'edema');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'eritema');
INSERT INTO `mydb`.`problemaFisico` (`id`, `nome`) VALUES (DEFAULT, 'umidade interdigital');

COMMIT;

-- -----------------------------------------------------
-- Data for table `mydb`.`procedimento`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'onicocriptose', 100);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'onicocriptose bilateral', 150);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'calos', 30);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'calosidades', 40);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'onicomicose', 50);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'onicofose', 70);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'órtese ungueal', 40);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'tunga penetrans', 50);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'verruga plantar', 30);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'larva migrans', 50);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'tínea interdigital', 50);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'úlceras', 60);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'ulcerações', 60);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'algias idiopáticas', 50);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'knésio taping', 35);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'avaliação', 60);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'orientação', 60);
INSERT INTO `mydb`.`procedimento` (`id`, `nome`, `preco`) VALUES (DEFAULT, 'prevenção do pé diabético', 60);

COMMIT;

select * from tratamento,procedimento_tratamento,procedimento where tratamento.id = procedimento_tratamento.idTratamento
and procedimento.id = procedimento_tratamento.idProcedimento;

select * from procedimento_tratamento;

select paciente.nome,procedimento.nome,procedimento_tratamento.`data`,tratamento.`status`
from paciente,anamnese,tratamento,procedimento_tratamento,procedimento
where paciente.id = anamnese.idPaciente
and anamnese.id = tratamento.idAnamnese
and procedimento_tratamento.idProcedimento = procedimento.id
and procedimento_tratamento.idTratamento = tratamento.id
group by paciente.nome;

select paciente.nome,tratamento.id as 'id tratamento',procedimento.nome,procedimento_tratamento.id as 'pt id'
from paciente,anamnese,tratamento,procedimento_tratamento,procedimento
where paciente.id = anamnese.idPaciente
and anamnese.id = tratamento.idAnamnese
and procedimento_tratamento.idTratamento = tratamento.id
and procedimento_tratamento.idProcedimento = procedimento.id;

select max(tratamento.id),procedimento.nome,paciente.nome
from paciente,anamnese,tratamento,procedimento_tratamento,procedimento
where paciente.id = anamnese.idPaciente
and anamnese.id = tratamento.idAnamnese
and procedimento_tratamento.idTratamento = tratamento.id
and procedimento_tratamento.idProcedimento = procedimento.id;

-- drop database mydb;
-- calcular somatoria total dos tratamentos
select sum(procedimento.preco) 
from paciente,anamnese,tratamento,procedimento_tratamento,procedimento
where paciente.id = anamnese.idPaciente
and anamnese.id = tratamento.idAnamnese
and procedimento_tratamento.idTratamento = tratamento.id
and procedimento_tratamento.idProcedimento = procedimento.id
group by paciente.id;


select paciente.nome,procedimento.nome from paciente,anamnese,tratamento,procedimento_tratamento,procedimento
where paciente.id = anamnese.idPaciente
and anamnese.id = tratamento.idAnamnese
and procedimento_tratamento.idTratamento = tratamento.id
and procedimento_tratamento.idProcedimento = procedimento.id
order by procedimento_tratamento.id desc;

select * from procedimento_tratamento;

select procedimento_tratamento.id,paciente.nome,procedimento.nome,procedimento.preco,tratamento.id
from paciente,anamnese,tratamento,procedimento_tratamento,procedimento
where paciente.id = anamnese.idPaciente
and anamnese.id = tratamento.idAnamnese
and procedimento_tratamento.idTratamento = tratamento.id
and procedimento_tratamento.idProcedimento = procedimento.id;

select procedimento.nome,procedimento_tratamento.data from procedimento,tratamento,procedimento_tratamento
where procedimento.id = procedimento_tratamento.idProcedimento
and tratamento.id = procedimento_tratamento.idTratamento
and tratamento.id = 1;

SELECT problemaClinico.id,problemaClinico.nome FROM problemaClinico,anamnese,historia_problema,historiaClinica
WHERE anamnese.idHistoriaClinica = historiaClinica.id
AND historiaClinica.id = historia_problema.idHistoria
AND problemaClinico.id = historia_problema.idProblemaClinico
AND anamnese.id = 8;

select procedimento_tratamento.id as pcId,t1.id,paciente.nome,procedimento.nome,procedimento_tratamento.`data`,t1.`status`
from paciente,anamnese,tratamento as t1,procedimento_tratamento,procedimento
where paciente.id = anamnese.idPaciente and anamnese.id = t1.idAnamnese
and procedimento_tratamento.idProcedimento = procedimento.id
and procedimento_tratamento.idTratamento = t1.id
and procedimento_tratamento.id = 
(select max(procedimento_tratamento.id) from 
	procedimento_tratamento,tratamento as t2 where t2.id = procedimento_tratamento.idTratamento 
    and t1.id = procedimento_tratamento.idTratamento order by procedimento_tratamento.id desc limit 1)
order by procedimento_tratamento.id desc;