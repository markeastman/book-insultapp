package org.openshift;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by markeastman on 07/07/2016.
 */
@WebServlet(value="/insult", name="insult-servlet")
public class InsultServlet extends GenericServlet {

    private static String COUNT_VALUE = "countValue";

    // We now want to use a seesion value to see if they persis across HA redirects
    public void service(ServletRequest req, ServletResponse res)
            throws IOException, ServletException
    {
        String message = new InsultGenerator().generateInsult();
        if (req instanceof HttpServletRequest)
        {
            HttpServletRequest httpReq = (HttpServletRequest) req;
            HttpSession session = httpReq.getSession();
            Integer count = incrementCount( session );
            message = "For the " + count + " time " + message;
        }
        res.getWriter().println(message);
    }

    private Integer incrementCount(HttpSession session)
    {
        Integer count = (Integer)session.getAttribute(COUNT_VALUE);
        if (count == null)
            count = new Integer(1);
        else
            count = new Integer(count.intValue() + 1);
        session.setAttribute(COUNT_VALUE,count);
        return count;
    }
}
