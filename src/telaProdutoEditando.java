import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class telaProdutoEditando extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField codigo;
	private JTextField compra;
	private JTextField lucro;
	private JTextField venda;
	private JTextField quantidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			telaProdutoEditando dialog = new telaProdutoEditando();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 *
	 */
	//chamo quando quero editar um produto
	public telaProdutoEditando(int id_produto) {
		initializeEditando(id_produto);
		
	}
	
	//chamo qunado quero criar um produto
	public telaProdutoEditando() {
		initialize ();
		
	}
	private void initialize ()
	{
		setBounds(100, 100, 800, 550);
		
		JEditorPane descricao = new JEditorPane();
		descricao.setBounds(25, 86, 747, 146);
		getContentPane().add(descricao);
		Locale pt = new Locale("en", "CA");
		NumberFormat nf = NumberFormat.getInstance(pt);
		
				
		
			
		
		
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(-11, 488, 809, 35);
			getContentPane().add(buttonPane);
			
			{
				venda = new JTextField();
				venda.setEditable(false);
				venda.setColumns(10);
				venda.setBounds(506, 370, 109, 28);
				getContentPane().add(venda);
			}
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("Confirmar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ProdutoDAO p = new ProdutoDAO();
							
						try {
							
							if(!(lucro.getText().trim().equals("")))
								{
								Produto produtol = new Produto(codigo.getText(), textField.getText(), nf.parse(compra.getText()).doubleValue(), nf.parse(lucro.getText()).doubleValue(),  descricao.getText());
																	
									if(p.adicionarProduto(produtol)){
										Locale pt = new Locale("en", "CA");
										NumberFormat nf = NumberFormat.getInstance(pt);
										
										EstoqueDAO estoque_bd = new EstoqueDAO();										
										estoque_bd.adicionarProdutoEstoque(codigo.getText(), nf.parse(quantidade.getText()).intValue());
										double r = produtol.precoFinal * 100;
										double precoFinalExibir = Math.round(r);
										precoFinalExibir = precoFinalExibir/100;
										String preco = String.valueOf(precoFinalExibir);								
										venda.setText("R$ " + preco);
										JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
										telaEstoque tela = new telaEstoque();
										tela.setVisible(true);
										dispose();
									}
									
									
								}
							else
							{
								Produto produtos = new Produto(codigo.getText(), textField.getText(), nf.parse(compra.getText()).doubleValue(), descricao.getText());
								EstoqueDAO estoque_bd = new EstoqueDAO();
															
								if(p.adicionarProduto(produtos)){
									Locale pt = new Locale("en", "CA");
									NumberFormat nf = NumberFormat.getInstance(pt);
									
									estoque_bd.adicionarProdutoEstoque(codigo.getText(), nf.parse(quantidade.getText()).intValue());
									
									double r = produtos.precoFinal * 100;
									double precoFinalExibir = Math.round(r);
									precoFinalExibir = precoFinalExibir/100;
									String preco = String.valueOf(precoFinalExibir);
									venda.setText("R$ " + preco);
									JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
									telaEstoque tela = new telaEstoque();
									tela.setVisible(true);
									dispose();
								}
								
							}
						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(null,"Houve algum erro no cadastro do produto, verifique se os campos foram corretamente preenchidos.");
							e1.printStackTrace();
						}
						
					}
				});
				okButton.setBounds(668, 5, 113, 25);
				okButton.setActionCommand("Confirmar");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBounds(540, 5, 105, 25);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						telaProdutoEditando.this.dispose();
						telaEstoque estoque = new telaEstoque();
						estoque.setVisible(true);
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
		{
			textField = new JTextField();
			textField.setBounds(25, 41, 459, 19);
			getContentPane().add(textField);
			textField.setColumns(10);
		}
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(25, 24, 66, 19);
		getContentPane().add(lblNome);
		{
			codigo = new JTextField();
			codigo.setBounds(580, 41, 135, 19);
			getContentPane().add(codigo);
			codigo.setColumns(10);
		}
		{
			JLabel lblCdigo = new JLabel("Código:");
			lblCdigo.setBounds(580, 26, 66, 15);
			getContentPane().add(lblCdigo);
		}
		
		
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setBounds(25, 66, 87, 19);
		getContentPane().add(lblDescrio);
		{
			JLabel lblPreoCompra = new JLabel("Preço Compra:");
			lblPreoCompra.setBounds(49, 262, 109, 19);
			getContentPane().add(lblPreoCompra);
		}
		{
			compra = new JTextField();
			compra.setBounds(49, 293, 120, 28);
			getContentPane().add(compra);
			compra.setColumns(10);
		}
		{
			JLabel lblPercentualLucro = new JLabel("% Lucro:");
			lblPercentualLucro.setBounds(49, 339, 130, 19);
			getContentPane().add(lblPercentualLucro);
		}
		{
			lucro = new JTextField();
			lucro.setColumns(10);
			lucro.setBounds(49, 370, 130, 28);
			getContentPane().add(lucro);
		}
		{
			JLabel lblPreoVenda = new JLabel("Preço Venda:");
			lblPreoVenda.setBounds(506, 339, 109, 19);
			getContentPane().add(lblPreoVenda);
		}
		
		
		JLabel lbldeixarVazioPara = new JLabel("(Deixar vazio para padrão)");
		lbldeixarVazioPara.setBounds(25, 405, 196, 15);
		getContentPane().add(lbldeixarVazioPara);
		
		quantidade = new JTextField();
		quantidade.setBounds(506, 293, 87, 28);
		getContentPane().add(quantidade);
		quantidade.setColumns(10);
		
		JLabel lblQuantidadeNoEstoque = new JLabel("Quantidade:");
		lblQuantidadeNoEstoque.setBounds(506, 264, 188, 15);
		getContentPane().add(lblQuantidadeNoEstoque);
	}
	
	
	private void initializeEditando (int id)
	{
		
		ProdutoDAO produtoEditando = new ProdutoDAO();
			
		//Aqui começa a brincadeira
		setBounds(100, 100, 800, 550);
		
		JEditorPane descricao = new JEditorPane();
		descricao.setText(produtoEditando.getProduto(id).descricao);
		descricao.setBounds(25, 86, 747, 146);
		getContentPane().add(descricao);
		
		Locale pt = new Locale("en", "CA");
		NumberFormat nf = NumberFormat.getInstance(pt);		
			
		
		
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(-11, 488, 809, 35);
			getContentPane().add(buttonPane);
			
			{
				venda = new JTextField();
				venda.setEditable(false);
				double r = produtoEditando.getProduto(id).precoFinal * 100;
				double round = Math.round(r);
				round = round/100;		
				String preco = String.valueOf(round);
				venda.setText(preco);
				venda.setColumns(10);
				venda.setBounds(506, 370, 109, 28);
				getContentPane().add(venda);
			}
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("Confirmar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ProdutoDAO p = new ProdutoDAO();
							
						try {
							if(p.atualizarProduto(id,codigo.getText(), textField.getText(), nf.parse(compra.getText()).doubleValue(), nf.parse(lucro.getText()).doubleValue(),  descricao.getText())){
								JOptionPane.showMessageDialog(null,"Produto atualizado com sucesso!");
								//telaEstoque tela = new telaEstoque();
								double r = produtoEditando.getProduto(id).precoFinal * 100;
								double round = Math.round(r);
								round = round/100;
									
								String preco = String.valueOf(round);								
									
								venda.setText("R$ " + preco);
								telaEstoque tela = new telaEstoque();
								tela.setVisible(true);
								dispose();
							}
							
								
						}
						 catch (ParseException e1) {
							JOptionPane.showMessageDialog(null,"Houve algum erro no cadastro do produto, verifique se os campos foram corretamente preenchidos.");
							e1.printStackTrace();
						}
						
					}
				});
				okButton.setBounds(668, 5, 113, 25);
				okButton.setActionCommand("Confirmar");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBounds(540, 5, 105, 25);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						telaProdutoEditando.this.dispose();
						telaEstoque estoque = new telaEstoque();
						estoque.setVisible(true);
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
		{
			textField = new JTextField();
			textField.setBounds(25, 41, 459, 19);
			textField.setText(produtoEditando.getProduto(id).getNome());
			getContentPane().add(textField);
			textField.setColumns(10);
		}
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(25, 24, 66, 19);
		getContentPane().add(lblNome);
		{
			codigo = new JTextField();
			codigo.setBounds(580, 41, 135, 19);
			codigo.setText(produtoEditando.getProduto(id).getCodigo());
			getContentPane().add(codigo);
			codigo.setColumns(10);
		}
		{
			JLabel lblCdigo = new JLabel("Código:");
			lblCdigo.setBounds(580, 26, 66, 15);
			getContentPane().add(lblCdigo);
		}
		
		
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setBounds(25, 66, 87, 19);
		getContentPane().add(lblDescrio);
		{
			JLabel lblPreoCompra = new JLabel("Preço Compra:");
			lblPreoCompra.setBounds(49, 262, 109, 19);
			getContentPane().add(lblPreoCompra);
		}
		{
			compra = new JTextField();
			compra.setBounds(49, 293, 120, 28);
			String str = String.valueOf(produtoEditando.getProduto(id).getPrecoCompra());
			compra.setText(str);
			getContentPane().add(compra);
			compra.setColumns(10);
		}
		{
			JLabel lblPercentualLucro = new JLabel("% Lucro:");
			lblPercentualLucro.setBounds(49, 339, 130, 19);
			getContentPane().add(lblPercentualLucro);
		}
		{
			lucro = new JTextField();
			lucro.setColumns(10);
			String str = String.valueOf(produtoEditando.getProduto(id).getPercentLucro());
			lucro.setText(str);
			lucro.setBounds(49, 370, 130, 28);
			getContentPane().add(lucro);
		}
		{
			JLabel lblPreoVenda = new JLabel("Preço Venda:");
			lblPreoVenda.setBounds(506, 339, 109, 19);
			getContentPane().add(lblPreoVenda);
		}
		
		
		JLabel lbldeixarVazioPara = new JLabel("(Deixar vazio para padrão)");
		lbldeixarVazioPara.setBounds(25, 405, 196, 15);
		getContentPane().add(lbldeixarVazioPara);
		EstoqueDAO est = new EstoqueDAO();
		
		
		quantidade = new JTextField();
		quantidade.setBounds(506, 293, 87, 28);
		String str = String.valueOf(est.getQuantidadeEstoque(id));
		quantidade.setText(str);
		getContentPane().add(quantidade);
		quantidade.setColumns(10);
		
		JLabel lblQuantidadeNoEstoque = new JLabel("Quantidade:");
		lblQuantidadeNoEstoque.setBounds(506, 264, 188, 15);
		getContentPane().add(lblQuantidadeNoEstoque);
	}
}	

	
