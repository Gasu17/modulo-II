package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class cliente {
    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket()){
            InetAddress ipServer = InetAddress.getByName("localhost");
            int puerto = 5000;
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("Cliente: ");
                String mensaje = sc.nextLine();
                DatagramPacket paquete = new DatagramPacket(mensaje.getBytes(StandardCharsets.UTF_8), mensaje.getBytes(StandardCharsets.UTF_8).length, ipServer, puerto);
                socket.send(paquete);
                if (mensaje.equalsIgnoreCase("exit") || mensaje.equalsIgnoreCase("salir")) {
                    break;
                }
                byte[] buffer = new byte[1024];
                DatagramPacket paqueteRespuesta = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRespuesta);
                String respuesta = new String(paqueteRespuesta.getData(), paqueteRespuesta.getOffset(), paqueteRespuesta.getLength());
                System.out.println("Servidor: " + respuesta);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println();
        }
    }
}