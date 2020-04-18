

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class Ball extends Thread{
		int x,y,dy,no;
		Color c;
		MyPanel p;

		public Ball(MyPanel mp){
			p = mp;
			x = (int)(Math.random()*p.getWidth());
			y = (int)(Math.random()*p.getHeight());
			c = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));

			dy = 1;

			no = (int)(Math.random()*10);
		}

		public void run(){
			while(true){
				if(y <= 0) dy = 1;
				if(y+30 > p.getHeight()) dy = -1;

				y += dy;
				p.repaint();
				try{
					Thread.sleep(10);
				}catch(Exception e){}
			}
		}
}


class MyPanel extends JPanel{
	ArrayList <Ball> al;

	public MyPanel(ArrayList <Ball> list){
		al = list;
	}

	public void paint(Graphics g){
		super.paint(g);
		for(Ball b : al){
			g.setColor(b.c);
			g.fillOval(b.x,b.y,30,30);
		}
	}
}


class MainFrame extends JFrame{
	MyPanel mp;
	JButton btnStart;
	ArrayList <Ball> list;

	public MainFrame(){
		
		btnStart  = new JButton("Start");
		list = new ArrayList();
		mp = new MyPanel(list);

		setTitle("Ball Animation");
		setSize(400,400);
		setLocationRelativeTo(null);
		add(mp,"Center");
		add(btnStart,"South");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);


		btnStart.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent ae){
				Ball b = new Ball(mp);
				list.add(b);
				b.start();
			}
		});
	}


	public static void main(String args[]){
		new MainFrame();
	}
}
