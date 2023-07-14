package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class GmCheckCommand implements CommandExecutor {
    public Inventory GmCheckInv = Bukkit.createInventory(null, 45, "Проверка режима");
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.gmcheck")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return false;
        } else {
            if (!sender.hasPermission("korshuntools.gmcheck")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            } else {
                if (!(sender instanceof Player)) {
                    if(args.length == 0) {
                        sender.sendMessage(ChatColor.RED + "Использование: /gncheck <Игрок>");
                        return false;
                    }
                    else if(args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        GameMode targetGM = target.getGameMode();
                        if(targetGM == GameMode.SURVIVAL) {
                            sender.sendMessage(ChatColor.GREEN + "Выживание (Survival)");
                        }
                        else if(targetGM == GameMode.CREATIVE) {
                            sender.sendMessage(ChatColor.GREEN + "Creative (Креатив)");
                        }
                        else if(targetGM == GameMode.ADVENTURE) {
                            sender.sendMessage(ChatColor.GREEN + "Adventure (Приключение)");
                        }
                        else if(targetGM == GameMode.SPECTATOR) {
                            sender.sendMessage(ChatColor.GREEN + "Spectator (Наблюдатель)");
                        }
                        return true;
                    }
                } else {
                    if (args.length == 0) {
                        sender.sendMessage(ChatColor.RED + "Использование: /gncheck <Игрок>");
                        return false;
                    } else if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        GameMode targetGM = target.getGameMode();
                        ItemStack SurvIS = new ItemStack(Material.STONE);
                        ItemStack CreatIS = new ItemStack(Material.BEDROCK);
                        ItemStack AdvenIS = new ItemStack(Material.GRASS);
                        ItemStack SpecIS = new ItemStack(Material.GLASS);
                        ItemMeta SurvIM = SurvIS.getItemMeta();
                        SurvIM.setDisplayName(ChatColor.RED + "Survival (Выживание)");
                        SurvIS.setItemMeta(SurvIM);
                        ItemMeta CreatIM = CreatIS.getItemMeta();
                        CreatIM.setDisplayName(ChatColor.BLUE + "Creative (Креатив)");
                        CreatIS.setItemMeta(CreatIM);
                        ItemMeta AdvenIM = AdvenIS.getItemMeta();
                        AdvenIM.setDisplayName(ChatColor.RED + "Adventure (Приключение)");
                        AdvenIS.setItemMeta(AdvenIM);
                        ItemMeta SpecIM = SpecIS.getItemMeta();
                            SpecIM.setDisplayName(ChatColor.RED + "Spectator (Наблюдатель)");
                        SpecIS.setItemMeta(SpecIM);
                        if (target == null) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                            return false;
                        }
                        if(targetGM == GameMode.SURVIVAL) {
                            GmCheckInv.setItem(22, SurvIS);
                        }
                        else if(targetGM == GameMode.CREATIVE) {
                            GmCheckInv.setItem(22, CreatIS);
                        }
                        else if(targetGM == GameMode.ADVENTURE) {
                            GmCheckInv.setItem(22, AdvenIS);
                        }
                        else if(targetGM == GameMode.SPECTATOR) {
                            GmCheckInv.setItem(22, SpecIS);
                        }
                        player.openInventory(GmCheckInv);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
