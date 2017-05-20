
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProdutoVendidoDAO extends BancoDeDados {
	
	public boolean addProdutoVendido(Produto p, int qtdVendida, Circulacao c, Vendedor v)  //Falta atualizar valor da tabela produto_circulando
	{
		ProdutoDAO prod = new ProdutoDAO();
		CirculacaoDAO circ = new CirculacaoDAO();
		VendedorDAO vend = new VendedorDAO();
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO produto_vendido VALUES (NULL, " + qtdVendida +", "+  prod.getProdutoID(p) + ", " + circ.getCirculacaoID(c, v) + ",'" + c.getDataAtual() + "')");
			v.atualizaVenda(p.precoFinal, qtdVendida);
			st.executeUpdate("UPDATE vendedor SET valor_a_receber=" + v.valorReceber() + " WHERE id=" + vend.getVendedorID(v));//Aumenta o valor a receber do vendedor
			st.executeUpdate("UPDATE circulacao SET valor_total=" + c.retiraValorCirculacao(p, qtdVendida) + " WHERE id=" + circ.getCirculacaoID(c, v));//Decrementa o valor da circulacao corrente
			st.executeUpdate("DELETE FROM produto_circulando WHERE produto_id=" + prod.getProdutoID(p) + " AND circulacao_id=" + circ.getCirculacaoID(c, v) + " AND qtd_circulando <=" + qtdVendida);
			return true;
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
	
	
	public static void main(String[] args) {
		ProdutoCirculandoDAO prod = new ProdutoCirculandoDAO();
		ProdutoVendidoDAO pv = new ProdutoVendidoDAO();
		ProdutoDAO pp = new ProdutoDAO();
		Produto p = new Produto("DPX", 1, "Que produto satisfatório.");
		CirculacaoDAO c = new CirculacaoDAO();
		Vendedor v = new Vendedor("Catatau", 5);
		VendedorDAO vend = new VendedorDAO();
		
		//pp.adicionarProduto(p);
		//c.iniciarCirculacao(v);		
		Circulacao circ = c.getCirculacao(31);
		//vend.fecharMesVendedor(vend.getVendedorID(v));
		//vend.adicionarVendedor(v);
		//System.out.println(prod.addProdutoCirculacao(p, 2, circ, v));
		System.out.println(pv.addProdutoVendido(p, 2, circ, v));

	}

}
