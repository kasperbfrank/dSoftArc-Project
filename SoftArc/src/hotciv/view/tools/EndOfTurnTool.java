package hotciv.view.tools;

import hotciv.framework.*;
import hotciv.view.*;

import minidraw.framework.*;
import minidraw.standard.*;

import java.awt.event.*;
import java.awt.*;


/** A tool to 'end the turn' of a game iff the top shield is clicked. */
public class EndOfTurnTool extends NullTool {
  private Game game;
  private DrawingEditor editor;

  public EndOfTurnTool(DrawingEditor editor, Game game) {
    this.editor = editor;
    this.game = game;
  }
  public void mouseDown(MouseEvent e, int x, int y) {
    Drawing drawing = editor.drawing();
    Figure f = drawing.findFigure(x,y);
    if ( f != null &&
         f instanceof ImageFigure ) {
      if ( f.displayBox().
           contains( new Point( GfxConstants.TURN_SHIELD_X,
                                GfxConstants.TURN_SHIELD_Y) ) ) {
        game.endOfTurn();
        editor.showStatus("Next player: "+game.getPlayerInTurn() );
      }
    }
  }
}
