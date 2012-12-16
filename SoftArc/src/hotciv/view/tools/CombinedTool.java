package hotciv.view.tools;

import java.awt.event.MouseEvent;

import hotciv.framework.*;
import minidraw.framework.DrawingEditor;
import minidraw.standard.*;
import hotciv.view.*;

public class CombinedTool extends SelectionTool {
	private MoveUnitTool moveUnit;
	private EndOfTurnTool endOfTurn;
	private FocusTool focus;
	private ActionTool action;
	
	public CombinedTool(DrawingEditor editor) {
		super(editor);
	}
	
	public CombinedTool(DrawingEditor editor, Game game) {
		super(editor);
		moveUnit = new MoveUnitTool(editor, game);
		endOfTurn = new EndOfTurnTool(editor, game);
		focus = new FocusTool(editor, game);
		action = new ActionTool(editor, game);
	}
	
	public void mouseDown(MouseEvent e, int x, int y) {
	Position p = GfxConstants.getPositionFromXY(x, y);
        if(p.getRow() >=0 && p.getRow() < 16 && p.getColumn()>=0 && p.getColumn() < 16){
            moveUnit.mouseDown(e, x, y);
            action.mouseDown(e, x, y);
        }else{ endOfTurn.mouseDown(e, x, y);}
	}
	
	public void mouseDrag(MouseEvent e, int x, int y) {
		moveUnit.mouseDrag(e, x, y);
	}
	
	public void mouseUp(MouseEvent e, int x, int y) {
            Position p = GfxConstants.getPositionFromXY(x, y);
            if(p.getRow() >=0 && p.getRow() < 16 && p.getColumn()>=0 && p.getColumn() < 16){
            moveUnit.mouseUp(e, x, y);
            focus.mouseUp(e, x, y);
            }
	}
}
