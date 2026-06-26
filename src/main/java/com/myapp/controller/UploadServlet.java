package com.myapp.controller;

import com.myapp.model.TestResult;
import com.myapp.service.TestExecutionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class UploadServlet extends HttpServlet {

    private final TestExecutionService testExecutionService = new TestExecutionService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("testFile");

        if (filePart == null || filePart.getSize() == 0) {
            request.setAttribute("error", "Please select a JSON file to upload.");
            request.getRequestDispatcher("upload.jsp").forward(request, response);
            return;
        }

        String fileName = getSubmittedFileName(filePart);
        if (fileName == null || !fileName.toLowerCase().endsWith(".json")) {
            request.setAttribute("error", "Only .json files are allowed.");
            request.getRequestDispatcher("upload.jsp").forward(request, response);
            return;
        }

        try (InputStream inputStream = filePart.getInputStream()) {
            List<TestResult> results = testExecutionService.runTests(inputStream);
            request.setAttribute("results", results);
            request.getRequestDispatcher("report.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Failed to run tests: " + e.getMessage());
            request.getRequestDispatcher("upload.jsp").forward(request, response);
        }
    }

    private String getSubmittedFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        if (contentDisposition == null) {
            return null;
        }

        for (String token : contentDisposition.split(";")) {
            token = token.trim();
            if (token.startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }
}
