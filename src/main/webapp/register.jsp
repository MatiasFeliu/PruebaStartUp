<%--
  Created by IntelliJ IDEA.
  User: matia
  Date: 16-07-2024
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Proveedores</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>
<body>
<%@include file="assets/components/navbar.jsp"%>
<section class="container">
    <br>
    <div class="container text-center">
        <h1><strong>Registro</strong></h1>
        <br>
        <h2><strong>Ingresa tus datos: </strong></h2>
    </div>
    <br>
    <br>
    <div class="row">

        <form action="RegisterServlet" method="POST">
            <section class="container">
                <label for="correo" class="form-label">Correo</label>
                <input type="text" class="form-control" name="correo" id="correo" placeholder="Correo" aria-label="correo" required>
                <br>

                <label for="clave" class="form-label">Password</label>
                <input type="password" class="form-control" name="clave" id="clave" placeholder="Password" aria-label="clave" required>
                <br>

                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" aria-label="nombre" required>
                <br>

                <label for="apodo" class="form-label">Nick</label>
                <input type="text" class="form-control" name="apodo" id="apodo" placeholder="apodo" aria-label="apodo" required>
                <br>

                <label for="peso" class="form-label">Peso</label>
                <input type="number" class="form-control" name="peso" id="peso" placeholder="Peso" aria-label="Peso" required>
                <br>
            </section>

            <section class="container">
                <div class="row">
                    <div class="col">
                        <label for="direccion" class="form-label">Dirección</label>
                        <input type="text" class="form-control" name="direccion" id="direccion" placeholder="Dirección" aria-label="direccion" required>
                        <br>
                    </div>
                    <div class="col">
                        <label for="num" class="form-label">Número</label>
                        <input type="text" class="form-control" name="num" id="num" placeholder="N°" aria-label="num" required>
                        <br>
                    </div>
                </div>
            </section>

            <section class="container">
                <label for="nombreAuto" class="form-label">Auto</label>
                <input type="text" class="form-control" name="nombreAuto" id="nombreAuto" placeholder="Marca" aria-label="Marca" required>
                <br>

                <label for="urlAuto" class="form-label">URL Auto</label>
                <input type="text" class="form-control" name="urlAuto" id="urlAuto" placeholder="Imagen del auto" aria-label="UrlAuto" required>
                <br>

                <label for="nombreProveedor" class="form-label">Nombre Proveedor</label>
                <input type="text" class="form-control" name="nombreProveedor" id="nombreProveedor" placeholder="Proveedor" aria-label="Proveedor" required>
                <br>
            </section>
            <section class="container">
                <select class="form-select" aria-label="SeleccionRol" name="rol" required>
                    <option value="admin">Administrador</option>
                    <option value="user">Usuario</option>
                </select>
            </section>
            <br>
            <div class="container text-center">
                <button type="submit" class="btn btn-warning btn-lg" value="insertUser">Registrar</button>
            </div>
            <br>
        </form>
    </div>
</section>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous">
</script>
</body>
</html>
