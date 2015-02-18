package kz.kstu.coffee.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kz.kstu.coffee.commands.Command;
import kz.kstu.coffee.commands.CommandFactory;
import kz.kstu.coffee.config.ConfigManager;

public class Controller extends HttpServlet {
 private static final long serialVersionUID = 1L;

    @Override
 protected void doGet(HttpServletRequest request,
  HttpServletResponse response) throws ServletException, IOException {
  processRequest(request, response);
 }

    @Override
 protected void doPost(HttpServletRequest request,
  HttpServletResponse response) throws ServletException, IOException {
  processRequest(request, response);
 }

 private void processRequest(HttpServletRequest request,
  HttpServletResponse response) throws ServletException, IOException {
  String page = null;
  // определение команды, пришедшей из JSP
  Command command = CommandFactory.getCommand(request);
  /*
   * вызов реализованного метода execute() и передача параметров
   * классу-обработчику конкретной команды
   */
  page = command.execute(request);
  // метод возвращает страницу ответа
  if (page != null) {
   RequestDispatcher dispatcher = getServletContext()
     .getRequestDispatcher(page);
    // вызов страницы ответа на запрос
    dispatcher.forward(request, response);
  } else {
   // установка страницы c cообщением об ошибке
   page = ConfigManager.getProperty("path.page.error");//index
   response.sendRedirect(request.getContextPath() + page);
   
  }
 }
}
