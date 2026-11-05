import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Cliente {


    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;
        String mensajeAEnviar = "hola, este es un texto de prueba";

        try (Socket socket = new Socket(host, puerto);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Conectado al servidor en " + host + ":" + puerto);

            salida.println(mensajeAEnviar);
            System.out.println("Mensaje enviado al servidor: \"" + mensajeAEnviar + "\"");

            String respuestaServidor = entrada.readLine();
            System.out.println("Respuesta recibida del servidor: \"" + respuestaServidor + "\"");

        } catch (ConnectException e) {
            System.err.println("ERROR: No se pudo conectar con el Servidor. Asegúrate de que esté encendido en el puerto " + puerto + ".");
        } catch (IOException e) {
            System.err.println("Error de E/S en el cliente: " + e.getMessage());
        }
    }
}

