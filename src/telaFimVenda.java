

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
	private JFrame frmResultadoVenda;
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
		
		frmResultadoVenda = new JFrame();
		frmResultadoVenda.setTitle("Resultado Venda");
		frmResultadoVenda.setBounds(100, 100, 450, 300);
		frmResultadoVenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmResultadoVenda.getContentPane().setLayout(null);
		
		JLabel lblValorRetiradoEm = new JLabel("Valor Retirado (R$):");
		lblValorRetiradoEm.setBounds(84, 60, 138, 14);
		frmResultadoVenda.getContentPane().add(lblValorRetiradoEm);
		
		JLabel lblValorVendido = new JLabel("Valor Vendido (R$):");
		lblValorVendido.setBounds(84, 85, 138, 14);
		frmResultadoVenda.getContentPane().add(lblValorVendido);
		
		JLabel lblComissoDoVendedor = new JLabel("Comissão (R$):");
		lblComissoDoVendedor.setBounds(113, 111, 109, 14);
		frmResultadoVenda.getContentPane().add(lblComissoDoVendedor);
		
		valorRetirado = new JTextField();
		valorRetirado.setForeground(Color.BLACK);
		valorRetirado.setEnabled(false);
		String vr = String.valueOf(circ.getValorCirculacao(idCirculacao));
		valorRetirado.setText(vr);
		valorRetirado.setBounds(230, 58, 86, 20);
		frmResultadoVenda.getContentPane().add(valorRetirado);
		valorRetirado.setColumns(10);
		
		valorVendido = new JTextField();
		valorVendido.setEnabled(false);
		String vv = String.valueOf(pv.getValorVendido(idCirculacao));
		valorVendido.setText(vv);
		valorVendido.setBounds(230, 84, 86, 20);
		frmResultadoVenda.getContentPane().add(valorVendido);
		valorVendido.setColumns(10);
		
		comissao = new JTextField();
		comissao.setEnabled(false);
		String c = String.valueOf(vend.getComissaoVendedor(idVendedor));
		comissao.setText(c);
		comissao.setBounds(230, 109, 86, 20);
		frmResultadoVenda.getContentPane().add(comissao);
		comissao.setColumns(10);
		
		JButton btnFinalizarEPagar = new JButton("Finalizar e Pagar");
		btnFinalizarEPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaImpressaoTermo termoRetornoPagaAgora = new telaImpressaoTermo(idVendedor);
				termoRetornoPagaAgora.setVisible(true);
				
			}
		});
		btnFinalizarEPagar.setBounds(193, 215, 238, 23);
		frmResultadoVenda.getContentPane().add(btnFinalizarEPagar);
		
		JButton btnFinalizarEPagar_1 = new JButton("Finalizar");
		btnFinalizarEPagar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaImpressaoTermo termoRetornoPagaDepois = new telaImpressaoTermo(vend.getVendedor(idVendedor), idCirculacao);
				termoRetornoPagaDepois.setVisible(true);
				
			}
		});
		btnFinalizarEPagar_1.setBounds(193, 180, 238, 23);
		frmResultadoVenda.getContentPane().add(btnFinalizarEPagar_1);
	}

	

}
