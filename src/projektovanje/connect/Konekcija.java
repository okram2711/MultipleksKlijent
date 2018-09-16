package projektovanje.connect;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Konekcija {

    public static final int TCP_PORT = 9000;

    private Socket socket;
    private InetAddress addr;
    public ObjectInputStream is;
    public ObjectOutputStream os;

    public Konekcija() {

        try {
            addr = InetAddress.getByName("127.0.0.1");
            socket = new Socket(addr, TCP_PORT);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
