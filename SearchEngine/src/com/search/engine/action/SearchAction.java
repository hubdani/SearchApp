package com.search.engine.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.entity.Article;
import com.google.gson.Gson;
import com.search.engine.utils.JsonHelper;
import com.solr.IndexSearcher;

/**
 * Servlet implementation class SearchAction
 */
@WebServlet("/SearchAction")
public class SearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IndexSearcher is=new IndexSearcher();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("action is here"+request.getParameter("searchText"));
		System.out.println("rowsInPage"+request.getParameter("rowsInPage"));
		System.out.println("page"+request.getParameter("page"));
		
		
		String searchText = request.getParameter("searchText");
		int rowsInPage = Integer.parseInt(request.getParameter("rowsInPage"));
		int page = Integer.parseInt(request.getParameter("page"));
		SolrDocumentList results;
		try {
			results = is.getSearchResults(searchText, rowsInPage, page);
			List<Article> jsonStrList = new ArrayList<Article>();
			System.out.println("results>>"+results);
			for (SolrDocument solrDocument : results) {
				jsonStrList.add(JsonHelper.articleObjectCreater(solrDocument));
			}
			String json2 = new Gson().toJson(jsonStrList);
			System.out.println(">>"+json2);
		response.setContentType("application/json"); 
		response.setCharacterEncoding("utf-8");
		//System.out.println(">>>>"+bothJson);
		response.getWriter().write(json2);
		
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String json1 = new Gson().toJson(object1); 
		String json2 = new Gson().toJson(object2); 
		response.setContentType("application/json"); 
		response.setCharacterEncoding("utf-8"); 
		String bothJson = "["+json1+","+json2+"]"; //Put both objects in an array of 2 elements
		response.getWriter().write(bothJson);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
