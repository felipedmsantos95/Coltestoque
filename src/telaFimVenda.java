

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
	private double vr;
	private double vv;
	private double c;
	private int idCirculacao;
	private int idVendedor;
	private CirculacaoDAO circ = new CirculacaoDAO();
	private VendedorDAO vend = new VendedorDAO();
	private ProdutoVendidoDAO pv = new ProdutoVendidoDAO();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public telaFimVenda(int idVendedor, int idCirculacao) {
		this.idVendedor = idVendedor;
		this.idCirculacao = idCirculacao;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		

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
		vr = circ.getValorCirculacao(idCirculacao);//valor da retirada
		//double valorRet = vr * 100;
		//Double vendr = (double) Math.round(valorRet);
		//vendr = vendr/100;
		valorRetirado.setText(String.valueOf(vr)); //Se der besteira descomentar linhas anteriores
		valorRetirado.setBounds(394, 109, 101, 32);
		this.getContentPane().add(valorRetirado);
		valorRetirado.setColumns(10);
		
		valorVendido = new JTextField();
		valorVendido.setEditable(false);
		vv = pv.getValorVendido(idCirculacao);//valorVendido
		double valorVend = vv * 100;
		Double vendv = (double) Math.round(valorVend);
		vendv = vendv/100;
		valorVendido.setText(String.valueOf(vendv));
		valorVendido.setBounds(394, 168, 101, 32);
		this.getContentPane().add(valorVendido);
		valorVendido.setColumns(10);
		
		comissao = new JTextField();
		comissao.setEditable(false);
		c = (vend.getPercentualComissaoVendedor(idVendedor)/100)*vv;//comissaoVendedor
		double r =c* 100;
		Double round = (double) Math.round(r);
		round = round/100;
		comissao.setText(String.valueOf(round));
		comissao.setBounds(394, 231, 101, 32);
		this.getContentPane().add(comissao);
		comissao.setColumns(10);
		
		JButton btnFinalizarEPagar = new JButton("Gerar Relatório e Pagar Vendedor Depois");
		btnFinalizarEPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaImpressaoTermo termoRetornoPagaAgora = new telaImpressaoTermo(false,vend.getVendedor(idVendedor), idCirculacao,vr,vv,c);
				termoRetornoPagaAgora.setVisible(true);
				dispose();
			}
		});
		btnFinalizarEPagar.setBounds(410, 441, 352, 46);
		this.getContentPane().add(btnFinalizarEPagar);
		
		JButton btnFinalizarEPagar_1 = new JButton("Gerar Relatório e Pagar Vendedor Agora");
		btnFinalizarEPagar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaImpressaoTermo termoRetornoPagaDepois = new telaImpressaoTermo(true,vend.getVendedor(idVendedor), idCirculacao,vr,vv,c);
				termoRetornoPagaDepois.setVisible(true);
				dispose();
			}
		});
		btnFinalizarEPagar_1.setBounds(410, 367, 352, 46);
		this.getContentPane().add(btnFinalizarEPagar_1);
	}

	

}
