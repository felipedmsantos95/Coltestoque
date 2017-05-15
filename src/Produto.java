import java.util.*;

public class Produto {
	
	String codigo;
	int quantidadeEstoque;
	private double precoCompra;
	private double precoFinal;
	String descricao;
	
	Produto(String codigo, int quantidadeEstoque, String descricao, double precoCompra)
	{
		this.codigo = codigo;
		this.quantidadeEstoque = quantidadeEstoque;
		this.descricao = descricao;
		this.setPrecoCompra(precoCompra);
		this.setPercentLucro(61);//Esse valor foi o padrao que encontramos na tabela do cliente
	}
	
	Produto(String codigo, double precoCompra, String descricao)//Criei para poder extrair um produto da tabela produto
	{
		this.codigo = codigo;
		this.quantidadeEstoque = 0;
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
		return ((this.precoFinal/this.precoCompra-1)/100);
	}

	public void setPercentLucro(double percentLucro) {
		this.precoFinal=this.precoCompra*(1 + this.getPercentLucro()/100);
	}
	
	String getDescricaoCompleta()
	{
		return(this.codigo + " - " + this.descricao);
	}
	
	double getPrecoFinal()
	{
		return this.precoFinal;
	}
	
}
