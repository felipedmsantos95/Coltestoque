

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaRetornoVenda {

	private JFrame frmRetornoDeVenda;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaRetornoVenda window = new telaRetornoVenda();
					window.frmRetornoDeVenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telaRetornoVenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRetornoDeVenda = new JFrame();
		frmRetornoDeVenda.setTitle("Retorno de Venda");
		frmRetornoDeVenda.setBounds(100, 100, 475, 333);
		frmRetornoDeVenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRetornoDeVenda.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setBounds(25, 163, 404, 86);
		frmRetornoDeVenda.getContentPane().add(table);
		
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setBounds(25, 25, 46, 14);
		frmRetornoDeVenda.getContentPane().add(lblVendedor);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(89, 22, 86, 20);
		frmRetornoDeVenda.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblDataSada = new JLabel("Data Sa\u00EDda");
		lblDataSada.setBounds(199, 25, 86, 14);
		frmRetornoDeVenda.getContentPane().add(lblDataSada);
		
		JLabel lblDataRetorno = new JLabel("Data Retorno");
		lblDataRetorno.setBounds(199, 48, 76, 14);
		frmRetornoDeVenda.getContentPane().add(lblDataRetorno);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setBounds(282, 22, 86, 20);
		frmRetornoDeVenda.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(282, 50, 86, 20);
		frmRetornoDeVenda.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(30, 105, 86, 20);
		frmRetornoDeVenda.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(159, 105, 86, 20);
		frmRetornoDeVenda.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnRetornar = new JButton("Retornar");
		btnRetornar.setBounds(282, 104, 89, 23);
		frmRetornoDeVenda.getContentPane().add(btnRetornar);
		
		JLabel lblQuantidadeProduto = new JLabel("Codigo Produto");
		lblQuantidadeProduto.setBounds(25, 85, 126, 14);
		frmRetornoDeVenda.getContentPane().add(lblQuantidadeProduto);
		
		JLabel lblQuantidadeRetornada = new JLabel("Quantidade Retornada");
		lblQuantidadeRetornada.setBounds(143, 85, 155, 14);
		frmRetornoDeVenda.getContentPane().add(lblQuantidadeRetornada);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaFimVenda fimVenda = new telaFimVenda();
				fimVenda.setVisible(true);
			}
		});
		btnConfirmar.setBounds(340, 260, 89, 23);
		frmRetornoDeVenda.getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(241, 260, 89, 23);
		frmRetornoDeVenda.getContentPane().add(btnCancelar);
		
		JLabel lblListaDeRetorno = new JLabel("Lista de Retorno de Produtos");
		lblListaDeRetorno.setBounds(25, 138, 155, 14);
		frmRetornoDeVenda.getContentPane().add(lblListaDeRetorno);
	}

}
