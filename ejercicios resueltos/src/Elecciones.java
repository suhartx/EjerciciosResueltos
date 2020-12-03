import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;

public class Elecciones {
	
	static String nombres[] = { "Luis", "María", "Elena", "Andoni", "Isabel", "Asier", "Andoni", "Luis",
			"Carlos", "Elena", "Luis", "Aitziber", "Luis"};
	
	public static void main(String[] args) {	
		
		System.out.println(nombres.length);
		
		LinkedList<String> lista =  new LinkedList<String>();
		
		for (int i = 0; i < nombres.length; i++) {
			lista.add(nombres[i]);
		}
		System.out.println(lista);
		
		ListIterator<String> litr = lista.listIterator(lista.size());
		
		ArrayList<String> listaAlReves = new ArrayList<>();
		
		while (litr.hasPrevious()) {
			
			listaAlReves.add(litr.previous());
			
		}
		
		System.out.println(listaAlReves.toString());
		
		HashSet<CuentaNombre> set = new HashSet<CuentaNombre>();

		int contador=1;
		for (String nombres : listaAlReves) {
			CuentaNombre cuenta = new CuentaNombre(nombres, contador);
			set.add(cuenta);
			contador++;

		}
		System.out.println(set.size());
		System.out.println(set.toString());
		
		System.out.println("lo mismo pero con contador ordenado");

		Iterator<CuentaNombre> iterador = set.iterator();
		contador=1;
        while (iterador.hasNext()) { 
        	iterador.next().setContador(contador);

            contador++;
        } 

        System.out.println(set.toString()); 
        
       Set<CuentaNombre> hashSetToTreeSet = new TreeSet<CuentaNombre>(set);
        
        System.out.println(hashSetToTreeSet.toString());
        
        
	}



}
class CuentaNombre{
		
		String nombre;
		
		int contador;

		public CuentaNombre(String nombre , int contador) {

			this.nombre = nombre;
			this.contador =contador;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public int getContador() {
			return contador;
		}

		public void setContador(int contador) {
			this.contador = contador;
		}


		
		@Override
		public String toString() {
			return "CuentaNombre [nombre=" + nombre + ", contador=" + contador + "]";
		}

		@Override
		public int hashCode() {
		  return nombre.hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
		  if (o instanceof CuentaNombre) {
			  CuentaNombre p = (CuentaNombre)o;
		    return this.nombre.equals(p.nombre);
		  } else {
		    return false;
		  }
		}
		
	}

