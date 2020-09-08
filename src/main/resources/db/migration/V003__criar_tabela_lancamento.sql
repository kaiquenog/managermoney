CREATE TABLE lancamento(
                           codigo BIGINT(20) primary key  auto_increment,
                           descricao varchar(50),
                           data_vencimento date not null,
                           data_pagamento date,
                           valor decimal(10,2) not null,
                           observacao varchar(100) not null,
                           tipo varchar(20) not null,
                           codigo_categoria bigint(20) not null,
                           codigo_pessoa bigint(20) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE lancamento  ADD CONSTRAINT `fk_lancamento_pessoa`
    FOREIGN KEY (`codigo_pessoa`) REFERENCES pessoa(`codigo`) ON UPDATE CASCADE ON DELETE NO ACTION;

ALTER TABLE lancamento  ADD CONSTRAINT `fk_lancamento_categoria`
    FOREIGN KEY (`codigo_categoria`) REFERENCES categoria(`codigo`) ON UPDATE CASCADE ON DELETE NO ACTION;


insert into lancamento ( descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
values ('Nota 1', sysdate(), null, 199, 'Nota 1', 'DEBITO', 1, 1);
