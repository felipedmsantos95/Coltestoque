import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

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
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(59, 422, 245, 63);
		
		ImageIcon foto = new ImageIcon(telaMain.class.getResource("/img/coltech.png"));
		Image imag = foto.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imag));
		contentPane.add(label);
		
		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.setBounds(88, 129, 174, 38);
		contentPane.add(btnEstoque);
		
		JButton btnVendedores = new JButton("Vendedores");
		btnVendedores.setBounds(88, 191, 174, 38);
		contentPane.add(btnVendedores);
		
		JButton btnNovaVenda = new JButton("Nova Venda");
		btnNovaVenda.setBounds(88, 252, 174, 38);
		contentPane.add(btnNovaVenda);
		
		JLabel lblColtestoqueV = new JLabel("ColtEstoque v1.0");
		lblColtestoqueV.setFont(new Font("Dialog", Font.BOLD, 16));
		lblColtestoqueV.setBounds(339, 12, 157, 43);
		contentPane.add(lblColtestoqueV);
		
		JButton btnSobre = new JButton("Sobre");
		btnSobre.setBounds(88, 310, 174, 38);
		contentPane.add(btnSobre);
	}
}
