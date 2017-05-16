import java.sql.*;

public class ProdutoCirculandoDAO extends BancoDeDados{

	//Dizer individualmente quantos produtos de cada tipo cadastrado está em ciruculação
	//Atualizar quantidade de vendidos a cada ciruculacao
	
	//listar produtos por id de circulacao
	
	//remover produto de circulacao quando ele retornar ao estoque
	//remover produto de circulacao quando ele for vendido -- operacao produto vendido
	
	
	public boolean addProdutoCirculacao(Produto p, int qtd, Circulacao circ, Vendedor v)
	{
		ProdutoDAO banco = new ProdutoDAO();
		CirculacaoDAO c = new CirculacaoDAO();
		
		//Vendedor v = c.getCirculacaoVendedor(circ);
		
				
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO produto_circulando VALUES (NULL, " + qtd +", 0, " + c.getCirculacaoID(circ, v) + ", " + banco.getProdutoID(p) + ")");
			st.executeUpdate("UPDATE circulacao SET valor_total=" + circ.atualizaValorCirculacao(p, qtd) + "WHERE id=" + c.getCirculacaoID(circ, v));
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		ProdutoCirculandoDAO prod = new ProdutoCirculandoDAO();
		ProdutoDAO pp = new ProdutoDAO();
		Produto p = new Produto("MPX", 1, "Produto top.");
		CirculacaoDAO c = new CirculacaoDAO();
		Vendedor v = new Vendedor("gilberto", 10);
		
		Circulacao circ = c.getCirculacao(28);
		//pp.adicionarProduto(p);
		System.out.println(prod.addProdutoCirculacao(p, 2, circ, v));

	}

}
