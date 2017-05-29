import java.sql.*;
import java.util.*;


public class VendedorDAO extends BancoDeDados {
	
	public boolean adicionarVendedor(Vendedor v)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO vendedor VALUES (NULL, '" + v.nome +"', '" + v.getCpf() + "'," + v.getPercentual() + ", NULL)");
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
	
	public ArrayList<Vendedor> listarVendedores()
	{
		ArrayList<Vendedor> list = new ArrayList<Vendedor>();
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM vendedor");
			while(rs.next())
			{		
				Vendedor novo = new Vendedor(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDouble(4),rs.getDouble(5));//A coluna 3 é a do cpf
				//System.out.println("Vendedor: "+ rs.getString(2) + "\nPercetual de Comissão: " + rs.getFloat(3) + " %\nValor Acumulado: R$ " + rs.getFloat(4) + "\n");
				list.add(novo);
			}
			
		}
		catch(SQLException e)
		{
			
		}	
		return list;
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
				//Tenho que tirar o valor vendido do vendedor atraves do valor receber da tabela
				double valorVendido = rs.getDouble(5)/(rs.getDouble(4)/100);
				return new Vendedor(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDouble(4), valorVendido);//A coluna 3 é a do CPF
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
	
	//Gerar um relatorio
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
	
	public boolean atualizarVendedor(int id, String nome, String cpf, double percent)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE vendedor SET nome='" + nome +"' WHERE id=" + id);
			st.executeUpdate("UPDATE vendedor SET cpf='" + cpf +"' WHERE id=" + id);
			st.executeUpdate("UPDATE vendedor SET percentual='" + percent +"' WHERE id=" + id);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public double getComissaoVendedor(int idVendedor)
	{
			try
			{
				Statement st = conexao.createStatement();
				ResultSet rs = st.executeQuery("SELECT valor_a_receber FROM vendedor WHERE id=" + idVendedor);
				
				if(rs.next()) return rs.getDouble(1);
				else return 0;
			}
			catch(SQLException e)
			{
				return 0;
			}		
		
	}
	
	
	//Para testar e aplicar métodos criados no banco local
	public static void main(String[] args) {
		VendedorDAO vend = new VendedorDAO();
		Vendedor v = new Vendedor("Catatau", "000.000.000-15", 20);
		
		//System.out.println(vend.getComissaoVendedor(6));
		
	}

}
