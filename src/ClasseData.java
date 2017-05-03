import java.util.Calendar;

public class ClasseData {
	int dia;
	int mes;
	int ano;
	
	ClasseData()
	{
		this.dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		this.mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
		this.ano = Calendar.getInstance().get(Calendar.YEAR);		
	}
	
	public String getData()
	{
		return ("Data: " + this.dia + "/" + this.mes + "/" + this.ano);
	}
}
