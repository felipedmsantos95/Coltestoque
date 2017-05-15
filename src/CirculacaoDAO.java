import java.sql.*;

public class CirculacaoDAO extends BancoDeDados{
	
	
	public boolean iniciarCirculacao(Vendedor v)
	{
		VendedorDAO banco = new VendedorDAO();
		Circulacao c = new Circulacao();
		
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO circulacao VALUES (NULL, '" + c.getDataAtual() +"', " + banco.getVendedorID(v) + ", " + c.valorTotal + ")");
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public Vendedor getCirculacaoVendedor(Circulacao circ)
	{
		VendedorDAO vend = new VendedorDAO();
		
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT vendedor_id FROM circulacao WHERE data_hora = '" + circ.dataRegistrada + "')");
			
			if(rs.next()) return  vend.getVendedor(rs.getInt(1));
			else return null;
		}
		catch(SQLException e)
		{
			return null;
		}		
	}
	
	public int getCirculacaoID(Circulacao circ, Vendedor v)
	{
		VendedorDAO vend = new VendedorDAO();
		
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM circulacao WHERE circulacao.data_hora ='" + circ.dataRegistrada + "' AND circulacao.vendedor_id=" + vend.getVendedorID(v));
			
			if(rs.next()) return rs.getInt(1);
			else return 0;
		}
		catch(SQLException e)
		{
			return 0;
		}		
	}
	//Atualizar valor total da circulacao
	
	public static void main(String[] args) {
		
		Vendedor v = new Vendedor("catatau", 5);
		CirculacaoDAO circ = new CirculacaoDAO();
		
		circ.iniciarCirculacao(v);
		
	}

}
