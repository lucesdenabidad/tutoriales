package datta.dev.pluginTutorial.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BlockBreakListener implements Listener {

    private final Material targetMaterial = Material.REDSTONE_BLOCK;
    private final ItemStack giveItem = new ItemStack(Material.DIAMOND, 1);

    private final World world = Bukkit.getWorlds().getFirst();
    private final Location safeLocation = new Location(world, 0, 100, 0);

    private final List<UUID> safePlayers = new ArrayList<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material type = block.getType();
        Player player = event.getPlayer();

        if (safePlayers.contains(player.getUniqueId())){ // Que el jugador unicamente pueda romper el bloque una vez
            player.sendMessage("¡No puedes romper este bloque!");
            event.setCancelled(true); // Cancelamos el evento
            return;
        }

        if (type == targetMaterial) { // Verificamos que el material que rompemos es IGUAL al material objetivo
            player.getInventory().addItem(giveItem); // Le damos el item especificado al jugador en su inventario.

            player.teleport(safeLocation); // Teleportamos a la ubicacion segura
            safePlayers.add(player.getUniqueId()); // Agregamos al listado de jugadores seguros
        }
    }
}
