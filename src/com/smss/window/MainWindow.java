package com.smss.window;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import VO.SharedVariables;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;

public class MainWindow {

	private JFrame frame;
	private JButton btnWordBench;
	private JButton btnDealRecord;
	private JButton btnSimpleSale;
	private JButton btnMerchInfo;
	private JButton btnStatInfo;
	private JButton btnProvideManage;
	private JButton btnMemberManage;
	private JButton btnUserManage;
	private static JPanel midJPanel;
	private List<JButton> jbList = new ArrayList<JButton>();
	private SharedVariables sa;
	private JButton btnExit;
	/**
	 * Create the application.
	 */
	public MainWindow(SharedVariables sa) {
		initialize(sa);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(SharedVariables sa) {
		this.sa = sa;
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(152, 251, 152));
		frame.setBounds(100, 100, 740, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		btnWordBench = new JButton("工作台");
		btnWordBench.setBounds(10, 92, 105, 23);
		frame.getContentPane().add(btnWordBench);
		WorkBench workBench = new WorkBench(this.sa);
		workBench.setBackground(SystemColor.menu);
		workBench.setBounds(121, 0, 610, 447);
		frame.getContentPane().add(workBench);
		midJPanel = workBench;
		//EnabledAll(btnWordBench);
		
		
		btnDealRecord = new JButton("交易记录");
		btnDealRecord.setBounds(10, 137, 105, 23);
		frame.getContentPane().add(btnDealRecord);
		btnSimpleSale = new JButton("单品交易");
		btnSimpleSale.setBounds(10, 178, 105, 23);
		frame.getContentPane().add(btnSimpleSale);
		
		btnMerchInfo = new JButton("商品信息");
		btnMerchInfo.setBounds(10, 223, 105, 23);
		frame.getContentPane().add(btnMerchInfo);
		
		btnStatInfo = new JButton("统计信息");
		btnStatInfo.setBounds(10, 266, 105, 23);
		frame.getContentPane().add(btnStatInfo);
		
		btnProvideManage = new JButton("供应商管理");
		btnProvideManage.setBounds(10, 299, 105, 23);
		frame.getContentPane().add(btnProvideManage);
		
		btnMemberManage = new JButton("会员管理");
		btnMemberManage.setBounds(10, 332, 105, 23);
		frame.getContentPane().add(btnMemberManage);
		
		btnUserManage = new JButton("用户管理");
		btnUserManage.setBounds(10, 365, 105, 23);
		frame.getContentPane().add(btnUserManage);
		
		jbList.clear();
		jbList.add(btnWordBench);
		jbList.add(btnDealRecord);
		jbList.add(btnMemberManage);
		jbList.add(btnMerchInfo);
		jbList.add(btnProvideManage);
		jbList.add(btnSimpleSale);
		jbList.add(btnUserManage);
		jbList.add(btnStatInfo);

		
		btnExit = new JButton("退出");
		btnExit.setBounds(10, 410, 105, 23);
		frame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel = new JLabel("欢迎你\r\n"+sa.getAccountString());
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 0, 101, 39);
		frame.getContentPane().add(lblNewLabel);
		

		btnWordBench.addMouseListener(new MouseAdapter() {
				private SharedVariables sa = MainWindow.this.sa;

				@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
					if (btnWordBench.isEnabled()){
						WorkBench workBench = new WorkBench(this.sa );
						workBench.setBackground(SystemColor.menu);
						workBench.setBounds(121, 0, 610, 447);
						//frame.getContentPane().remove(midJPanel);
						midJPanel.setVisible(false);
						frame.getContentPane().add(workBench);
						midJPanel = workBench;
						EnabledAll(btnWordBench);
					}
			}
		});
		btnDealRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnDealRecord.isEnabled()){
					DealRecord dealRecord = new DealRecord();
					dealRecord.setBackground(SystemColor.menu);
					dealRecord.setBounds(121, 0, 610, 447);
					//frame.getContentPane().remove(midJPanel);
					midJPanel.setVisible(false);
					frame.getContentPane().add(dealRecord);
					midJPanel = dealRecord;
					EnabledAll(btnMerchInfo);
				}
			}
		});
		
		btnMerchInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnMerchInfo.isEnabled()){
					MerchInfoWin merchInfoWin = new MerchInfoWin();
					merchInfoWin.setBackground(SystemColor.menu);
					merchInfoWin.setBounds(121, 0, 610, 447);
					//frame.getContentPane().remove(midJPanel);
					midJPanel.setVisible(false);
					frame.getContentPane().add(merchInfoWin);
					midJPanel = merchInfoWin;
					EnabledAll(btnMerchInfo);
				}
			}
		});
		btnProvideManage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnProvideManage.isEnabled()){
					ProvideManageWin provideManageWin = new ProvideManageWin();
					provideManageWin.setBackground(SystemColor.menu);
					provideManageWin.setBounds(121, 0, 610, 447);
					midJPanel.setVisible(false);
					frame.getContentPane().add(provideManageWin);
					midJPanel = provideManageWin;
					EnabledAll(btnProvideManage);
				}
			}
		});
		btnSimpleSale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSimpleSale.isEnabled()){
					SaleRecord saleRecord = new SaleRecord();
					saleRecord.setBackground(SystemColor.menu);
					saleRecord.setBounds(121, 0, 610, 447);
					//frame.getContentPane().remove(midJPanel);
					midJPanel.setVisible(false);
					frame.getContentPane().add(saleRecord);
					midJPanel = saleRecord;
					EnabledAll(btnSimpleSale);
				}
			}
		});
		btnUserManage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub\
				if (btnUserManage.isEnabled()){
					UserManageWin userManageWin = new UserManageWin();
					userManageWin.setBackground(SystemColor.menu);
					userManageWin.setBounds(121, 0, 610, 447);
					midJPanel.setVisible(false);
					frame.getContentPane().add(userManageWin);
					midJPanel = userManageWin;
					EnabledAll(btnUserManage);
				}
			}
		});
		btnStatInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnStatInfo.isEnabled()){
					CountInfoWin countInfoWin = new CountInfoWin();
					countInfoWin.setBackground(SystemColor.menu);
					countInfoWin.setBounds(121, 0, 610, 447);
					midJPanel.setVisible(false);
					frame.getContentPane().add(countInfoWin);
					midJPanel = countInfoWin;
					EnabledAll(btnStatInfo);
				}
			}
		});
		
		btnDealRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnDealRecord.isEnabled()){
					DealRecord dealRecord = new DealRecord();
					dealRecord.setBackground(SystemColor.menu);
					dealRecord.setBounds(121, 0, 610, 447);
					midJPanel.setVisible(false);
					frame.getContentPane().add(dealRecord);
					midJPanel = dealRecord;
					EnabledAll(btnDealRecord);
				}
			}
		});
		
		btnMemberManage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub		
				if (btnMemberManage.isEnabled()){
					MemberMangeWin memberMangeWin = new MemberMangeWin();
					memberMangeWin.setBackground(SystemColor.menu);
					memberMangeWin.setBounds(121, 0, 610, 447);
					midJPanel.setVisible(false);
					frame.getContentPane().add(memberMangeWin);
					midJPanel = memberMangeWin;
					EnabledAll(btnMemberManage);
				}
			}
		});
		
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
	
		if (sa.getUserType() == 0){
			for (int i=0; i<jbList.size(); i++){
				jbList.get(i).setEnabled(false);
			}
		}
	
		frame.setVisible(true);
		
	}
	//设置相应的按钮失活
	private void EnabledAll(JButton button) {
		if (sa.getUserType() == 1){
			for (int i=0; i<jbList.size(); i++){
				if (jbList.get(i) == button){
					button.setEnabled(false);
				}else {
					jbList.get(i).setEnabled(true);
				}
			}
		}else {
			for (int i=0; i<jbList.size(); i++){
				jbList.get(i).setEnabled(false);
			}
		}
	}
}
