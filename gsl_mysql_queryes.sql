use gsl_cliente
desc tb_cliente
select * from tb_cliente
select * from tb_endereco

drop table tb_endereco
drop table tb_cliente
truncate table tb_endereco
truncate table tb_cliente

INSERT INTO tb_cliente (id, cnpj, razao_Social, nome_Comercial, email, telefone, inscricao_Estadual, status, data_Inclusao, endereco_Id_Fk) VALUES (1, 20243276000108, 'Farmacia 123 Ltda', 'Farmacia 123', 'farm123@gmail.com', '(11)96423-6666', '325.818.055.890', 'A', STR_TO_DATE('20210901 000000','%Y%m%d %H%i%s'), 1);
-- ------------------------------------------------------
use gsl_user
select * from tb_user
select * from tb_role
select * from tb_user_role
