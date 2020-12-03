import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class TablaBasica extends JFrame{

	
	public TablaBasica() {
	
		setSize(600, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO Auto-generated constructor stub
		MiModelo modeloTabla = new MiModelo();
		
		
		
		JTable tabla =  new JTable(modeloTabla);
		
		modeloTabla.addColumn("Columna 1");
		modeloTabla.addColumn("Columna 2");
		modeloTabla.addColumn("Columna 3");
		
		Object [] fila = new Object[3];
		
		fila[0] = "data columna 0";
		fila[1] = 2;
		fila[2] = true;
		
		
		modeloTabla.addRow(fila);
		modeloTabla.addRow(fila);
		
		tabla.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int fila = tabla.rowAtPoint(e.getPoint());
				int columna = tabla.columnAtPoint(e.getPoint());
				//MUSHO OHO A ESTO
				if (columna>-1&&fila>-1) {
					System.out.println("columna " + columna);
					System.out.println("has seleccionado el dato " + modeloTabla.getValueAt(fila, columna));
					
				
				}
				
			}
		});
		
		modeloTabla.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				
				System.out.println("EL DATO HA CAMBIADO");
				
			}
		});
		//cambiamos valores de la tabla
		modeloTabla.setValueAt(4, 0, 1);
		
		//add(tabla, BorderLayout.CENTER);
		
		add(new JScrollPane(tabla), BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new TablaBasica();			}
		});	
	}
	
	public class MiModelo extends DefaultTableModel{

		@Override
		public boolean isCellEditable(int row, int column) {

			if (column ==1) {
				return true;
			}else {
				return false;
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub			
			if (columnIndex==2) { return boolean.class;}
			else if(columnIndex == 1 ) {return int.class;}
					
			return Object.class;
		}
	}

}
