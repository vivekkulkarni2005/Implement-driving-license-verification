<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--
    index.jsp - Home Page / License Verification Form
    
    This is the main entry point of the application.
    It displays a professional verification form where users
    can enter their driving license number for verification.
    
    Part of: Driving License Verification System
    Architecture Layer: View (MVC)
    
    @author Vivek
    @version 1.0
-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Verify your driving license details, check validity and expiry status online.">
    <title>Driving License Verification System</title>
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
                <li><a href="index.jsp" class="active">Home</a></li>
                <li><a href="#" onclick="alert('About: Driving License Verification System v1.0\nBuilt with Java, JSP, Servlets & MVC Architecture.')">About</a></li>
                <li><a href="#" onclick="alert('Contact: support@dlverify.gov.in')">Contact</a></li>
            </ul>
        </div>
    </nav>

    <!-- ========== Main Content ========== -->
    <main class="main-content">
        <div class="card">
            <!-- Card Header -->
            <div class="card-header">
                <h1>&#128275; License Verification</h1>
                <p>Enter your driving license number to verify its authenticity and status</p>
            </div>

            <!-- Card Body - Verification Form -->
            <div class="card-body">
                <form action="verify" method="POST" onsubmit="return validateForm()">

                    <!-- License Number Field (Required) -->
                    <div class="form-group">
                        <label for="licenseNumber">
                            Driving License Number <span class="required">*</span>
                        </label>
                        <input type="text" id="licenseNumber" name="licenseNumber"
                               class="form-control" placeholder="e.g., DL-0420110012345"
                               maxlength="20" oninput="toUpperCaseInput(this)" required>
                        <span id="licenseError" class="error-message" style="display:none; color:#dc2626; font-size:0.82rem; margin-top:4px;"></span>
                        <p class="form-hint">Format: State Code - License Digits (e.g., DL-0420110012345)</p>
                    </div>

                    <!-- Holder Name Field (Optional) -->
                    <div class="form-group">
                        <label for="holderName">
                            Holder Name <span class="optional">(Optional)</span>
                        </label>
                        <input type="text" id="holderName" name="holderName"
                               class="form-control" placeholder="e.g., Rahul Kumar Sharma"
                               maxlength="100">
                        <span id="nameError" class="error-message" style="display:none; color:#dc2626; font-size:0.82rem; margin-top:4px;"></span>
                        <p class="form-hint">Enter holder name for additional verification (optional)</p>
                    </div>

                    <!-- Submit Button -->
                    <button type="submit" id="verifyBtn" class="btn btn-primary">
                        <span class="btn-text">&#128269; Verify License</span>
                        <div class="spinner"></div>
                    </button>
                </form>

                <!-- Sample License Numbers for Testing -->
                <div class="sample-section">
                    <h3>&#128203; Sample License Numbers for Testing</h3>
                    <div class="sample-list">
                        <div class="sample-chip" onclick="fillSample('DL-0420110012345')">
                            DL-0420110012345
                            <span class="chip-status">Valid</span>
                        </div>
                        <div class="sample-chip" onclick="fillSample('MH-0220180067890')">
                            MH-0220180067890
                            <span class="chip-status">Valid</span>
                        </div>
                        <div class="sample-chip" onclick="fillSample('KA-0120150034567')">
                            KA-0120150034567
                            <span class="chip-status">Expired</span>
                        </div>
                        <div class="sample-chip" onclick="fillSample('TN-0920170089012')">
                            TN-0920170089012
                            <span class="chip-status">Suspended</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <!-- ========== Footer ========== -->
    <footer class="footer">
        &copy; 2026 Driving License Verification System | Built with Java, JSP, Servlets &amp; MVC Architecture
    </footer>

    <!-- JavaScript -->
    <script src="js/script.js"></script>

</body>
</html>
