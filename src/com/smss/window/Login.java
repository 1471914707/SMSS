package com.smss.window;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import VO.SharedVariables;
import VO.User;

import com.smss.linkDB.DAOFactory;

public class Login {

	private JFrame frame;
	private JTextField txtUserName;
	private JTextField txtUserPW;
	private JButton btnLogin;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(SystemColor.menu);
		frame.setBounds(100, 100, 401, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("欢迎登录");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(164, 23, 114, 31);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("账号");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setBounds(65, 71, 54, 22);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("密码");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_2.setBounds(65, 127, 54, 22);
		frame.getContentPane().add(label_2);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(126, 68, 183, 31);
		frame.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		txtUserPW = new JTextField();
		txtUserPW.setColumns(10);
		txtUserPW.setBounds(126, 118, 183, 31);
		frame.getContentPane().add(txtUserPW);
		
		btnLogin = new JButton("确定");
		btnLoginMouseClicked();
		
		btnLogin.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnLogin.setBounds(152, 200, 93, 23);
		frame.getContentPane().add(btnLogin);
	}
	
	private void btnLoginMouseClicked() {
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				User user = new User();
				user = DAOFactory.getUserInstance().searchByName(txtUserName.getText().toString().trim());
				if (user != null){
					if(txtUserPW.getText().toString().trim().equals(user.getUserPW())){
						SharedVariables sa = new SharedVariables();
						sa.setUserID(user.getUserID());
						sa.setAccountString(user.getUserName());
						sa.setUserType(1);
						new MainWindow(sa);
						frame.dispose();
					}else{
						JOptionPane.showMessageDialog(null, "密码错误","错误", JOptionPane.WARNING_MESSAGE); 
					}
				}else {
					JOptionPane.showMessageDialog(null, "不存在该用户","错误", JOptionPane.WARNING_MESSAGE); 
				}
			}
		});
	}
}
