

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaReposicao {

	private JFrame frmReposioDeEstoque;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaReposicao window = new telaReposicao();
					window.frmReposioDeEstoque.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telaReposicao() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReposioDeEstoque = new JFrame();
		frmReposioDeEstoque.setTitle("Reposi\u00E7\u00E3o de Estoque");
		frmReposioDeEstoque.setBounds(100, 100, 450, 300);
		frmReposioDeEstoque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReposioDeEstoque.getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo do Produto");
		lblCodigo.setBounds(31, 39, 114, 14);
		frmReposioDeEstoque.getContentPane().add(lblCodigo);
		
		textField = new JTextField();
		textField.setBounds(31, 65, 114, 20);
		frmReposioDeEstoque.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblQuantidadeASer = new JLabel("Quantidade");
		lblQuantidadeASer.setBounds(193, 39, 114, 14);
		frmReposioDeEstoque.getContentPane().add(lblQuantidadeASer);
		
		textField_1 = new JTextField();
		textField_1.setBounds(178, 65, 114, 20);
		frmReposioDeEstoque.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnReporNoEstoque = new JButton("Repor");
		btnReporNoEstoque.setBounds(319, 64, 89, 23);
		frmReposioDeEstoque.getContentPane().add(btnReporNoEstoque);
		
		table = new JTable();
		table.setBounds(31, 126, 382, 55);
		frmReposioDeEstoque.getContentPane().add(table);
		
		JLabel lblListaDeReposio = new JLabel("Lista de Reposi\u00E7\u00E3o no estoque");
		lblListaDeReposio.setBounds(31, 101, 201, 14);
		frmReposioDeEstoque.getContentPane().add(lblListaDeReposio);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnConfirmar.setBounds(335, 227, 89, 23);
		frmReposioDeEstoque.getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(236, 227, 89, 23);
		frmReposioDeEstoque.getContentPane().add(btnCancelar);
	}

}
