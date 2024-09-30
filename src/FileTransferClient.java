import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileTransferClient {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        DatagramSocket clientSocket = new DatagramSocket();

        String serverAddress = "127.0.0.1"; // Server IP address
        int serverPort = 12345; // Server port

        System.out.println("Enter the filename which you want to send: ");
        String fileName = sc.nextLine();
        // String fileName = "D:\\FILES\\SEM-5 (FILES)\\AdvJava\\src\\FTP_Server_Client\\abcd.txt"; // Specify the path to the file
        byte[] fileNameBytes = fileName.getBytes();

        InetAddress serverInetAddress = InetAddress.getByName(serverAddress);
        DatagramPacket fileNamePacket = new DatagramPacket(fileNameBytes, fileNameBytes.length, serverInetAddress, serverPort);
        clientSocket.send(fileNamePacket);

        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        while (true) {
            clientSocket.receive(receivePacket);
            String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());

            if (receivedData.equals("END"))
                break;

            System.out.println(receivedData);
        }

        System.out.println("File content received.");

        clientSocket.close();
    }
}