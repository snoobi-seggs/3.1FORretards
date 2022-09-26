package emu.grasscutter.config;

import com.google.gson.JsonObject;
import emu.grasscutter.Grasscutter;
import emu.grasscutter.Grasscutter.ServerDebugMode;
import emu.grasscutter.Grasscutter.ServerRunMode;
import emu.grasscutter.utils.JsonUtils;

import java.util.Set;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Locale;

import static emu.grasscutter.Grasscutter.config;

/**
 * *when your JVM fails*
 */
public class ConfigContainer {
    private static int version() {
        return 4;
    }

    /**
     * Attempts to update the server's existing configuration to the latest
     */
    public static void updateConfig() {
        try { // Check if the server is using a legacy config.
            JsonObject configObject = JsonUtils.loadToClass(Grasscutter.configFile.getPath(), JsonObject.class);
            if (!configObject.has("version")) {
                Grasscutter.getLogger().info("Updating legacy ..");
                Grasscutter.saveConfig(null);
            }
        } catch (Exception ignored) { }

        var existing = config.version;
        var latest = version();

        if (existing == latest)
            return;

        // Create a new configuration instance.
        ConfigContainer updated = new ConfigContainer();
        // Update all configuration fields.
        Field[] fields = ConfigContainer.class.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                field.set(updated, field.get(config));
            } catch (Exception exception) {
                Grasscutter.getLogger().error("Failed to update a configuration field.", exception);
            }
        }); updated.version = version();

        try { // Save configuration & reload.
            Grasscutter.saveConfig(updated);
            Grasscutter.loadConfig();
        } catch (Exception exception) {
            Grasscutter.getLogger().warn("Failed to inject the updated ", exception);
        }
    }

    public Structure folderStructure = new Structure();
    public Database databaseInfo = new Database();
    public Language language = new Language();
    public Account account = new Account();
    public Server server = new Server();

    // DO NOT. TOUCH. THE VERSION NUMBER.
    public int version = version();

    /* Option containers. */

    public static class Database {
        public DataStore server = new DataStore();
        public DataStore game = new DataStore();

        public static class DataStore {
            public String connectionUri = "mongodb://localhost:27017";
            public String collection = "grasscutter";
        }
    }

    public static class Structure {
        public String resources = "./resources/";
        public String data = "./data/";
        public String packets = "./packets/";
        public String scripts = "./resources/Scripts/";
        public String plugins = "./plugins/";

        // UNUSED (potentially added later?)
        // public String dumps = "./dumps/";
    }

    public static class Server {
        public Set<Integer> debugWhitelist = Set.of();
        public Set<Integer> debugBlacklist = Set.of();
        public ServerRunMode runMode = ServerRunMode.HYBRID;

        public HTTP http = new HTTP();
        public Game game = new Game();

        public Dispatch dispatch = new Dispatch();
    }

    public static class Language {
        public Locale language = Locale.getDefault();
        public Locale fallback = Locale.US;
        public String document = "EN";
    }

    public static class Account {
        public boolean autoCreate = true;
        public boolean EXPERIMENTAL_RealPassword = false;
        public String[] defaultPermissions = {"*"};
        public int maxPlayer = -1;
    }

    /* Server options. */

    public static class HTTP {
        public String bindAddress = "0.0.0.0";
        public int bindPort = 443;

        /* This is the address used in URLs. */
        public String accessAddress = "127.0.0.1";
        /* This is the port used in URLs. */
        public int accessPort = 0;

        public Encryption encryption = new Encryption();
        public Policies policies = new Policies();
        public Files files = new Files();
    }

    public static class Game {
        public String bindAddress = "0.0.0.0";
        public int bindPort = 22102;

        /* This is the address used in the default region. */
        public String accessAddress = "127.0.0.1";
        /* This is the port used in the default region. */
        public int accessPort = 0;

        /* Entities within a certain range will be loaded for the player */
        public int loadEntitiesForPlayerRange = 100;
        public boolean enableScriptInBigWorld = false;
        public boolean enableConsole = true;

        /* Kcp internal work interval (milliseconds) */
        public int kcpInterval = 20;
        /* Controls whether packets should be logged in console or not */
        public ServerDebugMode logPackets = ServerDebugMode.NONE;

        public GameOptions gameOptions = new GameOptions();
        public JoinOptions joinOptions = new JoinOptions();
        public ConsoleAccount serverAccount = new ConsoleAccount();
    }

    /* Data containers. */

    public static class Dispatch {
        public Region[] regions = {};

        public String defaultName = "Grasscutter";

        public ServerDebugMode logRequests = ServerDebugMode.NONE;
    }

    public static class Encryption {
        public boolean useEncryption = true;
        /* Should 'https' be appended to URLs? */
        public boolean useInRouting = true;
        public String keystore = "./keystore.p12";
        public String keystorePassword = "123456";
    }

    public static class Policies {
        public Policies.CORS cors = new Policies.CORS();

        public static class CORS {
            public boolean enabled = false;
            public String[] allowedOrigins = new String[]{"*"};
        }
    }

    public static class GameOptions {
        public InventoryLimits inventoryLimits = new InventoryLimits();
        public AvatarLimits avatarLimits = new AvatarLimits();
        public int sceneEntityLimit = 10000; // Unenforced. TODO: Implement.

        public boolean watchGachaConfig = false;
        public boolean enableShopItems = true;
        public boolean staminaUsage = false;
        public boolean energyUsage = false;
        public boolean fishhookTeleport = true;
        public ResinOptions resinOptions = new ResinOptions();
        public Rates rates = new Rates();

        public static class InventoryLimits {
            public int weapons = 20000;
            public int relics = 20000;
            public int materials = 20000;
            public int furniture = 20000;
            public int all = 100000;
        }

        public static class AvatarLimits {
            public int singlePlayerTeam = 40;
            public int multiplayerTeam = 40;
        }

        public static class Rates {
            public float adventureExp = 1.0f;
            public float mora = 1.0f;
            public float leyLines = 1.0f;
        }

        public static class ResinOptions {
            public boolean resinUsage = false;
            public int cap = 160;
            public int rechargeTime = 480;
        }
    }

    public static class JoinOptions {
        public int[] welcomeEmotes = {2007};
        public String welcomeMessage = "die";
        public JoinOptions.Mail welcomeMail = new JoinOptions.Mail();

        public static class Mail {
            public String title = "welcome to snoobi";
            public String content = "snoobi x orang\rcome succ paimon off\rRUN NEW COMMAND /givenew or /gn to get the new items in 3.1!\r\rAlso use /prop um 1 to unlock desert region.\r";
            public String sender = "Benj's nipples";
            public emu.grasscutter.game.mail.Mail.MailItem[] items = {
                    new emu.grasscutter.game.mail.Mail.MailItem(223, 99999, 1),
                    new emu.grasscutter.game.mail.Mail.MailItem(201, 99999, 1),
					new emu.grasscutter.game.mail.Mail.MailItem(224, 99999, 1)
            };
        }
    }

    public static class ConsoleAccount {
        public int avatarId = 10000070;
        public int nameCardId = 210133;
        public int adventureRank = 60;
        public int worldLevel = 8;

        public String nickName = "Paimon's Nipples";
        public String signature = "no bitches? go fuck urself";
    }

    public static class Files {
        public String indexFile = "./index.html";
        public String errorFile = "./404.html";
    }

    /* Objects. */

    public static class Region {
        public Region() { }

        public Region(
                String name, String title,
                String address, int port
        ) {
            this.Name = name;
            this.Title = title;
            this.Ip = address;
            this.Port  = port;
        }

        public String Name = "os_usa";
        public String Title = "Grasscutter";
        public String Ip = "127.0.0.1";
        public int Port = 22102;
    }
}
