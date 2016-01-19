import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

public class Settings extends JDialog implements ActionListener{
	boolean changed;
	JButton buttonOK,buttonCancel;
	JTextField text1,text2,text3;
	
	public Settings(Frame owner){

		
		JPanel upPanel,buttonPanel;
		this.getContentPane().setLayout(new GridLayout(5,0,0,10));
		upPanel=new JPanel(new FlowLayout());
		buttonPanel=new JPanel(new FlowLayout());
		
		JLabel label1,label2,label3,label4;
		label1=new JLabel("场地边长：");
		label2=new JLabel("高：");
		label3=new JLabel("                          每一次前进间隔（毫秒）：");
		label4=new JLabel("                        吃食物得分为 (1000/间隔)^2 ");

		
		text1=new JTextField();
		text2=new JTextField();
		text1.setText(Integer.toString(Main.window.column));
		text2.setText(Integer.toString(Main.window.row));
		text3=new JTextField();
		text3.setText(Integer.toString(Main.interval));
		text3.setHorizontalAlignment(JTextField.CENTER);
		                                     
		upPanel.add(label1);
		upPanel.add(text1);
		//upPanel.add(label2);
		//upPanel.add(text2);
		this.add(upPanel);
		this.add(label3);
		this.add(text3);
		this.add(label4);
		
		buttonOK=new JButton("确定");
		buttonCancel=new JButton("取消");
		buttonOK.addActionListener(this);
		buttonCancel.addActionListener(this);
		
		buttonPanel.add(buttonOK,BorderLayout.SOUTH);
		buttonPanel.add(buttonCancel,BorderLayout.SOUTH);				
		this.add(buttonPanel);
		
		this.setResizable(false);
		this.setSize(300,250);
		this.setLocationRelativeTo(owner);
		this.setTitle("设置");
		this.setModal(true);
		this.setVisible(true);
	}
	
	public void  actionPerformed (ActionEvent e ) {
		JButton clickedButton = (JButton) e.getSource();
		if (clickedButton==buttonOK){
			changed=false;
			if (!text1.getText().equals(Integer.toString(Main.window.column))) changed=true;
			//if (!text2.getText().equals(Integer.toString(Main.window.row))) changed=true;
			if (!text3.getText().equals(Integer.toString(Main.interval))) changed=true;
			if (changed==false) 
				if (Main.pause==5){
					Main.pause();
					this.dispose();
				}else
					this.dispose();
			else{
				Main.interval=Integer.parseInt(text3.getText());
				Main.window.dispose();
				this.dispose();
				Main.init(Integer.parseInt(text1.getText()),Integer.parseInt(text1.getText()));
			}
		}else
			this.dispose(); 
	}

}
