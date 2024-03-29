import java.sql.*;
import java.util.*;

public class EstoqueDAO extends BancoDeDados {
	

	
	public boolean adicionarProdutoEstoque(String codigoProduto, int quantidade)
	{
		ProdutoDAO prod = new ProdutoDAO();
		try
		{
			System.out.println(prod.getProdutoIDbyCodigo(codigoProduto));
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO estoque VALUES (NULL, " + quantidade +", "+ prod.getProdutoIDbyCodigo(codigoProduto) + ");");
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public boolean removerProdutoEstoque(int id_produto)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM estoque WHERE produto_id=" + id_produto);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public int getQuantidadeEstoque(int id_produto)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT qtd_estoque FROM estoque WHERE estoque.produto_id =" +id_produto+";");
			
			if(rs.next()) return rs.getInt(1);
			else return 0;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
	
	public boolean retiraEstoque(int id_produto, int quantidade)
	{
		int qEstoque = getQuantidadeEstoque(id_produto);
	
			try
			{
				Statement st = conexao.createStatement();
				st.executeUpdate("UPDATE estoque SET qtd_estoque="+(qEstoque-quantidade)+" WHERE produto_id=" + id_produto+";");
				
				return true;
			}
			catch(SQLException e)
			{
				System.out.println(e.getMessage());
				return false;
			}

	}
	public boolean repoeEstoque(int id_produto, int quantidade)
	{
		int qEstoque = getQuantidadeEstoque(id_produto);
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE estoque SET qtd_estoque="+(qEstoque+quantidade)+" WHERE produto_id=" + id_produto+";");
			
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;			
		}
		
	}
	
	public boolean retornaEstoque(int id_produto, int quantidade)
	{
		int qEstoque = getQuantidadeEstoque(id_produto);

		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE estoque SET qtd_estoque="+(qEstoque+quantidade)+" WHERE produto_id=" + id_produto+";");
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	

}
