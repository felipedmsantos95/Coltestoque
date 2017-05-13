import java.util.*;

public class Vendedor {
	
	String nome;
	private double percentComissao;
	double valorReceber;
	
	
	Vendedor(String nome, double percentComissao)
	{
		this.nome = nome;
		this.setPercentComissao(percentComissao);
		this.valorReceber = 0;
	}

	public double getPercentComissao() {
		return percentComissao;
	}

	public void setPercentComissao(double percentComissao) {
		this.percentComissao = percentComissao;
	}
	
	
	
}
