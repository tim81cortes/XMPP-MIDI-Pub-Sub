package com.pdl.cpychatclient2;

import java.util.HashMap;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

public class MyMessageListener implements MessageListener {
	private String from;
	private String body;
	private Message msg;
	
	@Override
	public void processMessage(Chat chat, Message message) {
		body = "nothing";
		String tmp = null;
		
		from = message.getFrom();
		tmp = message.getBody();
		
		if(tmp != null)
		{
			msg = message;
			body = tmp;		
			System.out.println(String.format("Received message "+ body + " from " + from));

		}	
		
	}
	public void updateHMap(HashMap hMap, int msgNum){
		hMap.put(msgNum, msg);
		
	}
	public String getMsgSndr(){
		return from;
	}
	
	public Message getMsg(){
		
		System.out.println("getMsg Called");
		return msg;
		
	}

}
