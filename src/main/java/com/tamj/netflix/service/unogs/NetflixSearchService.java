package com.tamj.netflix.service.unogs;

import java.util.List;

import com.tamj.netflix.service.unogs.entity.TitleDetail;
import com.tamj.netflix.service.unogs.entity.TitleSearchResult;

public interface NetflixSearchService {
	public TitleDetail getTitleDetailById(String id);
	public List<TitleSearchResult> searchTitleByName(String titleName);
}
