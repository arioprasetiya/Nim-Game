/* COMP90041 - ProjC
 * Coded by Ario Prasetiya
 * Student ID: 982918
 * NimHumanPlayer.java
 */
public class NimHumanPlayer extends NimPlayer{

	public NimHumanPlayer() {
		super();
	}
	
	public NimHumanPlayer(String username, String familyname, String givenname, int gameplayed, int wincount) {
		super(username, familyname, givenname, gameplayed, wincount);
	}
	
	
	public int stoneremoveinput(int initialstones, int stoneremove, int upperbound) {
		
		stoneremove = Nimsys.key.nextInt();
		
		return stoneremove;
	}
	
	public int removestones(int stoneinput, int stoneremove) {
		stoneinput = stoneinput - stoneremove;
		return stoneinput;
	
	}
	
}
