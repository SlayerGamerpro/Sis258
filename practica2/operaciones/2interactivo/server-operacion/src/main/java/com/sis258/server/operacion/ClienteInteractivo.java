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

public class ClienteInteractivo {
    public static void main(String[] args) throws IOException {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = 5002;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            System.out.print("\nIntroduzca el primer numero: ");
            String num1 = teclado.readLine();


            try (Socket socket = new Socket(host, port);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                out.println(num1);

                String promptSegundo = in.readLine();
                System.out.println("Servidor: " + promptSegundo);
                String num2 = teclado.readLine();
                out.println(num2);

                String promptOperacion = in.readLine();
                System.out.println("Servidor: " + promptOperacion);
                String operacion = teclado.readLine();
                out.println(operacion);

                String resultado = in.readLine();
                System.out.println("Servidor: " + resultado);

            } catch (IOException e) {
                System.out.println("Error al conectar con el servidor: " + e.getMessage());
            }
        }
    }
}
