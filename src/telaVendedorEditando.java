
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class telaVendedorEditando extends JFrame{

	private JTextField textField;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaVendedorEditando window = new telaVendedorEditando();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telaVendedorEditando(int id_vendedor) {
		initialize();
	}
	
	public telaVendedorEditando() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Adicionando/Editando Vendedor");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo");
		lblNomeCompleto.setBounds(23, 38, 113, 14);
		this.getContentPane().add(lblNomeCompleto);
		
		textField = new JTextField();
		textField.setBounds(119, 35, 253, 20);
		this.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(324, 227, 89, 23);
		this.getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(225, 227, 89, 23);
		this.getContentPane().add(btnCancelar);
		
		JLabel lblComisso = new JLabel("Comiss\u00E3o (%)");
		lblComisso.setBounds(23, 79, 89, 14);
		this.getContentPane().add(lblComisso);
		
		textField_3 = new JTextField();
		textField_3.setBounds(119, 76, 86, 20);
		this.getContentPane().add(textField_3);
		textField_3.setColumns(10);
	}

}