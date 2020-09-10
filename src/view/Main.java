package view;

import javax.swing.JOptionPane;

import controller.TaskController;

public class Main {
	
	public static void main ( String[] args ) {
		
		TaskController tc = new TaskController ( );
		String os = tc.os ( );
		
		String options[] = { "Matar por PID", "Matar por nome", "Fechar"};
        int option;
        if ( os.contains ( "linux" )) tc.callProcess ( "ps -ef" , true );
        else if ( os.contains ( "windows" )) tc.callProcess ( "tasklist" , true );
        else return; //os não suportado

        while ( true ) {
			
			option = JOptionPane.showOptionDialog (
					null,
					"Qual função quer chamar?", "Escolha uma opção",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
					options, options[0]
			);
			
			switch ( option ) {
				case 0: tc.killProcess ( os, Integer.parseInt ( JOptionPane.showInputDialog ( "Digite o PID do processo: " ))); break;
				case 1: tc.killProcess ( os, JOptionPane.showInputDialog ( "Digite o nome do processo: " ) ); break;
				default: System.exit ( 0 );
			}
		}
	}
}