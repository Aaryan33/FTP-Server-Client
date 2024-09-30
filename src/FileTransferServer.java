import java.io.*;
import java.net.*;

public class FileTransferServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(12345); // Server port

        System.out.println("Server started. Waiting for a client to connect...");

        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        serverSocket.receive(receivePacket);

        String requestedFileName = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Requested file: " + requestedFileName);

        BufferedReader fileReader = new BufferedReader(new FileReader(requestedFileName));
        String line;

        while ((line = fileReader.readLine()) != null) {
            byte[] sendData = line.getBytes();
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        }

        // Sending "END" to indicate the end of the file
        byte[] endSignal = "END".getBytes();
        DatagramPacket endPacket = new DatagramPacket(endSignal, endSignal.length, receivePacket.getAddress(), receivePacket.getPort());
        serverSocket.send(endPacket);

        System.out.println("File content sent to client.");

        fileReader.close();
        serverSocket.close();
    }
}