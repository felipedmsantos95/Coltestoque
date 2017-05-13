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
	
	
	
	//Para testar e aplicar métodos criados no banco local
	public static void main(String[] args) {
		Vendedor v = new Vendedor("Roberval", 15);
		VendedorDAO vend = new VendedorDAO();
		
		//vend.adicionarVendedor(v);
		vend.listarVendedores();
	}

}
