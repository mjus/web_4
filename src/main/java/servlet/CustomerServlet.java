package servlet;

import com.google.gson.Gson;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = gson.toJson(CarService.getInstance().getAllCars());
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(json);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String licensePlate = req.getParameter("licensePlate");

        if(CarService.getInstance().buyCar(brand, model, licensePlate)){
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
