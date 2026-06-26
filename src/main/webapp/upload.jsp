<!DOCTYPE html>
<html>
<head>
    <title>API Test Automation Software</title>

    <style>
        body {
            margin: 0;
            padding: 20px;
            font-family: "Segoe UI", Arial;
            background: linear-gradient(135deg, #1e3c72, #2a5298);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            width: 90%;
            max-width: 800px;
            padding: 35px;
            background: rgba(255,255,255,0.15);
            border-radius: 15px;
            backdrop-filter: blur(12px);
            box-shadow: 0 8px 25px rgba(0,0,0,0.25);
            text-align: center;
            color: #f5f3f3;
        }

        h2 {
            margin-bottom: 25px;
            font-weight: 500;
            letter-spacing: 1px;
            color: #f3f4f5;
        }

        input[type=file] {
            width: 100%;
            padding: 14px;
            border: none;
            outline: none;
            border-radius: 8px;
            background: #0b0101d9;
            margin-bottom: 25px;
            cursor: pointer;
        }

        input[type=submit] {
            width: 100%;
            padding: 14px;
            border: none;
            border-radius: 8px;
            background: #000d10;
            color: #f5f8fa;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
        }

        input[type=submit]:hover {
            background: #14b6e2;
        }

        .example-section {
            margin-top: 30px;
            text-align: left;
            background: rgba(0,0,0,0.2);
            border-radius: 10px;
            padding: 20px;
        }

        .example-title {
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 15px;
            text-align: center;
            color: #f7fafb;
        }

        .json-example {
            background: #1a1a2e;
            border-radius: 8px;
            padding: 15px;
            overflow-x: auto;
            font-family: 'Courier New', monospace;
            font-size: 13px;
            line-height: 1.6;
            color: #e0e0e0;
            border: 1px solid rgba(255,255,255,0.1);
            max-height: 400px;
            overflow-y: auto;
        }

        .json-key {
            color: #00d4ff;
        }

        .json-string {
            color: #4ade80;
        }

        .json-number {
            color: #fbbf24;
        }

        .note {
            margin-top: 15px;
            font-size: 12px;
            color: #eef0f2;
            text-align: center;
            font-weight: bold;
            padding: 15px;
            border: 2px solid rgba(255,255,255,0.3);
            border-radius: 8px;
            background: rgba(0,0,0,0.3);
        }

        .error-message {
            margin-top: 15px;
            padding: 12px;
            background: rgba(220, 38, 38, 0.9);
            color: #fff;
            border-radius: 8px;
            font-weight: bold;
            text-align: center;
            display: none;
            border: 2px solid rgba(255,255,255,0.3);
        }

        .error-message.show {
            display: block;
        }
    </style>
</head>

<body>

    <div class="container">
        <h2>Upload only JSON File</h2>

        <% if (request.getAttribute("error") != null) { %>
            <div class="error-message show"><%= request.getAttribute("error") %></div>
        <% } %>

        <form id="uploadForm" action="UploadServlet" method="post" enctype="multipart/form-data">
            <input type="file" id="testFile" name="testFile" accept=".json" required>
            <div id="errorMessage" class="error-message">This is not .json file</div>
            <input type="submit" value="Run Tests">
        </form>

        <div class="example-section">
            <div class="example-title"> JSON File Format Example</div>
            <div class="json-example">
<pre>{
  "tests": [
    {
      "testName": "Get User by ID",
      "method": "GET",
      "endpoint": "https://api.example.com/users/1",
      "expectedStatusCode": 200
    },
    {
      "testName": "Create New User",
      "method": "POST",
      "endpoint": "https://api.example.com/users",
      "expectedStatusCode": 201,
      "headers": {
        "Content-Type": "application/json"
      },
      "body": {
        "name": "John Doe",
        "email": "john@example.com"
      }
    },
    {
      "testName": "Update User",
      "method": "PUT",
      "endpoint": "https://api.example.com/users/1",
      "expectedStatusCode": 200,
      "headers": {
        "Content-Type": "application/json"
      },
      "body": {
        "name": "Updated Name",
        "email": "updated@example.com"
      }
    },
    {
      "testName": "Delete User",
      "method": "DELETE",
      "endpoint": "https://api.example.com/users/1",
      "expectedStatusCode": 200
    }
  ]
}</pre>
            </div>
            <div class="note">
                **Required fields:{ <br>"testName":"",<br> "method":"",<br> "endpoint":"",<br> "expectedStatusCode":"",<br>
                Optional fields:<br> "headers":"{}",<br> "body":"{}", (for POST/PUT requests)}
            </div>
        </div>
    </div>

    <script>
        const fileInput = document.getElementById('testFile');
        const errorMessage = document.getElementById('errorMessage');
        const uploadForm = document.getElementById('uploadForm');

        // Validate file extension on file selection
        fileInput.addEventListener('change', function(e) {
            const file = e.target.files[0];
            if (file) {
                const fileName = file.name;
                const fileExtension = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
                
                if (fileExtension !== '.json') {
                    errorMessage.classList.add('show');
                    fileInput.value = ''; // Clear the file input
                } else {
                    errorMessage.classList.remove('show');
                }
            }
        });

        // Validate before form submission
        uploadForm.addEventListener('submit', function(e) {
            const file = fileInput.files[0];
            if (file) {
                const fileName = file.name;
                const fileExtension = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
                
                if (fileExtension !== '.json') {
                    e.preventDefault(); // Prevent form submission
                    errorMessage.classList.add('show');
                    return false;
                }
            }
        });
    </script>

</body>
</html>
