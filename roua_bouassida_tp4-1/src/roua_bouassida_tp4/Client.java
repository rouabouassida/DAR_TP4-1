package roua_bouassida_tp4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	private static final int PORT = 1234;
	private static final int buffLen = 1024;
	private static final byte[] buff = new byte[buffLen];
	
	public static void main(String[] args) {
		
		// Lecture du nom.
		Scanner scanner = new Scanner(System.in);
		System.out.print("Donner votre nom : ");
		
		String userName;
		
		do {
			userName = scanner.nextLine();
		}while(userName.length() == 0);
		
		scanner.close();
		
		try {
			
			InetAddress serverAddress = InetAddress.getByName("localhost");
			
			DatagramSocket socket = new DatagramSocket();
			
			// Préparation du paquet à envoyer contenant le contenu du "userName"
			DatagramPacket sendPacket = new DatagramPacket(userName.getBytes(), userName.getBytes().length, serverAddress, PORT);
			
			// Envoi du paquet
			socket.send(sendPacket);
			
			System.out.println("Message envoyé vers le server");
			
			// Préparation d'un paquet pour recevoir le résultat du serveur
			DatagramPacket packet = new DatagramPacket(buff, buffLen, serverAddress, PORT);
			
			socket.receive(packet);
			
			// Transformer les données d'octets en chaîne de caractères
			String resultat = new String(packet.getData(), 0, buffLen);
			
			System.out.println("Resultat : " + resultat);
			
			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
