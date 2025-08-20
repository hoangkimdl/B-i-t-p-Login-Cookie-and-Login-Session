package vn.iostar.controllers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/hello", "/xin-chao"})
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String name = "";

        // Nhận cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) { // tránh NullPointerException
            for (Cookie c : cookies) {
                if ("username".equals(c.getName())) {
                    name = c.getValue();
                    break;
                }
            }
        }

        if (name == null || name.isEmpty()) {
            // Chưa đăng nhập hoặc cookie hết hạn -> về trang login
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Hiển thị nội dung
        out.println("<h1>Xin chào " + name + "!</h1>");
        out.println("<p>(Cookie sẽ hết hạn sau tối đa 30 giây kể từ lúc đăng nhập)</p>");
        out.println("<p><a href='" + req.getContextPath() + "/login'>Đăng nhập lại</a></p>");
    }
}