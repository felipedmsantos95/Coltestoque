import java.util.Calendar;

public class Circulacao {
	
	double valorTotal;
	
	Circulacao()
	{
		this.valorTotal = 0;//Ao inicar uma nova circulacao o valor total dela é zero
	}
		
	String getDataAtual() //Aqui deixamos no formato que o banco aceita o cadastro da data
	{
		Calendar data = Calendar.getInstance();
		
		return (data.get(Calendar.YEAR) + "-" + (data.get(Calendar.MONTH)+1) + "-" + data.get(Calendar.DAY_OF_MONTH) + " " + data.get(Calendar.HOUR_OF_DAY) + ":" + data.get(Calendar.MINUTE) + ":" + data.get(Calendar.SECOND));
	}
	
	double atualizaValorCirculacao(Produto p)//Toda vez que um produto é adicionado na circulação o valor total é atualizado
	{
		return (this.valorTotal += p.getPrecoFinal());
	}
}
