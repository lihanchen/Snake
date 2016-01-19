import java.awt.*;
import java.util.*;

public class Main {
	public static WindowMain window;
	public static Coordinate food;
	public static int score=0,interval=250;
	public static Snake snake;
	public static Timer timer;
	public static Food foodMaker;
	public static Snake.Direction originalDir;
	public static short pause;  //0运行，1暂停，2准备开始，3结束，5设置暂停中
	
	public static void main(String args[]){
		init(30,30);
	}
	
	public static void init(int row,int column){
		if (window!=null) window.dispose();
		window=new WindowMain(row,column);
		window.requestFocus();
		snake=new Snake(window);
		window.repaint(snake);
		originalDir=snake.direction;
		foodMaker=new Food(window.row, window.column);
		Main.food=Main.foodMaker.generateFood(Main.window.points);
		window.setColor(Main.food, Color.red);
		if (timer!=null) timer.cancel();
		timer=new Timer();
		score=0;
		pause=2;
		pause();
	}
	
	public static void pause(){
		if (pause==0){
			pause=1;
			window.labelDir.setText("暂停");
			window.centerPanel.setVisible(false); 
		}else{
			pause=2;
			window.centerPanel.setVisible(true); 
			Main.window.labelDir.setText("3");
			timer.cancel();
			timer=new Timer();
			timer.scheduleAtFixedRate(new MyTask(), 1000, 1000);
		}
	}
}

class MyTask extends java.util.TimerTask{ 
    @Override
    public void run() { 
    	if (Main.pause==0){
    		Main.snake.nextMove();
    		Main.originalDir=Main.snake.direction;
    		return;
    	}
    	if (Main.pause==2){ 
    		if (Main.window.labelDir.getText().equals("3")){
    			Main.window.labelDir.setText("2");
    			return;
    		}
    		if (Main.window.labelDir.getText().equals("2")){
    			Main.window.labelDir.setText("1");
    			return;
    		}
    		if (Main.window.labelDir.getText().equals("1")){
    			Main.pause=0;
    			if (Main.snake.direction==Snake.Direction.up) Main.window.labelDir.setText("↑   ");
    			if (Main.snake.direction==Snake.Direction.down) Main.window.labelDir.setText("↓   ");
    			if (Main.snake.direction==Snake.Direction.left) Main.window.labelDir.setText("←   ");
    			if (Main.snake.direction==Snake.Direction.right) Main.window.labelDir.setText("→   ");
    			Main.timer.cancel();
    			Main.timer=new Timer();
    			Main.timer.scheduleAtFixedRate(new MyTask(), 0, Main.interval);
    			return;
    		}
    	}
    }
}