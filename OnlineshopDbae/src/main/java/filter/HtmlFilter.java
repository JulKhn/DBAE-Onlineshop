package filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter( urlPatterns={"/*"})
public class HtmlFilter extends BaseFilter implements Filter
{
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException 
	{
		HtmlRequestWrapper wrapper = new HtmlRequestWrapper((HttpServletRequest) req);
		chain.doFilter(wrapper, res);
	}
}

class HtmlRequestWrapper extends HttpServletRequestWrapper implements HttpServletRequest {
	
	public HtmlRequestWrapper(HttpServletRequest request) 
	{
		super(request);
	}
	
	public String getParameter(String str)
	{ 
	    return super.getParameter(str) == null ? "" : super.getParameter(str).replaceAll("<(.|\n)*?>", "");
	}
	
	
}
