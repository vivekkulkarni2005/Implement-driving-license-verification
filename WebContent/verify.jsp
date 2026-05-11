<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.license.model.License" %>
<!--
    verify.jsp - Verification Results Page
    
    Displays the results of the license verification process.
    Shows license details, status indicators, and error messages.
    
    Part of: Driving License Verification System
    Architecture Layer: View (MVC)
    
    @author Vivek
    @version 1.0
-->
<%
    // Retrieve attributes set by MainServlet
    String verificationStatus = (String) request.getAttribute("verificationStatus");
    String statusMessage = (String) request.getAttribute("statusMessage");
    String statusClass = (String) request.getAttribute("statusClass");
    String errorMessage = (String) request.getAttribute("errorMessage");
    License license = (License) request.getAttribute("license");
    String inputLicenseNumber = (String) request.getAttribute("inputLicenseNumber");

    Long daysRemaining = (Long) request.getAttribute("daysRemaining");
    Long daysExpired = (Long) request.getAttribute("daysExpired");

    // Redirect to home if accessed directly without form submission
    if (verificationStatus == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    // Determine status icon
    String statusIcon = "&#10060;"; // default: error cross
    if ("success".equals(statusClass)) statusIcon = "&#9989;";       // green check
    else if ("expired".equals(statusClass)) statusIcon = "&#9888;";  // warning
    else if ("suspended".equals(statusClass)) statusIcon = "&#128683;"; // no entry
    else if ("revoked".equals(statusClass)) statusIcon = "&#128694;"; // prohibited

    // Determine badge class
    String badgeClass = "badge-error";
    if ("VALID".equals(verificationStatus)) badgeClass = "badge-valid";
    else if ("EXPIRED".equals(verificationStatus)) badgeClass = "badge-expired";
    else if ("SUSPENDED".equals(verificationStatus)) badgeClass = "badge-suspended";
    else if ("REVOKED".equals(verificationStatus)) badgeClass = "badge-revoked";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verification Result - DL Verify</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <!-- ========== Navigation Bar ========== -->
    <nav class="navbar">
        <div class="navbar-inner">
            <a href="index.jsp" class="navbar-brand">
                <div class="logo-icon">&#128663;</div>
                <span>DL Verify</span>
            </a>
            <ul class="navbar-links">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="#" onclick="alert('About: Driving License Verification System v1.0')">About</a></li>
                <li><a href="#" onclick="alert('Contact: support@dlverify.gov.in')">Contact</a></li>
            </ul>
        </div>
    </nav>

    <!-- ========== Main Content ========== -->
    <main class="main-content">
        <div class="card card-wide">
            <!-- Card Header -->
            <div class="card-header">
                <h1>&#128196; Verification Result</h1>
                <p>License Number: <strong><%= inputLicenseNumber != null ? inputLicenseNumber : "N/A" %></strong></p>
            </div>

            <div class="card-body">
                <!-- Status Banner -->
                <div class="result-status <%= statusClass != null ? statusClass : "error" %>">
                    <div class="status-icon"><%= statusIcon %></div>
                    <h2><%= statusMessage != null ? statusMessage : "Verification Error" %></h2>
                    <% if ("success".equals(statusClass) && daysRemaining != null) { %>
                        <p>License valid for approximately <strong><%= daysRemaining %></strong> more days</p>
                    <% } else if ("expired".equals(statusClass) && daysExpired != null) { %>
                        <p>License expired approximately <strong><%= daysExpired %></strong> days ago</p>
                    <% } else if ("suspended".equals(statusClass)) { %>
                        <p>This license has been suspended by the issuing authority</p>
                    <% } %>
                </div>

                <!-- Error Message (if any) -->
                <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
                    <div class="result-status error" style="margin-bottom:1.5rem;">
                        <p style="font-size:0.95rem;"><%= errorMessage %></p>
                    </div>
                <% } %>

                <!-- License Details (shown only if license was found) -->
                <% if (license != null) { %>
                    <table class="details-table">
                        <tr>
                            <th>License Number</th>
                            <td><%= license.getLicenseNumber() %></td>
                        </tr>
                        <tr>
                            <th>Holder Name</th>
                            <td><%= license.getHolderName() %></td>
                        </tr>
                        <tr>
                            <th>Date of Birth</th>
                            <td><%= license.getDateOfBirth() != null ? license.getDateOfBirth() : "N/A" %></td>
                        </tr>
                        <tr>
                            <th>Vehicle Type</th>
                            <td><%= license.getVehicleType() %></td>
                        </tr>
                        <tr>
                            <th>Issue Date</th>
                            <td><%= license.getIssueDate() %></td>
                        </tr>
                        <tr>
                            <th>Expiry Date</th>
                            <td><%= license.getExpiryDate() %></td>
                        </tr>
                        <tr>
                            <th>Status</th>
                            <td><span class="badge <%= badgeClass %>"><%= license.getStatus() %></span></td>
                        </tr>
                        <tr>
                            <th>Issuing Authority</th>
                            <td><%= license.getIssuingAuthority() != null ? license.getIssuingAuthority() : "N/A" %></td>
                        </tr>
                        <tr>
                            <th>Address</th>
                            <td><%= license.getAddress() != null ? license.getAddress() : "N/A" %></td>
                        </tr>
                        <tr>
                            <th>Blood Group</th>
                            <td><%= license.getBloodGroup() != null ? license.getBloodGroup() : "N/A" %></td>
                        </tr>
                    </table>
                <% } %>

                <!-- Action Buttons -->
                <div class="btn-group">
                    <a href="index.jsp" class="btn btn-primary">&#128269; Verify Another</a>
                    <a href="javascript:window.print()" class="btn btn-secondary">&#128424; Print Result</a>
                </div>
            </div>
        </div>
    </main>

    <!-- ========== Footer ========== -->
    <footer class="footer">
        &copy; 2026 Driving License Verification System | Built with Java, JSP, Servlets &amp; MVC Architecture
    </footer>

</body>
</html>
