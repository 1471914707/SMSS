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

import VO.Member;
import VO.Provide;

import com.smss.linkDB.DAOFactory;

public class MemberMangeWin extends JPanel {

	private JTextField txtContent;
	private static JTable tabMemberList;
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
	public MemberMangeWin() {
		setLayout(null);		
		JLabel lblNewLabel = new JLabel("会员管理");
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
		
		tabMemberList = new JTable();
		tabMemberList.setEnabled(true);
		JTableModel = new DefaultTableModel();
		JTableModel.addColumn("会员ID		");
		JTableModel.addColumn("会员卡号		");
		JTableModel.addColumn("消费金额	");
		JTableModel.addColumn("办理日期		");
		JTableModel.addColumn("操作");
		tabMemberList.setModel(JTableModel);
		tabMemberList.getColumnModel().getColumn(0).setPreferredWidth(120);
		tabMemberList.getColumnModel().getColumn(3).setPreferredWidth(150);
		scrollPaneTable  =new JScrollPane();
		scrollPaneTable.setViewportBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		scrollPaneTable.setBounds(46, 96, 528, 300);
		scrollPaneTable.setViewportView(tabMemberList);
		add(scrollPaneTable);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"卡号"}));
		comboBox.setBounds(46, 65, 98, 27);
		add(comboBox);
		
		JButton btnAddMerch = new JButton("增加会员");

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
		tabMemberList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int y = tabMemberList.getSelectedColumn();
				int x = tabMemberList.getSelectedRow();
				if (y == 4){
					if ("保存".equals((String)JTableModel.getValueAt(x, 4))){
						Member member = new Member();
						member.setMemberCard((String)JTableModel.getValueAt(x, 1));
						member.setTotalCost(Double.parseDouble((String)JTableModel.getValueAt(x, 2)));
						member.setRegDate(new Date());
						int n = JOptionPane.showConfirmDialog(null, "你确定吗?", "保存",JOptionPane.YES_NO_OPTION);
						if (n == 0){
							DAOFactory.getMemberInstance().save(member);
							member = DAOFactory.getMemberInstance().selectLast();
							Vector vc = new Vector();
							vc.addElement(member.getMemberID());
							vc.addElement(member.getMemberCard());
							vc.addElement(member.getTotalCost());
							vc.addElement(member.getRegDate());
							vc.addElement("删除");		
							JTableModel.removeRow(x);
							JTableModel.addRow(vc);							
						}
					}else{
						int n = JOptionPane.showConfirmDialog(null, "你确定吗?", "删除",JOptionPane.YES_NO_OPTION);
						if (n == 0){
							Member member = DAOFactory.getMemberInstance().searchByID((String) JTableModel.getValueAt(x, 0));
							DAOFactory.getMemberInstance().delete(member);
							JTableModel.removeRow(x);
						}
					}
				}
			}
		});
		tabMemberList.addKeyListener(new KeyListener() {
			
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
		tabMemberList.addFocusListener(new FocusListener() {
			
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
				vc.addElement("自动获取");
				vc.addElement("保存");
				JTableModel.addRow(vc);
			}
		});
	}
	private void selectedandUpdate(){
		String val = (String)comboBox.getSelectedItem();
		String val1 = txtContent.getText().toString().trim();
		switch (val) {
		case "卡号":
			updatePageByVal("MemberCard",val1);
			break;
		default:
			updatePage();
			break;
		}
	}
	private void updatePage(){
		if (page <= 0) page=1;
		JTableModel.setRowCount(0);
		List memberList = DAOFactory.getMemberInstance().searchByPage(page, span);
		labCurrentPage.setText("当前第"+page+"页");
		for(int i=0; i<memberList.size(); i++){
			Vector vc = new Vector();
			Member member = (Member) memberList.get(i);	
			vc.addElement(member.getMemberID());
			vc.addElement(member.getMemberCard());
			vc.addElement(member.getTotalCost());
			vc.addElement(member.getRegDate());
			vc.addElement("删除");
			JTableModel.addRow(vc);
		}
	}
	private void updatePageByVal(String val,String val1){
		JTableModel.setRowCount(0);
		labCurrentPage.setText("");
		List memberList= DAOFactory.getMemberInstance().searchByLikeProperty(val,val1);
		for(int i=0; i<memberList.size(); i++){
			Vector vc = new Vector();
			Provide provide = (Provide) memberList.get(i);	
			Member member = (Member) memberList.get(i);	
			vc.addElement(member.getMemberID());
			vc.addElement(member.getMemberCard());
			vc.addElement(member.getTotalCost());
			vc.addElement(member.getRegDate());
			vc.addElement("删除");
			JTableModel.addRow(vc);
		}
	}
	private void updateByListen(){
			int y = tabMemberList.getSelectedColumn();
			int x = tabMemberList.getSelectedRow();
			Member member = DAOFactory.getMemberInstance().searchByID((String) JTableModel.getValueAt(x, 0));
			switch (y) {
			case 1:
				member.setMemberCard((String) JTableModel.getValueAt(x, 1));
				DAOFactory.getMemberInstance().update(member);
				break;
			case 2:
				member.setTotalCost(Double.parseDouble(((String) JTableModel.getValueAt(x, 2))));
				DAOFactory.getMemberInstance().update(member);
				break;
			default:
				break;
			}
		}

}
