<%@ page import="java.util.*" %>
<%@ page import="com.myapp.model.TestResult" %>

<!DOCTYPE html>
<html>
<head>
<title>API Test Report</title>

<style>
    body {
        margin: 0;
        font-family: "Segoe UI", Arial;
        background: #eef2f3;
        padding: 20px;
    }

    .container {
        width: 90%;
        margin: auto;
        padding: 20px;
    }

    h2 {
        text-align: center;
        margin-bottom: 20px;
        color: #333;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        border-radius: 10px;
        overflow: hidden;
        box-shadow: 0 8px 20px rgba(0,0,0,0.10);
    }

    th {
        padding: 12px;
        background: #1e3c72;
        color: #fff;
        font-weight: 500;
        letter-spacing: 1px;
    }

    td {
        padding: 12px;
        background: #fff;
        text-align: center;
    }

    tr:nth-child(even) td {
        background: #f7f7f7;
    }

    .pass {
        background: #d4edda !important;
        color: #155724;
        font-weight: bold;
    }

    .fail {
        background: #f8d7da !important;
        color: #721c24;
        font-weight: bold;
    }
</style>

</head>

<body>

<div class="container">

<h2>API Test Execution Report</h2>

<table>
<tr>
    <th>Test Name</th>
    <th>Method</th>
    <th>Endpoint</th>
    <th>Status Code</th>
    <th>Expected</th>
    <th>Result</th>
    <th>Details</th>
</tr>

<%
    List<TestResult> results = (List<TestResult>) request.getAttribute("results");
    if (results != null) {
        for (TestResult t : results) {
%>

<tr class="<%= t.isPassed() ? "pass" : "fail" %>">
    <td><%= t.getTestName() %></td>
    <td><%= t.getMethod() %></td>
    <td><%= t.getEndpoint() %></td>
    <td><%= t.getStatusCode() %></td>
    <td><%= t.getExpectedStatusCode() != null ? t.getExpectedStatusCode() : "-" %></td>
    <td><%= t.isPassed() ? "PASS" : "FAIL" %></td>
    <td><%= t.getFailureReason() != null ? t.getFailureReason() : "All validations passed" %></td>
</tr>

<% } } %>

</table>

<p style="text-align:center; margin-top:20px;">
    <a href="upload.jsp" style="color:#1e3c72; font-weight:bold;">Run Another Test</a>
</p>

</div>

</body>
</html>
