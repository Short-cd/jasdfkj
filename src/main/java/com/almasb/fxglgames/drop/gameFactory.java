package com.almasb.fxglgames.drop;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxglgames.drop.Components.PlayerComponent;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;


public class gameFactory implements EntityFactory{

    @Spawns("Player")
    public Entity spawnBucket(SpawnData data){
        return entityBuilder()
                .at(100, 100)
                .viewWithBBox("player.png")
                .collidable()
                .with(new PlayerComponent())
                .build();//ASDF
    }

    @Spawns("Drops")
    public Entity spawnDrops(SpawnData data ){
        return entityBuilder(data)
                .type(TDGameApp.Type.DROPLET)
                .at(FXGLMath.random(0, getAppWidth()-64), 0)
                .viewWithBBox("droplet.png")
                .collidable()
                .build();
    }

}
