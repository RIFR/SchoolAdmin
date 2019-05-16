import se.devschool.SchoolAdmin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminServlet  extends HttpServlet {
    private String title = "Scanning";
    SchoolAdmin schoolAdmin = new SchoolAdmin();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String pos = request.getParameter("pos");

        String message = "";
        if (pos == null )
            message = loginScr();
        else {
            System.out.println(pos);
            switch (pos) {
                case "m":
                    message = inputMeny ();
                    break;
                case "0":
                    message = login(request.getParameter("username"),request.getParameter("password"));

                    break;
                case "2":
                    String barcode = request.getParameter("barcode");
                    if (barcode == null)
                        message = arrivalDeparture();
                    else
                        message =  schoolAdmin.registerArrivalDeparture(barcode);
                        message += "    <input type = \"hidden\" name = \"pos\" value = \"m\">\n";
                    break;
                case "3":


                    break;
                case "4":
                    message = schoolAdmin.listArrivalDeparture();
                    break;

            }
        }
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                message+
                "</body>" +
                "</html>"
        );
    }

    private String loginScr() {
        String returnStr;
        returnStr  = "<form action = \"AdminServlet\" method = \"GET\">\n";
        returnStr += "    <input type = \"hidden\" name = \"pos\" value = \"0\">\n";
        returnStr += "    Username: <input type = \"text\" name = \"username\">\n";
        returnStr += "    <br />";
        returnStr += "    Password:  <input type = \"password\" name = \"password\">\n";
        returnStr += "    <br />";
        returnStr += "    <input type = \"submit\" value = \"Submit\" />\n";
        returnStr += "</form>";
        title = "Login";
        return returnStr;
    }

    private String login(String username, String password) {
        String returnStr;
        String uname = schoolAdmin.checkLogin(username,password);
        if (uname.isEmpty()) {
            returnStr = loginScr();
        } else {
            returnStr  = "<form action = \"AdminServlet\" method = \"GET\">\n";
            returnStr += "    <input type = \"hidden\" name = \"pos\" value = \"m\">\n";
            returnStr += "    <input type = \"hidden\" name = \"username\" value = \""+uname+"\">\n";
            returnStr += "    <h1>Hi "+uname+" you're in!</h1>\n";
            returnStr += "    <br />";
            returnStr += "    <input type = \"submit\" value = \"Submit\" />\n";
            returnStr += "</form>";
            title = "Logged in";
        }
        return returnStr;

    }

    private String inputMeny () {
        String option = "";
        String returnStr;
        returnStr  = "<form action = \"AdminServlet\" method = \"GET\">\n";
        //returnStr += "    <input type = \"hidden\" name = \"pos\" value = \"m\">\n";
        returnStr += "    <h1>Options 1.Array/Departure</h1>\n";
        returnStr += "    <h1>        2.               </h1>\n";
        returnStr += "    Options: <input type = \"text\" name = \"pos\" value = \""+option+"\">\n";
        returnStr += "    <br />";
        returnStr += "    <input type = \"submit\" value = \"Submit\" />\n";
        returnStr += "</form>";
        title = "MENY";

        return returnStr;

    }

    private String arrivalDeparture () {
        String barcode = "";
        String returnStr;
        returnStr  = "<form action = \"AdminServlet\" method = \"GET\">\n";
        returnStr += "    <input type = \"hidden\" name = \"pos\" value = \"2\">\n";
        returnStr += "    Barcode: <input type = \"text\" name = \"barcode\" value = \""+barcode+"\">\n";
        returnStr += "    <br />";
        returnStr += "    <input type = \"submit\" value = \"Submit\" />\n";
        returnStr += "</form>";
        title = "Arrival/Departure";

        return returnStr;

    }

}
