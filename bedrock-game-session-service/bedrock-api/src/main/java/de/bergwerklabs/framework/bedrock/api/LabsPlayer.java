package de.bergwerklabs.framework.bedrock.api;

import de.bergwerklabs.framework.nabs.standalone.PlayerdataSet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;


/**
 * Created by Yannic Rieger on 01.05.2017.
 * <p>
 * Base class wrapper class for the {@link Player} interface. The use of this class is to provide more specific functionality.
 * Every uuid specific operation that is not covered by the {@link Player} interface should be contained in a class that extends
 * this one.
 *
 * @author Yannic Rieger
 */
public class LabsPlayer {

    /**
     * Gets the {@link UUID} of the player.
     */
    public UUID getUuid() { return  this.uuid; }

    /**
     * Gets the {@link Player} object.
     */
    public Player getPlayer() {
        System.out.println(uuid);
        System.out.println(Bukkit.getPlayer(this.uuid));
        return Bukkit.getPlayer(this.uuid);
    }

    /**
     * Gets whether or not the uuid is a spectator.
     */
    public boolean isSpectator() {
        return this.isSpectator;
    }

    /**
     * Gets whether or not the uuid is currently frozen.
     */
    public boolean isFrozen() { return this.isFrozen; }

    /**
     * Gets the {@link PlayerdataSet} for this uuid.
     */
    public PlayerdataSet getDataSet() { return this.dataSet; }

    /**
     * Sets the {@link PlayerdataSet} for this uuid.
     *
     * @param dataSet data set to be set.
     */
    public void setDataSet(PlayerdataSet dataSet) {
        this.dataSet = dataSet;
    }

    private UUID uuid;
    protected boolean isSpectator = false;
    protected boolean isFrozen = false;
    protected PlayerdataSet dataSet;

    /**
     * @param player {@link Player} to be wrapped.
     */
    public LabsPlayer(UUID player) {
        this.uuid = player;
    }

    /**
     * Sets the spectator mode of a uuid.
     */
    public void setSpectator() {
        final Player player = this.getPlayer();
        Bukkit.getOnlinePlayers().forEach(p -> p.hidePlayer(player));
        this.isSpectator = true;
    }

    /**
     * Freezes the uuid completely.
     */
    public void freeze() {
        final Player player = this.getPlayer();
        this.isFrozen = true;
        player.setWalkSpeed(0);
        PotionEffect effect = new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, -5, false, false);
        effect.apply(player);
    }

    /**
     * Unfreezes the uuid.
     */
    public void unfreeze() {
        final Player player = this.getPlayer();
        this.isFrozen = false;
        if (player.hasPotionEffect(PotionEffectType.JUMP)) {
            player.removePotionEffect(PotionEffectType.JUMP);
        }
        player.setWalkSpeed(0.2F);
    }
}
