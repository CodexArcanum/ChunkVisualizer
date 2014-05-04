package com.codex_arcanum.chunkvisualizer.listeners;


import org.bukkit.event.Listener; 
import org.bukkit.event.EventHandler; 

import org.bukkit.event.player.PlayerMoveEvent;

import com.codex_arcanum.chunkvisualizer.ChunkVisualizer;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;


public class Listener_Player implements Listener {
	private ChunkVisualizer plugin;

	public Listener_Player(ChunkVisualizer plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
            Player player = event.getPlayer();

            if(ChunkVisualizer.viewers.contains(player)){
                Location loc = player.getLocation();
                World world = loc.getWorld();
                int index = ChunkVisualizer.viewers.indexOf(player);
                Location prevloc = ChunkVisualizer.viewerslocs.get(index);
                Chunk chunk = prevloc.getChunk();

                if(!(loc.getX() == ChunkVisualizer.viewerslocs.get(index).getX() && loc.getY() == ChunkVisualizer.viewerslocs.get(index).getY() && loc.getZ() == ChunkVisualizer.viewerslocs.get(index).getZ())){
                    Location corner1 = chunk.getBlock(0, 0, 0).getLocation();
                    Location corner2 = chunk.getBlock(15, 0, 0).getLocation();
                    Location corner3 = chunk.getBlock(0, 0, 15).getLocation();
                    Location corner4 = chunk.getBlock(15, 0, 15).getLocation();
                    int i = 0;
                    int i2 = 0;
                    for(i = 0; i < 127; i++){
                       for(i2 = 0; i2 < 15; i2++){
                           corner1 = chunk.getBlock(i2, i, 0).getLocation();
                           corner2 = chunk.getBlock(15, i, i2).getLocation();
                           corner3 = chunk.getBlock((15 - i2), i, 15).getLocation();
                           corner4 = chunk.getBlock(0, i, (15 - i2)).getLocation();
                           if(corner1.getBlock().getType() == Material.AIR){player.sendBlockChange(corner1, Material.AIR, (byte) 0);}
                           if(corner2.getBlock().getType() == Material.AIR){player.sendBlockChange(corner2, Material.AIR, (byte) 0);}
                           if(corner3.getBlock().getType() == Material.AIR){player.sendBlockChange(corner3, Material.AIR, (byte) 0);}
                           if(corner4.getBlock().getType() == Material.AIR){player.sendBlockChange(corner4, Material.AIR, (byte) 0);}
                       }
                    }
                    player.sendMessage(ChatColor.GOLD + "Glass disabled because of movement.");
                    ChunkVisualizer.viewers.remove(player);
                    ChunkVisualizer.viewerslocs.remove(index);

                }
            }

	}

        /*public void onBlockBreakEvent(BlockBreakEvent event){
            Chunk chunk = event.getBlock().getLocation().getChunk();
            int chunkx = chunk.getX();
            int chunkz = chunk.getZ();
            int i = 0;
            for(i=0; i<ChunkVisualizer.viewerslocs.size(); i++){
                if(ChunkVisualizer.viewerslocs.get(i).getX() == chunkx && ChunkVisualizer.viewerslocs.get(i).getZ() == chunkz){

                }
            }

        }*/


}
