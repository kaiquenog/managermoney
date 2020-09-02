create table pessoa(   codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                       nome varchar(100) not null,
                       ativo boolean,
                       logradouro varchar(100),
                       numero varchar(10),
                       complemento varchar(100),
                       bairro varchar(100),
                       cep varchar(100),
                       cidade varchar(100),
                       estado varchar(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('Kaique',true, 'rua abc','123','apartamento', 'centro', '0909090-050', 'Sao Paulo', 'SP');

insert into pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('Maria',true, 'rua abc','321','apartamento', 'centro', '0909090-050', 'Sao Paulo', 'SP');

insert into pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('Joao',true, 'rua abc','987','casa', 'centro', '0909090-050', 'Sao Paulo', 'SP');

insert into pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('Jose',true, 'rua abc','456','casa', 'centro', '0909090-050', 'Sao Paulo', 'SP');
