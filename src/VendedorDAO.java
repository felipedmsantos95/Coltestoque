import java.sql.*;
import java.util.*;


public class VendedorDAO extends BancoDeDados {
	
	public boolean adicionarVendedor(Vendedor v)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO vendedor VALUES (NULL, '" + v.nome +"', " + v.getPercentual() + ", NULL)");
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public boolean removerVendedor(int id)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM vendedor WHERE id=" + id);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public void listarVendedores()
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM vendedor");
			while(rs.next())
			{				
				System.out.println("Vendedor: "+ rs.getString(2) + "\nPercetual de Comissão: " + rs.getFloat(3) + " %\nValor Acumulado: R$ " + rs.getFloat(4) + "\n");
			}
			
		}
		catch(SQLException e)
		{
			
		}		
	}
	
	//Pode ser obtido pelo nome também
	public Vendedor getVendedor(int id)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM vendedor WHERE id=" + id);
			
			if(rs.next()) 
			{
				return new Vendedor(rs.getString(2), rs.getDouble(3), rs.getDouble(4));
			}
			else return null;
		
		}
		catch (SQLException e)
		{
			return null;
		}
	}
	
	public int getVendedorID(Vendedor i)//Procura exatamente pelo objeto vendedor e retorna o id do espelho dele no banco
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM vendedor WHERE vendedor.nome ='" + i.nome + "' AND vendedor.percentual=" + i.getPercentual() );
			
			if(rs.next()) return rs.getInt(1);
			else return 0;
		}
		catch(SQLException e)
		{
			return 0;
		}		
	}
	
	public void fecharMesVendedor(int id)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE vendedor SET valor_a_receber=NULL WHERE id=" + id);
			
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			
		}
		
	}
	
	public void vendaDeProduto()
	{
		
	}
	
	
	//Para testar e aplicar métodos criados no banco local
	public static void main(String[] args) {
		Vendedor v = new Vendedor("Catatau", 5);
		
		VendedorDAO vend = new VendedorDAO();
		//Vendedor v = vend.getVendedor(1);
		//vend.adicionarVendedor(v);
		
		//System.out.println(v.getPercentual());
		//vend.removerVendedor(3);
		System.out.println(vend.getVendedorID(v));
	}

}
