
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProdutoVendidoDAO extends BancoDeDados {
	
	public boolean addProdutoVendido(Produto p, int qtdVendida, Circulacao c, Vendedor v)  //Falta atualizar valor da tabela produto_circulando
	{
		ProdutoDAO prod = new ProdutoDAO();
		CirculacaoDAO circ = new CirculacaoDAO();
		VendedorDAO vend = new VendedorDAO();
		ProdutoCirculandoDAO pc = new ProdutoCirculandoDAO();
		try
		{
			Statement st = conexao.createStatement();			
			//Agora vamos atualizar o valor de qtd circulando no primeiro elemento correspondente não nulo dos produto_circulando. No relatório da volta vamos pegar eles para registrar no estoque
			ResultSet rs = st.executeQuery("SELECT qtd_circulando from produto_circulando WHERE id = " + pc.getProdutoCirculandoID(c, p, v) + " and produto_id= " + prod.getProdutoID(p)) ;
			if(rs.next()){
				if((rs.getInt(1) - qtdVendida) >= 0){//Verifica se a quantidade vendida está disponivel em circulacao
					Statement ts = conexao.createStatement();
					ts.executeUpdate("INSERT INTO produto_vendido VALUES (NULL, " + qtdVendida +", "+  prod.getProdutoID(p) + ", " + circ.getCirculacaoID(c, v) + ",'" + c.getDataAtual() + "'," + pc.getProdutoCirculandoID(c, p, v)+ ")");
					v.atualizaVenda(p.getPrecoFinal(), qtdVendida);
					ts.executeUpdate("UPDATE vendedor SET valor_a_receber=" + v.valorReceber() + " WHERE id=" + vend.getVendedorID(v));//Aumenta o valor a receber do vendedor
					//ts.executeUpdate("UPDATE circulacao SET valor_total=" + c.retiraValorCirculacao(p, qtdVendida) + " WHERE id=" + circ.getCirculacaoID(c, v));//Decrementa o valor da circulacao corrente
					ts.executeUpdate("UPDATE produto_circulando SET qtd_circulando=" + (rs.getInt(1) - qtdVendida) + " WHERE id=" + pc.getProdutoCirculandoID(c, p, v));
					return true;
				}
				else return false;
			}
			else return false;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	
	
	public boolean desfazerProdutoVendido(Produto p, int qtdVendida, Circulacao c, Vendedor v)
	{
		ProdutoDAO prod = new ProdutoDAO();
		CirculacaoDAO circ = new CirculacaoDAO();
		VendedorDAO vend = new VendedorDAO();
		
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produto_vendido WHERE produto_id=" + prod.getProdutoID(p));
			v.retiraVenda(p.precoFinal, qtdVendida);
			if(v.valorReceber() < 0)
				st.executeUpdate("UPDATE vendedor SET valor_a_receber= NULL WHERE id=" + vend.getVendedorID(v));//Se acontecer do valor receber do vendedor ficar negativo o parametro valor receber é setado para NULL
			else
			{
				st.executeUpdate("UPDATE vendedor SET valor_a_receber=" + v.valorReceber() + "WHERE id=" + vend.getVendedorID(v));//Aumenta o valor a receber do vendedor
				st.executeUpdate("UPDATE circulacao SET valor_total=" + c.atualizaValorCirculacao(p, qtdVendida) + " WHERE id=" + circ.getCirculacaoID(c, v));//Decrementa o valor da circulacao corrente
			}
				
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
	
	public double getValorVendido(int id_circulacao)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("select qtd_produto, preco_final from produto_vendido,produto where produto_vendido.produto_id = produto.id and circulacao_id=" +id_circulacao);
			double valor = 0;
			while(rs.next())
			{
				double prod = rs.getDouble(1) * rs.getDouble(2);
				valor += prod;
			}
			return valor;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
	
	
	
	
	public static void main(String[] args) {
		
		ProdutoDAO p = new ProdutoDAO();
		VendedorDAO v = new VendedorDAO();
		CirculacaoDAO c = new CirculacaoDAO();
		ProdutoVendidoDAO pv = new ProdutoVendidoDAO();
		//(c.getCirculacao(1), p.getProduto(3), v.getVendedor(6))
		//System.out.println((pv.addProdutoVendido(p.getProduto(3), 10, c.getCirculacao(4), v.getVendedor(6))));
		//System.out.println(pv.getValorVendido(4));
	}

}
