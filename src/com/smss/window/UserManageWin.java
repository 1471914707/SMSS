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
import java.util.Date;
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

import VO.User;
import VO.Provide;

import com.smss.linkDB.DAOFactory;

public class UserManageWin extends JPanel {

	private JTextField txtContent;
	private static JTable tabUserList;
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
	public UserManageWin() {
		setLayout(null);		
		JLabel lblNewLabel = new JLabel("用户管理");
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
		
		tabUserList = new JTable();
		tabUserList.setEnabled(true);
		JTableModel = new DefaultTableModel();
		JTableModel.addColumn("用户ID");
		JTableModel.addColumn("用户账号");
		JTableModel.addColumn("用户密码");
		JTableModel.addColumn("是否为员工");
		JTableModel.addColumn("操作");
		tabUserList.setModel(JTableModel);
		tabUserList.getColumnModel().getColumn(0).setPreferredWidth(120);
		tabUserList.getColumnModel().getColumn(3).setPreferredWidth(150);
		scrollPaneTable  =new JScrollPane();
		scrollPaneTable.setViewportBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		scrollPaneTable.setBounds(46, 96, 528, 300);
		scrollPaneTable.setViewportView(tabUserList);
		add(scrollPaneTable);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"昵称","老板","员工"}));
		comboBox.setBounds(46, 65, 98, 27);
		add(comboBox);
		
		JButton btnAddMerch = new JButton("增加用户");

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
		tabUserList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int y = tabUserList.getSelectedColumn();
				int x = tabUserList.getSelectedRow();
				if (y == 3){
					if ("保存".equals((String)JTableModel.getValueAt(x, 3))){
						User User = new User();
						User.setUserName((String)JTableModel.getValueAt(x, 1));
						User.setUserPW((String)JTableModel.getValueAt(x, 2));
						if ("是".equals((String)JTableModel.getValueAt(x, 3))){
							User.setUserType(0);
						}else{
							User.setUserType(1);
						}
						int n = JOptionPane.showConfirmDialog(null, "你确定吗?", "保存",JOptionPane.YES_NO_OPTION);
						if (n == 0){
							DAOFactory.getUserInstance().save(User);
							User = DAOFactory.getUserInstance().selectLast();
							Vector vc = new Vector();
							vc.addElement(User.getUserID());
							vc.addElement(User.getUserName());
							vc.addElement(User.getUserPW());
							if(User.getUserType()== 0){
								vc.add("员工");
							}else{
								vc.add("老板");
							}
							
							vc.addElement("删除");		
							JTableModel.removeRow(x);
							JTableModel.addRow(vc);							
						}
					}else{
						int n = JOptionPane.showConfirmDialog(null, "你确定吗?", "删除",JOptionPane.YES_NO_OPTION);
						if (n == 0){
							User User = DAOFactory.getUserInstance().searchByID((String) JTableModel.getValueAt(x, 0));
							DAOFactory.getUserInstance().delete(User);
							JTableModel.removeRow(x);
						}
					}
				}
			}
		});
		tabUserList.addKeyListener(new KeyListener() {
			
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
		tabUserList.addFocusListener(new FocusListener() {
			
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
				vc.addElement("保存");
				JTableModel.addRow(vc);
			}
		});
	}
	private void selectedandUpdate(){
		String val = (String)comboBox.getSelectedItem();
		String val1 = txtContent.getText().toString().trim();
		switch (val) {
		case "昵称":
			updatePageByVal("UserName",val1);
			break;
		case "老板":
			updatePageByVal("UserType","1");
			break;
		case "员工":
			updatePageByVal("UserType","0");
			break;
		default:
			updatePage();
			break;
		}
	}
	private void updatePage(){
		if (page <= 0) page=1;
		JTableModel.setRowCount(0);
		List UserList = DAOFactory.getUserInstance().searchByPage(page, span);
		labCurrentPage.setText("当前第"+page+"页");
		for(int i=0; i<UserList.size(); i++){
			Vector vc = new Vector();
			User User = (User) UserList.get(i);	
			vc.addElement(User.getUserID());
			vc.addElement(User.getUserName());
			vc.addElement(User.getUserPW());
			if(User.getUserType()== 0){
				vc.add("员工");
			}else{
				vc.add("老板");
			}
			
			vc.addElement("删除");	
			vc.addElement("删除");
			JTableModel.addRow(vc);
		}
	}
	private void updatePageByVal(String val,String val1){
		JTableModel.setRowCount(0);
		labCurrentPage.setText("");
		List UserList= DAOFactory.getUserInstance().searchByLikeProperty(val,val1);
		for(int i=0; i<UserList.size(); i++){
			Vector vc = new Vector();
			User User = (User) UserList.get(i);	
			vc.addElement(User.getUserID());
			vc.addElement(User.getUserName());
			vc.addElement(User.getUserPW());
			if(User.getUserType()== 0){
				vc.add("员工");
			}else{
				vc.add("老板");
			}
			
			vc.addElement("删除");	
			vc.addElement("删除");
			JTableModel.addRow(vc);
		}
	}
	private void updateByListen(){
			int y = tabUserList.getSelectedColumn();
			int x = tabUserList.getSelectedRow();
			User User = DAOFactory.getUserInstance().searchByID((String) JTableModel.getValueAt(x, 0));
			switch (y) {
			case 1:
				User.setUserName((String) JTableModel.getValueAt(x, 1));
				DAOFactory.getUserInstance().update(User);
				break;
			case 2:
				User.setUserPW(((String) JTableModel.getValueAt(x, 2)));
				DAOFactory.getUserInstance().update(User);
				break;
			default:
				break;
			}
		}


}
