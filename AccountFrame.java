import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.TreeSet;
import java.awt.event.*;
import java.util.List;
import java.util.Set;
import  javax.swing.*;
import java.awt.*;


public class AccountFrame extends JFrame{
	JLabel accnNOLBL, ownerLBL, balanceLBL, cityLBL , genderLBL , amountLBL;
	JTextField accnNoTXT, ownerTXT, balanceTXT , amountTXT;
	JComboBox<City> citiesCMB;
	
	JButton newBTN, saveBTN, showBTN, quiteBTN, depositeBTN, withdrawBTN;
	JRadioButton maleRDB, femaleRDB;
	ButtonGroup genderBTNGRP;
	
	JList<Account> accountsLST;
	JPanel p1,p2,p3,p4,p5;
	
	Set<Account> accountSet=new  TreeSet<>();
	Account  acc, x;
	boolean  newRec =true;
	
	//Combobox data
	DefaultComboBoxModel<City> citiesCMBDL;
	DefaultListModel<Account>  accountsLSTMDL;
	
	//Table Data
	JTable table;
	DefaultTableModel tableModel;
	ArrayList<Transaction> transList =new ArrayList<>();
	
	
	public AccountFrame(){
		// super("Accounts Operations");
		// setLayout(null);
		// setSize(600,400);
		super("Accounts Operations");
        setLayout(null);
        setSize(600, 400);
        
		// Initialize panel objects
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
        // Set GUI colors
		
        Color background = new Color(230, 247, 255); // Light Blue
		Color panelColor = new Color(255, 255, 255);
        
        getContentPane().setBackground(background); // Set the background color
        
        // Panel colors
        p1.setBackground(panelColor);
        p2.setBackground(panelColor);
        p3.setBackground(panelColor);
        p4.setBackground(panelColor);
        p5.setBackground(panelColor);
		
		//adding components o the Frame
		
		//1-Labels
		accnNOLBL  =new JLabel("Account NO.");
		ownerLBL   =new JLabel("Owner");
		balanceLBL =new JLabel("Balance");
		cityLBL    =new JLabel("City");
		genderLBL  =new JLabel("Gender");
		amountLBL  =new JLabel("Amount");
		
		
		//2-TextFields
		accnNoTXT=new JTextField();   accnNoTXT.setEnabled(false);
		ownerTXT=new JTextField();
		balanceTXT=new JTextField(); balanceTXT.setEnabled(false);
		amountTXT=new JTextField();
		amountTXT.setPreferredSize(new Dimension(150,25));
		
		//3-Combobox
		citiesCMBDL=new DefaultComboBoxModel<>();
		citiesCMBDL.addElement(null);
		citiesCMBDL.addElement(new City("Lahore", "Punjab"));
		citiesCMBDL.addElement(new City("Karachi", "Sindh"));
		citiesCMBDL.addElement(new City("Islamabad", "Islamabad Capital Territory"));
		citiesCMBDL.addElement(new City("Hyderabad", "Sindh"));
		citiesCMBDL.addElement(new City("Gujranwala", "Punjab"));
		citiesCMBDL.addElement(new City("Abbottabad", "Khyber Pakhtunkhwa"));
		citiesCMBDL.addElement(new City("Sialkot", "Punjab"));
		citiesCMBDL.addElement(new City("Sargodha", "Punjab"));
		citiesCMBDL.addElement(new City("Rawalpindi", "Punjab"));
		citiesCMBDL.addElement(new City("Faisalabad", "Punjab"));
		citiesCMBDL.addElement(new City("Multan", "Punjab"));
		citiesCMBDL.addElement(new City("Peshawar", "Khyber Pakhtunkhwa"));
		citiesCMBDL.addElement(new City("Quetta", "Balochistan"));
		
		//adding data to combobox 
		citiesCMB  =new JComboBox<City>(citiesCMBDL);
		
		//4-radio buttons
		maleRDB=new JRadioButton("Male",true);
		femaleRDB=new JRadioButton("Female");
		genderBTNGRP=new ButtonGroup();
		genderBTNGRP.add(maleRDB);
		genderBTNGRP.add(femaleRDB);
		
		//5-Buttons 
		newBTN     =new JButton("New");
		saveBTN    =new JButton("Save");
		showBTN    =new JButton("Show");
		quiteBTN   =new JButton("Quite");
		depositeBTN=new JButton("Deposite");
		withdrawBTN=new JButton("WithDraw");
		
		//6 -Tables
		accountsLSTMDL =new  DefaultListModel<>();
		accountsLST    =new  JList<>(accountsLSTMDL);
		
		//7-panels
		p1=new JPanel();
		p1.setBounds(5,5,300,150);
		p1.setLayout(new GridLayout(5,2));
		
		p2=new JPanel();
		p2.setBounds(5,155,300,40);
		p2.setLayout(new FlowLayout());
		
		p3=new JPanel();
		p3.setBounds(5,195,600,40);
		p3.setLayout(new FlowLayout());
		
		p4=new JPanel();
		p4.setBounds(305,5,300,190);
		p4.setLayout(new BorderLayout());
		
		p5=new JPanel();
		p5.setBounds(5,240,580,120);
		p5.setLayout(new BorderLayout());
		
		//Adding Component to frame
		
		p1.add(accnNOLBL);
		p1.add(accnNoTXT);
		p1.add(ownerLBL);
		p1.add(ownerTXT);
		p1.add(balanceLBL);
		p1.add(balanceTXT);
		p1.add(cityLBL);
		p1.add(citiesCMB);
		p1.add(maleRDB);
		p1.add(femaleRDB);
		
		
		p2.add(newBTN);
		p2.add(saveBTN);
		p2.add(showBTN);
		p2.add(quiteBTN);
		
		
		p3.add(amountLBL);
		p3.add(amountTXT);
		p3.add(depositeBTN);
		p3.add(withdrawBTN);
		
		p4.add(accountsLST);
		
		
		//add panels to Frame
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5); 
		
