package com.example.duckilngs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sign-up")
public class signUp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userLoginDB dbConn = new userLoginDB();

        String name = req.getParameter("username");
        String passWord = req.getParameter("password");


        dbConn.singUp(name, passWord);
        resp.sendRedirect("/index.jsp");

    }
}
