package vn.iostar.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/login"})   // ánh xạ servlet với URL /login
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Nếu người dùng truy cập /login bằng GET thì hiển thị lại form Login.html
        req.getRequestDispatcher("/Login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        // Kiểm tra tài khoản (demo: user=trung, pass=123)
        if ("trung".equals(user) && "123".equals(pass)) {
            // Khởi tạo cookie
            Cookie cookie = new Cookie("username", user);
            cookie.setMaxAge(30); // thời gian tồn tại 30 giây
            resp.addCookie(cookie);

            // Chuyển sang HelloServlet
            resp.sendRedirect(req.getContextPath() + "/hello");
        } else {
            // Sai tài khoản → quay lại form login
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}