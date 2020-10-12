package com.vti.academy.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class LinkResponse {
	@JsonInclude(Include.NON_NULL)
	private String first;
	@JsonInclude(Include.NON_NULL)
	private String last;
	@JsonInclude(Include.NON_NULL)
	private String next;
	@JsonInclude(Include.NON_NULL)
	private String prev;

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

}
