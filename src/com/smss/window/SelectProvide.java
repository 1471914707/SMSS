package com.smss.window;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import VO.MerchInfo;
import VO.Provide;

import com.smss.linkDB.DAOFactory;

public class SelectProvide {

	private JFrame frame;
	private static JTable tabProvideList;
	private JScrollPane scrollPaneTable;
	private static DefaultTableModel JTableModel;
	private JTextField txtContent;
	private JButton btnSearch;

	public SelectProvide(JLabel LabID) {
		frame = new JFrame();
		frame.setBounds(100, 100, 423, 388);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		tabProvideList = new JTable();
		tabProvideList.setEnabled(true);
		JTableModel = new DefaultTableModel();
		JTableModel.addColumn("厂商ID");
		JTableModel.addColumn("厂商名称");
		JTableModel.addColumn("厂商地址");
		JTableModel.addColumn("厂商电话");
		frame.getContentPane().setLayout(null);
		tabProvideList.setModel(JTableModel);
		scrollPaneTable  =new JScrollPane();
		scrollPaneTable.setViewportBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		scrollPaneTable.setBounds(0, 56, 407,293);
		scrollPaneTable.setViewportView(tabProvideList);
		frame.getContentPane().add(scrollPaneTable);
		
		txtContent = new JTextField();
		txtContent.setBounds(10, 10, 200, 35);
		frame.getContentPane().add(txtContent);
		txtContent.setColumns(10);
		
		btnSearch = new JButton("搜索");
		btnSearch.setBounds(241, 10, 93, 36);
		frame.getContentPane().add(btnSearch);
		
/*			if (!"".equals(LabID.getText().toString())){
				Provide provide = DAOFactory.getProvideInstance().searchByID(LabID.getText().toString());
				Vector vc = new Vector();
				vc.addElement(provide.getProvideID());
				vc.addElement(provide.getProvideName());
				vc.addElement(provide.getProvideAddress());
				vc.addElement(provide.getProvidePhone());
				JTableModel.addRow(vc);
			}		*/
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				UpdateSearchResult();
			}
		});
		
		tabProvideList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int x = tabProvideList.getSelectedRow();
				String provideIdString = (String) tabProvideList.getValueAt(x, 0);
				LabID.setText(provideIdString);
				frame.dispose();
			}
		});
		
		frame.setVisible(true);		
	}
	private void UpdateSearchResult(){
		JTableModel.setRowCount(0);
		List listbyName= DAOFactory.getProvideInstance().searchByLikeProperty("ProvideName", txtContent.getText().toString().trim());
		for (int i=0; i<listbyName.size(); i++){
			Provide provide = new Provide();
			provide = (Provide) listbyName.get(i);
			Vector vc = new Vector();
			vc.addElement(provide.getProvideID());
			vc.addElement(provide.getProvideName());
			vc.addElement(provide.getProvideAddress());
			vc.addElement(provide.getProvidePhone());
			JTableModel.addRow(vc);
		}
	}
}
