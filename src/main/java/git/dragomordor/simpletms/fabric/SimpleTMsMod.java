package git.dragomordor.simpletms.fabric;

import git.dragomordor.simpletms.fabric.config.SimpleTMsConfig;
import git.dragomordor.simpletms.fabric.item.SimpleTMsItemGroups;
import git.dragomordor.simpletms.fabric.item.SimpleTMsItems;
import git.dragomordor.simpletms.fabric.event.ModEvents;
import git.dragomordor.simpletms.fabric.util.TMsTRsList;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleTMsMod implements ModInitializer {
    public static final Integer BLACK = 0;
    public static final Integer DARK_BLUE = 170;
    public static final Integer DARK_GREEN = 43520;
    public static final Integer DARK_AQUA = 43690;
    public static final Integer DARK_RED = 11141120;
    public static final Integer DARK_PURPLE = 11141290;
    public static final Integer GOLD = 16755200;
    public static final Integer GRAY = 11184810;
    public static final Integer DARK_GRAY = 5592405;
    public static final Integer BLUE = 5592575;
    public static final Integer GREEN = 5635925;
    public static final Integer AQUA = 5636095;
    public static final Integer RED = 16733525;
    public static final Integer LIGHT_PURPLE = 16733695;
    public static final Integer YELLOW = 16777045;
    public static final Integer WHITE = 16777215;

    public static final Logger LOGGER = LogManager.getLogger(SimpleTMsMod.class); // create logger
    public static final String MODID = "simpletms"; // mod ID

    @Override
    public void onInitialize() {
        // Load config file
        SimpleTMsConfig.loadConfig();
        // Register all items
        SimpleTMsItems.registerModItems(); // register generic items
        // Register creative tabs
        SimpleTMsItemGroups.registerItemGroups(); //register TM item tab

        // Register TM list
        TMsTRsList.registerTMList();
        // Register events
        ModEvents.registerEvents();
    }
}

