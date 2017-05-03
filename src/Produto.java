import java.util.*;

public class Produto {
	
	String codigo;
	int quantidadeEstoque;
	private double precoCompra;
	private double percentLucro;
	String descricao;
	
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
	
	String getDescrição()
	{
		return(this.codigo + " - " + this.descricao);
	}
	
	
}