		//table creation 
		tableModel =new DefaultTableModel();
		
		table =new JTable(tableModel);
		tableModel.addColumn("TrsNO");
		tableModel.addColumn("TrsDate");
		tableModel.addColumn("TrsType");
		tableModel.addColumn("TrsAmount");
		
		JScrollPane scrollPane=new JScrollPane(table);
		p5.add(scrollPane);
		
		//***********Functionalities**************
		
		newBTN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				accnNoTXT.setText("");
				ownerTXT.setText("");
				citiesCMB.setSelectedIndex(0);
				maleRDB.setSelected(true);
				balanceTXT.setText("");
				amountTXT.setText("");
				newRec =true;
			}
		});//newBTN end
		
		saveBTN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(newRec){
					//Insertion
					if(ownerTXT.getText().length()  !=0){
						acc=new Account(
							ownerTXT.getText(),
							(City)citiesCMB.getSelectedItem(),
							maleRDB.isSelected()? 'M':'F');
							
						accnNoTXT.setText(String.valueOf(acc.accNo));
						accountSet.add(acc);
						accountsLSTMDL.addElement(acc);
						newRec = false;
						
					}else{
						JOptionPane.showMessageDialog(null,"Please Fill All Fields");
					}
				}else{
					//Updating
					accountSet.remove(acc);
					
					int a=Integer.parseInt(accnNoTXT.getText());
					String o=ownerTXT.getText();
					City c=(City)citiesCMB.getSelectedItem();
					
					char g=maleRDB.isSelected()?'M':'F';
					double b=Double.parseDouble(balanceTXT.getText());
					acc =new Account(a,o,c,g,b);
					accountSet.add(acc);
					accountsLSTMDL.setElementAt(acc,accountsLST.getSelectedIndex());
					newRec=false;
				}
			}
		});//save Button end
	
	    
		showBTN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s="";
				Iterator<Account> it =accountSet.iterator();
				while(it.hasNext()){
					s+=it.next().toString()+"\n";
					JOptionPane.showMessageDialog(null,s );
				}
			}
		});//end show Button
		
		
		quiteBTN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,"            Good Bye");
				System.exit(0);
			}
		});// end Quite Button
		
		
		//DepositeButton
		depositeBTN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!newRec && amountTXT.getText().length() !=0){
					
					//Adding Transaction to table
					Transaction  t=new Transaction(acc, LocalDate.now(),
					'D', Double.parseDouble(amountTXT.getText()));
					DispalyTransactionInTable(t);
					
					//perform deposite from account
					acc.deposite(Double.parseDouble(amountTXT.getText()));
					balanceTXT.setText(String.valueOf(acc.balance));
				}
			}
		});//end  Deposite Button
		
		withdrawBTN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!newRec  &&   amountTXT.getText().length() !=0){
					//Adding Transaction to table
					Transaction t= new Transaction(
							acc, LocalDate.now(),
					'W', Double.parseDouble(amountTXT.getText()));
					
					DispalyTransactionInTable(t);
					
					//perform withdraw on account
					acc.withdraw(Double.parseDouble(amountTXT.getText()));
					balanceTXT.setText(String.valueOf(acc.balance));
				}
			}
		});//end Withdraw button
		
		accountsLST.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				acc = x =accountsLST.getSelectedValue();
				
				accnNoTXT.setText(String.valueOf(acc.accNo));
				ownerTXT.setText(acc.owner);
				citiesCMB.setSelectedItem(acc.city);
				
				if(acc.gender== 'M')maleRDB.setSelected(true);
				else femaleRDB.setSelected(true);
				
				
				balanceTXT.setText(String.valueOf(acc.balance));
				amountTXT.setEnabled(true);
				depositeBTN.setEnabled(true);
				newRec=false;
				
				
				//clear Table
				for(int i=tableModel.getRowCount() -1; i>=0; i--){
					tableModel.removeRow(i);
				}
				
				//get transaction to selected account
				SearchTransactionList(acc.accNo);
			}
		});//end accountsLST
		
		
	}//end AccountFrame constructor
	
	
	private void SearchTransactionList(int accNo){
		List<Transaction> filteredList=new ArrayList<>();
		
		//Iterate Through the list
		for(Transaction t: transList){
			
			//filter values that contain  trsNo
			if(t.getAcc().accNo==accNo){
				filteredList.add(t);
			}
			
		}
			//Display the filtered list
			for(int i=0;i<filteredList.size(); i++){
				
				//Display data into table
				tableModel.addRow(new Object[]{
					filteredList.get(i).getTrsNo(),
					filteredList.get(i).getDate(),
					filteredList.get(i).getOperation(),
					filteredList.get(i).getAmount(),
				});
			}
		 
	}// end SearchTransactionList
	
	
	private void DispalyTransactionInTable(Transaction t){
		//Display data into table
		tableModel.addRow(new Object[]{
			t.getTrsNo(),
			t.getDate(),
			t.getOperation(),
			t.getAmount()
		});
		
		//Adding objects to ArrayList
		transList.add(t);
	}//end DispalyTransactionInTable
	
	
	public static void main(String arg[]){
		AccountFrame af=new AccountFrame();
		af.setVisible(true);
		af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}