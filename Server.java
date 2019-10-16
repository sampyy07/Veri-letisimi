import java.io.*;
import java.net.*;
public class Server {

  public final static int SOCKET_PORT = 300;  
  public final static String FILE_TO_SEND = "C:\\Users\\MyC\\Desktop\\MyClient\\Selami.txt"; 
  
  public static void main (String [] args ) throws IOException {
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
    Socket sock = null;
    try {
      servsock = new ServerSocket(SOCKET_PORT);
      while (true) {
        System.out.println("Baglanti Bekleniyor...");
        try {
          sock = servsock.accept();
          System.out.println("Baglanildi : " + sock);
          File myFile = new File (FILE_TO_SEND);
          byte [] mybytearray  = new byte [(int)myFile.length()];
          fis = new FileInputStream(myFile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          os = sock.getOutputStream();
          System.out.println("Gonderiliyor " + FILE_TO_SEND + "(" + mybytearray.length + " byte)");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Tamamlandi.");
        }
        finally {
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();
        }
      }
    }
    finally {
      if (servsock != null) servsock.close();
    }
  }
}