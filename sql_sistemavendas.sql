CREATE TABLE Produtos (
  codprod INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  descricao VARCHAR(30)  NULL  ,
  preco NUMERIC(10,2)  NULL  ,
  unidade VARCHAR(2)  NULL  ,
  qtde_inicial NUMERIC(10,2)  NULL  ,
  data_cad VARCHAR(10)  NULL  ,
  qtde_atual NUMERIC(10,2)  NULL    ,
PRIMARY KEY(codprod));



CREATE TABLE Clientes (
  codcli INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  nome VARCHAR(40)  NULL  ,
  endereco VARCHAR(45)  NULL  ,
  bairro VARCHAR(20)  NULL  ,
  cidade VARCHAR(20)  NULL  ,
  cep VARCHAR(9)  NULL  ,
  uf VARCHAR(2)  NULL  ,
  email VARCHAR(30)  NULL  ,
  fone VARCHAR(10)  NULL  ,
  celular VARCHAR(10)  NULL    ,
PRIMARY KEY(codcli));



CREATE TABLE pedidos (
  codped INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Clientes_codcli INTEGER UNSIGNED  NOT NULL  ,
  codcliente INT  NULL  ,
  data_2 VARCHAR(12)  NULL    ,
PRIMARY KEY(codped)  ,
INDEX pedidos_FKIndex1(Clientes_codcli),
  FOREIGN KEY(Clientes_codcli)
    REFERENCES Clientes(codcli)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE itens_ped (
  Produtos_codprod INTEGER UNSIGNED  NOT NULL  ,
  pedidos_codped INTEGER UNSIGNED  NOT NULL  ,
  qtde NUMERIC(10,2)  NULL    ,
INDEX itens_ped_FKIndex1(pedidos_codped)  ,
INDEX itens_ped_FKIndex2(Produtos_codprod),
  FOREIGN KEY(pedidos_codped)
    REFERENCES pedidos(codped)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Produtos_codprod)
    REFERENCES Produtos(codprod)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);




