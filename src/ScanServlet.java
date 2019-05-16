import se.devschool.SchoolAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class ScanServlet  extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String barcode = request.getParameter("barcode");

        SchoolAdmin schoolAdmin = new SchoolAdmin();
        String message = schoolAdmin.registerArrivalDeparture(barcode);
        // Set response content type

        response.setContentType("text/html");
//        String message = "Welcome "+barcode;
        PrintWriter out = response.getWriter();
        String title = "Scanning";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<h2>"+message+"</h2>\n" +
                "<form action = \"ScanServlet\" method = \"GET\">\n"+
                "    Barcode: <input type = \"text\" name = \"barcode\">\n"+
                "    <br />"+
                "    <input type = \"submit\" value = \"Submit\" />\n"+
                "</form>"+
                "</body>" +
                "</html>"
        );
    }
}
