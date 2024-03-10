/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.areandina.threadandsocket;

/**
 *
 * @author mejia
 */
public class ThreadAndSocket {

    public static void main(String[] args) {
        Thread servidorThread = new Thread(() -> Servidor.main(null));
        servidorThread.start();

        // Ejecutar múltiples clientes en hilos separados
        Thread clienteThread1 = new Thread(() -> Cliente.main(null));
        Thread clienteThread2 = new Thread(() -> Cliente.main(null));
        clienteThread1.start();
        clienteThread2.start();

        // Esperar a que los hilos de los clientes terminen
        try {
            clienteThread1.join();
            clienteThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Detener el servidor
        servidorThread.interrupt();
    }
}
