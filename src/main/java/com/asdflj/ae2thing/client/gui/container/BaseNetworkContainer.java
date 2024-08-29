package com.asdflj.ae2thing.client.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

import com.asdflj.ae2thing.inventory.item.WirelessTerminal;

import appeng.api.config.Actionable;
import appeng.api.config.PowerMultiplier;
import appeng.api.networking.IGridNode;
import appeng.api.networking.energy.IEnergyGrid;
import appeng.api.storage.ITerminalHost;
import appeng.container.AEBaseContainer;
import appeng.container.guisync.GuiSync;
import appeng.util.Platform;

public class BaseNetworkContainer extends AEBaseContainer {

    protected final EntityPlayer player;
    protected WirelessTerminal terminal;
    protected int ticks;
    protected final double powerMultiplier = 0.5;

    @GuiSync(98)
    public boolean hasPower = false;

    public BaseNetworkContainer(InventoryPlayer ip, ITerminalHost host) {
        super(ip, host);
        this.player = ip.player;
        if (host instanceof WirelessTerminal) {
            this.terminal = (WirelessTerminal) host;
        }
    }

    protected void updatePowerStatus() {
        try {
            if (this.terminal == null) return;
            if (this.getNetworkNode() != null) {
                this.setPowered(
                    this.getNetworkNode()
                        .isActive());
            } else if (this.getPowerSource() instanceof IEnergyGrid) {
                this.setPowered(((IEnergyGrid) this.getPowerSource()).isNetworkPowered());
            } else {
                this.setPowered(
                    this.getPowerSource()
                        .extractAEPower(1, Actionable.SIMULATE, PowerMultiplier.CONFIG) > 0.8);
            }
        } catch (final Throwable ignore) {}
    }

    private IGridNode getNetworkNode() {
        return this.terminal.getGridNode();
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafting) {
        updatePowerStatus();
        super.addCraftingToCrafters(crafting);
    }

    protected void setPowered(final boolean isPowered) {
        this.hasPower = isPowered;
    }

    @Override
    public void detectAndSendChanges() {
        if (Platform.isServer()) {
            if (this.terminal != null && this.hasPower) {
                ticks = this.terminal.getWirelessObject()
                    .extractPower(getPowerMultiplier() * ticks, Actionable.MODULATE, PowerMultiplier.CONFIG, ticks);
            }
        }
        super.detectAndSendChanges();
    }

    public double getPowerMultiplier() {
        return this.powerMultiplier;
    }
}