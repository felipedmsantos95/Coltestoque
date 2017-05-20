
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JLabel;

public class telaMain {

	private JFrame frmColtestoque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaMain window = new telaMain();
					window.frmColtestoque.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telaMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmColtestoque = new JFrame();
		frmColtestoque.setTitle("Coltestoque");
		frmColtestoque.setBounds(100, 100, 450, 300);
		frmColtestoque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmColtestoque.getContentPane().setLayout(null);
		
		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaEstoque estoque1 = new telaEstoque();
				estoque1.setVisible(true);
			}
		});
		btnEstoque.setBounds(37, 84, 106, 23);
		frmColtestoque.getContentPane().add(btnEstoque);
		
		
		
		JButton btnNovaVenda = new JButton("Nova Venda");
		btnNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaNovaVenda novavenda1 = new telaNovaVenda();
				novavenda1.setVisible(true);
		});
		btnNovaVenda.setBounds(37, 152, 106, 23);
		frmColtestoque.getContentPane().add(btnNovaVenda);
		
		JButton btnVendedores = new JButton("Vendedores");
		btnVendedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaVendedores vendedores1 = new telaVendedores();
				vendedores1.setVisible(true);
		});
		btnVendedores.setBounds(37, 118, 106, 23);
		frmColtestoque.getContentPane().add(btnVendedores);
		
		JList list = new JList();
		list.setBounds(185, 57, 212, 141);
		frmColtestoque.getContentPane().add(list);
		
		JLabel lblVendasEmAberto = new JLabel("Vendas em aberto (Selecione uma abaixo...)");
		lblVendasEmAberto.setBounds(185, 38, 239, 14);
		frmColtestoque.getContentPane().add(lblVendasEmAberto);
		
		JButton btnRetornoDeVenda = new JButton("Retorno de Venda");
		btnRetornoDeVenda.setBounds(278, 209, 119, 23);
		frmColtestoque.getContentPane().add(btnRetornoDeVenda);
	}
}
