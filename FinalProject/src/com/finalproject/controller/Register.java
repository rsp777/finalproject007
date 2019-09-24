package com.finalproject.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finalproject.entity.FinalProjectEntity;
import com.finalproject.service.FinalProjectService;
import com.finalproject.service.FinalProjectServiceInterface;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		String option = request.getParameter("zensar");
		Connection con = null;
		
		if(option.equals("register")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			FinalProjectEntity fpe = new FinalProjectEntity();
			fpe.setName(name);
			fpe.setEmail(email);
			fpe.setPass(pass);
			FinalProjectServiceInterface fps = FinalProjectService.createServiceObject("fps");
			int i = fps.createProfile(fpe);
			
			if(i>0) {
				//out.println("Profile created Sucessfully Click <a href=#>here</a> to Login");
				try {
					Context inctx = new InitialContext();
					Queue que = (Queue) inctx.lookup("java:/finalque");
					Destination dest = que;
					
					QueueConnectionFactory qcf = (QueueConnectionFactory) inctx.lookup("java:/ConnectionFactory");
					con = qcf.createConnection();
					Session session = con.createSession(false,Session.AUTO_ACKNOWLEDGE);
					MessageProducer producer = session.createProducer(dest);
					TextMessage message = session.createTextMessage("Register successful");
					producer.send(message);
					
					out.println(message.getText());
					System.out.println(message.getText());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				try {
					Context inctx = new InitialContext();
					Queue que = (Queue) inctx.lookup("java:/finalque");
					Destination dest = que;
					
					QueueConnectionFactory qcf = (QueueConnectionFactory) inctx.lookup("java:/ConnectionFactory");
					con = qcf.createConnection();
					Session session = con.createSession(false,Session.AUTO_ACKNOWLEDGE);
					MessageProducer producer = session.createProducer(dest);
					TextMessage message = session.createTextMessage("Register unsuccessful");
					producer.send(message);
					
					out.println(message.getText());
					System.out.println(message.getText());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		}
		else if(option.equals("login")) {
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			FinalProjectEntity fpe = new FinalProjectEntity();
			fpe.setEmail(email);
			fpe.setPass(pass);
			FinalProjectServiceInterface fps = FinalProjectService.createServiceObject("fps");
			int i = fps.loginProfile(fpe);
			
			if(i>0) {
				//out.println("Profile created Sucessfully Click <a href=#>here</a> to Login");
				try {
					Context inctx = new InitialContext();
					Queue que = (Queue) inctx.lookup("java:/finalque");
					Destination dest = que;
					
					QueueConnectionFactory qcf = (QueueConnectionFactory) inctx.lookup("java:/ConnectionFactory");
					con = qcf.createConnection();
					Session session = con.createSession(false,Session.AUTO_ACKNOWLEDGE);
					MessageProducer producer = session.createProducer(dest);
					TextMessage message = session.createTextMessage("Login successful");
					producer.send(message);
					
					out.println(message.getText());
					System.out.println(message.getText());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				try {
					Context inctx = new InitialContext();
					Queue que = (Queue) inctx.lookup("java:/finalque");
					Destination dest = que;
					
					QueueConnectionFactory qcf = (QueueConnectionFactory) inctx.lookup("java:/ConnectionFactory");
					con = qcf.createConnection();
					Session session = con.createSession(false,Session.AUTO_ACKNOWLEDGE);
					MessageProducer producer = session.createProducer(dest);
					TextMessage message = session.createTextMessage("Register unsuccessful");
					producer.send(message);
					
					out.println(message.getText());
					System.out.println(message.getText());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		out.println("</body></html>");
			
	}

}
