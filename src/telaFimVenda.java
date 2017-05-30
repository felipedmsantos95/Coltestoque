

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class telaFimVenda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField valorRetirado;
	private JTextField valorVendido;
	private JTextField comissao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaFimVenda window = new telaFimVenda(6,4);//Esse id será extraido da tabela
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
	public telaFimVenda(int idVendedor, int idCirculacao) {
		initialize(idVendedor, idCirculacao);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int idVendedor, int idCirculacao) {
		CirculacaoDAO circ = new CirculacaoDAO();
		VendedorDAO vend = new VendedorDAO();
		ProdutoVendidoDAO pv = new ProdutoVendidoDAO();

		this.setTitle("Resultado Venda");
		this.setBounds(100, 100, 800, 550);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblValorRetiradoEm = new JLabel("Valor Retirado (R$):");
		lblValorRetiradoEm.setBounds(216, 117, 138, 14);
		this.getContentPane().add(lblValorRetiradoEm);
		
		JLabel lblValorVendido = new JLabel("Valor Vendido (R$):");
		lblValorVendido.setBounds(216, 176, 138, 14);
		this.getContentPane().add(lblValorVendido);
		
		JLabel lblComissoDoVendedor = new JLabel("Comissão (R$):");
		lblComissoDoVendedor.setBounds(245, 239, 109, 14);
		this.getContentPane().add(lblComissoDoVendedor);
		
		valorRetirado = new JTextField();
		valorRetirado.setEditable(false);
		valorRetirado.setForeground(Color.BLACK);
		String vr = String.valueOf(circ.getValorCirculacao(idCirculacao));
		valorRetirado.setText(vr);
		valorRetirado.setBounds(394, 109, 101, 32);
		this.getContentPane().add(valorRetirado);
		valorRetirado.setColumns(10);
		
		valorVendido = new JTextField();
		valorVendido.setEditable(false);
		String vv = String.valueOf(pv.getValorVendido(idCirculacao));
		valorVendido.setText(vv);
		valorVendido.setBounds(394, 168, 101, 32);
		this.getContentPane().add(valorVendido);
		valorVendido.setColumns(10);
		
		comissao = new JTextField();
		comissao.setEditable(false);
		String c = String.valueOf(vend.getComissaoVendedor(idVendedor));
		comissao.setText(c);
		comissao.setBounds(394, 231, 101, 32);
		this.getContentPane().add(comissao);
		comissao.setColumns(10);
		
		JButton btnFinalizarEPagar = new JButton("Gerar Recibo");
		btnFinalizarEPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaImpressaoTermo termoRetornoPagaAgora = new telaImpressaoTermo(idVendedor);
				termoRetornoPagaAgora.setVisible(true);
				
			}
		});
		btnFinalizarEPagar.setBounds(505, 441, 257, 46);
		this.getContentPane().add(btnFinalizarEPagar);
		
		JButton btnFinalizarEPagar_1 = new JButton("Gerar Relatório");
		btnFinalizarEPagar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaImpressaoTermo termoRetornoPagaDepois = new telaImpressaoTermo(vend.getVendedor(idVendedor), idCirculacao);
				termoRetornoPagaDepois.setVisible(true);
				
			}
		});
		btnFinalizarEPagar_1.setBounds(505, 367, 257, 46);
		this.getContentPane().add(btnFinalizarEPagar_1);
	}

	

}
