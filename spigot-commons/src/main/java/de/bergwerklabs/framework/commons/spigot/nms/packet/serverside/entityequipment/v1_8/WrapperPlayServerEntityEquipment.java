/**
 * PacketWrapper - ProtocolLib wrappers for Minecraft packets Copyright (C) dmulloy2
 * <http://dmulloy2.net> Copyright (C) Kristian S. Strangeland
 *
 * <p>This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * <p>You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entityequipment.v1_8;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers.ItemSlot;
import de.bergwerklabs.framework.commons.spigot.nms.packet.AbstractPacket;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public class WrapperPlayServerEntityEquipment extends AbstractPacket {
  public static final PacketType TYPE = PacketType.Play.Server.ENTITY_EQUIPMENT;

  public WrapperPlayServerEntityEquipment() {
    super(new PacketContainer(TYPE), TYPE);
    handle.getModifier().writeDefaults();
  }

  public WrapperPlayServerEntityEquipment(PacketContainer packet) {
    super(packet, TYPE);
  }

  /**
   * Retrieve Entity ID.
   *
   * <p>Notes: entity's ID
   *
   * @return The current Entity ID
   */
  public int getEntityID() {
    return handle.getIntegers().read(0);
  }

  /**
   * Set Entity ID.
   *
   * @param value - new value.
   */
  public void setEntityID(int value) {
    handle.getIntegers().write(0, value);
  }

  /**
   * Retrieve the entity of the painting that will be spawned.
   *
   * @param world - the current world of the entity.
   * @return The spawned entity.
   */
  public Entity getEntity(World world) {
    return handle.getEntityModifier(world).read(0);
  }

  /**
   * Retrieve the entity of the painting that will be spawned.
   *
   * @param event - the packet event.
   * @return The spawned entity.
   */
  public Entity getEntity(PacketEvent event) {
    return getEntity(event.getPlayer().getWorld());
  }

  public ItemSlot getSlot() {
    return this.itemSlotFromNum(this.handle.getIntegers().read(1));
  }

  public void setSlot(ItemSlot value) {
    handle.getIntegers().write(1, this.getSlot(value));
  }

  /**
   * Retrieve Item.
   *
   * <p>Notes: item in slot format
   *
   * @return The current Item
   */
  public ItemStack getItem() {
    return handle.getItemModifier().read(0);
  }

  /**
   * Set Item.
   *
   * @param value - new value.
   */
  public void setItem(ItemStack value) {
    handle.getItemModifier().write(0, value);
  }

  private int getSlot(ItemSlot slot) {
    switch (slot) {
      case MAINHAND:
        return 0;
      case FEET:
        return 1;
      case LEGS:
        return 2;
      case CHEST:
        return 3;
      case HEAD:
        return 4;
      default:
        return 0;
    }
  }

  private ItemSlot itemSlotFromNum(int val) {
    switch (val) {
      case 0:
        return ItemSlot.MAINHAND;
      case 1:
        return ItemSlot.FEET;
      case 2:
        return ItemSlot.LEGS;
      case 3:
        return ItemSlot.CHEST;
      case 4:
        return ItemSlot.HEAD;
      default:
        return ItemSlot.MAINHAND;
    }
  }
}
