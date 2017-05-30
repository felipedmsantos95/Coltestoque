import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton btnRetorno;
	private CirculacaoDAO circulacao_bd= new CirculacaoDAO();
	private ArrayList<Circulacao> listCirculacao = new ArrayList<Circulacao>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaMain frame = new telaMain();
					frame.setVisible(true);
					System.out.println("aparece ae");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public telaMain() {
		initialize();
		Show_Circulacoes_In_Jtable();
	}
	
	private void Show_Circulacoes_In_Jtable()
	{
		listCirculacao = circulacao_bd.ListarCirculacoes();
		DefaultTableModel model =(DefaultTableModel)table.getModel();
		model.setNumRows(0);
		Object[] row = new Object[2];
		for (int i=0; i<listCirculacao.size();i++)
		{
			Vendedor v = circulacao_bd.getCirculacaoVendedor(listCirculacao.get(i));
			row[0] = v.nome;
			row[1] = listCirculacao.get(i).dataRegistrada;

			model.addRow(row);
		}
	}
	
	private void initialize()
	{
		this.getContentPane().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				btnRetorno.setEnabled(false);
				table.clearSelection();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Show_Circulacoes_In_Jtable();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(59, 422, 245, 63);
		
		ImageIcon foto = new ImageIcon(telaMain.class.getResource("/img/coltech.png"));
		Image imag = foto.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imag));
		contentPane.add(label);
		
		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaEstoque telaestoque = new telaEstoque();
				telaestoque.setVisible(true);
			}
		});
		btnEstoque.setBounds(88, 129, 174, 38);
		contentPane.add(btnEstoque);
		
		JButton btnVendedores = new JButton("Vendedores");
		btnVendedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaVendedores tela = new telaVendedores();
				tela.setVisible(true);
			}
		});
		btnVendedores.setBounds(88, 191, 174, 38);
		contentPane.add(btnVendedores);
		
		JButton btnNovaVenda = new JButton("Nova Venda");
		btnNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaNovaVenda tela = new telaNovaVenda();
				tela.setVisible(true);
			}
		});
		btnNovaVenda.setBounds(88, 252, 174, 38);
		contentPane.add(btnNovaVenda);
		
		JLabel lblColtestoqueV = new JLabel("ColtEstoque v1.0");
		lblColtestoqueV.setFont(new Font("Dialog", Font.BOLD, 16));
		lblColtestoqueV.setBounds(339, 12, 157, 43);
		contentPane.add(lblColtestoqueV);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(339, 99, 400, 220);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				btnRetorno.setEnabled(true);
			}

		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"VENDEDOR", "DATA RETIRADA"
			}
		){
			public boolean isCellEditable(int row, int col) {  
		           return false;  
		   } 
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(79);
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(1).setMinWidth(160);
		table.getColumnModel().getColumn(1).setMaxWidth(160);
		scrollPane.setViewportView(table);
		
		btnRetorno = new JButton("Retorno");
		btnRetorno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaRetornoVenda telaretornovenda = new telaRetornoVenda(listCirculacao.get(table.getSelectedRow()).getID());
				telaretornovenda.setVisible(true);
			}
		});
		btnRetorno.setBounds(651, 340, 89, 23);
		contentPane.add(btnRetorno);
		btnRetorno.setEnabled(false);
	}
}
