package kz.kstu.coffee.filter;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import kz.kstu.coffee.constants.Constants;

/**
 *
 * @author Kirill
 */
public class InternatiolizationFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding(Constants.ENCODING);
        if (req.getSession().getAttribute(Constants.LOCALE) == null) {
            req.getSession().setAttribute(Constants.LOCALE,req.getLocale());
        }
        String language = request.getParameter(Constants.LANGUAGE);
        if (language != null) {
            Locale locale = new Locale(language);
            req.getSession().setAttribute(Constants.LOCALE, locale);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
