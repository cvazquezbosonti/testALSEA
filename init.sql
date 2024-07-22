CREATE TABLE IF NOT EXISTS usuarios(
id_user BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nombre varchar(50),
telefono int,
codigo varchar(50),
mail varchar(100),
fecha_registro datetime,
fecha_actualizacion datetime
);




CREATE TABLE IF NOT EXISTS paises(
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
codigo varchar(5),
nombre varchar(50),
utc varchar (6)
);

INSERT INTO paises (codigo,nombre,utc) values('CL','Chile','UTC-04');
INSERT INTO paises (codigo,nombre,utc) values('COL','Colombia','UTC-05');
INSERT INTO paises (codigo,nombre,utc) values('MX','mexico','UTC-06');



