package me.doragoncraft.dcEssentials;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.doragoncraft.dcEssentials.Listeners.*;
import me.doragoncraft.dcEssentials.commands.*;
import me.doragoncraft.dcEssentials.listeners.FlyListener;
import me.doragoncraft.dcEssentials.managers.*;
import me.doragoncraft.dcEssentials.uti.Lang;
import me.doragoncraft.dcEssentials.uti.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class Dcessentails extends JavaPlugin {

    private Logger log;

    public static YamlConfiguration LANG;
    public static File LANG_FILE;

    private final Set<Player> frPlayer = new HashSet<>();
    private final Set<Player> subtitleDiscord = new HashSet<>();
    private final Set<Player> subTitleWebsite = new HashSet<>();
    private final Set<Player> titleWebsite = new HashSet<>();
    private final Set<Player> store = new HashSet<>();
    private final Set<Player> storeSub = new HashSet<>();
    private final Set<Player> vote = new HashSet<>();
    private final Set<Player> voteSub = new HashSet<>();
    private final Set<Player> vanished = new HashSet<>();

    private TeleportRequestManager teleportRequestManager;
    private CmdSpyListener cmdSpyListener;
    private ProtocolManager protocolManager;
    private MotdListener motdListener;
    private WarpsManager warpsManager; // <-- Declare here
    private FlyManager flyManager;
    private GodManager godManager;
    private KitManager kitManager;


    @Override
    public void onEnable() {
        log = getLogger();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        int pluginId = 5709; // Replace with your actual plugin ID
        Metrics metrics = new Metrics(this, pluginId);

        protocolManager = ProtocolLibrary.getProtocolManager();
        motdListener = new MotdListener(this);
        teleportRequestManager = new TeleportRequestManager();
        cmdSpyListener = new CmdSpyListener(this);
        HomeManager homeManager = new HomeManager(this);
        warpsManager = new WarpsManager(this); // <-- Initialize here
        GamemodeCommand gamemodeCommand = new GamemodeCommand(this);
        flyManager = new FlyManager();
        TpposCommand tpposCommand = new TpposCommand();
        godManager = new GodManager();
        ClearCommand clearCommand = new ClearCommand();
        TimeCommand timeCommand = new TimeCommand(this);
        kitManager = new KitManager();

        Bukkit.getConsoleSender().sendMessage("§b  ____   §a _____ ");
        Bukkit.getConsoleSender().sendMessage("§b |  _ \\  §a| ____| §8DC DCEssentials §6v" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§b | | | | §a| |     §c Running on " + getServer().getName());
        Bukkit.getConsoleSender().sendMessage("§b | |_| | §a| |___   §4By Doragoncraft");
        Bukkit.getConsoleSender().sendMessage("§b |____/  §a|_____| §fDCEssentials Premium Copyright © 2019 By DoragonCraft");
        Bukkit.getConsoleSender().sendMessage("     §6Thank you for purchasing DCEssentials!           ");

        loadLang();

        // Register commands safely
        registerCommand("discord", new Discord(this));
        registerCommand("ip", new ServerIP(this));
        registerCommand("store", new StoreCmd(this));
        registerCommand("youtube", new YouTubeCmd(this));
        registerCommand("twitch", new Twitch(this));
        registerCommand("links", new Links(this));
        registerCommand("setgui", new GuiOpen(this));
        registerCommand("website", new Website(this));
        registerCommand("decp", new CommandManager(this));
        registerCommand("message", new LinkSP(this));
        registerCommand("ctc", new Ctc(this));
        registerCommand("vote", new Vote(this));
        registerCommand("playerip", new Whois(this));
        registerCommand("vanish", new VanishCMD(this));
        registerCommand("craft", new Craft(this));
        registerCommand("trash", new Trash(this));
        registerCommand("fly", new FlyCommand(this));
        registerCommand("gamemode", new GamemodeCommand(this));
        getCommand("gamemode").setTabCompleter(gamemodeCommand);
        registerCommand("speed", new SpeedCommand(this));
        registerCommand("weather", new WeatherCommand(this ));
        registerCommand("sun", new WeatherCommand(this));
        registerCommand("rain", new WeatherCommand(this));
        registerCommand("storm", new WeatherCommand(this));
        registerCommand("thunder", new WeatherCommand(this));
        registerCommand("time", new TimeCommand(this));
        registerCommand("feed", new Heal(this));
        registerCommand("heal", new Heal(this));
        registerCommand("anvil", new Anvil(this));
        registerCommand("spawn", new SpawnCMD(this));
        registerCommand("setspawn", new SpawnCMD(this));
        registerCommand("tp", new TpCommand(this));
        registerCommand("tpa", new TpaCommand(teleportRequestManager, this));
        registerCommand("tpaccept", new TpAcceptCommand(teleportRequestManager, this));
        registerCommand("tpdeny", new TpDenyCommand(teleportRequestManager, this));
        registerCommand("tptoggle", new TpToggleCommand(teleportRequestManager, this));
        registerCommand("tpo", new TpoCommand(this));
        registerCommand("tphere", new TphereCommand(this));
        registerCommand("tpahere", new TpahereCommand(teleportRequestManager, this));
        registerCommand("tppos", new TpposCommand());
        registerCommand("god", new GodCommand(this));
        getCommand("time").setExecutor(timeCommand);
        getCommand("time").setTabCompleter(timeCommand);
        getCommand("day").setExecutor(timeCommand);
        getCommand("night").setExecutor(timeCommand);
        getCommand("noon").setExecutor(timeCommand);
        getCommand("midnight").setExecutor(timeCommand);
        getCommand("sunrise").setExecutor(timeCommand);
        getCommand("sunset").setExecutor(timeCommand);
        getCommand("clear").setExecutor(clearCommand);
        getCommand("clear").setTabCompleter(clearCommand);
        getCommand("kit").setExecutor(new KitCommand(this));
        this.getCommand("ban").setExecutor(new BanCommands());
        this.getCommand("tempban").setExecutor(new BanCommands());
        this.getCommand("unban").setExecutor(new BanCommands());

        registerCommand("sethome", new SetHomeCommand(homeManager));
        registerCommand("home", new HomeCommand(homeManager));
        registerCommand("delhome", new DelHomeCommand(homeManager));
        registerCommand("setwarp", new SetWarpCommand(warpsManager));
        registerCommand("warp", new WarpCommand(warpsManager));
        registerCommand("delwarp", new DelWarpCommand(warpsManager));
        registerCommand("warps", new WarpsCommand(warpsManager));
        registerCommand("enchanttable",new PortableEnchant(this));
        registerCommand("cmdspy", new CmdSpyCommand(cmdSpyListener));

        // Register event listeners
        getServer().getPluginManager().registerEvents(new OnJoinListener(this, flyManager), this);
        getServer().getPluginManager().registerEvents(new ChatAsyncListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockCommands(this), this);
        getServer().getPluginManager().registerEvents(new VanishListener(this), this);
        getServer().getPluginManager().registerEvents(new WrongCommand(this), this);
        getServer().getPluginManager().registerEvents(new ListenerGUI(this), this);
        getServer().getPluginManager().registerEvents(motdListener, this);
        getServer().getPluginManager().registerEvents(cmdSpyListener, this);
        getServer().getPluginManager().registerEvents(new FlyListener(this), this);
        getServer().getPluginManager().registerEvents(new GodModeListener(this), this);
        getServer().getPluginManager().registerEvents(new GamemodeChangeListener(this, flyManager), this);
        getServer().getPluginManager().registerEvents(new KitPreviewListener(), this);



        log.info("Commands registered:");
        for (String command : getDescription().getCommands().keySet()) {
            log.info(" - " + command);
        }
    }

    private void registerCommand(String name, org.bukkit.command.CommandExecutor executor) {
        if (getCommand(name) != null) {
            getCommand(name).setExecutor(executor);
        } else {
            log.warning("Command '" + name + "' not found in plugin.yml!");
        }
    }

    public void loadLang() {
        File lang = new File(getDataFolder(), "lang.yml");
        if (!lang.exists()) {
            log.info("lang.yml not found — saving default copy from jar.");
            saveResource("lang.yml", false);
        }

        if (!lang.exists()) {
            log.severe("Failed to save lang.yml from jar! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(lang);

        boolean changed = false;
        for (Lang item : Lang.values()) {
            if (langConfig.getString(item.getPath()) == null) {
                langConfig.set(item.getPath(), item.getDefault());
                changed = true;
            }
        }

        Lang.setFile(langConfig);

        if (changed) {
            try {
                langConfig.save(lang);
            } catch (IOException e) {
                log.severe("Could not save updated lang.yml.");
                e.printStackTrace();
            }
        }
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public WarpsManager getWarpsManager() {
        return warpsManager;
    }
    public FlyManager getFlyManager() {
        return flyManager;
    }
    public GodManager getGodManager() {
        return godManager;
    }

    @Override
    public void onDisable() {
        log.info("[DCEssentials Premium] has been disabled");
        log.info("DCEssentials Premium Copyright © 2019 By DoragonCraft");
    }

    public Set<Player> getSubtitleDiscord() {
        return subtitleDiscord;
    }

    public Set<Player> getSubTitleWebsite() {
        return subTitleWebsite;
    }

    public Set<Player> getTitleWebsite() {
        return titleWebsite;
    }

    public Set<Player> getFrPlayer() {
        return frPlayer;
    }

    public Set<Player> getStore() {
        return store;
    }

    public Set<Player> getStoreSub() {
        return storeSub;
    }

    public Set<Player> getVote() {
        return vote;
    }

    public Set<Player> getVoteSub() {
        return voteSub;
    }

    public Set<Player> getVanished() {
        return vanished;
    }

    public void removeVanished(Player player) {
        vanished.remove(player);
    }

    public KitManager getKitManager() {
        return kitManager;

    }
}
