/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicaudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * @author luchop
 */
public class ClienteUDP {
    public static void main(String args[]) {
        int puerto = 6789;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Introduzca datos en formato 'nombre:ci' (ej. Juan:1234567): ");
            // Usamos nextLine() para leer la línea completa
            String dato = sc.nextLine();
            String ip = "localhost";
            
            DatagramSocket socketUDP = new DatagramSocket();
            byte[] mensaje = dato.getBytes();
            InetAddress hostServidor = InetAddress.getByName(ip);
          
            // Enviamos mensaje.length en lugar de dato.length()
            DatagramPacket peticion = new DatagramPacket(
                mensaje, 
                mensaje.length, 
                hostServidor,
                puerto
            );

            // Enviamos el datagrama
            socketUDP.send(peticion);

            // Preparamos el búfer para la respuesta
            byte[] bufer = new byte[1000];
            DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
            
            // Recibimos la respuesta del servidor
            socketUDP.receive(respuesta);

            // Convertimos solo los bytes leídos realmente
            String resTexto = new String(respuesta.getData(), 0, respuesta.getLength()).trim();
            
            System.out.println("Respuesta del servidor: '" + resTexto + "'");

            // Cerramos los recursos
            socketUDP.close();
            sc.close();

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
