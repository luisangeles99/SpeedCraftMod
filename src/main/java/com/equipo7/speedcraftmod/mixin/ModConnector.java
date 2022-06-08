package com.equipo7.speedcraftmod.mixin;

import com.equipo7.speedcraftmod.ModConnectorInterface;
import com.equipo7.speedcraftmod.SpeedcraftMod;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.chat.Component;

@Mixin(TitleScreen.class)
public abstract class ModConnector extends Screen implements ModConnectorInterface{
    protected ModConnector(Component title) {
        super(title);
    }
    @Inject(method = "init", at = @At("TAIL"))
    private void initMixin(CallbackInfo info) {
        // If auto reset mode is on, instantly switch to create world menu.
        if (SpeedcraftMod.resetWorld) {
            minecraft.setScreen(CreateWorldScreen.m_100898_(this));
        }
    }
}
