package com.tamj.netflix.service.unogs.entity;

public class SearchResultStat {
	private long total;
	private long limit;
	private long offset;
	
	@Override
	public String toString() {
		return "SearchResultStat [total=" + total + ", limit=" + limit + ", offset=" + offset + "]";
	}
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getLimit() {
		return limit;
	}
	public void setLimit(long limit) {
		this.limit = limit;
	}
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	
	
}
