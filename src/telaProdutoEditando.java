

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class telaProdutoEditando {

	private JFrame frmAdicionandoeditandoProduto;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaProdutoEditando window = new telaProdutoEditando();
					window.frmAdicionandoeditandoProduto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telaProdutoEditando() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdicionandoeditandoProduto = new JFrame();
		frmAdicionandoeditandoProduto.setTitle("Adicionando/Editando Produto");
		frmAdicionandoeditandoProduto.setBounds(100, 100, 450, 300);
		frmAdicionandoeditandoProduto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdicionandoeditandoProduto.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(118, 27, 86, 20);
		frmAdicionandoeditandoProduto.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNomeProduto = new JLabel("nome Produto");
		lblNomeProduto.setBounds(39, 30, 82, 14);
		frmAdicionandoeditandoProduto.getContentPane().add(lblNomeProduto);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(236, 30, 46, 14);
		frmAdicionandoeditandoProduto.getContentPane().add(lblId);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(266, 28, 86, 20);
		frmAdicionandoeditandoProduto.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDescrio = new JLabel("descri\u00E7\u00E3o");
		lblDescrio.setBounds(47, 81, 46, 14);
		frmAdicionandoeditandoProduto.getContentPane().add(lblDescrio);
		
		textField_2 = new JTextField();
		textField_2.setBounds(118, 78, 234, 48);
		frmAdicionandoeditandoProduto.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPreoDeCompra = new JLabel("pre\u00E7o de compra");
		lblPreoDeCompra.setBounds(35, 148, 108, 20);
		frmAdicionandoeditandoProduto.getContentPane().add(lblPreoDeCompra);
		
		JLabel lblPorcentagemDeLucro = new JLabel("porcentagem de lucro");
		lblPorcentagemDeLucro.setBounds(27, 179, 116, 14);
		frmAdicionandoeditandoProduto.getContentPane().add(lblPorcentagemDeLucro);
		
		textField_3 = new JTextField();
		textField_3.setBounds(142, 148, 86, 20);
		frmAdicionandoeditandoProduto.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(142, 176, 86, 20);
		frmAdicionandoeditandoProduto.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblPreoDeVenda = new JLabel("pre\u00E7o de venda");
		lblPreoDeVenda.setBounds(242, 167, 86, 14);
		frmAdicionandoeditandoProduto.getContentPane().add(lblPreoDeVenda);
		
		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setBounds(325, 164, 86, 20);
		frmAdicionandoeditandoProduto.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(322, 227, 89, 23);
		frmAdicionandoeditandoProduto.getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(224, 227, 89, 23);
		frmAdicionandoeditandoProduto.getContentPane().add(btnCancelar);
	}
}
