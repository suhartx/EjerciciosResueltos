

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

public class ErrorAccesoConcurrente {

	private static long CONPAUSA = 50; // msgs de pausa en los hilos
	
	private static JTextArea taSalida = new JTextArea();
	// Utilizamos la estructura Vector porque es síncrona y solo se puede modificar de uno e uno. 
	//Con ArrayList teníamos errores al poder ser modificada en paralelo por varios hilos.
	private static Vector<Long> listaNums = new Vector<>();
	
	//POSIBLE SOLUCIÓN PARA USAR ARRAYLIST - Convierte la implementación de este en síncrona (synchronized).
	//Solo se podrá entrar de 1 en 1 a este método para evitar errores.
	/*public static synchronized void modifyArray(Long num){
		if(num != null) {
			listaNums.add( num );
			println( "Añadido: " + listaNums.toString() );
		} else {
			if (!listaNums.isEmpty()) {
				long primero = listaNums.remove(0);
				println( "Eliminado: " + primero );
			}
		}
	}*/
	public static void main(String[] args) {
		// Ventana de salida
		JFrame f = new JFrame();
		f.setSize( 1000, 800 );
		// f.setLocation( 2000, 0 );
		taSalida.setEditable( false );
		f.add( new JScrollPane( taSalida ) );
		f.setVisible( true );
		println( "Test" ); // Mensaje de prueba
		// Empezamos con la lista [0]
		listaNums.add( 0L ); 
		listaNums.add( 1L ); 
				
		// TODO Programa un hilo que solo va añadiendo números incrementales a la lista por el final
		// Haz que el hilo visualice en la ventana lo que va haciendo y espere un poquito en cada iteración:
		// println( "Añadido: " + listaNums.toString() );
		// if (CONPAUSA>0) try { Thread.sleep(CONPAUSA); } catch (InterruptedException ex) {}
		
		Thread hilo1 = new Thread() {
			public void run() {
				long numero = 0;
				while (true) {
					numero++;
					//modifyArray(numero); /En caso de usar ArrayList comentamos las dos lineas siguientes y activamos esta
					listaNums.add( numero );
					println( "Añadido: " + listaNums.toString() );
					if (CONPAUSA>0) try { Thread.sleep(CONPAUSA); } catch (InterruptedException ex) {}
				}
			}
		};
		hilo1.start();
		
		// TODO Programa otro hilo que solo va quitando números por el principio
		// Haz que el hilo visualice en la ventana lo que va haciendo y espere un poquito en cada iteración:
		//println( "Borrado: " + listaNums.toString() );
		
		Thread hilo2 = new Thread() {
			public void run() {
				while (true) {
					if (!listaNums.isEmpty()) {
						long primero = listaNums.remove(0);
						println( "Eliminado: " + primero );
						//modifyArray(null);	//En caso de usar ArrayList comentamos las dos lineas anteriores y activamos esta
					}
					if (CONPAUSA>0) try { Thread.sleep(CONPAUSA); } catch (InterruptedException ex) {}
				}
			}
		};
		hilo2.start();
		// if (CONPAUSA>0) try { Thread.sleep(CONPAUSA); } catch (InterruptedException ex) {}
		
		// A partir de ahora se tiene que ir viendo en pantalla una lista donde se añaden números por el final 
		// y se quitan por el principio... salvo que haya algún problema de concurrencia y uno de los dos 
		// hilos deje de hacer bien su trabajo
	}

	// Método auxiliar para sacar información en la ventana
	// Lo hacemos syncrhonized para que no haya interferencia entre los hilos a la hora de visualizar
	// probar que si se quita el synchronized hay problemas)
	private static synchronized void println( String mens ) {
		taSalida.append( mens + "\n" );
		taSalida.setSelectionStart( taSalida.getText().length() );
		taSalida.setSelectionEnd( taSalida.getText().length() );
		if (taSalida.getText().length()>100000) {  // Para que no se llene la textarea vamos quitando de vez en cuando
			taSalida.replaceRange( "", 0, 50000 );
		}
		// Aunque en este caso no va a haber problema, sería más correcto hacer esto para respetar a Swing (que no es Thread-safe):
		// try {
		// 	SwingUtilities.invokeAndWait( new Runnable() {
		// 		@Override
		// 		public void run() {
		// 			taSalida.append( mens + "\n" );
		// 			taSalida.setSelectionStart( taSalida.getText().length() );
		// 			taSalida.setSelectionEnd( taSalida.getText().length() );
		// 			if (taSalida.getText().length()>100000) {  // Para que no se llene la textarea vamos quitando de vez en cuando
		// 				taSalida.replaceRange( "", 0, 50000 );
		// 			}
		// 		}
		// 	});
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }
	}

}