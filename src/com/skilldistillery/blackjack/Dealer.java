package com.skilldistillery.blackjack;

import java.util.List;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class Dealer {

	private BlackJackHand hand;
	private Deck deck;

	public Dealer() {
		this.hand = new BlackJackHand();
		this.deck = new Deck();

	}

	public List<Card> getCards() {
		return hand.getHand();
	}

	@Override
	public String toString() {
		return "Dealer hand value " + hand.getHandValue();
	}

	public int getHandTotal() {
		return hand.getHandValue();
	}

	public void displayCards() {
		System.out.println("Dealer has: ");
		for (Card card : hand.getHand()) {
			System.out.println(card);
		}
		System.out.println();

	}

	public void displaySingleCard() {
		System.out.println("Dealer has: " + hand.getHand().get(0));
	}

	public Card dealCard() {
		return this.deck.dealCard();
	}

	public int checkDeckSize() {
		return this.deck.checkDeckSize();
	}

}
