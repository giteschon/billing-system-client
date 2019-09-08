/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.jndi;

import javafx.scene.Parent;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Ivy
 */
public class Resources implements Referenceable{

    //class which makes references so it can be binded
    
    //trebam referencirat parent-a znaci root. moram ga postavit kao private
    
    private String name;
    String value;

    
    //u ctoru ga postavljam, znaci iz jndi klase mjenja sve atribute, sad samo treba napravit referencu

    public Resources(String name, String value) {
        this.name = name;
        this.value=value;
    }
    
    
    
    
    @Override
    public Reference getReference() throws NamingException {
      return new Reference(Resources.class.getName(), new StringRefAddr(name,
       value));
    }
    
    
    
    @Override
    public String toString(){
    
    return value;
    }
    
}
