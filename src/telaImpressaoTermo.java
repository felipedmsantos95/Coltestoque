

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaImpressaoTermo extends JFrame {

	private JFrame frmImpressoDeTermo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaImpressaoTermo window = new telaImpressaoTermo();
					window.frmImpressoDeTermo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telaImpressaoTermo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmImpressoDeTermo = new JFrame();
		frmImpressoDeTermo.setTitle("Impress\u00E3o de termo");
		frmImpressoDeTermo.setBounds(100, 100, 450, 300);
		frmImpressoDeTermo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmImpressoDeTermo.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(47, 33, 351, 170);
		frmImpressoDeTermo.getContentPane().add(textArea);
		
		
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(309, 227, 89, 23);
		frmImpressoDeTermo.getContentPane().add(btnImprimir);
	}
}
