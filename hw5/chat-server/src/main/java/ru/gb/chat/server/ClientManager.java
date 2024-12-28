package ru.gb.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable {
    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public static final ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket) {
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            name = bufferedReader.readLine();
            clients.add(this);
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    private void broadcastMessage(String message) {
        ClientManager recipient = getRecipientOfMessage(message);
        if (recipient == null) {
            for (ClientManager client : clients) {
                if (!client.name.equals(name)) {
                    sendMessage(client, message);
                }
            }
        } else {
            message = message.replace("@" + recipient.name, "");
            message = message.trim().replaceAll(" +", " ");
            sendMessage(recipient, message);
        }
    }

    private ClientManager getRecipientOfMessage(String message) {
        String recipientsName;
        if (message.contains("@")) {
            recipientsName = message.split("@", 2)[1];
            for (ClientManager client : clients) {
                if (recipientsName.startsWith(client.name)) {
                    return client;
                }
            }
        }
        return null;
    }

    private void sendMessage(ClientManager client, String message) {
        try {
            client.bufferedWriter.write(message);
            client.bufferedWriter.newLine();
            client.bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    private void removeClient() {
        clients.remove(this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server" + name + " покинул чат.");
    }
    private void closeEverything(Socket socket, BufferedReader bufferedReader,
                                 BufferedWriter bufferedWriter) {
        // Удаление клиента из коллекции
        removeClient();
        try {
            // Завершаем работу буфера на чтение данных
            if (bufferedReader != null) { bufferedReader.close(); }
            // Завершаем работу буфера для записи данных
            if (bufferedWriter != null) { bufferedWriter.close(); }
            // Закрытие соединения с клиентским сокетом
            if (socket != null) { socket.close(); }
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine(); // ожидаем сообщение от клиента
                /*
                if (messageFromClient == null) {
                    // для macOS
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }
                */
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }
}
