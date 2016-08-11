/**
 * 
 */
package com.framework.core.domain;


/**
 * @author Jiangyh
 *
 */
public class GridListVO {
	private int rows;
	private int page;
	private int total;
	@SuppressWarnings("unused")
	private int start;
	
	public int getStart() {
		return ( page - 1 ) * rows;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

}
