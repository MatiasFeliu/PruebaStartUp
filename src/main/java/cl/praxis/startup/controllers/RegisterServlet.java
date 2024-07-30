package cl.praxis.startup.controllers;

import cl.praxis.startup.model.dao.impl.AddressImpl;
import cl.praxis.startup.model.dao.impl.CarImpl;
import cl.praxis.startup.model.dao.impl.ProviderImpl;
import cl.praxis.startup.model.dao.impl.UserImpl;
import cl.praxis.startup.model.models.AddressDTO;
import cl.praxis.startup.model.models.CarDTO;
import cl.praxis.startup.model.models.ProviderDTO;
import cl.praxis.startup.model.models.UserDTO;
import cl.praxis.startup.model.service.AddressService;
import cl.praxis.startup.model.service.CarService;
import cl.praxis.startup.model.service.ProviderService;
import cl.praxis.startup.model.service.UserService;
import cl.praxis.startup.model.service.impl.AddressServiceImpl;
import cl.praxis.startup.model.service.impl.CarServiceImpl;
import cl.praxis.startup.model.service.impl.ProviderServiceImpl;
import cl.praxis.startup.model.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(name = "registerservlet", value = "/RegisterServlet")

public class RegisterServlet extends HttpServlet {

    private final UserService userService;
    private final AddressService addressService;
    private final CarService carService;
    private final ProviderService providerService;


    public RegisterServlet() throws ServletException {
        // Inicialización de los servicios
        this.userService = new UserServiceImpl(new UserImpl()); // Ajusta según tus implementaciones
        this.addressService = new AddressServiceImpl(new AddressImpl()); // Ajusta según tus implementaciones
        this.carService = new CarServiceImpl(new CarImpl()); // Ajusta según tus implementaciones
        this.providerService = new ProviderServiceImpl(new ProviderImpl()); // Ajusta según tus implementaciones
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String email= req.getParameter("correo");
        String password= req.getParameter("clave");
        String name = req.getParameter("nombre");
        String nick = req.getParameter("apodo");
        int weight = Integer.parseInt(req.getParameter("peso"));
        String address = req.getParameter("direccion");
        int num = Integer.parseInt(req.getParameter("num"));
        String carName = req.getParameter("nombreAuto");
        String carUrl = req.getParameter("urlAuto");
        String providerName = req.getParameter("nombreProveedor");
        String role = req.getParameter("rol");

        // Insertar proveedor si no existe
        ProviderDTO providerDTO = providerService.selectProvider(providerName);

        if (providerDTO == null) {
            providerDTO = new ProviderDTO();
            providerDTO.setProvidersName(providerName);
            providerDTO = providerService.insertProvider(providerDTO);
        }
        int providerId = providerDTO.getIdProviders();

        // Insertar coche
        CarDTO carDTO = new CarDTO();
        carDTO.setCarName(carName);
        carDTO.setUrl(carUrl);
        carDTO.setIdProvider(providerId);
        carDTO = carService.insertCar(carDTO);
        int carId = carDTO.getIdCar();

        // Insertar usuario
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setName(name);
        userDTO.setNick(nick);
        userDTO.setWeight(weight);
        userDTO.setIdAuto(carId);
        userDTO.setRole(role);
        userService.insertUser(userDTO);

        // Insertar dirección
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressName(address);
        addressDTO.setAddressNum(num);
        addressDTO.setIdUser(userDTO.getId());
        addressService.insertAddress(addressDTO);

        HttpSession session = req.getSession();
        session.setAttribute("registeredRole", role);

        // Redirigir a la página de inicio
        resp.sendRedirect(req.getContextPath() + "/index.jsp?registroExitoso=true&role=" + role);

    }

}