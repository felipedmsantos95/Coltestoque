
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProdutoVendidoDAO extends BancoDeDados {
	
	public boolean adicionarProdutoVendido(int id_produto)  
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO produto_vendido VALUES (NULL, '" + 0 +"', "+ id_produto + ")"); //Não entendi bem porque setar como 0 o segundo parametro e falta tambem indicar o ID da tabela produto pra executar esse comando sql
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public boolean removerProdutoVendido(int id_produto)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produto_vendido WHERE produto_id=" + id_produto);
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
	public boolean produtoVendido(int id_produto, int quantidade)
	{
		int qVendida = getQuantidadeVendida(id_produto);
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE produto_vendido SET qtd_produto="+(qVendida+quantidade)+" WHERE produto_id=" + id_produto);
			
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			
		}
		return false;
	}

}
