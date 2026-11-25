package core.render;

import assets.Mob;
import core.app.GameState;
import core.app.Settings;
import core.fx.Aura;
import core.fx.Blood;
import core.fx.Dust;
import core.fx.FloatingText;
import core.fx.WaterSplash;
import core.item.MoneyDrops;
import core.item.PotionDrops;
import core.map.Crates;
import core.map.GameMap;
import core.map.Houses;
import core.map.Signs;
import core.monster.ArcProjectiles;
import core.monster.BoarBoss;
import core.monster.FinalBoss;
import core.monster.Monsters;
import core.monster.NinjaStats;
import core.monster.Ninjas;
import core.monster.Projectiles;
import core.monster.RiverMonster;
import core.npc.Classmates;
import core.npc.Dog;
import core.npc.Girl;
import core.npc.Npcs;
import core.npc.Toniku;
import core.player.Darts;
import core.player.Motorbikes;
import core.player.Player;
import core.player.Poses;
import core.quest.QuestItem;
import core.ui.Dialogue;
import core.ui.GuideMarker;
import core.ui.Hud;
import core.ui.Letterbox;
import core.ui.Notice;
import javax.microedition.lcdui.Graphics;

/** Vẽ màn chơi theo thứ tự lớp: trời, nhà, tile, NPC, boss, quái, nhân vật, hiệu ứng, HUD. */
public final class WorldRenderer {
    private WorldRenderer() {
    }

    /** Vẽ màn hướng dẫn (map 101). */
    public static void drawTutorial(Graphics g) {
        g.setClip(-1, -1, Viewport.screenWidth + 2, Viewport.screenHeight + 1);
        TileRenderer.drawSky(g);
        g.translate(-Camera.x, -Camera.y);
        if (Settings.lowGraphics) {
            TileRenderer.drawTilesLowGraphics(g);
        } else {
            TileRenderer.drawTiles(g);
        }

        Signs.draw(g, Camera.x, Camera.y);
        HeroSprites.drawClassmates(g);
        HeroSprites.drawPlayer(g);
        Npcs.draw(g, Camera.x, Camera.y, GameState.tick);
        TileRenderer.drawForegroundTiles(g);
        Letterbox.draw(g);
        if (Dialogue.visible) {
            Dialogue.draw(g);
        }

    }

