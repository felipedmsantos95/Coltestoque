import java.sql.*;
import java.util.*;

public class ProdutoDAO extends BancoDeDados{
	
	public boolean adicionarProduto(Produto p)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO produto VALUES (NULL, '" + p.codigo +"', " + p.precoFinal + "', "+p.precoCompra+"', "+ p.descricao()" )");
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public boolean removerProduto(int id_produto)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produto WHERE id=" + id_produto);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	public void listarProdutos()
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM produto");
			while(rs.next())
			{				
				System.out.println("Produto: "+ rs.getInt(2) + "\n " + rs.getString(5)+ "\nPreco Final "+rs.getDouble(3) +"\n");
			}
			
		}
		catch(SQLException e)
		{
			
		}		
	}
	public void listarProdutosEdicao()
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM produto");
			while(rs.next())
			{				
				System.out.println("Produto: "+ rs.getInt(2) + "\n " + rs.getString(5)+ "\nPreco Final "+rs.getDouble(3) +"\nPreco Compra "+rs.getDouble(4) +"\n");
			}
			
		}
		catch(SQLException e)
		{
			
		}		
	}
	public Produto getProduto(int id)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM produto WHERE id=" + id);
			
			if(rs.next()) 
			{
				return new Produto(rs.getInt(2), rs.getDouble(3), rs.getDouble(4),rs.getString(5));
			}
			else return null;
		
		}
		catch (SQLException e)
		{
			return null;
		}
	}
	
	public int getProdutoID(Produto p)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM produto WHERE produto.codigo ='" + p.codigo + "' AND produto.descricao=" + p.descricao );
			
			if(rs.next()) return rs.getInt(1);
			else return 0;
		}
		catch(SQLException e)
		{
			return 0;
		}		
	}

}
