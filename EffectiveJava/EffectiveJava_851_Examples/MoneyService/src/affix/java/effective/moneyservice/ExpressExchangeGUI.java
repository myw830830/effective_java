package affix.java.effective.moneyservice;

import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ExpressExchangeGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JPanel currencyPanel = new JPanel();
	private String[] currencies; 
	private JComboBox<String> currencyJCB;
	
	private JPanel userPanel = new JPanel();
	private JLabel currencyJL = new JLabel("  Currency", SwingConstants.LEFT);
	private JTextField currencyJTF = new JTextField();
	private JLabel amountJL = new JLabel("  Amount", SwingConstants.LEFT);
	private JTextField amountJTF = new JTextField();	
	private JLabel rateJL = new JLabel("  Rate", SwingConstants.LEFT);
	private JTextField rateJTF = new JTextField();
	private JLabel totalJL = new JLabel("  Total", SwingConstants.LEFT);
	private JTextField totalJTF = new JTextField();	
	
	private JPanel controlPanel = new JPanel();
	private JButton reportJB = new JButton("Report");	
	private JButton buyJB = new JButton("Buy");
	private JButton sellJB = new JButton("Sell");
	private JButton exitJB = new JButton("Exit");
	
	private MoneyService model;
	private TransactionMode mode;
	
	public ExpressExchangeGUI(MoneyService ms){
	
		model = ms;

		this.setLayout(new BorderLayout());
		
		currencyPanel.setLayout(new GridLayout(2, 1));
		currencyPanel.setBorder(new TitledBorder("Currencies"));

		Map<String, Currency> currencyMap = ms.getCurrencyMap();
		currencies = (String[])currencyMap.keySet().toArray(new String[currencyMap.size()]);
		for(int i=0; i<currencies.length; i++){
			currencies[i] = "    " + currencies[i] + "     ";
		}
		currencyJCB = new JComboBox<String>(currencies);
		currencyJCB.addActionListener(new CurrencyActionListener());
		currencyPanel.add(currencyJCB);		
		this.add(currencyPanel, BorderLayout.EAST);
		
		userPanel.setLayout(new GridLayout(4, 2));
		userPanel.setBorder(new TitledBorder("Transaction"));
		userPanel.add(currencyJL);
		currencyJTF.setEditable(false);
		currencyJTF.setBackground(Color.WHITE);
		userPanel.add(currencyJTF);
		userPanel.add(amountJL);
		userPanel.add(amountJTF);
		userPanel.add(rateJL);
		rateJTF.setEditable(false);
		rateJTF.setBackground(Color.WHITE);
		userPanel.add(rateJTF);
		userPanel.add(totalJL);
		totalJTF.setEditable(false);
		totalJTF.setBackground(Color.WHITE);
		userPanel.add(totalJTF);
		this.add(userPanel, BorderLayout.CENTER);
		
		reportJB.addActionListener(new ControlActionListener());
		controlPanel.add(reportJB);
		buyJB.addActionListener(new ControlActionListener());
		controlPanel.add(buyJB);
		sellJB.addActionListener(new ControlActionListener());
		controlPanel.add(sellJB);
		exitJB.addActionListener(new ControlActionListener());
		controlPanel.add(exitJB);
		this.add(controlPanel, BorderLayout.SOUTH);
		
		// trigger an initial currency choice
		Runnable startUpRunner = new Runnable(){
			public void run(){
				currencyJCB.setSelectedIndex(3);
			}
		};
		SwingUtilities.invokeLater(startUpRunner);
		
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	
	class CurrencyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			amountJTF.setText("");
			totalJTF.setText("");
			String currencyCode = ((String)currencyJCB.getSelectedItem()).trim();
			currencyJTF.setText(currencyCode);
			double buyRate = model.getCurrencyMap().get(currencyCode).getBuyRate();
			double sellRate = model.getCurrencyMap().get(currencyCode).getSellRate();
			rateJTF.setText(String.format("%.2f / %.2f", buyRate, sellRate));
		}
			
	}
	
	
	class ControlActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ae) {
			
			JButton temp = (JButton)ae.getSource();
			
			if(temp == reportJB){
				model.printMoneyReport("Console");
			}
					
			if(temp == buyJB || temp == sellJB){
				
				mode = (temp == buyJB)? TransactionMode.BUY: TransactionMode.SELL;
								
				if(amountJTF.getText().isEmpty()){
					JOptionPane.showMessageDialog(ExpressExchangeGUI.this, "Amount missing", "Transaction Message", JOptionPane.ERROR_MESSAGE);
				}
				else{
					double total = confirmTransaction();
					if(total > 0){
						totalJTF.setText(String.format("%.0f", total));
						JOptionPane.showMessageDialog(ExpressExchangeGUI.this,  
								"Confirm transaction", "User Confirmation", JOptionPane.PLAIN_MESSAGE);							
					}			
				}
				amountJTF.setText("");
				totalJTF.setText("");
			}	
			
			if(temp == exitJB){
				model.shutDownService();
				System.exit(0);
			}
		}
		
		private double confirmTransaction() throws IllegalArgumentException{
			
			double transactionValue = 0;
			
			try{
				String currencyCode = ((String)currencyJCB.getSelectedItem()).trim();
				int amount = Integer.parseInt(amountJTF.getText());
				Order anOrder = new Order(currencyCode, amount, mode);
				if(mode == TransactionMode.BUY){
					transactionValue = model.buyMoney(anOrder);
					if(!(transactionValue > 0)){
						JOptionPane.showMessageDialog(ExpressExchangeGUI.this, "Cannot afford transaction", "Transaction Message", JOptionPane.WARNING_MESSAGE);
					}
				}
				else{
					transactionValue = model.sellMoney(anOrder);
					if(!(transactionValue > 0)){
						JOptionPane.showMessageDialog(ExpressExchangeGUI.this, "Amount not available", "Transaction Message", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(ExpressExchangeGUI.this, "Amount must be numerical!", "Transaction Message", JOptionPane.ERROR_MESSAGE);
			}
			catch(IllegalArgumentException e){
				JOptionPane.showMessageDialog(ExpressExchangeGUI.this, "Amount must be >0 !", "Transaction Message", JOptionPane.ERROR_MESSAGE);
			}

			return transactionValue;
		}
	}
	
}
