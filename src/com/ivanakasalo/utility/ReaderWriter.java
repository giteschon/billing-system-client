/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.utility;

import com.ivanakasalo.model.Invoice;
import com.ivanakasalo.model.Item;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ivy
 */
public class ReaderWriter {
    private static final String FILEPATH="C:\\Users\\Ivy\\Documents\\Algebra\\BillingSystem";
    private static final String FILENAME="billing_Reflection.html";
    
    private static final String FILENAME_SERIALIZABLE="billing_Serialization.ser";
    
    private static final String PATH  =FILEPATH + "\\" + FILENAME;
    private static final String PATH_SERIALIZABLE=FILEPATH +"\\" + FILENAME_SERIALIZABLE;
    
    
    public static void writeHtml(String text) throws IOException{
    try(FileWriter writer= new FileWriter(new File(PATH), false)){
        
        writer.append(text);
        
    }catch (FileNotFoundException e){
        System.out.println(e.getMessage());
    }
    }
    
    public static void writeSerialization(ArrayList<ArrayList<Item>> list){
        try (ObjectOutputStream stream= new ObjectOutputStream(new FileOutputStream(PATH_SERIALIZABLE))){
            
            stream.writeObject(list);
        } catch (IOException ex) {
           
        }
    }
    
    public  static ArrayList<ArrayList<Item>> readSerialization() throws ClassNotFoundException{
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(PATH_SERIALIZABLE));
            Object object= stream.readObject();
            ArrayList<ArrayList<Item>> list= null;
            if (object instanceof ArrayList) {
               list = (ArrayList<ArrayList<Item>>) object;
            }
            return  list;
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
        
                
        
//         try (ObjectOutputStream stream = new ObjectOutputStream(
//                new FileOutputStream(FILE_PATH))) {
//            stream.writeObject(cardsList);
//        } catch (IOException ex) {
//            Logger.getLogger(SerializationPrimjer.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
//
//            Object object = inStream.readObject();
//
//            if (object instanceof ArrayList) {
//                List<Card> lista = (List<Card>) object;
//
//                for (Card card : lista) {
//                    System.out.println(card.getNumber() + " " + card.getType());
//                }
//            }

      
        
        
    }

