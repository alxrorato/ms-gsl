INSERT INTO tb_cliente (cnpj, razao_Social, nome_Comercial, email, endereco, telefone, status, data_Inclusao, data_Exclusao) VALUES (65335780000176, 'Supermercado ABC Ltda.', 'Supermercado ABC', 'abc@gmail.com', 'Rua aaaaa 333);
INSERT INTO tb_entrega (codigo_Solicitacao, codigo_Cliente, endereco_Origem, endereco_Destino, descricao_Carga, data_Solicitacao, data_Previsao, data_Conclusao, status_Entrega, data_Alteracao, data_Exclusao, valor, status_Pagamento, observacoes) VALUES (2, 100, 'Rua ddd 1 - São paulo', 'Av. eee 2 - Fortaleza', 'Dipirona - qtde 1 - peso 50g - valor: 20.00 - Nota Fiscal 3333', '20210901 100000', '20210907', null, '1', null, null, 1500.0, '0', null);

cnpj, razao_Social, nome_Comercial, email, endereco, telefone, status, data_Inclusao, data_Exclusao

	private Long cnpj,
	private String razaoSocial,
	private String nomeComercial,
	private String email,
	private String endereco,
	private String telefone,
	private String status,
	private Date dataInclusao;
	