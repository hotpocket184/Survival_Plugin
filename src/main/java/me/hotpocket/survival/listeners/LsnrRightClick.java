package me.hotpocket.survival.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.Directional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class LsnrRightClick implements Listener {

    @EventHandler
    private void onRightClick(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getPlayer().getItemInHand() != null && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getPlayer().isSneaking()) {
            Directional directional = (Directional) event.getClickedBlock().getBlockData();
            Location loc = event.getClickedBlock().getLocation();
            World world = event.getClickedBlock().getWorld();
            if (event.getPlayer().getItemInHand().getType() == Material.WOODEN_AXE | event.getPlayer().getItemInHand().getType() == Material.STONE_AXE | event.getPlayer().getItemInHand().getType() == Material.IRON_AXE | event.getPlayer().getItemInHand().getType() == Material.GOLDEN_AXE | event.getPlayer().getItemInHand().getType() == Material.DIAMOND_AXE | event.getPlayer().getItemInHand().getType() == Material.NETHERITE_AXE) {
                if (event.getClickedBlock().getType() == Material.STICKY_PISTON) {
                    event.getClickedBlock().setType(Material.PISTON);
                    Directional d = (Directional) world.getBlockAt(loc).getBlockData();
                    d.setFacing(directional.getFacing());
                    world.getBlockAt(loc).setBlockData(d);
                    loc.setY(loc.getY() + 1);
                    world.dropItemNaturally(loc, new ItemStack(Material.SLIME_BALL, 1));
                }
            }
            if (event.getPlayer().getItemInHand().getType() == Material.SLIME_BALL) {
                if (event.getClickedBlock().getType() == Material.PISTON) {
                    event.getClickedBlock().setType(Material.STICKY_PISTON);
                    Directional d = (Directional) world.getBlockAt(loc).getBlockData();
                    d.setFacing(directional.getFacing());
                    world.getBlockAt(loc).setBlockData(d);
                    event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
                }
            }
        }
    }
}
