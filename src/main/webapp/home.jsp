<%--
  Created by IntelliJ IDEA.
  User: matia
  Date: 16-07-2024
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="es" data-bs-theme="dark">
<head>
    <title>Inicio - Administrador</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<%@include file="assets/components/navbar.jsp"%>

<section class="container">
    <br>
    <h1><strong>Bienvenido al Sistema</strong></h1>
    <br>
    <div class="mb-4">
        <p>Nombre de usuario: ${user.getName()}</p>
        <p>Correo electrónico: ${user.getEmail()}</p>
        <hr>
    </div>
    <div>
        <hr>
        <h3 class="text-center">Lista de Usuarios:</h3>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Correo Electrónico</th>
                <th>Nick</th>
                <th>Peso</th>
                <th>Fecha de Creación</th>
                <th>Fecha de Actualización</th>
                <th>ID Auto</th>
                <th>Nombre Auto</th>
                <th>URL</th>
                <th>ID del Proveedor</th>
                <th>Nombre Proveedor</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.getId()}</td>
                    <td>${user.getName()}</td>
                    <td>${user.getEmail()}</td>
                    <td>${user.getNick()}</td>
                    <td>${user.getWeight()}</td>
                    <td>${user.getCreatedAt()}</td>
                    <td>${user.getUpdatedAt()}</td>
                    <c:forEach var="car" items="${cars}">
                        <c:if test="${car.getIdCar() eq user.getIdAuto()}">
                            <td>${car.getIdCar()}</td>
                            <td>${car.getCarName()}</td>
                            <td>${car.getUrl()}</td>
                            <td>${car.getIdProvider()}</td>
                            <c:forEach var="provider" items="${providers}">
                                <c:if test="${provider.getIdProviders() eq car.getIdProvider()}">
                                    <td>${provider.getProvidersName()}</td>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <br>
    <div class="container text-center">
        <a href="index.jsp">Volver</a>
    </div>

</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
