package de.bergwerklabs.framework.nabs.standalone;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Yannic Rieger on 20.12.2017.
 *
 * <p>Creates an {@link PlayerdataSet}. Usage:
 *
 * <pre>
 *     PlayerdataFactory factory = new FactoryImp();
 *     UUID someUuid = UUID.randomUUID();
 *     PlayerdataSet set = factory.createInstance(someUuid);
 * </pre>
 *
 * @author Yannic Rieger
 */
public interface PlayerdataFactory {

  @Nullable
  PlayerdataSet createInstance(@NotNull UUID uuid);
}
