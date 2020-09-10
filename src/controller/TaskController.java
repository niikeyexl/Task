package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TaskController {
	
	public TaskController ( ) {}
	
	public String os ( ) {
		return System.getProperty ( "os.name" ).toLowerCase ( );
	}
	
	public void callProcess ( String url, boolean read ) {
		
		Runtime runtime = Runtime.getRuntime ( );
		StringBuffer sb = new StringBuffer   ( );
		Process process = null;
		
		sb.append ( url );
		
		try {
			
			process = runtime.exec ( sb.toString ( ));
		} catch ( Exception e ) {
			
			String message = e.getMessage ( );
			if ( message.contains ( "740" )) { // process need admin perm
				
				sb.insert ( 0, "cmd /c " );
				try {System.out.println ( "d" ); process = runtime.exec ( sb.toString ( )); }
				catch (IOException e1) { e1.printStackTrace(); }
			} else { e.printStackTrace ( ); }
		}
		
		// leitura de process
		if ( process == null && !read ) return;

		InputStream fluxo = process.getInputStream ( );
		InputStreamReader leitor = new InputStreamReader ( fluxo );
		BufferedReader buffer = new BufferedReader ( leitor );
		
		String ln;
		try {
			ln = buffer.readLine ( );
			while ( ln != null ) {
				System.out.println ( ln );
				ln = buffer.readLine ( );
			}
			fluxo.close  ( );
			leitor.close ( );
			buffer.close ( );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void killProcess ( String name ) {
		
		StringBuffer sb = new StringBuffer ( );
		sb.append ( "TASKKILL /IM " );
		sb.append ( name );
		
		callProcess ( sb.toString ( ), false);
	}
	
	public void killProcess ( Integer pid ) {
		
		StringBuffer sb = new StringBuffer ( );
		
		
		sb.append ( "TASKKILL /PID " );
		sb.append ( pid );
		
		callProcess ( sb.toString ( ), false);
	}
}