package de.bergwerklabs.framework.commons.spigot.inventorymenu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.json.version.VersionedJsonDeserializer;
import de.bergwerklabs.framework.commons.spigot.json.version.VersionedJsonSerializer;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by Yannic Rieger on 12.04.2017.
 *
 * <p>Factory used for creating InventoryMenu instances.
 *
 * @author Yannic Rieger
 */
public class InventoryMenuFactory {

  private static final String DESERIALIZER_PATH =
      "de.bergwerklabs.framework.commons.spigot.inventorymenu.version.{version}.InventoryMenuDeserializer{version}";
  private static final String SERIALIZER_PATH =
      "de.bergwerklabs.framework.commons.spigot.inventorymenu.version.{version}.InventoryMenuSerializer{version}";

  private static VersionedJsonSerializer<InventoryMenu> serializer =
      new VersionedJsonSerializer<>(SERIALIZER_PATH);
  private static VersionedJsonDeserializer<InventoryMenu> deserializer =
      new VersionedJsonDeserializer<>(DESERIALIZER_PATH);

  private static Gson gson =
      new GsonBuilder()
          .setPrettyPrinting()
          .registerTypeAdapter(InventoryMenu.class, deserializer)
          .registerTypeAdapter(InventoryMenu.class, serializer)
          .create();

  /**
   * Creates an instance of an InventoryMenu.
   *
   * @param json JsonObject representing the InventoryMenu.
   * @return InventoryMenu
   */
  public static InventoryMenu createInstance(JsonObject json) {
    return deserializer.deserialize(json, null, null);
  }

  /**
   * Creates an instance of an InventoryMenu.
   *
   * @param path Path where the JSON file is located.
   * @return InventoryMenu, null if an error occurred while reading.
   */
  public static InventoryMenu createInstance(String path) {

    try {
      File file = new File(path);
      InputStreamReader reader =
          new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8"));
      return gson.fromJson(reader, InventoryMenu.class);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  /**
   * Searches all folders and their subfolders for json files and tries to serialize them.
   *
   * @param rootDir Start directory.
   * @param map map to add the InventoryMenu to (optional)
   */
  public static void readMenus(File rootDir, Map<String, InventoryMenu> map) {

    File[] fList = rootDir.listFiles();

    for (File file : fList) {
      if (file.isFile()) {
        InventoryMenu menu = InventoryMenuFactory.createInstance(file.getPath());
        if (map != null) map.put(menu.getId(), menu);
      } else if (file.isDirectory() && file.listFiles().length != 0) {
        readMenus(file, map);
      }
    }
  }
}
