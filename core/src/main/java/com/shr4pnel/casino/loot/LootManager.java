package com.shr4pnel.casino.loot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LootManager {
    private List<Loot> loot;

    public LootManager() {
        loot = new ArrayList<>();
    }

    public LootManager(List<Loot> loot) {
        this.loot = new ArrayList<>(loot);
    }

    public void addLoot(Loot loot) {
        this.loot.add(loot);
    }

    public Loot getRandomLoot() {
        if (loot.isEmpty()) return null;

        int totalWeight = 0;
        for (Loot item : loot) {
            totalWeight += item.getWeight();
        }

        int randomValue = ThreadLocalRandom.current().nextInt(totalWeight);
        int cumulativeWeight = 0;

        for (Loot item : loot) {
            cumulativeWeight += item.getWeight();
            if (randomValue < cumulativeWeight) {
                return item;
            }
        }

        return null; // should never reach here
    }
}
