import java.sql.*;

public class CirculacaoDAO extends BancoDeDados{
	
	//Atualizar valor total da circulacao
	//Cadastro de data de inicio da circulacao
	public static void main(String[] args) {
		
		//Testando formatação de produtos no banco
		Produto p = new Produto("mdx", 1, "produto bacana, serve pra um monte de coisa.", 1.02);
		
		System.out.println(p.getPrecoFinal());
	}

}
