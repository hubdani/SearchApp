package com.search.engine.utils;

public class CommonConstraints {
	public static final String APPLICATION_SOLR_URL = "http://localhost:8983/solr/IR";
	public static final String APPLICATION_DELETE_DATA_SOLR_URL = "http://localhost:8983/solr/IR/update?stream.body=<delete><query>*:*</query></delete>&commit=true";
}
