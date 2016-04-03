package com.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.search.engine.utils.CommonConstraints;

import java.io.IOException;
import java.net.MalformedURLException;

public class IndexSearcher {
	public SolrDocumentList getSearchResults(String queryStr, int noOfRows, int pageNo) throws  IOException, SolrServerException, MalformedURLException {
		HttpSolrClient server = new HttpSolrClient( CommonConstraints.APPLICATION_SOLR_URL );

		SolrQuery query = new SolrQuery();
		query.setQuery(queryStr);
		//query.addFilterQuery("cat:electronics", "store:amazon.com");
		//query.setFields("id", "title", "journal", "authors", "key");
		query.setStart((pageNo - 1) * noOfRows);
		query.setRows(noOfRows);
		query.setFacet(true);
		query.set("defType", "edismax");
		query.setParam("wt", "json");  //doesn't affect the return format
		/*
		 * doc.addField("id",  entry.getValue().getId());
			doc.addField("title", entry.getValue().getTitle());
			doc.addField("journal", entry.getValue().getJournal());
			doc.addField("authors", entry.getValue().getAuthors());
			doc.addField("key", entry.getValue().getKey());
			doc.addField("mdate", entry.getValue().getMdate());
			doc.addField("year", entry.getValue().getYear());
		 */
		SolrDocumentList results = null;
		QueryResponse response;
		try {
			response = server.query(query);
			
			 results = response.getResults();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		server.close();
		return results;
	}
}