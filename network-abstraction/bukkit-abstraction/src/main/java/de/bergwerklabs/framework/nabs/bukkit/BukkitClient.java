package de.bergwerklabs.framework.nabs.bukkit;

import de.bergwerklabs.framework.nabs.standalone.PlayerdataFactory;

/**
 * Created by Yannic Rieger on 20.12.2017.
 * <p>
 * Abstraction for Bukkit clients that interact with the backend system.
 * This is done to decrease the the chance of breaking other plugins when changing the backend system.
 * <p>
 * All Bukkit plugins that interact with the backend have to implement this interface.
 *
 * @author Yannic Rieger
 */
public interface BukkitClient {

    /**
     * Gets the {@link PlayerdataFactory}.
     */
    PlayerdataFactory getDatasetFactory();

}
