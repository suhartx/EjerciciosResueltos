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
		
		System.out.println("linkedlist de lista");
		
		System.out.println(lista.size());
		
		System.out.println(lista);
		
		ListIterator<String> litr = lista.listIterator(lista.size());
		
		ArrayList<String> listaAlReves = new ArrayList<>();
		
		while (litr.hasPrevious()) {
			
			listaAlReves.add(litr.previous());
			
		}
		
		System.out.println("arraylist de linkedlista al reves");
		
		System.out.println(listaAlReves.toString());
		
//		System.out.println("lista de HashSet con for");
		
		HashSet<CuentaNombre> set = new HashSet<CuentaNombre>();


//		for (String nombres : listaAlReves) {
//			CuentaNombre cuenta = new CuentaNombre(nombres);
//			set.add(cuenta);
//
//
//		}
//		
//		System.out.println(set.size());
//		System.out.println(set.toString());
		
		System.out.println("HashSet de lista");

		Iterator<String> iterador = listaAlReves.iterator();

        while (iterador.hasNext()) { 

        	set.add(new CuentaNombre(iterador.next()));

        } 
        
        System.out.println(set.size());

        System.out.println(set.toString()); 
        
        
        
       Set<CuentaNombre> hashSetToTreeSet = new TreeSet<CuentaNombre>();
       
       
       
		Iterator<CuentaNombre> iterador2 = set.iterator();

       while (iterador2.hasNext()) { 

    	   hashSetToTreeSet.add(iterador2.next());

       } 
       
       System.out.println("TreeSet de lista");
       
       System.out.println(hashSetToTreeSet.size());
        
        System.out.println(hashSetToTreeSet.toString());
        
        
	}

}
class CuentaNombre implements Comparable<CuentaNombre>{
		
		String nombre;
		
		int contador =1;

		public CuentaNombre(String nombre) {

			this.nombre = nombre;

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
			  
			  p.contador++;
			  
			  
		    return this.nombre.equals(p.nombre);
		  } else {
		    return false;
		  }
		}

		@Override
		public int compareTo(CuentaNombre c) {
			if (nombre.compareToIgnoreCase(c.nombre)<0&& contador<c.contador) {
				return 1;
			}else if(contador>c.contador) {
				return -1;
			}else {
				return 0;
			}
		}
		
	}

