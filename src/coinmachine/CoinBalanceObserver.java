package coinmachine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.*;

public class CoinBalanceObserver extends JFrame implements Observer{
	JLabel balanceLabel, statusLabel;
	JProgressBar numCoins;
	JButton button1, button2, button3;
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
