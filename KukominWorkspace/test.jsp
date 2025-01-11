<%-- test.jsp --%>
<html>
<head><title>JSP Test</title></head>
<body>
    <h1>JSP Connection Test</h1>
    <%
        try {
            // データベース接続処理
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDB");
            Connection conn = ds.getConnection();

            if (conn != null) {
                out.println("Database connection successful!");
            } else {
                out.println("Database connection failed.");
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    %>
</body>
</html>
