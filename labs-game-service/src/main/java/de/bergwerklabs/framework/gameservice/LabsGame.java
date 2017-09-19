package de.bergwerklabs.framework.gameservice;

import de.bergwerklabs.framework.commons.spigot.messenger.PluginMessenger;
import de.bergwerklabs.framework.gameservice.listener.LabsListener;
import org.bukkit.Bukkit;

/**
 * Created by Yannic Rieger on 07.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public abstract class LabsGame<T extends LabsPlayer> {

    /**
     * Gets the {@link PlayerManager} for this game.
     */
    public PlayerManager<T> getPlayerManager() { return this.playerManager; }

    protected PlayerManager<T> playerManager = new PlayerManager<>();
    protected PluginMessenger messenger;

    public LabsGame(String name) {
        Bukkit.getPluginManager().registerEvents(new LabsListener<>(playerManager, GameService.getInstance().getServiceConfig()), GameService.getInstance());
        this.messenger = new PluginMessenger(name);
    }

    /**
     * Starts the game.
     */
    public abstract void start();

    /**
     * Stops the game.
     */
    public abstract void stop();
}
