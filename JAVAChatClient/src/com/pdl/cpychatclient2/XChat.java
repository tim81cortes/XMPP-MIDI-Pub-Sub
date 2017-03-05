package com.pdl.cpychatclient2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class XChat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] labels = {"XChats", "New XChat", "XChatters"};
		int[] widths = {50, 50, 25};
		int[] heights = {30, 5, 25};
		
		final Form chatWin = new Form(labels, widths, heights);
		JButton sendBut = new JButton("Send xChat");
		JButton recieveBut = new JButton("Clear Conversation");
		JTextArea fld;
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		
		XMPPHandler xmpHandle = new XMPPHandler("192.168.1.91", 5222);
		String username = "sparkusr";
		String password = "password";
		ChatManager chtMan = null;
		try {
			xmpHandle.initLogin(username, password);
			chtMan = xmpHandle.getChatHndlr();
		} catch (XMPPException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			Chat cht = chtMan.createChat("sparkusr2@yeahsowhat.local", new MessageListener(){
				public void processMessage(Chat chat, Message message){
					String tmpUsrnm;
					String tmpBod = message.getBody();
					String tmpFrm = message.getFrom();
					int fromAtIndx = tmpFrm.indexOf("@");

					tmpUsrnm = tmpFrm.substring(0, fromAtIndx);
					String time = sdf.format(cal.getTime()).toString();
					System.out.println("Processing message");
					
					if (tmpBod != null){
						chatWin.setDisplayMsg("(" + time + ") " +  tmpUsrnm + ": " + tmpBod, false);
						chatWin.addDisplayMsg(false);
					}
					
				}
			});
			
		fld = chatWin.getField(1);
		// Button action listener for sending which works fine
		sendBut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String time = sdf.format(cal.getTime()).toString();
				String inpStr = fld.getText();
				chatWin.cursorToStart();
				String tmpStr = inpStr.replaceAll("\n", "");
				
				fld.setText(tmpStr);
				chatWin.cursorToStart();
			   	try {
			   		cht.sendMessage(tmpStr);
			   		fld.setText("");
			   		
		    	} catch (XMPPException e2) {
		    		e2.printStackTrace();
		    	} 
			   	try {
					xmpHandle.printRoster();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			   	chatWin.setDisplayMsg("(" + time + ") " +  username + ": " + tmpStr, true);
				chatWin.addDisplayMsg(true);
			}
		});	

		fld.addKeyListener(new KeyListener(){
			@Override
			public void keyReleased(KeyEvent event){
				int ch = event.getKeyCode();
				if (ch == KeyEvent.VK_ENTER){
					String time = sdf.format(cal.getTime()).toString();
					String inpStr = fld.getText();
					chatWin.cursorToStart();
					String tmpStr = inpStr.replaceAll("\n", "");
					
					fld.setText(tmpStr);
					chatWin.cursorToStart();
				   	try {
				   		cht.sendMessage(tmpStr);
				   		fld.setText("");
				   		
			    	} catch (XMPPException e2) {
			    		e2.printStackTrace();
			    	} 
					try {
						xmpHandle.printRoster();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				   	chatWin.setDisplayMsg("(" + time + ") " +  username + ": " + tmpStr, true);
					chatWin.addDisplayMsg(true);
				}	
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		

		JFrame window = new JFrame("XChat");
		window.getContentPane().add(chatWin, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		chatWin.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(sendBut);
		buttonPanel.add(recieveBut);
		
		window.pack();
		window.setVisible(true);
		
		window.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
			    int confirmed = JOptionPane.showConfirmDialog(null, 
			        "Are you sure you want to exit the program?", "Exit Program Message Box",
			        JOptionPane.YES_NO_OPTION);

			    if (confirmed == JOptionPane.YES_OPTION) {
			    		xmpHandle.destroy();
			    		window.dispose();
			 
			    }
			  }
			});
		
	}

}
