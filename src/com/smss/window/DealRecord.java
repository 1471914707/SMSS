package com.smss.window;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import VO.Dealing;
import VO.MerchInfo;

import com.smss.linkDB.DAOFactory;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class DealRecord extends JPanel {
	private JTextField txtContent;
	private static JTable tabDealingList;
	private JScrollPane scrollPaneTable;
	private static DefaultTableModel JTableModel;
	private static int span = 25;
	private int page = 1;
	private JTextField textField;
	private JLabel labCurrentPage;
	private int TotalPage = (int) DAOFactory.getCountInfostance().getCountByTag("DealCount");
	private JComboBox comboBox;
	/**
	 * Create the panel.
	 */
	public DealRecord() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("交易单量详情");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
		lblNewLabel.setBounds(221, 10, 200, 50);
		add(lblNewLabel);
		
		JButton btnBackOne = new JButton("<<");
		btnBackOne.setBounds(46, 414, 61, 23);
		add(btnBackOne);
		
		JButton btnGoOne = new JButton(">>");
		btnGoOne.setBounds(206, 414, 61, 23);
		add(btnGoOne);
		
		labCurrentPage = new JLabel("当前第0页");
		labCurrentPage.setBounds(117, 409, 79, 33);
		add(labCurrentPage);
		
		JButton btnShowAll = new JButton("显示全部");
		btnShowAll.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnShowAll.setBounds(468, 62, 106, 33);
		add(btnShowAll);
		
		txtContent = new JTextField();
		txtContent.setBounds(154, 62, 188, 33);
		add(txtContent);
		txtContent.setColumns(10);
		
		JButton btnSearch = new JButton("搜索");
		btnSearch.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnSearch.setBounds(352, 62, 106, 33);
		add(btnSearch);
		
		tabDealingList = new JTable();
		tabDealingList.setEnabled(true);
		JTableModel = new DefaultTableModel();
		JTableModel.addColumn("交易ID");
		JTableModel.addColumn("交易金额");
		JTableModel.addColumn("交易日期");
		JTableModel.addColumn("会员ID");
		JTableModel.addColumn("店员ID");
		JTableModel.addColumn("操作");
		tabDealingList.setModel(JTableModel);
		scrollPaneTable  =new JScrollPane();
		scrollPaneTable.setViewportBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		scrollPaneTable.setBounds(46, 96, 528, 300);
		scrollPaneTable.setViewportView(tabDealingList);
		add(scrollPaneTable);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"时间", "用户ID", "会员ID", "交易ID"}));
		comboBox.setBounds(46, 65, 98, 27);
		add(comboBox);
		
		updatePage();
		
		btnBackOne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				page--;
				updatePage();
			}
		});
		btnGoOne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				page++;
				updatePage();
			}
		});
		btnShowAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				page = 1;
				updatePage();
			}
		});
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String content = txtContent.getText().toLowerCase().trim();
				if (!"".equals(content)){
					selectedandUpdate();
				}
			}
		});
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				switch (e.getStateChange()) {
				case ItemEvent.SELECTED:
					selectedandUpdate();
					break;
				default:
					break;
				}
			}
		});
		tabDealingList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int y = tabDealingList.getSelectedColumn();
				int x = tabDealingList.getSelectedRow();
				if (y == 5){
					Dealing dealing = DAOFactory.getDealingInstance().searchByID((String) JTableModel.getValueAt(x, 0));
					DAOFactory.getDealingInstance().delete(dealing);
					JTableModel.removeRow(x);
				}
			}
		});
		tabDealingList.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				int y = tabDealingList.getSelectedColumn();
				int x = tabDealingList.getSelectedRow();
				if (y == 1){
					Dealing dealing = DAOFactory.getDealingInstance().searchByID((String) JTableModel.getValueAt(x, 0));
					dealing.setDealingPrice((float) Double.parseDouble((String) JTableModel.getValueAt(x, 1)));
					DAOFactory.getDealingInstance().update(dealing);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	private void selectedandUpdate(){
		String val = (String)comboBox.getSelectedItem();
		String val1 = txtContent.getText().toString().trim();
		switch (val) {
		case "时间":
			updatePageByVal("DealingDate",val1);
			break;
		case "用户ID":
			updatePageByVal("UserID",val1);
			break;
		case "会员ID":
			updatePageByVal("MemberID",val1);
			break;
		case "交易ID":
			updatePageByVal("DealingID",val1);
			break;
		default:
			updatePage();
			break;
		}
	}
	private void updatePage(){
		if (page <= 0) page=1;
		if(page > TotalPage) page = TotalPage;
		JTableModel.setRowCount(0);
		List dealList = DAOFactory.getDealingInstance().searchByPage(page, span);
		labCurrentPage.setText("当前第"+page+"页");
		for(int i=0; i<dealList.size(); i++){
			Vector vc = new Vector();
			Dealing dealing = (Dealing) dealList.get(i);					
			vc.addElement(dealing.getDealingID());
			vc.addElement(dealing.getDealingPrice());
			vc.addElement(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dealing.getDealingDate()));
			vc.addElement(dealing.getMemberID());
			vc.addElement(dealing.getUserID());
			vc.addElement("删除");
			JTableModel.addRow(vc);
		}
	}
	private void updatePageByVal(String val,String val1){
		JTableModel.setRowCount(0);
		labCurrentPage.setText("");
		List dealList = DAOFactory.getDealingInstance().searchByLikeProperty(val,val1);
		for(int i=0; i<dealList.size(); i++){
			Vector vc = new Vector();
			Dealing dealing = (Dealing) dealList.get(i);					
			vc.addElement(dealing.getDealingID());
			vc.addElement(dealing.getDealingPrice());
			vc.addElement(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dealing.getDealingDate()));
			vc.addElement(dealing.getMemberID());
			vc.addElement(dealing.getUserID());
			vc.addElement("删除");
			JTableModel.addRow(vc);
		}
	}
}
