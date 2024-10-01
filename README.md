# FTP-Server-Client

The FTP Client-Server project implements a File Transfer application using UDP sockets in Java. The project consists of two main components: `FileTransferClient.java` and `FileTransferServer.java`.

## Components

1. **FileTransferClient**: 
   - Responsible for sending a file request to the server and receiving the file content.
   - Prompts the user to enter the name of the file they wish to transfer.
   - Listens for the file content and prints each line received until an "END" signal is encountered.

2. **FileTransferServer**: 
   - Listens for incoming requests from clients on a specified port.
   - Reads the requested file line by line and sends each line back to the client using UDP packets.
   - Sends an "END" signal to notify the client that the transfer is complete.

## Getting Started

### Step 1: Clone the Repository
Clone this repository to your local machine using:
```bash
https://github.com/Aaryan33/FTP-Server-Client.git
```

### Step 2: Compile the Code
```bash
javac FileTransferClient.java FileTransferServer.java
```
### Step 3: Run the Server
```bash
java FileTransferServer
```

### Step 4: Run the Client
```bash
java FileTransferClient
```
