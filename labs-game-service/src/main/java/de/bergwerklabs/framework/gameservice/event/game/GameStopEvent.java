package de.bergwerklabs.framework.gameservice.event.game;

import de.bergwerklabs.framework.gameservice.LabsGame;
import de.bergwerklabs.framework.gameservice.LabsPlayer;

/**
 * Created by Yannic Rieger on 21.09.2017.
 * <p>
 * Event that will be fired when an instance of {@link LabsGame} invokes {@link LabsGame#stop()}.
 *
 * @author Yannic Rieger
 */
public class GameStopEvent<T extends LabsPlayer> extends AbstractGameEvent<T> {

    /**
     * @param game Instance of a {@link LabsGame}.
     */
    public GameStopEvent(LabsGame<T> game) {
        super(game);
    }
}
