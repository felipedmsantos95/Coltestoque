
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class telaEstoque {

	private JFrame frmEstoque;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaEstoque window = new telaEstoque();
					window.frmEstoque.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telaEstoque() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEstoque = new JFrame();
		frmEstoque.setTitle("Estoque");
		frmEstoque.setBounds(100, 100, 450, 300);
		frmEstoque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEstoque.getContentPane().setLayout(null);
		
		JButton btnNovoProduto = new JButton("Novo Produto");
		btnNovoProduto.setBounds(42, 26, 145, 23);
		frmEstoque.getContentPane().add(btnNovoProduto);
		
		JButton btnReposioDeProduto = new JButton("Reposi\u00E7\u00E3o de Produto");
		btnReposioDeProduto.setBounds(212, 26, 150, 23);
		frmEstoque.getContentPane().add(btnReposioDeProduto);
		
		table = new JTable();
		table.setBounds(28, 85, 281, 149);
		frmEstoque.getContentPane().add(table);
		
		JButton btnEditar = new JButton("editar");
		btnEditar.setBounds(335, 134, 89, 23);
		frmEstoque.getContentPane().add(btnEditar);
		
		JButton btnExcluir = new JButton("excluir");
		btnExcluir.setBounds(335, 168, 89, 23);
		frmEstoque.getContentPane().add(btnExcluir);
		
		JLabel lblCodigoProduto = new JLabel("Codigo Produto");
		lblCodigoProduto.setBounds(335, 85, 79, 14);
		frmEstoque.getContentPane().add(lblCodigoProduto);
		
		textField = new JTextField();
		textField.setBounds(335, 103, 86, 20);
		frmEstoque.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
