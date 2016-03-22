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
		   article.setTitle("");
		   article.setKey("");

		   List<Person> list = (List<Person>) sd.get("authors");
		   article.setAuthors(list);
		   return article;
	   }
}
