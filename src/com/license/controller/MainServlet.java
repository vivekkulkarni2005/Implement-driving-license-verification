/**
 * MainServlet.java - Controller Servlet
 * 
 * This servlet acts as the Controller in the MVC architecture.
 * It handles HTTP POST requests from the verification form,
 * collects form data, invokes the service layer for business logic,
 * and forwards the results to the appropriate JSP view.
 * 
 * Part of: Driving License Verification System
 * Package: com.license.controller
 * Architecture Layer: Controller (MVC)
 * 
 * URL Pattern: /verify (mapped in web.xml)
 * 
 * @author Vivek
 * @version 1.0
 */
package com.license.controller;

import com.license.model.License;
import com.license.service.LicenseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    // Serial version UID for serialization
    private static final long serialVersionUID = 1L;

    /** Service layer instance for business logic operations */
    private LicenseService licenseService;

    // ========== Servlet Lifecycle Methods ==========

    /**
     * Initializes the servlet by creating a LicenseService instance.
     * Called once when the servlet is first loaded by the container.
     * 
     * @throws ServletException if initialization fails
     */
    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize the service layer
        licenseService = new LicenseService();
        System.out.println("[MainServlet] Servlet initialized successfully.");
    }

    // ========== HTTP Request Handlers ==========

    /**
     * Handles HTTP GET requests.
     * Redirects to the home page if accessed directly via GET.
     * 
     * @param request  The HTTP request object
     * @param response The HTTP response object
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET requests to the home page
        response.sendRedirect("index.jsp");
    }

    /**
     * Handles HTTP POST requests from the license verification form.
     * This is the main controller method that orchestrates the verification process.
     * 
     * Process Flow:
     * 1. Set character encoding for proper text handling
     * 2. Collect form data (license number, holder name)
     * 3. Validate input data
     * 4. Call service layer for verification
     * 5. Set result attributes for the view
     * 6. Forward to verify.jsp for result display
     * 
     * @param request  The HTTP request object containing form data
     * @param response The HTTP response object
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Set request encoding for proper character handling
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("[MainServlet] Processing verification request...");

        // Step 2: Collect form data from the request
        String licenseNumber = request.getParameter("licenseNumber");
        String holderName = request.getParameter("holderName");

        // Trim whitespace from inputs
        if (licenseNumber != null) {
            licenseNumber = licenseNumber.trim().toUpperCase();
        }
        if (holderName != null) {
            holderName = holderName.trim();
        }

        System.out.println("[MainServlet] License Number: " + licenseNumber);
        System.out.println("[MainServlet] Holder Name: " + holderName);

        // Step 3: Input validation
        // Check if license number is empty
        if (licenseNumber == null || licenseNumber.isEmpty()) {
            request.setAttribute("errorMessage", "Please enter a valid license number.");
            request.setAttribute("verificationStatus", "ERROR");
            request.getRequestDispatcher("verify.jsp").forward(request, response);
            return;
        }

        // Validate license number format
        if (!licenseService.validateLicenseFormat(licenseNumber)) {
            request.setAttribute("errorMessage",
                "Invalid license number format. Expected format: XX-NNXXXXXXXXXX (e.g., DL-0420110012345)");
            request.setAttribute("verificationStatus", "INVALID_FORMAT");
            request.getRequestDispatcher("verify.jsp").forward(request, response);
            return;
        }

        // Validate holder name format (if provided)
        if (!licenseService.validateHolderName(holderName)) {
            request.setAttribute("errorMessage",
                "Invalid holder name format. Name should contain only letters, spaces, and periods.");
            request.setAttribute("verificationStatus", "ERROR");
            request.getRequestDispatcher("verify.jsp").forward(request, response);
            return;
        }

        // Step 4: Call service layer for verification
        String verificationStatus = licenseService.getVerificationStatus(licenseNumber, holderName);
        License license = licenseService.verifyLicense(licenseNumber);

        // Step 5: Set result attributes for the view
        request.setAttribute("verificationStatus", verificationStatus);
        request.setAttribute("inputLicenseNumber", licenseNumber);
        request.setAttribute("inputHolderName", holderName);

        // Set specific attributes based on verification result
        switch (verificationStatus) {
            case "VALID":
                request.setAttribute("license", license);
                request.setAttribute("statusMessage", "License Verified Successfully!");
                request.setAttribute("statusClass", "success");
                long daysRemaining = licenseService.getDaysUntilExpiry(license);
                request.setAttribute("daysRemaining", daysRemaining);
                System.out.println("[MainServlet] License verified successfully.");
                break;

            case "EXPIRED":
                request.setAttribute("license", license);
                request.setAttribute("statusMessage", "License Expired!");
                request.setAttribute("statusClass", "expired");
                long daysExpired = Math.abs(licenseService.getDaysUntilExpiry(license));
                request.setAttribute("daysExpired", daysExpired);
                System.out.println("[MainServlet] License is expired.");
                break;

            case "SUSPENDED":
                request.setAttribute("license", license);
                request.setAttribute("statusMessage", "License Suspended!");
                request.setAttribute("statusClass", "suspended");
                System.out.println("[MainServlet] License is suspended.");
                break;

            case "REVOKED":
                request.setAttribute("license", license);
                request.setAttribute("statusMessage", "License Revoked!");
                request.setAttribute("statusClass", "revoked");
                System.out.println("[MainServlet] License is revoked.");
                break;

            case "NAME_MISMATCH":
                request.setAttribute("errorMessage",
                    "License number found but the holder name does not match our records. Please check the name and try again.");
                request.setAttribute("statusMessage", "Verification Failed - Name Mismatch");
                request.setAttribute("statusClass", "error");
                System.out.println("[MainServlet] Name mismatch detected.");
                break;

            case "NOT_FOUND":
                request.setAttribute("errorMessage",
                    "No license found with the number: " + licenseNumber + ". Please verify the number and try again.");
                request.setAttribute("statusMessage", "Invalid License!");
                request.setAttribute("statusClass", "error");
                System.out.println("[MainServlet] License not found in database.");
                break;

            default:
                request.setAttribute("errorMessage", "An unexpected error occurred during verification.");
                request.setAttribute("statusMessage", "Verification Error");
                request.setAttribute("statusClass", "error");
                System.out.println("[MainServlet] Unexpected verification status: " + verificationStatus);
                break;
        }

        // Step 6: Forward the request to verify.jsp (View)
        request.getRequestDispatcher("verify.jsp").forward(request, response);
        System.out.println("[MainServlet] Request forwarded to verify.jsp");
    }

    // ========== Servlet Cleanup ==========

    /**
     * Called when the servlet is being destroyed.
     * Performs cleanup operations.
     */
    @Override
    public void destroy() {
        super.destroy();
        licenseService = null;
        System.out.println("[MainServlet] Servlet destroyed.");
    }
}
