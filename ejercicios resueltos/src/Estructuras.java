
public class Estructuras {
	
	public static void main(String[] args) {
		Arbol arbol = new Arbol();
		
		arbol.insertar(4, "Naranja");
		arbol.insertar(8, "Manzana");
		arbol.insertar(1, "Uva");
		arbol.insertar(9, "Sandia");
		arbol.insertar(7, "Pera");
		
		
		arbol.recorrer(arbol.raiz);
	}

	public Estructuras() {
		
	}

}
