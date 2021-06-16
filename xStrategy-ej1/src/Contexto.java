
public class Contexto {

	private Estrategia estrategia;

	public void elegirEstrategia(Estrategia estrategiaElegida) {
		this.estrategia = estrategiaElegida; // Se aplica la estrategia
	}

	public int ejecutarEstrategia(int a, int b) {
		return estrategia.ejecutar(a, b); // Se ejecuta la estrategia
	}

}
