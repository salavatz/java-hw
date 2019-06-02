package part01.lesson10.task01;

import java.io.IOException;
import java.net.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Server extends Thread {

    private DatagramSocket socket;
    private byte[] buf = new byte[256];
    private HashMap<Map.Entry<Integer, InetAddress>, String> fullDataOfClients = new HashMap<>();

    public Server() throws SocketException, UnknownHostException {
        socket = new DatagramSocket(4445);
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        System.out.println("Server start!");
        Server server = new Server();
        server.start();
    }

    public void run() {
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        while (true) {

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress address = packet.getAddress();
            int port = packet.getPort();

            String received = new String(packet.getData(), 0, packet.getLength()).trim();
            String name = fullDataOfClients.get(new AbstractMap.SimpleEntry<>(port, address));
            String outMessage = "";
            if (name == null) {
                name = received;
                fullDataOfClients.put(new AbstractMap.SimpleEntry<>(port, address), name);
                outMessage = "Client " + name + " entered the chat";
            } else {
                outMessage = name + ": " + received;
            }
            if (received.split("#").length > 1) {
                String destName = received.split("#")[0];
                String privateMessage = received.split("#")[1];
                for (Map.Entry<Map.Entry<Integer, InetAddress>, String> entry : fullDataOfClients.entrySet()) {
                    if (entry.getValue().equals(destName)) {
                        byte[] message = (name + " sent you a private message: " + privateMessage).getBytes();
                        packet = new DatagramPacket(message, message.length, entry.getKey().getValue(), entry.getKey().getKey());
                        try {
                            socket.send(packet);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                }
                continue;
            }
            if (received.equals("quit")) {
                fullDataOfClients.remove(name);
                byte[] finalMessage = "Disconnect from server".getBytes();
                packet = new DatagramPacket(finalMessage, finalMessage.length, address, port);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                outMessage = "Client " + name + " left the chat";
            }
            System.arraycopy(outMessage.getBytes(), 0, buf, 0, outMessage.length());
            for (Map.Entry<Integer, InetAddress> entry : fullDataOfClients.keySet()) {
                packet = new DatagramPacket(buf, outMessage.length(), entry.getValue(), entry.getKey());
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (received.equals("end")) {
                break;
            }
        }
        socket.close();
    }
}
