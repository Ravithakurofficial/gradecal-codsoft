import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/onclick")
public class onclick extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public onclick() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        try {
            int nosub = Integer.parseInt(request.getParameter("nosub"));

            StringBuilder formHtml = new StringBuilder();
            formHtml.append("<form action=\"gradecal\" method=\"post\" id=\"cal\">");
            formHtml.append("<style>");
            formHtml.append("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
            formHtml.append("form { margin: 50px auto; padding: 20px; max-width: 500px; background-color: #ffffff; border-radius: 5px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }");
            formHtml.append("table { width: 100%; border-collapse: collapse; margin-top: 15px; }");
            formHtml.append("td { padding: 8px; border: 1px solid #ddd; }");
            formHtml.append("input[type=\"number\"] { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 3px; }");
            formHtml.append("button[type=\"submit\"] { padding: 10px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 3px; cursor: pointer; }");
            formHtml.append("</style>");
            formHtml.append("<table>");

            for (int i = 1; i <= nosub; i++) {
                formHtml.append("<tr>");
                formHtml.append("<td>Enter subject no " + i + " marks</td>");
                formHtml.append("<td><input type=\"number\" name=\"subject" + i + "\"></td>");
                formHtml.append("</tr>");
            }

            formHtml.append("<input type=\"hidden\" name=\"hidden_nosub\" value=\"" + nosub + "\">"); // Pass nosub value
            formHtml.append("<tr><td colspan=\"2\" align=\"center\"><button type=\"submit\">Calculate</button></td></tr>");
            formHtml.append("</table>");
            formHtml.append("</form>");

            pw.println(formHtml.toString());

        } catch (NumberFormatException e) {
            pw.println("<p>Invalid input. Please enter valid numbers.</p>");
        }
    }
}
