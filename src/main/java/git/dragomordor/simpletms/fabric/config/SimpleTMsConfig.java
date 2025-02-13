package git.dragomordor.simpletms.fabric.config;

import git.dragomordor.simpletms.fabric.SimpleTMsMod;
import net.fabricmc.loader.api.FabricLoader;
// import net.minecraft.util.Identifier;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class SimpleTMsConfig {
    // private static final Identifier CONFIG_ID = new Identifier(SimpleTMsMod.MODID, "simpletms_config.properties");
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve(SimpleTMsMod.MODID).resolve("simpletms_config.properties");

    private static boolean eggMovesLearnable = false;
    private static boolean tutorMovesLearnable = false;
    private static boolean allMovesLearnable = false;
    private static int tmCooldownTicks = 0;
    private static boolean imprintableBlankTMs = true;


    private static float trDropPercentChance = 5.0F;
    private static float tmDropPercentChance = 0.01F;

    // private static boolean canCraftTR = true;
    // private static boolean canCraftTM = false;

    public static void loadConfig() {
        if (!Files.exists(CONFIG_PATH)) {
            createConfigFile();
        }

        try (BufferedReader reader = Files.newBufferedReader(CONFIG_PATH)) {
            Properties properties = new Properties();
            properties.load(reader);

            eggMovesLearnable = Boolean.parseBoolean(properties.getProperty("EggMovesLearnable", "false"));
            tutorMovesLearnable = Boolean.parseBoolean(properties.getProperty("TutorMovesLearnable", "false"));
            allMovesLearnable = Boolean.parseBoolean(properties.getProperty("AnyMoveAnyPokemon", "false"));
            tmCooldownTicks = Integer.parseInt(properties.getProperty("TMCooldownTicks", "100"));
            imprintableBlankTMs = Boolean.parseBoolean(properties.getProperty("ImprintableBlankTMs","true"));

            trDropPercentChance = Float.parseFloat(properties.getProperty("TRDropPercentChance", "5.0"));
            tmDropPercentChance = Float.parseFloat(properties.getProperty("TMDropPercentChance", "0.01"));

//            canCraftTR = Boolean.parseBoolean(properties.getProperty("CanCraftTR","true"));
//            canCraftTM = Boolean.parseBoolean(properties.getProperty("CanCraftTM","false"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createConfigFile() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());

            Properties properties = new Properties();
            properties.setProperty("EggMovesLearnable", "false");
            properties.setProperty("TutorMovesLearnable", "false");
            properties.setProperty("AnyMoveAnyPokemon", "false");
            properties.setProperty("TMCooldownTicks", "100");
            properties.setProperty("ImprintableBlankTMs", "true");
            properties.setProperty("TRDropPercentChance", "5.0");
            properties.setProperty("TMDropPercentChance", "0.01");

//            properties.setProperty("CanCraftTR", "true");
//            properties.setProperty("CanCraftTM", "false");

            try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH)) {
                properties.store(writer, "TM Configuration");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean getAreEggMovesLearnable() {
        return eggMovesLearnable;
    }

    public static boolean getAreTutorMovesLearnable() {
        return tutorMovesLearnable;
    }

    public static boolean getAreAllMovesLearnable() {
        return allMovesLearnable;
    }

    public static int getTMCooldownTicks() {
        return tmCooldownTicks;
    }

    public static boolean getImprintableBlankTMs() {
        return imprintableBlankTMs;
    }

    public static float getTRDropChance() {
        return trDropPercentChance;
    }

    public static float getTMDropChance() {
        return tmDropPercentChance;
    }

//    public static boolean getCanCraftTR() {
//        return canCraftTR;
//    }
//
//    public static boolean getCanCraftTM() {
//        return canCraftTM;
//    }
}