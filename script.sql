/*Script banco de dados MySQL*/

use fornecedor;
insert into info_fornecedor (endereco, estado, nome)
values ('Endereço fornecedor de Goias', 'GO', 'Fornecedor-GO');

insert into produto (descricao, estado, nome, preco)
values ('Rosas', 'GO', 'Rosas', 2), 
('Orquidea', 'df', 'Orquidea', 25),
('Girassol', 'GO', 'Girassol', 30);