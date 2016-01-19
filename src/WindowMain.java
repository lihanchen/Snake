import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class WindowMain extends JFrame implements ActionListener,KeyListener{
	
	JPanel mainPanel,centerPanel,downPanel;
	public int width=600,height=600;
	public int row,column;
	public JPanel points[][]=new JPanel[50][50];
	public JLabel labelScore,labelDir;
	
	JButton settings,exit,pause;
	
	public WindowMain(int row,int column){
		this.row=row;
		this.column=column;
		this.setResizable(false);
		this.setSize(width, height);
		this.setTitle("贪食蛇 1.0");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		mainPanel=new JPanel();
		centerPanel=new JPanel();
		downPanel=new JPanel();
		this.getContentPane().setLayout(new GridLayout());
		this.getContentPane().add(mainPanel);
		initBut();
		downPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		
		labelScore=new JLabel("得分:0分     ");
		labelDir=new JLabel("→   ");
		labelScore.setFont(new Font(null,Font.PLAIN,20));
		labelDir.setFont(new Font(null,Font.BOLD,45));
		downPanel.add(labelScore);
		downPanel.add(labelDir);
		
		settings=new JButton();
		exit=new JButton();
		pause=new JButton();
		pause.setText("暂停（空格）");
		settings.setText("设置");
		exit.setText("退出");
		settings.addActionListener(this);
		exit.addActionListener(this);
		pause.addActionListener(this);
		downPanel.add(pause);
		downPanel.add(settings);
		downPanel.add(exit);
		
		mainPanel.setLayout (new BorderLayout()) ;
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		mainPanel.add(downPanel,BorderLayout.SOUTH);
		
		addKeyListener(this);
		this.setVisible(true);
		this.requestFocus();
	}
	
	public void initBut(){
		centerPanel.setLayout(new GridLayout(row,column,0,0));
		for(int i=0;i<column;i++)
			for(int j=0;j<row;j++){
				points[j][i]=new JPanel();
				points[j][i].setBackground(Color.lightGray);
				centerPanel.add(points[j][i]);
			}
	}
		
	public void  actionPerformed (ActionEvent e ) {
		JButton clickedButton = (JButton) e.getSource();
		if (clickedButton==exit)
			System.exit(0);
		if (clickedButton==pause)
			Main.pause();
		if (clickedButton==settings){
			this.setAlwaysOnTop(false);
			if ( (Main.pause==0) || (Main.pause==2) ){
				Main.pause=0;
				Main.pause();
				Main.pause=5;
			}
			new Settings(this);		
			this.setAlwaysOnTop(true);
		}
		this.requestFocus();	
	}
	
	public void repaint(Snake snake){
		for(int i=0;i<column;i++)
			for(int j=0;j<row;j++)
				points[j][i].setBackground(Color.lightGray);
		BodyNode node=snake.head;
		setColor(node.pos, Color.black);
		node=node.next;
		while (node!=null){
			setColor(node.pos, Color.blue);
			node=node.next;
		}
	}
	
	public void setColor(Coordinate pos,Color color){
		points[pos.x][pos.y].setBackground(color);
	}
	
	public Color getColor(Coordinate pos){
		return points[pos.x][pos.y].getBackground();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.println("asdf");
		switch(key){
		case KeyEvent.VK_UP:			
			if ((Main.pause==0)&&(Main.originalDir!=Snake.Direction.down)){
				if (Main.originalDir==Snake.Direction.up){
					Main.snake.nextMove();
					return;
				}
				Main.snake.direction=Snake.Direction.up;
				labelDir.setText("↑   ");
			}
			break;
		case KeyEvent.VK_DOWN:
			if ((Main.pause==0)&&(Main.originalDir!=Snake.Direction.up)){
				if (Main.originalDir==Snake.Direction.down){
					Main.snake.nextMove();
					return;
				}
				Main.snake.direction=Snake.Direction.down;
				labelDir.setText("↓   ");
			}
			break;
		case KeyEvent.VK_LEFT:
			if ((Main.pause==0)&&(Main.originalDir!=Snake.Direction.right)){
				if (Main.originalDir==Snake.Direction.left){
					Main.snake.nextMove();
					return;
				}
				Main.snake.direction=Snake.Direction.left;
				labelDir.setText("←   ");
			}
			break;
		case KeyEvent.VK_RIGHT:
			if ((Main.pause==0)&&(Main.originalDir!=Snake.Direction.left)){
				if (Main.originalDir==Snake.Direction.right){
					Main.snake.nextMove();
					return;
				}
				Main.snake.direction=Snake.Direction.right;
				labelDir.setText("→   ");
			}
			break;
		case KeyEvent.VK_SPACE:
			Main.pause();
			break;
		}
	}
	
	 public void keyTyped(KeyEvent e) {}
	 public void keyReleased(KeyEvent e) {}
}