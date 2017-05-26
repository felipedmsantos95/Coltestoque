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
		initialize ();
		
	}
	
	//chamo qunado quero criar um produto
	public telaProdutoEditando() {
		initialize ();
		
	}
	private void initialize ()
	{
		setBounds(100, 100, 450, 300);
		
		JEditorPane descricao = new JEditorPane();
		descricao.setBounds(25, 86, 368, 62);
		getContentPane().add(descricao);
		
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 238, 448, 35);
			getContentPane().add(buttonPane);
			
			{
				venda = new JTextField();
				venda.setEditable(false);
				venda.setColumns(10);
				venda.setBounds(284, 186, 109, 19);
				getContentPane().add(venda);
			}
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("Confirmar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Locale pt = new Locale("pt", "PT");
						NumberFormat nf = NumberFormat.getInstance(pt);
						ProdutoDAO p = new ProdutoDAO();
						
						try {
							
							if(!(lucro.getText().trim().equals("")))
								{
									Produto produto = new Produto(codigo.getText(), textField.getText(), nf.parse(compra.getText()).doubleValue(), nf.parse(lucro.getText()).doubleValue(),  descricao.getText());
									if(p.adicionarProduto(produto)){
										JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
										double r = produto.precoFinal * 100;
										double round = Math.round(r);
										round = round/100;
										
										String preco = String.valueOf(round);								
										
										venda.setText("R$ " + preco);
									}
									
									
								}
							else
							{
								Produto produto = new Produto(codigo.getText(), textField.getText(), nf.parse(compra.getText()).doubleValue(), descricao.getText());
								if(p.adicionarProduto(produto)){
									JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
									double r = produto.precoFinal * 100;
									double round = Math.round(r);
									round = round/100;
									
									String preco = String.valueOf(round);								
									
									venda.setText("R$ " + preco);
								}
								
							}
						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(null,"Houve algum erro no cadastro do produto, verifique se os campos foram corretamente preenchidos.");
							e1.printStackTrace();
						}
						
					}
				});
				okButton.setBounds(323, 5, 113, 25);
				okButton.setActionCommand("Confirmar");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBounds(206, 5, 105, 25);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						telaProdutoEditando.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
		{
			textField = new JTextField();
			textField.setBounds(25, 41, 237, 19);
			getContentPane().add(textField);
			textField.setColumns(10);
		}
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(25, 24, 66, 19);
		getContentPane().add(lblNome);
		{
			codigo = new JTextField();
			codigo.setBounds(306, 41, 87, 19);
			getContentPane().add(codigo);
			codigo.setColumns(10);
		}
		{
			JLabel lblCdigo = new JLabel("Código:");
			lblCdigo.setBounds(306, 26, 66, 15);
			getContentPane().add(lblCdigo);
		}
		
		
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setBounds(25, 66, 87, 19);
		getContentPane().add(lblDescrio);
		{
			JLabel lblPreoCompra = new JLabel("Preço Compra:");
			lblPreoCompra.setBounds(25, 144, 109, 19);
			getContentPane().add(lblPreoCompra);
		}
		{
			compra = new JTextField();
			compra.setBounds(25, 160, 109, 19);
			getContentPane().add(compra);
			compra.setColumns(10);
		}
		{
			JLabel lblPercentualLucro = new JLabel("% Lucro:");
			lblPercentualLucro.setBounds(25, 185, 130, 19);
			getContentPane().add(lblPercentualLucro);
		}
		{
			lucro = new JTextField();
			lucro.setColumns(10);
			lucro.setBounds(25, 216, 109, 19);
			getContentPane().add(lucro);
		}
		{
			JLabel lblPreoVenda = new JLabel("Preço Venda:");
			lblPreoVenda.setBounds(284, 170, 109, 19);
			getContentPane().add(lblPreoVenda);
		}
		
		
		JLabel lbldeixarVazioPara = new JLabel("(Deixar vazio para padrão)");
		lbldeixarVazioPara.setBounds(22, 201, 196, 15);
		getContentPane().add(lbldeixarVazioPara);
	}
}