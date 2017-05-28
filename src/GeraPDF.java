import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;

import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;


public class GeraPDF {
	
	public String geraRecibo(String diretorio, Vendedor v)
	{
		 Document document = new Document();
		 Calendar data = Calendar.getInstance();
		 Locale pt = new Locale("en", "CA");
		 NumberFormat nf = NumberFormat.getInstance(pt);
         try {
             FileOutputStream f = new FileOutputStream(diretorio + "/Recibo" + v.nome + data.getTime() + ".pdf");
             PdfWriter.getInstance(document, f );
             document.open();
            
             double r = v.getValorAReceber() * 100;
				Double round = (double) Math.round(r);
				round = round/100;
             // adicionando um parágrafo no documento
			Paragraph titulo = new Paragraph("RECIBO DE COMISSÃO\n\n\n\n");
			titulo.setAlignment(Element.ALIGN_CENTER);
			FontFactory.getFont(FontFactory.TIMES, Font.BOLD);
			document.add(titulo);
			Paragraph p = new Paragraph("        Eu, " + v.nome + ", vendedor portador do CPF " + v.getCpf() + ". Declaro que para os devidos fins legais recebi do Senhor " );
			Phrase nome = new Phrase("Oseás Oliveira da Silva");
			Font font = new Font(Font.FontFamily.TIMES_ROMAN,14, Font.BOLD);
			nome.setFont(font);
			p.add(nome);
			Phrase quantia = new Phrase(" a importância de R$ " + nf.parse(round.toString()).floatValue() + " como pagamento de comissão pelas vendas realizadas até o fechamento da presente circulação.\n\n");
			p.add(quantia);
			p.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(p);
			
			Paragraph p1 = new Paragraph("        A assinatura do presente recibo foi realizada na data " + data.get(Calendar.DAY_OF_MONTH) + "/" + (data.get(Calendar.MONTH) + 1) + "/" + data.get(Calendar.YEAR) + ".\n\n\n\n\n\n");
			p.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(p1);
			
			Paragraph line = new Paragraph("Assinatura: _________________________________");
			line.setAlignment(Element.ALIGN_CENTER);
			document.add(line);
			
			Paragraph vend = new Paragraph(v.nome + "\n\n");
			vend.setAlignment(Element.ALIGN_CENTER);
			document.add(vend);
			
			document.add(line);
			Paragraph nomep = new Paragraph("Oseás Oliveira da Silva");
			nomep.setAlignment(Element.ALIGN_CENTER);
			document.add(nomep);
			
			
         }
         catch(DocumentException de) {
             System.err.println(de.getMessage());
         }
         catch(IOException ioe) {
             System.err.println(ioe.getMessage());
         } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         document.close();
		return diretorio + "/Recibo" + v.nome + data.getTime() + ".pdf";
     }
	
	public String geraRelatrorioSaida(String diretorio, Vendedor v)
	{
		 Document document = new Document();
		 Calendar data = Calendar.getInstance();
		 Locale pt = new Locale("en", "CA");
		 NumberFormat nf = NumberFormat.getInstance(pt);
         try {
             FileOutputStream f = new FileOutputStream(diretorio + "/RelSaida" + v.nome + data.getTime() + ".pdf");
             PdfWriter.getInstance(document, f );
             document.open();
            
            
             // adicionando um parágrafo no documento
			Paragraph titulo = new Paragraph("RELATÓRIO DE SAÍDA\n\n\n\n");
			titulo.setAlignment(Element.ALIGN_CENTER);
			FontFactory.getFont(FontFactory.TIMES, Font.BOLD);
			document.add(titulo);
			
			
			Paragraph p = new Paragraph("        Eu, " + v.nome + ", vendedor portador do CPF " + v.getCpf() + ". Declaro que para os devidos fins legais recebi do Senhor " );
			Phrase nome = new Phrase("Oseás Oliveira da Silva");
			Font font = new Font(Font.FontFamily.TIMES_ROMAN,14, Font.BOLD);
			nome.setFont(font);
			p.add(nome);
			Phrase quantia = new Phrase(" a importância de R$ " + nf.parse(round.toString()).floatValue() + " como pagamento de comissão pelas vendas realizadas até o fechamento da presente circulação.\n\n");
			p.add(quantia);
			p.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(p);
			
			Paragraph p1 = new Paragraph("        A assinatura do presente recibo foi realizada na data " + data.get(Calendar.DAY_OF_MONTH) + "/" + (data.get(Calendar.MONTH) + 1) + "/" + data.get(Calendar.YEAR) + ".\n\n\n\n\n\n");
			p.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(p1);
			
			Paragraph line = new Paragraph("Assinatura: _________________________________");
			line.setAlignment(Element.ALIGN_CENTER);
			document.add(line);
			
			Paragraph vend = new Paragraph(v.nome + "\n\n");
			vend.setAlignment(Element.ALIGN_CENTER);
			document.add(vend);
			
			document.add(line);
			Paragraph nomep = new Paragraph("Oseás Oliveira da Silva");
			nomep.setAlignment(Element.ALIGN_CENTER);
			document.add(nomep);
			
			
         }
         catch(DocumentException de) {
             System.err.println(de.getMessage());
         }
         catch(IOException ioe) {
             System.err.println(ioe.getMessage());
         } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         document.close();
		return diretorio + "/Recibo" + v.nome + data.getTime() + ".pdf";
     }
	
	public static void main(String[] args) {
		GeraPDF recibo = new GeraPDF();
		VendedorDAO v = new VendedorDAO();
		
		//recibo.geraRecibo("/home/felipedmsantos/Área de Trabalho", v.getVendedor(7));
		
		
	}
}
	


