/* COMP90041 - ProjC
 * Coded by Ario Prasetiya
 * Student ID: 982918
 * Nimsys.java
 */
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Nimsys {

	private static final int MAXPLAYER = 100;
	public static Scanner key = new Scanner(System.in);
	private static int index = 0;

	public static void main(String[] args) {
		NimPlayer[] players = new NimPlayer[MAXPLAYER];
		Nimsys start = new Nimsys();

		System.out.println("Welcome to Nim");
		
		File file = new File("players.dat");

		try {
		BufferedReader input2 = new BufferedReader(new FileReader(file));
		String st;
		while ((st=input2.readLine()) != null) {
		StringTokenizer split2 = new StringTokenizer(st,", ");
		
			  	String typeplayer = split2.nextToken();
			  	if (typeplayer.equalsIgnoreCase("human")) {
				 String username = split2.nextToken();
				 String givenname = split2.nextToken();
				 String familyname = split2.nextToken();
				 String gameplayed = split2.nextToken();
				 String wincount = split2.nextToken();
				 
				 int gameplayedx = Integer.parseInt(gameplayed);
				 int wincountx = Integer.parseInt(wincount);
				 
				 players[index] = new NimHumanPlayer(username,familyname,givenname,gameplayedx,wincountx);
				 index++;
				 
			  	} else if (typeplayer.equalsIgnoreCase("comp")) {
			  		 String username = split2.nextToken();
					 String givenname = split2.nextToken();
					 String familyname = split2.nextToken();
					 String gameplayed = split2.nextToken();
					 String wincount = split2.nextToken();
					 
					 int gameplayedx = Integer.parseInt(gameplayed);
					 int wincountx = Integer.parseInt(wincount);
					 
					 players[index] = new NimAIPlayer(username,familyname,givenname,gameplayedx,wincountx);
					 index++;
					 
			  	}
		  } 
		} 
		  catch (IOException e) {
			  
		  }

	
		while (true) {
			
			System.out.println();
			System.out.print("$");
			String input = key.nextLine();

			try {
			StringTokenizer split = new StringTokenizer(input, ", ");

			String command = split.nextToken();
			//System.out.println(split.countTokens());
			
			if (command.equalsIgnoreCase("addplayer")) {
				try {
				if (split.countTokens() == 3) {
					String username = split.nextToken();
					String familyname = split.nextToken();
					String givenname = split.nextToken();
					
					start.addplayer(username, familyname, givenname, players);
					}
				else {
					throw new Exception("Incorrect number of arguments supplied to command.");
					}
				} 
				catch (Exception e) {
					String message = e.getMessage();
					System.out.println(message);
				}
			}
			
			else if (command.equalsIgnoreCase("addaiplayer")) {
				try {
				if (split.countTokens() == 3) {
					String username = split.nextToken();
					String familyname = split.nextToken();
					String givenname = split.nextToken();
					
					start.addaiplayer(username, familyname, givenname, players);
					}
				else {
					throw new Exception("Incorrect number of arguments supplied to command.");
					}
				} 
				catch (Exception e) {
					String message = e.getMessage();
					System.out.println(message);
				}
			}
			
			else if (command.equalsIgnoreCase("removeplayer")) {
				
				if (split.hasMoreTokens()) {
					String username = split.nextToken();

					start.removeplayer(username, players);
					
				} else {
					System.out.println("Are you sure you want to remove all players? (y/n)");
					String verify = key.nextLine();
					if(verify.equalsIgnoreCase("y")){

						for (int i = 0; i < index; i++) {
							players[i] = null;
							}
						index = 0;
					} else Nimsys.key.nextLine();
				}
			}

			else if (command.equalsIgnoreCase("editplayer")) {
				try {
				if (split.countTokens() == 3) {
				String username = split.nextToken();
				String familyname = split.nextToken();
				String givenname = split.nextToken();

				start.editplayer(username, familyname, givenname, players);
				}
				else {
					throw new Exception("Incorrect number of arguments supplied to command.");
					}
				} 
				catch (Exception e) {
					String message = e.getMessage();
					System.out.println(message);
				}
			}

			else if (command.equalsIgnoreCase("resetstats")) {
				if (split.hasMoreTokens()) {
					String username = split.nextToken();
					start.resetstats(username, players);
				} else {
					System.out.println("Are you sure you want to reset all player statistics? (y/n)");
					String verify = key.nextLine();
					if(verify.equalsIgnoreCase("y")){

						for (int i = 0; i < index; i++) {
							players[i].setwincount(0);
							players[i].setgameplayed(0);
							}
					} else Nimsys.key.nextLine();
				}
			}

			else if (command.equalsIgnoreCase("displayplayer")) {
				if (split.hasMoreTokens()) {
					String username = split.nextToken();
					start.displayplayersingle(username, players);
				}

				else {
					if (index > 0) {

						for (int i = 0; i < index; i++) {
							Arrays.sort(players, Comparator.nullsLast(NimPlayer.usernamecompare));
							if (players[i] != null) {
								System.out.println(players[i]);
							}
						}
					}
				}	
			}
			
			else if (command.equalsIgnoreCase("rankings")) {
				if (split.hasMoreTokens()) {
					String order = split.nextToken();
					start.rankings(order, players);

				}
				else {
					if (index > 0) {

						for (int i = 0; i < index; i++) {
							Arrays.sort(players, Comparator.nullsLast(rankcompareasc).thenComparing(NimPlayer.usernamecompare));
							DecimalFormat percent = new DecimalFormat("0%");
							if (players[i] != null) {
								if( players[i].getratio() >= 0 && players[i].getratio() < 0.1) {
									System.out.print(percent.format(players[i].getratio()));
									System.out.printf("   | %02d games | %s %s",players[i].getgameplayed(),
											players[i].getgivenname(),players[i].getfamilyname());
									System.out.println();
								} else if (players[i].getratio() > 0.09 && players[i].getratio() < 1) {
									System.out.print(percent.format(players[i].getratio()));
									System.out.printf("  | %02d games | %s %s",players[i].getgameplayed(),
											players[i].getgivenname(),players[i].getfamilyname());
									System.out.println();
								} else if (players[i].getratio() == 1){ 
									System.out.print(percent.format(players[i].getratio()));
									System.out.printf(" | %02d games | %s %s",players[i].getgameplayed(),
											players[i].getgivenname(),players[i].getfamilyname());
									System.out.println();
								}
									
							}
						}
					}
				}	
			}
			
			else if (command.equalsIgnoreCase("startgame")) {
				try {
				
				if (split.countTokens() == 4) {	
				String initialstones = split.nextToken();
				String upperbound = split.nextToken();
				String player1 = split.nextToken();
				String player2 = split.nextToken();
				
				start.startgame(initialstones, upperbound, player1, player2, players);
				} 
				else {
					throw new Exception("Incorrect number of arguments supplied to command.");
					}
				}
				
				catch (Exception e) {
					String message = e.getMessage();
					System.out.println(message);
				}
			}

				else if (command.equalsIgnoreCase("exit")) {
					PrintWriter outputstream = new PrintWriter(new FileOutputStream("players.dat"));
					for (int i = 0; i < index; i++) {
						if (players[i] instanceof NimHumanPlayer) {
							outputstream.println("human," + players[i].getusername() + "," + players[i].getgivenname() +","+ players[i].getfamilyname() 
									+ "," + players[i].getgameplayed() + "," + players[i].getwincount());
						} else if (players[i] instanceof NimAIPlayer) {
							outputstream.println("comp," + players[i].getusername() + "," + players[i].getgivenname() +","+ players[i].getfamilyname() 
									+ "," + players[i].getgameplayed() + "," + players[i].getwincount());
						}
					}
					outputstream.close();

					System.out.println();
					System.exit(0);

				}
			
			else {
				throw new Exception("'" + command + "' is not a valid command.");
				}
			}
		catch (Exception e) {
			String message = e.getMessage();
			System.out.println(message);
		}
			

		}
	}

	private void addplayer(String username, String familyname, String givenname, NimPlayer[] players) {
		
		int check = checkplayer(username, players);
		
		if (check < 0) {
		
			players[index] = new NimHumanPlayer(username, familyname, givenname,0,0);

			index++;
		} else {
			System.out.println("The player already exists.");
		}
	}
	
	
	private void addaiplayer(String username, String familyname, String givenname, NimPlayer[] players) {
		
		int check = checkplayer(username, players);
		
		if (check < 0) {
		
			players[index] = new NimAIPlayer(username, familyname, givenname,0,0);

			index++;
		} else {
			System.out.println("The player already exists.");
		}
	}
	
	

	private void removeplayer(String username, NimPlayer[] players) {
		int check = checkplayer(username, players);
		
		if(check >= 0) {
		for (int i = 0; i < index; i++) {
			if (players[i].getusername().equals(username)) {
				players[i] =  null;
				index--;
				break;
				}
			}
		} else
				System.out.println("The player does not exist.");
			}

	private void editplayer(String username, String familyname, String givenname, NimPlayer[] players) {
		int check = checkplayer(username, players);
		
		if(check >= 0) {
		int i;
		for (i = 0; i < index; i++) {
			if (players[i].getusername().equals(username)) {
				players[i].setfamilyname(familyname);
				players[i].setgivenname(givenname);
				break;
				}
			}
		}
		else
			System.out.println("The player does not exist.");
		}

	private void resetstats(String username, NimPlayer[] players) {
		int check = checkplayer(username, players);

		if (check >= 0) {
			int i;
			for (i = 0; i < index; i++) {
				if (username.equals(players[i].getusername())) {
					players[i].setgameplayed(0);
					players[i].setgameplayed(0);
					break;
				}
			}
		} else
			System.out.println("The player does not exist.");
	}

	private void displayplayersingle(String username, NimPlayer[] players) {
		int check = checkplayer(username,players);
		if(check >= 0) {
		int i;
		for (i = 0; i < index; i++) {
			if (username.equals(players[i].getusername())) {
				System.out.println(players[i]);
				break;
				}
			}
		}
		else
			System.out.println("The player does not exist.");
		
	}

	private void rankings(String order, NimPlayer[] players) {
		if (index > 0) {
		if (order.equalsIgnoreCase("asc")) {
			
				for (int i = 0; i < index; i++) {
					Arrays.sort(players, Comparator.nullsLast(rankcompareasc));
					DecimalFormat percent = new DecimalFormat("0%");
					if (players[i] != null) {
						if( players[i].getratio() >= 0 && players[i].getratio() < 0.1) {
							System.out.print(percent.format(players[i].getratio()));
							System.out.printf("   | %02d games | %s %s",players[i].getgameplayed(),
									players[i].getgivenname(),players[i].getfamilyname());
							System.out.println();
						} else if (players[i].getratio() > 0.09 && players[i].getratio() < 1) {
							System.out.print(percent.format(players[i].getratio()));
							System.out.printf("  | %02d games | %s %s",players[i].getgameplayed(),
									players[i].getgivenname(),players[i].getfamilyname());
							System.out.println();
						} else if (players[i].getratio() == 1){ 
							System.out.print(percent.format(players[i].getratio()));
							System.out.printf(" | %02d games | %s %s",players[i].getgameplayed(),
									players[i].getgivenname(),players[i].getfamilyname());
							System.out.println();
						}
							
					}
				}
			
		} else if (order.equalsIgnoreCase("desc")) {

				for (int i = 0; i < index; i++) {
					Arrays.sort(players, Comparator.nullsLast(rankcomparedesc));
					DecimalFormat percent = new DecimalFormat("0%");
					if (players[i] != null) {
						if( players[i].getratio() >= 0 && players[i].getratio() < 0.1) {
							System.out.print(percent.format(players[i].getratio()));
							System.out.printf("   | %02d games | %s %s",players[i].getgameplayed(),
									players[i].getgivenname(),players[i].getfamilyname());
							System.out.println();
						} else if (players[i].getratio() > 0.09 && players[i].getratio() < 1) {
							System.out.print(percent.format(players[i].getratio()));
							System.out.printf("  | %02d games | %s %s",players[i].getgameplayed(),
									players[i].getgivenname(),players[i].getfamilyname());
							System.out.println();
						} else if (players[i].getratio() == 1){ 
							System.out.print(percent.format(players[i].getratio()));
							System.out.printf(" | %02d games | %s %s",players[i].getgameplayed(),
									players[i].getgivenname(),players[i].getfamilyname());
							System.out.println();
						}
							
					}
				}
			}
		} else System.out.println("No User Data");

	}

	private void startgame(String initialstonesx, String upperboundx, String username1, String username2,
			NimPlayer[] players) {

		int initialstones = Integer.parseInt(initialstonesx);
		int upperbound = Integer.parseInt(upperboundx);
		int check1 = checkplayer(username1, players);
		int check2 = checkplayer(username2, players);

		if (check1 < 0 || check2 < 0) {
			System.out.println("One of the players doesn't exist.");
		} else {
			int position1 = 0;
			for (int i = 0; i < index; i++) {
				if (username1.equals(players[i].getusername())) {
					position1 = i;
				}

			}
			int position2 = 0;
			for (int i = 0; i < index; i++) {
				if (username2.equals(players[i].getusername())) {
					position2 = i;
				}
			}

			NimGame newgame = new NimGame();
			newgame.gamestart(initialstones, upperbound, players[position1], players[position2]);
		
		}
	}

	
	public int checkplayer(String username, NimPlayer[] players) {

		for (int i = 0; i < index; i++) {
			if (username.equals(players[i].getusername()))
				return i;
		}
		return -1;
	}
		
	
	public static Comparator<NimPlayer> rankcompareasc = new Comparator<NimPlayer>() {
		public int compare(NimPlayer u1, NimPlayer u2) {
			double ratio1 = u1.getratio();
			double ratio2 = u2.getratio();
			if (ratio1 == ratio2)
				return 0;
			if (ratio1 > ratio2)
				return -1;
			if (ratio1 < ratio2)
				return 1;
			return Double.compare(ratio1,ratio2);
		}
	};
	
	public static Comparator<NimPlayer> rankcomparedesc = new Comparator<NimPlayer>() {
		public int compare(NimPlayer u1, NimPlayer u2) {
			double ratio1 = u1.getratio();
			double ratio2 = u2.getratio();
			if (ratio1 == ratio2)
				return 0;
			if (ratio1 > ratio2)
				return 1;
			if (ratio1 < ratio2)
				return -1;
			return Double.compare(ratio1,ratio2);
		}
	};
	
}

