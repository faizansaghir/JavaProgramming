package faizan.java.assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Assignment1 {
	public static void main(String[] args) throws IOException {
		File originalTextFile=new File("18ELB158.txt");
		File duplicateTextFile=new File("GK9157.txt");
		duplicateTextFile.createNewFile();
		Scanner sc=new Scanner(originalTextFile);
		FileWriter fw=new FileWriter(duplicateTextFile);
		while(sc.hasNext()) {
			fw.append(sc.nextLine());
			if(sc.hasNext())
				fw.append("");
		}
		sc.close();
		fw.close();
	}
}
