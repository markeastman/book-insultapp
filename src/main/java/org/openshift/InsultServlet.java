package org.openshift;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Created by markeastman on 07/07/2016.
 */
@WebServlet(value="/insult", name="insult-servlet")
public class InsultServlet extends GenericServlet {

    public void service(ServletRequest req, ServletResponse res)
            throws IOException, ServletException
    {
        String message = new InsultGenerator().generateInsult();
        res.getWriter().println(message);
    }
}
