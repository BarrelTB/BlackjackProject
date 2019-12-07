package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
		
	private List<Card> hand;
	
	public abstract int getHandValue();

	public List<Card> getHand() {
		return hand;
	}
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	public void clear() {
			hand.clear();
		
	}

	@Override
	public String toString() {
		return "Hand [hand=" + hand + "]";
	}
	

}
