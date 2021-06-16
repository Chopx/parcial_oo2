
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * World first console e-commerce application.
 */
public class Main {
	private static Map<Integer, Integer> priceOnProducts = new HashMap<>();
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Order order = new Order();
	private static PayStrategy strategy;

	static {
		priceOnProducts.put(1, 4200);
		priceOnProducts.put(2, 9000);
		priceOnProducts.put(3, 3000);
		priceOnProducts.put(4, 2200);
	}

	public static void main(String[] args) throws IOException {
		while (!order.isClosed()) {
			int cost;

			String continueChoice;
			do {
				System.out.print("Seleccione un producto:" + "\n" + "1 - Motherboard" + "\n" + "2 - CPU" + "\n"
						+ "3 - HDD" + "\n" + "4 - RAM" + "\n");
				int choice = Integer.parseInt(reader.readLine());
				cost = priceOnProducts.get(choice);
				System.out.print("Cantidad: ");
				int count = Integer.parseInt(reader.readLine());
				order.setTotalCost(cost * count);
				System.out.print("¿Quiere seguir agregando items S=Si / N=No? S/N: ");
				continueChoice = reader.readLine();
			} while (continueChoice.equalsIgnoreCase("S"));

			if (strategy == null) {
				System.out.println(
						"Seleccione un metodo de pago:" + "\n" + "1 - PalPay" + "\n" + "2 - Tarjeta de Credito");
				String paymentMethod = reader.readLine();

				// Client creates different strategies based on input from user,
				// application configuration, etc.
				if (paymentMethod.equals("1")) {
					strategy = new PayByPayPal();
				} else {
					strategy = new PayByCreditCard();
				}
			}

			// Order object delegates gathering payment data to strategy object,
			// since only strategies know what data they need to process a
			// payment.
			order.processOrder(strategy);

			System.out.print("El monto es de " + order.getTotalCost()
					+ " ¿Está seguro de querer realizar el pago P=Pagar / A=Anular? P/A: ");
			String proceed = reader.readLine();
			if (proceed.equalsIgnoreCase("P")) {
				// Finally, strategy handles the payment.
				if (strategy.pay(order.getTotalCost())) {
					System.out.println("Pago realizado con éxito.");
				} else {
					System.out.println("Error, revise la información.");
				}
				order.setClosed();
			}
		}
	}
}
