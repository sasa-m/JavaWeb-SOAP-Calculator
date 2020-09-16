

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.tempuri.Calculator;
import org.tempuri.CalculatorSoap;


@WebServlet(urlPatterns = {"/logic"})
public class logicMachine extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Enumeration<String> parametars = request.getParameterNames();

            while (parametars.hasMoreElements()) {
                String param = (String) parametars.nextElement();
                String value = request.getParameter(param);
                request.setAttribute(param, value);
            }
            
            int f = Integer.parseInt(request.getParameter("fn"));
            int s = Integer.parseInt(request.getParameter("sn"));
            String o = request.getParameter("op");
            
            
            Calculator c = new Calculator();
            CalculatorSoap cs = c.getCalculatorSoap();
            
            switch (o) {
                case "+":
                    int r = cs.add(f, s);
                    out.print("The result is: " + r);
                    break;
                case "-":
                    int e = cs.subtract(f, s);
                    out.print("The result is: " + e);
                    break;
                case "*":
                    int w = cs.multiply(f, s);
                    out.print("The result is: " + w);
                    break;
                case ":":
                    int q = cs.divide(f, s);
                    out.print("The result is: " + q);
                    break;
            }

        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
