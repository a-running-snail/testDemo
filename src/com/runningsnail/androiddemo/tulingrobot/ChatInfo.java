package com.runningsnail.androiddemo.tulingrobot;

public class ChatInfo {

	public static final int SEND = 1;
	public static final int RECEIVE = 2;
	
	private String content;
	private int tag;
	private String time;
	public ChatInfo(String content,String time,int tag){
		this.content = content;
		this.time = time;
		this.tag = tag;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
