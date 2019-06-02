package part01.lesson10.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class Client {
    private String name;
    private DatagramSocket socket;
    private InetAddress address;
    private int port = 4445;
    private byte[] buf = new byte[256];
    private boolean isFirst = true;
    public static volatile boolean flag = true;

    public Client(String clientName) {
        name = clientName;
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Enter name: ");
        Scanner scanner = new Scanner(System.in);
        String clientName = scanner.nextLine();
        Client client = new Client(clientName);
        client.work();
    }

    public void work() throws IOException {
        while (true) {
            String message = "";
            if (isFirst) {
                message = name;
            } else {
                BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
                message = stdin.readLine();
            }
            System.arraycopy(message.getBytes(), 0, buf, 0, message.length());
            DatagramPacket packet = new DatagramPacket(buf, message.length(), address, port);
            socket.send(packet);
            if (message.equals("quit") || message.equals("end")) {
                flag = false;
                break;
            }
            if (isFirst) {
                Thread thread = new Thread(() -> {
                    while (flag) {
                        DatagramPacket incomingPacket = new DatagramPacket(buf, buf.length);
                        try {
                            socket.receive(incomingPacket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(new String(incomingPacket.getData(), 0, incomingPacket.getLength()));
                    }
                    socket.close();
                });
                thread.start();
                isFirst = false;
            }
        }
    }

}
