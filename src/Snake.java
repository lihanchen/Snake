import java.awt.Color;

import javax.swing.*;

public class Snake {
	public enum Direction{
		up,down,left,right;
	}
	
	public Direction direction;
	public BodyNode head;
	
	public Snake(WindowMain window){
		head=new BodyNode(new Coordinate(0,(window.row-1)/2,window), 
				new BodyNode(new Coordinate(0,(window.row-1)/2-1,window)) );
		direction=Direction.right;
	}
	
	public void delTail(){
		BodyNode nowNode=head;
		while (nowNode.next.next!=null)
			nowNode=nowNode.next;
		Main.window.setColor(nowNode.next.pos, Color.lightGray);
		nowNode.next=null;
	}
	
	public Coordinate getTail(){
		BodyNode nowNode=head;
		while (nowNode.next!=null)
			nowNode=nowNode.next;
		return nowNode.pos;
	}
	
	public void nextMove(){
		Coordinate nextCoordinate=head.pos.getNext(direction);
		Main.window.setColor(head.pos,Color.blue);
		if (nextCoordinate==null){
			JOptionPane.showMessageDialog(Main.window,"游戏结束","失败",JOptionPane.INFORMATION_MESSAGE);
			Main.timer.cancel();
			return;
		}
		head=new BodyNode(nextCoordinate,head);
		if (nextCoordinate.equals(Main.food)){ 
			Main.window.setColor(nextCoordinate, Color.black);
			Main.food=Main.foodMaker.generateFood(Main.window.points);
			Main.window.setColor(Main.food, Color.red);
			Main.score+=1000000/Main.interval/Main.interval;
			Main.window.labelScore.setText("得分:"+Main.score+"分     ");
		}else{
			delTail();
			if (Main.window.getColor(nextCoordinate)==Color.blue){
				JOptionPane.showMessageDialog(Main.window,"游戏结束","失败",JOptionPane.INFORMATION_MESSAGE);
				Main.timer.cancel();
				return;
			}
			Main.window.setColor(nextCoordinate, Color.black);
		}		
	}
}

class BodyNode{
	Coordinate pos;
	BodyNode next;
	
	public BodyNode(Coordinate coo){
		pos=coo;
		next=null;
	}
	
	public BodyNode(Coordinate coo,BodyNode next){
		pos=coo;
		this.next=next;
	}
}