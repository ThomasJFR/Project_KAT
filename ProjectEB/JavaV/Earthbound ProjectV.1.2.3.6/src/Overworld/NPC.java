package Overworld;

public class NPC {
	private String NPCName;
	@SuppressWarnings("unused")
	private int NPCSPD, NPCLCK, NPCEnemyVar, NPCWidth, NPCHeight;
	private String NPCFoward, NPCBackward, NPCLeft, NPCRight;
	public static NPC[] list=new NPC[50];
	static NPC[] NPCTypes = new NPC[100];
		
public static void setPresets()
{	
//Hooligan
	NPCTypes[0] = new NPC("Hooligan", 1, 3, 1, 25, 50, "src/Enemies/Hooligan Overworld Sprite.png","src/Enemies/Hooligan Overworld Sprite.png","src/Enemies/Hooligan Overworld Sprite.png","src/Enemies/Hooligan Overworld Sprite.png");
}

public static void generateNPC(int indexNo, int NPCIndex)
{

	//First number is for what position is to be set, second is for what NPC is to fill said position
	list[indexNo] = NPCTypes[NPCIndex];
}

//NPC Value setter
public NPC(String name, int EnemyVar, int SPD, int LCK, int Width, int Height, String Foward, String Backward, String Left, String Right){
	NPCName=name;
	NPCSPD=SPD;
	NPCLCK=LCK;
	NPCFoward=Foward;
	NPCBackward=Backward;
	NPCLeft=Left;
	NPCRight=Right;
	NPCEnemyVar=EnemyVar;
	NPCWidth=Width;
	NPCHeight=Height;
	
}

//Getters
public int getNPCSPD(){
	return NPCSPD;
}
public int getNPCLCK(){
	return NPCLCK;
}
public String getNPCName(){
	return NPCName;
}
public String getNPCFoward(){
	return NPCFoward;
}
public String getNPCBackward(){
	return NPCBackward;
}
public String getNPCLeft(){
	return NPCLeft;
}
public String getNPCRight(){
	return NPCRight;
}

}
