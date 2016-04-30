package coinmachine;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.*;

/**
 * An Observer which observe CoinMachine and can insert coin with GUI.
 * @author Napon Kittisiriprasert
 */
public class CoinBalanceObserver extends JFrame implements Observer{
	private JLabel balanceLabel, statusLabel;
	private JProgressBar numCoins;
	private TitledBorder lowerTitle;
	private JButton button1, button2, button3;
	private JPanel mainPanel, upperPanel, lowerPanel;
	private CoinMachine coinMachine;
	
	/**
	 * Constructor of the CoinBalanceObserver.
	 * @param cm the CoinMachine.
	 */
	public CoinBalanceObserver(CoinMachine cm) {
		this.coinMachine = cm;
		this.setTitle("CoinBalanceObserverGUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
	}
	
	/**
	 * add GUI components for the CoinBalanceObserver.
	 */
	private void initComponents() {
		balanceLabel = new JLabel("Balance: 0");
		statusLabel = new JLabel("Status:");
		numCoins = new JProgressBar();
		numCoins.setForeground(Color.GREEN);
		
		ClassLoader loader = this.getClass().getClassLoader();
		
		button1 = new JButton();
		URL url1 = loader.getResource("images/1baht.png");
		ImageIcon img1 = new ImageIcon(url1);
		button1.setIcon(img1);
		
		button2 = new JButton();
		URL url2 = loader.getResource("images/5baht.png");
		ImageIcon img2 = new ImageIcon(url2);
		button2.setIcon(img2);
		
		button3 = new JButton();
		URL url3 = loader.getResource("images/10baht.png");
		ImageIcon img3 = new ImageIcon(url3);
		button3.setIcon(img3);
		
		upperPanel = new JPanel(new FlowLayout());
		
		upperPanel.add(balanceLabel);
		upperPanel.add(statusLabel);
		upperPanel.add(numCoins);
		
		lowerPanel = new JPanel(new FlowLayout());
		lowerTitle = BorderFactory.createTitledBorder("Insert Money");
		lowerTitle.setTitleJustification(TitledBorder.LEFT);
		lowerPanel.setBorder(lowerTitle);
		
		lowerPanel.add(button1);
		lowerPanel.add(button2);
		lowerPanel.add(button3);
		
		button1.addActionListener(new CoinButtonListener(1));
		button2.addActionListener(new CoinButtonListener(5));
		button3.addActionListener(new CoinButtonListener(10));
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		mainPanel.add(upperPanel);
		mainPanel.add(lowerPanel);
		
		this.add(mainPanel);
	}
	
	/**
	 * Run the GUI of CoinBalanceObserver.
	 */
	public void run() {
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Update the progress bar and balance when there is a change.
	 * @param o the Observable which notify changes.
	 * @param arg the object sent when updated.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o != null && o instanceof CoinMachine) {
			CoinMachine cm = (CoinMachine) o;
			numCoins.setMaximum(cm.getCapacity());
			numCoins.setString(""+cm.getCount());
			numCoins.setValue(cm.getCount());
			numCoins.setStringPainted(true);;
			balanceLabel.setText("Balance: " + cm.getBalance());
			if (cm.getCount() == cm.getCapacity()) {
				numCoins.setForeground(Color.RED);
			}
		}
	}
	
	/**
	 * Action Listener of coin insert button.
	 */
	class CoinButtonListener implements ActionListener {
		private int inputMoney;
		
		/**
		 * Constructor of coin button action listener.
		 * @param inputMoney the amount of the coin to be added.
		 */
		public CoinButtonListener(int inputMoney) {
			this.inputMoney = inputMoney;
		}
		
		/* 
		 * Insert Coin to the CoinMachine.
		 */
		public void actionPerformed( ActionEvent evt ) {
			coinMachine.insert(new Coin(inputMoney));
		}
	}
}
