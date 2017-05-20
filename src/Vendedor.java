import java.util.*;

public class Vendedor {
	
	String nome;
	private double percentual;
	double valorVendido;
	
	
	Vendedor(String nome, double percentComissao)
	{
		this.nome = nome;
		this.setPercentual(percentComissao);
		this.valorVendido = 0;
	}
	
	Vendedor(String nome, double percentComissao, double valorVendido)
	{
		this.nome = nome;
		this.setPercentual(percentComissao);
		this.valorVendido = valorVendido;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setPercentual(double percentComissao) {
		this.percentual = percentComissao;
	}
	
	//O atributo valor vendido é uma variável acumuladora que será atualizada pela tabela produto_vendido identificando o id do vendedor
	public double valorReceber()
	{
		return this.valorVendido * (this.getPercentual()/100);
	}
	
	//Esta função atualiza o acumulador valorVendido, o parametro de entrada é o preco final do produto da tabela produto e a quantidade vendida na tabela produto_vendido
	public double atualizaVenda(double precoFinal, double qtdVendida)
	{
		double valor = precoFinal * qtdVendida;
		
		this.valorVendido += valor;
		
		return this.valorVendido;
	}
	
	public double retiraVenda(double precoFinal, double qtdVendida)
	{
		double valor = precoFinal * qtdVendida;
		
		this.valorVendido = this.valorVendido - valor;
		
		return this.valorVendido;
	}
}
