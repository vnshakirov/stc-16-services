package ru.inno.stc16;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import ru.inno.stc16.controllers.JaxWsController;

import javax.xml.ws.Endpoint;

public class App {

  public static void main(String[] args) throws Exception {
    ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
    handler.setContextPath("/");

    Server server = new Server(8080);
    server.setHandler(handler);

    ServletHolder jerseyServlet = handler.addServlet(ServletContainer.class, "/*");
    jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "ru.inno.stc16.controllers");

    Endpoint.publish("http://localhost:8081/myWebService", new JaxWsController());

    try {
      server.start();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      server.destroy();
    }
  }


}
