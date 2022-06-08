package com.equipo7.speedcraftmod.mixin;

import com.equipo7.speedcraftmod.SpeedcraftMod;
import net.minecraft.client.gui.components.EditBox;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mixin(CreateWorldScreen.class)
public abstract class ModController {

    @Shadow
    private EditBox nameEdit;

    @Shadow
    protected abstract void onCreate();

    private static final Logger LOGGER = LogManager.getLogger();
    @Inject(method = "init", at = @At("TAIL"))
    private void startMixin(CallbackInfo info){
        if(SpeedcraftMod.resetWorld){
            nameEdit.setValue("Test world");
            onCreate();
        }
    }


}
