

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class telaImpressaoTermo extends JFrame {


	private JTextField path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaImpressaoTermo window = new telaImpressaoTermo(6);//Aqui estava testando um exemplo para geracao de relatorio de saida
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
	public telaImpressaoTermo(int idVendedor) {
		initializeRecibo(idVendedor);
	}
	
	public telaImpressaoTermo(int idVendedor, int idCirculacao) {
		initializeSaida(idVendedor, idCirculacao);
	}
	
	public telaImpressaoTermo(Vendedor vendedor, int idCirculacao) {
		initializeRetorno(vendedor, idCirculacao);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeRecibo(int idVendedor) {

		this.setTitle("Impress\u00E3o de Termo");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeraPDF recibo = new GeraPDF();
				VendedorDAO v = new VendedorDAO();
				
				
				try {
					java.awt.Desktop.getDesktop().open( new File(recibo.geraRecibo(path.getText(), v.getVendedor(idVendedor))) );
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,"Erro ao selecionar a pasta, verifique se o campo foi preenchio ou se o diretório existe.");
					
					e1.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(310, 224, 126, 37);
		this.getContentPane().add(btnImprimir);
		
		path = new JTextField();
		path.setBounds(22, 109, 263, 19);
		this.getContentPane().add(path);
		path.setColumns(10);
		
		JButton btnNewButton = new JButton("Escolher Pasta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Escolher Pasta");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				
				

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					path.setText(chooser.getSelectedFile().toString());
					
				} else {
					
				}
				
			}
		});
		btnNewButton.setBounds(297, 109, 139, 19);
		this.getContentPane().add(btnNewButton);
		
		JLabel lblPastaASer = new JLabel("Pasta a ser salvo:");
		lblPastaASer.setBounds(22, 82, 139, 15);
		this.getContentPane().add(lblPastaASer);
	}
	
	//Aqui precisamos do id da circulacao que o vendedor abriu também
	
	private void initializeSaida(int idVendedor, int idCirculacao) {
		this.setTitle("Impress\u00E3o de Termo");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeraPDF relatorios = new GeraPDF();
				VendedorDAO v = new VendedorDAO();
				
				
				try {
					java.awt.Desktop.getDesktop().open( new File(relatorios.geraRelatorioSaida(path.getText(), v.getVendedor(idVendedor), idCirculacao)) );
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,"Erro ao selecionar a pasta, verifique se o campo foi preenchio ou se o diretório existe.");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(310, 224, 126, 37);
		this.getContentPane().add(btnImprimir);
		
		path = new JTextField();
		path.setBounds(22, 109, 263, 19);
		this.getContentPane().add(path);
		path.setColumns(10);
		
		JButton btnNewButton = new JButton("Escolher Pasta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Escolher Pasta");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				
				

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					path.setText(chooser.getSelectedFile().toString());
					
				} else {
					
				}
				
			}
		});
		btnNewButton.setBounds(297, 109, 139, 19);
		this.getContentPane().add(btnNewButton);
		
		JLabel lblPastaASer = new JLabel("Pasta a ser salvo:");
		lblPastaASer.setBounds(22, 82, 139, 15);
		this.getContentPane().add(lblPastaASer);
	}
	
	private void initializeRetorno(Vendedor idVendedor, int idCirculacao) {
		this.setTitle("Impress\u00E3o de Termo");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeraPDF relatorios = new GeraPDF();
				VendedorDAO v = new VendedorDAO();
				
				
				try {
					java.awt.Desktop.getDesktop().open( new File(relatorios.geraRelatorioRetorno(path.getText(), v.getVendedorID(idVendedor), idCirculacao)) );
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,"Erro ao selecionar a pasta, verifique se o campo foi preenchio ou se o diretório existe.");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(310, 224, 126, 37);
		this.getContentPane().add(btnImprimir);
		
		path = new JTextField();
		path.setBounds(22, 109, 263, 19);
		this.getContentPane().add(path);
		path.setColumns(10);
		
		JButton btnNewButton = new JButton("Escolher Pasta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Escolher Pasta");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				
				

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					path.setText(chooser.getSelectedFile().toString());
					
				} else {
					
				}
				
			}
		});
		btnNewButton.setBounds(297, 109, 139, 19);
		this.getContentPane().add(btnNewButton);
		
		JLabel lblPastaASer = new JLabel("Pasta a ser salvo:");
		lblPastaASer.setBounds(22, 82, 139, 15);
		this.getContentPane().add(lblPastaASer);
	}
}
