package roua_bouassida_tp4;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
	
	private static final int PORT = 1234;
	private static final int buffLen = 1024;
	private static byte[] buffer = new byte[buffLen];
	
	public static void main(String[] args) {
		try {
			// Reservation du port 1234
			DatagramSocket socket = new DatagramSocket(PORT);
			
			System.out.println("Démarrage du server.");
			
			while(true) {
				
				// Preparation du packet dont on collecte les bytes reçus du Client
				DatagramPacket packet = new DatagramPacket(buffer, buffLen);
				
				// Reception des octets
				socket.receive(packet);
				System.out.println("Packet reçu.");
				
				// Transformation les octets dans buffer vers une chaine des caractéres.
				// new String(byte[] buff, startIdx, endIdx);
				String userName = new String(packet.getData(), 0, packet.getLength());
				System.out.println("Le client " + userName + " est connecté ( " + packet.getAddress() + " ).");
				
				// Preparation du noveau message.
				userName = "Bienvenue " + userName;
				
				// Preparation du packet à envoyer.
				DatagramPacket packetSend = new DatagramPacket(userName.getBytes(), userName.length(), packet.getAddress(), packet.getPort());
				
				// L'envoi du noveau packet.
				socket.send(packetSend);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

