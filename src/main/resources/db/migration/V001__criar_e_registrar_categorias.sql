CREATE TABLE categoria (
                           codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                           nome   VARCHAR(50) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into categoria (nome) values ('LAZER');
insert into categoria (nome) values ('FAMARCIA');
