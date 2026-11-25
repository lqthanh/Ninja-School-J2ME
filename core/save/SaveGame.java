package core.save;

import assets.Character;
import core.app.GameState;
import core.item.Inventory;
import core.map.Crates;
import core.map.GameMap;
import core.monster.BoarBoss;
import core.monster.FinalBoss;
import core.monster.RiverMonster;
import core.npc.Classmates;
import core.npc.Girl;
import core.npc.Toniku;
import core.player.Motorbikes;
import core.player.Player;
import core.player.PlayerStats;
import core.quest.QuestItem;
import core.quest.QuestState;
import core.quest.Skills;
import core.quest.Story;
import core.render.Camera;
import core.render.HeroSprites;
import core.story.MapEvent;
import core.ui.GuideMarker;
import core.ui.WorldMap;
import javax.microedition.lcdui.Image;

/** Ghi / đọc toàn bộ trạng thái game vào RecordStore. */
public final class SaveGame {
    private SaveGame() {
    }

    /** Ghi toàn bộ trạng thái game vào bản ghi "ninjasave". */
    public static void save() {
        int[] data = new int[500];
        int index = 0;
        PlayTime.checkpoint();
        ++index;
        data[0] = PlayTime.savedSeconds;
        ++index;
        data[1] = GameMap.id;
        ++index;
        data[2] = GameState.tick;
        ++index;
        data[3] = Story.prologueActive ? 1 : 0;
        ++index;
        data[4] = Inventory.money;
        ++index;
        data[5] = Classmates.stayPut ? 1 : 0;
        ++index;
        data[6] = Story.riverMonsterDefeated ? 1 : 0;
        ++index;
        data[7] = Story.tonikuDefeated ? 1 : 0;
        ++index;
        data[8] = Story.sakuraFound ? 1 : 0;
        ++index;
        data[9] = Girl.following ? 1 : 0;
        ++index;
        data[10] = Girl.unusedSaveSlot;
        ++index;
        data[11] = Girl.mapId;
        ++index;
        data[12] = Player.motorbike;
        ++index;
        data[13] = Inventory.swordLevel;
        ++index;
        data[14] = Skills.dartsLevel2 ? 1 : 0;

        int i;
        for(i = 0; i < WorldMap.visited.length; ++i) {
            data[index++] = WorldMap.visited[i] ? 1 : 0;
        }

        for(i = 0; i < Inventory.potionCounts.length; ++i) {
            data[index++] = Inventory.potionCounts[i];
        }

        data[index++] = Skills.lightness ? 1 : 0;
        data[index++] = Skills.doubleJump ? 1 : 0;
        data[index++] = Skills.darts ? 1 : 0;
        data[index++] = Skills.burrow ? 1 : 0;
        data[index++] = Skills.motorbikeLicense ? 1 : 0;
        data[index++] = Skills.doubleStrength ? 1 : 0;
        data[index++] = Skills.dive ? 1 : 0;
        data[index++] = Skills.runOnWater ? 1 : 0;
        data[index++] = Skills.transform ? 1 : 0;
        data[index++] = Story.goldenBatQuestDone ? 1 : 0;
        data[index++] = Story.tonikuQuestDone ? 1 : 0;
        data[index++] = Story.girlWaiting ? 1 : 0;
        data[index++] = Story.girlOutingDone ? 1 : 0;
        data[index++] = Skills.tripleJump ? 1 : 0;
        data[index++] = Skills.sprint ? 1 : 0;
        data[index++] = Skills.standOnWater ? 1 : 0;
        data[index++] = Skills.hide ? 1 : 0;
        data[index++] = Story.gameCompleted ? 1 : 0;
        data[index++] = Story.sakuraEscorting ? 1 : 0;
        data[index++] = Player.slashEffect;
        data[index++] = Player.weapon;
        data[index++] = Player.x;
        data[index++] = Player.y;
        data[index++] = Player.vx;
        data[index++] = Player.vy;
        data[index++] = Player.action;
        data[index++] = Player.pose;
        data[index++] = Player.width;
        data[index++] = Player.height;
        data[index++] = Player.halfWidth;
        data[index++] = Player.halfHeight;
        data[index++] = Player.quarterWidth;
        data[index++] = Player.quarterHeight;
        data[index++] = Player.facing;
        data[index++] = Player.actionTimer;
        data[index++] = Player.actionTimer2;
        data[index++] = Player.hurtRecovery;
        data[index++] = PlayerStats.attack;
        data[index++] = PlayerStats.dartDamage;
        data[index++] = PlayerStats.hp;
        data[index++] = PlayerStats.maxHp;
        data[index++] = PlayerStats.mp;
        data[index++] = PlayerStats.maxMp;
        data[index++] = Player.runOnWaterSpeed;
        data[index++] = Player.underwaterSpeed;
        data[index++] = Player.sprintBonus;
        data[index++] = Player.checkpointX;
        data[index++] = Player.checkpointY;
        data[index++] = Player.comboCount;
        data[index++] = Player.comboStep;
        data[index++] = PlayerStats.level;
        data[index++] = PlayerStats.exp;
        data[index++] = Camera.followsPlayer ? 1 : 0;
        data[index++] = Player.comboQueued ? 1 : 0;
        data[index++] = Player.hidden ? 1 : 0;
        data[index++] = Player.transformed ? 1 : 0;
        data[index++] = QuestState.current;
        data[index++] = QuestState.dropMonster;
        data[index++] = QuestState.dropChance;
        data[index++] = QuestState.itemFound ? 1 : 0;
        data[index++] = QuestState.progress;
        data[index++] = Player.nearNpc;
        data[index++] = Player.nearClassmate;
        data[index++] = Girl.slashEffect;
        data[index++] = Girl.weapon;
        data[index++] = QuestItem.x;
        data[index++] = QuestItem.y;
        data[index++] = QuestItem.state;
        data[index++] = Camera.targetX;
        data[index++] = Camera.targetY;
        data[index++] = Camera.vx;
        data[index++] = Camera.vy;
        data[index++] = Camera.subpixelX;
        data[index++] = Camera.subpixelY;
        data[index++] = Camera.x;
        data[index++] = Camera.y;
        data[index++] = Camera.maxX;
        data[index++] = Camera.maxY;
        data[index++] = MapEvent.active ? 1 : 0;
        data[index++] = MapEvent.current;
        data[index++] = MapEvent.step;
        data[index++] = GuideMarker.map;
        data[index++] = GuideMarker.x;
        data[index++] = GuideMarker.y;
        data[index++] = GuideMarker.arrowFrame;
        data[index++] = GuideMarker.arrowX;
        data[index++] = GuideMarker.arrowY;
        data[index++] = GuideMarker.bobDirection;
        data[index++] = GuideMarker.bob;
        data[index++] = GuideMarker.npc;
        data[index++] = Girl.x;
        data[index++] = Girl.y;
        data[index++] = Girl.vx;
        data[index++] = Girl.vy;
        data[index++] = Girl.action;
        data[index++] = Girl.pose;
        data[index++] = Girl.width;
        data[index++] = Girl.height;
        data[index++] = Girl.halfWidth;
        data[index++] = Girl.halfHeight;
        data[index++] = Girl.quarterWidth;
        data[index++] = Girl.quarterHeight;
        data[index++] = Girl.facing;
        data[index++] = Girl.actionTimer;
        data[index++] = Girl.actionTimer2;
        data[index++] = Girl.airTimer;
        data[index++] = Girl.unused1;
        data[index++] = Girl.unused2;
        data[index++] = Girl.unused3;
        data[index++] = Girl.unused4;
        data[index++] = Girl.unused5;
        data[index++] = Girl.unused6;
        data[index++] = Girl.unused7;
        data[index++] = BoarBoss.hp;
        data[index++] = Toniku.hp;
        data[index++] = FinalBoss.hp;
        data[index++] = RiverMonster.hp;
        data[index++] = FinalBoss.state;
        data[index++] = Motorbikes.count;
        if (Motorbikes.count > 0) {
            for(i = 0; i < Motorbikes.falling.length; ++i) {
                data[index++] = Motorbikes.falling[i];
            }

            for(i = 0; i < Motorbikes.facing.length; ++i) {
                data[index++] = Motorbikes.facing[i];
            }

            for(i = 0; i < Motorbikes.frame.length; ++i) {
                data[index++] = Motorbikes.frame[i];
            }

            for(i = 0; i < Motorbikes.vy.length; ++i) {
                data[index++] = Motorbikes.vy[i];
            }

            for(i = 0; i < Motorbikes.x.length; ++i) {
                data[index++] = Motorbikes.x[i];
            }

            for(i = 0; i < Motorbikes.y.length; ++i) {
                data[index++] = Motorbikes.y[i];
            }
        }

        data[index++] = Crates.count;
        if (Crates.count > 0) {
            data[index++] = Player.crate;
            if (Crates.state != null) {
                for(i = 0; i < Crates.state.length; ++i) {
                    data[index++] = Crates.state[i];
                }
            }

            if (Crates.driftVx != null) {
                for(i = 0; i < Crates.driftVx.length; ++i) {
                    data[index++] = Crates.driftVx[i];
                }
            }

            if (Crates.bobDirection != null) {
                for(i = 0; i < Crates.bobDirection.length; ++i) {
                    data[index++] = Crates.bobDirection[i];
                }
            }

            if (Crates.vx != null) {
                for(i = 0; i < Crates.vx.length; ++i) {
                    data[index++] = Crates.vx[i];
                }
            }

            if (Crates.vy != null) {
                for(i = 0; i < Crates.vy.length; ++i) {
                    data[index++] = Crates.vy[i];
                }
            }

            if (Crates.x != null) {
                for(i = 0; i < Crates.x.length; ++i) {
                    data[index++] = Crates.x[i];
                }
            }

            if (Crates.y != null) {
                for(i = 0; i < Crates.y.length; ++i) {
                    data[index++] = Crates.y[i];
                }
            }
        }

        int[] ints = data;
        int length;
        byte[] bytes = new byte[(length = data.length) << 2];

        for(int j = 0; j < length; ++j) {
            bytes[j << 2] = (byte)(ints[j] >> 24);
            bytes[(j << 2) + 1] = (byte)(ints[j] >>> 16);
            bytes[(j << 2) + 2] = (byte)(ints[j] >>> 8);
            bytes[(j << 2) + 3] = (byte)ints[j];
        }

        Save.saveRecord("ninjasave", bytes);
        Save.markSaved();
    }

