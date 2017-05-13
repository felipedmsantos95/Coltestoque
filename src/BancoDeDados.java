
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDeDados {
	private static String url = "jdbc:mysql://localhost:3306/db_oseas";
	private static String user = "oseas";
	private static String pass = "senha";
	protected static Connection	conexao	= null;
	
	
	public BancoDeDados()
	{
		if (conexao ==  null) conecta();
	}
	
	private static boolean conecta()
	{
		try
		{
			conexao = DriverManager.getConnection(url, user, pass);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	private static boolean desconecta()
	{
		try
		{
			conexao.close();
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}	
	

}
