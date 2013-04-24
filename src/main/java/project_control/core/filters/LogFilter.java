package project_control.core.filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
        
        //Get the IP address of client machine.
        String ipAddress = request.getRemoteAddr();
        
        //Log the IP address and current timestamp.
        System.out.println("IP "+ipAddress + ", Time "
                            + new Date().toString());
        
        chain.doFilter(arg0, arg1);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		//Get init parameter
        String testParam = config.getInitParameter("test-param");
         
        //Print the init parameter
        System.out.println("Test Param: " + testParam);
	}

}
