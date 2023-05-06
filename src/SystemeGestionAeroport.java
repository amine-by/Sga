package src;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class SystemeGestionAeroport {
	
	private static final String URI =   "";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	
	private JButton btnValider;
	private JButton btnModifier_1;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_6;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JButton button_11;
	
	private JFrame frmSystmeDeGestion;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	private JComboBox<Object> comboBox;
	private JComboBox<Object> comboBox_1;	
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	
	private JTable table_2;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_9;
	private JTable table_3;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_8;
	private JList<Integer> list;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemeGestionAeroport window = new SystemeGestionAeroport();
					window.frmSystmeDeGestion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SystemeGestionAeroport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public static void connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
			System.out.print("connect� a mysql \n");
			conn.close();
		} catch(Exception e){
			System.out.print("Erreur de connexion base de donnee "+e+"\n");
		}
	}
	
	static ArrayList<Avions> getAvions(){
		
		ArrayList<Avions> avions = new ArrayList<Avions>();
		Avions a;
		
		ResultSet rs = null;
		Statement st = null;
		Connection conn = null;
		
		try {
			conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
			st = (Statement) conn.createStatement();
			rs = st.executeQuery("select * from avions");
			
			while(rs.next()) {
				a = new Avions(
						rs.getInt("Num"),
                        rs.getInt("Capacite"),
                        rs.getString("Model")
                        );
				avions.add(a);
			}
			System.out.println("Donnes du tableau Avions mis a jours avec succes");
		} catch(Exception e){
			System.out.println("Erreur de mise a jour de tableau Avions");
		} return avions;
	}
	
    static ArrayList<Pilotes> getPilotes(){
		
		ArrayList<Pilotes> pilotes = new ArrayList<Pilotes>();
		Pilotes p;
		
		ResultSet rs = null;
		Statement st = null;
		Connection conn = null;
		
		try {
			conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
			st = (Statement) conn.createStatement();
			rs = st.executeQuery("select * from pilotes");
			
			while(rs.next()) {
				p = new Pilotes(
						rs.getInt("License"),
                        rs.getString("Nom"),
                        rs.getString("Prenom")
                        );
				pilotes.add(p);
			}
			System.out.println("Donnes du tableau Pilotes mis a jours avec succes");
		} catch(Exception e){
			System.out.println("Erreur de mise a jour de tableau Pilotes");
		} return pilotes;
    }
    
    static ArrayList<Passagers> getPassagers(){
		
		ArrayList<Passagers> passagers = new ArrayList<Passagers>();
		Passagers p;
		
		ResultSet rs = null;
		Statement st = null;
		Connection conn = null;
		
		try {
			conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
			st = (Statement) conn.createStatement();
			rs = st.executeQuery("select * from passagers");
			
			while(rs.next()) {
				p = new Passagers(
						rs.getInt("CIN"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Adresse")
                        );
				passagers.add(p);
			}
			System.out.println("Donnes du tableau Passagers mis a jours avec succes");
		} catch(Exception e){
			System.out.println("Erreur de mise a jour de tableau Passagers");
		} return passagers;
    }
	
    static ArrayList<Vols> getVols(){
		
		ArrayList<Vols> vols = new ArrayList<Vols>();
		Vols v;
		
		ResultSet rs = null;
		Statement st = null;
		Connection conn = null;
		
		try {
			conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
			st = (Statement) conn.createStatement();
			rs = st.executeQuery("select * from Vols");
			
			while(rs.next()) {
				v = new Vols(
						rs.getInt("ID"),
                        rs.getInt("num"),
                        rs.getInt("license"),
                        rs.getString("destination"),
                        rs.getString("depart"),
                        rs.getInt("cout")
                        );
				vols.add(v);
			}
			System.out.println("Donnes du tableau Vols mis a jours avec succes");
		} catch(Exception e){
			System.out.println("Erreur de mise a jour de tableau Vols");
		} return vols;
    }
    
    public void updateList() {
    	try {
			DefaultListModel<Integer> model =new DefaultListModel<Integer>();
			String id = table_3.getValueAt(table_3.getSelectedRow(), 0).toString() ;
			Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery("select cin from vol_passager where id = "+id);
			while(rs.next()) {
				model.addElement(rs.getInt("cin"));
			}
			list.setModel(model);
		} catch (SQLException x) {
			System.out.println("Erreur" +x.getMessage());
		}
    }
    
	public void update() {
		
		String[] avionsColumnNames = {"Num" , "Capacit�", "Model"};
		Object[] avionsData = new Object[3];
		String[] pilotesColumnNames = {"License" , "Nom", "Pr�nom"};
		Object[] pilotesData = new Object[3];
		String[] passagersColumnNames = {"Numero Passport" , "Nom", "Pr�nom", "Adresse"};
		Object[] passagersData = new Object[4];
		String[] volsColumnNames = {"ID","avion","pilote","destination","temp depart","cout"};
		Object[] volsData = new Object[6];
		DefaultTableModel model;
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(avionsColumnNames);
		
		for ( int i = 0; i <getAvions().size() ; i++ ) {
			
			avionsData[0]= getAvions().get(i).Num;
			avionsData[1]= getAvions().get(i).Capacite;
			avionsData[2]= getAvions().get(i).Model;
			
			model.addRow(avionsData);
		}
		
		table.setModel(model);
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(pilotesColumnNames);
		
		for ( int i = 0; i <getPilotes().size() ; i++ ) {
			
			pilotesData[0]= getPilotes().get(i).License;
			pilotesData[1]= getPilotes().get(i).Nom;
			pilotesData[2]= getPilotes().get(i).Prenom;
			
			model.addRow(pilotesData);
		}
		
		table_1.setModel(model);
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(passagersColumnNames);
		
		for ( int i = 0; i <getPassagers().size() ; i++ ) {
			
			passagersData[0]= getPassagers().get(i).CIN;
			passagersData[1]= getPassagers().get(i).Nom;
			passagersData[2]= getPassagers().get(i).Prenom;
			passagersData[3]= getPassagers().get(i).Adresse;
			
			model.addRow(passagersData);
		}
		
		table_2.setModel(model);
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(volsColumnNames);
		
		for ( int i = 0; i <getVols().size() ; i++ ) {
			
			volsData[0]= getVols().get(i).id;
			volsData[1]= getVols().get(i).num;
			volsData[2]= getVols().get(i).license;
			volsData[3]= getVols().get(i).destination;
			volsData[4]= getVols().get(i).depart;
			volsData[5]= getVols().get(i).cout;
			
			model.addRow(volsData);
		}
		
		table_3.setModel(model);
		
		comboBox.removeAllItems();
		Object[] avionsNums = new Object[table.getRowCount()] ;
		for (int i = 0; i < table.getRowCount(); i++) {
			avionsNums[i]= table.getValueAt(i,0);
			comboBox.addItem(avionsNums[i]);
		}
		
		comboBox_1.removeAllItems();
		Object[] pilotesLicenses = new Object[table_1.getRowCount()] ;
		for (int i = 0; i < table_1.getRowCount(); i++) {
			pilotesLicenses[i]= table_1.getValueAt(i,0);
			comboBox_1.addItem(pilotesLicenses[i]);
		}
	}
	
	private void setVisible(int key) {
		panel_1.setVisible(false);
		panel_2.setVisible(false);
		panel_3.setVisible(false);
		panel_4.setVisible(false);
		panel_5.setVisible(false);
		panel_6.setVisible(false);
		panel_7.setVisible(false);
		panel_8.setVisible(false);
		panel_9.setVisible(false);
		panel_10.setVisible(false);
		
		switch (key) {
		case 1: panel_1.setVisible(true); break;
		case 2: panel_2.setVisible(true); break;
		case 3: panel_3.setVisible(true); break;
		case 4: panel_4.setVisible(true); break;
		case 5: panel_5.setVisible(true); break;
		case 6: panel_6.setVisible(true); break;
		case 7: panel_7.setVisible(true); break;
		case 8: panel_8.setVisible(true); break;
		case 9: panel_9.setVisible(true); break;
		case 10: panel_10.setVisible(true); break;
		
		}
	}
	
	private void initialize() {
		frmSystmeDeGestion = new JFrame("Syst�me de Gestion d'A�roport");
		frmSystmeDeGestion.setTitle("Syst\u00E9me de Gestion d'A�roport");
		frmSystmeDeGestion.setVisible(true);
		frmSystmeDeGestion.setBackground(Color.WHITE);
		frmSystmeDeGestion.setResizable(false);
		frmSystmeDeGestion.setBounds(100, 100, 736, 525);
		frmSystmeDeGestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSystmeDeGestion.setLocation(320, 100);
		frmSystmeDeGestion.getContentPane().setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setVisible(false);
		
		panel_10 = new JPanel();
		panel_10.setVisible(false);
		panel_10.setBackground(Color.GRAY);
		panel_10.setBounds(140, 0, 594, 501);
		frmSystmeDeGestion.getContentPane().add(panel_10);
		panel_10.setLayout(null);
		
		JLabel lblSelecterPassagerA = new JLabel("Selecter passager a ajouter");
		lblSelecterPassagerA.setForeground(Color.BLACK);
		lblSelecterPassagerA.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSelecterPassagerA.setBounds(10, 11, 222, 29);
		panel_10.add(lblSelecterPassagerA);
		
		JComboBox<Integer> comboBox_2 = new JComboBox<Integer>();
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setBounds(10, 51, 574, 29);
		panel_10.add(comboBox_2);
		
		JButton btnValider_1 = new JButton("Valider");
		btnValider_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					Statement st = (Statement) conn.createStatement();
					st.executeUpdate("insert into vol_passager (id,cin) values ("+table_3.getValueAt(table_3.getSelectedRow(), 0)+","+comboBox_2.getSelectedItem()+")");
					updateList();
					setVisible(9);
				} catch (SQLException x) {
					System.out.println("Erreur" +x.getMessage());
				}
			}
		});
		btnValider_1.setForeground(Color.BLACK);
		btnValider_1.setBackground(Color.WHITE);
		btnValider_1.setBounds(480, 450, 104, 40);
		panel_10.add(btnValider_1);
		
		JButton button_13 = new JButton("Annuler");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(9);
			}
		});
		button_13.setForeground(Color.BLACK);
		button_13.setBackground(Color.WHITE);
		button_13.setBounds(366, 450, 104, 40);
		panel_10.add(button_13);
		
		panel_9 = new JPanel();
		panel_9.setVisible(false);
		panel_9.setBackground(Color.GRAY);
		panel_9.setBounds(140, 0, 594, 501);
		frmSystmeDeGestion.getContentPane().add(panel_9);
		panel_9.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 60, 574, 386);
		panel_9.add(scrollPane_4);
		
		list = new JList<Integer>();
		scrollPane_4.setViewportView(list);
		
		JLabel lblListeDePassagers_1 = new JLabel("Liste de Passagers enregistres a cette vol");
		lblListeDePassagers_1.setForeground(Color.BLACK);
		lblListeDePassagers_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblListeDePassagers_1.setBounds(10, 11, 332, 43);
		panel_9.add(lblListeDePassagers_1);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comboBox_2.removeAllItems();
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					Statement st = (Statement) conn.createStatement();
					ResultSet rs = st.executeQuery("select cin from passagers where not (cin in(select cin from vol_passager where id = "+table_3.getValueAt(table_3.getSelectedRow(), 0)+"))");
					while(rs.next()) {
						comboBox_2.addItem(rs.getInt("cin"));
					}
					
				} catch (SQLException x) {
					System.out.println("Erreur" +x.getMessage());
				}
				setVisible(10);
			}
		});
		btnAjouter.setForeground(Color.BLACK);
		btnAjouter.setBackground(Color.WHITE);
		btnAjouter.setBounds(480, 450, 104, 40);
		panel_9.add(btnAjouter);
		
		JButton btnSupprimer_4 = new JButton("Supprimer");
		btnSupprimer_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					Statement st = (Statement) conn.createStatement();
					st.executeUpdate("delete from vol_passager where id = "+table_3.getValueAt(table_3.getSelectedRow(), 0).toString()+" and cin = "+list.getSelectedValue());
					updateList();
				} catch(SQLException x) {
					System.out.println("Erreur" +x.getMessage());
				}
			}
		});
		btnSupprimer_4.setForeground(Color.BLACK);
		btnSupprimer_4.setBackground(Color.WHITE);
		btnSupprimer_4.setBounds(366, 450, 104, 40);
		panel_9.add(btnSupprimer_4);
		
		JButton btnAnnuler_2 = new JButton("Annuler");
		btnAnnuler_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(7);
			}
		});
		btnAnnuler_2.setForeground(Color.BLACK);
		btnAnnuler_2.setBackground(Color.WHITE);
		btnAnnuler_2.setBounds(252, 450, 104, 40);
		panel_9.add(btnAnnuler_2);
		
		panel_8 = new JPanel();
		panel_8.setBounds(140, 0, 594, 501);
		frmSystmeDeGestion.getContentPane().add(panel_8);
		panel_8.setVisible(false);
		panel_8.setBackground(Color.GRAY);
		panel_8.setLayout(null);
		
		JLabel lblTempDeDpart = new JLabel("Temp de D\u00E9part");
		lblTempDeDpart.setForeground(Color.BLACK);
		lblTempDeDpart.setBounds(10, 335, 145, 29);
		lblTempDeDpart.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel_8.add(lblTempDeDpart);
		
		JLabel lblDstination = new JLabel("D\u00E9stination");
		lblDstination.setForeground(Color.BLACK);
		lblDstination.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDstination.setBounds(10, 171, 130, 29);
		panel_8.add(lblDstination);
		
		JLabel lblCout = new JLabel("Cout");
		lblCout.setForeground(Color.BLACK);
		lblCout.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCout.setBounds(10, 255, 81, 29);
		panel_8.add(lblCout);
		
		JLabel lblAvion = new JLabel("Avion");
		lblAvion.setForeground(Color.BLACK);
		lblAvion.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblAvion.setBounds(10, 11, 81, 29);
		panel_8.add(lblAvion);
		
		JLabel lblCapacit_1 = new JLabel("Pilote");
		lblCapacit_1.setForeground(Color.BLACK);
		lblCapacit_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCapacit_1.setBounds(10, 91, 81, 29);
		panel_8.add(lblCapacit_1);
		
		comboBox = new JComboBox<Object>();
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(10, 51, 574, 29);
		panel_8.add(comboBox);
		
		comboBox_1 = new JComboBox<Object>();
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(10, 131, 574, 29);
		panel_8.add(comboBox_1);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(10, 295, 574, 29);
		panel_8.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(10, 375, 574, 29);
		panel_8.add(textField_12);
		
		button_6 = new JButton("Valider");
		button_6.setVisible(false);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					Statement st = (Statement) conn.createStatement();
					ResultSet rs = st.executeQuery("select max(id) from vols");
					int x = 1;
				    while (rs.next()) {
				    	if (rs.getString(1)!=null) x = Integer.parseInt(rs.getString(1)) + 1 ;
				    }
					st.executeUpdate("insert into vols values("+x+","+comboBox.getSelectedItem()+","+comboBox_1.getSelectedItem()+",'"+textField_8.getText()+"','"+textField_12.getText()+"',"+textField_11.getText()+")");
					textField_8.setText("");
					textField_11.setText("");
					textField_12.setText("");
					setVisible(7);
					update();
					System.out.println("Un ligne de donnee a ete ajoute au tableau avions avec succes");
				} catch (Exception x) {
					System.out.println("Erreur d'ajout au tableau avions");
				}
				
			}
		});
		button_6.setForeground(Color.BLACK);
		button_6.setBackground(Color.WHITE);
		button_6.setBounds(480, 450, 104, 40);
		panel_8.add(button_6);
		
		JButton button_7 = new JButton("Annuler");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(7);
			}
		});
		button_7.setForeground(Color.BLACK);
		button_7.setBackground(Color.WHITE);
		button_7.setBounds(366, 450, 104, 40);
		panel_8.add(button_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(10, 211, 574, 29);
		panel_8.add(textField_8);
		
		button_11 = new JButton("Valider");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int key = Integer.parseInt(table_3.getValueAt(table_3.getSelectedRow(), 0).toString());
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					PreparedStatement st = (PreparedStatement) conn.prepareStatement("update vols set destination = '"+textField_8.getText()+"' , depart = '"+textField_12.getText()+"' , cout = "+textField_11.getText()+" , num ="+comboBox.getSelectedItem()+" , license = "+comboBox_1.getSelectedItem()+" where id = "+key+"");
					st.executeUpdate();
					setVisible(7);
					update();
				} catch (SQLException x) {
					System.out.println("Erreur"+x.getMessage());
				}
			}
		});
		
		button_11.setVisible(false);
		button_11.setForeground(Color.RED);
		button_11.setBackground(Color.WHITE);
		button_11.setBounds(480, 450, 104, 40);
		panel_8.add(button_11);
		
		panel_7 = new JPanel();
		panel_7.setBackground(Color.GRAY);
		panel_7.setFocusable(false);
		panel_7.setBounds(140, 0, 594, 501);
		frmSystmeDeGestion.getContentPane().add(panel_7);
		panel_7.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 60, 574, 386);
		panel_7.add(scrollPane_3);
		
		table_3 = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		scrollPane_3.setViewportView(table_3);
		
		JButton button_5 = new JButton("Ajouter");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				button_6.setVisible(true);
				button_11.setVisible(false);
				setVisible(8);
			}
		});
		button_5.setForeground(Color.BLACK);
		button_5.setBackground(Color.WHITE);
		button_5.setBounds(480, 450, 104, 40);
		panel_7.add(button_5);
		
		JLabel lblListeDeVols = new JLabel("Liste de Vols");
		lblListeDeVols.setForeground(Color.BLACK);
		lblListeDeVols.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblListeDeVols.setBounds(10, 11, 160, 43);
		panel_7.add(lblListeDeVols);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_6.setVisible(false);
				button_11.setVisible(true);
				comboBox.setSelectedItem(table_3.getValueAt(table_3.getSelectedRow(), 1));
				comboBox_1.setSelectedItem(table_3.getValueAt(table_3.getSelectedRow(), 2));
				textField_8.setText(table_3.getValueAt(table_3.getSelectedRow(), 3).toString());
				textField_11.setText(table_3.getValueAt(table_3.getSelectedRow(), 5).toString());
				textField_12.setText(table_3.getValueAt(table_3.getSelectedRow(), 4).toString());
				setVisible(8);
			}
		});
		btnModifier.setForeground(Color.BLACK);
		btnModifier.setBackground(Color.WHITE);
		btnModifier.setBounds(366, 450, 104, 40);
		panel_7.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int key = (int) table_3.getValueAt(table_3.getSelectedRow(), 0);
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					PreparedStatement st1 = (PreparedStatement) conn.prepareStatement("delete from vol_passager where id = ?");
					PreparedStatement st2 = (PreparedStatement) conn.prepareStatement("delete from vols where id = ?");
					st1.setInt(1,key);
					st1.executeUpdate();
					st2.setInt(1,key);
					st2.executeUpdate();
					update();
					System.out.println("Succes suppression d'element de tableau vols");
				} catch(SQLException x){
					System.out.println("Erreur "+x.getMessage());
				}
			}
		});
		btnSupprimer.setForeground(Color.BLACK);
		btnSupprimer.setBackground(Color.WHITE);
		btnSupprimer.setBounds(252, 450, 104, 40);
		panel_7.add(btnSupprimer);
		
		JButton btnAjouterPassager = new JButton("Manager Vol");
		btnAjouterPassager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateList();
				setVisible(9);
			}
		});
		btnAjouterPassager.setForeground(Color.BLACK);
		btnAjouterPassager.setBackground(Color.WHITE);
		btnAjouterPassager.setBounds(10, 450, 232, 40);
		panel_7.add(btnAjouterPassager);
		
		panel_6 = new JPanel();
		panel_6.setVisible(false);
		panel_6.setBounds(140, 0, 594, 501);
		frmSystmeDeGestion.getContentPane().add(panel_6);
		panel_6.setBackground(Color.GRAY);
		panel_6.setLayout(null);
		
		JLabel label = new JLabel("Nom");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label.setBounds(10, 91, 122, 29);
		panel_6.add(label);
		
		JLabel lblPrnom_1 = new JLabel("Pr\u00E9nom");
		lblPrnom_1.setForeground(Color.BLACK);
		lblPrnom_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblPrnom_1.setBounds(10, 171, 122, 29);
		panel_6.add(lblPrnom_1);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setForeground(Color.BLACK);
		lblAdresse.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblAdresse.setBounds(10, 251, 122, 29);
		panel_6.add(lblAdresse);
		
		JLabel lblEmail = new JLabel("Numero Passport");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEmail.setBounds(10, 11, 198, 29);
		panel_6.add(lblEmail);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(10, 51, 574, 29);
		panel_6.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(10, 131, 574, 29);
		panel_6.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(10, 211, 574, 29);
		panel_6.add(textField_7);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(10, 291, 574, 29);
		panel_6.add(textField_9);
		
		button_3 = new JButton("Valider");
		button_3.setVisible(false);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					Statement st = (Statement) conn.createStatement();
					st.executeUpdate("insert into passagers values("+textField_5.getText()+",'"+textField_6.getText()+"','"+textField_7.getText()+"','"+textField_9.getText()+"')");
					textField_5.setText("");
					textField_6.setText("");
					textField_7.setText("");
					textField_9.setText("");
					setVisible(5);
					update();
					System.out.println("Un ligne de donnee a ete ajoute au tableau passagers avec succes");
				} catch (Exception x) {
					System.out.println("Erreur d'ajout au tableau passagers");
				}
			}
		});
		button_3.setForeground(Color.BLACK);
		button_3.setBackground(Color.WHITE);
		button_3.setBounds(480, 450, 104, 40);
		panel_6.add(button_3);
		
		JButton button_4 = new JButton("Annuler");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(5);
			}
		});
		button_4.setForeground(Color.BLACK);
		button_4.setBackground(Color.WHITE);
		button_4.setBounds(366, 450, 104, 40);
		panel_6.add(button_4);
		
		button_10 = new JButton("Valider");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int key = Integer.parseInt(table_2.getValueAt(table_2.getSelectedRow(), 0).toString());
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					PreparedStatement st1 = (PreparedStatement) conn.prepareStatement("update passagers set nom ='"+textField_6.getText()+"', prenom = '"+textField_7.getText()+"', adresse = '"+textField_9.getText()+"' where cin = "+key+"");
					st1.executeUpdate();
					PreparedStatement st2 = (PreparedStatement) conn.prepareStatement("update passagers set cin = "+textField_5.getText()+" where cin = "+key+"");
					st2.executeUpdate();
					setVisible(5);
					update();
				} catch (SQLException x) {
					System.out.println("Erreur"+x.getMessage());
				}
			}
		});
		button_10.setVisible(false);
		button_10.setForeground(Color.BLACK);
		button_10.setBackground(Color.WHITE);
		button_10.setBounds(480, 450, 104, 40);
		panel_6.add(button_10);
		
		panel_5 = new JPanel();
		panel_5.setVisible(false);
		panel_5.setBackground(Color.GRAY);
		panel_5.setBounds(140, 0, 594, 501);
		frmSystmeDeGestion.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 60, 574, 386);
		panel_5.add(scrollPane_2);
		
		table_2 = new JTable(){
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		scrollPane_2.setViewportView(table_2);
		
		JLabel lblListeDePassagers = new JLabel("Liste de Passagers");
		lblListeDePassagers.setForeground(Color.BLACK);
		lblListeDePassagers.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblListeDePassagers.setBounds(10, 11, 152, 43);
		panel_5.add(lblListeDePassagers);
		
		button_2 = new JButton("Ajouter");
		button_2.setForeground(Color.BLACK);
		button_2.setBackground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_3.setVisible(true);
				button_10.setVisible(false);
				setVisible(6);
			}
		});
		button_2.setBounds(480, 450, 104, 40);
		panel_5.add(button_2);
		
		btnModifier_1 = new JButton("Modifier");
		btnModifier_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_3.setVisible(false);
				button_10.setVisible(true);
				textField_5.setText(table_2.getValueAt(table_2.getSelectedRow(), 0).toString());
				textField_6.setText(table_2.getValueAt(table_2.getSelectedRow(), 1).toString());
				textField_7.setText(table_2.getValueAt(table_2.getSelectedRow(), 2).toString());
				textField_9.setText(table_2.getValueAt(table_2.getSelectedRow(), 3).toString());
				setVisible(6);
			}
		});
		btnModifier_1.setForeground(Color.BLACK);
		btnModifier_1.setBackground(Color.WHITE);
		btnModifier_1.setBounds(366, 450, 104, 40);
		panel_5.add(btnModifier_1);
		
		JButton btnSupprimer_1 = new JButton("Supprimer");
		btnSupprimer_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int key = (int) table_2.getValueAt(table_2.getSelectedRow(), 0);
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					PreparedStatement st1 = (PreparedStatement) conn.prepareStatement("delete from vol_passager where cin = ?");
					PreparedStatement st2 = (PreparedStatement) conn.prepareStatement("delete from passagers where cin = ?");
					st1.setInt(1,key);
					st1.executeUpdate();
					st2.setInt(1,key);
					st2.executeUpdate();
					update();
					System.out.println("Succes suppression d'element de tableau passagers");
				} catch(Exception x){
					System.out.println("Erreur de suppression d'un element du tableau passagers");
				}
			}
		});
		btnSupprimer_1.setForeground(Color.BLACK);
		btnSupprimer_1.setBackground(Color.WHITE);
		btnSupprimer_1.setBounds(252, 450, 104, 40);
		panel_5.add(btnSupprimer_1);
		
		panel_4 = new JPanel();
		panel_4.setBackground(Color.GRAY);
		panel_4.setVisible(false);
		panel_4.setBounds(140, 0, 594, 501);
		frmSystmeDeGestion.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setForeground(Color.BLACK);
		lblNom.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNom.setBounds(10, 11, 122, 29);
		panel_4.add(lblNom);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 50, 574, 29);
		panel_4.add(textField_2);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setForeground(Color.BLACK);
		lblPrnom.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblPrnom.setBounds(10, 90, 122, 29);
		panel_4.add(lblPrnom);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(10, 130, 574, 29);
		panel_4.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 210, 574, 29);
		panel_4.add(textField_4);
		
		JLabel lblLicense = new JLabel("License");
		lblLicense.setForeground(Color.BLACK);
		lblLicense.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLicense.setBounds(10, 170, 122, 29);
		panel_4.add(lblLicense);
		
		button_1 = new JButton("Valider");
		button_1.setVisible(false);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					Statement st = (Statement) conn.createStatement();
					st.executeUpdate("insert into pilotes values("+textField_4.getText()+",'"+textField_3.getText()+"','"+textField_2.getText()+"')");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					setVisible(3);
					update();
					System.out.println("Un ligne de donnee a ete ajoute au tableau pilotes avec succes");
				} catch (Exception x) {
					System.out.println("Erreur d'ajout au tableau pilotes");
				}
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(480, 450, 104, 40);
		panel_4.add(button_1);
		
		JButton btnAnnuler_1 = new JButton("Annuler");
		btnAnnuler_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(3);
			}
		});
		btnAnnuler_1.setForeground(Color.BLACK);
		btnAnnuler_1.setBackground(Color.WHITE);
		btnAnnuler_1.setBounds(366, 450, 104, 40);
		panel_4.add(btnAnnuler_1);
		
		button_9 = new JButton("Valider");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int key = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					PreparedStatement st1 = (PreparedStatement) conn.prepareStatement("update pilotes set nom ='"+textField_2.getText()+"',prenom = '"+textField_3.getText()+"' where license = "+key+"");
					st1.executeUpdate();
					PreparedStatement st2 = (PreparedStatement) conn.prepareStatement("update pilotes set license = "+textField_4.getText()+" where license = "+key+"");
					st2.executeUpdate();
					setVisible(3);
					update();
				} catch (Exception x) {
					System.out.println("Erreur");
				}
			}
		});
		button_9.setVisible(false);
		button_9.setForeground(Color.BLACK);
		button_9.setBackground(Color.WHITE);
		button_9.setBounds(480, 450, 104, 40);
		panel_4.add(button_9);
		
		panel_3 = new JPanel();
		panel_3.setVisible(false);
		panel_3.setBackground(Color.GRAY);
		panel_3.setBounds(140, 0, 594, 501);
		frmSystmeDeGestion.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblListeDePilotes = new JLabel("Liste de Pilotes");
		lblListeDePilotes.setBounds(10, 11, 160, 43);
		lblListeDePilotes.setForeground(Color.BLACK);
		lblListeDePilotes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel_3.add(lblListeDePilotes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 60, 574, 386);
		panel_3.add(scrollPane_1);
		
		table_1 = new JTable(){
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		scrollPane_1.setViewportView(table_1);
		
		JButton button = new JButton("Ajouter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_1.setVisible(true);
				button_9.setVisible(false);
				setVisible(4);
			}
		});
		button.setForeground(Color.BLACK);
		button.setBackground(Color.WHITE);
		button.setBounds(480, 450, 104, 40);
		panel_3.add(button);
		
		JButton btnModifier_3 = new JButton("Modifier");
		btnModifier_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_1.setVisible(false);
				button_9.setVisible(true);
				textField_4.setText(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
				textField_2.setText(table_1.getValueAt(table_1.getSelectedRow(), 1).toString());
				textField_3.setText(table_1.getValueAt(table_1.getSelectedRow(), 2).toString());
				setVisible(4);
			}
		});
		btnModifier_3.setForeground(Color.BLACK);
		btnModifier_3.setBackground(Color.WHITE);
		btnModifier_3.setBounds(366, 450, 104, 40);
		panel_3.add(btnModifier_3);
		
		JButton btnSupprimer_3 = new JButton("Supprimer");
		btnSupprimer_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int key = (int) table_1.getValueAt(table_1.getSelectedRow(), 0);
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					PreparedStatement st1 = (PreparedStatement) conn.prepareStatement("delete from vols where license = ?");
					PreparedStatement st2 = (PreparedStatement) conn.prepareStatement("delete from pilotes where license = ?");
					st1.setInt(1,key);
					st1.executeUpdate();
					st2.setInt(1,key);
					st2.executeUpdate();
					update();
					System.out.println("Succes suppression d'element de tableau pilotes");
				} catch(Exception x){
					System.out.println("Erreur de suppression d'un element du tableau pilotes");
				}
			}
		});
		btnSupprimer_3.setForeground(Color.BLACK);
		btnSupprimer_3.setBackground(Color.WHITE);
		btnSupprimer_3.setBounds(252, 450, 104, 40);
		panel_3.add(btnSupprimer_3);
		
		panel_2 = new JPanel();
		panel_2.setForeground(Color.WHITE);
		panel_2.setBackground(Color.GRAY);
		panel_2.setVisible(false);
		panel_2.setBounds(140, 0, 594, 501);
		frmSystmeDeGestion.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 51, 574, 29);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setForeground(Color.BLACK);
		lblModel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblModel.setBounds(10, 11, 122, 29);
		panel_2.add(lblModel);
		
		JLabel lblCapacit = new JLabel("Capacit\u00E9");
		lblCapacit.setForeground(Color.BLACK);
		lblCapacit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCapacit.setBounds(10, 91, 122, 29);
		panel_2.add(lblCapacit);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 131, 574, 29);
		panel_2.add(textField_1);
		
		btnValider = new JButton("Valider");
		btnValider.setVisible(false);
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					Statement st = (Statement) conn.createStatement();
					ResultSet rs = st.executeQuery("select max(num) from avions");
					int x = 1;
				    while (rs.next()) {
				    	if (rs.getString(1)!=null) x = Integer.parseInt(rs.getString(1)) + 1 ;
				    }
					st.executeUpdate("insert into avions values("+x+","+textField_1.getText()+",'"+textField.getText()+"')");
					textField.setText("");
					textField_1.setText("");
					setVisible(1);
					update();
					System.out.println("Un ligne de donnee a ete ajoute au tableau avions avec succes");
				} catch (Exception x) {
					System.out.println("Erreur d'ajout au tableau avions");
				}
			}
		});
		btnValider.setForeground(Color.BLACK);
		btnValider.setBackground(Color.WHITE);
		btnValider.setBounds(480, 450, 104, 40);
		panel_2.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(1);
			}
		});
		btnAnnuler.setForeground(Color.BLACK);
		btnAnnuler.setBackground(Color.WHITE);
		btnAnnuler.setBounds(366, 450, 104, 40);
		panel_2.add(btnAnnuler);
		
		button_8 = new JButton("Valider");
		button_8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					int key = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					PreparedStatement st = (PreparedStatement) conn.prepareStatement("update avions set capacite = "+textField_1.getText()+", model ='"+textField.getText()+"' where num = "+key+"");
					st.executeUpdate();
					setVisible(1);
					update();
				} catch (Exception x) {
					System.out.println("Erreur");
				}
			}
		});
		button_8.setVisible(false);
		button_8.setForeground(Color.BLACK);
		button_8.setBackground(Color.WHITE);
		button_8.setBounds(480, 450, 104, 40);
		panel_2.add(button_8);
		panel_1.setBounds(140, 0, 594, 501);
		frmSystmeDeGestion.getContentPane().add(panel_1);
		panel_1.setBackground(Color.GRAY);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 574, 386);
		panel_1.add(scrollPane);
		
		table = new JTable() {
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		table.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("Liste d'Avions");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(10, 11, 160, 43);
		panel_1.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Ajouter");
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_8.setVisible(false);
				btnValider.setVisible(true);
				setVisible(2);
			}
		});
		btnNewButton_2.setBounds(480, 450, 104, 40);
		panel_1.add(btnNewButton_2);
		
		JButton btnModifier_2 = new JButton("Modifier");
		btnModifier_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_8.setVisible(true);
				btnValider.setVisible(false);
				textField.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				textField_1.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				setVisible(2);
			}
		});
		btnModifier_2.setForeground(Color.BLACK);
		btnModifier_2.setBackground(Color.WHITE);
		btnModifier_2.setBounds(366, 450, 104, 40);
		panel_1.add(btnModifier_2);
		
		JButton btnSupprimer_2 = new JButton("Supprimer");
		btnSupprimer_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int key = (int) table.getValueAt(table.getSelectedRow(), 0);
					Connection conn = (Connection) DriverManager.getConnection(URI,USERNAME, PASSWORD);
					PreparedStatement st1 = (PreparedStatement) conn.prepareStatement("delete from vols where num = ?");
					PreparedStatement st2 = (PreparedStatement) conn.prepareStatement("delete from avions where num = ?");
					st1.setInt(1,key);
					st1.executeUpdate();
					st2.setInt(1,key);
					st2.executeUpdate();
					update();
					System.out.println("Succes suppression d'element de tableau avions");
				} catch(SQLException x){
					System.out.println("Erreur" +x.getMessage());
				}
			}
		});
		btnSupprimer_2.setForeground(Color.BLACK);
		btnSupprimer_2.setBackground(Color.WHITE);
		btnSupprimer_2.setBounds(252, 450, 104, 40);
		panel_1.add(btnSupprimer_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 141, 501);
		frmSystmeDeGestion.getContentPane().add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 46, 119, 2);
		panel.add(separator);
		
		JLabel lblAdministrateur = new JLabel("Administrateur");
		lblAdministrateur.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrateur.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblAdministrateur.setForeground(Color.WHITE);
		lblAdministrateur.setBounds(10, 11, 119, 37);
		panel.add(lblAdministrateur);
		
		JButton btnNewButton = new JButton("Vols");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(7);
			}
		});
		btnNewButton.setBounds(0, 60, 141, 40);
		panel.add(btnNewButton);
		
		JButton btnAvions = new JButton("Avions");
		btnAvions.setForeground(Color.BLACK);
		btnAvions.setBackground(Color.WHITE);
		btnAvions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(1);
			}
		});
		btnAvions.setBounds(0, 100, 141, 40);
		panel.add(btnAvions);
		
		JButton btnPilotes = new JButton("Pilotes");
		btnPilotes.setForeground(Color.BLACK);
		btnPilotes.setBackground(Color.WHITE);
		btnPilotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(3);
			}
		});
		btnPilotes.setBounds(0, 140, 141, 40);
		panel.add(btnPilotes);
		
		JButton btnNewButton_1 = new JButton("Passagers");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(5);
			}
		});
		btnNewButton_1.setBounds(0, 180, 141, 40);
		panel.add(btnNewButton_1);
		
		connect();
		update();
	}	
}
