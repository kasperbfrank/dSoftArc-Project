package hotciv.visual;

import hotciv.common.GameImpl;
import hotciv.framework.Game;
import hotciv.stub.StubGame2;
import hotciv.view.tools.*;
import hotciv.factories.*;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class TestMoving {
	  
	  public static void main(String[] args) {
	    Game game = new GameImpl(new SemiCivFactory());

	    DrawingEditor editor = 
	      new MiniDrawApplication( "Moving units",  
	                               new HotCivFactory4(game) );
	    editor.open();
	    editor.setTool( new CombinedTool(editor, game) );

	    editor.showStatus("Click anywhere to state changes reflected on the GUI");
	                      
	    // Try to set the selection tool instead to see
	    // completely free movement of figures, including the icon

	    // editor.setTool( new SelectionTool(editor) );
	  }
	}
