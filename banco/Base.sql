create database banco;

create sequence sec_cliente;
create table tbl_cliente (
	id_cliente int8 primary key default nextval('sec_cliente'),
	nombre varchar(200) not null,
	genero varchar(20) not null,
	fecha_nacimiento date not null,
	edad varchar(3),
	identificacion varchar(10) not null,
	direccion varchar(200) not null,
	celular varchar(10) not null,
	clave text not null,
	estado boolean default true
);

create sequence sec_cuenta;
create table tbl_cuenta (
	id_cuenta int4 primary key default nextval('sec_cuenta'),
	id_cliente int8,
	numero_cuenta varchar(6) not null, 
	tipo_cuenta varchar(20) not null, 
	saldo_inicial numeric(10,2) not null, 
	estado boolean default true,
	constraint tbl_cuenta_id_cliente_fk FOREIGN KEY (id_cliente) REFERENCES tbl_cliente (id_cliente)
);

create or replace function proc_generar_numero_cuenta() 
returns trigger as $$
declare
    nuevo_numero VARCHAR(6);
begin
    select LPAD(COALESCE(MAX(CAST(numero_cuenta AS INTEGER)) + 1, 1)::TEXT, 6, '0')
    into nuevo_numero
    from tbl_cuenta
    where tipo_cuenta = new.tipo_cuenta;

    new.numero_cuenta = nuevo_numero;

    return new;
end;
$$ language plpgsql;

create trigger tgr_generar_numero_cuenta
before insert on tbl_cuenta
for each row
execute function proc_generar_numero_cuenta();

create or replace function proc_hash_clave() 
returns trigger as $$
begin
    new.edad := DATE_PART('year', AGE(new.fecha_nacimiento));
    new.clave = md5(new.clave);
    return new;
end;
$$ language plpgsql;

create trigger tgr_hash_clave
before insert on tbl_cliente
for each row
execute function proc_hash_clave();

create sequence sec_movimiento;
create table tbl_movimiento(
	id_movimiento int4 primary key default nextval('sec_movimiento'),
	id_cuenta int4,
	fecha_movimiento date default now(),
	hora_movimiento time default now(), 
	tipo_movimiento varchar(20) not null, 
	valor numeric(10,2) not null, 
	saldo numeric(10,2) not null,
	constraint tbl_cuenta_id_cuenta_fk FOREIGN KEY (id_cuenta) REFERENCES tbl_cuenta (id_cuenta)
);

create or replace view vta_cuenta_movimiento as
select
    e.id_cliente, 
	e.nombre, 
	e.identificacion,
	c.id_cuenta,
	c.numero_cuenta,
	c.tipo_cuenta,
	c.estado,
	c.saldo_inicial as saldo_disponible,
    m.id_movimiento ,
	m.fecha_movimiento ,
	m.hora_movimiento, 
	m.tipo_movimiento, 
	case when m.tipo_movimiento = 'RETIRO' 
	          then '-'|| m.valor :: text
			  else m.valor :: text
			  end as movimiento,
	m.saldo as saldo_inicial
	from tbl_cliente e
	left join tbl_cuenta c on c.id_cliente = e.id_cliente
	left join tbl_movimiento m on c.id_cuenta = m.id_cuenta