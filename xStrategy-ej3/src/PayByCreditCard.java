
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Concrete strategy. Implements credit card payment method.
 */
public class PayByCreditCard implements PayStrategy {
	private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
	private CreditCard card;

	/**
	 * Collect credit card data.
	 */
	@Override
	public void collectPaymentDetails() {
		try {
			System.out.print("Ingrese numero de tarjeta: ");
			String number = READER.readLine();
			System.out.print("Ingrese expiraci�n de tarjeta 'mm/yy': ");
			String date = READER.readLine();
			System.out.print("Ingrese c�digo de seguridad: ");
			String cvv = READER.readLine();
			card = new CreditCard(number, date, cvv);

			// Validate credit card number...

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * After card validation we can charge customer's credit card.
	 */
	@Override
	public boolean pay(int paymentAmount) {
		if (cardIsPresent()) {
			System.out.println("Pagando " + paymentAmount + " usando tarjeta de credito.");
			card.setAmount(card.getAmount() - paymentAmount);
			return true;
		} else {
			return false;
		}
	}

	private boolean cardIsPresent() {
		return card != null;
	}
}
