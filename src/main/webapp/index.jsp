<<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>
<body>
<header>
    <%@include file="assets/components/navbar.jsp" %>
</header>
<section class="container-fluid d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="card text-center h-40">
        <div class="card-header">
            <% if ("true".equals(request.getParameter("registroExitoso"))) { %>
            <div class="alert alert-success" role="alert">
                Te has registrado exitosamente como: <%= request.getParameter("role") %>
            </div>
            <% } else if ("usuarioNoEncontrado".equals(request.getParameter("error"))) { %>
            <div class="alert alert-danger text-red" role="alert">
                Usuario no encontrado.
            </div>
            <% } else if ("contrasenaIncorrecta".equals(request.getParameter("error"))) { %>
            <div class="alert alert-danger text-red" role="alert">
                Contraseña incorrecta.
            </div>
            <% } %>
        </div>
        <div class="card-body overflow-auto">
            <h1 class="card-title">Bienvenido@</h1>
            <br>
            <br>
            <br>
            <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#exampleModal">Login</button>
            <a href="register.jsp" type="button" class="btn btn-warning" data-bs-toggle="btn">Registrate</a>
            <br>
        </div>
        <div class="card-footer text-body-secondary"></div>
    </div>
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Ingresa tus credenciales</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="LoginServlet" method="POST">
                        <div class="mb-3">
                            <label for="email" class="form-label">Correo</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="pass" class="form-label">Contraseña</label>
                            <input type="password" class="form-control" id="pass" name="pass" required>
                        </div>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        <button type="submit" class="btn btn-warning">Iniciar Sesión</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script>
    const myModal = document.getElementById('myModal')
    const myInput = document.getElementById('myInput')

    myModal.addEventListener('shown.bs.modal', () => {
        myInput.focus()
    })
</script>
</body>
</html>