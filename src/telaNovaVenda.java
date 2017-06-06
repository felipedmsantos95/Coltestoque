

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class telaNovaVenda extends JFrame{
	private JTextField textFieldQuant;
	private JTable table;
	JComboBox comboBoxVend;
	JComboBox comboBoxProd;
	EstoqueDAO estoque_bd = new EstoqueDAO();
	VendedorDAO vendedor_bd = new VendedorDAO();
	ProdutoDAO produto_bd=new ProdutoDAO();
	ArrayList<Vendedor> listVendedores = new ArrayList<Vendedor>();
	ArrayList<Produto> listProdutos = new ArrayList<Produto>();
	ArrayList<ProdutoCirculando> listPedido = new ArrayList<ProdutoCirculando>();
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public telaNovaVenda() {
		initialize();
		Show_Vendedores_In_ComboBox();
		Show_Produtos_In_ComboBox();
	}
	
	public void Show_Vendedores_In_ComboBox()
	{
		comboBoxVend.removeAllItems();
		listVendedores = vendedor_bd.listarVendedores();
		for (int i=0; i<listVendedores.size();i++)
		{
				comboBoxVend.addItem(listVendedores.get(i).getNome());
				//System.out.println(listJogosNaoAconteceram.get(i).nome);
		}
		
	}

	public void Show_Produtos_In_ComboBox()
	{
		comboBoxProd.removeAllItems();
		listProdutos = produto_bd.listarProdutos();
		for (int i=0; i<listProdutos.size();i++)
		{
				comboBoxProd.addItem(listProdutos.get(i).getCodigo());
				//System.out.println(listJogosNaoAconteceram.get(i).nome);
		}
		
	}
	
	private void Show_ListaPedido_In_Jtable()
	{
		DefaultTableModel model =(DefaultTableModel)table.getModel();
		model.setNumRows(0);
		Object[] row = new Object[11];
		for (int i=0; i<listPedido.size();i++)
		{

			row[0] = listPedido.get(i).produto.getCodigo();
			double r =listPedido.get(i).produto.getPrecoFinal()* 100;
			Double round = (double) Math.round(r);
			round = round/100;
			row[1] = round;
			row[2] = listPedido.get(i).quantCirculando;
			double r2 =listPedido.get(i).produto.getPrecoFinal()*listPedido.get(i).quantCirculando;
			Double round2 = (double) Math.round(r2);
			round = round2/100;
			row[3] = round2;

			
			model.addRow(row);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Nova Venda");
		this.setBounds(100, 100, 800, 550);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setBounds(90, 33, 83, 14);
		this.getContentPane().add(lblVendedor);
		
		comboBoxVend = new JComboBox();
		comboBoxVend.setBounds(176, 30, 285, 20);
		this.getContentPane().add(comboBoxVend);
		
		/*JLabel lblData = new JLabel("Data");
		lblData.setBounds(21, 58, 46, 14);
		this.getContentPane().add(lblData);*/
		
		JLabel lblCodigoProduto = new JLabel("Codigo Produto");
		lblCodigoProduto.setBounds(24, 97, 80, 14);
		this.getContentPane().add(lblCodigoProduto);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(160, 97, 86, 14);
		this.getContentPane().add(lblQuantidade);
		
		textFieldQuant = new JTextField();
		textFieldQuant.setBounds(160, 124, 86, 20);
		this.getContentPane().add(textFieldQuant);
		textFieldQuant.setColumns(10);
		
		JButton btnAdicionarProduto = new JButton("Adicionar Produto");
		btnAdicionarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produto produtoSelecionado = listProdutos.get(comboBoxProd.getSelectedIndex());
				int quantDesejada=Integer.parseInt(textFieldQuant.getText());
				if(produtoSelecionado.quantidadeEstoque>=quantDesejada)
				{
					listPedido.add(new ProdutoCirculando(produtoSelecionado,quantDesejada));

					Show_ListaPedido_In_Jtable();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Não é possível realizar essa operação \n\n Só tem "+produtoSelecionado.quantidadeEstoque+" desse produto no estoque.");
				}
				
			}
		});
		btnAdicionarProduto.setBounds(582, 122, 161, 37);
		this.getContentPane().add(btnAdicionarProduto);
		
		JLabel lblListaDeSada = new JLabel("Lista de sa\u00EDda");
		lblListaDeSada.setBounds(21, 159, 120, 14);
		this.getContentPane().add(lblListaDeSada);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProdutoCirculandoDAO pcirculando_bd = new ProdutoCirculandoDAO();
				CirculacaoDAO circulacao_bd = new CirculacaoDAO();
				Vendedor vendedor = listVendedores.get(comboBoxVend.getSelectedIndex());
				Circulacao circ = circulacao_bd.iniciarCirculacao(vendedor);
				for (int i =0;i<listPedido.size();i++)
				{
					pcirculando_bd.addProdutoCirculacao(listPedido.get(i).produto, listPedido.get(i).quantCirculando, circ, vendedor);
					estoque_bd.retiraEstoque(listPedido.get(i).produto.getID(), listPedido.get(i).quantCirculando);
				
				}
				telaImpressaoTermo termoVenda = new telaImpressaoTermo(vendedor.getID(),circ.getID());
				termoVenda.setVisible(true);
				dispose();
			}
		});
		btnConfirmar.setBounds(634, 437, 131, 37);
		this.getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaMain window = new telaMain();
				window.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(491, 437, 131, 37);
		this.getContentPane().add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 184, 744, 225);
		this.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO", "PRE\u00C7O", "QUANT RETIRADA", "PRE\u00C7O FINAL"
			}
		){
			public boolean isCellEditable(int row, int col)
			{
				return false;
			}
			}
		);
		
		comboBoxProd = new JComboBox();
		comboBoxProd.setBounds(21, 122, 105, 22);
		this.getContentPane().add(comboBoxProd);
		
		/*JLabel lblValorData = new JLabel("New label");
		lblValorData.setBounds(66, 55, 146, 20);
		getContentPane().add(lblValorData);*/
	}
}
