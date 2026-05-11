<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!--
    error.jsp - Custom Error Page
    Handles 404 and 500 errors gracefully.
    
    @author Vivek
    @version 1.0
-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - DL Verify</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar">
        <div class="navbar-inner">
            <a href="index.jsp" class="navbar-brand">
                <div class="logo-icon">&#128663;</div>
                <span>DL Verify</span>
            </a>
            <ul class="navbar-links">
                <li><a href="index.jsp">Home</a></li>
            </ul>
        </div>
    </nav>
    <main class="main-content">
        <div class="card">
            <div class="card-body error-content">
                <div class="error-code">
                    <%= response.getStatus() %>
                </div>
                <h2>Oops! Something went wrong.</h2>
                <p>The page you are looking for might not exist or an internal error occurred.</p>
                <a href="index.jsp" class="btn btn-primary" style="max-width:280px;margin:0 auto;">
                    &#127968; Go to Home
                </a>
            </div>
        </div>
    </main>
    <footer class="footer">
        &copy; 2026 Driving License Verification System
    </footer>
</body>
</html>
