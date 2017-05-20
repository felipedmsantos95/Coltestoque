

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class telaVendedores {

	private JFrame frmVendedores;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaVendedores window = new telaVendedores();
					window.frmVendedores.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telaVendedores() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVendedores = new JFrame();
		frmVendedores.setTitle("Vendedores");
		frmVendedores.setBounds(100, 100, 450, 300);
		frmVendedores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVendedores.getContentPane().setLayout(null);
		
		JButton btnAdicionarNovoVendedor = new JButton("Adicionar Novo Vendedor");
		btnAdicionarNovoVendedor.setBounds(40, 26, 180, 23);
		frmVendedores.getContentPane().add(btnAdicionarNovoVendedor);
		
		table = new JTable();
		table.setBounds(32, 69, 250, 181);
		frmVendedores.getContentPane().add(table);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(335, 104, 89, 23);
		frmVendedores.getContentPane().add(btnEditar);
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.setBounds(335, 138, 89, 23);
		frmVendedores.getContentPane().add(btnPagar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(335, 171, 89, 23);
		frmVendedores.getContentPane().add(btnExcluir);
		
		JLabel lblCodigoVendedor = new JLabel("Codigo Vendedor");
		lblCodigoVendedor.setBounds(335, 43, 89, 14);
		frmVendedores.getContentPane().add(lblCodigoVendedor);
		
		textField = new JTextField();
		textField.setBounds(335, 66, 86, 20);
		frmVendedores.getContentPane().add(textField);
		textField.setColumns(10);
	}

}
