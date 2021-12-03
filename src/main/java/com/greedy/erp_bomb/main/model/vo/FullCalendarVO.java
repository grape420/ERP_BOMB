package com.greedy.erp_bomb.main.model.vo;

import java.io.Serializable;

public class FullCalendarVO implements Serializable {
	private static final long serialVersionUID = -3527949181678630889L;
	
	private String title;		// 제목
	private String start;		// 시작일 ("yyyy-MM-dd)
	private String end;			// 종료일 ("yyyy-MM-dd)
	private String allDay;		// 일정이 종일인지 아닌지 여부("true" or "false")
	
	public FullCalendarVO() {
	}
	public FullCalendarVO(String title, String start, String end, String allDay) {
		this.title = title;
		this.start = start;
		this.end = end;
		this.allDay = allDay;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getAllDay() {
		return allDay;
	}
	public void setAllDay(String allDay) {
		this.allDay = allDay;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "FullCalendarVO [title=" + title + ", start=" + start + ", end=" + end + ", allDay=" + allDay + "]";
	}
}
