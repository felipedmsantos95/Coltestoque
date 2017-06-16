
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutoVendidoDAO extends BancoDeDados {
	
	public boolean addProdutoVendido(int id_produto, int qtdVendida, Circulacao c, Vendedor v)  
	{
		try
		{
			Statement st = conexao.createStatement();			
			st.executeUpdate("INSERT INTO produto_vendido VALUES (NULL, " + qtdVendida +", "+  id_produto + ", " + c.getID() + ",'" + c.getDataAtual() + "')");				
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	
	
	public boolean desfazerProdutoVendido(Produto p, int qtdVendida, Circulacao c, Vendedor v)
	{
		ProdutoDAO prod = new ProdutoDAO();
		CirculacaoDAO circ = new CirculacaoDAO();
		VendedorDAO vend = new VendedorDAO();
		
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produto_vendido WHERE produto_id=" + prod.getProdutoID(p));
			v.retiraVenda(p.precoFinal, qtdVendida);
			if(v.valorReceber() < 0)
				st.executeUpdate("UPDATE vendedor SET valor_a_receber= NULL WHERE id=" + vend.getVendedorID(v));//Se acontecer do valor receber do vendedor ficar negativo o parametro valor receber é setado para NULL
			else
			{
				st.executeUpdate("UPDATE vendedor SET valor_a_receber=" + v.valorReceber() + "WHERE id=" + vend.getVendedorID(v));//Aumenta o valor a receber do vendedor
				st.executeUpdate("UPDATE circulacao SET valor_total=" + c.atualizaValorCirculacao(p, qtdVendida) + " WHERE id=" + circ.getCirculacaoID(c, v));//Decrementa o valor da circulacao corrente
			}
				
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public int getQuantidadeVendida(int id_produto)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT qtd_produto FROM produto_vendido WHERE produto_vendido.produto_id ='" +id_produto);
			
			if(rs.next()) return rs.getInt(1);
			else return 0;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
	
	public double getValorVendido(int id_circulacao)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("select qtd_produto, preco_final from produto_vendido,produto where produto_vendido.produto_id = produto.id and circulacao_id=" +id_circulacao);
			double valor = 0;
			while(rs.next())
			{
				double prod = rs.getDouble(1) * rs.getDouble(2);
				valor += prod;
			}
			return valor;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
	
	public ArrayList<ProdutoCirculando> ListarProdutosVendidos(int id_circulacao)
	{
		 ArrayList<ProdutoCirculando> list = new  ArrayList<ProdutoCirculando>();
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT produto_id, qtd_produto FROM produto_vendido WHERE produto_vendido.circulacao_id =" + id_circulacao+";");
			
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
	public boolean RemoverProdutosVendidosDessaCirculacao(int id_circulacao)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produto_vendido WHERE produto_vendido.circulacao_id =" + id_circulacao+";");

			return true;
		}
		catch(SQLException e)
		{
			return false;
		}
	}
	
	
	
	

}
