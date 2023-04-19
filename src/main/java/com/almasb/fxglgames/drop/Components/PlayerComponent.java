package com.almasb.fxglgames.drop.Components;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

public class PlayerComponent extends Component {
    private double speed = 10;
    private Vec2 speedX = new Vec2(10,0);
    private Vec2 negSpeedX = new Vec2(-10,0);
    private Vec2 speedY = new Vec2(0, 10);
    private Vec2 negSpeedY = new Vec2(0, -10);

    public Entity getPlayer(){
        return entity;
    }
//movement stuff
    public void up(){
        entity.translate(negSpeedY);
    }
    public void down(){
        entity.translate(speedY);
    }
    public void left(){
        entity.translate(negSpeedX);
    }
    public void right(){
        entity.translate(speedX);
    }
}
