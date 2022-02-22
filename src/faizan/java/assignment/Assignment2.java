package faizan.java.assignment;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.sun.mail.util.BASE64DecoderStream;

public class Assignment2 {
	public static void main(String[] args) throws IOException {
		File inputImage=new File("Assignment2.pgm");
		System.out.println(inputImage.length());
		File outputImage=new File("Assignment2Duplicate.pgm");
		outputImage.createNewFile();
		DataInputStream dis= new DataInputStream(new FileInputStream(inputImage));
		BufferedWriter bw=new BufferedWriter(new FileWriter(outputImage));
		// Read header
		String header=dis.readLine();
		bw.write(header+"\n");
		
		// Read dimensions
		String dimensions=dis.readLine();
		bw.write(dimensions+"\n");
		Scanner sc=new Scanner(dimensions);
		int width=sc.nextInt();
		int height=sc.nextInt();
		sc.close();
		// Read max value
		int maxValue=Integer.parseInt(dis.readLine());
		bw.write(maxValue+"\n");
		bw.close();
		FileWriter fw= new FileWriter(outputImage);
		fw.append(header+"\n");
		fw.append(dimensions+"\n");
		fw.append(maxValue+"\n");
		System.out.printf("header : %s , width : %d , height: %d , max value : %d\n",header,width,height,maxValue);
		short[][] img=new short[height][width];
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				img[i][j]=(short) dis.readUnsignedByte();
				fw.append((char)(255-img[i][j])+"");
			}
		}
		fw.flush();
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				System.out.print(img[i][j]+" ");
			}
			System.out.println();
		}
	}
}
