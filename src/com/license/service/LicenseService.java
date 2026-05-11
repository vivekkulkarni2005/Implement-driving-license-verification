/**
 * LicenseService.java - Service Layer Class
 * 
 * This class contains the business logic for driving license verification.
 * It manages a hardcoded sample database using HashMap and ArrayList,
 * and provides methods for license lookup, validation, and expiry checking.
 * 
 * Part of: Driving License Verification System
 * Package: com.license.service
 * Architecture Layer: Service / Business Logic (MVC)
 * 
 * OOP Concepts Used:
 *   - Encapsulation (private data, public methods)
 *   - Method Overloading (multiple verifyLicense methods)
 *   - Static initialization block
 * 
 * @author Vivek
 * @version 1.0
 */
package com.license.service;

import com.license.model.License;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LicenseService {

    // ========== Sample Database (HashMap as temporary storage) ==========

    /** HashMap storing license records with license number as the key */
    private static final Map<String, License> licenseDatabase = new HashMap<>();

    /** ArrayList maintaining an ordered list of all license numbers */
    private static final List<String> licenseNumberList = new ArrayList<>();

    /** Date formatter for parsing and formatting dates */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    // ========== Static Initialization Block ==========
    // Populates the sample database with hardcoded license records
    static {
        // Sample License 1 - Valid license (Two Wheeler)
        License license1 = new License(
            "DL-0420110012345",       // licenseNumber
            "Rahul Kumar Sharma",     // holderName
            "15-06-1995",             // dateOfBirth
            "Two Wheeler (MCWG)",     // vehicleType
            "10-03-2020",             // issueDate
            "09-03-2040",             // expiryDate
            "VALID",                  // status
            "RTO Delhi - Central",    // issuingAuthority
            "45, Connaught Place, New Delhi - 110001", // address
            "B+"                      // bloodGroup
        );

        // Sample License 2 - Valid license (Four Wheeler)
        License license2 = new License(
            "MH-0220180067890",       // licenseNumber
            "Priya Anand Deshmukh",   // holderName
            "22-11-1990",             // dateOfBirth
            "Four Wheeler (LMV)",     // vehicleType
            "15-07-2018",             // issueDate
            "14-07-2038",             // expiryDate
            "VALID",                  // status
            "RTO Mumbai - Andheri",   // issuingAuthority
            "12, Bandra West, Mumbai - 400050", // address
            "O+"                      // bloodGroup
        );

        // Sample License 3 - Expired license
        License license3 = new License(
            "KA-0120150034567",       // licenseNumber
            "Arun Venkatesh Reddy",   // holderName
            "08-04-1988",             // dateOfBirth
            "Four Wheeler (LMV)",     // vehicleType
            "20-01-2015",             // issueDate
            "19-01-2025",             // expiryDate
            "EXPIRED",                // status
            "RTO Bangalore - Koramangala", // issuingAuthority
            "78, Indiranagar, Bangalore - 560038", // address
            "A+"                      // bloodGroup
        );

        // Sample License 4 - Suspended license
        License license4 = new License(
            "TN-0920170089012",       // licenseNumber
            "Suresh Babu Krishnan",   // holderName
            "30-12-1992",             // dateOfBirth
            "Commercial Vehicle (HMV)", // vehicleType
            "05-06-2017",             // issueDate
            "04-06-2037",             // expiryDate
            "SUSPENDED",              // status
            "RTO Chennai - T. Nagar", // issuingAuthority
            "23, Anna Nagar, Chennai - 600040", // address
            "AB+"                     // bloodGroup
        );

        // Sample License 5 - Valid license (Two Wheeler + Four Wheeler)
        License license5 = new License(
            "UP-1520210045678",       // licenseNumber
            "Neha Gupta Singh",       // holderName
            "14-09-1997",             // dateOfBirth
            "Two Wheeler + Four Wheeler (MCWG + LMV)", // vehicleType
            "28-08-2021",             // issueDate
            "27-08-2041",             // expiryDate
            "VALID",                  // status
            "RTO Lucknow - Hazratganj", // issuingAuthority
            "56, Gomti Nagar, Lucknow - 226010", // address
            "O-"                      // bloodGroup
        );

        // Sample License 6 - Expired license (Commercial)
        License license6 = new License(
            "RJ-1420160023456",       // licenseNumber
            "Mahesh Chand Meena",     // holderName
            "25-03-1985",             // dateOfBirth
            "Commercial Vehicle (HMV + TRANS)", // vehicleType
            "12-05-2016",             // issueDate
            "11-05-2024",             // expiryDate
            "EXPIRED",                // status
            "RTO Jaipur - MI Road",   // issuingAuthority
            "34, Malviya Nagar, Jaipur - 302017", // address
            "B-"                      // bloodGroup
        );

        // Add all licenses to the HashMap database
        licenseDatabase.put(license1.getLicenseNumber(), license1);
        licenseDatabase.put(license2.getLicenseNumber(), license2);
        licenseDatabase.put(license3.getLicenseNumber(), license3);
        licenseDatabase.put(license4.getLicenseNumber(), license4);
        licenseDatabase.put(license5.getLicenseNumber(), license5);
        licenseDatabase.put(license6.getLicenseNumber(), license6);

        // Add all license numbers to the ArrayList
        licenseNumberList.add(license1.getLicenseNumber());
        licenseNumberList.add(license2.getLicenseNumber());
        licenseNumberList.add(license3.getLicenseNumber());
        licenseNumberList.add(license4.getLicenseNumber());
        licenseNumberList.add(license5.getLicenseNumber());
        licenseNumberList.add(license6.getLicenseNumber());
    }

    // ========== Verification Methods ==========

    /**
     * Verifies a license by its license number.
     * Searches the database and returns the License object if found.
     * 
     * @param licenseNumber The license number to search for
     * @return License object if found, null otherwise
     */
    public License verifyLicense(String licenseNumber) {
        // Search in the HashMap database
        if (licenseNumber != null && licenseDatabase.containsKey(licenseNumber.trim().toUpperCase())) {
            return licenseDatabase.get(licenseNumber.trim().toUpperCase());
        }
        return null;
    }

    /**
     * Verifies a license by license number and holder name (Method Overloading).
     * Provides additional verification by matching the holder's name.
     * 
     * @param licenseNumber The license number to search for
     * @param holderName    The name of the license holder to match
     * @return License object if found and name matches, null otherwise
     */
    public License verifyLicense(String licenseNumber, String holderName) {
        License license = verifyLicense(licenseNumber);

        // If license found and holder name is provided, verify name match
        if (license != null && holderName != null && !holderName.trim().isEmpty()) {
            if (!license.getHolderName().toLowerCase().contains(holderName.trim().toLowerCase())) {
                return null; // Name mismatch
            }
        }
        return license;
    }

    // ========== Validation Methods ==========

    /**
     * Validates the format of a license number.
     * Expected format: XX-NNXXXXXXXXXX (State code - digits)
     * Example: DL-0420110012345
     * 
     * @param licenseNumber The license number to validate
     * @return true if the format is valid, false otherwise
     */
    public boolean validateLicenseFormat(String licenseNumber) {
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            return false;
        }

        String trimmed = licenseNumber.trim().toUpperCase();

        // Check minimum length
        if (trimmed.length() < 10) {
            return false;
        }

        // Check format: starts with 2 letters, followed by a hyphen
        if (!Character.isLetter(trimmed.charAt(0)) || !Character.isLetter(trimmed.charAt(1))) {
            return false;
        }

        if (trimmed.charAt(2) != '-') {
            return false;
        }

        // Remaining characters after hyphen should be alphanumeric
        String remaining = trimmed.substring(3);
        for (char c : remaining.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Validates whether the holder name contains only valid characters.
     * 
     * @param holderName The name to validate
     * @return true if the name is valid or empty (optional field), false otherwise
     */
    public boolean validateHolderName(String holderName) {
        // Holder name is optional, so empty is valid
        if (holderName == null || holderName.trim().isEmpty()) {
            return true;
        }

        // Name should only contain letters, spaces, and periods
        return holderName.trim().matches("[a-zA-Z .]+");
    }

    // ========== Expiry Checking Methods ==========

    /**
     * Checks whether a license has expired based on its expiry date.
     * Compares the expiry date with the current system date.
     * 
     * @param license The License object to check
     * @return true if the license has expired, false otherwise
     */
    public boolean isLicenseExpired(License license) {
        if (license == null || license.getExpiryDate() == null) {
            return true; // Treat null as expired for safety
        }

        try {
            Date expiryDate = dateFormat.parse(license.getExpiryDate());
            Date currentDate = new Date();
            return expiryDate.before(currentDate);
        } catch (ParseException e) {
            System.err.println("Error parsing expiry date: " + e.getMessage());
            return true; // Treat parse error as expired for safety
        }
    }

    /**
     * Checks whether a license has expired based on its expiry date string.
     * Method overloading - accepts date string directly.
     * 
     * @param expiryDateStr The expiry date string in dd-MM-yyyy format
     * @return true if the license has expired, false otherwise
     */
    public boolean isLicenseExpired(String expiryDateStr) {
        if (expiryDateStr == null || expiryDateStr.trim().isEmpty()) {
            return true;
        }

        try {
            Date expiryDate = dateFormat.parse(expiryDateStr.trim());
            Date currentDate = new Date();
            return expiryDate.before(currentDate);
        } catch (ParseException e) {
            System.err.println("Error parsing expiry date: " + e.getMessage());
            return true;
        }
    }

    /**
     * Gets the number of days remaining until the license expires.
     * Returns a negative value if the license has already expired.
     * 
     * @param license The License object to check
     * @return Number of days until expiry (negative if expired)
     */
    public long getDaysUntilExpiry(License license) {
        if (license == null || license.getExpiryDate() == null) {
            return -1;
        }

        try {
            Date expiryDate = dateFormat.parse(license.getExpiryDate());
            Date currentDate = new Date();
            long diffMillis = expiryDate.getTime() - currentDate.getTime();
            return diffMillis / (1000 * 60 * 60 * 24); // Convert ms to days
        } catch (ParseException e) {
            System.err.println("Error parsing expiry date: " + e.getMessage());
            return -1;
        }
    }

    // ========== Status Methods ==========

    /**
     * Determines the comprehensive verification status of a license.
     * Checks existence, format validity, expiry, and suspension status.
     * 
     * @param licenseNumber The license number to verify
     * @param holderName    The optional holder name for additional verification
     * @return A status message string describing the verification result
     */
    public String getVerificationStatus(String licenseNumber, String holderName) {
        // Step 1: Validate format
        if (!validateLicenseFormat(licenseNumber)) {
            return "INVALID_FORMAT";
        }

        // Step 2: Search in database
        License license = verifyLicense(licenseNumber, holderName);
        if (license == null) {
            // Check if license exists but name doesn't match
            License licenseOnly = verifyLicense(licenseNumber);
            if (licenseOnly != null && holderName != null && !holderName.trim().isEmpty()) {
                return "NAME_MISMATCH";
            }
            return "NOT_FOUND";
        }

        // Step 3: Check suspension/revocation
        if ("SUSPENDED".equalsIgnoreCase(license.getStatus())) {
            return "SUSPENDED";
        }
        if ("REVOKED".equalsIgnoreCase(license.getStatus())) {
            return "REVOKED";
        }

        // Step 4: Check expiry
        if (isLicenseExpired(license)) {
            return "EXPIRED";
        }

        // Step 5: All checks passed
        return "VALID";
    }

    /**
     * Gets all license numbers stored in the database.
     * 
     * @return List of all license number strings
     */
    public List<String> getAllLicenseNumbers() {
        return new ArrayList<>(licenseNumberList);
    }

    /**
     * Gets the total count of licenses in the database.
     * 
     * @return Total number of licenses
     */
    public int getTotalLicenseCount() {
        return licenseDatabase.size();
    }
}
