package de.bergwerklabs.framework.bedrock.api;

import java.util.*;

/**
 * Created by Yannic Rieger on 01.05.2017.
 * <p>
 * Class which keeps track of every player that will play the game.
 * The {@code PlayerRegistry} is part of every {@link LabsGame}.
 *
 * @author Yannic Rieger
 */
public class PlayerRegistry<T extends LabsPlayer> {

    /**
     * Gets all {@link LabsPlayer} that play the {@link LabsGame}.
     */
    public Map<UUID, T> getPlayers() { return Collections.unmodifiableMap(players); }

    /**
     * Gets all the Spectators in a {@link LabsGame}.
     */
    public Map<UUID, T> getSpectators() { return Collections.unmodifiableMap(spectators); }

    public Collection<T> getPlayerCollection() { return Collections.unmodifiableCollection(playerCollection); }

    private Map<UUID, T> players    = new HashMap<>();
    private Map<UUID, T> spectators = new HashMap<>();
    private Collection<T> playerCollection = players.values();


    /**
     * Retrieves a player from the map. Can be null if player left.
     *
     * @param uuid {@link UUID} of the player.
     */
    public T getPlayer(UUID uuid) {
        T p1 = this.players.get(uuid);
        T p2 = this.spectators.get(uuid);
        if (p2 != null) return p2;
        else if (p1 != null) return p1;
        else return null;
    }

    /**
     * Retrieves a spectator from the map. Entry can be null if they left the game or is playing.
     *
     * @param uuid {@link UUID} of the player.
     */
    public T getSpectator(UUID uuid) {
        return this.spectators.get(uuid);
    }

    /**
     * Registers a spectator.
     *
     * @param spectator {@link LabsPlayer} to be registered as an spectator.
     * @return          instance of {@link LabsPlayer}
     */
    public T registerSpectator(T spectator) {
        return this.spectators.putIfAbsent(spectator.getUuid(), spectator);
    }

    /**
     * Registers a player.
     *
     * @param player {@link LabsPlayer} to be registered.
     * @return       instance of {@link LabsPlayer}.
     */
    public T registerPlayer(T player) {
        return this.players.putIfAbsent(player.getUuid(), player);
    }

    /**
     * Unregisters a spectator
     *
     * @param spectator {@link LabsPlayer} to be unregistered as an spectator.
     * @return          instance of {@link LabsPlayer}
     */
    public T unregisterSpectator(T spectator) {
        this.playerCollection.remove(spectator);
        return this.spectators.remove(spectator);
    }

    /**
     * Unregisters a player.
     *
     * @param player {@link LabsPlayer} to be unregistered.
     * @return       instance of {@link LabsPlayer}
     */
    public T unregisterPlayer(T player) {
        this.playerCollection.remove(player);
        return this.players.remove(player);
    }
}
