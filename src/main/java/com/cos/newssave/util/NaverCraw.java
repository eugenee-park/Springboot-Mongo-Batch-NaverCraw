package com.cos.newssave.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.newssave.domain.News;

@Component
public class NaverCraw {

	int aidNum = 1;
	
	public List<News> collect5(){
		RestTemplate rt = new RestTemplate();
		List<News> newsList = new ArrayList<>();
		
		for (int i = 1; i < 6; i++) {
			String aid = String.format("%010d", aidNum);
			String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=102&oid=022&aid="+aid;
			String html = rt.getForObject(url, String.class);

			Document doc = Jsoup.parse(html);

			Element titleElement = doc.selectFirst("#articleTitle");
			Element createdAtElement = doc.selectFirst(".t11");
			Element companyElement = doc.selectFirst(".c_text"); // alt 속성값 가져오기 실패염...  
			
			String title = titleElement.text();
			String createdAt = createdAtElement.text();
			String company = companyElement.text();
			
			//System.out.println(title);
			//System.out.println(company);
			
			News news = News.builder()
					.title(title)
					.createdAt(createdAt)
					.company(company)
					.build();
			
			newsList.add(news);
			
			aidNum ++;
		}
		return newsList;
	}
}








