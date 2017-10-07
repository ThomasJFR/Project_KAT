package Main;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class StateManager {
    private Map<String, State> stateMap; //Map referenced in main thread initializer
    private State currentState; //Whatever state is currently being shown
 
    public StateManager() {
        stateMap = new HashMap<String, State>();
    }
 
    public void addState(State state) {
        stateMap.put(state.getName().toUpperCase(), state); //Add a new state to the map
//        state.init(); //Prepare the state for use
        if (currentState == null) { //If there is no state, set the newly loaded state as the current state. By default, menu.
//            state.enter();
            currentState = state;
        }
    }
   
    public void setState(String name) {
        State state = stateMap.get(name.toUpperCase()); //Find needed state and store in variable.
        if(state == null){ //Check for error, exit method if the state doesn't exist.
            System.err.println("State <" + name + "> does not exist.");
            return;
        }
//        currentState.exit(); //Exit existing state.
//        state.enter(); //Prepare new state for showing.
        currentState = state; //Switch to the newly prepared state.
    }
    public State getCurrentState(){
    	return currentState;
    }
   
    public State getState(String name) {
    	if (stateMap.get(name.toUpperCase()) == null){
    		System.err.println("State <" + name + "> is not stored.");
    	}
    	return stateMap.get(name.toUpperCase());
        
    }
    
    public void tick(){
        currentState.tick(this); // Tick whatever state is currently active.
    }
   
    public void render(Graphics2D g){
        currentState.render(g); // Render whatever state is currently active.
    }
}