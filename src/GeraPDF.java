import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
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
        	 //String bar;
        	 FileOutputStream f;
        	 if(!(System.getProperty("os.name").equals("Linux"))){
        		// diretorio.replaceAll("/", File.separator);
        		 f = new FileOutputStream(diretorio + "\\Recibo" + v.nome + data.get(Calendar.YEAR) + "-" + (data.get(Calendar.MONTH) + 1) + "-" + data.get(Calendar.DAY_OF_MONTH) + "_" + data.get(Calendar.HOUR) + "-" + data.get(Calendar.MINUTE) +"-" + data.get(Calendar.SECOND) + ".pdf");
        	 }        	
        	 else {
        		 f = new FileOutputStream(diretorio + "/Recibo" + v.nome + data.getTime() + ".pdf");
        	 }
             PdfWriter.getInstance(document, f );
             document.open();
            
             double r = v.valorReceber() * 100;
				Double round = (double) Math.round(r);
				round = round/100;
             // adicionando um parÃ¡grafo no documento
			Paragraph titulo = new Paragraph("RECIBO DE COMISSÃO\n\n\n\n");
			titulo.setAlignment(Element.ALIGN_CENTER);
			FontFactory.getFont(FontFactory.TIMES, Font.BOLD);
			document.add(titulo);
			Paragraph p = new Paragraph("        Eu, " + v.nome + ", vendedor portador do CPF " + v.getCpf() + ". Declaro que para os devidos fins legais recebi do Senhor " );
			Phrase nome = new Phrase("Oséas Oliveira da Silva");
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
			
			Paragraph line = new Paragraph("Ass: _________________________________");
			line.setAlignment(Element.ALIGN_CENTER);
			document.add(line);
			
			Paragraph vend = new Paragraph(v.nome + "\n\n");
			vend.setAlignment(Element.ALIGN_CENTER);
			document.add(vend);
			
			document.add(line);
			Paragraph nomep = new Paragraph("Oséas Oliveira da Silva");
			nomep.setAlignment(Element.ALIGN_CENTER);
			document.add(nomep);
			
			VendedorDAO vendedor_bd = new VendedorDAO();
			vendedor_bd.updateValorAReceberVendedor(v.getID(),0);
			
			
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
         if(!(System.getProperty("os.name").equals("Linux"))){
    		 //diretorio.replaceAll("/", File.separator);
    		 return (diretorio + "\\Recibo" + v.nome + data.get(Calendar.YEAR) + "-" + (data.get(Calendar.MONTH) + 1) + "-" + data.get(Calendar.DAY_OF_MONTH) + "_" + data.get(Calendar.HOUR) + "-" + data.get(Calendar.MINUTE) +"-" + data.get(Calendar.SECOND) + ".pdf");
    	 } 
         else return diretorio + "/Recibo" + v.nome + data.getTime() + ".pdf";
     }
	
	public String geraRelatorioSaida(String diretorio, Vendedor v, int idCirculacao)
	{
		 Document document = new Document();
		 Calendar data = Calendar.getInstance();
		 Locale pt = new Locale("en", "CA");
		 NumberFormat nf = NumberFormat.getInstance(pt);
		 //CirculacaoDAO c = new CirculacaoDAO();
         try {
        	 FileOutputStream f;
        	 if(!(System.getProperty("os.name").equals("Linux"))){
        		// diretorio.replaceAll("/", File.separator);
        		 f = new FileOutputStream(diretorio + "\\RelSaida" + v.nome + data.get(Calendar.YEAR) + "-" + (data.get(Calendar.MONTH) + 1) + "-" + data.get(Calendar.DAY_OF_MONTH) + "_" + data.get(Calendar.HOUR) + "-" + data.get(Calendar.MINUTE) +"-" + data.get(Calendar.SECOND) + ".pdf");
        	 }        	
        	 else {
        		 f = new FileOutputStream(diretorio + "/RelSaida" + v.nome + data.getTime() + ".pdf");
        	 }
             PdfWriter.getInstance(document, f );
             document.open();
            
            
             // adicionando um parÃ¡grafo no documento
			Paragraph titulo = new Paragraph("RELATÓRIO DE SAIDA\n\n\n\n");
			titulo.setAlignment(Element.ALIGN_CENTER);
			FontFactory.getFont(FontFactory.TIMES, Font.BOLD);
			document.add(titulo);
			
			//Aqui serÃ¡ feita uma consulta no banco para extrair as informaÃ§Ãµes necessÃ¡rias da circulaÃ§Ã£o
			
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("select  valor_total,vendedor_id,data_hora,qtd_circulando,codigo,produto.nome,preco_final from circulacao,produto_circulando,produto,vendedor where produto_circulando.produto_id = produto.id and circulacao.vendedor_id = vendedor.id and circulacao.id=" + idCirculacao + " and produto_circulando.circulacao_id= " + idCirculacao);
						
			PdfPTable tabela = new PdfPTable(5);
			PdfPCell codigo = new PdfPCell(new Paragraph("Código"));
			PdfPCell nomeProduto = new PdfPCell(new Paragraph("Nome do Produto"));
			PdfPCell precou = new PdfPCell(new Paragraph("Preço Unitário (R$)"));
			PdfPCell qtd = new PdfPCell(new Paragraph("Quantidade"));
			PdfPCell precot = new PdfPCell(new Paragraph("Preço Total (R$)"));
			
			tabela.addCell(codigo);
			tabela.addCell(nomeProduto);
			tabela.addCell(precou);
			tabela.addCell(qtd);
			tabela.addCell(precot);
			
			tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			Double  totaCirculacao = (double) 0;//Esse Ã© o valor total de produtos
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
			
			Paragraph concl = new Paragraph("\n\n        O valor total desta retirada de estoque é de: R$ " + totaCirculacao + "\n\n");
			concl.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(concl);
			
			Paragraph line = new Paragraph("\n\n\n\nAss: _________________________________");
			line.setAlignment(Element.ALIGN_CENTER);
			
			document.add(line);
			Paragraph nomep = new Paragraph("Oséas Oliveira da Silva\n");
			nomep.setAlignment(Element.ALIGN_CENTER);
			document.add(nomep);
			
			document.add(line);			
			Paragraph vend = new Paragraph(v.nome);
			vend.setAlignment(Element.ALIGN_CENTER);
			document.add(vend);
			
			
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
         
         if(!(System.getProperty("os.name").equals("Linux"))){
    		 //diretorio.replaceAll("/", File.separator);
    		 return (diretorio + "\\RelSaida" + v.nome + data.get(Calendar.YEAR) + "-" + (data.get(Calendar.MONTH) + 1) + "-" + data.get(Calendar.DAY_OF_MONTH) + "_" + data.get(Calendar.HOUR) + "-" + data.get(Calendar.MINUTE) +"-" + data.get(Calendar.SECOND) + ".pdf");
    	 } 
         else return diretorio + "/RelSaida" + v.nome + data.getTime() + ".pdf";
     }
	
	
	public String geraRelatorioRetorno(boolean pagoagora, String diretorio, int idVendedor, int idCirculacao,double vr, double vv, double c)
	{
		 Document document = new Document();
		 VendedorDAO vend = new VendedorDAO();
		 CirculacaoDAO circulacao_bd = new CirculacaoDAO();
		 Vendedor v = vend.getVendedor(idVendedor);
		 Calendar data = Calendar.getInstance();
		 Locale pt = new Locale("en", "CA");
		 NumberFormat nf = NumberFormat.getInstance(pt);
		 //CirculacaoDAO c = new CirculacaoDAO();
         try {
        	 FileOutputStream f;
        	 if(!(System.getProperty("os.name").equals("Linux"))){
        		// diretorio.replaceAll("/", File.separator);
        		 f = new FileOutputStream(diretorio + "\\RelRetorno" + v.nome + data.get(Calendar.YEAR) + "-" + (data.get(Calendar.MONTH) + 1) + "-" + data.get(Calendar.DAY_OF_MONTH) + "_" + data.get(Calendar.HOUR) + "-" + data.get(Calendar.MINUTE) +"-" + data.get(Calendar.SECOND) + ".pdf");
        	 }        	
        	 else {
        		 f = new FileOutputStream(diretorio + "/RelReorno" + v.nome + data.getTime() + ".pdf");
        	 }
             PdfWriter.getInstance(document, f );
             document.open();
            
            
             // adicionando um parÃ¡grafo no documento
			Paragraph titulo = new Paragraph("RELATÓRIO DE RETORNO\n\n\n\n");
			titulo.setAlignment(Element.ALIGN_CENTER);
			FontFactory.getFont(FontFactory.TIMES, Font.BOLD);
			document.add(titulo);
			
			//Aqui serÃ¡ feita uma consulta no banco para extrair as informaÃ§Ãµes necessÃ¡rias da circulaÃ§Ã£o
			ProdutoCirculandoDAO pcirculando_bd = new ProdutoCirculandoDAO();
			ArrayList<ProdutoCirculando> listRetorno = pcirculando_bd.ListarProdutosDessaCirculacao(idCirculacao);
		
			PdfPTable tabela = new PdfPTable(5);
			PdfPCell codigo = new PdfPCell(new Paragraph("Código"));
			PdfPCell nomeProduto = new PdfPCell(new Paragraph("Nome do Produto"));
			PdfPCell precou = new PdfPCell(new Paragraph("Preço Unitário (R$)"));
			PdfPCell qtd = new PdfPCell(new Paragraph("Quantidade"));
			PdfPCell precot = new PdfPCell(new Paragraph("Preço Total (R$)"));
			
			tabela.addCell(codigo);
			tabela.addCell(nomeProduto);
			tabela.addCell(precou);
			tabela.addCell(qtd);
			tabela.addCell(precot);
			
			tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
			double totaCirculacao =0;
			for(int i=0;i<listRetorno.size();i++)
			{
				PdfPCell codigoe = new PdfPCell(new Paragraph(listRetorno.get(i).produto.getCodigo()));
				PdfPCell nomeProdutoe = new PdfPCell(new Paragraph(listRetorno.get(i).produto.getNome()));
				double r = listRetorno.get(i).produto.getPrecoFinal() * 100;
				Double round = (double) Math.round(r);
				round = round/100;
				PdfPCell precoue = new PdfPCell(new Paragraph(round.toString()));
				Integer q = listRetorno.get(i).quantCirculando;
				PdfPCell qtde = new PdfPCell(new Paragraph(q.toString()));
				Double total = listRetorno.get(i).quantCirculando * round * 100;
				total = (double) Math.round(total);
				total = total/100;
				PdfPCell precote = new PdfPCell(new Paragraph(total.toString()));
				
				tabela.addCell(codigoe);
				tabela.addCell(nomeProdutoe);
				tabela.addCell(precoue);
				tabela.addCell(qtde);
				tabela.addCell(precote);
				
				totaCirculacao += total;
				
			}
			
			double circ = totaCirculacao * 100;
			totaCirculacao = (double) Math.round(circ);
			totaCirculacao = totaCirculacao / 100;
			
//Adicionar tabela vendidos
			
			ProdutoVendidoDAO pvendido_bd = new ProdutoVendidoDAO();
			ArrayList<ProdutoCirculando> listVendidos = pvendido_bd.ListarProdutosVendidos(idCirculacao);
		
			PdfPTable tabelav = new PdfPTable(5);
			PdfPCell codigov = new PdfPCell(new Paragraph("Código"));
			PdfPCell nomeProdutov = new PdfPCell(new Paragraph("Nome do Produto"));
			PdfPCell precov = new PdfPCell(new Paragraph("Preço Unitário (R$)"));
			PdfPCell qtdv = new PdfPCell(new Paragraph("Quantidade"));
			PdfPCell precotv = new PdfPCell(new Paragraph("Preço Total (R$)"));
			
			tabelav.addCell(codigov);
			tabelav.addCell(nomeProdutov);
			tabelav.addCell(precov);
			tabelav.addCell(qtdv);
			tabelav.addCell(precotv);
			
			tabelav.setHorizontalAlignment(Element.ALIGN_CENTER);
			double totaCirculacao2 =0;
			for(int i=0;i<listVendidos.size();i++)
			{
				PdfPCell codigoe = new PdfPCell(new Paragraph(listVendidos.get(i).produto.getCodigo()));
				PdfPCell nomeProdutoe = new PdfPCell(new Paragraph(listVendidos.get(i).produto.getNome()));
				double r = listVendidos.get(i).produto.getPrecoFinal() * 100;
				Double round = (double) Math.round(r);
				round = round/100;
				PdfPCell precoue = new PdfPCell(new Paragraph(round.toString()));
				Integer q1 = listVendidos.get(i).quantCirculando;
				PdfPCell qtde = new PdfPCell(new Paragraph(q1.toString()));
				Double total = listVendidos.get(i).quantCirculando * round * 100;
				total = (double) Math.round(total);
				total = total/100;
				PdfPCell precote = new PdfPCell(new Paragraph(total.toString()));
				
				tabelav.addCell(codigoe);
				tabelav.addCell(nomeProdutoe);
				tabelav.addCell(precoue);
				tabelav.addCell(qtde);
				tabelav.addCell(precote);
				
				totaCirculacao2 += total;
			}
			
		
			double circ2 = totaCirculacao2 * 100;
			totaCirculacao2 = (double) Math.round(circ2);
			totaCirculacao2 = totaCirculacao2 / 100;
			
			//Escrevendo Relatorio Retorno
			
			Paragraph p = new Paragraph("        Os seguintes produtos estão retornando ao estoque na data "+ data.get(Calendar.DAY_OF_MONTH) + "/" + (data.get(Calendar.MONTH) + 1) + "/" + data.get(Calendar.YEAR) + " no horário " + data.get(Calendar.HOUR_OF_DAY) + "h e " + data.get(Calendar.MINUTE) + "min através do vendedor " + v.nome + ":\n\n");
			p.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(p);
			document.add(tabela);
			
			Paragraph concl = new Paragraph("\n\n        O valor total que está sendo retornado é de: R$ " + totaCirculacao + ". Os seguintes produtos foram vendidos:\n\n");
			concl.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(concl);
			document.add(tabelav);
			
			double aux= vv * 100;
			vv = (double) Math.round(aux);
			vv = vv / 100;
			
			double aux2= c * 100;
			c = (double) Math.round(aux2);
			c = c / 100;
			
			Paragraph concl2;
			if(pagoagora)concl2 = new Paragraph("\n\n        O valor total que foi vendido é de: R$ " + vv + ".  E a comissão a ser paga ao vendedor é de: R$ " + c+ ", que já foi pago.\n\n");
			else concl2 = new Paragraph("\n\n        O valor total que foi vendido é de: R$ " + vv + ".  E a comissão a ser paga ao vendedor é de: R$ " + c+ ", que será pago posteriormente.\n\n");
			concl2.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(concl2);
			
			Paragraph line = new Paragraph("\n\n\nAss: _________________________________");
			line.setAlignment(Element.ALIGN_CENTER);
			
			document.add(line);
			Paragraph nomep = new Paragraph("Oséas Oliveira da Silva\n");
			nomep.setAlignment(Element.ALIGN_CENTER);
			document.add(nomep);
			
			document.add(line);			
			Paragraph nomeVend = new Paragraph(v.nome);
			nomeVend.setAlignment(Element.ALIGN_CENTER);
			document.add(nomeVend);
			
			//Finaliza retorno
			if(!pagoagora)vend.updateValorAReceberVendedor(v.getID(),v.getValorAReceber()+c);
			pcirculando_bd.retornarProdutosParaEstoque(idCirculacao);
			pcirculando_bd.RemoverProdutosDessaCirculacao(idCirculacao);
			pvendido_bd.RemoverProdutosVendidosDessaCirculacao(idCirculacao);
			circulacao_bd.RemoverCirculacao(idCirculacao);
			
			
         }
         catch(DocumentException de) {
             System.err.println(de.getMessage());
         }
         catch(IOException ioe) {
             System.err.println(ioe.getMessage());
         }
         document.close();
         
         if(!(System.getProperty("os.name").equals("Linux"))){
    		 //diretorio.replaceAll("/", File.separator);
    		 return (diretorio + "\\RelRetorno" + v.nome + data.get(Calendar.YEAR) + "-" + (data.get(Calendar.MONTH) + 1) + "-" + data.get(Calendar.DAY_OF_MONTH) + "_" + data.get(Calendar.HOUR) + "-" + data.get(Calendar.MINUTE) +"-" + data.get(Calendar.SECOND) + ".pdf");
    	 } 
         else return diretorio + "/RelRetorno" + v.nome + data.getTime() + ".pdf";
     }
	
	
}
	


