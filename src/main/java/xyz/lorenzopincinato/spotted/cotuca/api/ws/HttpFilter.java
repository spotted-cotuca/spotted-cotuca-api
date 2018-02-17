package xyz.lorenzopincinato.spotted.cotuca.api.ws;

import io.yawp.commons.http.HttpException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public abstract class HttpFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(HttpFilter.class.getCanonicalName());

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            filter((HttpServletRequest) request, (HttpServletResponse) response);
            chain.doFilter(request, response);
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception e) {
            LOGGER.log(SEVERE, "Unexpected error on Filter", e);
            throw new HttpException(500, "Unexpected error on filter");
        }
    }

    protected abstract void filter(HttpServletRequest request, HttpServletResponse response) throws Exception;

    @Override
    public void destroy() {
    }
}