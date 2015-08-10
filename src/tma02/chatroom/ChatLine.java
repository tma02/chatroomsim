package tma02.chatroom;

import java.io.Serializable;

public class ChatLine implements Serializable {
	
	private static final long serialVersionUID = -8697075356470412520L;
	private String timeStamp;
	private String userName;
	private String message;
	
	public ChatLine(String line) {
		String[] seg = line.split(" ");
		this.timeStamp = seg[0];
		this.userName = seg[1].substring(0, seg[1].length() - 1);
		this.message = line.split(": ")[1];
	}
	
	public String getTimeStamp() {
		return this.timeStamp;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getMessage() {
		return this.message;
	}

}
