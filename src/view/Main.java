package view;

import javax.swing.JOptionPane;

import controller.TaskController;

public class Main {
	
	public static void main ( String[] args ) {
		
		TaskController tc = new TaskController ( );
		
		String options[] = { "Matar por PID", "Matar por nome" };
		int option;
		
		tc.callProcess( "tasklist" , true);
		
		while ( true ) {
			
			option = JOptionPane.showOptionDialog (
					null,
					"Qual função quer chamar?", "Escolha uma opção",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
					options, options[0]
			);
			
			switch ( option ) {
				case 0: tc.killProcess ( Integer.parseInt ( JOptionPane.showInputDialog ( "Digite o PID do processo: " ))); break;
				case 1: tc.killProcess ( JOptionPane.showInputDialog ( "Digite o nome do processo: " ) ); break;
				default: System.exit ( 0 );
			}
		}
	}
}