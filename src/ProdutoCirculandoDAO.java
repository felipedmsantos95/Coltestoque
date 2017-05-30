import java.sql.*;
import java.util.ArrayList;

public class ProdutoCirculandoDAO extends BancoDeDados{

	//Dizer individualmente quantos produtos de cada tipo cadastrado está em ciruculação
	
	//listar produtos por id de circulacao
	
	//remover produto de circulacao quando ele retornar ao estoque
	
	//Incrementar no estoque quantidade de produtos circulando
	/* acho que essa fun��o eh inutil
	public int extrairQuantidadeCirculandoEstoque(int produtoID)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT qtd_circulando FROM estoque WHERE estoque.produto_id =" + produtoID);
			
			if(rs.next()) return rs.getInt(1);
			else return 0;
		}
		catch(SQLException e)
		{
			return 0;
		}		
	}*/
	public int getProdutoCirculandoQuant(int id_produtocirculando)//Procura exatamente pelo objeto vendedor e retorna o id do espelho dele no banco
	{
		CirculacaoDAO circ = new CirculacaoDAO();
		ProdutoDAO prod = new ProdutoDAO();
		VendedorDAO vend = new VendedorDAO();
		
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("select qtd_circulando from produto_circulando where id =" +id_produtocirculando+";");
			
			if(rs.next())
			{
				return rs.getInt(1);
			}
			return 0;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}	
	
	//Vai retornar a primeira ocorrrencia onde a quantidade não é nula, pois ela ira decrementar na medida em que os produtos são vendidos
	public int getProdutoCirculandoID(Circulacao c, Produto p, Vendedor v)//Procura exatamente pelo objeto vendedor e retorna o id do espelho dele no banco
	{
		CirculacaoDAO circ = new CirculacaoDAO();
		ProdutoDAO prod = new ProdutoDAO();
		VendedorDAO vend = new VendedorDAO();
		
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("select produto_circulando.id,qtd_circulando from produto_circulando,circulacao where produto_circulando.circulacao_id =" +circ.getCirculacaoID(c, v) + " and produto_circulando.produto_id = " + prod.getProdutoID(p) + " and circulacao.vendedor_id = " + vend.getVendedorID(v));
			
			if(rs.next())
			{
				if(rs.getInt(2) > 0) return rs.getInt(1);
				
			}
			return 0;
		}
		catch(SQLException e)
		{
			return 0;
		}
				
	}
	//mudei aqui pro retira estoque
	public boolean addProdutoCirculacao(Produto p, int qtd, Circulacao circ, Vendedor v)
	{
		int id_produtocirculando=this.getProdutoCirculandoID(circ, p, v);
		if(id_produtocirculando<=0)
		{
			try
			{
				Statement st = conexao.createStatement();
				st.executeUpdate("INSERT INTO produto_circulando VALUES (NULL, " + qtd +", " + circ.getID() + ", " + p.getID() + ")");
				st.executeUpdate("UPDATE circulacao SET valor_total=" + circ.atualizaValorCirculacao(p, qtd) + "WHERE id=" + circ.getID());
				return true;
			}
			catch(SQLException e)
			{
				//System.out.println(e.getMessage());
				e.printStackTrace();
				return false;
			}
		}
		else
		{
			try
			{
				Statement st = conexao.createStatement();
				st.executeUpdate("UPDATE  produto_circulando SET qtd_circulando="+(this.getProdutoCirculandoQuant(id_produtocirculando)+qtd)+" WHERE id="+id_produtocirculando+";");
				st.executeUpdate("UPDATE circulacao SET valor_total=" + circ.atualizaValorCirculacao(p, qtd) + "WHERE id=" + circ.getID());
				return true;
			}
			catch(SQLException e)
			{
				//System.out.println(e.getMessage());
				e.printStackTrace();
				return false;
			}
		}
		
	}
	
	public boolean updateQuantCirculacao(int id_produto, int id_circulacao , int novaquant)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE  produto_circulando SET qtd_circulando="+novaquant+" WHERE produto_id="+id_produto+" AND circulacao_id="+id_circulacao+";");
			return true;
		}
		catch(SQLException e)
		{
			//System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<ProdutoCirculando> ListarProdutosDessaCirculacao(int id_circulacao)
	{
		 ArrayList<ProdutoCirculando> list = new  ArrayList<ProdutoCirculando>();
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT produto_id, qtd_circulando FROM produto_circulando WHERE produto_circulando.circulacao_id =" + id_circulacao+";");
			
			while(rs.next()) 
				{
				ProdutoDAO produto_bd = new ProdutoDAO();
				list.add(new ProdutoCirculando(produto_bd.getProduto(rs.getInt(1)), rs.getInt(2)));
				}
			return list;
		}
		catch(SQLException e)
		{
			return null;
		}
		
	}
	
	public boolean retornarProdutosParaEstoque(int id_circulacao)
	{
		EstoqueDAO estoque_bd = new EstoqueDAO();
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT produto_id, qtd_circulando FROM produto_circulando WHERE produto_circulando.circulacao_id =" + id_circulacao+";");
			
			while(rs.next()) 
				{
					estoque_bd.retornaEstoque(rs.getInt(1), rs.getInt(2));
				}
			return true;
		}
		catch(SQLException e)
		{
			return false;
		}
		
	}
	
	public boolean RemoverProdutosDessaCirculacao(int id_circulacao)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE * FROM produto_circulando WHERE produto_circulando.circulacao_id =" + id_circulacao+";");

			return true;
		}
		catch(SQLException e)
		{
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		ProdutoDAO p = new ProdutoDAO();
		//ProdutoDAO p1 = new ProdutoDAO();
		VendedorDAO v = new VendedorDAO();
		CirculacaoDAO c = new CirculacaoDAO();
		ProdutoCirculandoDAO pc = new ProdutoCirculandoDAO();
		
		System.out.println(pc.addProdutoCirculacao(p.getProduto(2), 50, c.getCirculacao(4), v.getVendedor(6)));		
		//System.out.println(p.getProduto(9).precoFinal);
//
	}

}
