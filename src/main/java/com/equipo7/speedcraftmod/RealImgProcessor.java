/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipo7.speedcraftmod;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jpdia
 */
public class RealImgProcessor extends ImageProcessor {
    
    @Override
    public void readLastImage(){
        String [] command = {"python", "/Users/luisalbertoangeles/Desktop/GITHUB/SpeedCraftMod/src/main/java/com/equipo7/speedcraftmod/dummy.py"};
        
        ProcessBuilder processBuilder = new ProcessBuilder(command); 
        processBuilder.directory(new File(System.getProperty("user.home")));
        
        try {
            Process process = processBuilder.start();
            
            BufferedReader reader = 
                new BufferedReader (new InputStreamReader(process.getInputStream()));
            
            String line;
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                // TODO: return to main class client
                SpeedcraftMod.modelResult = Integer.parseInt(line);
            }
            
            int exitCode = process.waitFor(); 
            
            System.out.println ("\nExited with error code : " + exitCode);
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(RealImgProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("ProyectoArqui");
        }
    
}
