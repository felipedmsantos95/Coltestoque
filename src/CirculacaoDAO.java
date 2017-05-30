import java.sql.*;
import java.util.ArrayList;

public class CirculacaoDAO extends BancoDeDados{
	
	
	public Circulacao iniciarCirculacao(Vendedor v)
    {
        Circulacao c = new Circulacao();
       
        try
        {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO circulacao VALUES (NULL, '" + c.getDataAtual() +"', " + v.getID() + ", " + c.valorTotal + ")");
            ResultSet rs = st.executeQuery("SELECT id FROM circulacao WHERE circulacao.data_hora ='" + c.dataRegistrada + "' AND circulacao.vendedor_id=" + v.getID());//Alterar aqui
           
            if(rs.next()) c.setID(rs.getInt(1));
            return c;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
	
	public boolean removerCirculacao(int id)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM circulacao WHERE id=" + id);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
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
			ResultSet rs = st.executeQuery("SELECT vendedor_id FROM circulacao WHERE data_hora = '" + circ.dataRegistrada + "';");
			
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
	
	public double getValorCirculacao(int idCirculacao)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT valor_total FROM circulacao WHERE id =" + idCirculacao);//Alterar aqui
			
			if(rs.next()) return rs.getDouble(1);
			else return 0;
		}
		catch(SQLException e)
		{
			return 0;
		}		
	}
	
	public ArrayList<Circulacao> ListarCirculacoes()
    {
        ArrayList<Circulacao> list = new ArrayList<Circulacao>();
        try
        {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM circulacao ;");
            while(rs.next())
                {
                list.add(new Circulacao(rs.getInt(1),rs.getDouble(4), rs.getString(2)));
                }
            return list;
        }
        catch(SQLException e)
        {
            return null;
        }       
    }
	
	
	public static void main(String[] args) {
		
		Vendedor v = new Vendedor("catatau", "000.000.000-14", 20);
		CirculacaoDAO circ = new CirculacaoDAO();
		
		
		//circ.iniciarCirculacao(v);
		//System.out.println(circ.getValorCirculacao(4));
		
	}

}
