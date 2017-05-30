

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;
import java.awt.ComponentOrientation;

public class telaRetornoVenda extends JFrame{
	private JTextField textFieldVendedor;
	private JTextField textFieldDataSaida;
	private JTextField textFieldDataRetorno;
	private JTable tableProduto;
	private JTable tableQuantRetornada;
	JButton btnConfirmar;
	ProdutoCirculandoDAO pcirculando_bd = new ProdutoCirculandoDAO();
	ProdutoVendidoDAO pvendido_bd = new ProdutoVendidoDAO();
	ArrayList<ProdutoCirculando> listaPedido = new ArrayList<ProdutoCirculando>();
	CirculacaoDAO circulacao_bd = new CirculacaoDAO();
	EstoqueDAO estoque_bd = new EstoqueDAO();
	private Circulacao circulacao;
	private Vendedor vendedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaRetornoVenda window = new telaRetornoVenda(1);
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
	public telaRetornoVenda(int id_circulacao) {
		initialize();
		circulacao = circulacao_bd.getCirculacao(id_circulacao);
		MostrarDadosCirculacao();
		System.out.println("estoy aqui");
		Show_Pedido_In_Jtable();	
		
	}
	private void MostrarDadosCirculacao()
	{
		vendedor = circulacao_bd.getCirculacaoVendedor(circulacao);
		textFieldVendedor.setText(vendedor.getNome());
		textFieldDataSaida.setText(circulacao.dataRegistrada);
		textFieldDataRetorno.setText(circulacao.getDataAtual());
	}
	
	private void Show_Pedido_In_Jtable()
	{
		listaPedido = pcirculando_bd.ListarProdutosDessaCirculacao(circulacao.getID());
		DefaultTableModel model =(DefaultTableModel)tableProduto.getModel();
		model.setNumRows(0);
		Object[] row = new Object[2];
		for (int i=0; i<listaPedido.size();i++)
		{
			row[0] = listaPedido.get(i).produto.getCodigo();
			row[1] = listaPedido.get(i).quantCirculando;		
			model.addRow(row);
		}
		DefaultTableModel model1 =(DefaultTableModel)tableQuantRetornada.getModel();
		model1.setNumRows(0);
		Object[] row1 = new Object[1];
		for (int i=0; i<listaPedido.size();i++)
		{
			row1[0] = 0;		
			model1.addRow(row1);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Retorno de Venda");
		this.setBounds(100, 100, 800, 550);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setBounds(12, 24, 91, 14);
		this.getContentPane().add(lblVendedor);
		
		textFieldVendedor = new JTextField();
		textFieldVendedor.setEditable(false);
		textFieldVendedor.setBounds(89, 22, 525, 20);
		this.getContentPane().add(textFieldVendedor);
		textFieldVendedor.setColumns(10);
		
		JLabel lblDataSada = new JLabel("Data Sa\u00EDda");
		lblDataSada.setBounds(12, 56, 86, 14);
		this.getContentPane().add(lblDataSada);
		
		JLabel lblDataRetorno = new JLabel("Data Retorno");
		lblDataRetorno.setBounds(297, 56, 112, 14);
		this.getContentPane().add(lblDataRetorno);
		
		textFieldDataSaida = new JTextField();
		textFieldDataSaida.setEditable(false);
		textFieldDataSaida.setBounds(99, 50, 180, 30);
		this.getContentPane().add(textFieldDataSaida);
		textFieldDataSaida.setColumns(10);
		
		textFieldDataRetorno = new JTextField();
		textFieldDataRetorno.setBounds(408, 54, 180, 28);
		this.getContentPane().add(textFieldDataRetorno);
		textFieldDataRetorno.setColumns(10);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for(int i=0; i<listaPedido.size();i++)
				{
					Object ob =tableQuantRetornada.getValueAt(i,0);
					int quantDigitada = (Integer.parseInt(ob.toString()));
					System.out.println(quantDigitada);
					pvendido_bd.addProdutoVendido(listaPedido.get(i).produto,listaPedido.get(i).quantCirculando-quantDigitada, circulacao, vendedor);
					estoque_bd.retornaEstoque(listaPedido.get(i).produto.getID(), quantDigitada);
				}
				telaFimVenda fimVenda = new telaFimVenda(vendedor.getID(),circulacao.getID());
				fimVenda.setVisible(true);
				dispose();
			}
		});
		btnConfirmar.setBounds(639, 432, 111, 37);
		this.getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaMain tela = new telaMain();
				tela.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(496, 432, 118, 37);
		this.getContentPane().add(btnCancelar);
		
		JLabel lblListaDeRetorno = new JLabel("Lista de Retorno de Produtos");
		lblListaDeRetorno.setBounds(22, 100, 224, 14);
		this.getContentPane().add(lblListaDeRetorno);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 140, 700, 210);
		getContentPane().add(scrollPane);
		
		JSplitPane splitPane = new JSplitPane();
		scrollPane.setViewportView(splitPane);
		
		tableProduto = new JTable();
		tableProduto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO", "QUANT RETIRADA"
			}
		));
		splitPane.setLeftComponent(tableProduto);
		
		tableQuantRetornada = new JTable();
		tableQuantRetornada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableQuantRetornada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(tableQuantRetornada.isEditing())
					{
					btnConfirmar.setEnabled(false);
					}
				else btnConfirmar.setEnabled(true);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
		});
		tableQuantRetornada.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"QUANT RETORNADA"
			}
		));
		splitPane.setRightComponent(tableQuantRetornada);
		
		JLabel lblQuantRetornada = new JLabel("+ QUANT RETORNADA");
		lblQuantRetornada.setBounds(241, 125, 168, 14);
		getContentPane().add(lblQuantRetornada);
		
		JLabel lblCodigo = new JLabel("CODIGO");
		lblCodigo.setBounds(12, 126, 64, 14);
		getContentPane().add(lblCodigo);
		
		JLabel lblQuantRetirada = new JLabel("QUANT RETIRADA");
		lblQuantRetirada.setBounds(88, 126, 129, 13);
		getContentPane().add(lblQuantRetirada);
	}
}
