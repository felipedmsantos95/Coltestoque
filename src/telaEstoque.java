
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
	private JPanel panel;
	private JLabel lblSelecioneUmProduto;
	private ProdutoDAO produto_bd= new ProdutoDAO();
	private ArrayList<Produto> listProdutos = new ArrayList<Produto>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaEstoque window = new telaEstoque();
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
			row[4] = listProdutos.get(i).getPrecoFinal();
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
			public boolean isCellEditable(int row, int col) {  
		           return false;  
		   } 
		}
		);
		
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.setBounds(36, 22, 712, 45);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNovoProduto = new JButton("Novo Produto");
		btnNovoProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaProdutoEditando window= new telaProdutoEditando();
				window.setVisible(true);
				dispose();
			}
		});
		panel.add(btnNovoProduto);
		
		JButton btnReposioDeProduto = new JButton("Reposi\u00E7\u00E3o de Produto");
		btnReposioDeProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaReposicao window= new telaReposicao();
				window.setVisible(true);
				dispose();
			}
		});
		panel.add(btnReposioDeProduto);
		
		btnEditar = new JButton("Editar");
		panel.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//te envio o id do produto que quero editar
				telaProdutoEditando window= new telaProdutoEditando(listProdutos.get(table.getSelectedRow()).getID());
				window.setVisible(true);
				dispose();
			}
		});
		btnEditar.setEnabled(false);
		
		btnExcluir = new JButton("excluir");
		panel.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse produto?");
				if(confirm==0)
					{
					Produto produtoSelecionado= listProdutos.get(table.getSelectedRow());
					//remover do banco de vendidos
					//remover do banco de circulacao
					//remover do banco de estoque
					EstoqueDAO es = new EstoqueDAO();
					es.removerProdutoEstoque(produtoSelecionado.getID());
					//remover do banco de produtos
					produto_bd.removerProduto(produtoSelecionado.getID());
					}
			}
		});
		btnExcluir.setEnabled(false);
		
		lblSelecioneUmProduto = new JLabel("Selecione um produto abaixo :");
		lblSelecioneUmProduto.setBounds(33, 92, 213, 27);
		getContentPane().add(lblSelecioneUmProduto);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(598, 459, 176, 45);
		getContentPane().add(btnVoltar);
	}
}
