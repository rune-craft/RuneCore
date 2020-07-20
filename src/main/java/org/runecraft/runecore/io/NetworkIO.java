package org.runecraft.runecore.io;

import org.runecraft.runecore.RuneCore;
import org.spongepowered.api.scheduler.Task;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class NetworkIO {

    private static Set<String> operations = new HashSet<>();
    private static final int PORT = 25565;
    private static Task connectionTask;

    public static void startConnection(String server, int port){
        connectionTask = Task.builder().async().execute(() -> {
            try(ServerSocket serverSocket = new ServerSocket(PORT); Socket socket = serverSocket.accept()) {
                DataInputStream dataInput = new DataInputStream(socket.getInputStream());
                while(dataInput.available()>0){
                    String str = dataInput.readUTF();
                    Operation operation = Operation.by(Integer.parseInt(str.split(";")[0]));
                    String args = str.split(";")[1];
                    operations.add(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).submit(RuneCore.getInstance());
    }
}
