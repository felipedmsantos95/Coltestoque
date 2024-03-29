import java.util.Calendar;

public class Circulacao {
	private int id;//adicionei aqui
	public double valorTotal;
	public String dataRegistrada;
	
	Circulacao()
	{
		this.valorTotal = 0;//Ao inicar uma nova circulacao o valor total dela é zero
		this.dataRegistrada = this.getDataAtual();
	}
	
	Circulacao(int id, double valorTotal, String data)
	{
		this.id=id;//adicionei aqui
		this.valorTotal = valorTotal;
		this.dataRegistrada = data;
	}
	Circulacao(double valorTotal, String data)
	{
		this.valorTotal = valorTotal;
		this.dataRegistrada = data;
	}
		
	String getDataAtual() //Aqui deixamos no formato que o banco aceita o cadastro da data
	{
		Calendar data = Calendar.getInstance();
		
		return (data.get(Calendar.YEAR) + "-" + (data.get(Calendar.MONTH)+1) + "-" + data.get(Calendar.DAY_OF_MONTH) + " " + data.get(Calendar.HOUR_OF_DAY) + ":" + data.get(Calendar.MINUTE) + ":" + data.get(Calendar.SECOND));
		
	}
	
	/* acho que essa funcao eh desnecessaria
	double atualizaValorCirculacao(Produto p)//Toda vez que um produto é adicionado na circulação o valor total é atualizado
	{
		this.valorTotal += p.precoFinal;
		return (this.valorTotal);
	}*/
	
	double atualizaValorCirculacao(Produto p, int qtd)//Toda vez que um produto é adicionado na circulação o valor total é atualizado
	{
		double valor = p.precoFinal * qtd;
		this.valorTotal += valor;
		return (this.valorTotal);
	}
	
	double retiraValorCirculacao(Produto p)//Toda vez que um produto é adicionado na circulação o valor total é atualizado
	{
		this.valorTotal = this.valorTotal - p.precoFinal;
		return (this.valorTotal);
	}
	
	
	double retiraValorCirculacao(Produto p, int qtd)//Toda vez que um produto é adicionado na circulação o valor total é atualizado
	{
		double valor = p.precoFinal * qtd;
		this.valorTotal = this.valorTotal - valor;
		return (this.valorTotal);
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public int getID()
	{
		return this.id;
	}
	
}
