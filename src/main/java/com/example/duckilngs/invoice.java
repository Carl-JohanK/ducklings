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
import java.util.Map;

@WebServlet("/invoice/*")
public class invoice extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    switch (req.getPathInfo()){
        case "/login": login(req, resp); break;
        case "/logout": logout(req, resp); break;
    }
    }
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        HttpSession session = req.getSession(true);

        session.setAttribute("username", null); // nollställ "state" för inloggade användare
        session.invalidate(); // nollställ session

        resp.sendRedirect("/index.jsp"); //dirigera om användaren till login sidan
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        userLoginDB dbConn = new userLoginDB();

        String name = req.getParameter("username");
        String passWord = req.getParameter("password");

        Map<String, String> db = dbConn.getAllUsers();
        if(db.get(name) == null){
            req.getRequestDispatcher("/index.jsp").forward(req, resp);

        } else if (db.get(name).equals(passWord)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("username", name);

            resp.sendRedirect("/invoice/my-invoice");

        } else {
            resp.sendRedirect("/index.jsp");
        }
    }
}
