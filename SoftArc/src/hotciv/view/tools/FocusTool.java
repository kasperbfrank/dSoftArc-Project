package hotciv.view.tools;

import java.awt.event.MouseEvent;

import hotciv.framework.*;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

public class FocusTool extends NullTool {
	private Game game;
	private DrawingEditor editor;

	public FocusTool(DrawingEditor editor, Game game) {
		this.editor = editor;
		this.game = game;
	}

	public void mouseUp(MouseEvent e, int x, int y) {
		Position p = GfxConstants.getPositionFromXY(x, y);
                Unit u = game.getUnitAt(p);
		City c = game.getCityAt(p);
		game.setTileFocus(p);
		if (u != null && c == null) {
			editor.showStatus("Inspect " + u.getOwner() + " "
					+ u.getTypeString() + " at " + p);
		} else if (c != null && u == null) {
			editor.showStatus("Inspect " + c.getOwner() 
					+ " city at " + p);
		} else if (u != null && c != null) {
			editor.showStatus("Inspect " + c.getOwner() + " "
					+ u.getTypeString() + " and city at " + p);
		} else {
			editor.showStatus("");
		}

	}
}
