package com.back.base.page;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


public class PageContext extends   Page  {
	/**
	 * 
	 */

	private static final Logger logger = Logger.getLogger(PageContext.class);

	private static ThreadLocal<PageContext> context = new ThreadLocal<PageContext>();


	public static PageContext getContext(HttpServletRequest request,int pageRow)
	{
		PageContext ci = context.get();
		if(ci == null)
		{
			ci = new PageContext();
			context.set(ci);
		}
		
		String pagec = request.getParameter("page"); 
		String pageSize = request.getParameter("pageSize"); 
		//String totalPages = request.getParameter("totalPages"); 
		//String totalRows = request.getParameter("totalRows"); 
		if(null == pagec)
		{
			ci.setCurrentPage(1);
			ci.setPageSize(pageRow);
		}
		else{
			if(pagec.equals("") || !pagec.matches("[0-9]+") ){
				pagec = "1";
			}
			if(pageSize.equals("") || !pageSize.matches("[0-9]+")){
				pageSize = "1";
			}
			if(Integer.parseInt(pageSize)>50){
				pageSize = "50";
			}
			ci.setCurrentPage(Integer.parseInt(pagec));
			ci.setPageSize(Integer.parseInt(pageSize));
			//ci.setTotalPages(Integer.parseInt(totalPages));
			//ci.setTotalRows(Integer.parseInt(totalRows));
		}
		
		return ci;
	}
	public static PageContext getContext()
	{
		PageContext ci = context.get();
		if(ci == null)
		{
			ci = new PageContext();
			context.set(ci);
		}
		return ci;
	}
	public  static void removeContext()
	{
		context.remove();
	}

	protected void initialize() {

		

	}
	


	
	
	

	
}
