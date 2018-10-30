package org.soraworld.durability.manager;

import net.minecraft.server.v1_7_R4.Item;
import org.soraworld.durability.serializer.PatternSerializer;
import org.soraworld.hocon.node.Setting;
import org.soraworld.violet.manager.SpigotManager;
import org.soraworld.violet.plugin.SpigotPlugin;
import org.soraworld.violet.util.ChatColor;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class DurabilityManager extends SpigotManager {

    @Setting
    private boolean excludeBlock = true;
    @Setting
    private boolean excludeZero = true;
    @Setting
    private Pattern excludePattern = Pattern.compile("");
    @Setting(path = "maxDurability")
    private final TreeMap<String, TreeMap<String, Integer>> damages = new TreeMap<>();

    private static final Method SET_MAX_DAMAGE;

    static {
        Method method = null;
        try {
            method = Item.class.getDeclaredMethod("setMaxDurability", int.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException ignored) {
            try {
                method = Item.class.getDeclaredMethod("func_77656_e", int.class);
                method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        SET_MAX_DAMAGE = method;
    }

    public DurabilityManager(SpigotPlugin plugin, Path path) {
        super(plugin, path);
        options.registerType(new PatternSerializer());
    }

    public void afterLoad() {
        if (damages.isEmpty()) {
            Item.REGISTRY.forEach(o -> {
                if (o instanceof Item) {
                    String name = Item.REGISTRY.c(o);
                    int maxDamage = ((Item) o).getMaxDurability();
                    System.out.println(name);
                    String[] ss = name.split(":");
                    if (ss.length == 1) {
                        TreeMap<String, Integer> map = damages.computeIfAbsent("minecraft", k -> new TreeMap<>());
                        map.put(ss[0], maxDamage);
                    } else if (ss.length == 2) {
                        TreeMap<String, Integer> map = damages.computeIfAbsent(ss[0], k -> new TreeMap<>());
                        map.put(ss[1], maxDamage);
                    }
                }
            });
        } else setItemMaxDamage();
    }

    public void setItemMaxDamage() {
        if (SET_MAX_DAMAGE != null) {
            damages.forEach((modid, modMap) -> modMap.forEach((name, damage) -> {
                Object object = Item.REGISTRY.get(modid + ":" + name);
                if (object instanceof Item) {
                    try {
                        SET_MAX_DAMAGE.invoke(object, damage);
                    } catch (Throwable ignored) {
                    }
                }
            }));
        }
    }

    public ChatColor defChatColor() {
        return ChatColor.YELLOW;
    }
}
