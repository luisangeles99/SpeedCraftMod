package com.equipo7.speedcraftmod;

import com.mojang.blaze3d.pipeline.RenderTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class ImageEncoder {
    private static final Logger LOGGER = LogManager.getLogger();
    public void takeScreenShot(){
        LOGGER.info("TOMO FOTO!");
        RenderTarget mc = Minecraft.getInstance().getMainRenderTarget();
        File f = new File("/Users/luisalbertoangeles/Desktop/GITHUB/SpeedCraftMod/src/main/java/com/equipo7/images");
        Screenshot.grab(f, "ss.png", mc, (cmp) ->{

        });
        SpeedcraftMod.picTaken = true;
    }
}
