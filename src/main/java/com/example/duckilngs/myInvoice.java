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

@WebServlet("/invoice/my-invoice")
public class myInvoice extends HttpServlet {
    userLoginDB db = new userLoginDB();
    List<payments> paymentList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("username");

        if(username == null){
            resp.sendRedirect("/index.jsp");
        } else {
            req.getRequestDispatcher("/invoice.jsp").include(req, resp);
            paymentList = db.getAllPayments(username);

            PrintWriter out = resp.getWriter();
            out.print("<b>user: </b>" + username + "<br>");
            for (payments payments : paymentList) {
                out.print("<br>" + payments.getDateOfPost() +
                        " <b>title:</b> " + payments.getTitle() +
                        "<br><b>description:</b> " + payments.getDescription() +
                        " <b>category:</b> " + payments.getCategory() +
                        "<br><b>price:</b> " + payments.getPrice() +
                        "<br>"
                );
            }
            out.print("<br> <form action=\"/my-payment\">\n" +
                    "    <button>new payment</button>\n" +
                    "</form> <br>");

            out.print("<br> <form action=\"/edit-payment\">\n" +
                    "    <button>Edit payment</button>\n" +
                    "</form> <br>");

            out.print("<br> <form method=\"post\" action=\"/invoice/logout\">\n" +
                    "    <button>logout</button>\n" +
                    "</form> <br>");
        }
    }
}
