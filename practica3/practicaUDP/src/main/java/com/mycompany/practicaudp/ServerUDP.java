/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicaudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author luchop
 */
public class ServerUDP {
    public static void main(String args[]) { 
        int port = 6789;  
        try {
            DatagramSocket socketUDP = new DatagramSocket(port);
            byte[] bufer = new byte[1000];

            System.out.println("Servidor UDP iniciado en el puerto " + port);

            while (true) {
                // Construimos el DatagramPacket para recibir peticiones
                DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);

                // Leemos una petición del DatagramSocket
                socketUDP.receive(peticion);

                System.out.print("Datagrama recibido del host: " + peticion.getAddress());
                System.out.println(" desde el puerto remoto: " + peticion.getPort());

                // Se extrae ÚNICAMENTE el fragmento de bytes recibido
                String cadena = new String(peticion.getData(), 0, peticion.getLength()).trim();

                String response = procesar(cadena);
                byte[] mensaje = response.getBytes();

                // Usamos mensaje.length para los bytes exactos
                DatagramPacket respuesta = new DatagramPacket(
                    mensaje, 
                    mensaje.length,
                    peticion.getAddress(), 
                    peticion.getPort()
                );

                // Enviamos la respuesta
                socketUDP.send(respuesta);
            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    public static String procesar(String cadena) {
        String[] comando = cadena.split(":");

        // Evita el error si la cadena no tiene ':'
        if (comando.length < 2) {
            return "ERROR: Formato incorrecto. Debe usar formato 'texto:ci'";
        }

        String ci = comando[1].trim();

        if (ci.equals("1234567")) {
            return "";
        } else {
            return "234";
        }
    }   
}
