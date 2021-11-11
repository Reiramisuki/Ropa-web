
-- Tienda de ropa
drop database if exists negocioWebRopa;
create database negocioWebRopa;
use negocioWebRopa;


drop table if exists detalles;
drop table if exists articulos;
drop table if exists facturas;
drop table if exists clientes;


create table clientes(
	id int auto_increment primary key,
	nombre varchar(20) not null,
	apellido varchar(20) not null,
	edad int,
	direccion varchar(50),
	email varchar(30),
	telefono varchar(25),
	tipoDocumento enum('DNI','LIBRETA_CIVICA','LIBRETA_ENROLAMIENTO','PASS'),
	numeroDocumento char(8)
);

alter table clientes 
	add constraint CK_clientes_edad
    check (edad>=18 and edad<=120); 

alter table clientes
	add constraint U_clientes_TipoNumero
	unique(tipoDocumento,numeroDocumento);

create table facturas(
	id int auto_increment primary key,
	letra enum('A','B','C'),
	numero int,
        fecha date,
	medioDePago enum('EFECTIVO','DEBITO','TARJETA'),
 	idCliente int not null
);

-- creamos la restriccion FK facturas idCliente
alter table facturas
	add constraint FK_facturas_idCliente
    foreign key(idCliente)
    references clientes(id);

alter table facturas
	add constraint CK_facturas_numero
    check (numero>0);


alter table facturas 
	add constraint U_facturas_LetraNumero
    unique(letra,numero);

create table articulos(
    id int auto_increment primary key,
    descripcion varchar(25) not null,
    tipo enum('CALZADO','ROPA'),
    color varchar(20),
    talle_num varchar(20),
    stock int,
    stockMin int,
    stockMax int,
    costo double not null,
    precio double,
    temporada enum('VERANO','OTOÑO','INVIERNO')
);

alter table articulos
	add constraint CK_articulos_stock
    check (stock>=0);

alter table articulos
	add constraint CK_articulos_stockMin
    check (stockMin>0);
    
alter table articulos
	add constraint CK_articulos_stockMax
    check (stockMax>=stockMin);
    
alter table articulos
	add constraint CK_articulos_costo
    check (costo>=0);

alter table articulos
	add constraint CK_articulos_precio
    check (precio>=costo);
 
create table detalles(
	id int auto_increment primary key,
	idArticulo int not null,
	idFactura int not null,
	precio double,
	cantidad int
);

alter table detalles
	add constraint U_detallesIdArtIdFact
    unique(idArticulo,idFactura);

alter table detalles
	add constraint U_detalles_precio
    check(precio>=0);   
   
alter table detalles
	add constraint U_detalles_cantidad
    check(cantidad>=0);
   
alter table detalles
	add constraint FK_detalles_Articulos
    foreign key(idArticulo)
   	references articulos(id);
   
alter table detalles
	add constraint FK_detalles_Facturas
    foreign key(idFactura)
   	references facturas(id);   











