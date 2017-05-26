import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JButton;

public class telaMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaMain frame = new telaMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public telaMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(22, 205, 170, 41);
		
		ImageIcon foto = new ImageIcon(telaMain.class.getResource("/img/coltech.png"));
		Image imag = foto.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imag));
		contentPane.add(label);
		
		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.setBounds(40, 74, 130, 25);
		contentPane.add(btnEstoque);
		
		JButton btnVendedores = new JButton("Vendedores");
		btnVendedores.setBounds(40, 111, 130, 25);
		contentPane.add(btnVendedores);
		
		JButton btnNovaVenda = new JButton("Nova Venda");
		btnNovaVenda.setBounds(40, 148, 130, 25);
		contentPane.add(btnNovaVenda);
		
		JLabel lblColtestoqueV = new JLabel("ColtEstoque v1.0");
		lblColtestoqueV.setBounds(154, 12, 150, 15);
		contentPane.add(lblColtestoqueV);
	}
}
