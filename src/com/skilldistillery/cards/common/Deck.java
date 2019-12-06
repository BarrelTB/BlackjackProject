package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	private List<Card> cards;

	public Deck() {
		this.cards = new ArrayList<Card>(52);
		for(Suit s : Suit.values()) {
			for(Rank r : Rank.values()) {
				this.cards.add(new Card(s, r));
			}
		}
	}
	
//	public void createDeck() {
//		this.cards = new ArrayList<Card>(52);
//		for(Suit s : Suit.values()) {
//			for(Rank r : Rank.values()) {
//				this.cards.add(new Card(s, r));
//			}
//		}
//	}
	
	public int checkDeckSize() {
		return cards.size();
	}
	
	public Card dealCard() {
		return cards.remove(0);
	
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	

}
