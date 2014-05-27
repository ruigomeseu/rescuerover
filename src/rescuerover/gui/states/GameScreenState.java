package rescuerover.gui.states;

import rescuerover.gui.GamePanel;
import rescuerover.gui.TileMap;
import rescuerover.logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameScreenState implements ScreenState, Subject {

    private static GameScreenState instance = null;
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    ScreenState nextState;
    JPanel panel;
    GamePanel gamePanel;


    protected GameScreenState(JFrame frame) {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setVisible(false);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        gamePanel = new GamePanel(new Dimension(Constants.WIDTH, Constants.HEIGHT));

        panel.add(gamePanel, gbc);

        gbc.gridy = 1;
        JButton exitButton = new JButton("Play");

        panel.add(exitButton, gbc);

        TileSet tileSet = new TileSet(32, 25, 18, "/tileset.png");
        // loads tiles -> no blocks
        tileSet.loadTile(TileSet.NO_BLOCKS, TileSet.NO_BLOCKS);

        TileMap tileMap = new TileMap(new Dimension(30,30), "/map");
        // Adds a tile set to load map
        tileMap.setTileSet(tileSet);
        // Set different position to start showing map
        tileMap.setPosition(0, 0);

        tileMap.setTileDimension(new Dimension(Constants.WIDTH / Constants.VISIBLE_TILES, Constants.HEIGHT / Constants.VISIBLE_TILES));
        tileMap.setShowDimension(new Dimension(Constants.VISIBLE_TILES, Constants.VISIBLE_TILES));

        // loads the map from map file
        tileMap.loadMap(5, ",");

        Map map = new Map(tileMap);
        gamePanel.setMap(map);

        gamePanel.repaint();
    }

    public static GameScreenState getInstance(JFrame frame) {
        if(instance == null) {
            instance = new GameScreenState(frame);
        }
        return instance;
    }

    @Override
    public void draw(JFrame frame) {

    }

    @Override
    public void onEnter() {
        panel.setVisible(true);
    }

    @Override
    public void onExit() {
        panel.setVisible(false);
    }

    @Override
    public void register(Observer obj) {
        observers.add(obj);
    }

    @Override
    public void unregister(Observer obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {
        for (Observer ob : observers) {
            System.out.println("Notifying Observers..");
            ob.update(nextState);
        }
    }
}
