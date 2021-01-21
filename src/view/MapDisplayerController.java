package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;

public class MapDisplayerController extends Canvas
{
    int[][] mapData;
    double min;
    double max;

    public MapDisplayerController() {
        this.min = Double.MAX_VALUE;
        this.max = 0.0;
    }

    public void setMapData(final int[][] mapData) {
        this.mapData = mapData;
        for (int i = 0; i < mapData.length; ++i) {
            for (int j = 0; j < mapData[i].length; ++j) {
                if (this.min > mapData[i][j]) {
                    this.min = mapData[i][j];
                }
                if (this.max < mapData[i][j]) {
                    this.max = mapData[i][j];
                }
            }
        }
        final double new_max = 255.0;
        final double new_min = 0.0;
        for (int k = 0; k < mapData.length; ++k) {
            for (int l = 0; l < mapData[k].length; ++l) {
                mapData[k][l] = (int)((mapData[k][l] - this.min) / (this.max - this.min) * (new_max - new_min) + new_min);
            }
        }
        this.redraw();
    }

    public void redraw() {
        if (this.mapData != null) {
            final double H = this.getHeight();
            final double W = this.getWidth();
            final double h = H / this.mapData.length;
            final double w = W / this.mapData[0].length;
            final GraphicsContext gc = this.getGraphicsContext2D();
            for (int i = 0; i < this.mapData.length; ++i) {
                for (int j = 0; j < this.mapData[i].length; ++j) {
                    final int tmp = this.mapData[i][j];
                    gc.setFill((Paint)Color.rgb(255 - tmp, 0 + tmp, 0));
                    gc.fillRect(j * w, i * h, w, h);
                }
            }
        }
    }
}
