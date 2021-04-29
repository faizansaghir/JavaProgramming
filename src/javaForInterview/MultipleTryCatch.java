package javaForInterview;

import java.io.IOException;

public class MultipleTryCatch {
	public static void main(String args[]) {
		try {
			int a=0;
			throw new IOException();
		}
		
		//Correct flow
		catch(IOException e) {
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		//Compile error : The exception IOException is already caught by the alternative Exception
		/*
		catch(Exception e) {
			System.out.println(e);
		}
		catch(IOException e) {
			System.out.println(e);
		}
		*/
		
		//Compile error : The exception IOException is already caught by the alternative Exception
		/*
		catch(Exception e | IOException e) {
			System.out.println(e);
		}
		*/
		
		
	}
}
