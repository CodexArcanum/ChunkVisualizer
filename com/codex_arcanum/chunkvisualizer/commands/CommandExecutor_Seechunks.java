package com.codex_arcanum.chunkvisualizer.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.codex_arcanum.chunkvisualizer.ChunkVisualizer;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;


public class CommandExecutor_Seechunks implements CommandExecutor {
	private ChunkVisualizer plugin;

	public CommandExecutor_Seechunks(ChunkVisualizer plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		
			if (!(sender instanceof Player)) {
				sender.sendMessage("Doesn't work from the console. Why would it?");
				return true;
			}
                        Player player = (Player) sender;
                        if(player.hasPermission("chunkvisualizer.view") || player.isOp() || !plugin.getConfig().getBoolean("requiresop")){


                            int blockid = this.plugin.getConfig().getInt("borderblockid");
                            int blockdata = this.plugin.getConfig().getInt("borderdata");

                            ChunkVisualizer.viewers.add(player);
                            ChunkVisualizer.viewerslocs.add(player.getLocation());

                            Chunk chunk = player.getLocation().getChunk();
                        
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
                                    if(corner1.getBlock().getType() == Material.AIR){player.sendBlockChange(corner1, Material.getMaterial(blockid), (byte) blockdata);}
                                    if(corner2.getBlock().getType() == Material.AIR){player.sendBlockChange(corner2, Material.getMaterial(blockid), (byte) blockdata);}
                                    if(corner3.getBlock().getType() == Material.AIR){player.sendBlockChange(corner3, Material.getMaterial(blockid), (byte) blockdata);}
                                    if(corner4.getBlock().getType() == Material.AIR){player.sendBlockChange(corner4, Material.getMaterial(blockid), (byte) blockdata);}
                                }
                            }

                            player.sendMessage(ChatColor.GOLD + "Glass blocks now (inclusively) showing the border of your current chunk.");
                            return true;
                        }
                        else{
                            player.sendMessage(ChatColor.GOLD + "You do not have permission to execute this command.");
                            return true;
                        }
		
	}
}
