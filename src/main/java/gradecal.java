import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/gradecal")
public class gradecal extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public gradecal() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        try {
            int nosub = Integer.parseInt(request.getParameter("hidden_nosub"));

            int[] marks = new int[nosub];

            for (int i = 1; i <= nosub; i++) {
                int mark = Integer.parseInt(request.getParameter("subject" + i));
                marks[i - 1] = mark;
            }

            
            int total = 0;
            for (int mark : marks) {
                total += mark;
            }
            double average = (double) total / nosub;

            
            String grade;
            if (average >= 90) {
                grade = "A";
            } else if (average >= 80) {
                grade = "B";
            } else if (average >= 70) {
                grade = "C";
            } else if (average >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }

            StringBuilder result = new StringBuilder();
            result.append("<style>");
            result.append("table { border-collapse: collapse; width: 50%; margin: auto; font-family: Arial, sans-serif; }");
            result.append("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }");
            result.append("th { background-color: #f2f2f2; }");
            result.append("</style>");
            result.append("<table>");
            result.append("<tr><th>Subject</th><th>Marks</th></tr>");
            for (int i = 0; i < nosub; i++) {
                result.append("<tr><td>Subject " + (i + 1) + "</td><td>" + marks[i] + "</td></tr>");
            }
            result.append("<tr><td>Total</td><td>" + total + "</td></tr>");
            result.append("<tr><td>Average</td><td>" + String.format("%.2f", average) + "</td></tr>");
            result.append("<tr><td>Grade</td><td>" + grade + "</td></tr>");
            result.append("</table>");

            pw.println(result.toString());

        } catch (NumberFormatException e) {
            pw.println("<p>Invalid input. Please enter valid numbers.</p>");
        }
    }
}
