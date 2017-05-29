import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import java.awt.Image;

public class telaSobre extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaSobre frame = new telaSobre();
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
	public telaSobre() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSobre = new JButton("Ok");
		btnSobre.setBounds(595, 441, 151, 38);
		contentPane.add(btnSobre);
		
		JLabel lblColtestoqueV = new JLabel("ColtEstoque v1.0");
		lblColtestoqueV.setFont(new Font("Dialog", Font.BOLD, 14));
		lblColtestoqueV.setBounds(340, 12, 144, 15);
		contentPane.add(lblColtestoqueV);
		
		JLabel lblAColtechEmpresa = DefaultComponentFactory.getInstance().createLabel("       A COLTECH, empresa júnior da facudade de tecnologia da Universidade Federal do Amazonas, foi ");
		lblAColtechEmpresa.setBounds(48, 67, 738, 51);
		contentPane.add(lblAColtechEmpresa);
		
		JLabel lblCriadaEmVirtude = DefaultComponentFactory.getInstance().createLabel("criada em virtude do preparo de alunos para a vida empreendedora, para a valorização de  projetos");
		lblCriadaEmVirtude.setBounds(48, 103, 720, 15);
		contentPane.add(lblCriadaEmVirtude);
		
		JLabel lblCriadosDentroDa = DefaultComponentFactory.getInstance().createLabel("criados dentro da universidade e para pôr em prática conhecimentos\n adquiridos em sala de aula.");
		lblCriadosDentroDa.setBounds(48, 122, 710, 15);
		contentPane.add(lblCriadosDentroDa);
		
		
		
		JLabel label = new JLabel("");
		label.setBounds(48, 412, 245, 63);
		
		ImageIcon foto = new ImageIcon(telaMain.class.getResource("/img/coltech.png"));
		Image imag = foto.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imag));
		contentPane.add(label);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("A COLTECH é formada por alunos de Engenharia da Computação, Engenharia Mecânica, Engenharia Elétrica além do curso de design.");
		lblNewJgoodiesLabel.setBounds(75, 183, 671, 15);
		contentPane.add(lblNewJgoodiesLabel);
	}
}
