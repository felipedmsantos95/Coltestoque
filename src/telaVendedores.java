

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class telaVendedores extends JFrame{

	private JTable table;
	private VendedorDAO vendedor_bd= new VendedorDAO();
	private ArrayList<Vendedor> listVendedores = new ArrayList<Vendedor>();
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnPagar;
	private JLabel lblSelecioneUmVendedor;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public telaVendedores() {
		initialize();
		Show_Vendedores_In_Jtable();
	}
	
	private void Show_Vendedores_In_Jtable()
	{
		listVendedores = vendedor_bd.listarVendedores();
		DefaultTableModel model =(DefaultTableModel)table.getModel();
		model.setNumRows(0);
		Object[] row = new Object[11];
		for (int i=0; i<listVendedores.size();i++)
		{

			row[0] = listVendedores.get(i).getNome();
			row[1] = listVendedores.get(i).getComissao() + " % ";
			row[2] = "R$ "+ listVendedores.get(i).getValorAReceber();

			
			model.addRow(row);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.getContentPane().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
				btnPagar.setEnabled(false);
				table.clearSelection();
			}
		});
		this.setTitle("Vendedores");
		this.setBounds(100, 100, 800, 550);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JButton btnAdicionarNovoVendedor = new JButton("Adicionar Novo Vendedor");
		btnAdicionarNovoVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaVendedorEditando tela = new telaVendedorEditando();
				tela.setVisible(true);
				dispose();
			}
		});
		btnAdicionarNovoVendedor.setBounds(22, 23, 230, 45);
		this.getContentPane().add(btnAdicionarNovoVendedor);
		
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//te envio o id do produto que quero editar
				telaVendedorEditando window= new telaVendedorEditando(listVendedores.get(table.getSelectedRow()).getID());
				window.setVisible(true);
				dispose();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBounds(306, 78, 120, 30);
		this.getContentPane().add(btnEditar);
		
		btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// passa o ID do vendedor
				telaImpressaoTermo window= new telaImpressaoTermo(listVendedores.get(table.getSelectedRow()).getID());
				window.setVisible(true);
				dispose();
			}
		});
		btnPagar.setEnabled(false);
		btnPagar.setBounds(438, 78, 120, 30);
		this.getContentPane().add(btnPagar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(570, 78, 120, 30);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse vendedor?");
				if(confirm==0)
					{
					int idVendedorExcluido =listVendedores.get(table.getSelectedRow()).getID();
					//verificar se vendedor está em circulacao
					CirculacaoDAO circulacao_bd = new CirculacaoDAO();
					ArrayList<Circulacao> listcirculacao = circulacao_bd.ListarCirculacoesDeVendedor(idVendedorExcluido);
					if(listcirculacao.isEmpty())
					{
						vendedor_bd.removerVendedor(idVendedorExcluido);
						JOptionPane.showMessageDialog(null,"Vendedor removido com sucesso.");
						Show_Vendedores_In_Jtable();
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Impossivel remover este vendedor pois ele possui uma Retirada em aberto");
					}
					}
			}
		});
		btnExcluir.setEnabled(false);
		this.getContentPane().add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 120, 738, 301);
		this.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(25);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
				btnPagar.setEnabled(true);
			}

		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NOME", "COMISS\u00C3O", "VALOR A RECEBER"
			}
		)
		{
			public boolean isCellEditable(int row, int col)
			{
				return false;
			}
			}
	);
		table.getColumnModel().getColumn(1).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMaxWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setMinWidth(120);
		table.getColumnModel().getColumn(2).setMaxWidth(120);
		scrollPane.setViewportView(table);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaMain tela = new telaMain();
				tela.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(600, 444, 160, 45);
		getContentPane().add(btnVoltar);
		
		lblSelecioneUmVendedor = new JLabel("Selecione um vendedor abaixo para:");
		lblSelecioneUmVendedor.setBounds(32, 82, 258, 22);
		getContentPane().add(lblSelecioneUmVendedor);
	}
}
