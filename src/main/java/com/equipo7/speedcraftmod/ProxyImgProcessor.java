/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipo7.speedcraftmod;

/**
 *
 * @author jpdia
 */
public class ProxyImgProcessor extends ImageProcessor{
    private RealImgProcessor realProcessor;
    
    @Override
    public void readLastImage(){
        if(realProcessor == null){
            realProcessor = new RealImgProcessor();
        }
        
        realProcessor.readLastImage();
    }
}
