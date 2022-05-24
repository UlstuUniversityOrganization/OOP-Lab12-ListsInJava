import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;

public class Source {

	private JFrame frame;
	private LinkedList<String> list;
	private JTextField textField_AddElementID;
	private JTextField textField_inputStr;
	private JTextField textField_ShowElementID;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Source window = new Source();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Source() {
		initialize();
	}
	
	public String getListElementsString(List<String> list, boolean reversed)
	{
		String outputStr = "";
		
		if(reversed)
		{
			for(int i = list.size() - 1; i >= 0; i--) {
				outputStr = outputStr + list.get(i);
				if(i != 0)
					outputStr = outputStr + ", ";
			}	
		}
		else
		{
			for(int i = 0; i < list.size(); i++) {
				outputStr = outputStr + list.get(i);
				if(i != list.size() - 1)
					outputStr = outputStr + ", ";
			}		
		}
		return outputStr;
	}
	

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 820, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 41, 570, 425);
		textArea.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(textArea);
		
		list = new LinkedList<String>();
		
		JCheckBox chckbxReversed = new JCheckBox("Reversed");
		chckbxReversed.setBackground(Color.WHITE);
		chckbxReversed.setBounds(681, 93, 97, 23);
		frame.getContentPane().add(chckbxReversed);
		
		JButton btnShowList = new JButton("Show list");
		btnShowList.setBackground(Color.WHITE);
		btnShowList.setIcon(null);
		btnShowList.setBounds(586, 93, 89, 23);
		btnShowList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				
				String outputStr = "";
						
				outputStr = getListElementsString(list, chckbxReversed.isSelected());
				
				textArea.setText(outputStr);
			}			
		});
		frame.getContentPane().add(btnShowList);
		
		
		
		JButton btnAddHead = new JButton("Add head");
		btnAddHead.setBackground(Color.WHITE);
		btnAddHead.setBounds(647, 213, 89, 23);
		frame.getContentPane().add(btnAddHead);
		
		btnAddHead.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String outputStr = "";
				
				list.add(textField_inputStr.getText());
				
				outputStr = getListElementsString(list, chckbxReversed.isSelected());
				textArea.setText(outputStr);
			}
			
		});
		
		JButton btnAddTail = new JButton("Add tail");
		btnAddTail.setBackground(Color.WHITE);
		btnAddTail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String outputStr = "";
				
				list.addFirst(textField_inputStr.getText());
				
				outputStr = getListElementsString(list, chckbxReversed.isSelected());
				textArea.setText(outputStr);
			}
		});
		btnAddTail.setBounds(647, 247, 89, 23);
		frame.getContentPane().add(btnAddTail);
		
		textField_AddElementID = new JTextField();
		textField_AddElementID.setBackground(Color.WHITE);
		textField_AddElementID.setBounds(612, 296, 86, 20);
		frame.getContentPane().add(textField_AddElementID);
		textField_AddElementID.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBackground(Color.WHITE);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				String outputStr = "";
										
				list.add(Integer.parseInt(textField_AddElementID.getText()), textField_inputStr.getText());
				
				outputStr = getListElementsString(list, chckbxReversed.isSelected());
				textArea.setText(outputStr);
			}
		});
		btnInsert.setBounds(705, 295, 89, 23);
		frame.getContentPane().add(btnInsert);
		

		
		textField_inputStr = new JTextField();
		textField_inputStr.setBackground(Color.WHITE);
		textField_inputStr.setBounds(650, 182, 86, 20);
		frame.getContentPane().add(textField_inputStr);
		textField_inputStr.setColumns(10);
		
		textField_ShowElementID = new JTextField();
		textField_ShowElementID.setBounds(650, 365, 86, 20);
		frame.getContentPane().add(textField_ShowElementID);
		textField_ShowElementID.setColumns(10);
		
		JButton btnShowElement = new JButton("Show element");
		btnShowElement.setBackground(Color.WHITE);
		btnShowElement.setBounds(632, 396, 124, 23);
		frame.getContentPane().add(btnShowElement);
		
		btnShowElement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String idStr = textField_ShowElementID.getText();
				int id = 0;
				if(idStr != null && idStr.matches("-?\\d+(\\.\\d+)?"))
					id = Integer.parseInt(textField_ShowElementID.getText());
				idStr = String.valueOf(id);
				textField_ShowElementID.setText(idStr);
				textArea.setText(list.get(id));
			}
		});
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setBackground(Color.WHITE);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idStr = textField_ShowElementID.getText();
				int id = list.size();
				
				if(idStr != null && idStr.matches("-?\\d+(\\.\\d+)?"))
					id = Integer.parseInt(idStr);
				
				id--;
				if(id < 0)
					id = 0;
				
				idStr = String.valueOf(id);
				textField_ShowElementID.setText(idStr);
				textArea.setText(list.get(id));
			}
		});
		btnPrevious.setBounds(590, 430, 89, 23);
		frame.getContentPane().add(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBackground(Color.WHITE);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idStr = textField_ShowElementID.getText();
				int id = -1;
				
				if(idStr != null && idStr.matches("-?\\d+(\\.\\d+)?"))
					id = Integer.parseInt(idStr);
				
				id++;
				if(id >= list.size())
					id = list.size() - 1;
				
				idStr = String.valueOf(id);
				textField_ShowElementID.setText(idStr);
				textArea.setText(list.get(id));
			}
		});
		btnNext.setBounds(705, 430, 89, 23);
		frame.getContentPane().add(btnNext);
		
		JLabel lblElementId_2 = new JLabel("Element id");
		lblElementId_2.setBounds(590, 368, 67, 14);
		frame.getContentPane().add(lblElementId_2);
		
		JLabel lblShowElements = new JLabel("Show element");
		lblShowElements.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblShowElements.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowElements.setBounds(579, 327, 224, 30);
		frame.getContentPane().add(lblShowElements);
		
		JLabel lblAddingElements = new JLabel("Adding elements");
		lblAddingElements.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddingElements.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddingElements.setBounds(579, 141, 224, 30);
		frame.getContentPane().add(lblAddingElements);
		
		JLabel lblInput = new JLabel("Input");
		lblInput.setBounds(612, 185, 67, 14);
		frame.getContentPane().add(lblInput);
		
		JLabel lblElementId_1 = new JLabel("Element id");
		lblElementId_1.setBounds(625, 276, 67, 14);
		frame.getContentPane().add(lblElementId_1);
		
		JLabel lblShowList = new JLabel("Show list");
		lblShowList.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblShowList.setBounds(579, 41, 224, 30);
		frame.getContentPane().add(lblShowList);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(100, 269, 1, 1);
		frame.getContentPane().add(desktopPane);
		

	}
}
