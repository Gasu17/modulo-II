package Mayusculas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

        public static void main(String[] args) {
            int puerto = 6000;

            try (ServerSocket servidorSocket = new ServerSocket(puerto)) {
                System.out.println("Servidor iniciado y escuchando en el puerto " + puerto + "...");

                while (true) {
                    try (Socket socketCliente = servidorSocket.accept();
                         BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                         PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true)) {

                        System.out.println("¡p10.Cliente conectado desde: " + socketCliente.getInetAddress() + "!");

                        String mensajeRecibido = entrada.readLine();

                        if (mensajeRecibido != null) {
                            System.out.println("Mensaje recibido: \"" + mensajeRecibido + "\"");

                            String mensajeMayusculas = mensajeRecibido.toUpperCase();

                            salida.println(mensajeMayusculas);
                            System.out.println("Respuesta enviada: \"" + mensajeMayusculas + "\"");
                        }

                    } catch (IOException e) {
                        System.err.println("Error al procesar la comunicación con el cliente: " + e.getMessage());
                    }

                    System.out.println("Conexión cerrada con el cliente actual. Esperando al siguiente...\n");
                }

            } catch (BindException e) {
                System.err.println("ERROR CRÍTICO: El puerto " + puerto + " ya se encuentra ocupado por otra aplicación.");
            } catch (IOException e) {
                System.err.println("Error en el servidor de sockets: " + e.getMessage());
            }
        }
    }

