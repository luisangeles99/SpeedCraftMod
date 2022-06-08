package com.equipo7.speedcraftmod;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MinecraftMod.MOD_ID)
public class SpeedcraftMod extends MinecraftMod
{
    private static SpeedcraftMod instance; //TODO: Remove if singleton does not work
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    private static int modDifficulty = 1;
    public static boolean isRunning = false;
    private static boolean worldReady = false;
    public static boolean resetWorld = false;
    public static boolean picTaken = false;
    private int ticks = 0;

    public static int modelResult = 0;

    private Random rand = new Random();

    public SpeedcraftMod() {


        if(instance != null){
            return;
        }
        else{
            instance = this;
            LOGGER.info("Object assigned to instance; SINGLETON");
        }


        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
        isRunning = true;
    }

    @SubscribeEvent
    public void onEntityJoinsWorld(EntityJoinWorldEvent event){
        if (event.getEntity() instanceof LocalPlayer){
            LOGGER.info("Got the player");
            worldReady = true;
            resetWorld = true;
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if(worldReady && resetWorld) {
                if (ticks == 50 && !picTaken) {
                    setUpScreen();
                } else if (picTaken && ticks == 150) {
                    nextReset();
                } else {
                    ticks++;
                    LOGGER.info("tick " + ticks);
                }
            } else{
                ticks = 0;
            }
        }
    }


    @Override
    protected void setUpScreen() {
        ImageEncoder imageEncoder = new ImageEncoder();
        imageEncoder.takeScreenShot();
    }

    @Override
    protected void nextReset() {
        RealImgProcessor proxy = new RealImgProcessor();
        proxy.readLastImage();
        stopReset();
    }

    @Override
    protected void resetWorld() {
        Minecraft mc = Minecraft.getInstance();
        mc.level.disconnect();
        mc.clearLevel();
        TitleScreen titlescreen = new TitleScreen();
        mc.setScreen(titlescreen);


    }

    @Override
    protected void stopReset() {
        if(modelResult == 1){
            resetWorld = false;
        }
        else{
            picTaken = false;
            worldReady = false;
            resetWorld();
        }
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
