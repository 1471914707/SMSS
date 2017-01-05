package com.smss.window;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import VO.MerchInfo;

import com.smss.linkDB.DAOFactory;
import com.sun.istack.internal.FinalArrayList;

public class SearchMerch {

	private JFrame frame;
	private JTextField txtContent;
	private static JTable tabMerchList;
	private JScrollPane scrollPaneTable;
	private static DefaultTableModel JTableModel;
	/**
	 * Create the application.
	 */
	public SearchMerch(DefaultTableModel JTableModelMain,JLabel labTotalPrice) {
		frame = new JFrame();
		frame.setBounds(100, 100, 369, 323);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		txtContent = new JTextField();
		txtContent.setBounds(20, 10, 200, 33);
		frame.getContentPane().add(txtContent);
		txtContent.setColumns(10);
		
		JButton btnSearch = new JButton("搜索");
		btnSearch.setBounds(243, 12, 93, 28);
		frame.getContentPane().add(btnSearch);
		
		tabMerchList = new JTable();
		tabMerchList.setEnabled(true);
		JTableModel = new DefaultTableModel();
		JTableModel.addColumn("条码");
		JTableModel.addColumn("名称");
		JTableModel.addColumn("数量");
		JTableModel.addColumn("单价");
		tabMerchList.setModel(JTableModel);
		scrollPaneTable  =new JScrollPane();
		scrollPaneTable.setViewportBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		scrollPaneTable.setBounds(20, 53, 316, 221);
		scrollPaneTable.setViewportView(tabMerchList);
		frame.add(scrollPaneTable);
		
		tabMerchList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				MerchInfo merch1 = new MerchInfo();
				int x =tabMerchList.getSelectedRow();
				merch1 = DAOFactory.getMerchInfoInstance().searchByBarCode((String) JTableModel.getValueAt(x, 0));
				Vector vc = new Vector();
				vc.addElement(merch1.getBarCode());
				vc.addElement(merch1.getMerchName());
				vc.addElement(merch1.getMerchPrice());
				vc.add("删除");
				Double priceDouble = Double.parseDouble(labTotalPrice.getText().toString().trim());
				labTotalPrice.setText(""+(priceDouble+merch1.getMerchPrice()));
				JTableModelMain.addRow(vc);
			}
		});
/*		
		txtContent.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				UpdateSearchResult();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				insertUpdate(e);
			}
		});*/
		
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				UpdateSearchResult();
			}
		});
	}
	private void UpdateSearchResult(){
		JTableModel.setRowCount(0);
		List listbyName= DAOFactory.getMerchInfoInstance().searchByLikeProperty("MerchName", txtContent.getText().toString().trim());
		List listbyBarCode = DAOFactory.getMerchInfoInstance().searchByLikeProperty("BarCode", txtContent.getText().toString().trim());
		for (int i=0; i<listbyName.size(); i++){
			MerchInfo merch = (MerchInfo) listbyName.get(i);
			Vector vc = new Vector();
			vc.addElement(merch.getBarCode());
			vc.addElement(merch.getMerchName());
			vc.addElement(merch.getMerchNum());
			vc.addElement(merch.getMerchPrice());
			JTableModel.addRow(vc);
		}
		for (int i=0; i<listbyBarCode.size(); i++){
			MerchInfo merch = (MerchInfo) listbyBarCode.get(i);
			Vector vc = new Vector();
			vc.addElement(merch.getBarCode());
			vc.addElement(merch.getMerchName());
			vc.addElement(merch.getMerchNum());
			vc.addElement(merch.getMerchPrice());
			JTableModel.addRow(vc);
		}
	}
	
}
