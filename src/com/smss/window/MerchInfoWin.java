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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import VO.Dealing;
import VO.MerchInfo;

import com.smss.linkDB.DAOFactory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MerchInfoWin extends JPanel {
	private JTextField txtContent;
	private static JTable tabMerchList;
	private JScrollPane scrollPaneTable;
	private static DefaultTableModel JTableModel;
	private static int span = 25;
	private int page = 1;
	private JTextField textField;
	private JLabel labCurrentPage;
	private JComboBox comboBox;
	/**
	 * Create the panel.
	 */
	public MerchInfoWin() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品详情");
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
		
		tabMerchList = new JTable();
		tabMerchList.setEnabled(true);
		JTableModel = new DefaultTableModel();
		JTableModel.addColumn("商品ID");
		JTableModel.addColumn("名称");
		JTableModel.addColumn("条形码");
		JTableModel.addColumn("单价");
		JTableModel.addColumn("库存");
		JTableModel.addColumn("供应商ID");
		JTableModel.addColumn("操作");
		tabMerchList.setModel(JTableModel);
		tabMerchList.getColumnModel().getColumn(0).setPreferredWidth(120);
		tabMerchList.getColumnModel().getColumn(2).setPreferredWidth(120);
		tabMerchList.getColumnModel().getColumn(5).setPreferredWidth(120);
		scrollPaneTable  =new JScrollPane();
		scrollPaneTable.setViewportBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		scrollPaneTable.setBounds(46, 96, 528, 300);
		scrollPaneTable.setViewportView(tabMerchList);
		add(scrollPaneTable);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"条形码","名称","供应商ID"}));
		comboBox.setBounds(46, 65, 98, 27);
		add(comboBox);
		
		JButton btnAddMerch = new JButton("增加商品");

		btnAddMerch.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnAddMerch.setBounds(468, 406, 106, 33);
		add(btnAddMerch);
		
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
		tabMerchList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int y = tabMerchList.getSelectedColumn();
				int x = tabMerchList.getSelectedRow();
				if (y == 6){
					int n = JOptionPane.showConfirmDialog(null, "你确定吗?", "删除",JOptionPane.YES_NO_OPTION);
					if (n == 0){
						MerchInfo merchInfo = DAOFactory.getMerchInfoInstance().searchByBarCode((String) JTableModel.getValueAt(x, 2));
						DAOFactory.getMerchInfoInstance().delete(merchInfo);
						JTableModel.removeRow(x);
					}
				}else{
					new MerchInfoModify(JTableModel,x);
				}
			}
		});
		btnAddMerch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int x = JTableModel.getRowCount();
				new addMerchWin(JTableModel, x);
			}
		});
	}
	private void selectedandUpdate(){
		String val = (String)comboBox.getSelectedItem();
		String val1 = txtContent.getText().toString().trim();
		switch (val) {
		case "条形码":
			updatePageByVal("BarCode",val1);
			break;
		case "名称":
			updatePageByVal("MerchName",val1);
			break;
		case "供应商ID":
			updatePageByVal("ProvideID",val1);
			break;
		default:
			updatePage();
			break;
		}
	}
	private void updatePage(){
		if (page <= 0) page=1;
		JTableModel.setRowCount(0);
		List merchList = DAOFactory.getMerchInfoInstance().searchByPage(page, span);
		labCurrentPage.setText("当前第"+page+"页");
		for(int i=0; i<merchList.size(); i++){
			Vector vc = new Vector();
			MerchInfo merch = (MerchInfo) merchList.get(i);	
			vc.addElement(merch.getMerchID());
			vc.addElement(merch.getMerchName());
			vc.addElement(merch.getBarCode());
			vc.addElement(merch.getMerchPrice());
			vc.addElement(merch.getMerchNum());
			vc.addElement(merch.getProvideID());
			vc.addElement("删除");
			JTableModel.addRow(vc);
		}
	}
	private void updatePageByVal(String val,String val1){
		JTableModel.setRowCount(0);
		labCurrentPage.setText("");
		List merchList= DAOFactory.getMerchInfoInstance().searchByLikeProperty(val,val1);
		for(int i=0; i<merchList.size(); i++){
			Vector vc = new Vector();
			MerchInfo merch = (MerchInfo) merchList.get(i);	
			vc.addElement(merch.getMerchID());
			vc.addElement(merch.getMerchName());
			vc.addElement(merch.getBarCode());
			vc.addElement(merch.getMerchPrice());
			vc.addElement(merch.getMerchNum());
			vc.addElement(merch.getProvideID());
			vc.addElement("删除");
			JTableModel.addRow(vc);
		}
	}
}
