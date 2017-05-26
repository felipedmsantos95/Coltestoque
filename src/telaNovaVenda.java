

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class telaNovaVenda {

	private JFrame frmNovaVenda;
	private JTextField textField;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaNovaVenda window = new telaNovaVenda();
					window.frmNovaVenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telaNovaVenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNovaVenda = new JFrame();
		frmNovaVenda.setTitle("Nova Venda");
		frmNovaVenda.setBounds(100, 100, 491, 356);
		frmNovaVenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNovaVenda.getContentPane().setLayout(null);
		
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setBounds(21, 30, 83, 14);
		frmNovaVenda.getContentPane().add(lblVendedor);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(114, 27, 285, 20);
		frmNovaVenda.getContentPane().add(comboBox);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(21, 58, 46, 14);
		frmNovaVenda.getContentPane().add(lblData);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(73, 55, 86, 20);
		frmNovaVenda.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblCodigoProduto = new JLabel("Codigo Produto");
		lblCodigoProduto.setBounds(24, 97, 80, 14);
		frmNovaVenda.getContentPane().add(lblCodigoProduto);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(160, 97, 86, 14);
		frmNovaVenda.getContentPane().add(lblQuantidade);
		
		textField_2 = new JTextField();
		textField_2.setBounds(160, 124, 86, 20);
		frmNovaVenda.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAdicionarProduto = new JButton("Adicionar Produto");
		btnAdicionarProduto.setBounds(282, 111, 117, 23);
		frmNovaVenda.getContentPane().add(btnAdicionarProduto);
		
		JLabel lblListaDeSada = new JLabel("Lista de sa\u00EDda");
		lblListaDeSada.setBounds(21, 159, 83, 14);
		frmNovaVenda.getContentPane().add(lblListaDeSada);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaImpressaoTermo termoVenda = new telaImpressaoTermo();
				termoVenda.setVisible(true);
			}
		});
		btnConfirmar.setBounds(365, 283, 89, 23);
		frmNovaVenda.getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(266, 283, 89, 23);
		frmNovaVenda.getContentPane().add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 184, 433, 75);
		frmNovaVenda.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO", "PRE\u00C7O", "QUANT RETIRADA", "PRE\u00C7O FINAL"
			}
		));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(44, 122, 94, 25);
		frmNovaVenda.getContentPane().add(comboBox_1);
	}
}