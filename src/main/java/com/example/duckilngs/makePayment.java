package com.example.duckilngs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/my-payment/*")
public class makePayment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("username");

        if (username == null) {
            resp.sendRedirect("/login.jsp");
        } else {
            req.getRequestDispatcher("/paymentPage.jsp").include(req, resp);

            PrintWriter out = resp.getWriter();
            out.print("<br> <form action=\"/invoice/my-invoice\">" +
                    "<button>back</button>" +
                    "</form> <br>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("username");

        userLoginDB db = new userLoginDB();

        if (username == null) {
            resp.sendRedirect("/index.jsp");
        } else {
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String category = req.getParameter("category");
            int price = Integer.parseInt(req.getParameter("price"));

            db.createPayment(username, title, description, category, price);
            resp.sendRedirect("/invoice/my-invoice");
        }
    }
}
