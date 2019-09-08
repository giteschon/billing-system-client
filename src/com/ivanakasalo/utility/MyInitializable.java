/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.utility;

import com.ivanakasalo.bl.BillingManager;
import javafx.fxml.Initializable;

/**
 *
 * @author Ivy
 */
public interface MyInitializable extends Initializable {
    BillingManager manager= new BillingManager();
}
