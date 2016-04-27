package com.kenan.libgdxtutorial.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

/**
 * Created by KenanO on 3/23/16.
 */
public class Icicles {
    public static final String TAG = Icicle.class.getName();

    private Viewport mViewport;

    // holds icicles. data structure has feature that allows its elements to be removed while
    //the array is being iterated over.
    private DelayedRemovalArray<Icicle> icicles;

    public Icicles(Viewport viewport){
        mViewport = viewport;
    }

    public void init() {

        //Initialize the DelayedRemovalArray
        icicles = new DelayedRemovalArray<Icicle>();
    }

    public void update(float delta) {

        //spawn a new Icicle if below the spawn rate
        if (MathUtils.random() < delta * Constants.ICICLE_SPAWNS_PER_SECOND) {
            Vector2 start_position = new Vector2(MathUtils.random(Constants.WORLD_SIZE),
                    Constants.WORLD_SIZE);
            Icicle icicle = new Icicle(start_position);
            icicles.add(icicle);
         }

        // Update each icicle
        for(Icicle icl : icicles){
            icl.update(delta, mViewport);
        }

        //begin a removal session
        icicles.begin();

        //check if icicle is below the screen and if so remove it.
        if(icicles.size > 0){
            for(int i = 0; i < icicles.size; i++){
                if(icicles.get(i).getPosition().y < 0 ){
                    icicles.removeIndex(i);
                }
            }
        }

        // End removal session
        icicles.end();
    }

    public void render(ShapeRenderer renderer) {

        //Set ShapeRenderer Color
        renderer.setColor(Constants.ICICLE_COLOR);

        //Render each icicle
        if(icicles != null) {
            for (Icicle icicle : icicles) {
                icicle.render(renderer);
            }
        }
    }
}
