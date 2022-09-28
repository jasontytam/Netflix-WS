package com.tamj.netflix.service.unogs.entity;

import java.util.List;

public class SearchResult {
	private SearchResultStat object;
	private List<TitleSearchResult> results;
	
	@Override
	public String toString() {
		return "SearchResult [object=" + object + ", results=" + results + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public SearchResultStat getObject() {
		return object;
	}

	public void setObject(SearchResultStat object) {
		this.object = object;
	}

	public List<TitleSearchResult> getResults() {
		return results;
	}

	public void setResults(List<TitleSearchResult> results) {
		this.results = results;
	}
	
}
