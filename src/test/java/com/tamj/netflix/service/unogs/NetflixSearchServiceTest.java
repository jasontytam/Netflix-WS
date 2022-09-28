package com.tamj.netflix.service.unogs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tamj.netflix.service.unogs.entity.TitleDetail;
import com.tamj.netflix.service.unogs.entity.TitleSearchResult;

@SpringBootTest
public class NetflixSearchServiceTest {

	@Autowired
	private NetflixSearchService searchSvc;
	 
	@Test
	void testContext() {
//		 assertThat(searchSvc, is(notNullValue()));
		assertNotNull(this.searchSvc);
	}
	
	@Test
	void testGetOneTitleDetail() {
		TitleDetail titleDtl = null;

		titleDtl = this.searchSvc.getTitleDetailById(null);
		assertNull(titleDtl);

		titleDtl = this.searchSvc.getTitleDetailById("");
		assertNull(titleDtl);

		titleDtl = this.searchSvc.getTitleDetailById("1234");
		assertNull(titleDtl);
		
		titleDtl = this.searchSvc.getTitleDetailById("80161352");
		assertNotNull(titleDtl);
		assertEquals(titleDtl.getTitle(), "The Mummy");
	}
	
	@Test
	void testGetTilteByName() {
		List<TitleSearchResult> resultList = null;
		
		resultList = this.searchSvc.searchTitleByName(null);
		assertNull(resultList);

		resultList = this.searchSvc.searchTitleByName("");
		assertNull(resultList);

//		resultList = this.searchSvc.searchTitleByName("1234");
//		assertThat(resultList).isNull();
		
		resultList = this.searchSvc.searchTitleByName("mummy");
		assertNotNull(resultList);
//		assertEquals(resultList.getTitle(), "The Mummy");
	}
}
