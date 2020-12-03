

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;

public class VentanaQuijote extends JFrame {

	private JTextArea taTexto;
	private JScrollPane spTexto;
	
	public VentanaQuijote() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Don Quijote de la Mancha" );
		setSize( 800, 600 );
		setLocationRelativeTo( null );  // Pone la ventana relativa a la pantalla
		taTexto = new JTextArea();
		spTexto = new JScrollPane( taTexto );
		add( spTexto, BorderLayout.CENTER );
		JPanel pBotonera = new JPanel();
		JButton bPagArriba = new JButton( "^" );
		JButton bPagAbajo = new JButton( "v" );
		pBotonera.add( bPagArriba );
		pBotonera.add( bPagAbajo );
		add( pBotonera, BorderLayout.SOUTH );
		bPagArriba.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				muevePagina( -(spTexto.getHeight()-20) );
			}
		});
		bPagAbajo.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				muevePagina( (spTexto.getHeight()-20) );
			}
		});
	}
	
	private void muevePagina( int pixelsVertical ) {
		
		

		JScrollBar bVertical = spTexto.getVerticalScrollBar();
		System.out.println( "Moviendo texto de " + bVertical.getValue() + " a " + (bVertical.getValue()+pixelsVertical) );
		
			Thread hilo1 = new Thread() {
				public void run() {
					
					
					long numero = 0;

					if(pixelsVertical<0) {
						
						while (numero<Math.abs(pixelsVertical)) {
							numero+=20;
	
							bVertical.setValue( bVertical.getValue() - 20 );
	
							try { Thread.sleep(20); } catch (InterruptedException ex) {}
						}
						
					}else {
						
						while (numero<pixelsVertical) {
							numero+=20;
	
							bVertical.setValue( bVertical.getValue() + 20 );
	
							try { Thread.sleep(20); } catch (InterruptedException ex) {}
						}
					}
				}
			};
		// TODO Cambiar este comportamiento de acuerdo a los comentarios de la cabecera de clase
		//		JScrollBar bVertical = spTexto.getVerticalScrollBar();
		//		System.out.println( "Moviendo texto de " + bVertical.getValue() + " a " + (bVertical.getValue()+pixelsVertical) );
		//		bVertical.setValue( bVertical.getValue() + pixelsVertical );
			hilo1.run();
		

	}
	
	private void cargaQuijote() {
		try {
			Scanner scanner = new Scanner( VentanaQuijote.class.getResourceAsStream( "DonQuijote.txt" ), "UTF-8" );
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				taTexto.append( linea + "\n" );
			}
			scanner.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, "No se ha podido cargar el texto" );
		}
		
	}

	public static void main(String[] args) {
		VentanaQuijote v = new VentanaQuijote();
		v.setVisible( true );
		v.cargaQuijote();
	}

}
