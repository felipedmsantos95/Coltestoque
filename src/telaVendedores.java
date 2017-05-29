

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaVendedores window = new telaVendedores();
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
			row[1] = listVendedores.get(i).getComissao();
			row[2] = listVendedores.get(i).getValorAReceber();

			
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
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JButton btnAdicionarNovoVendedor = new JButton("Adicionar Novo Vendedor");
		btnAdicionarNovoVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaVendedorEditando tela = new telaVendedorEditando();
				tela.setVisible(true);
			}
		});
		btnAdicionarNovoVendedor.setBounds(22, 11, 227, 23);
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
		btnEditar.setBounds(20, 45, 89, 23);
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
		btnPagar.setBounds(113, 45, 89, 23);
		this.getContentPane().add(btnPagar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(203, 45, 89, 23);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse produto?");
				if(confirm==0)vendedor_bd.removerVendedor(listVendedores.get(table.getSelectedRow()).getID());
			}
		});
		btnExcluir.setEnabled(false);
		this.getContentPane().add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 73, 402, 177);
		this.getContentPane().add(scrollPane);
		
		table = new JTable();
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
		){
			public boolean isCellEditable(int row, int col) {  
		           return false;  
		   } 

			Class[] columnTypes = new Class[] {
				String.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
	}
}
