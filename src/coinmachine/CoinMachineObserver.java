package coinmachine;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class CoinMachineObserver implements Observer{

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
