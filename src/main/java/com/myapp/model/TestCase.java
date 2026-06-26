package com.myapp.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Represents a single API test case parsed from the uploaded JSON file.
 *
 * This POJO is intentionally flexible so you can express various validations:
 * - status code validation
 * - JSON field existence
 * - value comparison
 * - partial match
 * - ignored dynamic fields (token, timestamp, id, etc.)
 * - regex-based validation (email, UUID, phone, etc.)
 */
public class TestCase {

    private String testName;
    private String method;            // GET, POST, PUT, DELETE
    private String endpoint;          // Full URL

    private Map<String, String> headers; // Optional request headers
    private Object body;                // Optional request body (JSON object/array/primitive)

    private Integer expectedStatusCode; // Optional (if null, status code validation is skipped)

    /**
     * JSON field existence validation: list of JSON paths that must exist in the response.
     * Example: ["data.id", "data.email"]
     */
    private List<String> expectedFields;

    /**
     * Exact value comparisons (JSON path -> expected value).
     * Example: {"data.role":"admin", "success": true}
     */
    private Map<String, Object> expectedValues;

    /**
     * Partial match validations (JSON path -> expected substring).
     * Example: {"message":"created"}
     */
    private Map<String, String> partialMatches;

    /**
     * Fields to ignore during comparison/validation (JSON paths or leaf keys).
     * Example: ["token", "timestamp", "data.id"]
     */
    private List<String> ignoredFields;

    /**
     * Regex validations (JSON path -> regex pattern).
     * Example: {"data.email":"^[^@]+@[^@]+\\.[^@]+$", "data.uuid":"^[0-9a-fA-F-]{36}$"}
     */
    private Map<String, String> regexValidations;

    // -------- Getters / Setters --------

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

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Integer getExpectedStatusCode() {
        return expectedStatusCode;
    }

    public void setExpectedStatusCode(Integer expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode;
    }

    public List<String> getExpectedFields() {
        return expectedFields == null ? Collections.<String>emptyList() : expectedFields;
    }

    public void setExpectedFields(List<String> expectedFields) {
        this.expectedFields = expectedFields;
    }

    public Map<String, Object> getExpectedValues() {
        return expectedValues == null ? Collections.<String, Object>emptyMap() : expectedValues;
    }

    public void setExpectedValues(Map<String, Object> expectedValues) {
        this.expectedValues = expectedValues;
    }

    public Map<String, String> getPartialMatches() {
        return partialMatches == null ? Collections.<String, String>emptyMap() : partialMatches;
    }

    public void setPartialMatches(Map<String, String> partialMatches) {
        this.partialMatches = partialMatches;
    }

    public List<String> getIgnoredFields() {
        return ignoredFields == null ? Collections.<String>emptyList() : ignoredFields;
    }

    public void setIgnoredFields(List<String> ignoredFields) {
        this.ignoredFields = ignoredFields;
    }

    public Map<String, String> getRegexValidations() {
        return regexValidations == null ? Collections.<String, String>emptyMap() : regexValidations;
    }

    public void setRegexValidations(Map<String, String> regexValidations) {
        this.regexValidations = regexValidations;
    }
}


