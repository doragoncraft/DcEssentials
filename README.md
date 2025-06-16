# ⚙️ DcEssentials

![GitHub issues](https://img.shields.io/github/issues/doragoncraft/DcEssentials.svg?style=for-the-badge)
[![Discord](https://img.shields.io/discord/381442112400523264.svg?style=for-the-badge)](https://discord.gg/VMx9JmY)

A premium essential command plugin made for DoragonCraft servers, now available for others to use and enjoy. 

---

## 📜 Description

DcEssentials provides a set of essential and utility commands for your Minecraft server, along with player management tools, GUIs, and enhanced gameplay features. Highly configurable and easy to use, designed to enhance both player and admin experiences.

---

## 🌐 Connect With Me

<p align="left">
  Join my Discord:  
  <a href="https://discord.gg/VMx9JmY"><img src="https://discordapp.com/api/guilds/381442112400523264/widget.png?style=banner2" alt="Discord server"></a>  

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
- Custom MOTD and join messages
- Warp and home management with config persistence
- Tab completion for most commands
- Metrics and plugin update notification
- Fully configurable language and command permissions

---

## 🗺️ Plugin Map

<details>
  <summary>📂 Click to expand full plugin structure map</summary>

### 📁 1️⃣ `src/main/java/me/doragoncraft/dcEssentials/`
- `DcEssentials.java` (Main plugin class)

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

### 📁 3️⃣ `listeners/`
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

### 📁 5️⃣ `util/`
- `ChatUtil`
- `GameModeHelper`
- `Lang`
- `Metrics`
- `DcEssentials`
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

<details>
  <summary>📂 V1.1</summary>

### 📂 V1.1

- ✅ Added `/weather [clear sun storm thunder]` and aliases `/sun`, `/rain`, `/storm`, `/thunder`
- ✅ Added `/time [day night noon sunrise sunset midnight]` and aliases `/day`, `/night`, etc
- ✅ Improved fly handling on login in creative & spectator
- ✅ Added `/clear` command to wipe player inventory
- ✅ Gamemode commands with tab completion and permission checks
- ✅ Added FlyManager with persistence
- ✅ Enhanced OnJoinListener for OP join messages and thank-you notes
- ✅ Reorganized command map and managers for clarity
- ✅ Fully documented plugin map inside README.md spoiler
</details>

<details>
  <summary>📂 V1.2</summary>

### New Features
- ✅ Added comprehensive `/help` menu with pagination and clickable navigation for easy command discovery.
- ✅ Implemented `/ban` and `/mute` commands with full permission control and feedback messages.
- ✅ Added `/homes` command to list player homes and `/sethome` enhancements for better home management.
- ✅ Introduced `/kits` command for managing and redeeming predefined item kits.
- ✅ Added `/kick` command with customizable kick messages and reasons.
- ✅ Added `/rules` command to display server rules configurable via language files.
- ✅ Added `/spawn` and `/setspawn` commands to manage and teleport to the server spawn location.
- ✅ Added `/spawner` command for managing mob spawners with GUI support.
- ✅ Added mob teleport commands to allow players and admins to teleport to or summon mobs easily.

### Improvements
- ✅ Refined command permission checks and error messages for improved user experience.
- ✅ Enhanced chat and command formatting consistency across new and existing commands.
- ✅ Optimized command execution speed and server resource usage.
- ✅ Updated configuration and language files with new messages and placeholders.

### Bug Fixes
- ✅ Fixed minor bugs in teleport and spawn command handling.
- ✅ Corrected permission node inconsistencies in `/mute` and `/ban` commands.
- ✅ Resolved edge case issues in `/kits` and `/homes` commands when no data was found.
</details>


<details>
  <summary>📂 V1.3 </summary>

**New Features:**

- ✅ Private Messaging System
    - Added `/message <player> <message>` and alias `/msg` for direct player-to-player messaging.
    - Added `/reply <message>` to quickly reply to the last player who messaged you.
    - Added `/msgtoggle` command to toggle receiving private messages.

**Mail System:**

- ✅ Added `/mail` command with subcommands:
    - `send <player> <message>` — send mail to online or offline players.
    - `read` — read received mail messages.
    - `clear` — clear all your mail.
- ✅ Mail messages are stored persistently in an SQLite database.

**Social Spy Feature:**

- ✅ Added `/socialspy on|off` to toggle social spy for staff.
- ✅ Permission-based and intended for moderation.

**Improvements:**

- ✅ Refactored messaging system into `MessageManager` and `DatabaseHandler` classes.
- ✅ Modular SQLite backend with future extensibility in mind.
- ✅ Improved command permissions using `dcessentials.<command>` namespace.
- ✅ Added detailed permission and usage messages.

**Bug Fixes:**

- ✅ Fixed minor bugs in message handling and command parsing.
- ✅ Improved input validation and error handling.
</details>
</details>



---

## 📥 Installation

1. Download the latest release JAR
2. Drop it into your server’s `/plugins/` folder
3. Restart your server (do **not** reload)
4. Configure `config.yml`, `lang.yml` and `warps.yml` as needed
5. Done!

---

## ❤️ Thank You for Using DcEssentials!

---

## 📋 License

MIT License © 2025 DoragonCraft
