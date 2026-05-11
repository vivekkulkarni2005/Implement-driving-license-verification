/**
 * script.js - Client-Side JavaScript
 * Driving License Verification System
 * Handles form validation, sample license autofill, and UI interactions.
 */

/**
 * Validates the license verification form before submission.
 * Checks license number format and holder name validity.
 * @returns {boolean} true if form is valid, false otherwise
 */
function validateForm() {
    var licenseNumber = document.getElementById('licenseNumber').value.trim();
    var holderName = document.getElementById('holderName').value.trim();
    var isValid = true;

    // Clear previous error styles
    clearErrors();

    // Validate license number - required field
    if (licenseNumber === '') {
        showError('licenseNumber', 'licenseError', 'Please enter a license number.');
        isValid = false;
    } else if (!validateLicenseFormat(licenseNumber)) {
        showError('licenseNumber', 'licenseError', 'Invalid format. Use: XX-NNXXXXXXXXXX (e.g., DL-0420110012345)');
        isValid = false;
    }

    // Validate holder name - optional but must be valid if provided
    if (holderName !== '' && !/^[a-zA-Z .\-]+$/.test(holderName)) {
        showError('holderName', 'nameError', 'Name should only contain letters, spaces, and periods.');
        isValid = false;
    }

    // Show loading state on button if valid
    if (isValid) {
        var btn = document.getElementById('verifyBtn');
        btn.classList.add('loading');
    }

    return isValid;
}

/**
 * Validates license number format on the client side.
 * Expected: 2 letters + hyphen + 10+ alphanumeric characters
 * @param {string} license - The license number to validate
 * @returns {boolean} true if format is valid
 */
function validateLicenseFormat(license) {
    var pattern = /^[A-Za-z]{2}-[A-Za-z0-9]{10,}$/;
    return pattern.test(license);
}

/**
 * Displays an error message for a specific form field.
 * @param {string} fieldId - The ID of the input field
 * @param {string} errorId - The ID of the error message span
 * @param {string} message - The error message to display
 */
function showError(fieldId, errorId, message) {
    var field = document.getElementById(fieldId);
    var errorSpan = document.getElementById(errorId);
    if (field) field.classList.add('error');
    if (errorSpan) {
        errorSpan.textContent = message;
        errorSpan.style.display = 'block';
    }
}

/**
 * Clears all error styles and messages from the form.
 */
function clearErrors() {
    var errorFields = document.querySelectorAll('.form-control.error');
    errorFields.forEach(function(field) { field.classList.remove('error'); });

    var errorMessages = document.querySelectorAll('.error-message');
    errorMessages.forEach(function(msg) {
        msg.textContent = '';
        msg.style.display = 'none';
    });
}

/**
 * Fills the license number field with a sample value when a chip is clicked.
 * @param {string} licenseNumber - The sample license number to fill
 */
function fillSample(licenseNumber) {
    var field = document.getElementById('licenseNumber');
    if (field) {
        field.value = licenseNumber;
        field.focus();
        clearErrors();
        // Add a brief highlight animation
        field.style.borderColor = '#4f46e5';
        field.style.boxShadow = '0 0 0 4px rgba(79, 70, 229, 0.12)';
        setTimeout(function() {
            field.style.borderColor = '';
            field.style.boxShadow = '';
        }, 1500);
    }
}

/**
 * Converts the license number input to uppercase as the user types.
 */
function toUpperCaseInput(el) {
    el.value = el.value.toUpperCase();
}
