package core.render;

import assets.Character;
import assets.Effect;
import core.app.GameState;
import core.item.Inventory;
import core.npc.Classmates;
import core.player.Player;
import core.player.Poses;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/** Ảnh nhân vật chính (đầu, thân, chân, hiệu ứng chém) và cách vẽ nhân vật / bạn học. */
public final class HeroSprites {
    /** Ảnh thân, đầu (thường / biến hình) và chân của nhân vật; bạn học dùng chung ảnh thân và chân. */
    public static Image[] bodies;
    public static Image[] heads;
    public static Image[] transformHeads;
    public static Image[] legs;

    /** Ảnh vệt chém theo cấp kiếm. */
    public static Image[] slashEffects;

    private HeroSprites() {
    }

    /** Nạp ảnh nhân vật (một lần) và ảnh vệt chém. */
    public static final void load() {
        Player.slashEffect = -1;
        if (bodies == null) {
            try {
                bodies = new Image[20];

                int i;
                for(i = 0; i < 20; ++i) {
                    bodies[i] = Character.loadHeroBody(i);
                }

                heads = new Image[11];

                for(i = 0; i < 11; ++i) {
                    heads[i] = Character.loadHeroHead(i);
                }

                legs = new Image[10];

                for(i = 0; i < 10; ++i) {
                    legs[i] = Character.loadHeroLegs(i);
                }
            } catch (Exception e) {
            }
        }

        reloadSlashEffects();
        Player.width = 22;
        Player.height = 32;
        Player.halfWidth = Player.width >> 1;
        Player.halfHeight = Player.height >> 1;
        Player.quarterWidth = Player.width >> 2;
        Player.quarterHeight = Player.height >> 2;
        Player.nearNpc = -1;
    }

    /** Nạp lại ảnh vệt chém theo cấp kiếm hiện tại. */
    public static void reloadSlashEffects() {
        slashEffects = null;
        System.gc();
        slashEffects = Effect.loadSlashes(Inventory.swordLevel);

    }

    /** Vẽ nhân vật (đầu, chân, thân) theo tư thế hiện tại và vệt chém; đang ẩn thân thì dùng tư thế ẩn. */
    public static void drawPlayer(Graphics g) {
        if (Player.hidden) {
            if (GameState.tick % 20 > 2) {
                Player.pose = 25;
            } else {
                Player.pose = 26;
            }
        }

        if (Player.facing == 1) {
            g.drawImage(heads[Poses.heroPoses[Player.pose][0][0]], Player.x + Poses.heroPoses[Player.pose][0][1], Player.y - Poses.heroPoses[Player.pose][0][2], 0);
            g.drawImage(legs[Poses.heroPoses[Player.pose][1][0]], Player.x + Poses.heroPoses[Player.pose][1][1], Player.y - Poses.heroPoses[Player.pose][1][2], 0);
            g.drawImage(bodies[Poses.heroPoses[Player.pose][2][0]], Player.x + Poses.heroPoses[Player.pose][2][1], Player.y - Poses.heroPoses[Player.pose][2][2], 0);
            if (Player.slashEffect != -1) {
                g.drawImage(slashEffects[Player.slashEffect], Player.x - Poses.slashOffsetX[Player.slashEffect], Player.y - Poses.slashOffsetY[Player.slashEffect], 0);
                return;
            }
        } else {
            g.drawRegion(heads[Poses.heroPoses[Player.pose][0][0]], 0, 0, heads[Poses.heroPoses[Player.pose][0][0]].getWidth(), heads[Poses.heroPoses[Player.pose][0][0]].getHeight(), 2, Player.x - Poses.heroPoses[Player.pose][0][1], Player.y - Poses.heroPoses[Player.pose][0][2], 24);
            g.drawRegion(legs[Poses.heroPoses[Player.pose][1][0]], 0, 0, legs[Poses.heroPoses[Player.pose][1][0]].getWidth(), legs[Poses.heroPoses[Player.pose][1][0]].getHeight(), 2, Player.x - Poses.heroPoses[Player.pose][1][1], Player.y - Poses.heroPoses[Player.pose][1][2], 24);
            g.drawRegion(bodies[Poses.heroPoses[Player.pose][2][0]], 0, 0, bodies[Poses.heroPoses[Player.pose][2][0]].getWidth(), bodies[Poses.heroPoses[Player.pose][2][0]].getHeight(), 2, Player.x - Poses.heroPoses[Player.pose][2][1], Player.y - Poses.heroPoses[Player.pose][2][2], 24);
            if (Player.slashEffect != -1) {
                g.drawRegion(slashEffects[Player.slashEffect], 0, 0, slashEffects[Player.slashEffect].getWidth(), slashEffects[Player.slashEffect].getHeight(), 2, Player.x + Poses.slashOffsetX[Player.slashEffect], Player.y - Poses.slashOffsetY[Player.slashEffect], 24);
            }
        }

    }

