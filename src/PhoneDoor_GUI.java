import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PhoneDoor_GUI{
  boolean debug = true;	
  JFrame frame;
  JPanel panel;
  JButton button1,button2,button3,button4,button5;
  JTextArea area;
  JScrollPane pane;
  Thread thread;
  DatagramSocket socketout;

  public static void main(String[] args) {
	  PhoneDoor_GUI u = new PhoneDoor_GUI();
  }
 
  public PhoneDoor_GUI(){

  final String SERVERIP = "192.168.0.177"; // IP adress of arduino ethernet
  final int SERVERPORT = 8888;

  // read ini file for settings

  // Determining OS, reading settings		
		
			  int OS = PlatformDetector.detect();
			    try {
			        if (OS == PlatformDetector.WINDOWS)
			        	try {
			        		final List<String> lignes = Files.readAllLines(FileSystems.getDefault().getPath("C:\\Phone_door\\config.txt"), StandardCharsets.ISO_8859_1);//ISO-8859-1  UTF_8
			        		String audiofilepath = lignes.get(0);
			        		System.out.println("bla bla" + audiofilepath);
			        		
			        			for (String ligne : lignes)
			        			System.out.println(ligne);
			        		
			        	  		
			        	  } catch (IOException e2) {
			        		// TODO Auto-generated catch block
			        		e2.printStackTrace();
			        	}     

			        else { // assume Unix or Linux
			        	try {
			        		final List<String> lignes = Files.readAllLines(FileSystems.getDefault().getPath("C:\\Phone_door\\config.txt"), StandardCharsets.UTF_8);
			        		
			        			for (String ligne : lignes)
			        			System.out.println(ligne);
			        		
			        	  } catch (IOException e2) {
			        		// TODO Auto-generated catch block
			        		e2.printStackTrace();
			        }
			        }
			        } catch (Exception e) {
			        JOptionPane.showMessageDialog(null,
			             ":\n" + e.getLocalizedMessage());
			      }
			 
  frame = new JFrame("COMMANDES");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setUndecorated(true);
  frame.getRootPane()
  .setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
  panel = new JPanel();
  panel.setLayout(null);
  area = new JTextArea();
  area.setEditable(false);
  // button1 settings and usage
  button1 = new JButton("OUVRIR PORTAIL");
  button1.setBounds(10, 10, 320, 40);
  button1.addActionListener(new ActionListener(){
  public void actionPerformed(ActionEvent ae){
  try {
	InetAddress serverAddr = InetAddress.getByName(SERVERIP);
	socketout = new DatagramSocket();
	socketout.connect(serverAddr,SERVERPORT);
	SendDataToNetwork( "button ouvrir portail pressed",socketout,serverAddr, SERVERPORT);
	area.append("\n COMMANDE OUVRIR PORTAIL ENVOYEE");
	} catch (UnknownHostException e1) {
		JOptionPane.showMessageDialog(null,
	             ":\n" + e1.getLocalizedMessage());
	e1.printStackTrace();
	} catch (SocketException e) {
		 JOptionPane.showMessageDialog(null,"socket problem" + 
	             ":\n" + e.getLocalizedMessage());
	e.printStackTrace();
	} catch(Exception error){
    System.out.print("could't to connect to server because : " + error.getMessage());
  }	
  }
  });
  panel.add(button1);
 
// button2 settings and usage
  button2 = new JButton("OUVRIR UN BATTANT");
  button2.setBounds(10, 90, 320, 40);
  button2.addActionListener(new ActionListener(){
  public void actionPerformed (ActionEvent ae){

 
  try {
	InetAddress serverAddr = InetAddress.getByName(SERVERIP);
	socketout = new DatagramSocket();
	socketout.connect(serverAddr,SERVERPORT);
	SendDataToNetwork( "button ouvrir BATTANT pressed",socketout,serverAddr, SERVERPORT);
	 area.append("\n COMMANDE OUVRIR BATTANT ENVOYEE");
	} catch (UnknownHostException e1) {
	// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null,
	             ":\n" + e1.getLocalizedMessage());
	e1.printStackTrace();
	} catch (SocketException e) {
		 JOptionPane.showMessageDialog(null,"socket problem" + 
	             ":\n" + e.getLocalizedMessage());
	} catch(Exception error){
    System.out.print("could't to connect to server because : " + error.getMessage());
  }	
  }
  });
  button2.setEnabled(true);
  panel.add(button2);
//button3 settings and usage  
  button3 = new JButton("PARLER");
  button3.setBounds(10, 170, 320, 40);
  button3.addActionListener(new ActionListener(){
  public void actionPerformed (ActionEvent ae){
	  try {
	//		No real need, the aim is to activate the mic and send it to the micro...
		  	InetAddress serverAddr = InetAddress.getByName(SERVERIP);
			socketout = new DatagramSocket();
			socketout.connect(serverAddr,SERVERPORT);
			SendDataToNetwork( "button ouvrir PARLER pressed",socketout,serverAddr, SERVERPORT);
			area.append("\n PARLER");
			} catch (UnknownHostException e1) {
				 JOptionPane.showMessageDialog(null,
			             ":\n" + e1.getLocalizedMessage());
			e1.printStackTrace();
			} catch (SocketException e) {
				 JOptionPane.showMessageDialog(null,"socket problem" + 
			             ":\n" + e.getLocalizedMessage());
			e.printStackTrace();
			} catch(Exception error){
		    System.out.print("could't to connect to server because : " + error.getMessage());
		  }	
		  }
  });
  button3.setEnabled(true);
  panel.add(button3);
//button4 settings and usage  
  button4 = new JButton("SPARE");
  button4.setBounds(10, 450, 320, 40);
  button4.addActionListener(new ActionListener(){
  public void actionPerformed (ActionEvent ae){
	  try {
			InetAddress serverAddr = InetAddress.getByName(SERVERIP);
			socketout = new DatagramSocket();
			socketout.connect(serverAddr,SERVERPORT);
			SendDataToNetwork( "button SPARE pressed",socketout,serverAddr, SERVERPORT);
			  area.append("\n SPARE"); 
			} catch (UnknownHostException e1) {
				 JOptionPane.showMessageDialog(null,
			             ":\n" + e1.getLocalizedMessage());
			e1.printStackTrace();
			} catch (SocketException e) {
			// TODO Auto-generated catch block
				 JOptionPane.showMessageDialog(null,"socket problem" + 
			             ":\n" + e.getLocalizedMessage());
			e.printStackTrace();
			} catch(Exception error){
		    System.out.print("could't to connect to server because : " + error.getMessage());
		  }	
		  }
  });
  button4.setEnabled(true);
  panel.add(button4);
//button5 settings and usage  
  button5 = new JButton("SPARE 2");
  button5.setBounds(10, 530, 320, 40);
  button5.addActionListener(new ActionListener(){
  public void actionPerformed (ActionEvent ae){
	  try {
			InetAddress serverAddr = InetAddress.getByName(SERVERIP);
			socketout = new DatagramSocket();
			socketout.connect(serverAddr,SERVERPORT);
			SendDataToNetwork( "button SPARE 2 pressed",socketout,serverAddr, SERVERPORT);
			area.append("\n SPARE 2"); 
			} catch (UnknownHostException e1) {
				JOptionPane.showMessageDialog(null,
			             ":\n" + e1.getLocalizedMessage());
			e1.printStackTrace();
			} catch (SocketException e) {
				 JOptionPane.showMessageDialog(null,"socket problem" + 
			             ":\n" + e.getLocalizedMessage());
			} catch(Exception error){
		    System.out.print("could't to connect to server because : " + error.getMessage());
		  }	
		  }
  
  });
  button5.setEnabled(true);
  panel.add(button5);
  
  pane = new JScrollPane(area);
  pane.setBounds(10, 230, 320, 200);
  panel.add(pane);
  frame.add(panel);
  frame.setSize(350, 600);
  frame.setVisible(true);
  
  
  }
  
	 public void SendDataToNetwork(String cmd,DatagramSocket chaussette,InetAddress Addrserver,int SERVERPORT) { //You run this from the main thread. chaussette = socket in french
	       try {
		 			/* Create new UDP-Socket */ /* Create UDP-packet with data & destination(url+port) */
		 			byte[] c = cmd.getBytes();
		 			DatagramPacket packetcmd = new DatagramPacket(c, c.length, Addrserver,SERVERPORT);
		 			chaussette.send(packetcmd);
		       } catch (Exception e) {
		    	   area.append("COMMANDE OUVRIR UN BATTANT ENVOYEE" + e.getMessage());
		       }
		   }
  }
