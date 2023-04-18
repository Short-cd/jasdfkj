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
import javafx.geometry.Point2D;
import javafx.scene.effect.Light;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class TDGameApp extends GameApplication {

    public enum Type {
        PLAYER,ENEMY,BUILDING,RESOURCE
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
    }
    @Override
    protected void initInput(){
        getInput().addAction(new UserAction("Left"){
            @Override
            protected void onAction(){
                player.getComponent(PlayerComponent.class).left();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Right"){
            @Override
            protected void onAction(){
                player.getComponent(PlayerComponent.class).right();
            }

        }, KeyCode.D);

        getInput().addAction(new UserAction("Down"){
            @Override
            protected void onAction(){
                player.getComponent(PlayerComponent.class).down();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("Up"){
            @Override
            protected void onAction(){
                player.getComponent(PlayerComponent.class).up();
            }
        }, KeyCode.W);
    }

    @Override
    protected void initPhysics() {
        /*player enemy - can attack
        *
        * player building - stop movement
        *
        * player resource - stop movement, able to mine
        *
        * enemy building - stop movement, attack
        *
        * */
        onCollision(Type.PLAYER, Type.ENEMY, (player, enemy) -> {
            player.setPosition(checkCollisionLocation(player, enemy));
            play("drop.wav");
        });
    }
    private Point2D checkCollisionLocation(Entity thing1, Entity thing2){
        double xLoc = 0, yLoc = 0;
        if(thing1.getX()<thing2.getRightX()){

            xLoc = thing2.getX()-thing1.getWidth();
            System.out.println(thing1.getX()+" "+thing2.getRightX()+" "+xLoc);
        }
        if(thing1.getRightX()<thing2.getX()){

            xLoc = thing2.getRightX();
            System.out.println(thing1.getRightX()+ " "+thing2.getX()+" "+xLoc);
        }
        if(thing1.getY()<thing2.getBottomY()){
            yLoc = thing2.getBottomY();
            System.out.println(thing1.getY()+" "+thing2.getBottomY()+ " "+xLoc);
        }
        if(thing1.getBottomY()<thing2.getY()){
            yLoc = thing2.getY()-thing1.getHeight();
            System.out.println(thing1.getBottomY()+" "+thing2.getY()+" "+xLoc);
        }
        System.out.println("it checks things");
        Point2D newPoint = new Point2D(xLoc, yLoc);
        System.out.println(newPoint);
        return newPoint;
    }

    @Override
    protected void onUpdate(double tpf) {
        getGameWorld().getEntitiesByType(Type.ENEMY).forEach(droplet -> droplet.translateY(150 * tpf));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
