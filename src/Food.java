import java.awt.Color;
import java.util.Random;

import javax.swing.JPanel;

public class Food {
	Coordinate record[];
	public int size;
	int pointer=0;
	
	public Food(int row,int column){
		size=row*column;
		record=new Coordinate[size];
		for(int i=0;i<column;i++)
			for(int j=0;j<row;j++){
				record[pointer]=new Coordinate(j, i, row, column);
				pointer++;
			}
		pointer=0;
		shuffle();
	}
		
	public void shuffle(){
		int j;
		Coordinate temp;
		Random rand=new Random();
		for(int i=0;i<size;i++){
			j=rand.nextInt(size-1);
			temp=record[i];
			record[i]=record[j];
			record[j]=temp;
		}
	}
	
	public Coordinate generateFood(JPanel points[][]){
		while(Main.window.getColor(record[pointer])!=Color.lightGray){
			pointer++;
			if (pointer>=size){
				shuffle();
				pointer=0;
			}
		}
		Coordinate temp=record[pointer];
		while(Main.window.getColor(record[pointer])!=Color.lightGray){
			pointer++;
			if (pointer>=size){
				shuffle();
				pointer=0;
			}
		}
		return temp;
	}
}
