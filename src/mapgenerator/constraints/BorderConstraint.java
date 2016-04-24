/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgenerator.constraints;

import java.util.ArrayList;
import java.util.List;
import mapgenerator.TilePattern;
import util.Label;

/**
 *
 * @author santi
 */
public class BorderConstraint extends Constraint {
    List<Label> tags = new ArrayList<>();
    List<Label> negativeTags = new ArrayList<>();
    
    public BorderConstraint() {
    }

    public BorderConstraint(Label a_border) {
        tags.add(a_border);
    }
    
    public BorderConstraint(Label a_border, boolean positive) {
        if (positive) tags.add(a_border);
                 else negativeTags.add(a_border);
    }
        
    public void addTag(Label tag) {
        tags.add(tag);
    }
    
    public void addNegativeTag(Label tag) {
        negativeTags.add(tag);
    }
    
    public boolean checkConstraint(TilePattern tp, int px, int py, int width, int height) {
        if (!tags.isEmpty()) {
            if (px==0 && !tp.satisfiesAtLeastOneConstraint(TilePattern.WEST, tags)) return false;
            if (py==0 && !tp.satisfiesAtLeastOneConstraint(TilePattern.NORTH, tags)) return false;
            if (px==width-1 && !tp.satisfiesAtLeastOneConstraint(TilePattern.EAST, tags)) return false;
            if (py==height-1 && !tp.satisfiesAtLeastOneConstraint(TilePattern.SOUTH, tags)) return false;
        }
        if (!negativeTags.isEmpty()) {
            if (px==0 && tp.satisfiesAtLeastOneConstraint(TilePattern.WEST, negativeTags)) return false;
            if (py==0 && tp.satisfiesAtLeastOneConstraint(TilePattern.NORTH, negativeTags)) return false;
            if (px==width-1 && tp.satisfiesAtLeastOneConstraint(TilePattern.EAST, negativeTags)) return false;
            if (py==height-1 && tp.satisfiesAtLeastOneConstraint(TilePattern.SOUTH, negativeTags)) return false;
        }
        return true;
    }
}
