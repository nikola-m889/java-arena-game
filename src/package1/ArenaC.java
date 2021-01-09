package package1;
import java.util.Random;
import java.util.Scanner;

public class ArenaC {

		String rank=null;
		String weaponChoice=null;
		String[] opponents = {"Warrior","Mage","Rogue","Assassin","Archer"};
		int maxEnemyHealth = 0;
		int enemyAttackDamage = 0;
		int health = 100;
		int attackDamage = 0;
		int gold=0;
		int numHealthPotions = 7;
		int healthPotionHealAmount = 30;
		int healthPotionDropChance = 50;
		int level=0;
		int enemyDefeated=0;
		int mageDefeated=0;
		int rogueDefeated=0;
		int warriorDefeated=0;
		int assassinDefeated=0;
		int archerDefeated=0;
		int weaponState=100;
		
		
		boolean dagger=false;
		boolean sword=false;
		boolean axe=false;
		boolean running = true;
		
		Scanner in=new Scanner(System.in);
		Random rand = new Random();
		
		void battle() {
		System.out.println("Welcome to the Arena, Novice"+", your level is "+level);
		
		System.out.println("Select your difficulty level: ");
		System.out.println("1. Easy");
		System.out.println("2. Normal");
		System.out.println("3. Hard");
		String dif=in.nextLine();
		while(!dif.equals("1")&&!dif.equals("2")&&!dif.equals("3")) {
			System.out.println("You must pick one of the three options");
			dif=in.nextLine();
		}
		if(dif.equals("1")) {
			maxEnemyHealth = 65;
			enemyAttackDamage = 15;
		}
		else if(dif.equals("2")) {
			maxEnemyHealth = 75;
			enemyAttackDamage = 25;
		}
		else if(dif.equals("3")) {
			maxEnemyHealth = 100;
			enemyAttackDamage = 45;
		}
		
		
		System.out.println("Choose your weapon: ");
		System.out.println("1. Dagger");
		System.out.println("2. Sword");
		System.out.println("3. Axe");
		String weapon=in.nextLine();
		if(weapon.equals("1")) {
			dagger=true;
			weaponChoice="dagger";
			attackDamage=40;
			System.out.println("You are armed with a dagger");
			System.out.println("Attack damage: "+attackDamage);
		}
		else if(weapon.equals("2")) {
			sword=true;
			weaponChoice="sword";
			attackDamage=50;
			System.out.println("You are armed with a sword");
			System.out.println("Attack damage: "+attackDamage);
		}
		else if(weapon.equals("3")) {
			axe=true;
			weaponChoice="axe";
			attackDamage=60;
			System.out.println("You are armed with an axe");
			System.out.println("Attack damage: "+attackDamage);
		}
		
	
		START:
		while(running) {
			System.out.println();
			if(level>0) {System.out.println("You are going to battle again "+rank+", your current level is "+level);}
			int enemyHealth = rand.nextInt(maxEnemyHealth);
			String enemy = opponents[rand.nextInt(opponents.length)];
			if(enemyDefeated==0) {
				System.out.println("\t# "+enemy+" appeared! #\n");
			}else {
				switch(enemy) {
				case "Mage":
					if(mageDefeated>0) {
					System.out.println("\t# "+enemy+" from previous battle was revived by magical powers!");
					enemyAttackDamage+=10;
					}
					System.out.println("\t# "+enemy+" appeared! #\n");
					break;
				case "Rogue":
					if(rogueDefeated>0) {
					System.out.println("\t# "+enemy+" from previous battle has risen again, more dangerous!");
					enemyAttackDamage+=10;
					}
					System.out.println("\t# "+enemy+" appeared! #\n");
					break;
				case "Warrior":
					if(warriorDefeated>0) {
					System.out.println("\t# "+enemy+" this time more prepared is ready for you!");
					enemyAttackDamage+=10;
					}
					System.out.println("\t# "+enemy+" appeared! #\n");
					break;
				case "Assassin":
					if(assassinDefeated>0) {
					System.out.println("\t# Another "+enemy+" was sent after you, this time prepared!");
					enemyAttackDamage+=10;
					}
					System.out.println("\t# "+enemy+" appeared! #\n");
					break;	
				case "Archer":
					if(archerDefeated>0) {
					System.out.println("\t# "+enemy+" from previous battle recovered and is ready to fight again!");
					enemyAttackDamage+=10;
					}
					System.out.println("\t# "+enemy+" appeared! #\n");
					break;	
			
				}
			}
			
			while(enemyHealth>0) { 
				System.out.println("\tYour HP: "+health);
				System.out.println("\t" +enemy+"'s HP: "+enemyHealth);
				System.out.println("\n\tWhat would you like to do?");
				System.out.println("\t1. Attack");
				System.out.println("\t2. Heal");
				System.out.println("\t3. Run from enemy!");
				System.out.println("\t4. Check the state of your weapon");
				
				String input  = in.nextLine();
				if(input.equals("1")) {
					int damageDealt=rand.nextInt(attackDamage);
					int damageTaken=rand.nextInt(enemyAttackDamage);
					
					enemyHealth -= damageDealt;
					health -=damageTaken;
					
					System.out.println("\t> You strike the "+enemy+" for "+damageDealt+" damage.");
					System.out.println("\t> You receive "+damageTaken+" in retaliation");
					
					if (health < 1) {
						System.out.println("\t> You are unable to fight any longer");
						break;
					}
					
				}else if(input.equals("2")) {
					if(numHealthPotions > 0) {
						if(health==100) {
							System.out.println("You have full health");
						}else {
						health+=healthPotionHealAmount;
						numHealthPotions--;
						if(health>100) {
							health=100;
						}
						System.out.println("\t> You drink health potion, healing youself for "+healthPotionHealAmount+"."+
						"\n\t> You now have "+health+" HP."+
						"\n\t> You have "+numHealthPotions+" health potions left.\n");
						}
					}else {
						System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!\n");
					}
					
				}else if(input.equals("3")) {
					System.out.println("\tYou run away from the "+enemy+"!");
					continue START;
					
				}else if(input.equals("4")) {
					System.out.println("Your "+weaponChoice +" is at "+weaponState+" %");
					System.out.println("Attack damage: "+attackDamage);
					if(weaponState<100 && gold>=100) {
						System.out.println("Would you like to fix your weapon?");
						System.out.println("1. Yes(pay 100 gold)");
						System.out.println("2. No");
						String wfix=in.nextLine();
						while(!wfix.equals("1")&&!wfix.equals("2")) {
							System.out.println("You must pick one of the two options");
							wfix=in.nextLine();
						}
						if(wfix.equals("1")) {
							gold=gold-100;
							weaponState=100;
							System.out.println("You have fixed your weapon!");
							if(dagger) {attackDamage=40;}
							else if(sword) {attackDamage=50;}
							else if(axe) {attackDamage=60;}
							
						}else if(wfix.equals("2")) {
							continue;
						}
					}
					continue;
				}
				else{
					System.out.println("\tInvalid command!");
				}
				
			}
			
			if(health<1) {
				break;
			}
			
			System.out.println("-------------------------------------------");
			System.out.println(" # "+enemy+" was defeated! #");
			System.out.println(" # You have "+health+" HP left #");
			if(rand.nextInt(100) < healthPotionDropChance) {
				numHealthPotions++;
				System.out.println(" You found a health potion! ");
				System.out.println(" # You have "+ numHealthPotions+" health potions in your inventory. #");
			}
			
			switch(enemy) {
			case "Mage":
				mageDefeated++;
				enemyDefeated++;
				System.out.println("Mage was defeated for now");
				break;
			case "Rogue":
				rogueDefeated++;
				enemyDefeated++;
				System.out.println("Rogue evaded final attack, and may return again");
				break;
			case "Warrior":
				warriorDefeated++;
				enemyDefeated++;
				System.out.println("There is a always stronger warrior");
				break;
			case "Assassin":
				assassinDefeated++;
				enemyDefeated++;
				System.out.println("Another assassin will be sent");
				break;	
			case "Archer":
				archerDefeated++;
				enemyDefeated++;
				System.out.println("Archer may return");
				break;		
			}
			
			level++;
			System.out.println("You have gained level "+level);
			if(level==0) {
				rank="Novice";
			}
			else if(level==1) {
				rank="Adventurer";
			}
			else if(level>1 && level<5) {
				rank="Knight";
			}
			else if(level>=5) {
				rank="Master";
			}
			
			gold=gold+50;
			System.out.println("You received 50 gold");
			
			weaponState=weaponState-10;
			
			if(weaponState==70) {
				if(dagger) {
					attackDamage=(int) (attackDamage*0.70);
				}
				else if(sword) {
					attackDamage=(int) (attackDamage*0.70);
				}
				else if(axe) {
					attackDamage=(int) (attackDamage*0.70);
				}
			}
			else if(weaponState==50){
				if(dagger) {
					attackDamage=40;
					attackDamage=(int) (attackDamage*0.50);
				}
				else if(sword) {
					attackDamage=50;
					attackDamage=(int) (attackDamage*0.50);
				}
				else if(axe) {
					attackDamage=60;
					attackDamage=(int) (attackDamage*0.50);
				}
			}
			else if(weaponState==25){
				if(dagger) {
					attackDamage=40;
					attackDamage=(int) (attackDamage*0.25);
				}
				else if(sword) {
					attackDamage=50;
					attackDamage=(int) (attackDamage*0.25);
				}
				else if(axe) {
					attackDamage=60;
					attackDamage=(int) (attackDamage*0.25);
				}
			}
			else if(weaponState==0) {
				attackDamage=0;
			}
			

			
			System.out.println();
			System.out.println("What is your next decision?");
			System.out.println("1. Continue fightning");
			System.out.println("2. Exit the arena");
			
			String input=in.nextLine();
			
			while(!input.equals("1") && !input.equals("2")) {
				System.out.println("Invalid command!");
				input=in.nextLine();
			}
			
			if(input.equals("1")) {
				System.out.println("You continue to fight!");
			}
			else if(input.equals("2")) {
				System.out.println("You are leaving the combat arena!");
				break;
			}
			
		}
		System.out.println();
		System.out.println("Your highest level was level "+level+", reaching the rank of "+rank);
		System.out.println();
		
	}
	}


