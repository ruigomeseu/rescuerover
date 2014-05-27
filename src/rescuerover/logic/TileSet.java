package rescuerover.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TileSet {
    public static final int NO_BLOCKS = -1;

    /**
     * Tile filename
     */
    private String tile;
    /**
     * Each image in tile size
     */
    private int tileSize;
    /**
     * Tile number of columns
     */
    private int numCols;
    /**
     * Tile number of rows
     */
    private int numRows;
    /**
     * Tile image
     */
    private BufferedImage tileImage;
    /**
     * Tiles Array
     */
    private Tile[] tiles;

    /**
     * @param tileSize Each image in tile size
     * @param numCols  Number of columns
     * @param numRows  Number of rows
     */
    public TileSet(int tileSize, int numCols, int numRows) {
        this.setTileSize(tileSize);
        this.setNumCols(numCols);
        this.setNumRows(numRows);
        this.tiles = new Tile[this.numCols * this.numRows];
    }

    /**
     * @param tileSize Each image in tile size
     * @param numCols  Number of columns
     * @param numRows  Number of rows
     * @param tile     Tile file name
     */
    public TileSet(int tileSize, int numCols, int numRows, String tile) {
        this.setTileSize(tileSize);
        this.setNumCols(numCols);
        this.setNumRows(numRows);
        this.tile = tile;
        this.tiles = new Tile[this.numCols * this.numRows];
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public BufferedImage getTileImage() {
        return tileImage;
    }

    public void setTileImage(BufferedImage tileImage) {
        this.tileImage = tileImage;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public BufferedImage getImageAtPosition(int position) {
        return tiles[position].getTile();
    }

    /**
     * Loads all tiles from tile file
     *
     * @param blockRowPosition row where block tiles start
     * @param blockColPosition column where block tiles start
     */
    public void loadTile(int blockRowPosition, int blockColPosition) {
        try {
            tileImage = ImageIO.read(
                    getClass().getResourceAsStream(tile)
            );
            int counter = 0;

            BufferedImage subImage;
            int type = Tile.NORMAL;
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    subImage = tileImage.getSubimage(j * tileSize, i * tileSize, tileSize, tileSize);
                    tiles[counter] = new Tile(subImage, tileSize);
                    if (blockRowPosition != -1 && blockColPosition != -1) {
                        if (i > blockRowPosition)
                            type = Tile.UNPASSABLE;
                        if (i == blockRowPosition && j >= blockColPosition)
                            type = Tile.UNPASSABLE;
                    }

                    tiles[counter].setType(type);
                    counter++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        int counter = 0;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                g.drawImage(tiles[counter].getTile(), j * tileSize, i * tileSize, null);
                counter++;
            }
        }
    }
}
