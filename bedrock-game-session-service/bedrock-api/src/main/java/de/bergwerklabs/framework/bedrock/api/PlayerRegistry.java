package de.bergwerklabs.framework.bedrock.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Yannic Rieger on 01.05.2017.
 * <p>
 * Class which keeps track of every player that will play the game.
 * The {@code PlayerRegistry} is part of every {@link LabsGame}.
 *
 * @author Yannic Rieger
 */
public class PlayerRegistry {

    /**
     * Gets all {@link LabsPlayer} that play the {@link LabsGame}.
     */
    public Map<UUID, LabsPlayer> getPlayers() { return Collections.unmodifiableMap(players); }

    /**
     * Gets all the Spectators in a {@link LabsGame}.
     */
    public Map<UUID, LabsPlayer> getSpectators() { return Collections.unmodifiableMap(spectators); }

    private Map<UUID, LabsPlayer> players    = new HashMap<>();
    private Map<UUID, LabsPlayer> spectators = new HashMap<>();


    public void registerSpectator(LabsPlayer spectator) {
        this.spectators.putIfAbsent(spectator.getPlayer().getUniqueId(), spectator);
    }

    public void registerPlayer(LabsPlayer player) {
        this.players.putIfAbsent(player.getPlayer().getUniqueId(), player);
    }

    public void unregisterPlayer(LabsPlayer player) {
        this.players.remove(player);
    }

    public void unregisterSpectator(LabsPlayer spectator) {
        this.spectators.remove(spectator);
    }

}
