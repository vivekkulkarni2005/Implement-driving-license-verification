/**
 * License.java - JavaBean Model Class
 * 
 * This class represents a Driving License entity following the JavaBean convention.
 * It uses encapsulation with private fields, a parameterized constructor,
 * and public getter/setter methods.
 * 
 * Part of: Driving License Verification System
 * Package: com.license.model
 * Architecture Layer: Model (MVC)
 * 
 * @author Vivek
 * @version 1.0
 */
package com.license.model;

import java.io.Serializable;

public class License implements Serializable {

    // Serial version UID for serialization compatibility
    private static final long serialVersionUID = 1L;

    // ========== Private Instance Variables (Encapsulation) ==========

    /** Unique driving license number (e.g., DL-1234567890) */
    private String licenseNumber;

    /** Full name of the license holder */
    private String holderName;

    /** Date of birth of the license holder */
    private String dateOfBirth;

    /** Type of vehicle the license permits (e.g., Two Wheeler, Four Wheeler, Commercial) */
    private String vehicleType;

    /** Date when the license was issued */
    private String issueDate;

    /** Date when the license expires */
    private String expiryDate;

    /** Current status of the license: VALID, EXPIRED, SUSPENDED, or REVOKED */
    private String status;

    /** Issuing authority / Regional Transport Office */
    private String issuingAuthority;

    /** Address of the license holder */
    private String address;

    /** Blood group of the license holder */
    private String bloodGroup;

    // ========== Constructors ==========

    /**
     * Default no-argument constructor.
     * Required for JavaBean specification compliance.
     */
    public License() {
        // Default constructor
    }

    /**
     * Parameterized constructor with essential fields.
     * 
     * @param licenseNumber   The unique license number
     * @param holderName      The name of the license holder
     * @param vehicleType     The type of vehicle permitted
     * @param issueDate       The date the license was issued
     * @param expiryDate      The date the license expires
     * @param status          The current status of the license
     */
    public License(String licenseNumber, String holderName, String vehicleType,
                   String issueDate, String expiryDate, String status) {
        this.licenseNumber = licenseNumber;
        this.holderName = holderName;
        this.vehicleType = vehicleType;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    /**
     * Fully parameterized constructor with all fields.
     * Demonstrates constructor overloading.
     * 
     * @param licenseNumber    The unique license number
     * @param holderName       The name of the license holder
     * @param dateOfBirth      The date of birth of the holder
     * @param vehicleType      The type of vehicle permitted
     * @param issueDate        The date the license was issued
     * @param expiryDate       The date the license expires
     * @param status           The current status of the license
     * @param issuingAuthority The RTO that issued the license
     * @param address          The address of the holder
     * @param bloodGroup       The blood group of the holder
     */
    public License(String licenseNumber, String holderName, String dateOfBirth,
                   String vehicleType, String issueDate, String expiryDate,
                   String status, String issuingAuthority, String address,
                   String bloodGroup) {
        this.licenseNumber = licenseNumber;
        this.holderName = holderName;
        this.dateOfBirth = dateOfBirth;
        this.vehicleType = vehicleType;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.status = status;
        this.issuingAuthority = issuingAuthority;
        this.address = address;
        this.bloodGroup = bloodGroup;
    }

    // ========== Getter and Setter Methods ==========

    /**
     * Gets the license number.
     * @return the license number
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * Sets the license number.
     * @param licenseNumber the license number to set
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * Gets the holder name.
     * @return the holder name
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * Sets the holder name.
     * @param holderName the holder name to set
     */
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    /**
     * Gets the date of birth.
     * @return the date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth.
     * @param dateOfBirth the date of birth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the vehicle type.
     * @return the vehicle type
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Sets the vehicle type.
     * @param vehicleType the vehicle type to set
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Gets the issue date.
     * @return the issue date
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the issue date.
     * @param issueDate the issue date to set
     */
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Gets the expiry date.
     * @return the expiry date
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date.
     * @param expiryDate the expiry date to set
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the license status.
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the license status.
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the issuing authority.
     * @return the issuing authority
     */
    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    /**
     * Sets the issuing authority.
     * @param issuingAuthority the issuing authority to set
     */
    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    /**
     * Gets the address.
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address.
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the blood group.
     * @return the blood group
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * Sets the blood group.
     * @param bloodGroup the blood group to set
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    // ========== Utility Methods ==========

    /**
     * Returns a string representation of the License object.
     * Useful for debugging and logging.
     * 
     * @return formatted string with all license details
     */
    @Override
    public String toString() {
        return "License [licenseNumber=" + licenseNumber
                + ", holderName=" + holderName
                + ", vehicleType=" + vehicleType
                + ", issueDate=" + issueDate
                + ", expiryDate=" + expiryDate
                + ", status=" + status + "]";
    }
}
