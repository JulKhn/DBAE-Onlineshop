package filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter({"/*"})
public class XSSFilter extends BaseFilter implements Filter {
	public void doFilter(
			ServletRequest req, ServletResponse res, FilterChain chain)
				throws IOException, ServletException {
		XSSRequestWrapper wrapper = new XSSRequestWrapper((HttpServletRequest) req); 
		chain.doFilter(wrapper, res);
	}
}

class XSSRequestWrapper extends HttpServletRequestWrapper implements HttpServletRequest {
	public XSSRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	public String getParameter(String str) {
		str = super.getParameter(str);
		if (str != null) {
			Pattern scriptPattern = null;
			scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll(" ");
			scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll(" ");
			scriptPattern = Pattern.compile("onmouseover=", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll(" ");
		}
		
		return str;
	}
}