<!DOCTYPE html>
<html>
<head>
<title>Running Tests...</title>

<style>
    body {
        margin: 0;
        background: linear-gradient(135deg, #0f2027, #203a43, #2c5364);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        font-family: "Segoe UI", Arial;
        color: #fff;
    }

    .box {
        text-align: center;
    }

    .loader {
        border: 6px solid rgba(255,255,255,0.2);
        border-top: 6px solid #00f2ff;
        border-radius: 50%;
        width: 65px;
        height: 65px;
        margin: auto;
        animation: spin 1s linear infinite;
    }

    @keyframes spin {
        0% { transform: rotate(0deg); }
        100% { transform: rotate(360deg); }
    }
</style>
</head>

<body>
    <div class="box">
        <h2>Executing API Tests...</h2>
        <div class="loader"></div>
    </div>
</body>
</html>
