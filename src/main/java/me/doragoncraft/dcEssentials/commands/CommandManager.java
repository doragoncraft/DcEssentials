package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.uti.ChatUti;
import me.doragoncraft.dcEssentials.uti.Lang;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager
        implements CommandExecutor
{
    Dcessentails plugin;

    public CommandManager(Dcessentails plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        TextComponent msg;
        Player player = (Player)sender;
        String noperm = ChatUti.format("&7You don't have enough permissions \nto view the full list.");
        String setgui = "/setgui";
        String decp = "/decp";
        String about = "/decp about";
        String reload = "/decp reload";
        String discord = "/discord";
        String ctc = "/ctc";
        String website = "/website";
        String ip = "/playerip";
        String vote = "/vote";
        String pmsg = "/message";
        String store = "/store";
        String twitch = "/twitch";
        String links = "/links";
        String youtube = "/youtube";
        String help = ChatColor.AQUA + "Click to select command";
        if (!(sender instanceof Player)) {
            System.out.println(Lang.CONSOLE);
        }
        if (args.length == 0) {
            player.sendMessage(ChatUti.format("&8[ &fHelpful Commands &8]"));
            player.sendMessage(ChatUti.format("&r"));
            ((Player)sender).spigot().sendMessage((new ComponentBuilder(decp))
                    .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, decp))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help })))).create());
            player.sendMessage(ChatUti.format("&8[&bThis menu&8]"));
            ((Player)sender).spigot().sendMessage((new ComponentBuilder(about))
                    .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, about))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                    })))).create());
            player.sendMessage(ChatUti.format("&8[&binfo About this plugin&8]"));
            ((Player)sender).spigot().sendMessage((new ComponentBuilder(discord))
                    .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, discord))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                    })))).create());
            player.sendMessage(ChatUti.format("&8[&bShows our discord link&8]"));
            ((Player)sender).spigot().sendMessage((new ComponentBuilder(website))
                    .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, website))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                    })))).create());
            player.sendMessage(ChatUti.format("&8[&bShows our website link&8]"));
            ((Player)sender).spigot().sendMessage((new ComponentBuilder(vote))
                    .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, vote))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                    })))).create());
            player.sendMessage(ChatUti.format("&8[&bShows our vote link&8]"));
            ((Player)sender).spigot().sendMessage((new ComponentBuilder(pmsg))
                    .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, pmsg))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                    })))).create());
            player.sendMessage(ChatUti.format("&8[&bShows some messages set by server admins&8]"));
            ((Player)sender).spigot().sendMessage((new ComponentBuilder(store))
                    .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, store))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                    })))).create());
            player.sendMessage(ChatUti.format("&8[&bShows our store link&8]"));
            ((Player)sender).spigot().sendMessage((new ComponentBuilder(twitch))
                    .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, twitch))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                    })))).create());
            player.sendMessage(ChatUti.format("&8[&bShows our Twitch link&8]"));

            ((Player)sender).spigot().sendMessage((new ComponentBuilder(links))
                    .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, links))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                    })))).create());
            player.sendMessage(ChatUti.format("&8[&bShows some of our other links&8]"));
            ((Player)sender).spigot().sendMessage((new ComponentBuilder(youtube))
                    .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, youtube))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                    })))).create());
            player.sendMessage(ChatUti.format("&8[&bShows our youtube link&8]"));

            return true;
        }
        player.sendMessage("");


        switch (args[0].toLowerCase()) {
            case "about":
                player.sendMessage(ChatUti.format("&2DCEasyCmds &cAbout:"));
                player.sendTitle(ChatColor.GREEN + "DCEasyCmds", ChatColor.RED + "By DoragonCraft", 20, 20, 10);
                player.sendMessage(" ");
                player.sendMessage("Simple commands for your server");
                player.sendMessage("For help use " + ChatColor.AQUA + "/decp");
                player.sendMessage(" ");
                player.sendMessage(ChatUti.format("&7&m-----------------------"));
                player.sendMessage(ChatUti.format("&7Coded by: &cDoragoncraft"));
                player.sendMessage(ChatUti.format("&7&m-----------------------"));
                player.sendMessage(" ");

                return true;
            case "help":
                if (player.hasPermission("decp.admin"))
                    player.sendMessage(ChatUti.format("&r"));
                player.sendMessage(ChatUti.format("&b&lAdmin Commands"));
                player.sendMessage(ChatUti.format("&bAll theses commands require permissions"));
                player.sendMessage(ChatUti.format("&bIf you are a normal user please use command /decp"));
                ((Player)sender).spigot().sendMessage((new ComponentBuilder(setgui))
                        .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, setgui))
                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                        })))).create());
                player.sendMessage(ChatUti.format("&8[&bSetup titles GUI&8]"));
                ((Player)sender).spigot().sendMessage((new ComponentBuilder(ctc))
                        .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, ctc))
                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                        })))).create());
                player.sendMessage(ChatUti.format("&8[&bClears all chat&8]"));
                ((Player)sender).spigot().sendMessage((new ComponentBuilder(ctc + " personal"))
                        .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, ctc + " personal"))
                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                        })))).create());
                player.sendMessage(ChatUti.format("&8[&bClears personal chat&8]"));
                ((Player)sender).spigot().sendMessage((new ComponentBuilder(ctc + " lines"))
                        .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, ctc + " lines"))
                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                        })))).create());
                player.sendMessage(ChatUti.format("&8[&bShows hom many lines of chat /ctc will clear&8]"));
                ((Player)sender).spigot().sendMessage((new ComponentBuilder(reload))
                        .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, reload))
                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                        })))).create());
                player.sendMessage(ChatUti.format("&8[&bReloads the config&8]"));
                msg = new TextComponent(ChatColor.AQUA + "Report errors on Github! DcEasyCmds Premium.\n ");
                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(ChatColor.AQUA + "Click here to report any errors "))
                        .create()));
                ((Player)sender).spigot().sendMessage((new ComponentBuilder(ip))
                        .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, ip))
                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", new CharSequence[] { help
                        })))).create());
                player.sendMessage(ChatUti.format("&8[&bShows player ip and Other info&8]"));
                player.sendMessage(ChatUti.format("&r"));
                msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/doragoncraft/DcEasyCmdsPrem/issues"));
                player.spigot().sendMessage(msg);
                return true;
            case "reload":
                if (player.hasPermission("decp.reload") || player.hasPermission("decp.admin")) { player.sendMessage(Lang.RELOAD.get());
                    this.plugin.reloadConfig(); }
                else { player.sendMessage(Lang.NO_PERM.get()); }

                return true;
        }  player.sendMessage(ChatUti.format("&8[&cDECP&8] &7The command you entered does not exist or is spelt incorrectly"));
        return true;
    }
}
