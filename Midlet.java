import assets.*;
import core.app.*;
import core.fx.*;
import core.input.*;
import core.item.*;
import core.map.*;
import core.monster.*;
import core.npc.*;
import core.player.*;
import core.quest.*;
import core.render.*;
import core.save.*;
import core.story.*;
import core.ui.*;
import core.util.*;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class Midlet extends MIDlet implements Runnable {
    private static boolean needsDefaultsInit = true;

    public Midlet() {
        App.midlet = this;
        App.loader = this;
        FontGraph.init();
        Display.getDisplay(this).setCurrent(App.canvas);
        GameCanvas.start();
    }

    public void run() {
        try {
            Thread.sleep(200L);
            Thread.yield();
            GameState.tick = MathUtil.abs((int) System.currentTimeMillis() % 1000);
            LoadingScreen.target = 30;
            Thread.yield();
            MapStory.apply(GameMap.id);
            TileRenderer.loadMap(GameMap.id);
            Houses.setup(GameMap.id);
            Signs.setup(GameMap.id);
            LoadingScreen.target = 50;
            Thread.yield();
            Npcs.setup(GameMap.id);
            HeroSprites.load();
            Girl.setup(GameMap.id);
            Classmates.setup(GameMap.id);
            Dog.setup(GameMap.id);
            LoadingScreen.target = 65;
            Thread.yield();
            MonsterSpawns.setup(GameMap.id);
            LoadingScreen.target = 70;
            Thread.yield();
            Ninjas.setup(GameMap.id);
            BoarBoss.setup(GameMap.id);
            RiverMonster.setup(GameMap.id);
            FinalBoss.setup(GameMap.id);
            Toniku.setup(GameMap.id);
            LoadingScreen.target = 75;
            MoneyDrops.init();
            QuestItem.loadIcon();
            PotionDrops.init();
            LoadingScreen.target = 78;
            Thread.yield();
            Crates.setup(GameMap.id);
            Motorbikes.setup(GameMap.id);
            LoadingScreen.target = 80;
            Dust.init();
            WaterSplash.init();
            LoadingScreen.target = 82;
            Thread.yield();
            Blood.init();
            FloatingText.init();
            Darts.init();
            LoadingScreen.target = 85;
            Projectiles.init(GameMap.id);
            ArcProjectiles.init();
            Aura.init();
            LoadingScreen.target = 87;
            Hud.loadArrowIcons();
            Viewport.setup();
            Hud.setup();
            LoadingScreen.target = 95;
            Thread.yield();
            Sky.setup(Save.backgroundIndex, GameMap.id, Viewport.viewWidth, Viewport.viewHeight, Settings.lowGraphics);
            if (GameMap.id < 19) {
                WorldMap.visited[GameMap.id] = true;
            }

            Hud.dirty = true;
            Hud.leftSoftKeyLabel = "Menu";
            Hud.rightSoftKeyLabel = " ";
            Story.prologueNoRespawn = false;
            MapStory.onEnter(GameMap.id);
            LoadingScreen.target = 100;
            if (needsDefaultsInit) {
                needsDefaultsInit = false;
                PlayerStats.initDefaults();
                QuestState.current = Quest.NONE;
                Player.moveSpeed = 5;
                Player.runOnWaterSpeed = 6;
                Player.underwaterSpeed = 2;
                Player.motorbikeSpeed = 8;
                Player.action = PlayerAction.FALL;
                Player.vx = 1;
                Player.vy = 1;
                Player.pose = 0;
                Player.actionTimer2 = 0;
                Player.actionTimer = 0;
            }

            Input.clearPressed();
            Input.clearHeld();
            if (Save.pendingLoad) {
                SaveGame.load();
                Save.pendingLoad = false;
            } else if (Save.pendingSave) {
                SaveGame.save();
                Save.pendingSave = false;
            }

            Sound.loadTrack(Sound.trackForMap(GameMap.id));

            if (!Sound.isDisabled()) {
                Sound.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            App.shutdown();
        }

        while (LoadingScreen.progress < 90) {
            Thread.yield();
        }

        GameState.current = GameState.next;
    }

    // =====================================================================================
    // #region App

    protected void startApp() {
    }

    protected void pauseApp() {
    }

    protected void destroyApp(boolean unconditional) {
        Sound.stopAndRelease();
    }

    // #endregion
    // =====================================================================================

}
