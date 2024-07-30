package cl.praxis.startup.controllers;

import cl.praxis.startup.model.dao.impl.CarImpl;
import cl.praxis.startup.model.dao.impl.ProviderImpl;
import cl.praxis.startup.model.dao.impl.UserImpl;
import cl.praxis.startup.model.models.CarDTO;
import cl.praxis.startup.model.models.ProviderDTO;
import cl.praxis.startup.model.models.UserDTO;
import cl.praxis.startup.model.service.CarService;
import cl.praxis.startup.model.service.ProviderService;
import cl.praxis.startup.model.service.UserService;
import cl.praxis.startup.model.service.impl.CarServiceImpl;
import cl.praxis.startup.model.service.impl.ProviderServiceImpl;
import cl.praxis.startup.model.service.impl.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


    // Instanciamos la implementación de IUsuarioService
    //private IUsuarioService usuarioService = new UsuarioServiceImp();
    @WebServlet(name = "loginServlet", value = "/LoginServlet")

    public class LoginServlet extends HttpServlet {

        private UserService userService = new UserServiceImpl(new UserImpl());
        private ProviderService providerService = new ProviderServiceImpl(new ProviderImpl());
        private CarService carService = new CarServiceImpl(new CarImpl());

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            processLogin(request, response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.sendRedirect("index.jsp");
        }


        private void processLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String email = request.getParameter("email");
            String password = request.getParameter("pass");

            System.out.println("email: " + email);
            System.out.println("pass: " + password);

            UserDTO user = userService.login(email);

            if (user == null) {
                response.sendRedirect("index.jsp?error=usuarioNoEncontrado");
                return;
            }

            if (!user.getPassword().equals(password)) {
                response.sendRedirect("index.jsp?error=contrasenaIncorrecta");
                return;
            }

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if ("Admin".equals(user.getRole())) {
                System.out.println("rol: " + user.getRole());
                loadAdminHome(request, response);
            } else {
                response.sendRedirect("home2.jsp");
                System.out.println("rol: " + user.getRole());
            }
        }


        private void loadAdminHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<UserDTO> allUsers = userService.selectAllUsers();
            List<ProviderDTO> allProviders = providerService.selectAllProviders();
            List<CarDTO> allCars = carService.selectAllCars();

            req.setAttribute("users", allUsers);
            req.setAttribute("providers", allProviders);
            req.setAttribute("cars", allCars);

            RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
            dispatcher.forward(req, resp);
        }

    }