    /** Vẽ màn chơi: nền, nhà, tile, NPC, quái, boss, nhân vật, hiệu ứng, HUD. */
    public static void draw(Graphics g) {
        g.setClip(-1, -1, Viewport.viewWidth + 2, Viewport.viewHeight + 2);
        TileRenderer.drawSky(g);
        g.translate(-Camera.x, -Camera.y);

        int i;
        for(i = 0; i < Houses.count; ++i) {
            if (Houses.x[i] + Houses.halfWidth[i] >= Camera.x && Houses.x[i] - Houses.halfWidth[i] <= Camera.x + Viewport.viewWidth && Houses.y[i] >= Camera.y && Houses.y[i] - Houses.height[i] <= Camera.y + Viewport.viewHeight) {
                TileRenderer.parallaxFromCenter = (TileRenderer.parallaxScreenX = Houses.x[i] - Camera.x) - Viewport.viewCenterX;
                TileRenderer.parallaxDrawX = (TileRenderer.parallaxScaled = (GameMap.TILE_SIZE - 2) * TileRenderer.parallaxFromCenter / GameMap.TILE_SIZE) + Viewport.viewCenterX;
                g.drawImage(Houses.images[Houses.types[i]], Camera.x + TileRenderer.parallaxDrawX, Houses.y[i], 33);
            }
        }

        if (Settings.lowGraphics) {
            TileRenderer.drawTilesLowGraphics(g);
        } else {
            TileRenderer.drawTiles(g);
        }

        Signs.draw(g, Camera.x, Camera.y);
        Npcs.draw(g, Camera.x, Camera.y, GameState.tick);
        if (RiverMonster.active) {
            if (RiverMonster.direction < 0) {
                g.drawImage(RiverMonster.sprites[0], RiverMonster.x, RiverMonster.y, 33);
                g.drawImage(RiverMonster.sprites[2], RiverMonster.part2X, RiverMonster.part2Y, 33);
                g.drawImage(RiverMonster.sprites[3], RiverMonster.part3X, RiverMonster.part3Y, 33);
                g.drawImage(RiverMonster.sprites[1], RiverMonster.part1X, RiverMonster.part1Y, 33);
            } else {
                g.drawRegion(RiverMonster.sprites[0], 0, 0, RiverMonster.sprites[0].getWidth(), RiverMonster.sprites[0].getHeight(), 2, RiverMonster.x, RiverMonster.y, 33);
                g.drawRegion(RiverMonster.sprites[2], 0, 0, RiverMonster.sprites[2].getWidth(), RiverMonster.sprites[2].getHeight(), 2, RiverMonster.part2X, RiverMonster.part2Y, 33);
                g.drawRegion(RiverMonster.sprites[3], 0, 0, RiverMonster.sprites[3].getWidth(), RiverMonster.sprites[3].getHeight(), 2, RiverMonster.part3X, RiverMonster.part3Y, 33);
                g.drawRegion(RiverMonster.sprites[1], 0, 0, RiverMonster.sprites[1].getWidth(), RiverMonster.sprites[1].getHeight(), 2, RiverMonster.part1X, RiverMonster.part1Y, 33);
            }

            if (RiverMonster.healthBarTimer != 0) {
                g.setColor(3342353);
                g.drawRect(RiverMonster.x - (RiverMonster.width >> 1) - 1, RiverMonster.y - RiverMonster.height - 7, RiverMonster.width + 2, 4);
                int riverBarWidth = RiverMonster.hp * RiverMonster.width / RiverMonster.maxHp;
                g.setColor(15597568);
                g.fillRect(RiverMonster.x - (RiverMonster.width >> 1), RiverMonster.y - RiverMonster.height - 6, riverBarWidth, 3);
                g.setColor(4456482);
                g.fillRect(RiverMonster.x - (RiverMonster.width >> 1) + riverBarWidth, RiverMonster.y - RiverMonster.height - 6, RiverMonster.width - riverBarWidth + 1, 3);
            }
        }

        for(i = 0; i < Crates.count; ++i) {
            if (Camera.x <= Crates.x[i] + 12 && Camera.x + Viewport.viewWidth >= Crates.x[i] - 12 && Camera.y <= Crates.y[i] && Camera.y + Viewport.viewHeight >= Crates.y[i] - 24) {
                g.drawImage(Crates.sprite, Crates.x[i], Crates.y[i], 33);
            }
        }

        for(i = 0; i < Motorbikes.count; ++i) {
            if (Camera.x <= Motorbikes.x[i] + 12 && Camera.x + Viewport.viewWidth >= Motorbikes.x[i] - 12 && Camera.y <= Motorbikes.y[i] && Camera.y + Viewport.viewHeight >= Motorbikes.y[i] - 24) {
                if (Motorbikes.facing[i] == 1) {
                    g.drawImage(Motorbikes.sprites[Motorbikes.frame[i]], Motorbikes.x[i], Motorbikes.y[i], 33);
                } else {
                    g.drawRegion(Motorbikes.sprites[Motorbikes.frame[i]], 0, 0, Motorbikes.sprites[Motorbikes.frame[i]].getWidth(), Motorbikes.sprites[Motorbikes.frame[i]].getHeight(), 2, Motorbikes.x[i], Motorbikes.y[i], 33);
                }
            }
        }

        MoneyDrops.draw(g, Camera.x, Camera.y);
        PotionDrops.draw(g, Camera.x, Camera.y);

        int barWidth;
        if (BoarBoss.active && BoarBoss.state != BoarBoss.STATE_GONE && Camera.x + Viewport.viewWidth >= BoarBoss.x - (BoarBoss.width >> 1) && Camera.x <= BoarBoss.x + (BoarBoss.width >> 1) && Camera.y <= BoarBoss.y && Camera.y + Viewport.viewHeight >= BoarBoss.y - BoarBoss.height) {
            byte bob = 0;
            if (BoarBoss.frame == 0) {
                bob = -2;
                if (BoarBoss.state == BoarBoss.STATE_IDLE && GameState.tick % 10 > 5) {
                    bob = -1;
                }

                if (BoarBoss.direction == 1) {
                    g.drawImage(BoarBoss.sprites[4], BoarBoss.x, BoarBoss.y + 1, 33);
                } else {
                    g.drawRegion(BoarBoss.sprites[4], 0, 0, BoarBoss.sprites[4].getWidth(), BoarBoss.sprites[4].getHeight(), 2, BoarBoss.x, BoarBoss.y + 1, 33);
                }
            }

            if (BoarBoss.direction == 1) {
                g.drawImage(BoarBoss.sprites[BoarBoss.frame], BoarBoss.x, BoarBoss.y + bob + 1, 33);
            } else {
                g.drawRegion(BoarBoss.sprites[BoarBoss.frame], 0, 0, BoarBoss.sprites[BoarBoss.frame].getWidth(), BoarBoss.sprites[BoarBoss.frame].getHeight(), 2, BoarBoss.x, BoarBoss.y + bob + 1, 33);
            }

            if (BoarBoss.state == BoarBoss.STATE_HURT) {
                if (BoarBoss.direction == 1) {
                    g.drawImage(BoarBoss.sprites[3], BoarBoss.x + 10, BoarBoss.y - 25 + 1, 0);
                } else {
                    g.drawRegion(BoarBoss.sprites[3], 0, 0, BoarBoss.sprites[3].getWidth(), BoarBoss.sprites[3].getHeight(), 2, BoarBoss.x - 10, BoarBoss.y - 25 + 1, 24);
                }
            }

            if (BoarBoss.hurtFlash != 0) {
                g.setColor(3342353);
                g.drawRect(BoarBoss.x - (BoarBoss.width >> 1) - 1, BoarBoss.y - BoarBoss.height - 7, BoarBoss.width + 2, 4);
                barWidth = BoarBoss.hp * BoarBoss.width / BoarBoss.maxHp;
                g.setColor(15597568);
                g.fillRect(BoarBoss.x - (BoarBoss.width >> 1), BoarBoss.y - BoarBoss.height - 6, barWidth, 3);
                g.setColor(4456482);
                g.fillRect(BoarBoss.x - (BoarBoss.width >> 1) + barWidth, BoarBoss.y - BoarBoss.height - 6, BoarBoss.width - barWidth + 1, 3);
            }
        }

        if (FinalBoss.active) {
            if (FinalBoss.direction > 0) {
                for(i = 0; i < 6; ++i) {
                    g.drawImage(FinalBoss.sprites[i], FinalBoss.partX[i], FinalBoss.partY[i], 33);
                }
            } else {
                for(i = 0; i < 6; ++i) {
                    g.drawRegion(FinalBoss.sprites[i], 0, 0, FinalBoss.sprites[i].getWidth(), FinalBoss.sprites[i].getHeight(), 2, FinalBoss.partX[i], FinalBoss.partY[i], 33);
                }
            }

            if (FinalBoss.healthBarTimer != 0) {
                g.setColor(3342353);
                g.drawRect(FinalBoss.x - (FinalBoss.width >> 1) - 1, FinalBoss.y - FinalBoss.height - 7, FinalBoss.width + 2, 4);
                int bossBarWidth = FinalBoss.hp * FinalBoss.width / FinalBoss.maxHp;
                g.setColor(15597568);
                g.fillRect(FinalBoss.x - (FinalBoss.width >> 1), FinalBoss.y - FinalBoss.height - 6, bossBarWidth, 3);
                g.setColor(4456482);
                g.fillRect(FinalBoss.x - (FinalBoss.width >> 1) + bossBarWidth, FinalBoss.y - FinalBoss.height - 6, FinalBoss.width - bossBarWidth + 1, 3);
            }
        }

        for(i = 0; i < Monsters.count; ++i) {
            if (Monsters.state[i] != Monsters.STATE_RESPAWN_WAIT && Monsters.state[i] != Monsters.STATE_GONE && Monsters.x[i] >= Camera.x - 10 && Monsters.x[i] <= Camera.x + Viewport.viewWidth + 10 && Monsters.y[i] >= Camera.y && Monsters.y[i] <= Camera.y + Viewport.viewHeight + 20) {
                g.drawImage(Monsters.sprites[Monsters.group[i]][Monsters.direction[i] >= 0 ? Mob.MONSTER_DIR_RIGHT : Mob.MONSTER_DIR_LEFT][Monsters.frame[i]], Monsters.x[i], Monsters.y[i], 33);
                if (Monsters.healthBarTimer[i] != 0) {
                    g.setColor(13073);
                    g.drawRect(Monsters.x[i] - (Monsters.width[i] >> 1) - 1, Monsters.y[i] - Monsters.height[i] - 7, Monsters.width[i] + 2, 4);
                    barWidth = Monsters.hp[i] * Monsters.width[i] / Monsters.maxHp[i];
                    g.setColor(60928);
                    g.fillRect(Monsters.x[i] - (Monsters.width[i] >> 1), Monsters.y[i] - Monsters.height[i] - 6, barWidth, 3);
                    g.setColor(17442);
                    g.fillRect(Monsters.x[i] - (Monsters.width[i] >> 1) + barWidth, Monsters.y[i] - Monsters.height[i] - 6, Monsters.width[i] - barWidth + 1, 3);
                }
            }
        }

        for(i = 0; i < Ninjas.count; ++i) {
            if (Ninjas.state[i] != Ninjas.STATE_DEAD && Ninjas.x[i] + 15 >= Camera.x && Ninjas.x[i] - 15 <= Camera.x + Viewport.viewWidth && Ninjas.y[i] >= Camera.y && Ninjas.y[i] - 30 <= Camera.y + Viewport.viewHeight) {
                if (Ninjas.direction[i] == -1) {
                    g.drawImage(Ninjas.legSprites[Ninjas.legsFrame[i]], Ninjas.x[i] + NinjaStats.legsOffsetX[Ninjas.type[i]], Ninjas.y[i], 33);
                    g.drawImage(Ninjas.bodySprites[Ninjas.type[i]][Ninjas.bodyFrame[i]], Ninjas.bodyX[i] + NinjaStats.bodyOffsetX[Ninjas.type[i]][Ninjas.bodyFrame[i]], Ninjas.bodyY[i], 33);
                } else {
                    g.drawRegion(Ninjas.legSprites[Ninjas.legsFrame[i]], 0, 0, Ninjas.legSprites[Ninjas.legsFrame[i]].getWidth(), Ninjas.legSprites[Ninjas.legsFrame[i]].getHeight(), 2, Ninjas.x[i] - NinjaStats.legsOffsetX[Ninjas.type[i]], Ninjas.y[i], 33);
                    g.drawRegion(Ninjas.bodySprites[Ninjas.type[i]][Ninjas.bodyFrame[i]], 0, 0, Ninjas.bodySprites[Ninjas.type[i]][Ninjas.bodyFrame[i]].getWidth(), Ninjas.bodySprites[Ninjas.type[i]][Ninjas.bodyFrame[i]].getHeight(), 2, Ninjas.bodyX[i] - NinjaStats.bodyOffsetX[Ninjas.type[i]][Ninjas.bodyFrame[i]], Ninjas.bodyY[i], 33);
                }

                if (Ninjas.healthBarTimer[i] != 0) {
                    g.setColor(13073);
                    g.drawRect(Ninjas.x[i] - 13, Ninjas.y[i] - 40, 26, 4);
                    barWidth = Ninjas.hp[i] * 24 / NinjaStats.maxHp[Ninjas.type[i]];
                    g.setColor(15597568);
                    g.fillRect(Ninjas.x[i] - 12, Ninjas.y[i] - 39, barWidth, 3);
                    g.setColor(4456482);
                    g.fillRect(Ninjas.x[i] - 12 + barWidth, Ninjas.y[i] - 39, 24 - barWidth + 1, 3);
                }
            }
        }

        HeroSprites.drawClassmates(g);
        if (Dog.visible && Dog.x + 15 >= Camera.x && Dog.x - 15 <= Camera.x + Viewport.viewWidth && Dog.y >= Camera.y && Dog.y - 10 <= Camera.y + Viewport.viewHeight) {
            int dogFrame = GameState.tick % 8 > 3 ? 0 : 1;
            if (Dog.vx > 0) {
                g.drawImage(Dog.sprites[dogFrame], Dog.x, Dog.y, 33);
            } else {
                // Quay trái dùng nhầm biến vòng lặp i thay cho dogFrame (giữ như bản gốc).
                g.drawRegion(Dog.sprites[i], 0, 0, Dog.sprites[i].getWidth(), Dog.sprites[i].getHeight(), 2, Dog.x, Dog.y, 33);
            }
        }

        if (Toniku.active) {
            if (Toniku.healthBarVisible) {
                g.setColor(16768477);
                int barX = Camera.x + 50;
                int barY = Camera.y + 15;
                if (!Hud.panelVisible) {
                    barY += 20;
                }

                g.drawRect(barX - 35 - 1, barY - 7, 72, 4);
                int tonikuBarWidth = Toniku.hp * 70 / Toniku.maxHp;
                g.setColor(15597568);
                g.fillRect(barX - 35, barY - 6, tonikuBarWidth, 3);
                g.setColor(4456482);
                g.fillRect(barX - 35 + tonikuBarWidth, barY - 6, 70 - tonikuBarWidth + 1, 3);
            }

            if (Classmates.x[0] + Player.halfWidth >= Camera.x && Classmates.x[0] - Player.halfWidth <= Camera.x + Viewport.viewWidth && Classmates.y[0] >= Camera.y && Classmates.y[0] - Player.height <= Camera.y + Viewport.viewHeight) {
                if (Classmates.facing[0] == 1) {
                    g.drawImage(Classmates.heads[Classmates.skin[0]][Poses.classmatePoses[Classmates.pose[0]][0][0]], Classmates.x[0] + Poses.classmatePoses[Classmates.pose[0]][0][1], Classmates.y[0] - Poses.classmatePoses[Classmates.pose[0]][0][2], 0);
                    g.drawImage(HeroSprites.legs[Poses.classmatePoses[Classmates.pose[0]][1][0]], Classmates.x[0] + Poses.classmatePoses[Classmates.pose[0]][1][1], Classmates.y[0] - Poses.classmatePoses[Classmates.pose[0]][1][2], 0);
                    g.drawImage(HeroSprites.bodies[Poses.classmatePoses[Classmates.pose[0]][2][0]], Classmates.x[0] + Poses.classmatePoses[Classmates.pose[0]][2][1], Classmates.y[0] - Poses.classmatePoses[Classmates.pose[0]][2][2], 0);
                    if (Classmates.slashEffect[0] != -1) {
                        g.drawImage(HeroSprites.slashEffects[Classmates.slashEffect[0]], Classmates.x[0] - Poses.slashOffsetX[Classmates.slashEffect[0]], Classmates.y[0] - Poses.slashOffsetY[Classmates.slashEffect[0]], 0);
                    }
                } else {
                    g.drawRegion(Classmates.heads[Classmates.skin[0]][Poses.classmatePoses[Classmates.pose[0]][0][0]], 0, 0, Classmates.heads[Classmates.skin[0]][Poses.classmatePoses[Classmates.pose[0]][0][0]].getWidth(), Classmates.heads[Classmates.skin[0]][Poses.classmatePoses[Classmates.pose[0]][0][0]].getHeight(), 2, Classmates.x[0] - Poses.classmatePoses[Classmates.pose[0]][0][1], Classmates.y[0] - Poses.classmatePoses[Classmates.pose[0]][0][2], 24);
                    g.drawRegion(HeroSprites.legs[Poses.classmatePoses[Classmates.pose[0]][1][0]], 0, 0, HeroSprites.legs[Poses.classmatePoses[Classmates.pose[0]][1][0]].getWidth(), HeroSprites.legs[Poses.classmatePoses[Classmates.pose[0]][1][0]].getHeight(), 2, Classmates.x[0] - Poses.classmatePoses[Classmates.pose[0]][1][1], Classmates.y[0] - Poses.classmatePoses[Classmates.pose[0]][1][2], 24);
                    g.drawRegion(HeroSprites.bodies[Poses.classmatePoses[Classmates.pose[0]][2][0]], 0, 0, HeroSprites.bodies[Poses.classmatePoses[Classmates.pose[0]][2][0]].getWidth(), HeroSprites.bodies[Poses.classmatePoses[Classmates.pose[0]][2][0]].getHeight(), 2, Classmates.x[0] - Poses.classmatePoses[Classmates.pose[0]][2][1], Classmates.y[0] - Poses.classmatePoses[Classmates.pose[0]][2][2], 24);
                    if (Classmates.slashEffect[0] != -1) {
                        g.drawRegion(HeroSprites.slashEffects[Classmates.slashEffect[0]], 0, 0, HeroSprites.slashEffects[Classmates.slashEffect[0]].getWidth(), HeroSprites.slashEffects[Classmates.slashEffect[0]].getHeight(), 2, Classmates.x[0] + Poses.slashOffsetX[Classmates.slashEffect[0]], Classmates.y[0] - Poses.slashOffsetY[Classmates.slashEffect[0]], 24);
                    }
                }
            }
        }

        Aura.draw(g);

        if (Girl.mapId == GameMap.id) {
            if (Girl.facing == 1) {
                g.drawImage(Girl.heads[Poses.girlPoses[Girl.pose][0][0]], Girl.x + Poses.girlPoses[Girl.pose][0][1], Girl.y - Poses.girlPoses[Girl.pose][0][2], 0);
                g.drawImage(Girl.legs[Poses.girlPoses[Girl.pose][1][0]], Girl.x + Poses.girlPoses[Girl.pose][1][1], Girl.y - Poses.girlPoses[Girl.pose][1][2], 0);
                g.drawImage(Girl.bodies[Poses.girlPoses[Girl.pose][2][0]], Girl.x + Poses.girlPoses[Girl.pose][2][1], Girl.y - Poses.girlPoses[Girl.pose][2][2], 0);
                if (Girl.slashEffect != -1) {
                    g.drawImage(HeroSprites.slashEffects[Girl.slashEffect], Girl.x - Poses.slashOffsetX[Girl.slashEffect], Girl.y - Poses.slashOffsetY[Girl.slashEffect], 0);
                }
            } else {
                g.drawRegion(Girl.heads[Poses.girlPoses[Girl.pose][0][0]], 0, 0, Girl.heads[Poses.girlPoses[Girl.pose][0][0]].getWidth(), Girl.heads[Poses.girlPoses[Girl.pose][0][0]].getHeight(), 2, Girl.x - Poses.girlPoses[Girl.pose][0][1], Girl.y - Poses.girlPoses[Girl.pose][0][2], 24);
                g.drawRegion(Girl.legs[Poses.girlPoses[Girl.pose][1][0]], 0, 0, Girl.legs[Poses.girlPoses[Girl.pose][1][0]].getWidth(), Girl.legs[Poses.girlPoses[Girl.pose][1][0]].getHeight(), 2, Girl.x - Poses.girlPoses[Girl.pose][1][1], Girl.y - Poses.girlPoses[Girl.pose][1][2], 24);
                g.drawRegion(Girl.bodies[Poses.girlPoses[Girl.pose][2][0]], 0, 0, Girl.bodies[Poses.girlPoses[Girl.pose][2][0]].getWidth(), Girl.bodies[Poses.girlPoses[Girl.pose][2][0]].getHeight(), 2, Girl.x - Poses.girlPoses[Girl.pose][2][1], Girl.y - Poses.girlPoses[Girl.pose][2][2], 24);
                if (Girl.slashEffect != -1) {
                    g.drawRegion(HeroSprites.slashEffects[Girl.slashEffect], 0, 0, HeroSprites.slashEffects[Girl.slashEffect].getWidth(), HeroSprites.slashEffects[Girl.slashEffect].getHeight(), 2, Girl.x + Poses.slashOffsetX[Girl.slashEffect], Girl.y - Poses.slashOffsetY[Girl.slashEffect], 24);
                }
            }
        }

        HeroSprites.drawPlayer(g);
        if (QuestItem.state != 0) {
            g.drawImage(QuestItem.icon, QuestItem.x, QuestItem.y, 33);
        }

        Blood.draw(g);
        Dust.draw(g);

        Projectiles.draw(g);

        for(i = 0; i < Darts.count; ++i) {
            if (Darts.state[i] != Darts.STATE_FREE) {
                g.drawImage(Darts.sprites[Darts.frame[i]], Darts.x[i], Darts.y[i], 3);
            }
        }

        ArcProjectiles.draw(g);
        WaterSplash.draw(g);
        TileRenderer.drawForegroundTiles(g);
        FloatingText.draw(g);
        if (GuideMarker.map == GameMap.id) {
            g.drawImage(Hud.arrowIcons[GuideMarker.arrowFrame], GuideMarker.arrowX, GuideMarker.arrowY, 3);
        }

        Hud.drawOverlay(g);
        if (Hud.dirty) {
            Hud.drawPanel(g);
        }

        if (Letterbox.height > 1) {
            Letterbox.draw(g);
        }

        if (Notice.visible) {
            Notice.draw(g, GameState.tick);
        }

        if (Dialogue.visible) {
            Dialogue.draw(g);
        }
    }
}
