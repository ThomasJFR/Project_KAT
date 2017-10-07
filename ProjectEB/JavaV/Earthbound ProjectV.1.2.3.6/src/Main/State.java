package Main;

import java.awt.Graphics2D;

public interface State {
	public void tick(StateManager statemanager);
	public void render(Graphics2D g);
	public String getName();
}
