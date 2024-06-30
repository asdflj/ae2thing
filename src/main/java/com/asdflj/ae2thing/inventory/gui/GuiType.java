package com.asdflj.ae2thing.inventory.gui;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;

import com.asdflj.ae2thing.client.gui.GuiCellLink;
import com.asdflj.ae2thing.client.gui.GuiCraftAmount;
import com.asdflj.ae2thing.client.gui.GuiCraftConfirm;
import com.asdflj.ae2thing.client.gui.GuiCraftingStatus;
import com.asdflj.ae2thing.client.gui.GuiCraftingTerminal;
import com.asdflj.ae2thing.client.gui.GuiDistillationPatternTerminal;
import com.asdflj.ae2thing.client.gui.GuiPatternValueAmount;
import com.asdflj.ae2thing.client.gui.container.ContainerCellLink;
import com.asdflj.ae2thing.client.gui.container.ContainerCraftingTerminal;
import com.asdflj.ae2thing.client.gui.container.ContainerDistillationPatternTerminal;
import com.asdflj.ae2thing.client.gui.container.ContainerFluidCraftConfirm;
import com.asdflj.ae2thing.client.gui.container.ContainerPatternValueAmount;
import com.asdflj.ae2thing.common.parts.THPart;
import com.asdflj.ae2thing.inventory.ItemCellLinkInventory;
import com.google.common.collect.ImmutableList;

import appeng.api.storage.ITerminalHost;
import appeng.container.implementations.ContainerCraftAmount;
import appeng.container.implementations.ContainerCraftingStatus;

public enum GuiType {

    DISTILLATION_PATTERN_TERMINAL(new PartGuiFactory<>(ITerminalHost.class) {

        @Override
        protected Object createServerGui(EntityPlayer player, ITerminalHost inv) {
            return new ContainerDistillationPatternTerminal(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, ITerminalHost inv) {
            return new GuiDistillationPatternTerminal(player.inventory, inv);
        }
    }),
    CRAFTING_CONFIRM(new PartGuiFactory<>(THPart.class) {

        @Override
        protected Object createServerGui(EntityPlayer player, THPart inv) {
            return new ContainerFluidCraftConfirm(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, THPart inv) {
            return new GuiCraftConfirm(player.inventory, inv);
        }
    }),
    CRAFTING_STATUS(new PartGuiFactory<>(ITerminalHost.class) {

        @Override
        protected Object createServerGui(EntityPlayer player, ITerminalHost inv) {
            return new ContainerCraftingStatus(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, ITerminalHost inv) {
            return new GuiCraftingStatus(player.inventory, inv);
        }
    }),
    PATTERN_VALUE_SET(new PartGuiFactory<>(ITerminalHost.class) {

        @Override
        protected Object createServerGui(EntityPlayer player, ITerminalHost inv) {
            return new ContainerPatternValueAmount(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, ITerminalHost inv) {
            return new GuiPatternValueAmount(player.inventory, inv);
        }
    }),

    CRAFTING_AMOUNT(new PartGuiFactory<>(ITerminalHost.class) {

        @Override
        protected Object createServerGui(EntityPlayer player, ITerminalHost inv) {
            return new ContainerCraftAmount(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, ITerminalHost inv) {
            return new GuiCraftAmount(player.inventory, inv);
        }
    }),

    BACKPACK_TERMINAL(new ItemGuiFactory<>(ITerminalHost.class) {

        @Override
        protected Object createServerGui(EntityPlayer player, ITerminalHost inv) {
            return new ContainerCraftingTerminal(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, ITerminalHost inv) {
            return new GuiCraftingTerminal(player.inventory, inv);
        }
    }),
    CELL_LINK(new ItemGuiFactory<>(ItemCellLinkInventory.class) {

        @Override
        protected Object createServerGui(EntityPlayer player, ItemCellLinkInventory inv) {
            return new ContainerCellLink(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, ItemCellLinkInventory inv) {
            return new GuiCellLink(player.inventory, inv);
        }
    });

    public static final List<GuiType> VALUES = ImmutableList.copyOf(values());

    @Nullable
    public static GuiType getByOrdinal(int ordinal) {
        return ordinal < 0 || ordinal >= VALUES.size() ? null : VALUES.get(ordinal);
    }

    public final IGuiFactory guiFactory;

    GuiType(IGuiFactory guiFactory) {
        this.guiFactory = guiFactory;
    }
}
