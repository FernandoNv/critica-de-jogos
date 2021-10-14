https://dev.mysql.com/downloads/

Download MySQL Community Server

Ultimas versoes:
- 5.5
- 5.6
- 5.7
- 8.0.12 (atual)

CREATE TABLE banco.usuario (
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
	login VARCHAR(50) NOT NULL,
    senha text NOT NULL,
    data_cadastro DATE NOT NULL,
    versao INT DEFAULT 0,
    CONSTRAINT usuario_pk PRIMARY KEY(id)
)
ENGINE = INNODB
CHARACTER SET utf8mb4;

CREATE TABLE banco.jogo (
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    data_lancamento DATE NOT NULL,
    descricao TEXT NULL,
    versao INT DEFAULT 0,
    CONSTRAINT jogo_pk PRIMARY KEY(id)
)
ENGINE = INNODB
CHARACTER SET utf8mb4;

CREATE TABLE banco.critica (
    id INT NOT NULL AUTO_INCREMENT,
    texto TEXT NOT NULL,
    nota SMALLINT NOT NULL,
    data_publicacao DATE NOT NULL,
    jogo_id INT NOT NULL,
    autor_id INT NOT NULL,
    versao INT DEFAULT 0,
    CONSTRAINT critica_pk PRIMARY KEY(id),
    CONSTRAINT critica_jogo_fk FOREIGN KEY(jogo_id) REFERENCES jogo(id),
    CONSTRAINT critica_autor_fk FOREIGN KEY(autor_id) REFERENCES usuario(id)
)
ENGINE = INNODB
CHARACTER SET utf8mb4;

CREATE TABLE banco.comentario(
    id INT NOT NULL AUTO_INCREMENT,
    texto TEXT NOT NULL,
    autor_id INT NOT NULL,
    critica_id INT NOT NULL,
    data_publicacao DATE NOT NULL,
    versao INT DEFAULT 0,
    CONSTRAINT comentario_pk PRIMARY KEY(id),
    CONSTRAINT comentario_autor_fk FOREIGN KEY(autor_id) REFERENCES usuario(id),
    CONSTRAINT comentario_critica_fk FOREIGN KEY(critica_id) REFERENCES critica(id)
)
ENGINE = INNODB
CHARACTER SET utf8mb4;

INSERT INTO usuario(NOME, LOGIN, SENHA, DATA_CADASTRO)
VALUES('Fernando Vieira', 'fernandonv', '123456789', curdate());
INSERT INTO usuario(NOME, LOGIN, SENHA, DATA_CADASTRO)
VALUES('Juliana Vieira', 'juliananv', '11223344', curdate());


INSERT INTO jogo(NOME, DATA_LANCAMENTO, DESCRICAO)
VALUES('FIFA 21', '2020/09/01', 'FIFA 21 - EA');
INSERT INTO jogo(NOME, DATA_LANCAMENTO, DESCRICAO)
VALUES('GTA V', '2013/09/01', 'GTA - ROCKSTAR');
INSERT INTO jogo(NOME, DATA_LANCAMENTO, DESCRICAO)
VALUES('The Last of US', '2013/09/01', 'The Last Of Us - Sony');
INSERT INTO jogo(NOME, DATA_LANCAMENTO, DESCRICAO)
VALUES('The Last of US: Part II', '2020/09/01', 'The Last Of Us - Sony');

INSERT INTO critica(texto, nota, data_publicacao, jogo_id, autor_id)
VALUES(
    'Bom jogo',
    '10',
    curdate(),
    '1',
    '1'
);

INSERT INTO critica(texto, nota, data_publicacao, jogo_id, autor_id)
VALUES(
    'Otimo jogo',
    '10',
    curdate(),
    '2',
    '2'
);

INSERT INTO critica(texto, nota, data_publicacao, jogo_id, autor_id)
VALUES(
    'Otimo jogo novamente',
    '10',
    curdate(),
    '3',
    '1'
);

INSERT INTO critica(texto, nota, data_publicacao, jogo_id, autor_id)
VALUES(
    'Otimo jogo novamente',
    '10',
    curdate(),
    '4',
    '1'
);

INSERT INTO comentario(texto, autor_id, critica_id, data_publicacao)
VALUES(
    'Curit muito',
    '2',
    '1',
    curdate()
);

INSERT INTO comentario(texto, autor_id, critica_id, data_publicacao)
VALUES(
    'Nao Curit muito',
    '1',
    '3',
    curdate()
);
