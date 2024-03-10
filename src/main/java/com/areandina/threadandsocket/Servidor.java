/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.areandina.threadandsocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author mejia
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Crear un ServerSocket para escuchar conexiones en el puerto 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                // Aceptar una conexión del cliente
                Socket socket = serverSocket.accept();
                System.out.println("Conexión establecida con un cliente.");

                // Crear un nuevo hilo para manejar la conexión del cliente
                Thread clientThread = new Thread(() -> {
                    try {
                        // Obtener flujo de entrada para recibir números del cliente
                        InputStream inputStream = socket.getInputStream();

                        // Generar número aleatorio para adivinar
                        int numeroAdivinar = generarNumeroAdivinar();

                        // Contadores para los desaciertos consecutivos y el total de desaciertos
                        int desaciertosConsecutivos = 0;
                        int totalDesaciertos = 0;

                        // Adivinar los números generados por el cliente
                        while (desaciertosConsecutivos < 3) {
                            int numeroAdivinado = inputStream.read();
                            System.out.println("Número adivinado por el servidor: " + numeroAdivinado);

                            // Verificar si el número adivinado coincide con el número generado
                            if (numeroAdivinado == numeroAdivinar) {
                                System.out.println("¡Adivinaste el número!");
                                break; // Terminar el juego si se adivina el número
                            } else {
                                System.out.println("Número incorrecto. Sigue intentando.");
                                desaciertosConsecutivos++; // Incrementar el contador de desaciertos consecutivos
                                totalDesaciertos++; // Incrementar el contador total de desaciertos
                            }
                        }

                        if (desaciertosConsecutivos == 3) {
                            System.out.println("Perdiste. Tres desaciertos consecutivos.");
                        }

                        // Cerrar conexión con el cliente
                        socket.close();

                        System.out.println("Total de desaciertos: " + totalDesaciertos);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                // Iniciar el hilo para manejar la conexión del cliente
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int generarNumeroAdivinar() {
        Random random = new Random();
        return random.nextInt(100) + 1; // Generar un número aleatorio en el rango del 1 al 100
    }
}
