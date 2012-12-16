package hotciv.view.tools;

import java.awt.event.MouseEvent;

import hotciv.framework.*;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

public class ActionTool extends NullTool {
	private Game game;
	private DrawingEditor editor;

	public ActionTool(DrawingEditor editor, Game game) {
		this.editor = editor;
		this.game = game;
	}

	public void mouseDown(MouseEvent e, int x, int y) {
		Position p = GfxConstants.getPositionFromXY(x, y);
		Unit unit = game.getUnitAt(p);
                if(unit==null)return;
		if (e.isShiftDown() && validAction(p)) {
			game.performUnitActionAt(p);
			editor.showStatus(game.getCityAt(p).getOwner()
					+ " did action at " + p);
		} else if (e.isShiftDown() && unit != null
				&& unit.getOwner() != game.getPlayerInTurn()) {
			editor.showStatus("It's an enemys unit");
		} else if (e.isShiftDown() && unit != null
				&& unit.getTypeString() != GameConstants.SETTLER
				|| unit.getTypeString() != GameConstants.ARCHER) {
			editor.showStatus("Unit is legion, no associated actions");
		} else if (e.isShiftDown() && unit != null) {
			editor.showStatus("Not a valid position");
		} else {
			editor.showStatus("");
		}
	}

	private boolean validAction(Position p) {
		Unit u = game.getUnitAt(p);
		Tile t = game.getTileAt(p);
		City c = game.getCityAt(p);
		if (u != null && c == null && t.getTypeString() == GameConstants.PLAINS
				&& u.getOwner() == game.getPlayerInTurn()
				&& u.getTypeString() == GameConstants.SETTLER
				|| u.getTypeString() == GameConstants.ARCHER) {
			return true;
		}
		return false;
	}
}
