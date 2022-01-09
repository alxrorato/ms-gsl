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
-- Lista usuarios e suas roles
select u.email, r.role_name
  from tb_user u, tb_role r, tb_user_role ur
 where ur.user_id = u.id
   and ur.role_id = r.id
order by 1, 2
-- ---------------------------------------------------
use gsl_entrega
select * from tb_carga
select * from tb_endereco
select count(1) from tb_entrega
select * from tb_entrega where codigo_solicitacao = 402346983175
select * from tb_entrega where cnpj_cliente = 80200396000150
update tb_entrega set status_pagamento = 1 where codigo_solicitacao = 402346983175
commit;
select * from tb_entrega_cargas
truncate table tb_entrega_cargas
truncate table tb_entrega_cargas
truncate table tb_entrega
truncate table tb_carga
select * from tb_entrega


