package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class servidor {
    public static void main(String[] args) {
        try (DatagramSocket server = new DatagramSocket(5000)){
            System.out.println("Server esperando");
            Scanner sc = new Scanner(System.in);
            while (true){
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                server.receive(packet);
                String mensaje = new String(packet.getData(), packet.getOffset(), packet.getLength());
                System.out.println("Cliente: " + mensaje);
                if(mensaje.equalsIgnoreCase("exit") || mensaje.equalsIgnoreCase("salir")){
                    break;
                }
                System.out.print("Servidor: ");
                String respuesta = sc.nextLine();
                DatagramPacket paqueteRespuesta = new DatagramPacket(respuesta.getBytes(StandardCharsets.UTF_8), respuesta.getBytes(StandardCharsets.UTF_8).length, packet.getAddress(), packet.getPort());
                server.send(paqueteRespuesta);
            }
            sc.close();
        }catch (IOException e){
            System.out.println("error: " + e.getMessage());
        }

    }
}
