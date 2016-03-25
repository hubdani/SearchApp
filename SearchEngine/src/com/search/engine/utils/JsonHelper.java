package com.search.engine.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.solr.common.SolrDocument;

import com.entity.Article;
import com.entity.Person;

public class JsonHelper {
	   public static Article articleObjectCreater(SolrDocument sd) {
		   Article article= new Article();
		   article.setId(Long.parseLong((String) sd.get("id")));
		   article.setTitle(((List<String>) sd.get("title")).get(0));
		   article.setKey(((List<String>) sd.get("key")).get(0).toString());

		   List<Person> list = (List<Person>) sd.get("authors");
		   String[] authorNamesArr = new String[list.size()];
		   for (int i = 0; i < list.size(); i++) {
			   System.out.println("???"+list.get(i));
			authorNamesArr[i] = list.get(i).getName();
			
		   }
		   article.setAuthorNamesArr(authorNamesArr);
		   //article.setAuthors(list);
		   return article;
	   }
}
