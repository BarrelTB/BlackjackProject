package com.skilldistillery.blackjack;

import java.util.List;

import com.skilldistillery.cards.common.Card;

public class Player{

	private BlackJackHand hand;
	private String name;

	public Player(String name) {
		this.hand = new BlackJackHand();
		this.name = name;
	}

	public List<Card> getCards() {
		return hand.getHand();
	}

	@Override
	public String toString() {
		return name + " hand value " + hand.getHandValue();
	}
	
	public int getHandTotal() {
		return hand.getHandValue();
	}
	
	public void displayCards() {
		System.out.println("Player has: ");
		for (Card card : hand.getHand()) {
			System.out.println(card);
		}
		System.out.println();
		
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
