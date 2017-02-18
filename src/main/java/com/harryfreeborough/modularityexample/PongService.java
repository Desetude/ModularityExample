package com.harryfreeborough.modularityexample;

import com.google.common.collect.MapMaker;
import com.harryfreeborough.modularity.injector.AutoRegister;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@AutoRegister
public class PongService {

    private final Map<Player, AtomicInteger> pongs;

    public PongService() {
        this.pongs = new MapMaker().weakKeys().makeMap();
    }

    public int getPongs(Player player) {
        AtomicInteger pongs = this.pongs.get(player);
        if (pongs != null) {
            return pongs.get();
        }

        return 0;
    }

    public int incrementPongs(Player player) {
        if (!this.pongs.containsKey(player)) {
            this.pongs.put(player, new AtomicInteger(1));
            return 1;
        }

        return this.pongs.get(player).incrementAndGet();
    }

}
