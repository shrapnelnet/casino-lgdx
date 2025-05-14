package com.shr4pnel.casino.loot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LootManager {
    private List<Loot> loot;

    public LootManager() {
        loot = new ArrayList<Loot>();
    }

    public LootManager(List<Loot> loot) {
        this.loot = new ArrayList<Loot>();
        this.loot.addAll(loot);
    }

    public void addLoot(Loot loot) {
        this.loot.add(loot);
    }

    public Loot getRandomLoot() {
        if (loot.isEmpty())
            return null;

        ThreadLocalRandom random = ThreadLocalRandom.current();
        int itemInd = random.nextInt(loot.size());
        return loot.get(itemInd);
    }
}
