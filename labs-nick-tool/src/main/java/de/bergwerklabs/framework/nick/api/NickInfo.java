package de.bergwerklabs.framework.nick.api;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import de.bergwerklabs.framework.commons.spigot.entity.npc.PlayerSkin;

/**
 * Created by Yannic Rieger on 03.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class NickInfo {

    /**
     *
     */
    public WrappedGameProfile getRealGameProfile() {
        return realGameProfile;
    }

    /**
     *
     */
    public PlayerSkin getSkin() {
        return skin;
    }

    /**
     *
     * @return
     */
    public String getNickName() {
        return nickName;
    }

    private WrappedGameProfile realGameProfile;
    private String nickName;
    private PlayerSkin skin;

    /**
     * @param realGameProfile
     * @param skin
     * @param fakeName
     */
    public NickInfo(WrappedGameProfile realGameProfile, PlayerSkin skin, String fakeName) {
        this.realGameProfile = realGameProfile;
        this.nickName        = fakeName;
        this.skin            = skin;
    }
}
