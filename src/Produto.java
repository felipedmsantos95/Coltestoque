import java.util.*;

public class Produto {
	
	String codigo;
	int quantidadeEstoque;
	private double precoCompra;
	private double percentLucro;
	String descricao;
	
	Produto(String codigo, int quantidadeEstoque, String descricao, double precoCompra)
	{
		this.codigo = codigo;
		this.quantidadeEstoque = quantidadeEstoque;
		this.descricao = descricao;
		this.setPrecoCompra(precoCompra);
		this.setPercentLucro(61);//Esse valor foi o padrao que encontramos na tabela do cliente
	}
	
	Produto(String codigo, int quantidadeEstoque, String descricao, double precoCompra, double percentLucro)
	{
		this.codigo = codigo;
		this.quantidadeEstoque = quantidadeEstoque;
		this.descricao = descricao;
		this.setPrecoCompra(precoCompra);
		this.setPercentLucro(percentLucro);
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public double getPercentLucro() {
		return percentLucro;
	}

	public void setPercentLucro(double percentLucro) {
		this.percentLucro = percentLucro;
	}
	
	String getDescriçãoCompleta()
	{
		return(this.codigo + " - " + this.descricao);
	}
	
	double getPrecoFinal()
	{
		return (this.getPrecoCompra() * (1 + this.getPercentLucro()/100));
	}
}
