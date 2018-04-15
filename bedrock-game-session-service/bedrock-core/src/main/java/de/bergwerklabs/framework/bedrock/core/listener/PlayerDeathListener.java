package de.bergwerklabs.framework.bedrock.core.listener;

import com.comphenix.protocol.ProtocolLib;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.api.PlayerdataDao;
import de.bergwerklabs.framework.bedrock.core.BedrockSessionService;
import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import de.bergwerklabs.framework.commons.spigot.nms.NmsUtil;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayClientClientCommand;
import de.bergwerklabs.framework.bedrock.core.config.SessionServiceConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.lang.reflect.Method;

/**
 * Created by Yannic Rieger on 01.08.2017.
 *
 * @author Yannic Rieger
 */
public class PlayerDeathListener extends LabsListener {


    /**
     * @param playerRegistry registry where all players ares registered.
     * @param dao            playerdata DAO
     * @param config         config of the current session.
     */
    public PlayerDeathListener(PlayerRegistry playerRegistry, PlayerdataDao dao, SessionServiceConfig config) {
        super(playerRegistry, dao, config);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        // unregister player this.playerRegistry.unregisterPlayer(player.getUniqueId());

        if (this.config.useAutoRespawn()) {
            Bukkit.getScheduler().runTaskLater(BedrockSessionService.getInstance(), () -> {
                try {
                    Object performRespawn = LabsReflection.getNmsClass("PacketPlayInClientCommand$EnumClientCommand").getEnumConstants()[0];
                    Object packetRespawn = LabsReflection.getNmsClass("PacketPlayInClientCommand").newInstance();
                    LabsReflection.getField(packetRespawn.getClass(), "a").set(packetRespawn, performRespawn);

                    ProtocolLibrary.getProtocolManager().recieveClientPacket(player, PacketContainer.fromPacket(packetRespawn));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }, 1L);
        }
    }
}
