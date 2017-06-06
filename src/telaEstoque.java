
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class telaEstoque extends JFrame{

	private JTable table;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JLabel lblSelecioneUmProduto;
	private ProdutoDAO produto_bd= new ProdutoDAO();
	private ArrayList<Produto> listProdutos = new ArrayList<Produto>();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public telaEstoque() {
		setResizable(false);
		initialize();
		Show_Produtos_In_Jtable();
	}
	
	private void Show_Produtos_In_Jtable()
	{
		listProdutos = produto_bd.listarProdutos();
		DefaultTableModel model =(DefaultTableModel)table.getModel();
		model.setNumRows(0);
		Object[] row = new Object[6];
		for (int i=0; i<listProdutos.size();i++)
		{
			row[0] = listProdutos.get(i).getCodigo();
			row[1] = listProdutos.get(i).getNome();
			row[2] = listProdutos.get(i).getQuantidadeEstoque();
			row[3] = listProdutos.get(i).getPrecoCompra();

			double r =listProdutos.get(i).getPrecoFinal()* 100;
			Double round = (double) Math.round(r);
			round = round/100;
			
			row[4] = round;
			row[5] = listProdutos.get(i).getDescricao();
			
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
				table.clearSelection();
			}
		});
		this.setTitle("Estoque");
		this.setBounds(100, 100, 800, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 130, 764, 318);
		this.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(25);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
			}

		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO", "NOME", "QUANT ESTOQUE", "PRE\u00C7O COMPRA", "PRE\u00C7O VENDA", "DESCRI\u00C7\u00C3O"
			}
		)
				{
				public boolean isCellEditable(int row, int col)
				{
					return false;
				}
				}
		);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(0).setMinWidth(70);
		table.getColumnModel().getColumn(0).setMaxWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setMinWidth(120);
		table.getColumnModel().getColumn(2).setMaxWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setMinWidth(120);
		table.getColumnModel().getColumn(3).setMaxWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(98);
		table.getColumnModel().getColumn(4).setMinWidth(98);
		table.getColumnModel().getColumn(4).setMaxWidth(98);
		
		scrollPane.setViewportView(table);
		
		lblSelecioneUmProduto = new JLabel("Selecione um produto abaixo para :");
		lblSelecioneUmProduto.setBounds(33, 92, 266, 27);
		getContentPane().add(lblSelecioneUmProduto);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaMain tala = new telaMain();
				tala.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(614, 460, 160, 45);
		getContentPane().add(btnVoltar);
		
		JButton btnNovoProduto = new JButton("Novo Produto");
		btnNovoProduto.setBounds(33, 12, 230, 45);
		getContentPane().add(btnNovoProduto);
		
		JButton btnReposioDeProduto = new JButton("Reposi\u00E7\u00E3o de Produto");
		btnReposioDeProduto.setBounds(275, 12, 230, 45);
		getContentPane().add(btnReposioDeProduto);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(300, 90, 120, 30);
		getContentPane().add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//te envio o id do produto que quero editar
				telaProdutoEditando window= new telaProdutoEditando(listProdutos.get(table.getSelectedRow()).getID());
				window.setVisible(true);
				dispose();
			}
		});
		btnEditar.setEnabled(false);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(434, 88, 120, 30);
		getContentPane().add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse produto?");
				if(confirm==0)
					{
					int idProdutoSelecionado= listProdutos.get(table.getSelectedRow()).getID();
					//verificar se vendedor está em circulacao
					ProdutoCirculandoDAO pcirculando_bd = new ProdutoCirculandoDAO();
					ArrayList<Integer> listcirculacao = pcirculando_bd.ListarCirculacoesDesseProduto(idProdutoSelecionado);
					if(listcirculacao.isEmpty()){
						//remover do banco de estoque
						EstoqueDAO es = new EstoqueDAO();
						es.removerProdutoEstoque(idProdutoSelecionado);
						//remover do banco de produtos
						produto_bd.removerProduto(idProdutoSelecionado);
						JOptionPane.showMessageDialog(null,"Produto removido com sucesso.");
						Show_Produtos_In_Jtable();
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Impossivel remover este produto pois ele possui uma Retirada em aberto");
					}
					}
			}
		});
		btnExcluir.setEnabled(false);
		btnReposioDeProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaReposicao window= new telaReposicao();
				window.setVisible(true);
				dispose();
			}
		});
		btnNovoProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaProdutoEditando window= new telaProdutoEditando();
				window.setVisible(true);
				dispose();
			}
		});
	}
}
