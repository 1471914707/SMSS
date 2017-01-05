package com.smss.window;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import VO.MerchInfo;

import com.smss.linkDB.DAOFactory;

import java.awt.Color;
import java.util.Vector;

public class addMerchWin {

	private JFrame frame;
	private JTextField txtMerchName;
	private JTextField txtBarCode;
	private JTextField txtPrice;
	private JTextField txtNum;
	private static JLabel labProvideID;

	public addMerchWin(DefaultTableModel JTableModel,int x) {
		frame = new JFrame();
		frame.setBounds(100, 100, 351, 366);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品信息修改");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		lblNewLabel.setBounds(123, 10, 132, 28);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblid = new JLabel("商品ID");
		lblid.setFont(new Font("微软雅黑", Font.BOLD, 16));
		lblid.setBounds(10, 58, 69, 28);
		frame.getContentPane().add(lblid);
		
		JLabel label = new JLabel("   名称");
		label.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label.setBounds(10, 98, 69, 28);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("条形码");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_1.setBounds(10, 132, 69, 28);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("   单价");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_2.setBounds(10, 170, 69, 28);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("   库存");
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_3.setBounds(10, 208, 69, 28);
		frame.getContentPane().add(label_3);
		
		JLabel lblid_1 = new JLabel("供应商ID");
		lblid_1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		lblid_1.setBounds(0, 246, 79, 28);
		frame.getContentPane().add(lblid_1);
		
		JLabel labMerchID = new JLabel("自动生成");
		labMerchID.setFont(new Font("微软雅黑", Font.BOLD, 16));
		labMerchID.setBounds(83, 58, 182, 28);
		frame.getContentPane().add(labMerchID);
		
		txtMerchName = new JTextField();
		txtMerchName.setBounds(83, 98, 200, 28);
		frame.getContentPane().add(txtMerchName);
		txtMerchName.setColumns(10);
		
		txtBarCode = new JTextField();
		txtBarCode.setColumns(10);
		txtBarCode.setBounds(83, 132, 200, 28);
		frame.getContentPane().add(txtBarCode);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(83, 170, 200, 28);
		frame.getContentPane().add(txtPrice);
		
		txtNum = new JTextField();
		txtNum.setColumns(10);
		txtNum.setBounds(83, 210, 200, 28);
		frame.getContentPane().add(txtNum);
		
		labProvideID = new JLabel((String) null);
		labProvideID.setFont(new Font("微软雅黑", Font.BOLD, 16));
		labProvideID.setBounds(83, 246, 200, 28);
		frame.getContentPane().add(labProvideID);
		
		JButton btnSure = new JButton("确定");
		btnSure.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnSure.setBounds(123, 284, 93, 33);
		frame.getContentPane().add(btnSure);
		
		JButton btnSearchProvide = new JButton("...");
		btnSearchProvide.setForeground(Color.WHITE);
		btnSearchProvide.setBounds(287, 248, 38, 27);
		frame.getContentPane().add(btnSearchProvide);
		
/*		labMerchID.setText((String) JTableModel.getValueAt(x, 0));
		txtMerchName.setText((String) JTableModel.getValueAt(x, 1));
		txtBarCode.setText((String) JTableModel.getValueAt(x, 2));
		txtPrice.setText(Double.toString((Double)JTableModel.getValueAt(x, 3)));
		txtNum.setText(Integer.toString((Integer) JTableModel.getValueAt(x, 4)));
		labProvideID.setText((String)JTableModel.getValueAt(x, 5));*/
		
		btnSearchProvide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new SelectProvide(labProvideID);
			}
		});
		
		btnSure.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				MerchInfo merchInfo = new MerchInfo();
				Vector vc = new Vector();
				merchInfo.setBarCode(txtBarCode.getText().toString().trim());
				merchInfo.setMerchName(txtMerchName.getText().toString().trim());
				merchInfo.setMerchPrice(Double.parseDouble(txtPrice.getText().toString().trim()));
				merchInfo.setProvideID(labProvideID.getText().toString());
				merchInfo.setMerchNum(Integer.parseInt(txtNum.getText().toString().trim()));
				DAOFactory.getMerchInfoInstance().save(merchInfo);
				MerchInfo m1 = new MerchInfo();
				m1 = DAOFactory.getMerchInfoInstance().searchByBarCode(merchInfo.getBarCode());
				vc.addElement(m1.getMerchID());
				vc.addElement(merchInfo.getMerchName());
				vc.addElement(merchInfo.getBarCode());
				vc.addElement(merchInfo.getMerchPrice());
				vc.addElement(merchInfo.getMerchNum());
				vc.addElement(merchInfo.getProvideID());
				vc.addElement("删除");
				JTableModel.addRow(vc);
				frame.dispose();
			}
		});
			
		frame.setVisible(true);
	}
}
