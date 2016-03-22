package com.solr;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import com.entity.Article;
import com.parser.util.Parser;
import com.search.engine.utils.CommonConstraints;
import com.store.ArticleStore;

public class Indexer {
	public static void main(String[] args) throws IOException, SolrServerException {
		String xmlToParse = "dblp.xml";
		//String xmlToParse = "test.xml";
		Parser p = new Parser(xmlToParse);
		HttpSolrClient server = new HttpSolrClient( CommonConstraints.APPLICATION_SOLR_URL );
		Iterator<Entry<Long,Article>> iterator=ArticleStore.articleMap.entrySet().iterator();
		int i=0;
		while(iterator.hasNext()){
			Map.Entry<Long, Article> entry=(Map.Entry<Long, Article>)iterator.next();
			SolrInputDocument doc = new SolrInputDocument();
			
			doc.addField("id",  entry.getValue().getId());
			doc.addField("title", entry.getValue().getTitle());
			doc.addField("journal", entry.getValue().getJournal());
			doc.addField("authors", entry.getValue().getAuthors());
			doc.addField("key", entry.getValue().getKey());
			doc.addField("mdate", entry.getValue().getMdate());
			doc.addField("year", entry.getValue().getYear());
			server.add(doc);
			 i++;
			if (i % 100 == 0){
				server.commit(); // periodically flush
				System.out.println("No Records Commited :"+i);
			}
			  			 
		}
		/*for (int i = 0; i < 1000; ++i) {
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("dog", "book");
			doc.addField("id", "book-" + i);
			doc.addField("name", "The Legend of the Hobbit part " + i);
			server.add(doc);
			if (i % 100 == 0)
				server.commit(); // periodically flush
		}*/
		server.commit();
		server.close();
		 
	}
}
