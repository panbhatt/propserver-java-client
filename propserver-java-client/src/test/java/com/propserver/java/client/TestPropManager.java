/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.propserver.java.client;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author PBhatt
 */
public class TestPropManager {
    
    static PropManager  propManager = null ; 
     String validKey = "redis" , invalidKey = "ASKDFSLDKF" ;
     

        @BeforeClass
        public static void initialize() {
            
            String  projectName = "VisaCheckout" ;
            String propManagerUrl = "http://localhost:3000" ;
            String environment = "DEV" ;
            String release = "0.0" ;
            
            propManager = PropManager.getInstance(propManagerUrl, projectName, environment, release) ;
        }
        
        @Test
        public void testKeyPresence() {
            
            String keyValue = propManager.get(validKey)  ;
            Assert.assertNotNull(keyValue); 
            Assert.assertTrue(keyValue.length() > 0 ); 
        }
        
        @Test
        public void testInvalidKeyAbsence() {
            
            String keyValue = propManager.get(invalidKey)  ;
            Assert.assertNull(keyValue); 
        }
        
        @Test
        public void testGetAllKeysAsJson() {
            
            String json = propManager.getAllKeys() ;
            Assert.assertNotNull(json); 
            Assert.assertTrue(json.length() > 0 );
        }
        
        
}
