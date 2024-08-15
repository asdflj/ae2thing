package com.asdflj.ae2thing.client.textures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import com.asdflj.ae2thing.util.NameConst;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public enum Texture {

    Block_ExIOPort_Side("ex_io_portSide"),
    Block_ExIOPort_Side_Off("ex_io_port_offSide"),
    Block_ExIOPort_Bottom("ex_io_portBottom"),
    Block_ExIOPort_Top("ex_io_port"),
    Block_ExIOPort_Top_Off("ex_io_port_off");

    private final String name;
    public net.minecraft.util.IIcon IIcon;

    Texture(final String name) {
        this.name = name;
    }

    public static ResourceLocation GuiTexture(final String string) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public static IIcon getMissing() {
        return ((TextureMap) Minecraft.getMinecraft()
            .getTextureManager()
            .getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
    }

    public String getName() {
        return this.name;
    }

    public IIcon getIcon() {
        return this.IIcon;
    }

    public void registerIcon(final TextureMap map) {
        this.IIcon = map.registerIcon(NameConst.RES_KEY + this.name);
    }
}