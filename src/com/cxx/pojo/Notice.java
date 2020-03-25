package com.cxx.pojo;

public class Notice {
	private int noticeId;
	private String noticeName;
	private String noticeContent;
	private String noticeTimes;
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeName() {
		return noticeName;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeTimes() {
		return noticeTimes;
	}
	public void setNoticeTimes(String noticeTimes) {
		this.noticeTimes = noticeTimes;
	}
	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", noticeName=" + noticeName + ", noticeContent=" + noticeContent
				+ ", noticeTimes=" + noticeTimes + "]";
	}
	
}
