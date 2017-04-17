/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Timestamp;
import java.time.Instant;
import java.util.*;
import java.io.*;
import java.time.Duration;
/**
 *
 * @author melan
 */
/*
Loads a default/previously saved instant, compares to current instant and outputs
difference, then saves current instant.
*/
public class Timestamp {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    
    static long T = 0;
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        Instant thisinst = Instant.now();
        System.out.println(thisinst.toString());
        
        
        ///SET DEFAULT PROPS
        ///create appProperties
        Properties appProps = new Properties();
        File f = new File("appProperties.properties");

            if(!f.exists() || f.isDirectory()) { 
                System.out.println("No save found, using default");                
                ///Load defaultProperties and initialize default timestamp
                try(BufferedReader in = 
                    new BufferedReader(new FileReader("defaultProperties.properties"))){
                    appProps.load(in);
                }
                //appProps.setProper
            }   
            else{
                System.out.println("Save found");
         
                ///Load appProperties into appProps
                try(BufferedReader in = 
                    new BufferedReader(new FileReader("appProperties.properties"))){                            
                    appProps.load(in);
                }
                ///calculate time difference in seconds
                Instant lastinst = Instant.parse(appProps.getProperty("timestamp"));
                T = Duration.between(lastinst,thisinst).getSeconds();
            }
        
        
        System.out.println("T:"+T);
        ///update timestamp
        appProps.setProperty("timestamp",thisinst.toString());
        
        ///Write to appProperties
        try(BufferedWriter out = 
                new BufferedWriter(new FileWriter("appProperties.properties"))) {
            appProps.store(out, null);
        }
        
    }
    
}
