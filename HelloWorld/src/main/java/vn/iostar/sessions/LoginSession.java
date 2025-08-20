package vn.iostar.sessions;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/LoginSession"})   // đổi urlPatterns cho phù hợp
public class LoginSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Mở form login khi truy cập /login1 trực tiếp
        req.getRequestDispatcher("/LoginSession.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Demo kiểm tra
        if ("trungnh".equals(username) && "123".equals(password)) {
            // Lưu thông tin vào session
            HttpSession session = request.getSession();   // tạo mới nếu chưa có
            session.setAttribute("name", username);

            // Điều hướng sang trang chào mừng (WelcomeServlet)
            response.sendRedirect(request.getContextPath() + "/welcome");
        } else {
            // Sai: in thông báo + include lại form
            PrintWriter out = response.getWriter();
            out.println("<p style='color:red'>Tài khoản hoặc mật khẩu không chính xác</p>");
            request.getRequestDispatcher("/LoginSession.html").include(request, response);
            out.close();
        }
    }
}