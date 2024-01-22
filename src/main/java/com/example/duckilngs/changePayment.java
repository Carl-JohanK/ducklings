package com.example.duckilngs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/edit-payment/*")
public class changePayment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("username");
        userLoginDB db = new userLoginDB();
        List<payments> paymentList;

        if (username == null) {
            resp.sendRedirect("/login.jsp");
        } else {
            req.getRequestDispatcher("/editPayment.jsp").include(req, resp);
            paymentList = db.getAllPayments(username);

            PrintWriter out = resp.getWriter();
            out.print("<b>user: </b>" + username + "<br>");
            for (payments payments : paymentList) {
                out.print("<br>" + payments.getDateOfPost() +
                        " <b>title:</b> " + payments.getTitle() +
                        "<br><b>description:</b> " + payments.getDescription() +
                        " <b>category:</b> " + payments.getCategory() +
                        "<br><b>price:</b> " + payments.getPrice() +
                        "<br>");
            }
            out.print("<br> <form action=\"/invoice/my-invoice\">" +
                    "<button>back</button>" +
                    "</form> <br>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getPathInfo()){
            case "/edit": editPayment(req, resp); break;
            case "/delete": deletePayment(req, resp); break;
        }
    }
    private void editPayment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("username");
        userLoginDB db = new userLoginDB();

        if (username == null) {
            resp.sendRedirect("/index.jsp");
        } else {
            String title = req.getParameter("title");
            String newTitle = req.getParameter("change title");
            String newDescription = req.getParameter("change description");
            String newCategory = req.getParameter("change category");
            int newPrice = Integer.parseInt(req.getParameter("change price"));

            db.editPayment(username, title, newTitle, newDescription, newCategory, newPrice);
            resp.sendRedirect("/invoice/my-invoice");
        }

    }
    private void deletePayment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("username");
        userLoginDB db = new userLoginDB();

        if (username == null) {
            resp.sendRedirect("/index.jsp");
        } else {
            String title = req.getParameter("title");
            db.deletePayment(username, title);
            resp.sendRedirect("/invoice/my-invoice");
        }
    }
}
