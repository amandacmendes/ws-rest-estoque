package ifmt.cba.app;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;

import ifmt.cba.util.ServidorHTTP;

public class Aplicacao {
    public static void main(String[] args) throws IOException {
        HttpServer servidor = ServidorHTTP.getServerHTTP();
        System.out.println("--------------");
        System.out.println("Web Services Jersey Publicados - Pressione qualquer tecla para encerrar.");
        System.in.read();
        servidor.shutdownNow();
    }
}
