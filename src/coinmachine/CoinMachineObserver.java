package coinmachine;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * an observer which observe the CoinMachine.
 * @author Napon Kittisiriprasert
 */
public class CoinMachineObserver implements Observer{

	/* 
	 * override update method to print balance to console.
	 * @param o the Observable which notify changes.
	 * @param arg the object sent when updated.
	 */
	@Override
	public void update(Observable subject, Object info) {
		if (info != null && info instanceof List) {
			List<Coin> coinList = (List<Coin>)info;
			double balance = 0;
			for( int i = 0; i < coinList.size(); i++) {
				balance += coinList.get(i).getValue();
			}
			System.out.println("Balance = " + balance );
		}
	}

}
