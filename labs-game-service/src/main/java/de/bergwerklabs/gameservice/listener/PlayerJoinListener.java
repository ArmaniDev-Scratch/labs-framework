package de.bergwerklabs.gameservice.listener;

import de.bergwerklabs.gameservice.LabsPlayer;
import de.bergwerklabs.gameservice.PlayerManager;
import de.bergwerklabs.gameservice.config.GameServiceConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Yannic Rieger on 01.08.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class PlayerJoinListener<T extends LabsPlayer> extends LabsListener<T> {

    /**
     * @param playerManager
     */
    public PlayerJoinListener(PlayerManager<T> playerManager, GameServiceConfig config) {
        super(playerManager, config);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (this.config.loadStatisticsOnJoin()) {
            // TODO: load statistics using context
        }
    }
}
