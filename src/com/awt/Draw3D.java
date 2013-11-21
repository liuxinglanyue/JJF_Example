package com.awt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 创建一个Draw3D的类，继承JFrame类，同时实现动作监听器，这样
 * 就不需要再写一个监听器类，也不用传参（传画布对象）也不用生成监听器对象
 * http://mntms.iteye.com/blog/1965876
 */
public class Draw3D extends JFrame implements ActionListener {
	
	//入口函数
	public static void main(String[] args) {
		//创建对象并调用类的初始化画板的方法
		Draw3D draw = new Draw3D();
		draw.init();
	}
	
	    //初始化画板的方法
	public void init() {
		this.setTitle("3D画图板");
		this.setSize(800,760);
		this.setDefaultCloseOperation(3);
		
		//使窗体的位置居中
		this.setLocationRelativeTo(null);
		
		//将窗体的默认的布局取消，才能使用setBounds()方法将四个面板插入到指定的位置
		this.setLayout(null);
		
		//将窗体分成四个区域（四个面板），并将每个面板的背景色设置成黑色，更能体现艺术的效果
		jp1 = new JPanel();
		jp1.setBackground(Color.black);
		jp1.setBounds(0,0,400,380);
		jp2 = new JPanel();
		jp2.setBackground(Color.black);
		jp2.setBounds(400, 0, 400, 380);
		jp3 = new JPanel();
		jp3.setBackground(Color.black);
		jp3.setBounds(0, 380, 400, 380);
	        jp4 = new JPanel();
	        jp4.setBackground(Color.black);
		jp4.setBounds(400, 380,400, 380);
		
		//将每个面板的默认的布局取消，才能使用setBounds()方法插入相应的按钮组件
		jp1.setLayout(null);
		jp2.setLayout(null);
		jp3.setLayout(null);
		jp4.setLayout(null);
		
		//给每个面板添加控制按钮，是用setBounds()将按钮组件安放到指定的位置
		//此处使用setBounds()时要注意的是设置坐标的时候是相对于每块面板的，而不是整个窗体
		JButton buC1 = new JButton("删除");
		JButton buC2 = new JButton("删除");
		JButton buC3 = new JButton("删除");
		JButton buC4 = new JButton("删除");
		
		//插入按钮的位置应该相对于每个面板，而不是整个窗体
		buC1.setBounds(280, 10,80, 20);
		buC2.setBounds(280, 10,80, 20);
		buC3.setBounds(280, 10,80, 20);
		buC4.setBounds(280, 10,80, 20);
		JButton bu1 = new JButton("立体矩");
		bu1.setBounds(80, 10,80, 20);
		jp1.add(bu1);
	        jp1.add(buC1);
		JButton bu2 = new JButton("立体球");
		bu2.setBounds(80, 10, 80, 20);
		jp2.add(bu2);
		jp2.add(buC2);
		JButton bu3 = new JButton("体三角");
		bu3.setBounds(80, 10, 80, 20);
		jp3.add(bu3);
		jp3.add(buC3);
		JButton bu4 = new JButton("渐变线");
		bu4.setBounds(80, 10, 80, 20);
		jp4.add(bu4);
		jp4.add(buC4);
		
		//给每个按钮添加命令
		bu1.setActionCommand("3DRect");
		bu2.setActionCommand("3DOval");
		bu3.setActionCommand("Prim");
		bu4.setActionCommand("litix");
		
		//用数字分别代表每个画板上的删除操作clear1代表删除立体矩，clear2代表删除立体球
		//clear3代表删除体三角，clear4代表删除渐变线
		buC1.setActionCommand("clear1");   
		buC2.setActionCommand("clear2"); 
		buC3.setActionCommand("clear3"); 
		buC4.setActionCommand("clear4"); 
		
		//给每个按钮添加监听器
		bu1.addActionListener(this);
		bu2.addActionListener(this);
		bu3.addActionListener(this);
		bu4.addActionListener(this);
		buC1.addActionListener(this);
		buC2.addActionListener(this);
		buC3.addActionListener(this);
		buC4.addActionListener(this);
		
		
		//添加两条分界线，划分出四个区域
		JButton bu6 = new JButton();
		bu6.setBackground(Color.blue);
		bu6.setBounds(400, 5, 1, 780);
		JButton bu7 = new JButton();
		bu7.setBackground(Color.blue);
		bu7.setBounds(3, 380, 780, 1);
		
		//将四块面板添加到窗体上
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(bu6);
		this.add(bu7);
		this.setVisible(true);
		
		//给每个面板获取画布对象，对应相应的面板
		g1 = jp1.getGraphics();
		g2 = jp2.getGraphics();
		g3 = jp3.getGraphics();
		g4 = jp4.getGraphics();
		
	}
	 /**
	  * 重写动作监听器中的方法，实现点击不同的按钮执行相应的操作，通过e.getActionCommand()
	  * 来判断点击的是哪个按钮并执行
	  */
	 public void actionPerformed(ActionEvent e) {
		           
                    
		 if(e.getActionCommand().equals("3DRect")) {   
			    //画3DRect使用循环绘制矩形，同时改变矩形的大小并改变颜色，实现3D的效果
                            for(int i = 0; i <=100; i++) {            
				 int m =i;
				 g1.setColor(new Color(2*i,2*i,i));
				 g1.drawRect(50+m, 50+m, 250-2*m, 250-2*m);
			 }
		 } else if(e.getActionCommand().equals("3DOval")) {
                           //画3DOval,使用fillOval()方法，同时循环改变大小和颜色	                                                
			    for(int m = 0; m <= 100; m++) {
			    	g2.setColor(new Color(m,2*m,m));
			    	g2.fillOval(50+m, 50+m,250-2*m, 250-2*m);
			    }
			 
		 } else if(e.getActionCommand().equals("Prim")) { 
		          //画体三角，首先做一个三角形，在此基础上循环，并改变颜色
			    for(int m = 0; m <= 250; m++) {
			    	g3.setColor(new Color(m,m,250));
			        g3.drawLine(200, 40+m, 20+m,300-m );
			        g3.setColor(new Color(m,250,m));
			        g3.drawLine(200, 40+m, 380-m, 300-m);
			        g3.setColor(new Color(250,m,m));
			        g3.drawLine(380-m, 300-m,20+m , 300-m);
			    }
		 }else if(e.getActionCommand().equals("litix")) {
		         //画渐变线，在一条的基础上实现长度和颜色的渐变 
			 for(int m = 0; m <= 240; m++) {
			    	g4.setColor(new Color(20,m,250));
			        g4.drawLine(80+m,300-m,250-m ,300-m);
			    }
		 }
		 //删除动作的实现，使用每个面板的背景色来作为绘图颜色绘制指定大小的fillRect图形，从而覆盖原来画的图形，实现删除
		 else if(e.getActionCommand().equals("clear1")) {   
			 g1.setColor(jp1.getBackground());
			 g1.fillRect(40, 40,  290, 290);
		 }else if(e.getActionCommand().equals("clear2")) {
			 g2.setColor(jp2.getBackground());
			 g2.fillRect(40, 40,  290, 290);
		 }else if(e.getActionCommand().equals("clear3")) {
			 g3.setColor(jp3.getBackground());
			 g3.fillRect(20, 40,  380, 290);
		 }else if(e.getActionCommand().equals("clear4")) {
			 g4.setColor(jp4.getBackground());
			 g4.fillRect(10, 40,  380, 290);
		 }
			    
	 }
	 
	 //将画布对象和面板对象设置成Draw3D类的私有属性，实现在类中不同方法中的使用
	 private Graphics g1;
	 private Graphics g2;
	 private Graphics g3;
	 private Graphics g4;
	 private JPanel jp1;
	 private JPanel jp2;
	 private JPanel jp3;
	 private JPanel jp4;
}
