import java.awt.Color;
import java.io.FileOutputStream;
import java.sql.*;
import java.sql.Connection;
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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;


public class GeraPDF extends BancoDeDados {
	
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
	
	public String geraRelatorioSaida(String diretorio, Vendedor v, int idCirculacao)
	{
		 Document document = new Document();
		 Calendar data = Calendar.getInstance();
		 Locale pt = new Locale("en", "CA");
		 NumberFormat nf = NumberFormat.getInstance(pt);
		 //CirculacaoDAO c = new CirculacaoDAO();
         try {
             FileOutputStream f = new FileOutputStream(diretorio + "/RelSaida" + v.nome + data.getTime() + ".pdf");
             PdfWriter.getInstance(document, f );
             document.open();
            
            
             // adicionando um parágrafo no documento
			Paragraph titulo = new Paragraph("RELATÓRIO DE SAÍDA\n\n\n\n");
			titulo.setAlignment(Element.ALIGN_CENTER);
			FontFactory.getFont(FontFactory.TIMES, Font.BOLD);
			document.add(titulo);
			
			//Aqui será feita uma consulta no banco para extrair as informações necessárias da circulação
			
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("select  valor_total,vendedor_id,data_hora,qtd_circulando,codigo,produto.nome,preco_final from circulacao,produto_circulando,produto,vendedor where produto_circulando.produto_id = produto.id and circulacao.vendedor_id = vendedor.id and circulacao.id=" + idCirculacao);
						
			PdfPTable tabela = new PdfPTable(5);
			PdfPCell codigo = new PdfPCell(new Paragraph("Código"));
			PdfPCell nomeProduto = new PdfPCell(new Paragraph("Nome do Produto"));
			PdfPCell precou = new PdfPCell(new Paragraph("Preço Unitário"));
			PdfPCell qtd = new PdfPCell(new Paragraph("Quantidade"));
			PdfPCell precot = new PdfPCell(new Paragraph("Preço Total"));
			
			tabela.addCell(codigo);
			tabela.addCell(nomeProduto);
			tabela.addCell(precou);
			tabela.addCell(qtd);
			tabela.addCell(precot);
			
			tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			Double  totaCirculacao = (double) 0;//Esse é o valor total de produtos
			while(rs.next())
			{
				PdfPCell codigoe = new PdfPCell(new Paragraph(rs.getString(5)));
				PdfPCell nomeProdutoe = new PdfPCell(new Paragraph(rs.getString(6)));
				double r = rs.getDouble(7) * 100;
				Double round = (double) Math.round(r);
				round = round/100;
				PdfPCell precoue = new PdfPCell(new Paragraph(round.toString()));
				PdfPCell qtde = new PdfPCell(new Paragraph(rs.getString(4)));
				Double total = rs.getInt(4) * round * 100;
				total = (double) Math.round(total);
				total = total/100;
				PdfPCell precote = new PdfPCell(new Paragraph(total.toString()));
				
				tabela.addCell(codigoe);
				tabela.addCell(nomeProdutoe);
				tabela.addCell(precoue);
				tabela.addCell(qtde);
				tabela.addCell(precote);
				
				double circ = rs.getDouble(1) * 100;
				totaCirculacao = (double) Math.round(circ);
				totaCirculacao = totaCirculacao / 100;
				
			}
			
			
			
			
			
			Paragraph p = new Paragraph("        Os seguintes produtos estão saindo do estoque na data "+ data.get(Calendar.DAY_OF_MONTH) + "/" + (data.get(Calendar.MONTH) + 1) + "/" + data.get(Calendar.YEAR) + " no horário " + data.get(Calendar.HOUR_OF_DAY) + "h e " + data.get(Calendar.MINUTE) + "min para o início do processo de venda através do vendedor " + v.nome + ":\n\n");
			p.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(p);
			document.add(tabela);
			
			Paragraph concl = new Paragraph("\n\n        O valor total desta retirada é de: R$ " + totaCirculacao + "\n\n");
			concl.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(concl);
			
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
         } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         document.close();
		return diretorio + "/RelSaida" + v.nome + data.getTime() + ".pdf";
     }
	
	public static void main(String[] args) {
		GeraPDF recibo = new GeraPDF();
		VendedorDAO v = new VendedorDAO();
		
		recibo.geraRelatorioSaida("/home/felipedmsantos/Área de Trabalho", v.getVendedor(6),1);
		
		
	}
}
	


