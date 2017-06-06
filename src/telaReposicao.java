

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.SystemColor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ListSelectionModel;

public class telaReposicao extends JFrame{
	private JTable tableProdutos;
	private JTable tableQuant;
	private JButton btnConfirmar;
	private ProdutoDAO produto_bd= new ProdutoDAO();
	private EstoqueDAO estoque_bd= new EstoqueDAO();
	private ArrayList<Produto> listProdutos = new ArrayList<Produto>();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public telaReposicao() {
		initialize();
		Show_Produtos_In_Jtable();
	}
	
	private void Show_Produtos_In_Jtable()
	{
		listProdutos = produto_bd.listarProdutos();
		DefaultTableModel model =(DefaultTableModel)tableProdutos.getModel();
		model.setNumRows(0);
		Object[] row = new Object[11];
		for (int i=0; i<listProdutos.size();i++)
		{
			row[0] = listProdutos.get(i).getCodigo();
			row[1] = listProdutos.get(i).getNome();		
			model.addRow(row);
		}
		DefaultTableModel model1 =(DefaultTableModel)tableQuant.getModel();
		model1.setNumRows(0);
		Object[] row1 = new Object[11];
		for (int i=0; i<listProdutos.size();i++)
		{
			row1[0] = 0;		
			model1.addRow(row1);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		this.setTitle("Reposi\u00E7\u00E3o de Estoque");
		this.setBounds(100, 100, 500, 550);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0; i<listProdutos.size();i++)
				{
					Object ob =tableQuant.getValueAt(i,0);
					int quantDigitada = (Integer.parseInt(ob.toString()));
					estoque_bd.repoeEstoque(listProdutos.get(i).getID(),quantDigitada);
					System.out.println(listProdutos.get(i).getID()+" adiciona"+quantDigitada);
					
				}
				telaEstoque window= new telaEstoque();
				window.setVisible(true);
				dispose();
				
			}
		});
		btnConfirmar.setBounds(367, 461, 100, 30);
		this.getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaEstoque window= new telaEstoque();
				window.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(257, 461, 100, 30);
		this.getContentPane().add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 50, 430, 400);
		getContentPane().add(scrollPane);
		
		JSplitPane splitPane = new JSplitPane();
		scrollPane.setViewportView(splitPane);
		
		tableProdutos = new JTable();
		tableProdutos.setBackground(SystemColor.control);
		tableProdutos.setRowSelectionAllowed(false);
		tableProdutos.setEnabled(false);
		tableProdutos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO", "NOME"
			}
		));
		splitPane.setRightComponent(tableProdutos);
		
		tableQuant = new JTable();
		tableQuant.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableQuant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(tableQuant.isEditing())
					{
					btnConfirmar.setEnabled(false);
					}
				else btnConfirmar.setEnabled(true);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
		});
		tableQuant.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"+ ADICIONAR"
			}
		));
		tableQuant.getColumnModel().getColumn(0).setPreferredWidth(85);
		tableQuant.getColumnModel().getColumn(0).setMinWidth(85);
		tableQuant.getColumnModel().getColumn(0).setMaxWidth(85);
		splitPane.setLeftComponent(tableQuant);
		
		JLabel lblRepor = new JLabel("+ REPOR");
		lblRepor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRepor.setBounds(32, 15, 80, 30);
		getContentPane().add(lblRepor);
		
		JLabel lblCdigo = new JLabel("C\u00D3DIGO");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCdigo.setBounds(162, 21, 80, 18);
		getContentPane().add(lblCdigo);
		
		JLabel lblProduto = new JLabel("PRODUTO");
		lblProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProduto.setBounds(314, 20, 100, 19);
		getContentPane().add(lblProduto);
	}
}
