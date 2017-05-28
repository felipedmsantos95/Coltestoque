import java.sql.*;
import java.util.*;

public class ProdutoDAO extends BancoDeDados{
	
	public boolean adicionarProduto(Produto p)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO produto VALUES (NULL, '" + p.getCodigo() +"', '" + p.getNome() + "', " + p.getPrecoFinal() + ", "+ p.getPrecoCompra() +", '"+ p.getDescricao() + "' )");
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
	public ArrayList<Produto> listarProdutos()
	{
		ArrayList<Produto> list = new ArrayList<Produto>();
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT produto.id, produto.codigo, produto.nome, produto.preco_final, produto.preco_atacado, produto.descricao, estoque.qtd_estoque FROM produto, estoque WHERE estoque.produto_id=produto.id;");
			while(rs.next())
			{		
				Produto produto = new Produto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5),rs.getString(6),rs.getInt(7));
				//System.out.println("Produto: "+ rs.getInt(2) + "\n " + rs.getString(5)+ "\nPreco Final "+rs.getDouble(3) +"\n");
				list.add(produto);
			}
			
		}
		catch(SQLException e)
		{
			
		}
		return list;
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
				return new Produto(rs.getString(2),rs.getString(3),rs.getDouble(5), rs.getString(6), rs.getDouble(4));
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
			ResultSet rs = st.executeQuery("SELECT id FROM produto WHERE produto.codigo ='" + p.getCodigo() + "' AND produto.descricao='" + p.getDescricao() + "'");
			
			if(rs.next()) return rs.getInt(1);
			else return 0;
		}
		catch(SQLException e)
		{
			return 0;
		}		
	}
	
	public int getProdutoIDbyCodigo(String codigo)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM produto WHERE produto.codigo ='" + codigo + "'");
			
			if(rs.next()) return rs.getInt(1);
			else return 0;
		}
		catch(SQLException e)
		{
			return 0;
		}	
	}
	
	public boolean atualizarProduto(int id, String codigo, String nome, double precoAtacado, double percent, String descricao)
	{
		Produto p = new Produto(codigo, nome, precoAtacado, percent, descricao);
		
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE produto SET codigo='" + codigo +"' WHERE id=" + id);
			st.executeUpdate("UPDATE produto SET nome='" + nome +"' WHERE id=" + id);
			st.executeUpdate("UPDATE produto SET preco_atacado=" + precoAtacado +" WHERE id=" + id);			
			st.executeUpdate("UPDATE produto SET preco_final=" + p.getPrecoFinal() +" WHERE id=" + id);
			st.executeUpdate("UPDATE produto SET descricao='" + descricao +"' WHERE id=" + id);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean atualizarProduto(int id, String codigo, String nome, double precoAtacado, String descricao)
	{
		Produto p = new Produto(codigo, nome, precoAtacado, descricao);
		
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE produto SET codigo='" + codigo +"' WHERE id=" + id);
			st.executeUpdate("UPDATE produto SET nome='" + nome +"' WHERE id=" + id);
			st.executeUpdate("UPDATE produto SET preco_atacado=" + precoAtacado +" WHERE id=" + id);			
			st.executeUpdate("UPDATE produto SET preco_final=" + p.getPrecoFinal() +" WHERE id=" + id);
			st.executeUpdate("UPDATE produto SET descricao='" + descricao +"' WHERE id=" + id);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	

}
