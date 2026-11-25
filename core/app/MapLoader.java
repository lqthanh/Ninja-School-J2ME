package core.app;

import core.map.Crates;
import core.map.GameMap;
import core.monster.BoarBoss;
import core.monster.Monsters;
import core.monster.Ninjas;
import core.monster.Projectiles;
import core.monster.RiverMonster;
import core.npc.Classmates;
import core.npc.Dog;
import core.npc.Npcs;
import core.player.Motorbikes;
import core.ui.LoadingScreen;

/** Chuyển map: giải phóng ảnh của map cũ và chạy màn hình tải. */
public final class MapLoader {
    private MapLoader() {
    }

    /** Bỏ ảnh của NPC, quái, boss... thuộc map cũ để giải phóng bộ nhớ. */
    public static void releaseSprites() {
        Npcs.sprites = null;
        System.gc();
        Motorbikes.sprites = null;
        Crates.sprite = null;
        BoarBoss.sprites = null;
        System.gc();
        RiverMonster.sprites = null;
        System.gc();
        Monsters.sprites = null;
        System.gc();
        Ninjas.releaseSprites();
        Classmates.heads = null;
        Dog.sprites = null;
        Projectiles.sprites = null;
        System.gc();
    }

    /** Chuyển sang map {@code targetMap}: hiện màn hình tải và nạp map ở thread riêng (Midlet.run). */
    public static void change(int targetMap) {
        GameState.current = GameState.LOADING;
        GameMap.id = targetMap;
        GameState.next = GameState.PLAYING;
        LoadingScreen.reset();
        App.startLoader();
    }
}
