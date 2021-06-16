
public class Main {

	public static void main(String[] args) {

		Contexto contexto = new Contexto();
		Estrategia multiplicacion = new EstrategiaMultiplicar();
		Estrategia resta = new EstrategiaResta();

		contexto.elegirEstrategia(multiplicacion);

		System.out.println("Multiplicacion: " + contexto.ejecutarEstrategia(1, 2));

		contexto.elegirEstrategia(resta);

		System.out.println("Resta: " + contexto.ejecutarEstrategia(1, 2));

	}

}
