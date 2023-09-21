package gliese832c.selectionGuiCrafting.events;

import gliese832c.SelectionGuiCrafting;
import gliese832c.selectionGuiCrafting.gui.ModGuiHandler;
import gliese832c.selectionGuiCrafting.proxy.CommonProxy;
import gliese832c.selectionGuiCrafting.recipe.GuiSelectionItemPair;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventRightClick {

    @SubscribeEvent(priority = EventPriority.HIGH)
    @SideOnly(Side.CLIENT)
    public void rightClickItem(PlayerInteractEvent.RightClickItem event) {

        EntityPlayer player = (EntityPlayer) event.getEntity();

        Item eventItemMainhand = player.getHeldItemMainhand().getItem();
        Item eventItemOffhand = player.getHeldItemOffhand().getItem();

        for (GuiSelectionItemPair itemPair : CommonProxy.selectionCraftingItems) {
            for (Item itemTool : itemPair.tool) {
                if (ItemStack.areItemStacksEqual(new ItemStack(itemTool), new ItemStack(eventItemMainhand))) {
                    for (Item itemInput : itemPair.input) {
                        if (ItemStack.areItemStacksEqual(new ItemStack(itemInput), new ItemStack(eventItemOffhand))) {
                            openSelectionGui();
                            event.setCanceled(true);
                            return;
                        }
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private void openSelectionGui() {
        // Get data
        EntityPlayer player = Minecraft.getMinecraft().player;
        World world = Minecraft.getMinecraft().world;

        // Open GUI
        player.openGui(SelectionGuiCrafting.instance, ModGuiHandler.CRAFTING_GUI_ID, world, (int) player.posX, (int) player.posY, (int) player.posZ);
    }
}