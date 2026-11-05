package AdivinaElNumero;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {
    public static void main(String[] args) {
        try (ServerSocket servidorSocket = new ServerSocket(5000)) {
            System.out.println("Esperando cliente");

            try (Socket clienteSocket = servidorSocket.accept();
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                 PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true)) {

                System.out.println("Cliente conectado correctamente");

                int numeroSecreto = (int) (Math.random() * 100) + 1;
                salida.println("Adivina el numero entre 1 y 100");

                boolean acertado = false;
                while (!acertado) {
                    String linea = entrada.readLine();
                    if (linea == null) {
                        break;
                    }

                    try {
                        int intento = Integer.parseInt(linea.trim());
                        if (intento < numeroSecreto) {
                            salida.println("Mayor");
                        } else if (intento > numeroSecreto) {
                            salida.println("Menor");
                        } else {
                            salida.println("Enhorabuena el número era: " + numeroSecreto);
                            acertado = true;
                        }
                    } catch (NumberFormatException e) {
                        salida.println("Intento no válido");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("No se ha podido establecer conexión");
        }
    }
}
