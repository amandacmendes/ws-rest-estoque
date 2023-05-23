package ifmt.cba.util;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class ServidorHTTP {

    public static HttpServer getServerHTTP() {

        String BASE_URI = "http://localhost:8080";
        final ResourceConfig rc = new ResourceConfig().packages("ifmt.cba.servico");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        
    }
}
