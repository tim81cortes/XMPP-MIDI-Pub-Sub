package com.pdl.cpychatclient2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;



public class Form extends JPanel{
	private JTextArea[] fields;
	private String displayMessageRecieved;
	private String displayMessageSent;
	
	public Form(String[] labels, int[] widths, int[] heights)
	{
		super(new BorderLayout());
		Color bgColor = new Color(109,8,57);
		GridLayout vertDivide = new GridLayout(2,0);
		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBackground(bgColor);
		JPanel northInner = new JPanel(new BorderLayout());
		JPanel eastWestInner = new JPanel(new BorderLayout());
		eastWestInner.setBackground(bgColor);
		JPanel xChatPane = new JPanel(new GridLayout(1, 1));
		JPanel sendMsgPane = new JPanel(new GridLayout(1, 1));
		JPanel xChtPeoplePane = new JPanel(new GridLayout(1, 1));
		
		JPanel subPane[] = new JPanel[labels.length];
		
		add(outerPanel, BorderLayout.NORTH);
		outerPanel.add(northInner, BorderLayout.NORTH);
		northInner.add(sendMsgPane, BorderLayout.SOUTH);
		northInner.add(eastWestInner, BorderLayout.NORTH);
		eastWestInner.add(xChatPane, BorderLayout.CENTER);
		eastWestInner.add(xChtPeoplePane, BorderLayout.EAST);
		fields = new JTextArea[labels.length];
		
		System.out.println("labels length " + labels.length);
		for (int i = 0;  i < labels.length; i ++)
		{		    
			subPane[i] = new JPanel();

			fields[i] = new JTextArea();
			if (i < widths.length)
			{
				fields[i].setColumns(widths[i]);
			}
			if (i < heights.length)
			{
				fields[i].setRows(heights[i]);
			}
			JLabel jLabel = new JLabel(labels[i], JLabel.RIGHT);
			jLabel.setLabelFor(fields[i]);
		    subPane[i].add(fields[i]);
		}
		xChatPane.add(subPane[0]);
		sendMsgPane.add(subPane[1]);
		xChtPeoplePane.add(subPane[2]);
		Color bgColor0 = new Color(208,231,153);
		Color bgColor1 = new Color(216,195,88);
		Color bgColor2 = new Color(251,239,244);

 		fields[0].setEditable(false);
		fields[0].setBackground(bgColor0);
		fields[1].setBackground(bgColor1);
		fields[2].setBackground(bgColor2);
		fields[2].setEditable(false);
	}	
	public void cursorToStart(){
		fields[1].setText("");
	}
	
	
	 public JTextArea getField(int i) {
		 	
		    return fields[i];
		  }

	 public String getText(int i) {
		 	
		    return (fields[i].getText());
		  }
	 
	 public void setDisplayMsg(String dspMsg, boolean outBound){
		 if (outBound == true)
		 {
			 displayMessageSent = dspMsg;
		 }
		 else
		 {
			 displayMessageRecieved = dspMsg;
		 }
		 
	 }
	 public void addDisplayMsg(boolean outBound){
		 if (outBound == true)
		 {
			 if (fields[0]!=null && displayMessageSent != null)
			 {
				 System.out.println("Displaying text");
				 fields[0].append(displayMessageSent+"\n");
			 }
			 else
			 {
				 System.out.println("Nothing to display");
			 }
		 }
		else
		{
			 if (fields[0]!=null && displayMessageRecieved != null)
			 {
				 System.out.println("Displaying text");
				 fields[0].append("\t" + displayMessageRecieved+"\n");
			 }
			 else
			 {
				 System.out.println("Nothing to display");
			 }
		}
		
	 }
	
}
