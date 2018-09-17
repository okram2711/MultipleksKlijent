package projektovanje.connect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static projektovanje.connect.KonekcijaNET.CONFIG;

/**
 *
 * @author Marko
 */
public class Test {
    
     public static void main(String args[]){
        Properties p=new Properties();
        try {
            Properties prop=new Properties();
            FileInputStream input=new FileInputStream(CONFIG);
            prop.load(input);
            System.out.println(prop.getProperty("serverAddress"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KonekcijaNET.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
             Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
