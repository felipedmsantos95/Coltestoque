import java.util.*;

public class Vendedor {
	
	String nome;
	private double percentual;
	double valorReceber;
	
	
	Vendedor(String nome, double percentComissao)
	{
		this.nome = nome;
		this.setPercentual(percentComissao);
		this.valorReceber = 0;
	}
	
	Vendedor(String nome, double percentComissao, double valorReceber)
	{
		this.nome = nome;
		this.setPercentual(percentComissao);
		this.valorReceber = valorReceber;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setPercentual(double percentComissao) {
		this.percentual = percentComissao;
	}
	
	
	
}
