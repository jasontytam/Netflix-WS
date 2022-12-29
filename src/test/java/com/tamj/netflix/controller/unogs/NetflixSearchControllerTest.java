package com.tamj.netflix.controller.unogs;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tamj.netflix.service.unogs.NetflixSearchService;
import com.tamj.netflix.service.unogs.entity.TitleDetail;
import com.tamj.netflix.service.unogs.entity.TitleSearchResult;

@ExtendWith(MockitoExtension.class)
public class NetflixSearchControllerTest {

	@Mock
	private NetflixSearchService mockSearchSvc;
	
	@InjectMocks
	private NetflixSearchController searchController = new NetflixSearchController();
	
	@Test
	void getTitleByNetflixId_OneRecord() {
		String netflixId = "60004484";
		
		TitleDetail expResult = new TitleDetail();
		expResult.setTitle("The Lord of the Rings: The Return of the King");
		expResult.setTitle_type("movie");
		expResult.setNetflix_id("60004484");
		
		Mockito.when(this.mockSearchSvc.getTitleDetailById(netflixId)).thenReturn(expResult);
		
		TitleDetail actualResult = this.searchController.getTitleDetailById(netflixId);
		
		Assertions.assertEquals(expResult, actualResult);
		
		Mockito.verify(this.mockSearchSvc).getTitleDetailById(netflixId);
	}
	
	@Test
	void getTitleByNetflixId_NoRecord() {
		String netflixId = "60004484";
		
		Mockito.when(this.mockSearchSvc.getTitleDetailById(netflixId)).thenReturn(null);
		
		TitleDetail actualResult = this.searchController.getTitleDetailById(netflixId);
		
		Assertions.assertNull(actualResult);
		
		Mockito.verify(this.mockSearchSvc).getTitleDetailById(netflixId);
	}
	
	@Test
	void searchByTitleName_HaveRecord() {
		String titleName = "king";
		
		TitleSearchResult expResult = new TitleSearchResult();
		expResult.setNetflix_id("60004484");
		expResult.setTitle("The Lord of the Rings: The Return of the King");
		expResult.setTitle_type("movie");
		
		List<TitleSearchResult> expResultList = new ArrayList<TitleSearchResult>();
		expResultList.add(expResult);
		
		Mockito.when(this.mockSearchSvc.searchTitleByName(titleName)).thenReturn(expResultList);
		
		List<TitleSearchResult> actualResultList = this.searchController.searchTitleByName(titleName);
		
		Assertions.assertEquals(expResultList, actualResultList);
		
		Mockito.verify(this.mockSearchSvc).searchTitleByName(titleName);
	}
	
	@Test
	void searchByTitleName_NoRecord() {
		String titleName = "king";
		
		List<TitleSearchResult> expResultList = new ArrayList<TitleSearchResult>();
		
		Mockito.when(this.mockSearchSvc.searchTitleByName(titleName)).thenReturn(expResultList);
		
		List<TitleSearchResult> actualResultList = this.searchController.searchTitleByName(titleName);
		
		Assertions.assertEquals(expResultList, actualResultList);
		
		Mockito.verify(this.mockSearchSvc).searchTitleByName(titleName);
	}
	
	@Test
	void searchByTitleName_NoServiceAvailable() {
		String titleName = "king";
		
		Mockito.when(this.mockSearchSvc.searchTitleByName(titleName)).thenReturn(null);
		
		List<TitleSearchResult> actualResultList = this.searchController.searchTitleByName(titleName);
		
		Assertions.assertNull(actualResultList);
		
		Mockito.verify(this.mockSearchSvc).searchTitleByName(titleName);
	}
}
