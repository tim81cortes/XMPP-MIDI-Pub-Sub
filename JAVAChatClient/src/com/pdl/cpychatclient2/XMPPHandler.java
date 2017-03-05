package com.pdl.cpychatclient2;

import java.util.Collection;

import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;



public class XMPPHandler {
	String server;
	int port;
	private static final int timeout = 500; // The packet reply times out after half a second
	private XMPPConnection xmpConnect;
	private ChatManager chatMngr;
	private ConnectionConfiguration cnnctnConfig;
	private String latestMsg;
	//private String server;
	private MyMessageListener msgLstnr;
	// Add constructor
	public XMPPHandler(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	public void initLogin(String username, String password) throws XMPPException{
		
		cnnctnConfig = new ConnectionConfiguration(server, port);
		cnnctnConfig.setSASLAuthenticationEnabled(false);
		cnnctnConfig.setSecurityMode(SecurityMode.disabled);
		
		latestMsg = "nothing yet";
		
		System.out.println("Creating connection to server on port");
		
		SmackConfiguration.setPacketReplyTimeout(timeout);
		// New xmpp connection
		xmpConnect = new XMPPConnection(cnnctnConfig);
		
		//Attempt to establish a connection
		
		xmpConnect.connect();
		//check connection
		System.out.println("Connected: " + xmpConnect.isConnected());

		// Log in with username and password
		xmpConnect.login(username, password);		
		chatMngr = xmpConnect.getChatManager();
		// Set up event listener for incoming messages
				
	}
//	public XMPPConnection getXMPPCnnctn(){
//		// Trying to return the xmpp connection to main
//		return xmpConnect;
//	}
	public MyMessageListener getMsgLstnr(){
		return msgLstnr;
	}
	public ChatManager getChatHndlr(){
		return chatMngr;
	}
	
	
	// Round about way of returning most recent message to main
	public String getLatestMsg(){
	 
		return latestMsg;
	}
	//  Recieve has to pull message from MyMessageListener first
	
	public void printRoster() throws Exception {
		Roster roster = xmpConnect.getRoster();
		Collection<RosterEntry> entries = roster.getEntries();		
		for (RosterEntry entry : entries) {
		    System.out.println(String.format("Buddy:%1$s - Status:%2$s", 
		    		entry.getName(), entry.getStatus()));
		}
	}

	public void destroy() {
		if (xmpConnect!=null && xmpConnect.isConnected()) {
			System.out.println("Just before disconnection");
			// Disconnect from server
			xmpConnect.disconnect();
		}	
	}	
}
