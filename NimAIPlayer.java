/* COMP90041 - ProjC
 * Coded by Ario Prasetiya
 * Student ID: 982918
 * NimAIPlayer.java
 */
public class NimAIPlayer extends NimPlayer{

	public NimAIPlayer() {
		super();
	}

	public NimAIPlayer(String username, String familyname, String givenname, int gameplayed, int wincount) {
		super(username, familyname, givenname, gameplayed, wincount);
	}

	public int stoneremoveinput(int initialstones, int stoneremove, int upperbound) {
		int k = 0;
		while (initialstones >= k*(upperbound+1)+1) {
			k++;
		} k--;
		int removal = (k*(upperbound + 1) + 1);
			stoneremove = initialstones - removal;
		return stoneremove;
	}
	
	public int removestones(int stoneinput, int stoneremove) {
		stoneinput = stoneinput - stoneremove;
		return stoneinput;
	}
	
	
}
