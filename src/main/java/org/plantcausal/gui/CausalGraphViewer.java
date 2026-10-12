package org.plantcausal.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * JavaFX canvas based DAG viewer.
 */
public class CausalGraphViewer extends Canvas {

    public CausalGraphViewer(double width, double height){
        super(width,height);
    }

    public void render(JavaFxDAGViewerModel model){
        GraphicsContext gc=getGraphicsContext2D();
        gc.clearRect(0,0,getWidth(),getHeight());

        double x=100;
        double y=100;
        for(String node:model.getNodes()){
            gc.fillOval(x,y,30,30);
            gc.fillText(node,x-10,y-10);
            x+=100;
        }
    }
}