    /** Đọc lại trạng thái game từ bản ghi "ninjasave". */
    public static void load() {
        int[] data = bytesToInts(Save.loadRecord("ninjasave"));
        int index = 0;
        ++index;
        PlayTime.savedSeconds = data[0];
        ++index;
        GameMap.id = data[1];
        ++index;
        GameState.tick = data[2];
        ++index;
        Story.prologueActive = data[3] == 1;
        ++index;
        Inventory.money = data[4];
        ++index;
        Classmates.stayPut = data[5] == 1;
        ++index;
        Story.riverMonsterDefeated = data[6] == 1;
        ++index;
        Story.tonikuDefeated = data[7] == 1;
        ++index;
        Story.sakuraFound = data[8] == 1;
        ++index;
        Girl.following = data[9] == 1;
        ++index;
        Girl.unusedSaveSlot = data[10];
        ++index;
        Girl.mapId = data[11];
        ++index;
        Player.motorbike = data[12];
        ++index;
        Inventory.swordLevel = data[13];
        ++index;
        Skills.dartsLevel2 = data[14] == 1;

        int i;
        for(i = 0; i < WorldMap.visited.length; ++i) {
            WorldMap.visited[i] = data[index++] == 1;
        }

        for(i = 0; i < Inventory.potionCounts.length; ++i) {
            Inventory.potionCounts[i] = data[index++];
        }

        Skills.lightness = data[index++] == 1;
        Skills.doubleJump = data[index++] == 1;
        Skills.darts = data[index++] == 1;
        Skills.burrow = data[index++] == 1;
        Skills.motorbikeLicense = data[index++] == 1;
        Skills.doubleStrength = data[index++] == 1;
        Skills.dive = data[index++] == 1;
        Skills.runOnWater = data[index++] == 1;
        Skills.transform = data[index++] == 1;
        Story.goldenBatQuestDone = data[index++] == 1;
        Story.tonikuQuestDone = data[index++] == 1;
        Story.girlWaiting = data[index++] == 1;
        Story.girlOutingDone = data[index++] == 1;
        Skills.tripleJump = data[index++] == 1;
        Skills.sprint = data[index++] == 1;
        Skills.standOnWater = data[index++] == 1;
        Skills.hide = data[index++] == 1;
        Story.gameCompleted = data[index++] == 1;
        Story.sakuraEscorting = data[index++] == 1;
        Player.slashEffect = data[index++];
        Player.weapon = data[index++];
        Player.x = data[index++];
        Player.y = data[index++];
        Player.vx = data[index++];
        Player.vy = data[index++];
        Player.action = data[index++];
        Player.pose = data[index++];
        Player.width = data[index++];
        Player.height = data[index++];
        Player.halfWidth = data[index++];
        Player.halfHeight = data[index++];
        Player.quarterWidth = data[index++];
        Player.quarterHeight = data[index++];
        Player.facing = data[index++];
        Player.actionTimer = data[index++];
        Player.actionTimer2 = data[index++];
        Player.hurtRecovery = data[index++];
        PlayerStats.attack = data[index++];
        PlayerStats.dartDamage = data[index++];
        PlayerStats.hp = data[index++];
        PlayerStats.maxHp = data[index++];
        PlayerStats.mp = data[index++];
        PlayerStats.maxMp = data[index++];
        Player.runOnWaterSpeed = data[index++];
        Player.underwaterSpeed = data[index++];
        Player.sprintBonus = data[index++];
        Player.checkpointX = data[index++];
        Player.checkpointY = data[index++];
        Player.comboCount = data[index++];
        Player.comboStep = data[index++];
        PlayerStats.level = data[index++];
        PlayerStats.exp = data[index++];
        Camera.followsPlayer = data[index++] == 1;
        Player.comboQueued = data[index++] == 1;
        Player.hidden = data[index++] == 1;
        Player.transformed = data[index++] == 1;
        QuestState.current = data[index++];
        QuestState.dropMonster = data[index++];
        QuestState.dropChance = data[index++];
        QuestState.itemFound = data[index++] == 1;
        QuestState.progress = data[index++];
        Player.nearNpc = data[index++];
        Player.nearClassmate = data[index++];
        Girl.slashEffect = data[index++];
        Girl.weapon = data[index++];
        QuestItem.x = data[index++];
        QuestItem.y = data[index++];
        QuestItem.state = data[index++];
        Camera.targetX = data[index++];
        Camera.targetY = data[index++];
        Camera.vx = data[index++];
        Camera.vy = data[index++];
        Camera.subpixelX = data[index++];
        Camera.subpixelY = data[index++];
        Camera.x = data[index++];
        Camera.y = data[index++];
        Camera.maxX = data[index++];
        Camera.maxY = data[index++];
        MapEvent.active = data[index++] == 1;
        MapEvent.current = data[index++];
        MapEvent.step = data[index++];
        GuideMarker.map = data[index++];
        GuideMarker.x = data[index++];
        GuideMarker.y = data[index++];
        GuideMarker.arrowFrame = data[index++];
        GuideMarker.arrowX = data[index++];
        GuideMarker.arrowY = data[index++];
        GuideMarker.bobDirection = data[index++];
        GuideMarker.bob = data[index++];
        GuideMarker.npc = data[index++];
        Girl.x = data[index++];
        Girl.y = data[index++];
        Girl.vx = data[index++];
        Girl.vy = data[index++];
        Girl.action = data[index++];
        Girl.pose = data[index++];
        Girl.width = data[index++];
        Girl.height = data[index++];
        Girl.halfWidth = data[index++];
        Girl.halfHeight = data[index++];
        Girl.quarterWidth = data[index++];
        Girl.quarterHeight = data[index++];
        Girl.facing = data[index++];
        Girl.actionTimer = data[index++];
        Girl.actionTimer2 = data[index++];
        Girl.airTimer = data[index++];
        Girl.unused1 = data[index++];
        Girl.unused2 = data[index++];
        Girl.unused3 = data[index++];
        Girl.unused4 = data[index++];
        Girl.unused5 = data[index++];
        Girl.unused6 = data[index++];
        Girl.unused7 = data[index++];
        if ((BoarBoss.hp = data[index++]) <= 0) {
            BoarBoss.hp = BoarBoss.maxHp;
        }

        Toniku.hp = data[index++];
        FinalBoss.hp = data[index++];
        RiverMonster.hp = data[index++];
        FinalBoss.state = data[index++];
        int savedMotorbikeCount = data[index++];
        if (savedMotorbikeCount > 0) {
            try {
                for(i = 0; i < Motorbikes.falling.length; ++i) {
                    ++index;
                }

                for(i = 0; i < Motorbikes.facing.length; ++i) {
                    ++index;
                }

                for(i = 0; i < Motorbikes.frame.length; ++i) {
                    ++index;
                }

                for(i = 0; i < Motorbikes.vy.length; ++i) {
                    ++index;
                }

                for(i = 0; i < Motorbikes.x.length; ++i) {
                    ++index;
                }

                for(i = 0; i < Motorbikes.y.length; ++i) {
                    ++index;
                }
            } catch (Exception e) {
            }
        }

        if ((Crates.count = data[index++]) > 0) {
            Player.crate = data[index++];

            try {
                for(i = 0; i < Crates.state.length; ++i) {
                    Crates.state[i] = data[index++];
                }

                for(i = 0; i < Crates.driftVx.length; ++i) {
                    Crates.driftVx[i] = data[index++];
                }

                for(i = 0; i < Crates.bobDirection.length; ++i) {
                    Crates.bobDirection[i] = data[index++];
                }

                for(i = 0; i < Crates.vx.length; ++i) {
                    Crates.vx[i] = data[index++];
                }

                for(i = 0; i < Crates.vy.length; ++i) {
                    Crates.vy[i] = data[index++];
                }

                for(i = 0; i < Crates.x.length; ++i) {
                    Crates.x[i] = data[index++];
                }

                for(i = 0; i < Crates.y.length; ++i) {
                    Crates.y[i] = data[index++];
                }
            } catch (Exception e) {
            }
        }

        if (Player.transformed) {
            if (HeroSprites.transformHeads == null) {
                HeroSprites.transformHeads = new Image[4];

                try {
                    HeroSprites.transformHeads[0] = Character.loadHeroTransformHead(0);
                    HeroSprites.transformHeads[1] = Character.loadHeroTransformHead(1);
                } catch (Exception e) {
                }

                HeroSprites.transformHeads[2] = HeroSprites.transformHeads[1];
                HeroSprites.transformHeads[3] = HeroSprites.transformHeads[0];
            }

            for(i = 0; i < 4; ++i) {
                Image swap = HeroSprites.heads[i];
                HeroSprites.heads[i] = HeroSprites.transformHeads[i];
                HeroSprites.transformHeads[i] = swap;
            }
        }

    }

    /** Ghép mảng byte (mỗi số 4 byte) thành mảng int. */
    public static int[] bytesToInts(byte[] bytes) {
        int length;
        int[] ints = new int[(length = bytes.length) >> 2];

        for(int i = 0; i < ints.length; ++i) {
            ints[i] = (bytes[i << 2] << 24) + ((bytes[(i << 2) + 1] & 255) << 16) + ((bytes[(i << 2) + 2] & 255) << 8) + (bytes[(i << 2) + 3] & 255);
        }

        return ints;
    }
}
