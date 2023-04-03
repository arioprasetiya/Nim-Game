/* COMP90041 - ProjC
 * Coded by Ario Prasetiya
 * Student ID: 982918
 * NimPlayer.java
 */
import java.util.Comparator;

public abstract class NimPlayer {
	
	private String username;
	private String familyname;
	private String givenname;
	private int gameplayed;
	private int wincount;
	private double ratio;
	
	//Constructor
	public NimPlayer() {
		username = null;
		familyname = null;
		givenname = null;
		gameplayed = 0;
		wincount = 0;
	}
	
	public NimPlayer(String username, String familyname, String givenname, int gameplayed, int wincount) {
		this.username = username;
		this.familyname = familyname;
		this.givenname = givenname;
		this.gameplayed = gameplayed;
		this.wincount = wincount;
	}
	
	public String toString() {
		return (username + "," + givenname + "," + familyname + "," + gameplayed + " games," + wincount + " wins");
	}
	
	public abstract int removestones(int stoneinput, int stoneremove);
	
	public abstract int stoneremoveinput(int initialstones, int stoneinput, int upperbound);

	
	// Accessors
	public String getusername() {
		return username;
	}
	
	public String getfamilyname() {
		return familyname;
	}
	
	public String getgivenname() {
		return givenname;
	}
	
	public int getgameplayed() {
		return gameplayed;
	}
	
	public int getwincount() {
		return wincount;
	}
	
	public double getratio() {
		if (wincount == 0 && gameplayed ==0) {
			ratio = 0;
		} else
		ratio = (((double)wincount)/((double)gameplayed)); 
		return ratio;
	}
	
	// Mutators
	public void setplayer(String username, String familyname, String givenname) {
		this.username = username;
		this.familyname = familyname;
		this.givenname = givenname;
	}
	
	public void setusername(String username) {
		this.username = username;
	}
	
	public void setfamilyname(String familyname) {
		this.familyname = familyname;
	}
	
	public void setgivenname(String givenname) {
		this.givenname = givenname;
	}
	
	public void setgameplayed() {
		this.gameplayed++;
	}
	
	public void setwincount() {
		this.wincount++;
	}
	
	public void setgameplayed(int i) {
		this.gameplayed = i;
	}
	
	public void setwincount(int i) {
		this.wincount = i;
	}
	
	public static Comparator<NimPlayer> usernamecompare = new Comparator<NimPlayer>() {
		public int compare(NimPlayer u1, NimPlayer u2) {
			String username1 = u1.getusername();
			String username2 = u2.getusername();
			if (username1 == null && username2 == null) 
				return 0;
			if (username1 == null) 
				return 1;
			if (username2 == null) 
				return -1;
		return username1.compareTo(username2);
		}
	};
}
	
