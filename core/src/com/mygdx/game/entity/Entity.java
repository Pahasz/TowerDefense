package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Team;

/**
 * Created by Paha on 2/3/2015.
 */
public class Entity {
    protected Vector2 position;
    protected float rotation;
    protected boolean destroyed = false, initiated = false;
    protected Team teamOwner;
    protected String name;
    protected Vector2 health;

    protected Sprite sprite;
    protected World world;
    protected Body body;
    protected Fixture fixture;

    public Entity(Vector2 position, float rotation, Team teamOwner, String name, Vector2 health){
        this.position = new Vector2(position);
        this.rotation = rotation;
        this.teamOwner = teamOwner;
        this.name = name;
        this.health = new Vector2(health);

        this.teamOwner.addEntity(this.name, this);
    }

    public void init(){

    }

    public void update(float delta){
        if(health.y <= 0)
            this.setDestroyed();
    }

    public void render(float delta, SpriteBatch batch){

    }

    public void damage(float amount){
        this.health.y -= amount;
    }

    public Team getTeamOwner(){
        return this.teamOwner;
    }

    public boolean sameTeam(Entity entity){
        return this.getTeamOwner().getId() == entity.getTeamOwner().getId();
    }

    public String getName(){
        return this.name;
    }

    public boolean isInitiated(){
        return this.initiated;
    }

    public void setDestroyed(){
        this.destroyed = true;
    }

    public boolean isDestroyed(){
        return this.destroyed || (this.health.y <= 0);
    }

    public void destroy(){
        this.world.destroyBody(this.body);
        this.sprite = null;
        this.world = null;
        this.body = null;
        this.fixture = null;
        this.teamOwner = null;
        this.destroyed = true;
    }

}
