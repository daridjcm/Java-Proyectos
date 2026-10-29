CREATE DATABASE IF NOT EXISTS universidad
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE universidad;

CREATE TABLE IF NOT EXISTS facultades (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  facultad VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS estudiantes (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  cod_estudiantil INT NOT NULL UNIQUE,
  cedula_ciudadania BIGINT NOT NULL UNIQUE,
  fecha_nacimiento DATE NOT NULL,
  edad INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  id_facultad INT NOT NULL,
  rama VARCHAR(150) NOT NULL,
  estado BOOLEAN NOT NULL DEFAULT TRUE,
  fecha_matricula DATE NOT NULL,
  CONSTRAINT fk_estudiante_facultad FOREIGN KEY (id_facultad)
    REFERENCES facultades(id)
);

INSERT INTO facultades (facultad)
SELECT 'Ingeniería' WHERE NOT EXISTS (SELECT 1 FROM facultades WHERE facultad='Ingeniería');
INSERT INTO facultades (facultad)
SELECT 'Ciencias Básicas' WHERE NOT EXISTS (SELECT 1 FROM facultades WHERE facultad='Ciencias Básicas');
INSERT INTO facultades (facultad)
SELECT 'Ciencias de la Educación' WHERE NOT EXISTS (SELECT 1 FROM facultades WHERE facultad='Ciencias de la Educación');
INSERT INTO facultades (facultad)
SELECT 'Ciencias de la Salud' WHERE NOT EXISTS (SELECT 1 FROM facultades WHERE facultad='Ciencias de la Salud');
INSERT INTO facultades (facultad)
SELECT 'Humanidades' WHERE NOT EXISTS (SELECT 1 FROM facultades WHERE facultad='Humanidades');
INSERT INTO facultades (facultad)
SELECT 'Ciencias Empresariales y Económicas' WHERE NOT EXISTS (SELECT 1 FROM facultades WHERE facultad='Ciencias Empresariales y Económicas');
