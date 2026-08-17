/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sis258.server.operacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dell
 */
public class ServerOperacion {

    public static void main(String[] args) {
        int port = 5002;
        
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Se inicio el servidor con éxito en el puerto " + port);
            Socket client = server.accept(); 
            System.out.println("Cliente se conecto");
            while (true) {
                try {
                    BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintStream toClient = new PrintStream(client.getOutputStream());

                    String recibido = fromClient.readLine();
                    System.out.println("El cliente envio el mensaje: " + recibido);
                    int numero1 = Integer.parseInt(recibido);

                    toClient.println("introduzca el segundo numero");
                    String recibido2 = fromClient.readLine();
                    int numero2 = Integer.parseInt(recibido2);

                    toClient.println("1.sum 2.resta 3.mul 4.div .introduzca la operacion");
                    String recibido3 = fromClient.readLine();

                    int resultado = 0;
                    switch (recibido3) {
                        case "sum":
                            resultado = numero1 + numero2;
                            break;
                        case "res":
                            resultado = numero1 - numero2;
                            break;
                        case "mul":
                            resultado = numero1 * numero2;
                            break;
                        case "div":
                            if (numero2 == 0) {
                                toClient.println("Error: no se puede dividir entre 0");
                                client.close();
                                continue;
                            }
                            resultado = numero1 / numero2;
                            break;
                    }

                    // 4. Envía el resultado
                    toClient.println(String.valueOf(resultado));

                    // Cierra la conexión del cliente actual para dar paso al siguiente en el ciclo
                    client.close();

                } catch (IOException ex) {
                    System.out.println("Error en la comunicacion con el cliente: " + ex.getMessage());
                } catch (NumberFormatException ex) {
                    System.out.println("Error de formato numerico enviado por el cliente.");
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al iniciar el ServerSocket: " + ex.getMessage());
        }
    }
}
