package servlet;

import model.Car;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProducerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String brand = req.getParameter("brand");
            String model = req.getParameter("model");
            String licensePlate = req.getParameter("licensePlate");
            long price = Long.parseLong(req.getParameter("price"));

            Car car = new Car(brand, model, licensePlate, price);
            resp.setStatus(CarService.getInstance().addCar(car) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_FORBIDDEN);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
