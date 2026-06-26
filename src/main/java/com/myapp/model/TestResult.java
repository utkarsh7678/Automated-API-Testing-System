package com.myapp.model;

/**
 * Represents the result of executing and validating a single TestCase.
 * This is what gets returned to the controller (Servlet) and forwarded to the JSP.
 */
public class TestResult {

    private String testName;
    private String method;
    private String endpoint;

    private int statusCode;
    private Integer expectedStatusCode;

    private boolean passed;
    private String failureReason; // Non-null if failed

    public TestResult() {
    }

    public TestResult(String testName, String method, String endpoint) {
        this.testName = testName;
        this.method = method;
        this.endpoint = endpoint;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getExpectedStatusCode() {
        return expectedStatusCode;
    }

    public void setExpectedStatusCode(Integer expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}


