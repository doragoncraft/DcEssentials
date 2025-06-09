# ⚙️ DcEssentials

![GitHub issues](https://img.shields.io/github/issues/doragoncraft/DcEssentials.svg?style=for-the-badge)
[![Discord](https://img.shields.io/discord/381442112400523264.svg?style=for-the-badge)](https://discordapp.com/invite/VMx9JmY)

A premium essential command plugin made for DoragonCraft servers, now available for others to use and enjoy.

---

## 📜 Description
DcEssentials provides a set of essential and utility commands for your Minecraft server, along with player management tools, GUIs, and enhanced gameplay features. Highly configurable and easy to use, designed to enhance both player and admin experiences.

---

## 🌐 Connect With Me
<p align="left">
  Join my Discord:  
  <a  href="https://discord.gg/VMx9JmY"><img src="https://discordapp.com/api/guilds/381442112400523264/widget.png?style=banner2" alt="Discord server"></a>  

My Website:  
<a href="https://doragoncraftnetwork.com/"><img src="https://crafatar.com/avatars/d88dc2506f5d4bef8fdc08690d32f731?size=64&overlay" alt="Website"></a>

View my other resources:  
<a href="https://www.spigotmc.org/resources/authors/doragoncraft.126499/"><img src="https://static.spigotmc.org/img/spigot.png" alt="Spigot"></a>
</p>

---

## 📦 Features
- Essential player commands (`/fly`, `/heal`, `/god`, `/home`, `/warp`, etc.)
- Admin tools (`/vanish`, `/tpo`, `/tpahere`, `/speed`, etc.)
- Interactive GUIs and inventory-based menus
- Player teleport request system
- Weather and time control commands
- Command blocking and monitoring
- Custom MOTD and Join messages
- Warp and home management with config persistence
- Tab completion for most commands
- Metrics and plugin update notification
- Fully configurable language and command permissions

---

## 🗺️ Plugin Map

<details>
  <summary>📂 Click to expand full plugin structure map</summary>

### 📁 1️⃣ `src/main/java/me/doragoncraft/dcEssentials/`
- `Dcessentails.java` (Main plugin class)

### 📁 2️⃣ `commands/`
- `Anvil`
- `ClearCommand`
- `CmdSpyCommand`
- `CommandManager`
- `Craft`
- `Ctc`
- `DelHomeCommand`
- `DelWarpCommand`
- `Discord`
- `FlyCommand`
- `GamemodeCommand`
- `GodCommand`
- `GuiOpen`
- `Heal`
- `HomeCommand`
- `Links`
- `LinkSP`
- `PortableEnchant`
- `ServerIP`
- `SetHomeCommand`
- `SetWarpCommand`
- `SpawnCMD`
- `SpeedCommand`
- `StoreCmd`
- `TimeCommand`
- `TpAcceptCommand`
- `TpaCommand`
- `TpahereCommand`
- `TpCommand`
- `TpDenyCommand`
- `TphereCommand`
- `TpoCommand`
- `TpposCommand`
- `TpToggleCommand`
- `Trash`
- `Twitch`
- `VanishCMD`
- `Vote`
- `WarpCommand`
- `WarpsCommand`
- `WeatherCommand`
- `Website`
- `Whois`
- `YouTubeCmd`

### 📁 3️⃣ `Listeners/`
- `BlockCommands`
- `ChatAsyncListener`
- `CmdSpyListener`
- `FlyListener`
- `GamemodeChangeListener`
- `GodModeListener`
- `MotdListener`
- `OnJoinListener`
- `VanishListener`
- `WrongCommand`

### 📁 4️⃣ `managers/`
- `FlyManager`
- `GamemodeManager`
- `GodManager`
- `HomeManager`
- `TeleportRequestManager`
- `SpeedManager`
- `WarpsManager`

### 📁 5️⃣ `uti/`
- `ChatUti`
- `GameModeHelper`
- `Lang`
- `Metrics`
- `Dcessentails`
- `GUIcmd`
- `ListenerGUI`

### 📁 6️⃣ `resources/`
- `config.yml`
- `lang.yml`
- `plugin.yml`
- `warps.yml`

</details>

---

## 📝 Updates & Changelog

- ✅ Added `/weather <clear/sun/storm/thunder>` and aliases `/sun`, `/rain`, `/storm`, `/thunder`
- ✅ Added `/time <day/night/noon/sunrise/sunset/midnight>` and aliases `/day`, `/night`, etc
- ✅ Improved fly handling on login in creative & spectator
- ✅ Added `/clear` command to wipe player inventory
- ✅ Gamemode commands with tab completion and permission checks
- ✅ Added FlyManager with persistence
- ✅ Enhanced OnJoinListener for OP join messages and thank-you notes
- ✅ Reorganized command map and managers for clarity
- ✅ Fully documented plugin map inside README.md spoiler

---

## 📥 Installation

1. Download the latest release JAR
2. Drop it into your server’s `/plugins/` folder
3. Restart your server do not reload
4. Configure `config.yml`, `lang.yml` and `warps.yml` as needed
5. Done!

---

## 📋 License
MIT License © 2025 DoragonCraft

---

## ❤️ Thank you for using DcEssentials!
