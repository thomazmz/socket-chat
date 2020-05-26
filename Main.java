import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static String terminateCommand = "FIM";
    private static Scanner scanner = new Scanner(System.in);
    private static PrintStream outputStream = System.out;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Formato "client port host"
        if(args.length == 3 && args[0].equals("client")) {
            startClient(args[2], Integer.parseInt(args[1]));

            // Formato "client port"
        } else if(args.length == 2 && args[0].equals("client")) {
            startClient("localhost", Integer.parseInt(args[1]));

            // Formato "server port"
        } else if(args.length == 2 && args[0].equals("server")) {
            startServer(Integer.parseInt(args[1]));

            // Formato inválido
        } else {
            print("Parâmetros inválidos");
        }
    }

    public static void startClient(String host, Integer port) throws IOException, ClassNotFoundException {

        while(true) {

            // Cria um socket de conexão
            Socket socket = new Socket(host, port);

            // Armazena a mensagem digitada pelo usuário
            print(String.format("%-10s", "Cliente:"));
            String clientMessage = readString();

            // Envia a mensagem digitada pelo usuário para o servidor
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(clientMessage);

            // Fecha o chat se cliente enviar comando para encerrar
            if(clientMessage.equalsIgnoreCase(terminateCommand)) break;

            // Lê a mensagem enviada pelo servidor
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            String serverMessage = (String) input.readObject();

            // Exibe mensagem de resposta do servidor
            print(String.format("%-10s", "Servidor:"));
            print(serverMessage);
            print("\n");

            // Fecha o chat se servidor enviar comando para encerrar
            if (serverMessage.equalsIgnoreCase(terminateCommand)) break;

            // Close resources
            input.close();
            output.close();

        }

        System.out.println("Chat encerrado");

    }

    public static void startServer(Integer port) throws IOException, ClassNotFoundException {

        // Cria um socket de conexão
        ServerSocket server = new ServerSocket(port);

        // Dá ao usuário algum feedback
        print("Aguardando conexão do cliente... \n");

        while(true){

            // Espera pela conexão do cliente
            Socket socket = server.accept();

            // Lê a mensagem do cliente
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Exibe a mensagem do cliente
            String clientMessage = (String) input.readObject();
            print(String.format("%-10s", "Cliente:"));
            print(clientMessage + "\n");

            // Fecha o chat se cliente enviar comando para encerrar
            if(clientMessage.equalsIgnoreCase(terminateCommand)) break;

            // Exibe a mensagem do servidor
            print(String.format("%-10s", "Servidor:"));
            String serverMessage = readString();

            // Send server message to the client
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(serverMessage);

            // Close resources
            input.close();
            output.close();
            socket.close();

            // Fecha o chat se servidor enviar comando para encerrar
            if(serverMessage.equalsIgnoreCase(terminateCommand)) break;
        }

        System.out.println("Chat encerrado");

        // Terminate server
        server.close();

    }

    public static String readString(){
        return scanner.nextLine();
    }

    public static void print(Object object) {
        outputStream.print(object);
    }
}