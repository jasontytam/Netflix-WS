package com.tamj.netflix.controller.unogs;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamj.netflix.service.unogs.NetflixSearchService;
import com.tamj.netflix.service.unogs.entity.TitleDetail;
import com.tamj.netflix.service.unogs.entity.TitleSearchResult;

@RestController
@RequestMapping("/netflix/movie/search")
@CrossOrigin
public class NetflixSearchController {

	final private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private NetflixSearchService netSearchSvc;


	@GetMapping("/get/{id}")
	public TitleDetail getTitleDetailById(@PathVariable("id") String id) {
		return netSearchSvc.getTitleDetailById(id);
	}
	
	@GetMapping("/bytitlename/{titleName}")
	public List<TitleSearchResult> searchTitleByName(@PathVariable("titleName") String titleName) {
		return netSearchSvc.searchTitleByName(titleName);
	}
}
