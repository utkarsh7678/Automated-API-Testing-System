package com.myapp.engine;

import com.google.gson.Gson;
import com.myapp.model.TestCase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiExecutor {

    private final Gson gson = new Gson();

    public ApiResponse execute(TestCase testCase) {

        HttpURLConnection connection = null;

        try {
            URL url = new URL(testCase.getEndpoint());
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(testCase.getMethod());
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            boolean hasContentType = false;
            if (testCase.getHeaders() != null) {
                for (java.util.Map.Entry<String, String> header : testCase.getHeaders().entrySet()) {
                    connection.setRequestProperty(header.getKey(), header.getValue());
                    if ("Content-Type".equalsIgnoreCase(header.getKey())) {
                        hasContentType = true;
                    }
                }
            }

            if (!hasContentType) {
                connection.setRequestProperty("Content-Type", "application/json");
            }

            // For POST / PUT
            if ("POST".equalsIgnoreCase(testCase.getMethod()) ||
                "PUT".equalsIgnoreCase(testCase.getMethod())) {

                connection.setDoOutput(true);

                if (testCase.getBody() != null) {
                    try (OutputStream os = connection.getOutputStream()) {
                        String jsonBody = gson.toJson(testCase.getBody());
                        os.write(jsonBody.getBytes("UTF-8"));
                        os.flush();
                    }
                }
            }

            int statusCode = connection.getResponseCode();

            BufferedReader reader;
            if (statusCode >= 200 && statusCode < 300) {
                reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );
            } else {
                reader = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream())
                );
            }

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            return new ApiResponse(statusCode, response.toString());

        } catch (Exception e) {
            return new ApiResponse(500,
                    "API execution failed: " + e.getMessage());

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}


