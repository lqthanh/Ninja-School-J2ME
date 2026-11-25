package core.story;

import core.map.GameMap;
import core.monster.BoarBoss;
import core.npc.Classmates;
import core.npc.Girl;
import core.player.Player;
import core.player.PlayerAction;
import core.player.PlayerStats;
import core.quest.Quest;
import core.quest.QuestItem;
import core.quest.QuestState;
import core.quest.Skills;
import core.quest.Story;
import core.render.Camera;
import core.render.Viewport;
import core.ui.WorldMap;

/** Thiết lập theo cốt truyện khi nạp / vào map. */
public final class MapStory {
    private MapStory() {
    }

    /** Trước khi nạp map: đặt vị trí nhân vật, cô bé, bạn học... theo cốt truyện (vd. lễ ra trường ở map 94). */
    public static void apply(int map) {
        if (map == 94) {
            Classmates.stayPut = true;
            Player.x = 24;
            Player.y = 288;
            Girl.mapId = 94;
            Girl.x = 12;
            Girl.y = 288;
            Girl.facing = 1;
            Girl.following = false;
            Player.facing = 1;
        }

        if (map == 0 && QuestState.current == Quest.FIND_HIDE_SCROLL && QuestState.itemFound) {
            Girl.mapId = -1;
            Girl.following = false;
        }

        if (map == 0 && QuestState.current == Quest.RESCUE_SAKURA && Story.sakuraEscorting) {
            Story.gameCompleted = true;
            Girl.mapId = 0;
            Girl.following = true;
            Camera.followsPlayer = true;
            QuestState.current = Quest.NONE;
            Player.x = 984;
            Player.y = 408;
        } else {
            if (map == 95 && !Story.sakuraFound) {
                Girl.following = false;
                Girl.x = 599;
                Girl.y = 264;
                Girl.facing = -1;
                Girl.mapId = 95;
            }

        }
    }

    /** Sau khi nạp map: đặt camera, vật phẩm nhiệm vụ và sự kiện map theo cốt truyện / nhiệm vụ đang làm. */
    public static void onEnter(int map) {
        MapEvent.active = false;
        Cutscene.playing = false;
        QuestItem.state = 0;
        if (map == 15 && QuestState.current == Quest.FIND_HIDE_SCROLL) {
            QuestItem.place(216, 432);
        }

        if (map == 8 && QuestState.current == Quest.CROSS_RIVER) {
            QuestItem.place(72, 240);
        }

        int i;
        if (map == 4 && Story.prologueActive) {
            Story.prologueNoRespawn = true;
            Skills.lightness = true;
            Skills.transform = true;
            Skills.runOnWater = true;
            PlayerStats.setMaxHpMp(1000, 2000);
            PlayerStats.attack = 25;
            Player.action = PlayerAction.FALL;
            BoarBoss.maxHp = 500;
            BoarBoss.hp = 500;
            MapEvent.start(0);
        } else if (map == 99) {
            Story.prologueActive = true;
            Classmates.stayPut = true;
            Player.x = 108;
            Player.y = 312;
            Player.facing = -1;
            MapEvent.start(1);
        } else if (map == 94) {
            MapEvent.start(10);
        } else if (map == 0 && Story.prologueActive) {
            Story.prologueNoRespawn = false;
            Skills.lightness = false;
            Skills.runOnWater = false;
            Skills.transform = false;
            Player.transformed = false;
            PlayerStats.resetForNewGame();
            BoarBoss.maxHp = 1000;
            BoarBoss.hp = 1000;
            Player.x = 864;
            Player.y = 336;

            for(i = 0; i < 19; ++i) {
                WorldMap.visited[i] = false;
            }

            WorldMap.visited[0] = true;
            Player.facing = -1;
            Classmates.stayPut = false;
            Classmates.gathering = false;
            MapEvent.start(2);
            Story.prologueActive = false;
        } else if (map == 3 && QuestState.current == Quest.ESCORT_AYUMI_SISTER && !Girl.following) {
            MapEvent.start(3);
        } else if (map == 0 && QuestState.current == Quest.ESCORT_AYUMI_SISTER && Girl.following) {
            MapEvent.start(4);
        } else if (map == 97 && !Story.riverMonsterDefeated) {
            MapEvent.start(5);
        } else if (map == 96 && !Story.tonikuDefeated) {
            MapEvent.start(6);
        } else if (map == 95 && !Story.sakuraFound) {
            MapEvent.start(7);
        } else if (map == 101) {
            MapEvent.start(8);
            Camera.y = Camera.targetY = 336 - Viewport.viewHeight;
            Camera.targetX = 24;
            Camera.x = 24;
        } else if (map == 9 && QuestState.current == Quest.GIRL_OUTING) {
            MapEvent.start(9);
        }

        if (map == 97 && Story.riverMonsterDefeated) {
            for(i = 0; i <= 6; ++i) {
                GameMap.clearFlags(24, i);
            }
        }

        if (map == 96 && Story.tonikuDefeated) {
            for(i = 0; i <= 11; ++i) {
                GameMap.clearFlags(24, i);
            }
        }

    }
}
