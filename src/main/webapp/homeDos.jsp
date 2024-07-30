<%--
  Created by IntelliJ IDEA.
  User: matia
  Date: 29-07-2024
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="es" data-bs-theme="dark">
<head>
    <title>Inicio - Usuario</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<%@include file="assets/components/navbar.jsp"%>
<section class="container">
    <h1><strong>Bienvenido al Sistema</strong></h1>
    <br>
    <div class="mb-3">
        <p>Nombre de usuario: ${user.getName()}</p>
        <p>Correo electrónico: ${user.getEmail()}</p>
        <p>Nick: ${user.getNick()}</p>
    </div>
    <a href="index.jsp">Volver</a>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
