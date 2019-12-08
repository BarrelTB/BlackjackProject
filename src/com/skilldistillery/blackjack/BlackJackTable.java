package com.skilldistillery.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.common.Deck;

public class BlackJackTable {
	private Dealer dealer;
	private Player player;

	Scanner kb = new Scanner(System.in);

	public static void main(String[] args) {
		BlackJackTable app = new BlackJackTable();

		app.gameSetUp();

	}

	public Dealer createDealer() {
		Dealer dealer = new Dealer();
		return dealer;
	}

	public Deck createDeck() {
		Deck deck = new Deck();
		return deck;
	}

	public Player createPlayer() {
		System.out.println("Please enter your player name: ");
		String playerName = kb.next();
		Player player = new Player(playerName);
		System.out.println("Okay, " + playerName + " let's play!");
		System.out.println();
		return player;
	}

	public void gameSetUp() {
		dealer = createDealer();
		player = createPlayer();

		System.out.println("Would you like to start the game? 'Y' or 'N'");
		String answer = kb.next();
		switch (answer) {
			case "Y":
			case "y":
				playGame();
				break;
			case "N":
			case "n":
			default:
				System.out.println("GoodBye");
				break;
		}

	}

	public void playGame() {

		while (dealer.checkDeckSize() > 3) {
			player.getCards().clear();

			System.out.println();
			System.out.println("Current cards in deck " + dealer.checkDeckSize());

			player.getCards().add(dealer.dealCard());
			dealer.getCards().add(dealer.dealCard());
			player.getCards().add(dealer.dealCard());
			dealer.getCards().add(dealer.dealCard());
			if (dealer.getCards().size() < 2 || player.getCards().size() < 2) {
				System.out.println("Thank you for playing, you have reached the end of the deck!");
			} else {
				dealer.displaySingleCard();
				if (dealer.getHandTotal() >= 21) {
					dealerHit();
					player.getCards().clear();
					dealer.getCards().clear();
					playGame();
				}
				System.out.println();
				player.displayCards();
				System.out.println("Players hand value is: " + player.getHandTotal());

				int win = playerHit();
				if (win == 1) {
					player.getCards().clear();
					dealer.getCards().clear();
					playGame();
				} else if (win == 0) {
					if (dealerHit() == 1) {
						whoWon();
						player.getCards().clear();
						dealer.getCards().clear();
						playGame();
					} else {
						player.getCards().clear();
						dealer.getCards().clear();
						playGame();
					}
				}

			}
		}
	}

	public int playerHit() {
		boolean hit = true;
		int win = 0;
		if (player.getHandTotal() == 21) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Player " + player.getName() + " Wins!");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			hit = false;
			win = 1;
		} else if (player.getHandTotal() > 21) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Player " + player.getName() + " BUST!");
			System.out.println("Dealer wins with " + dealer.getHandTotal());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			hit = false;
			win = 1;
		}
		while (hit) {
			if (dealer.checkDeckSize() >= 1) {
				System.out.println("Would you like another card? 'Yes' or 'No'");
				String playerChoice = kb.next();

				int playerTotal = 0;
				if (playerChoice.equalsIgnoreCase("Yes")) {
					player.getCards().add(dealer.dealCard());
					player.displayCards();
					playerTotal = player.getHandTotal();
					System.out.println("Player current total is: " + playerTotal);
					if (playerTotal > 21) {
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("PLAYER BUST!");
						System.out.println("Dealer wins with " + dealer.getHandTotal());
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						win = 1;
						hit = false;
					} else if (playerTotal == 21) {
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("Player Wins with 21!");
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						win = 1;
						hit = false;
					}
				} else {
					win = 0;
					hit = false;
				}
			} else {
				win = 0;
				hit = false;
			}

		}
		return win;

	}

	public int dealerHit() {
		int dealerTotal = 0;
		int win = 0;
		dealerTotal = dealer.getHandTotal();
		dealer.displayCards();
		if (dealerTotal == 21) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Dealer wins with 21!");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		while (dealerTotal < 17) {
			System.out.println("Dealer current value is: " + dealerTotal);
			if (dealer.checkDeckSize() >= 1) {
				dealer.getCards().add(dealer.dealCard());
			}
			dealer.displayCards();
			dealerTotal = dealer.getHandTotal();
		}
		win = 1;
		if (dealerTotal > 21) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("DEALER BUST!");
			System.out.println("Player " + player.getName() + " wins!");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			win = 0;
		}
		return win;

	}

	public void whoWon() {
		int playerTotal = player.getHandTotal();
		int dealerTotal = dealer.getHandTotal();
		if ((21 - playerTotal) < (21 - dealerTotal)) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Player " + player.getName() + " total is : " + playerTotal);
			System.out.println("Dealer total is : " + dealerTotal);
			System.out.println("Player " + player.getName() + " won this round.");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		} else if ((21 - playerTotal) > (21 - dealerTotal)) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Player " + player.getName() + " total is : " + playerTotal);
			System.out.println("Dealer total is : " + dealerTotal);
			System.out.println("Player " + player.getName() + " lost to the dealer.");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		} else {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("The dealer and player " + player.getName() + " tied.");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}
}
