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
        ServerSocket server;
        while (true) {
        try {
            // TODO code application logic here
            server = new ServerSocket(port);
            System.out.println("Se inicio el servidor con éxito");
            Socket client;
            PrintStream toClient;
            client = server.accept(); //conexion entre cliente y servidor para comunicacion bidireccional
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
            System.out.println("Cliente se conecto");
            String recibido = fromClient.readLine();
            System.out.println("El cliente envio el mensaje:" + recibido);
            toClient = new PrintStream(client.getOutputStream());
            String respuesta=procesarSolicitud(recibido);
            toClient.println(respuesta);
            
            //client.close();
            //server.close();
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
        }
    }
    public static String procesarSolicitud(String cadena )
    {
        if(cadena == null || cadena.trim().isEmpty()){
            return "ERROR: cadena vacia";
        }
        
        String[] elementos = cadena.split(";");
        String operacion = elementos[0].toUpperCase();
        
        try{
            switch(operacion){
                case "SUM":
                    double a1 = Double.parseDouble(elementos[1]);
                    double b1 = Double.parseDouble(elementos[2]);
                    return "El RESULTADO ES:" + (a1+b1);
                case "RES":
                    double a2 = Double.parseDouble(elementos[1]);
                    double b2 = Double.parseDouble(elementos[2]);
                    return "El RESULTADO ES:" + (a2-b2);
                case "MUL":
                    double a3 = Double.parseDouble(elementos[1]);
                    double b3 = Double.parseDouble(elementos[2]);
                    return "El RESULTADO ES:" + (a3*b3);
                case "DIV":
                    double a4 = Double.parseDouble(elementos[1]);
                    double b4 = Double.parseDouble(elementos[2]);
                    if(b4 == 0){
                        return "Error: no se puede divir entre 0"; 
                    }
                    return "El RESULTADO ES:" + (a4+b4);
                default:
                    return "Error operacion no conocida";
            } 
            } catch(Exception e){
                    return "Error: formato o elementos incorrectos";   
            }
        }
    }

