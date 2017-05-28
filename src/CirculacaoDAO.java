import java.sql.*;

public class CirculacaoDAO extends BancoDeDados{
	
	
	public Circulacao iniciarCirculacao(Vendedor v)
	{
		VendedorDAO banco = new VendedorDAO();
		Circulacao c = new Circulacao();
		
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO circulacao VALUES (NULL, '" + c.getDataAtual() +"', " + banco.getVendedorID(v) + ", " + c.valorTotal + ")");
			return c;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public Circulacao getCirculacao(int id)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM circulacao WHERE circulacao.id =" + id);//Alterar aqui
			
			if(rs.next()) return new Circulacao(rs.getDouble(4), rs.getString(2));
			else return null;
		}
		catch(SQLException e)
		{
			return null;
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
			ResultSet rs = st.executeQuery("SELECT id FROM circulacao WHERE circulacao.data_hora ='" + circ.dataRegistrada + "' AND circulacao.vendedor_id=" + vend.getVendedorID(v));//Alterar aqui
			
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
		
		Vendedor v = new Vendedor("zé colméia", "000.000.000-10", 15);
		CirculacaoDAO circ = new CirculacaoDAO();
		
		//circ.iniciarCirculacao(v);
		
	}

}
