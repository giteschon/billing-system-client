/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.jndi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.BorderPane;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;


/**
 *
 * @author Ivy
 */
public class JndiResource {
private static final String FILEURL="file:";

    private static final String PATH = "C:\\Users\\Ivy\\Documents\\Algebra\\BillingSystem";
    private static final String FILENAME = "jndi_resources.txt";

    private static final String FILEPATH = PATH + "\\" + FILENAME;
    
    //file is needed or it thinks it's url
    private static final String JNDI_FILEPATH = FILEURL + PATH;

    private static final String DELIMITER = ";";

//    //for changing scene widtht and height, ctor take root, width and height
//    private Scene scene;
//
//    private Parent root;
    
    private final BorderPane pane;

    public JndiResource(BorderPane pane) {
        this.pane = pane;
    }
   
    private double width;
    private double height;

    //ovo bi bilo bolje da korisnik moze izabrati resursnu datoteku
    private void readResourceFile(String path) {
        if (path == null) {
            path = FILEPATH;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

//samo da vidim dal dela
//String line=reader.readLine();
//String[] data= line.split(DELIMITER);
// width=Double.valueOf(data[1]);
// height=Double.valueOf(data[1]);
 
 width=readDoubleValueFromLine(reader);
 height=readDoubleValueFromLine(reader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void changeAttributesWithJndi() {
        readResourceFile(null);
        
        //search by name -> context -> initial context
        Hashtable hashtable = new Hashtable();
        hashtable.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.fscontext.RefFSContextFactory");
        hashtable.put(Context.PROVIDER_URL, JNDI_FILEPATH);
        try {

            Context context = new InitialContext(hashtable);
            
           
        
            
            // Properties properties = null;
        
        
            //properties = (Properties) context.lookup("screen");
//             Reference reference = (Reference) context.lookup("screen");
//            System.out.println(reference.toString());
//            context.close();
            
            //search for file in directory specified
            //File file= (File)context.lookup(FILENAME);
//            root.prefWidth(width);
//            root.prefHeight(height);
            //Perform the bind 
            //pane.setPrefWidth(width);
            //pane.setPrefHeight(height);
//            Resources resourceWidth= new Resources("w",String.valueOf(width));
            
            Resources res= createResource("w",width);
            
            
            //bind object-> everythin that object has(props) is binded at the same time
            //context.rebind("screen", resourceWidth.getReference());
            
                context.rebind("width", createResource("w",width).getReference());
//Check that it is bound 
           Object object = context.lookup("width");
//            Reference ref=(Reference)object;
//            RefAddr ra=ref.get(0);
//            //cast to string and then to double
//            Double jndiWidth=Double.valueOf((String)ra.getContent());
            
            pane.setPrefWidth(getJndiDouble(object));
            
            context.rebind("height", createResource("h",height).getReference());
//Check that it is bound 
           object = context.lookup("height");
           pane.setPrefHeight(getJndiDouble(object));
            
            //RADI FAK JEA!!!!!!!!!!!!!!!!!!!!!!!!!!
            //SAD SAMO TREBA NAPRAVIT DA BUDE DOUBLE MUHAHAHAHAHHA
            //I OSTALE STAVKE JES JES JES
            //System.out.println(String.valueOf(jndiWidth));
            
            //System.out.println(msg.toString());
            //System.out.println(object.toString());
//             NameParser parser=context.getNameParser((Name)context.lookup("screen"));
//             
//             Name msg=parser.parse("screen");
             
            // System.out.println(msg.get(0));
            //BorderPane p = (BorderPane) resourceWidth.getReference();
//            Resources s = (Resources) context.lookup("screen");
//            System.out.println(s.toString());
            
           // System.out.println(object);
            
            //pane=(BorderPane)context.lookup("screen");
            
//           Reference ref= new Reference("com.ivanakasalo.gui.LoginController.java");
//           ref.add(new StringRefAddr);
            
            //javax.naming.OperationNotSupportedException: Can only bind References or Referenceable objects
//            root.prefHeight(height);
//            context.bind("height", root);
//            object = context.lookup("favorite");
//            System.out.println(object);
            
            context.close();
        } catch (NamingException ex) {
            Logger.getLogger(JndiResource.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Double readDoubleValueFromLine(BufferedReader reader) {
    try {
        String line=reader.readLine();
        String[] data= line.split(DELIMITER);
        return Double.valueOf(data[1]);
    } catch (IOException ex) {
        Logger.getLogger(JndiResource.class.getName()).log(Level.SEVERE, null, ex);
    }
     return null;
 
    }

    private Resources createResource(String name, double value) {
        return new Resources(name,String.valueOf(value));
    }

    private double getJndiDouble(Object object) {
        
            Reference ref=(Reference)object;
            RefAddr ra=ref.get(0);
            //cast to string and then to double
            return Double.valueOf((String)ra.getContent());
    }

}
