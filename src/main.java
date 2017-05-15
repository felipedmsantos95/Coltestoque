package parteCarlla;

public class main {
	
	ProdutoDAO produtod = new ProdutoDAO();
	EstoqueDAO estoqued = new EstoqueDAO();
	ProdutoVendidoDAO produtovendidod = new ProdutoVendidoDAO();
	
	// adicionando produto
	Produto p = new Produto(); //construtor
	produtod.adicionarProduto(p);
	int id_produto = produtod.getProdutoID(p);
	estoqued.adicionarProdutoEstoque(id_produto,p.quantidade);
	produtovendidod.adicionarProdutoVendido(id_produto,p.quantidade);
	
	//remover produto p
	int id_produto = produtod.getProdutoID(p);
	produtod.removerProduto(p);
	estoqued.removerProdutoEstoque(id_produto);
	produtovendidod.removerProdutoVendido(id_produto);
}
