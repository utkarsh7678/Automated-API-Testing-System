package com.myapp.validator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myapp.engine.ApiResponse;
import com.myapp.model.TestCase;
import com.myapp.model.TestResult;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ResponseValidator {

    // Fields that change dynamically
    private static final List<String> IGNORE_FIELDS =
            Arrays.asList("token", "timestamp", "id");

    public TestResult validate(TestCase testCase, ApiResponse response) {

        TestResult result = new TestResult();
        result.setTestName(testCase.getTestName());
        result.setMethod(testCase.getMethod());
        result.setEndpoint(testCase.getEndpoint());
        result.setStatusCode(response.getStatusCode());
        result.setExpectedStatusCode(testCase.getExpectedStatusCode());

        /* 1️⃣ Status Code Validation */
        if (testCase.getExpectedStatusCode() != null &&
            response.getStatusCode() != testCase.getExpectedStatusCode()) {
            result.setPassed(false);
            result.setFailureReason(
                    "Expected status " + testCase.getExpectedStatusCode() +
                    " but got " + response.getStatusCode()
            );
            return result;
        }

        /* 2️⃣ If no expected fields, mark PASS */
        if ((testCase.getExpectedFields() == null || testCase.getExpectedFields().isEmpty()) &&
            (testCase.getRegexValidations() == null || testCase.getRegexValidations().isEmpty())) {

            result.setPassed(true);
            result.setFailureReason(null);
            return result;
        }

        /* 3️⃣ Parse response JSON */
        JsonObject actualJson;
        try {
            JsonElement element =
                    JsonParser.parseString(response.getResponseBody());
            actualJson = element.getAsJsonObject();
        } catch (Exception e) {
            result.setPassed(false);
            result.setFailureReason("Invalid JSON response: " + e.getMessage());
            return result;
        }

        /* 4️⃣ Validate expected fields exist */
        for (String field : testCase.getExpectedFields()) {
            if (IGNORE_FIELDS.contains(field)) {
                continue;
            }

            if (!actualJson.has(field)) {
                result.setPassed(false);
                result.setFailureReason("Missing field: " + field);
                return result;
            }
        }

        /* 5️⃣ Validate regex patterns */
        for (Map.Entry<String, String> entry :
                testCase.getRegexValidations().entrySet()) {

            String field = entry.getKey();
            String expectedPattern = entry.getValue();

            if (IGNORE_FIELDS.contains(field)) {
                continue;
            }

            if (!actualJson.has(field)) {
                result.setPassed(false);
                result.setFailureReason("Missing field for regex validation: " + field);
                return result;
            }

            String actualValue = actualJson.get(field).getAsString();

            // Regex validation
            if (expectedPattern != null &&
                !Pattern.matches(expectedPattern, actualValue)) {

                result.setPassed(false);
                result.setFailureReason(
                        "Field '" + field + "' value '" + actualValue +
                        "' does not match pattern '" + expectedPattern + "'"
                );
                return result;
            }
        }

        /* 6️⃣ All validations passed */
        result.setPassed(true);
        result.setFailureReason(null);
        return result;
    }
}
