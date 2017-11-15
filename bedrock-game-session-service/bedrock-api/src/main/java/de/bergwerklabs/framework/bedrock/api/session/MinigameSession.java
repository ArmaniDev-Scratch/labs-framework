package de.bergwerklabs.framework.bedrock.api.session;

import de.bergwerklabs.framework.bedrock.api.event.session.SessionInitializedEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public abstract class MinigameSession extends JavaPlugin implements GameSession {

    @Override
    public String getId() {
        return GameId.create();
    }

    public static MinigameSession getInstance() {
        return instance;
    }

    protected static MinigameSession instance;

    public MinigameSession() {
        instance = this;
        Bukkit.getServer().getPluginManager().callEvent(new SessionInitializedEvent(this));
    }
}
