
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class telaVendedorEditando extends JFrame{

	private JTextField nome;
	private JTextField comissao;
	private JTextField cpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaVendedorEditando window = new telaVendedorEditando();
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
	public telaVendedorEditando(int id_vendedor) {
		initialize1(id_vendedor);
	}
	
	public telaVendedorEditando() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Adicionando/Editando Vendedor");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo");
		lblNomeCompleto.setBounds(23, 62, 113, 14);
		this.getContentPane().add(lblNomeCompleto);
		
		nome = new JTextField();
		nome.setBounds(160, 60, 253, 20);
		this.getContentPane().add(nome);
		nome.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale pt = new Locale("pt", "PT");
				NumberFormat nf = NumberFormat.getInstance(pt);
				
				try {
					Vendedor vend = new Vendedor(nome.getText(), cpf.getText(), nf.parse(comissao.getText()).doubleValue());
					VendedorDAO v = new VendedorDAO();					
					if(v.adicionarVendedor(vend)){
						JOptionPane.showMessageDialog(null,"Vendedor cadastrado com sucesso!");
						telaVendedores tela = new telaVendedores();
						tela.setVisible(true);
					}
					
					
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null,"Ops, ocorreu algum erro no cadastro. Verifique se os campos foram preenchidos corretamente.");
					e1.printStackTrace();
				}
			}
		});
		btnConfirmar.setBounds(324, 227, 112, 23);
		this.getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaVendedorEditando.this.dispose();
				telaVendedores tela = new telaVendedores();
				tela.setVisible(true);
			}
		});
		btnCancelar.setBounds(200, 227, 113, 23);
		this.getContentPane().add(btnCancelar);
		
		JLabel lblComisso = new JLabel("Comiss\u00E3o (%)");
		lblComisso.setBounds(42, 128, 113, 14);
		this.getContentPane().add(lblComisso);
		
		comissao = new JTextField();
		comissao.setBounds(160, 126, 86, 20);
		this.getContentPane().add(comissao);
		comissao.setColumns(10);
		
		cpf = new JTextField();
		cpf.setColumns(10);
		cpf.setBounds(160, 92, 153, 20);
		getContentPane().add(cpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(107, 94, 37, 15);
		getContentPane().add(lblCpf);
	}
	
	private void initialize1(int id) {
		this.setTitle("Adicionando/Editando Vendedor");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		VendedorDAO vendedorEditado = new VendedorDAO();
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo");
		lblNomeCompleto.setBounds(23, 62, 113, 14);
		this.getContentPane().add(lblNomeCompleto);
		
		nome = new JTextField();
		nome.setText(vendedorEditado.getVendedor(id).nome);
		nome.setBounds(160, 60, 253, 20);
		this.getContentPane().add(nome);
		nome.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale eua = new Locale("en", "CA");
				NumberFormat nf = NumberFormat.getInstance(eua);
				
				try {
					VendedorDAO v = new VendedorDAO();					
					if(v.atualizarVendedor(id, nome.getText(), cpf.getText(), nf.parse(comissao.getText()).doubleValue())){
						JOptionPane.showMessageDialog(null,"Vendedor atualizado com sucesso!");
						telaVendedores tela = new telaVendedores();
						tela.setVisible(true);
					}
					
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null,"Ops, ocorreu algum erro na edição. Verifique se os campos foram preenchidos corretamente.");
					e1.printStackTrace();
				}
			}
		});
		btnConfirmar.setBounds(324, 227, 112, 23);
		this.getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaVendedorEditando.this.dispose();
				telaVendedores tela = new telaVendedores();
				tela.setVisible(true);
			}
		});
		btnCancelar.setBounds(200, 227, 113, 23);
		this.getContentPane().add(btnCancelar);
		
		JLabel lblComisso = new JLabel("Comiss\u00E3o (%)");
		lblComisso.setBounds(42, 128, 113, 14);
		this.getContentPane().add(lblComisso);
		
		comissao = new JTextField();
		String str = String.valueOf(vendedorEditado.getVendedor(id).getPercentual());
		comissao.setText(str);
		comissao.setBounds(160, 126, 86, 20);
		this.getContentPane().add(comissao);
		comissao.setColumns(10);
		
		cpf = new JTextField();
		cpf.setText(vendedorEditado.getVendedor(id).getCpf());
		cpf.setColumns(10);
		cpf.setBounds(160, 92, 153, 20);
		getContentPane().add(cpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(107, 94, 37, 15);
		getContentPane().add(lblCpf);
	}
}
