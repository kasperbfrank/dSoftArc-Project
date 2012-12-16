package hotciv.view.tools;

import java.awt.event.MouseEvent;

import hotciv.framework.*;
import hotciv.view.GfxConstants;
import minidraw.framework.*;
import minidraw.standard.NullTool;

public class MoveUnitTool extends NullTool {
	private Game game;
	private DrawingEditor editor;
	private Position from;
	private Unit unitToMove;

	public MoveUnitTool(DrawingEditor editor, Game game) {
		this.editor = editor;
		this.game = game;
	}

	public void mouseDown(MouseEvent e, int x, int y) {
		from = GfxConstants.getPositionFromXY(x, y);
                unitToMove = game.getUnitAt(from);
		if (unitToMove != null) {
			if (unitToMove.getOwner() != game.getPlayerInTurn()) {
				editor.showStatus("Can't move the enemy unit");
			} else {
				editor.showStatus("Move the unit");
			}
		}
	}

	public void mouseDrag(MouseEvent e, int x, int y) {
		Position to = GfxConstants.getPositionFromXY(x, y);
		if (to.getColumn() < 16 && to.getColumn() >= 0 && to.getRow() < 16
				&& to.getRow() >= 0) {
			Unit tu = game.getUnitAt(to);
			Tile t = game.getTileAt(to);
			if (tu != null && unitToMove != null) {
				if (unitToMove.getOwner() != tu.getOwner()) {
					editor.showStatus("Attack " + tu.getOwner() + " "
							+ tu.getTypeString() + " at " + to);
				} else {
					editor.showStatus("Can't move there");
				}
			} else if (unitToMove != null
					&& t.getTypeString() == GameConstants.MOUNTAINS) {
				editor.showStatus("Can't move on a mountain");
			} else if (unitToMove != null
					&& t.getTypeString() == GameConstants.OCEANS) {
				editor.showStatus("Can't move on a ocean");
			} else if (unitToMove != null) {
				editor.showStatus("Move unit from " + from + " to " + to);
			} else {
				editor.showStatus("");
			}
		}
	}

	public void mouseUp(MouseEvent e, int x, int y) {
		Position to = GfxConstants.getPositionFromXY(x, y);
		if (unitToMove != null) {
			game.moveUnit(from, to);
		}
	}
}
