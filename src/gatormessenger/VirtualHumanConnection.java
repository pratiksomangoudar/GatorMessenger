/*
 * This is the Java class with a single function
 * you will be using to connect to the Virtual Human
 */

package gatormessenger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shivahalan
 */
public class VirtualHumanConnection {
    
     public static String getResponse(String userText)
        {
          String urlParameters;
        try {
            urlParameters =
            "model=" + URLEncoder.encode("FindResponseScript", "UTF-8") +
            "&primary_key_value=" + URLEncoder.encode("269", "UTF-8") +
            "&method=" + URLEncoder.encode("FindResponseSpeechText", "UTF-8") +
            "&encoding=" + URLEncoder.encode("json", "UTF-8") +
            "&username=" + URLEncoder.encode("hcispring2013", "UTF-8") +
            "&password_md5=" + URLEncoder.encode("8eb58dd5e328e978169c7b0cbd30d43f", "UTF-8") +
            "&userInput=" + URLEncoder.encode(userText, "UTF-8");          
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(VirtualHumanConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new AssertionError("UTF-8 is unknown");
        } 
            
          URL url;
          HttpURLConnection connection = null;  
          try {
            //Create connection
            url = new URL("http://vpf.cise.ufl.edu/VirtualPeopleFactory/virtual_patient_mvc/View/web_service.php");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", 
                 "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", "" + 
                     Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");  

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                        connection.getOutputStream ());
            wr.writeBytes (urlParameters);
            wr.flush ();
            wr.close ();

            //Get Response	
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer(); 
            while((line = rd.readLine()) != null) {
              response.append(line);
              response.append('\r');
            }
            rd.close();
            return response.toString();

          } catch (Exception e) {

            e.printStackTrace();
            return null;

          } finally {

            if(connection != null) {
              connection.disconnect(); 
            }
          }
        }
    
}