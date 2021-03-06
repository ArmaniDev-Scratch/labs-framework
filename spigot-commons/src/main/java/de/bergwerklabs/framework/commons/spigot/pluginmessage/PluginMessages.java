package de.bergwerklabs.framework.commons.spigot.pluginmessage;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.plugin.Plugin;

/**
 * Created by Yannic Rieger on 08.10.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class PluginMessages {

  public static final String PLUGIN_CHANNEL = "BungeeCord";

  /**
   * @param plugin
   * @param option
   * @param args
   */
  public static void sendPluginMessage(Plugin plugin, String channel, PluginMessageOption option, String... args) {
    plugin.getServer().sendPluginMessage(plugin, channel, writeData(option, args));
  }

  /**
   * @param plugin
   * @param data
   */
  public static void sendPluginMessage(Plugin plugin, String channel, byte[] data) {
    plugin.getServer().sendPluginMessage(plugin, channel, data);
  }


  /**
   * @param option
   * @param args
   * @return
   */
  private static byte[] writeData(PluginMessageOption option, String... args) {
    ByteArrayDataOutput output = ByteStreams.newDataOutput();
    output.writeUTF(option.getId());
    for (String argument : args) output.writeUTF(argument);
    return output.toByteArray();
  }
}
