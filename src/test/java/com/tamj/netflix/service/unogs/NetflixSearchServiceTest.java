package com.tamj.netflix.service.unogs;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tamj.netflix.helper.AwsSecretMgrHelper;
import com.tamj.netflix.service.unogs.entity.SearchResult;
import com.tamj.netflix.service.unogs.entity.TitleDetail;
import com.tamj.netflix.service.unogs.entity.TitleSearchResult;

@SpringBootTest
public class NetflixSearchServiceTest {
	
	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private NetflixSearchServiceImpl searchSvc;
	
	private HttpHeaders httpHeaders;
	private String url;
	
	@BeforeEach
	void init() {
		String rapidApiKey = AwsSecretMgrHelper.getSecret("prod/RapidApi", "rapid.api.key");
		
		this.httpHeaders = new HttpHeaders();
		this.httpHeaders.add("X-RapidAPI-Key", rapidApiKey);
		this.httpHeaders.add("X-RapidAPI-Host", "unogs-unogs-v1.p.rapidapi.com");
		
		this.url = "https://unogs-unogs-v1.p.rapidapi.com";
	}
	 
	@Test
	void testContext() {
		Assertions.assertNotNull(this.searchSvc);
	}
	
	@Test
	void getTitleDetailById_Success() {
		String netflixId = "60004484";
		
		TitleDetail titleDetailResult = new TitleDetail();
		titleDetailResult.setTitle("The Lord of the Rings: The Return of the King");
		titleDetailResult.setTitle_type("movie");
		titleDetailResult.setNetflix_id("60004484");
		
		ResponseEntity<TitleDetail> expResultEntity = new ResponseEntity<TitleDetail>(titleDetailResult, HttpStatus.OK);
		
		Mockito.when(this.restTemplate
					.exchange(
						url + "/title/details?netflix_id={neflix_id}", 
						HttpMethod.GET, 
						new HttpEntity<String>(this.httpHeaders), 
						TitleDetail.class, netflixId)
					)
				.thenReturn(expResultEntity);
		
		TitleDetail actualTitleDetail = this.searchSvc.getTitleDetailById(netflixId);
		
		Assertions.assertEquals(titleDetailResult, actualTitleDetail);
		
		Mockito.verify(this.restTemplate)
				.exchange(
					url + "/title/details?netflix_id={neflix_id}", 
					HttpMethod.GET, 
					new HttpEntity<String>(this.httpHeaders), 
					TitleDetail.class, netflixId
				);
	}
	
	@Test
	void getTitleDetailById_Failure() {
		String netflixId = "60004484";
		
		ResponseEntity<TitleDetail> expResultEntity = new ResponseEntity<TitleDetail>(HttpStatus.UNAUTHORIZED);
		
		Mockito.when(this.restTemplate
					.exchange(
						url + "/title/details?netflix_id={neflix_id}", 
						HttpMethod.GET, 
						new HttpEntity<String>(this.httpHeaders), 
						TitleDetail.class, netflixId)
					)
				.thenReturn(expResultEntity);
		
		TitleDetail actualTitleDetail = this.searchSvc.getTitleDetailById(netflixId);
		
		Assertions.assertNull(actualTitleDetail);
		
		Mockito.verify(this.restTemplate)
				.exchange(
					url + "/title/details?netflix_id={neflix_id}", 
					HttpMethod.GET, 
					new HttpEntity<String>(this.httpHeaders), 
					TitleDetail.class, netflixId
				);
	}
	
	@Test
	void getTitleByName_Success() {
		String titleName = "mummy";
		
		TitleSearchResult titleSearchResult = new TitleSearchResult();
		titleSearchResult.setTitle("The Mummy");
		titleSearchResult.setTitle_type("movie");
		titleSearchResult.setNetflix_id("80161352");
		
		List<TitleSearchResult> expResultList = new ArrayList<TitleSearchResult>();
		expResultList.add(titleSearchResult);
		
		SearchResult searchResult = new SearchResult();
		searchResult.setResults(expResultList);
		
		ResponseEntity<SearchResult> expResultEntity = new ResponseEntity<SearchResult>(searchResult, HttpStatus.OK);
		
		Mockito.when(this.restTemplate
					.exchange(
						url + "/search/titles?order_by=date&title={titleName}&type=movie", 
						HttpMethod.GET, 
						new HttpEntity<String>(this.httpHeaders), 
						SearchResult.class, titleName)
					)
				.thenReturn(expResultEntity);
		
		List<TitleSearchResult> actualResultList = this.searchSvc.searchTitleByName(titleName);
		
		Assertions.assertEquals(expResultList, actualResultList);
		
		Mockito.verify(this.restTemplate)
				.exchange(
					url + "/search/titles?order_by=date&title={titleName}&type=movie", 
					HttpMethod.GET, 
					new HttpEntity<String>(this.httpHeaders), 
					SearchResult.class, titleName
				);

	}
	
	@Test
	void getTitleByName_Failure() {
		String titleName = "mummy";
		
		TitleSearchResult titleSearchResult = new TitleSearchResult();
		titleSearchResult.setTitle("The Mummy");
		titleSearchResult.setTitle_type("movie");
		titleSearchResult.setNetflix_id("80161352");
		
		List<TitleSearchResult> expResultList = new ArrayList<TitleSearchResult>();
		expResultList.add(titleSearchResult);
		
		SearchResult searchResult = new SearchResult();
		searchResult.setResults(expResultList);
		
		ResponseEntity<SearchResult> expResultEntity = new ResponseEntity<SearchResult>(HttpStatus.UNAUTHORIZED);
		
		Mockito.when(this.restTemplate
					.exchange(
						url + "/search/titles?order_by=date&title={titleName}&type=movie", 
						HttpMethod.GET, 
						new HttpEntity<String>(this.httpHeaders), 
						SearchResult.class, titleName)
					)
				.thenReturn(expResultEntity);
		
		List<TitleSearchResult> actualResultList = this.searchSvc.searchTitleByName(titleName);
		
		Assertions.assertNull(actualResultList);
		
		Mockito.verify(this.restTemplate)
				.exchange(
					url + "/search/titles?order_by=date&title={titleName}&type=movie", 
					HttpMethod.GET, 
					new HttpEntity<String>(this.httpHeaders), 
					SearchResult.class, titleName
				);

	}
}
