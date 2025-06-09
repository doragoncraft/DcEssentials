package me.doragoncraft.dcEssentials.uti;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Lang {
    NO_PERM("noperms", "&4You do not have permission to do that"),
    CONSOLE("console", "&cYou cannot use this command from the console!"),
    WRONG_ARGS("wrong_args", "&4You are missing Args! please try again!"),
    CONFIG_RELOADED("config_reloaded", "Config &aSuccessfully reloaded."),
    BLOCK_CMD_MESSAGE("block_cmd_message", "&cYou can not use that command!"),
    UNKNOWN_COMMAND("unknown_command", "&cUnknown Command"),
    INCORRECT_USAGE("incorrect_usage", "Incorrect Usage"),
    ANTI_OP("anti_op", "This command can only be used by the console. For security reasons"),
    INCORRECT_FORMAT("incorrect_format", "Incorrect format! Please use"),
    ADMIN("admin", "Please ask one of the admins to enable this option!"),
    BROADCAST("broadcast", "&2[&6Broadcast&2] "),
    RELOAD("reload", "&cConfigs Reloaded"),
    PLAYER_NOT_FOUND("player_not_found", "is not online or doesn't exist!"),

    /* CMDSPY */
    CmdspyEnabled("CmdSpyEnabled", "&dCommandspy Enabled"),
    CmdspyDisabled("CmdSpyDisabled", "&dCommandspy Disabled"),

    /* GODMODE */
    GOD_ENABLED("god_enabled", "&6Godmode &2Enabled "),
    GOD_DISABLED("god_disabled", "&6Godmode &cDisabled "),

    /* HOMES */
    SOLID_BLOCK("solid_block", "You must be on a solid block!"),
    NO_HOMES("no_homes", "You don't have any homes!"),
    HOME_NAME("home_name", "You must specify a home name!"),
    HOME_DELETED("home_deleted", " has been successfully deleted!"),
    UNKNOWN_HOME("unknown_home", "Unknown home name!"),
    TOO_MANY_HOMES("too_many_homes", "You have too many homes! Try deleting some of them."),

    /* SPAWN */
    SPAWN_NOT_SET("spawn_not_set", "&cSpawn is not set! Please notify an administrator to set the spawn!"),
    SET_SPAWN_MSG("set_spawn_msg", "&aSuccessfully set spawn!"),
    SPAWN_WELCOME_MSG("spawn_welcome_msg", "&aWelcome to the server's spawn!"),

    /* TPA */
    TPA_REQUEST_RECEIVED_1("tpa_request_received_1", "&aTeleport request from &6/user/ &areceived."),
    TPA_REQUEST_RECEIVED_2("tpa_request_received_2", "&aType &b&l/tpaccept /user/ &ato accept or &c&l/tpdeny /user/ &ato deny."),
    TPA_REQUEST_RECEIVED_3("tpa_request_received_3", "&6/user/ &ahas requested you to teleport to them."),
    TPA_REQUEST_RECEIVED_4("tpa_request_received_4", "&aType &b&l/tpaccept /user/ &ato accept or &c&l/tpdeny /user/ &ato deny."),
    TPA_TPA_REQUEST_ACCEPTED("tpa_tpa_request_accepted", " &6/user/ &ahas been teleported to you!"),
    TPA_REQUEST_ACCEPTED("tpa_request_accepted", "&6/target/ &aaccepted the teleport!"),
    TPA_REQUEST_SENT("tpa_request_sent", "&aTeleport request successfully sent to &6/target/"),
    TPA_TPHERE_REQUEST_ACCEPTED("tpa_tphere_request_accepted", "&aYou have been teleported to &6/user/!"),
    TPA_REQUEST_DENIED_TELEPORT("tpa_request_denied_teleport", "&6/target/ &7has denied the teleport "),
    TPA_REQUEST_DENIED("tpa_request_denied", "&7You denied the teleport"),
    TPA_NO_REQUEST_TO_DENY("tpa_no_request_to_deny", "&7No request to deny!"),
    TPA_NO_REQUEST_TO_ACCEPT("tpa_no_request_to_accept", "&7No request to accept!"),
    TPA_PLAYER_IN_ANOTHER_WORLD_ERROR("tpa_player_in_another_world_error", "&7You can't teleport to a player in another world!"),
    TPA_NO_PLAYER_SPECIFIED("tpa_no_player_specified", "&7Please specify a player!"),
    TPA_PLAYER_DOES_NOT_EXIST_ERROR("tpa_player_does_not_exist_error", "&7That player doesn't exist or is not online!"),
    EXISTING_TPA_REQUEST("existing_tpa_request", "You have already requested a tpa from that player!"),
    EXISTING_TPHERE_REQUEST("existing_tphere_request", "You have already requested a tphere from that player!"),
    TP_TOGGLE_ON("TP_TOGGLE_ON", "TP Toggled on"),
    TP_TOGGLE_OFF("TP_TOGGLE_OFF","TP Toggled off"),


    /* REPAIR */
    REPAIRED_ITEM_HAND("repaired_item_hand", "Successfully repaired item in hand"),
    REPAIR_ALL_ITEMS("repair_all_items", "Successfully repaired all repairable items"),

    /* VANISH */
    VANISH_MESSAGE("vanish_message", "You are now invisible!"),
    VANISH_DISABLED("vanish_disabled", "You are now visible to other players on the server"),

    /* FLY */
    FLY_ENABLED("fly_enabled", "&aEnabled fly mode."),
    FLY_DISABLED("fly_disabled", "&cDisabled fly mode."),
    FLY_OTHER_ENABLED("fly_other_enabled", "&afly mode Enabled."),
    FLY_OTHER_DISABLED("fly_other_disabled", "&cfly mode Disabled."),

    /* FLYSPEED */
    FLYSPEED_SET("flyspeed_set", "Flyspeed set to "),
    FLYSPEED_ERROR("flyspeed_error", "Fly speed can't be less than -10 or more than 10"),
    SPEED_NUMBER_ERROR("speed_number_error", "Only number values are allowed here"),

    /* WALKSPEED */
    WALKSPEED_SET("walkspeed_set", "Walkspeed set to "),
    WALKSPEED_ERROR("walkspeed_error", "Walk speed can't be less than -10 or more than 10"),

    /* GAMEMODE */
    TOO_HIGH_NUMBER("too_high_number", "&cError. Use /gm <0-3> [Player]."),
    NOT_ONLINE("not_online", "&cError. This Player isn't Online."),

    /* SELF */
    SET_TO_CREATIVE("set_to_creative", "Your Gamemode has been set to &1Creative&9."),
    SET_TO_SURVIVAL("set_to_survival", "Your Gamemode has been set to &1Survival&9."),
    SET_TO_ADVENTURE("set_to_adventure", "Your Gamemode has been set to &1Adventure&9."),
    SET_TO_SPECTATOR("set_to_spectator", "Your Gamemode has been set to &1Spectator&9."),

    /* OTHER */
    SET_OTHER_CREATIVE("set_other_creative", "You set &1[%TARGET%]'s &9Gamemode to &1Creative&9."),
    SET_OTHER_SURVIVAL("set_other_survival", "You set &1[%TARGET%]'s &9Gamemode to &1Survival&9."),
    SET_OTHER_ADVENTURE("set_other_adventure", "You set &1[%TARGET%]'s &9Gamemode to &1Adventure&9."),
    SET_OTHER_SPECTATOR("set_other_spectator", "You set &1[%TARGET%]'s &9Gamemode to &1Spectator&9."),

    /* HEALTH */
    FEED_MSG("feed_msg", "You have been fed"),
    FEED_MSG_OTHER("feed_msg_other", "You have been fed by"),
    FEED_OTHER_SENDER("feed_other_sender", "You have fed"),
    HEAL_MSG("heal_msg", "You have been healed"),
    HEAL_MSG_OTHER("heal_msg_other", "You have been healed by"),
    HEAL_OTHER_SENDER("heal_other_sender", "You have healed"),

    /* WEATHER */
    SUN_MSG("sun_msg", "Sunny"),
    RAIN_MSG("rain_msg", "Storm"),

    /* TIME */
    TIME_DAY("time_day", "Set Day Time!"),
    TIME_NIGHT("time_night", "Set Night Time"),

    /* SUPERPICK */
    ENABLE_MESSAGE("enable_message", "&5Superpick Enabled!"),
    DISABLE_MESSAGE("disable_message", "&dSuperpick Disabled!");

    private final String path;
    private final String get;
    private static YamlConfiguration LANG;

    Lang(String path, String def) {
        this.path = path;
        this.get = def;
    }

    /**
     * Set the YamlConfiguration that holds the language messages.
     * Call this once after loading your lang.yml file.
     */
    public static void setFile(YamlConfiguration config) {
        LANG = config;
    }

    /**
     * Get the translated message with color codes translated.
     * Falls back to the default message if config is missing or LANG is not set.
     */
    public String get() {
        if (LANG == null) {
            return ChatColor.translateAlternateColorCodes('&', get);
        }
        return ChatColor.translateAlternateColorCodes('&', LANG.getString(path, get));
    }

    /**
     * Get the default message (without color code translation).
     */
    public String getDefault() {
        return this.get;
    }

    /**
     * Get the config path key for this message.
     */
    public String getPath() {
        return this.path;
    }
}
