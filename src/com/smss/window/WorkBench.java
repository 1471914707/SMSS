package com.smss.window;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import VO.Dealing;
import VO.Member;
import VO.MerchInfo;
import VO.Sale;
import VO.SharedVariables;
import VO.User;

import com.smss.linkDB.DAOFactory;

import java.awt.Color;

public class WorkBench extends JPanel {
	private JTextField txtBarCode;
	private static JTable tabMerchList;
	private JScrollPane scrollPaneTable;
	private static DefaultTableModel JTableModel;
	private JLabel label_1;
	private static JLabel labTotalPrice;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel labBackMoney;
	private JButton btnSussDeal;
	private JTextField txtTruePay;
	private static double TotalPrice = 0;
	private JLabel label_4;
	private JTextField txtMemberCard;
	private JButton btnSearchMerch;
	private SharedVariables sa;
	/**
	 * Create the panel.
	 */
	public WorkBench(SharedVariables sa) {
		this.sa = sa;
		setLayout(null);
		tabMerchList = new JTable();
		tabMerchList.setEnabled(true);
		JTableModel = new DefaultTableModel();
		JTableModel.addColumn("条码");
		JTableModel.addColumn("昵称");
		JTableModel.addColumn("单价");
		JTableModel.addColumn("操作");	
		tabMerchList.setModel(JTableModel);
		scrollPaneTable  =new JScrollPane();
		scrollPaneTable.setViewportBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		scrollPaneTable.setBounds(80, 114, 406, 241);
		scrollPaneTable.setViewportView(tabMerchList);
		add(scrollPaneTable);
		JLabel lblNewLabel = new JLabel("条形码");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(80, 40, 54, 15);
		add(lblNewLabel);
		
		txtBarCode = new JTextField();
		txtBarCode.setBounds(144, 34, 220, 30);
		add(txtBarCode);
		txtBarCode.setColumns(10);
		
		JButton btnSure = new JButton("确定");
		btnSure.setBounds(387, 33, 99, 30);
		add(btnSure);
		
		label_1 = new JLabel("总价");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setBounds(90, 361, 43, 30);
		add(label_1);
		
		labTotalPrice = new JLabel("0.0");
		labTotalPrice.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		labTotalPrice.setBounds(144, 361, 78, 30);
		add(labTotalPrice);
		
		label_2 = new JLabel("实付");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_2.setBounds(298, 365, 43, 30);
		add(label_2);
		
		label_3 = new JLabel("找零");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_3.setBounds(91, 397, 43, 30);
		add(label_3);
		
		labBackMoney = new JLabel("0.0");
		labBackMoney.setForeground(new Color(50, 205, 50));
		labBackMoney.setFont(new Font("微软雅黑", Font.BOLD, 16));
		labBackMoney.setBounds(144, 397, 78, 30);
		add(labBackMoney);
		
		btnSussDeal = new JButton("完成交易");
		btnSussDeal.setBounds(298, 401, 149, 30);
		add(btnSussDeal);
		
		txtTruePay = new JTextField();
		txtTruePay.setColumns(10);
		txtTruePay.setBounds(337, 365, 106, 30);
		add(txtTruePay);
		
		label_4 = new JLabel("会员卡");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_4.setBounds(80, 80, 54, 15);
		add(label_4);
		
		txtMemberCard = new JTextField();
		txtMemberCard.setColumns(10);
		txtMemberCard.setBounds(144, 74, 220, 30);
		add(txtMemberCard);
		
		btnSearchMerch = new JButton("搜索商品");
		btnSearchMerch.setBounds(387, 74, 99, 30);
		add(btnSearchMerch);
		
		btnSearchMerch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new SearchMerch(JTableModel,labTotalPrice);
			}
		});
		
		btnSussDeal.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Dealing dealing = new Dealing();
				dealing.setUserID(sa.getUserID());
				Sale sale = new Sale();
				Map<String,Integer> map = new HashMap<String,Integer>();
				String card = txtMemberCard.getText().toString().trim();
				for (int i=0; i<JTableModel.getRowCount(); i++){
					map.put((String) JTableModel.getValueAt(i, 0), 0);
				}
				for (int i=0; i<JTableModel.getRowCount(); i++){
					map.put((String) JTableModel.getValueAt(i, 0), map.get(JTableModel.getValueAt(i, 0))+1);
				}
				Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
				int totalNum = 0;
				while(it.hasNext()){
					Map.Entry<String, Integer> entry = it.next();
					MerchInfo merch = DAOFactory.getMerchInfoInstance().searchByBarCode(entry.getKey());
					sale.setBarCode(merch.getBarCode());
					sale.setSaleDate(new Date());
					sale.setSaleNum(entry.getValue());
					totalNum += entry.getValue();
					sale.setSalePrice((float) (merch.getMerchPrice() * entry.getValue()));
					DAOFactory.getSaleInstance().save(sale);
				}
				float moeny = (float) Double.parseDouble(labTotalPrice.getText().toString().trim());
				dealing.setDealingDate(new Date());
				if (!"".equals(card)) {
					DAOFactory.getMemberInstance().updateTotalCost(card, moeny);
				}
				dealing.setDealingPrice(moeny);
				updateTotalPrice(-moeny);
				labBackMoney.setText("0.0");
				txtTruePay.setText("");
				txtMemberCard.setText("");
				DAOFactory.getDealingInstance().save(dealing,totalNum);
				JTableModel.setRowCount(0);
			}
		});
		
		tabMerchList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int y = tabMerchList.getSelectedColumn();
				int x = tabMerchList.getSelectedRow();
				if (y == 3){
					MerchInfo merchInfo = DAOFactory.getMerchInfoInstance().searchByBarCode((String) JTableModel.getValueAt(x, 0));
					updateTotalPrice(-merchInfo.getMerchPrice());
					JTableModel.removeRow(x);
				}
			}
		});
		
		txtTruePay.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				//insertUpdate(e);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				try {
					double truePay = Double.parseDouble(txtTruePay.getText().toString().trim());
					double backMoney = truePay - Double.parseDouble(labTotalPrice.getText().toString().trim());
					if (backMoney > 0){
						labBackMoney.setText(""+backMoney);
					}else {
						labBackMoney.setText("0.0");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "请输入正确的实付金额","错误", JOptionPane.WARNING_MESSAGE); 
				}	
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changedUpdate(e);
			}
		});
			
		btnSure.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String barcode = txtBarCode.getText().toString().trim();
				try {
					MerchInfo merch = DAOFactory.getMerchInfoInstance().searchByBarCode(barcode);
					if (merch != null){
							if (merch.getMerchNum() > 0){
								Vector vc = new Vector();
								vc.addElement(merch.getBarCode());
								vc.addElement(merch.getMerchName());
								vc.addElement(merch.getMerchPrice());
								vc.add("删除");
								updateTotalPrice(merch.getMerchPrice());
								JTableModel.addRow(vc);
							}else{
								JOptionPane.showMessageDialog(null, "商品已经售空","错误", JOptionPane.WARNING_MESSAGE); 
							}
					}else{
						JOptionPane.showMessageDialog(null, "不存在该商品","错误", JOptionPane.WARNING_MESSAGE); 
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "输入条形码格式不正确","错误", JOptionPane.WARNING_MESSAGE); 
				}
				
			}
		});	
	}
	
	//更新总价格
	public void updateTotalPrice(double price) {
		this.TotalPrice =Double.parseDouble(labTotalPrice.getText().toString().trim())+ price;
		labTotalPrice.setText(""+this.TotalPrice);
	}
	
	
}
