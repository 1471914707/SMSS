import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


public class FirstFrame {
	public static void testSwing(){
		JFrame frame = new JFrame("swing测试");
		JButton bnEast = new JButton("east");
		JButton bnWest = new JButton("west");
		JButton bnNorth = new JButton("north");
		JButton bnSouth = new JButton("south");
		JButton bnCenter = new JButton("center");
		frame.add(bnEast,BorderLayout.EAST);
		frame.add(bnWest,BorderLayout.WEST);
		frame.add(bnNorth,BorderLayout.NORTH);
		frame.add(bnSouth,BorderLayout.SOUTH);
		frame.add(bnCenter,BorderLayout.CENTER);
		//设置窗体的大小
		//frame.setSize(800,600);
		//自动调整窗体的默认大小
		frame.pack();
		//设置窗体标题：
		frame.setTitle("我的第一个窗体");
		//窗体默认是隐藏的
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Welcome to kende's home");
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		JButton button = null;
		for(int i = 0; i < 9; i ++){
			button = new JButton("按钮-" + i);
			frame.add(button);
		}
		frame.setSize(800,500);
		frame.setLocation(400, 300);
		frame.setVisible(true);

	}

}
