INSERT INTO usuario (id, instante_cadastro, login, senha) VALUES (1,"2021-05-25 17:58:28","abcd@email.com","$2a$10$vAwqlAuT4dPFvAQK3KBlbuMRlgNinoXOtan8S5XmWud9/5yVWXls.");
INSERT INTO categoria (id, nome) VALUES (1,"Categoria 1");
INSERT INTO produto (id, descricao, instante_cadastro, nome, qtd_disponivel, valor, categoria_id, usuario_id) VALUES (1, "Descrição do Produto 1", "2021-05-25 17:58:28", "Nome do Produto 1", 12, 34.00, 1,1);
INSERT INTO caracteristica_produto (id, descricao, nome, produto_id) VALUES (1, "Descrição da Característica do Produto 1", "Nome da Característica do Produto 1",1);
INSERT INTO caracteristica_produto (id, descricao, nome, produto_id) VALUES (2, "Descrição da Característica do Produto 2", "Nome da Característica do Produto 2",1);
INSERT INTO caracteristica_produto (id, descricao, nome, produto_id) VALUES (3, "Descrição da Característica do Produto 3", "Nome da Característica do Produto 3",1);