    /** Vẽ các bạn học (thân, chân, đầu riêng) và vệt chém của họ. */
    public static void drawClassmates(Graphics g) {
        for(int i = 0; i < Classmates.count; ++i) {
            if (Classmates.x[i] + Player.halfWidth >= Camera.x && Classmates.x[i] - Player.halfWidth <= Camera.x + Viewport.viewWidth && Classmates.y[i] >= Camera.y && Classmates.y[i] - Player.height <= Camera.y + Viewport.viewHeight) {
                if (Classmates.facing[i] == 1) {
                    g.drawImage(Classmates.heads[Classmates.skin[i]][Poses.classmatePoses[Classmates.pose[i]][0][0]], Classmates.x[i] + Poses.classmatePoses[Classmates.pose[i]][0][1], Classmates.y[i] - Poses.classmatePoses[Classmates.pose[i]][0][2], 0);
                    g.drawImage(legs[Poses.classmatePoses[Classmates.pose[i]][1][0]], Classmates.x[i] + Poses.classmatePoses[Classmates.pose[i]][1][1], Classmates.y[i] - Poses.classmatePoses[Classmates.pose[i]][1][2], 0);
                    g.drawImage(bodies[Poses.classmatePoses[Classmates.pose[i]][2][0]], Classmates.x[i] + Poses.classmatePoses[Classmates.pose[i]][2][1], Classmates.y[i] - Poses.classmatePoses[Classmates.pose[i]][2][2], 0);
                    if (Classmates.slashEffect[i] != -1) {
                        g.drawImage(slashEffects[Classmates.slashEffect[i]], Classmates.x[i] - Poses.slashOffsetX[Classmates.slashEffect[i]], Classmates.y[i] - Poses.slashOffsetY[Classmates.slashEffect[i]], 0);
                    }
                } else {
                    g.drawRegion(Classmates.heads[Classmates.skin[i]][Poses.classmatePoses[Classmates.pose[i]][0][0]], 0, 0, Classmates.heads[Classmates.skin[i]][Poses.classmatePoses[Classmates.pose[i]][0][0]].getWidth(), Classmates.heads[Classmates.skin[i]][Poses.classmatePoses[Classmates.pose[i]][0][0]].getHeight(), 2, Classmates.x[i] - Poses.classmatePoses[Classmates.pose[i]][0][1], Classmates.y[i] - Poses.classmatePoses[Classmates.pose[i]][0][2], 24);
                    g.drawRegion(legs[Poses.classmatePoses[Classmates.pose[i]][1][0]], 0, 0, legs[Poses.classmatePoses[Classmates.pose[i]][1][0]].getWidth(), legs[Poses.classmatePoses[Classmates.pose[i]][1][0]].getHeight(), 2, Classmates.x[i] - Poses.classmatePoses[Classmates.pose[i]][1][1], Classmates.y[i] - Poses.classmatePoses[Classmates.pose[i]][1][2], 24);
                    g.drawRegion(bodies[Poses.classmatePoses[Classmates.pose[i]][2][0]], 0, 0, bodies[Poses.classmatePoses[Classmates.pose[i]][2][0]].getWidth(), bodies[Poses.classmatePoses[Classmates.pose[i]][2][0]].getHeight(), 2, Classmates.x[i] - Poses.classmatePoses[Classmates.pose[i]][2][1], Classmates.y[i] - Poses.classmatePoses[Classmates.pose[i]][2][2], 24);
                    if (Classmates.slashEffect[i] != -1) {
                        g.drawRegion(slashEffects[Classmates.slashEffect[i]], 0, 0, slashEffects[Classmates.slashEffect[i]].getWidth(), slashEffects[Classmates.slashEffect[i]].getHeight(), 2, Classmates.x[i] + Poses.slashOffsetX[Classmates.slashEffect[i]], Classmates.y[i] - Poses.slashOffsetY[Classmates.slashEffect[i]], 24);
                    }
                }
            }
        }

    }
}
