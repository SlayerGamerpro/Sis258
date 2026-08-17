/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.server.operacion;

/**
 *
 * @author luchop
 */

import java.io.*;
import java.net.*;
public class ClienteProtocolo {
    public static void main(String[] args) throws IOException {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = 5002;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Cliente Protocolo iniciado");

        while (true) {
            System.out.print("\nIngrese la solicitud ej: SUMA;8;2, RES, MUL, DIV ");
            String texto = teclado.readLine();


            try (Socket socket = new Socket(host, port);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                out.println(texto);
                System.out.println("Respuesta del Servidor: " + in.readLine());

            } catch (IOException e) {
                System.out.println("Error al conectar con el servidor: " + e.getMessage());
            }
        }
    }
}
