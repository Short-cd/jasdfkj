package com.almasb.fxglgames.drop;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxglgames.drop.Components.PlayerComponent;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;


public class gameFactory implements EntityFactory{

    @Spawns("Player")
    public Entity spawnBucket(SpawnData data){//spawns player
        var texture = texture("PlayerNew.png").outline(Color.web("blue", 0.5), 5);
        return entityBuilder()
                .type(TDGameApp.Type.PLAYER)
                .at(100, 100)
                .viewWithBBox("player.png")
                .collidable()
                .with(new PlayerComponent())
                .build();
    }

    @Spawns("enemy")
    public Entity spawnDrops(SpawnData data ){//asdf
        return entityBuilder(data)
                .type(TDGameApp.Type.ENEMY)
                .at(FXGLMath.random(0, getAppWidth()-64), 0)
                .viewWithBBox("droplet.png")
                .collidable()
                .build();
    }

    @Spawns("building")
    public Entity spawnBuildings(SpawnData data){
        return entityBuilder(data)
                .type(TDGameApp.Type.BUILDING)
                .at(FXGLMath.random(0, getAppWidth()-64), FXGLMath.random(0, getAppHeight()-64))
                .viewWithBBox("bucket.png")
                .collidable()
                .build();
    }

}
