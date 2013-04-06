package DragDropListener;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

public class DragDropListener implements IOnSceneTouchListener {

	@Override
	public boolean onSceneTouchEvent(Scene pScene, final TouchEvent pSceneTouchEvent) {	
    	switch (pSceneTouchEvent.getAction()) {
    	case TouchEvent.ACTION_DOWN:
    		return true;
    	case TouchEvent.ACTION_MOVE:
    		// lol jotain paskaa
    		return true;
    	case TouchEvent.ACTION_UP:
    		return false;
    	}
		return false;
	}
}