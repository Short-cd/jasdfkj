/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package com.almasb.fxglgames.drop;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxglgames.drop.Components.PlayerComponent;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class TDGameApp extends GameApplication {

    public enum Type {
        DROPLET, BUCKET
    }

    private Entity player;

    private PlayerComponent playerComponent;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Drop");
        settings.setVersion("1.0");
        settings.setWidth(1200);
        settings.setHeight(800);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new gameFactory());
        player = spawn("Player");

        run(() -> spawn("Drops"), Duration.seconds(1));

        playerComponent = player.getComponent(PlayerComponent.class);
//        player.rota
//        loopBGM("bgm.mp3");ASDF
    }
    @Override
    protected void initInput(){
        getInput().addAction(new UserAction("Left"){
            @Override
            protected void onAction(){
                player.getComponent(PlayerComponent.class).left();
            }
            @Override
            protected void onActionEnd(){
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Right"){
            @Override
            protected void onAction(){
                player.getComponent(PlayerComponent.class).right();
            }

            @Override
            protected void onActionEnd(){
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.D);

        getInput().addAction(new UserAction("Down"){
            @Override
            protected void onAction(){
                player.getComponent(PlayerComponent.class).down();
            }
            @Override
            protected void onActionEnd(){
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("Up"){
            @Override
            protected void onAction(){
                player.getComponent(PlayerComponent.class).up();
            }
            @Override
            protected void onActionEnd(){
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.W);
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(Type.BUCKET, Type.DROPLET, (bucket, droplet) -> {

            droplet.removeFromWorld();

            play("drop.wav");
        });
    }

    @Override
    protected void onUpdate(double tpf) {
        getGameWorld().getEntitiesByType(Type.DROPLET).forEach(droplet -> droplet.translateY(150 * tpf));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
