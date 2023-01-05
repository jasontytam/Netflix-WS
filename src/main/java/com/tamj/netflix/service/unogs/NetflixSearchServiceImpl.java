package com.tamj.netflix.service.unogs;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tamj.netflix.helper.AwsSecretMgrHelper;
import com.tamj.netflix.service.unogs.entity.SearchResult;
import com.tamj.netflix.service.unogs.entity.TitleDetail;
import com.tamj.netflix.service.unogs.entity.TitleSearchResult;

@Component
public class NetflixSearchServiceImpl implements NetflixSearchService {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AwsSecretMgrHelper awsSecretHelper;
	
	private String url;
	private String headerRapidApiKey;
	private String headerRapidApiHost;
	
	public NetflixSearchServiceImpl() throws Exception {
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("rapidapi.properties"));
		
		this.url = props.getProperty("rapid.api.url");
		this.headerRapidApiKey = props.getProperty("rapid.api.key");
		this.headerRapidApiHost = props.getProperty("rapid.api.host");
	}
	
	private String getRapidApiKey() {
		
		if (this.headerRapidApiKey == null || "".equals(this.headerRapidApiKey.trim())) {
			this.headerRapidApiKey = awsSecretHelper.getSecret("prod/RapidApi", "rapid.api.key");
			
			logger.info("Rapid API Key retrieved from AWS Secret Manager");
		} else {
			logger.info("Rapid API Key retrieved from properties file");
		}
		
		return this.headerRapidApiKey;

	}
	
	@Override
	public TitleDetail getTitleDetailById(String netflixId) {
		
		if (netflixId == null || "".equals(netflixId.trim())) {
			return null;
		}

		HttpHeaders headers = new HttpHeaders();
//		headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
//		headers.add("accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("X-RapidAPI-Key", this.getRapidApiKey());
		headers.add("X-RapidAPI-Host", headerRapidApiHost);
		
		ResponseEntity<TitleDetail> responseEntity
			= this.restTemplate
				.exchange(
					url + "/title/details?netflix_id={neflix_id}", 
					HttpMethod.GET, 
					new HttpEntity<String>(headers), 
					TitleDetail.class, 
					netflixId
				);
		

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			if (responseEntity.getBody().getTitle() == null || "".equals(responseEntity.getBody().getTitle())) {
				return null;
			} else {
				return responseEntity.getBody();
			}
		} else {
			return null;
		}
	}

	@Override
	public List<TitleSearchResult> searchTitleByName(String titleName) {
		
		if (titleName == null || "".equals(titleName.trim())) {
			return null;
		}

		HttpHeaders headers = new HttpHeaders();
//		headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
//		headers.add("accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("X-RapidAPI-Key", this.getRapidApiKey());
		headers.add("X-RapidAPI-Host", headerRapidApiHost);
		
		ResponseEntity<SearchResult> responseEntity
			= this.restTemplate
				.exchange(
					url + "/search/titles?order_by=date&title={titleName}&type=movie", 
					HttpMethod.GET, 
					new HttpEntity<String>(headers), 
					SearchResult.class, 
					titleName
				);
		
		

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			List<TitleSearchResult> resultList = responseEntity.getBody().getResults();
			if (resultList != null) {
				resultList = resultList.stream().filter(
						(result) -> (
								(" "+result.getTitle()+" ").toLowerCase().contains(" "+titleName.toLowerCase()+" ")
						)
					).toList();
			}
			return resultList;
		} else {
			return null;
		}
	}

}
