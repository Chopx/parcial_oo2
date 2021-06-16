
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete strategy. Implements PayPal payment method.
 */
public class PayByPayPal implements PayStrategy {
	private static final Map<String, String> DATA_BASE = new HashMap<>();
	private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
	private String email;
	private String password;
	private boolean signedIn;

	static {
		DATA_BASE.put("1234", "juan@gmail.com");
		DATA_BASE.put("qwer", "maria@hotmail.com");
	}

	/**
	 * Collect customer's data.
	 */
	@Override
	public void collectPaymentDetails() {
		try {
			while (!signedIn) {
				System.out.print("Ingrese email de usuario Paypal: ");
				email = READER.readLine();
				System.out.print("Ingrese su contraseña: ");
				password = READER.readLine();
				if (verify()) {
					System.out.println("Verificación correcta.");
				} else {
					System.out.println("Email o Contraseña incorrectos");
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private boolean verify() {
		setSignedIn(email.equals(DATA_BASE.get(password)));
		return signedIn;
	}

	/**
	 * Save customer data for future shopping attempts.
	 */
	@Override
	public boolean pay(int paymentAmount) {
		if (signedIn) {
			System.out.println("Pagando " + paymentAmount + " usando PayPal.");
			return true;
		} else {
			return false;
		}
	}

	private void setSignedIn(boolean signedIn) {
		this.signedIn = signedIn;
	}
}