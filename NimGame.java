/* COMP90041 - ProjC
 * Coded by Ario Prasetiya
 * Student ID: 982918
 * NimGame.java
 */
public class NimGame {

	private int initialstones;
	private int upperbound;
	
	public void gamestart(int initialstones, int upperbound, NimPlayer player1, NimPlayer player2) {

		String firstname1 = player1.getgivenname();
		String firstname2 = player2.getgivenname();
		String lastname1 = player1.getfamilyname();
		String lastname2 = player2.getfamilyname();
		
		player1.setgameplayed();
		player2.setgameplayed();
		
		System.out.println();
		System.out.println("Initial stone count: " + initialstones);
		System.out.println("Maximum stone removal: " + upperbound);
		System.out.println("Player 1: " + firstname1 + " " + lastname1);
		System.out.println("Player 2: " + firstname2 + " " + lastname2);
		
		this.initialstones = initialstones;
		this.upperbound = upperbound;
		int turn = 1;
		do {

			if (turn % 2 == 1) {
				stoneprint(this.initialstones);
				System.out.println(firstname1 + "'s turn - remove how many?");

				int stoneremov =0;
				stoneremov = player1.stoneremoveinput(this.initialstones, stoneremov, this.upperbound);
						//Nimsys.key.nextInt();

				if (stoneremov <= this.upperbound && this.initialstones > this.upperbound && stoneremov > 0) {
					
					this.initialstones = player1.removestones(this.initialstones, stoneremov);
					turn++;
					
				} else if (stoneremov > this.upperbound && this.initialstones > this.upperbound) {
					System.out.println("\nInvalid move. You must remove between 1 & " + this.upperbound + " stones.");
				} else if (stoneremov <= this.upperbound && stoneremov <= this.initialstones && this.initialstones <= this.upperbound && stoneremov > 0) {
					
					this.initialstones = player1.removestones(this.initialstones, stoneremov);
							//this.initialstones - stoneremov;
					turn++;
					
				} else if ((stoneremov > this.upperbound || stoneremov > this.initialstones) && this.initialstones <= this.upperbound) {
				
					System.out.println("\nInvalid move. You must remove between 1 & " + this.initialstones + " stones.");
				} else if (stoneremov == 0 && this.initialstones > this.upperbound) {
					System.out.println("\nInvalid move. You must remove between 1 and " + this.upperbound + " stones.");
				}  else if (stoneremov == 0 && this.initialstones <= this.upperbound) {
					System.out.println("\nInvalid move. You must remove between 1 and " + this.initialstones + " stones.");
				}
			}

			else if (turn % 2 == 0) {
				
				stoneprint(this.initialstones);
				System.out.println(firstname2 +"'s turn - remove how many?");

				int stoneremov = 0;
				stoneremov = player2.stoneremoveinput(this.initialstones, stoneremov, this.upperbound);
						//Nimsys.key.nextInt();
				
				if (stoneremov <= this.upperbound && this.initialstones > this.upperbound && stoneremov > 0) {
					
					this.initialstones =  player2.removestones(this.initialstones, stoneremov);
							//this.initialstones - stoneremov;
					turn++;
					
				} else if (stoneremov > this.upperbound && this.initialstones > this.upperbound) {
					System.out.println("\nInvalid move. You must remove between 1 and " + this.upperbound + " stones.");
				} else if (stoneremov <= this.upperbound && stoneremov <= this.initialstones && this.initialstones <= this.upperbound && stoneremov >0) {
					
					this.initialstones = player2.removestones(this.initialstones, stoneremov);
							//this.initialstones - stoneremov;
					turn++;
				} else if ((stoneremov > this.upperbound || stoneremov > this.initialstones) && this.initialstones <= this.upperbound) {
					
					System.out.println("\nInvalid move. You must remove between 1 and " + this.initialstones + " stones.");
					
				} else if (stoneremov == 0 && this.initialstones > this.upperbound) {
					System.out.println("\nInvalid move. You must remove between 1 and " + this.upperbound + " stones.");
				}  else if (stoneremov == 0 && this.initialstones <= this.upperbound) {
					System.out.println("\nInvalid move. You must remove between 1 and " + this.initialstones + " stones.");
				}
				
			}

		} while (this.initialstones > 0);

		if (turn % 2 == 1) {
			System.out.println("\nGame Over");
			System.out.println(firstname1 +" "+lastname1 +" wins!");
			player1.setwincount();
		} else if (turn % 2 == 0) {
			System.out.println("\nGame Over");
			System.out.println(firstname2 +" "+lastname2 +" wins!");
			player2.setwincount();
		}
		
		Nimsys.key.nextLine();
	}
		
	// this method print stones
		public void stoneprint (int stoneinput) {
			System.out.println();
			System.out.print(stoneinput + " stones left:");
			for (int counter=1; counter<=stoneinput; counter++) {
			System.out.print(" *");}
			System.out.println();	
		}
	
}
