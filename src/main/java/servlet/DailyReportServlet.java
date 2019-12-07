package servlet;

import com.google.gson.Gson;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DailyReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().contains("all")) {
            Gson gson = new Gson();
            String json = gson.toJson(DailyReportService.getInstance().getAllDailyReports());
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(json);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else if (req.getPathInfo().contains("last")) {
            Gson gson = new Gson();
            String json = gson.toJson(DailyReportService.getInstance().getLastReport());
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(json);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DailyReportService.getInstance().delete();
    }
}