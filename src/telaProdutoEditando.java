import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
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
	 */
	public telaProdutoEditando() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 238, 448, 35);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("Confirmar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Locale pt = new Locale("pt", "PT");
						NumberFormat nf = NumberFormat.getInstance(pt);
						
						//Produto produto = new Produto(codigo.getText(), textField.getText(), nf.parse(compra.getText()).dou)
						
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
		
		JEditorPane descricao = new JEditorPane();
		descricao.setBounds(25, 86, 368, 62);
		getContentPane().add(descricao);
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setBounds(25, 66, 87, 19);
		getContentPane().add(lblDescrio);
		{
			JLabel lblPreoCompra = new JLabel("Preço Compra:");
			lblPreoCompra.setBounds(25, 154, 109, 19);
			getContentPane().add(lblPreoCompra);
		}
		{
			compra = new JTextField();
			compra.setBounds(25, 170, 109, 19);
			getContentPane().add(compra);
			compra.setColumns(10);
		}
		{
			JLabel lblPercentualLucro = new JLabel("Percentual Lucro:");
			lblPercentualLucro.setBounds(25, 197, 130, 19);
			getContentPane().add(lblPercentualLucro);
		}
		{
			lucro = new JTextField();
			lucro.setColumns(10);
			lucro.setBounds(25, 213, 109, 19);
			getContentPane().add(lucro);
		}
		{
			JLabel lblPreoVenda = new JLabel("Preço Venda:");
			lblPreoVenda.setBounds(284, 170, 109, 19);
			getContentPane().add(lblPreoVenda);
		}
		{
			venda = new JTextField();
			venda.setColumns(10);
			venda.setBounds(284, 186, 109, 19);
			getContentPane().add(venda);
		}
	}
}
