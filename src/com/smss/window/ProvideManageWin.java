package com.smss.window;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import VO.Provide;

import com.smss.linkDB.DAOFactory;

public class ProvideManageWin extends JPanel {
	private JTextField txtContent;
	private static JTable tabProvideList;
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
	public ProvideManageWin() {
setLayout(null);
		
		JLabel lblNewLabel = new JLabel("供应商管理");
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
		
		tabProvideList = new JTable();
		tabProvideList.setEnabled(true);
		JTableModel = new DefaultTableModel();
		JTableModel.addColumn("供应商ID");
		JTableModel.addColumn("名称");
		JTableModel.addColumn("地址");
		JTableModel.addColumn("电话");
		JTableModel.addColumn("操作");
		tabProvideList.setModel(JTableModel);
		tabProvideList.getColumnModel().getColumn(0).setPreferredWidth(120);
		tabProvideList.getColumnModel().getColumn(1).setPreferredWidth(150);
		scrollPaneTable  =new JScrollPane();
		scrollPaneTable.setViewportBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		scrollPaneTable.setBounds(46, 96, 528, 300);
		scrollPaneTable.setViewportView(tabProvideList);
		add(scrollPaneTable);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"供应商","供应商ID","电话","地址"}));
		comboBox.setBounds(46, 65, 98, 27);
		add(comboBox);
		
		JButton btnAddMerch = new JButton("增加商家");

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
		tabProvideList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int y = tabProvideList.getSelectedColumn();
				int x = tabProvideList.getSelectedRow();
				if (y == 4){
					if ("保存".equals((String)JTableModel.getValueAt(x, 4))){
						Provide provide = new Provide();
						provide.setProvideName((String)JTableModel.getValueAt(x, 1));
						provide.setProvideAddress((String)JTableModel.getValueAt(x, 2));
						provide.setProvidePhone((String)JTableModel.getValueAt(x, 3));
						int n = JOptionPane.showConfirmDialog(null, "你确定吗?", "保存",JOptionPane.YES_NO_OPTION);
						if (n == 0){
							DAOFactory.getProvideInstance().save(provide);
							provide = DAOFactory.getProvideInstance().selectLast();
							Vector vc = new Vector();
							vc.addElement(provide.getProvideID());
							vc.addElement(provide.getProvideName());
							vc.addElement(provide.getProvideAddress());
							vc.addElement(provide.getProvidePhone());
							vc.addElement("删除");		
							JTableModel.removeRow(x);
							JTableModel.addRow(vc);							
						}
					}else{
						int n = JOptionPane.showConfirmDialog(null, "你确定吗?", "删除",JOptionPane.YES_NO_OPTION);
						if (n == 0){
							Provide provide = DAOFactory.getProvideInstance().searchByID((String) JTableModel.getValueAt(x, 0));
							DAOFactory.getProvideInstance().delete(provide);
							JTableModel.removeRow(x);
						}
					}
				}
			}
		});
		tabProvideList.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					updateByListen();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		tabProvideList.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				/*updateByListen();*/
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnAddMerch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Vector vc = new Vector();
				vc.addElement("自动生成");
				vc.addElement("请填写");
				vc.addElement("请填写");
				vc.addElement("请填写");
				vc.addElement("保存");
				JTableModel.addRow(vc);
/*				DAOFactory.getProvideInstance().save(provide);
				provide = DAOFactory.getProvideInstance().selectLast();*/
			}
		});
	}
	private void selectedandUpdate(){
		String val = (String)comboBox.getSelectedItem();
		String val1 = txtContent.getText().toString().trim();
		switch (val) {
		case "供应商":
			updatePageByVal("ProvideName",val1);
			break;
		case "供应商ID":
			updatePageByVal("ProvideID",val1);
			break;
		case "电话":
			updatePageByVal("ProvidePhone",val1);
			break;
		case "地址":
			updatePageByVal("ProvideAddress",val1);
			break;
		default:
			updatePage();
			break;
		}
	}
	private void updatePage(){
		if (page <= 0) page=1;
		JTableModel.setRowCount(0);
		List merchList = DAOFactory.getProvideInstance().searchByPage(page, span);
		labCurrentPage.setText("当前第"+page+"页");
		for(int i=0; i<merchList.size(); i++){
			Vector vc = new Vector();
			Provide provide = (Provide) merchList.get(i);	
			vc.addElement(provide.getProvideID());
			vc.addElement(provide.getProvideName());
			vc.addElement(provide.getProvideAddress());
			vc.addElement(provide.getProvidePhone());
			vc.addElement("删除");
			JTableModel.addRow(vc);
		}
	}
	private void updatePageByVal(String val,String val1){
		JTableModel.setRowCount(0);
		labCurrentPage.setText("");
		List merchList= DAOFactory.getProvideInstance().searchByLikeProperty(val,val1);
		for(int i=0; i<merchList.size(); i++){
			Vector vc = new Vector();
			Provide provide = (Provide) merchList.get(i);	
			vc.addElement(provide.getProvideID());
			vc.addElement(provide.getProvideName());
			vc.addElement(provide.getProvideAddress());
			vc.addElement(provide.getProvidePhone());
			vc.addElement("删除");
			JTableModel.addRow(vc);
		}
	}
	private void updateByListen(){
			int y = tabProvideList.getSelectedColumn();
			int x = tabProvideList.getSelectedRow();
			Provide provide = DAOFactory.getProvideInstance().searchByID((String) JTableModel.getValueAt(x, 0));
			switch (y) {
			case 1:
				provide.setProvideName((String) JTableModel.getValueAt(x, 1));
				DAOFactory.getProvideInstance().update(provide);
				break;
			case 2:
				provide.setProvideAddress((String) JTableModel.getValueAt(x, 2));
				DAOFactory.getProvideInstance().update(provide);
				break;
			case 3:
				provide.setProvidePhone((String) JTableModel.getValueAt(x, 3));
				DAOFactory.getProvideInstance().update(provide);
				break;
			default:
				break;
			}
		}
}
