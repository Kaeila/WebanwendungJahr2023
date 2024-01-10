package de.hhbk.web.filter;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "BackendAreaFilter", urlPatterns ={"/backend/*"})
public class BackendAreaFilter implements Filter, Serializable
{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException
    { 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession websession = req.getSession(true); 
        
        if (websession.getAttribute("MyLoginObject") != null && (boolean) websession.getAttribute("MyLoginObject") == true)
        { 
            chain.doFilter(request, response);         
        }
        else
        { 
            HttpServletResponse resp = (HttpServletResponse) response; 
            resp.sendRedirect("../login.xhtml");             
        }        
    } 

}
