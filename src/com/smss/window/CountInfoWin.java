package com.smss.window;

import javax.swing.JPanel;
import javax.swing.JLabel;

import VO.CountInfo;

import com.smss.linkDB.DAOFactory;

import java.awt.Font;
import java.util.List;

public class CountInfoWin extends JPanel {

	public CountInfoWin() {
		setLayout(null);
		
		JLabel label = new JLabel("统计信息");
		label.setFont(new Font("微软雅黑", Font.BOLD, 24));
		label.setBounds(257, 22, 200, 50);
		add(label);
		
		JLabel label_1 = new JLabel("总销售量");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_1.setBounds(84, 73, 89, 50);
		add(label_1);
		
		JLabel labDealPrice = new JLabel("0.00");
		labDealPrice.setFont(new Font("微软雅黑", Font.BOLD, 18));
		labDealPrice.setBounds(183, 82, 89, 39);
		add(labDealPrice);
		
		JLabel label_2 = new JLabel("总销售单");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_2.setBounds(84, 123, 89, 45);
		add(label_2);
		
		JLabel labSaleCount = new JLabel("0.00");
		labSaleCount.setFont(new Font("微软雅黑", Font.BOLD, 18));
		labSaleCount.setBounds(183, 126, 89, 39);
		add(labSaleCount);
		
		JLabel label_3 = new JLabel("会员数量");
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_3.setBounds(84, 175, 89, 39);
		add(label_3);
		
		JLabel labMemberCount = new JLabel("0.00");
		labMemberCount.setFont(new Font("微软雅黑", Font.BOLD, 18));
		labMemberCount.setBounds(183, 175, 89, 39);
		add(labMemberCount);
		
		JLabel label_4 = new JLabel("供应商数");
		label_4.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_4.setBounds(84, 215, 89, 39);
		add(label_4);
		
		JLabel labProvideCount = new JLabel("0.00");
		labProvideCount.setFont(new Font("微软雅黑", Font.BOLD, 18));
		labProvideCount.setBounds(183, 215, 89, 39);
		add(labProvideCount);
		
		JLabel label_5 = new JLabel("用户数量");
		label_5.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_5.setBounds(84, 260, 89, 39);
		add(label_5);
		
		JLabel labUserCount = new JLabel("0.00");
		labUserCount.setFont(new Font("微软雅黑", Font.BOLD, 18));
		labUserCount.setBounds(183, 260, 89, 39);
		add(labUserCount);
		
		JLabel label_7 = new JLabel("商品总量");
		label_7.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_7.setBounds(84, 298, 89, 39);
		add(label_7);
		
		JLabel labMerchPriceCount = new JLabel("0.00");
		labMerchPriceCount.setFont(new Font("微软雅黑", Font.BOLD, 18));
		labMerchPriceCount.setBounds(183, 298, 89, 39);
		add(labMerchPriceCount);
		
		JLabel label_6 = new JLabel("商品总资");
		label_6.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_6.setBounds(84, 346, 89, 39);
		add(label_6);
		JLabel labMerchCount = new JLabel("375.0");
		labMerchCount.setFont(new Font("微软雅黑", Font.BOLD, 18));
		labMerchCount.setBounds(179, 346, 89, 39);
		add(labMerchCount);
		
		List list = DAOFactory.getCountInfostance().getAllCountInfo();
		labSaleCount.setText(Double.toString(((CountInfo)list.get(0)).getNum()));
		labDealPrice.setText(Double.toString(((CountInfo)list.get(1)).getNum()));
		labMemberCount.setText(Double.toString(((CountInfo)list.get(2)).getNum()));
		labProvideCount.setText(Double.toString(((CountInfo)list.get(3)).getNum()));
		labUserCount.setText(Double.toString(((CountInfo)list.get(4)).getNum()));
		labMerchPriceCount.setText(Double.toString(((CountInfo)list.get(5)).getNum()));
		labMerchCount.setText(Double.toString(((CountInfo)list.get(6)).getNum()));
	}
}
