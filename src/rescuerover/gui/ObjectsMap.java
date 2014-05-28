package rescuerover.gui;

import rescuerover.logic.Constants;
import rescuerover.logic.MapObject;
import rescuerover.logic.Position;

import java.awt.*;
import java.util.ArrayList;

public class ObjectsMap {
    private Dimension tileDimension;
    private ArrayList<MapObject> objects;

    public ObjectsMap(ArrayList<MapObject> objects, Dimension tileDimension) {
        this.objects = objects;
        this.tileDimension = tileDimension;
    }

    public void draw(Graphics g, Position heroPosition) {
        for(MapObject obj : objects) {
            if( obj.getX() <= Math.abs(heroPosition.getX() + Constants.VISIBLE_TILES / 2)
                    ||  obj.getX() >= Math.abs(heroPosition.getX() - Constants.VISIBLE_TILES / 2)) {

                System.out.println("Obj X: " + obj.getX());
                System.out.println("Obj offestX: " + obj.getOffsetX());

                System.out.println("Obj Y: " + obj.getY());
                System.out.println("Obj offestY: " + obj.getOffsetY());

                g.drawImage(
                        obj.getSprite().getFrame(),
                        (int) Math.round((Constants.VISIBLE_TILES/2) * (int) tileDimension.getWidth()),
                        (Constants.VISIBLE_TILES/2) * (int) tileDimension.getHeight(),
                        (int) tileDimension.getWidth(),
                        (int) tileDimension.getHeight(),
                        null);

                if(obj.isMoving())
                    obj.step();
            }
        }
    }
}
