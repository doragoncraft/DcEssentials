package me.doragoncraft.dcEssentials.uti;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameModeHelper {

    private static GameModeHelper instance;
    private final ProtocolManager protocolManager;

    private GameModeHelper() {
        this.protocolManager = ProtocolLibrary.getProtocolManager();
    }

    public static GameModeHelper getInstance() {
        if (instance == null) {
            instance = new GameModeHelper();
        }
        return instance;
    }

    public void setGameModeWithAbilitiesUpdate(Player player, GameMode mode) {
        player.setGameMode(mode);
        sendPlayerAbilitiesPacket(player);
    }

    private void sendPlayerAbilitiesPacket(Player player) {
        try {
            PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.ABILITIES);

            boolean isCreative = player.getGameMode() == GameMode.CREATIVE;
            boolean isSpectator = player.getGameMode() == GameMode.SPECTATOR;

            packet.getBooleans().write(0, player.isInvulnerable());
            packet.getBooleans().write(1, player.isFlying());
            packet.getBooleans().write(2, isCreative || isSpectator);
            packet.getBooleans().write(3, isCreative);

            packet.getFloat().write(0, player.getFlySpeed());
            packet.getFloat().write(1, player.getWalkSpeed());

            protocolManager.sendServerPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
