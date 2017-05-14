import java.sql.*;

public class CirculacaoDAO extends BancoDeDados{
	
	
	public static void main(String[] args) {
		
		
		Produto p = new Produto("mdx", 1, "produto bacana, serve pra um monte de coisa.", 1.02);
		
		System.out.println(p.getPrecoFinal());
	}

}
