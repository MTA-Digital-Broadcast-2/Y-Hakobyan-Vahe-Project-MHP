/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.*;
import org.havi.ui.*;

/**
 *
 * @author student
 */
public class ImageComponent extends HComponent {
    private Image bmap;
    private MediaTracker mtrack;
    
    public ImageComponent(String bitmapnaam, int x, int y) {
        bmap = this.getToolkit().getImage(bitmapnaam);
        mtrack = new MediaTracker(this);
        mtrack.addImage(bmap, 0);
        try {
            mtrack.waitForAll();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        this.setBounds(x, y, bmap.getWidth(null), bmap.getHeight(null));     
    }
    
    public void paint(Graphics g) {
        g.drawImage(bmap, 0, 0, null);
    }    
}
