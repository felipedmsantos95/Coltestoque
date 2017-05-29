

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaFimVenda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmResultadoVenda;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaFimVenda window = new telaFimVenda();
					window.frmResultadoVenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telaFimVenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmResultadoVenda = new JFrame();
		frmResultadoVenda.setTitle("Resultado Venda");
		frmResultadoVenda.setBounds(100, 100, 450, 300);
		frmResultadoVenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmResultadoVenda.getContentPane().setLayout(null);
		
		JLabel lblValorRetiradoEm = new JLabel("Valor Retirado em Produto");
		lblValorRetiradoEm.setBounds(52, 61, 183, 14);
		frmResultadoVenda.getContentPane().add(lblValorRetiradoEm);
		
		JLabel lblValorVendido = new JLabel("Valor vendido");
		lblValorVendido.setBounds(52, 86, 114, 14);
		frmResultadoVenda.getContentPane().add(lblValorVendido);
		
		JLabel lblComissoDoVendedor = new JLabel("Comiss\u00E3o do vendedor");
		lblComissoDoVendedor.setBounds(52, 111, 114, 14);
		frmResultadoVenda.getContentPane().add(lblComissoDoVendedor);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(210, 58, 86, 20);
		frmResultadoVenda.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(210, 86, 86, 20);
		frmResultadoVenda.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(210, 111, 86, 20);
		frmResultadoVenda.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnFinalizarEPagar = new JButton("Finalizar e pagar vendedor agora");
		btnFinalizarEPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//telaImpressaoTermo termoRetornoPagaAgora = new telaImpressaoTermo();
				//termoRetornoPagaAgora.setVisible(true);
			}
		});
		btnFinalizarEPagar.setBounds(223, 180, 201, 23);
		frmResultadoVenda.getContentPane().add(btnFinalizarEPagar);
		
		JButton btnFinalizarEPagar_1 = new JButton("Finalizar e pagar vendedor depois");
		btnFinalizarEPagar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//telaImpressaoTermo termoRetornoPagaDepois = new telaImpressaoTermo();
				//termoRetornoPagaDepois.setVisible(true);
			}
		});
		btnFinalizarEPagar_1.setBounds(223, 211, 201, 23);
		frmResultadoVenda.getContentPane().add(btnFinalizarEPagar_1);
	}

	

}
