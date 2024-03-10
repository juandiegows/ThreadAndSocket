/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.areandina.threadandsocket;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.io.IOException;

/**
 *
 * @author mejia
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Establecer conexión con el servidor
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conexión establecida con el servidor.");

            // Obtener flujo de salida para enviar números aleatorios
            OutputStream outputStream = socket.getOutputStream();

            // Generar números aleatorios y enviarlos al servidor
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                int numeroAleatorio = random.nextInt(100);
                System.out.println("Enviando número: " + numeroAleatorio);
                outputStream.write(numeroAleatorio);
            }

            // Cerrar conexión con el servidor
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
