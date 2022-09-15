CREATE TABLE departamentos (
	id int auto_increment not null,
	departamento_codigo varchar(200) not null,
	departamento_nombre varchar(200) not null,
	fecha_hora_crea varchar(200) not null,
	fecha_hora_modifica varchar(200),
	
	PRIMARY KEY (id)
);

CREATE TABLE empleados (

	id int auto_increment not null,
	documento_tipo varchar(200) not null,
	documento_numero varchar(200) not null,
	nombres varchar(200) not null,
	apellidos varchar(200) not null,
	departamentos_id int not null,
	ciudad varchar(200) not null,
	dirección varchar(200) not null,
	correo_electrónico varchar(200) not null,
	telefono varchar(200) not null,
	fecha_hora_crea varchar(200) not null,
	fecha_hora_modifica varchar(200),
	
	PRIMARY KEY (id),
	foreign key (departamentos_id) references departamentos(id)
);