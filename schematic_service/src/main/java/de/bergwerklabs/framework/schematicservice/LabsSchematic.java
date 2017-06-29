package de.bergwerklabs.framework.schematicservice;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.util.EditSessionBuilder;
import com.boydti.fawe.util.TaskManager;
import com.flowpowered.nbt.CompoundTag;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;

/**
 * Created by Yannic Rieger on 05.05.2017.
 * <p> This class is used for pasting schematics asynchronously.
 *
 *  @author Yannic Rieger
 */
public class LabsSchematic {

    /**
     * Gets the schematic as a file object.
     */
    public File getSchematicFile() {
        return schematicFile;
    }

    /**
     * Gets the origin of this schematic.
     */
    public Vector getOrigin() { return this.origin; }

    /**
     * Gets the metadata contained in this schematic.
     */
    public Object getMetadata() { return this.metadata; }

    /**
     * Sets the metadata.
     *
     * @param metadata Metadata object that will be written into the schematic file.
     */
    void setMetadata(Object metadata) { this.metadata = metadata; }

    private File schematicFile;
    private Vector origin;
    private Object metadata;

    /**
     * @param schematicFile File representing the schematic (File extension: .schematic)
     */
    public LabsSchematic(File schematicFile) {
        this.schematicFile = schematicFile;

        CompoundTag tag = NbtUtil.readCompoundTag(schematicFile);

        this.origin = new Vector(Float.valueOf(NbtUtil.readTag("WEOriginX", tag).getValue().toString()),
                                 Float.valueOf(NbtUtil.readTag("WEOriginY", tag).getValue().toString()),
                                 Float.valueOf(NbtUtil.readTag("WEOriginZ", tag).getValue().toString()));
    }

    /**
     * Pastes a WorldEdit schematic asynchronously in the specified world.
     *
     * @param world World where to paste the schematic in.
     * @param to Vector which conains x, y and z coordinates representing the paste location.
     */
    public void pasteAsync(String world, Vector to) {

        TaskManager.IMP.async(() -> {
            EditSession session = new EditSessionBuilder(FaweAPI.getWorld(world)).fastmode(true).checkMemory(true).build();
            try {
                SchematicFormat.getFormat(schematicFile).load(schematicFile).paste(session, new com.sk89q.worldedit.Vector(to.getX(), to.getY(), to.getZ()), true, true);
                session.flushQueue();
            }
            catch (MaxChangedBlocksException e) {
                e.printStackTrace();
            }
            catch (DataException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
