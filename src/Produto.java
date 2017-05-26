import java.util.*;

public class Produto {
	
	private int id;
	private String codigo;
	private String nome;
	public int quantidadeEstoque;
	private double percentLucro;
	private double precoCompra;
	public double precoFinal;
	String descricao;
	
	Produto( String codigo, String nome, int quantidadeEstoque, String descricao, double precoCompra)
	{
		this.nome = nome;
		this.codigo = codigo;
		this.quantidadeEstoque = quantidadeEstoque;
		this.descricao = descricao;
		this.setPrecoCompra(precoCompra);
		this.setPercentLucro(61);//Esse valor foi o padrao que encontramos na tabela do cliente
		this.precoFinal = (this.getPrecoCompra() * (1 + (this.getPercentLucro()/100)));
	}
	
	Produto( String codigo, String nome, double precoCompra, String descricao)//Criei para poder extrair um produto da tabela produto
	{
		this.nome = nome;
		this.codigo = codigo;
		this.quantidadeEstoque = 0;
		this.descricao = descricao;
		this.setPrecoCompra(precoCompra);
		this.setPercentLucro(61); //Esse valor foi o padrao que encontramos na tabela do cliente
		this.precoFinal = (this.getPrecoCompra() * (1 + (this.getPercentLucro()/100)));
	}
	
	Produto( String codigo, String nome, double precoCompra, double percentlucro, String descricao)//Criei para poder extrair um produto da tabela produto
	{
		this.nome = nome;
		this.codigo = codigo;
		this.quantidadeEstoque = 0;
		this.descricao = descricao;
		this.setPrecoCompra(precoCompra);
		this.setPercentLucro(percentlucro); //Esse valor foi o padrao que encontramos na tabela do cliente
		this.precoFinal = (this.getPrecoCompra() * (1 + (this.getPercentLucro()/100)));
	}
	
	Produto(int id, String codigo, String nome, double precoCompra, double percentLucro, String descricao, int quantidadeEstoque)
	{
		this.id=id;
		this.nome = nome;
		this.codigo = codigo;
		this.quantidadeEstoque = quantidadeEstoque;
		this.descricao = descricao;
		this.setPrecoCompra(precoCompra);
		this.setPercentLucro(percentLucro);
		this.precoFinal = (this.getPrecoCompra() * (1 + (this.getPercentLucro()/100)));
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}	
	
	String getDescricaoCompleta()
	{
		return(this.codigo + " - " + this.descricao);
	}

	public double getPercentLucro() {
		return percentLucro;
	}

	public void setPercentLucro(double percentLucro) {
		this.percentLucro = percentLucro;
	}
	
	public int incrementarEstoque()
	{
		this.quantidadeEstoque++;
		
		return this.quantidadeEstoque;
	}
	public int getID()
	{
		return this.id;
	}
	public String getNome()
	{
		return this.nome;
	}
	public String getCodigo()
	{
		return this.codigo;
	}
	public int getQuantidadeEstoque()
	{
		return this.quantidadeEstoque;
	}
	public String getDescricao()
	{
		return this.descricao;
	}
	public double getPrecoFinal()
	{
		return this.precoFinal;
	}
	
}
