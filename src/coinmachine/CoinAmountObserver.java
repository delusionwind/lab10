package coinmachine;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * An Observer of the CoinMachine which shows what is observed with GUI.
 * @author Napon Kittisiriprasert
 */
public class CoinAmountObserver extends JFrame implements Observer{
	private JLabel coinLabel, statusLabel;
	private JTextField numCoin;
	
	/**
	 * Construct the CoinAmountObserver.
	 */
	public CoinAmountObserver() {
		this.setTitle("CoinAmountObserverGUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
	}
	
	/**
	 * create and add GUI components.
	 */
	private void initComponents() {
		coinLabel = new JLabel("#Coins:");
		statusLabel = new JLabel("Accepting Coins");
		statusLabel.setForeground(Color.GREEN);
		numCoin = new JTextField(5);
		numCoin.setEditable(false);
		
		JPanel contents = new JPanel();
		contents.setLayout(new FlowLayout());
		
		contents.add(coinLabel);
		contents.add(numCoin);
		
		JPanel layout = new JPanel();
		layout.setLayout(new GridLayout(2, 0));
		
		JPanel lower = new JPanel();
		lower.setLayout(new FlowLayout());
		
		lower.add(statusLabel);
		
		layout.add(contents);
		layout.add(lower);
		
		this.add(layout);
	}
	
	/**
	 * Run the CoinAmountObserver when called.
	 */
	public void run() {
		this.pack();
		this.setVisible(true);
	}
	
	/* 
	 * Override the update method to update GUI.
	 * @param o the Observable which notify changes.
	 * @param arg the object sent when updated.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o != null && o instanceof CoinMachine) {
			CoinMachine cm = (CoinMachine) o;
			numCoin.setText(""+cm.getCount());
			if ( cm.getCount() == cm.getCapacity() ) {
				statusLabel.setText("Full");
				statusLabel.setForeground(Color.RED);
			}
		}
	}
}
