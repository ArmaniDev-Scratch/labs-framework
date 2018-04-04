package de.bergwerklabs.framework.bedrock.core.listener;

import com.comphenix.protocol.wrappers.EnumWrappers;
import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.api.PlayerdataDao;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayClientClientCommand;
import de.bergwerklabs.framework.bedrock.core.config.SessionServiceConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;

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

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        this.playerRegistry.getPlayers().remove(player.getUniqueId());

        if (this.config.useAutoRespawn()) {
            WrapperPlayClientClientCommand playClientClientCommand = new WrapperPlayClientClientCommand();
            playClientClientCommand.setAction(EnumWrappers.ClientCommand.PERFORM_RESPAWN);
            playClientClientCommand.sendPacket(player);
        }
    }
}
