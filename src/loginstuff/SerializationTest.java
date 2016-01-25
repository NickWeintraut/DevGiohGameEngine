package loginstuff;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import base.Card;
import base.Effect;
import damage.Element;
import dgcards.CreatureCard;
import dgcards.EnhancementCard;
import dgeffects.ZombieEffect;

public class SerializationTest {

	public SerializationTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		DeckList eric = new DeckList("GigaDrillBreaker27");
		DeckList nick = new DeckList("Nick");
		DeckList brendan = new DeckList("Brendan");
		DeckList olivia = new DeckList("Olivia");
		
		Card[] presetCards = {
				new CreatureCard("Penguin", 10, 10, Element.NO_ELEMENT, false),
				new CreatureCard("Big Penguin", 15, 15, Element.NO_ELEMENT, false),
				new CreatureCard("King Penguin", 30, 30, Element.NO_ELEMENT, true),
				new CreatureCard("Fire Penguin", 10, 10, Element.FIRE, false),
				new CreatureCard("Wind Penguin", 10, 10, Element.WIND, false),
				new CreatureCard("Earth Penguin", 10, 10, Element.EARTH, false),
				new CreatureCard("Water Penguin", 10, 10, Element.WATER, false),
				new CreatureCard("Inferno Penguin", 40, 10, Element.FIRE, false),
				new CreatureCard("Tornado Penguin", 25, 25, Element.WIND, false),
				new CreatureCard("Golem Penguin", 10, 40, Element.EARTH, false),
				new CreatureCard("Aqueous Penguin", 25, 25, Element.WATER, false),
				new CreatureCard("Assassin Penguin", 30, 5, Element.NO_ELEMENT, false),
				new CreatureCard("Tanky Penguin", 5, 30, Element.NO_ELEMENT, false),
				new CreatureCard("Baby Penguin", 5, 5, Element.NO_ELEMENT, false),
				new CreatureCard("Magic Baby Penguin", 5, 5, Element.NO_ELEMENT, true),
				new CreatureCard("MENACING PENGUIN", 0, 1, Element.NO_ELEMENT, true),
				new CreatureCard("Happy Feet", 20, 15, Element.NO_ELEMENT, false),
				new CreatureCard("Tall Penguin", 10, 25, Element.FIRE, false),
				new CreatureCard("Volcano Penguin", 60, 40, Element.FIRE, true),
				new CreatureCard("Typhoon Penguin", 50, 50, Element.WIND, true),
				new CreatureCard("The Moon Penguin", 90, 500, Element.EARTH, true),
				new CreatureCard("Posiedon Penguin", 50, 50, Element.WATER, true),
				new CreatureCard("Fat Penguin", 10, 23, Element.NO_ELEMENT, false),
				new CreatureCard("Deffective Penguin", -8, 16, Element.WIND, false),
				new CreatureCard("Musical Penguin", 30, 30, Element.NO_ELEMENT, false),
				new CreatureCard("Rockstar Penguin", 45, 15, Element.FIRE, true),
				new CreatureCard("Tommy Wiseau", 100, 120, Element.FIRE, true),
				new CreatureCard("Psyduck", 69, 69, Element.WATER, true),
				makeZombieCard("Hidden Evil Pengin", 100, 100, Element.EARTH, true),
				makeZombieCard("Fazbear Pengin", 100, 100, Element.EARTH, true),
				makeZombieCard("Silent Pengins", 100, 100, Element.EARTH, false),
				makeZombieCard("The hills have Pengin", 100, 100, Element.EARTH, false),
				makeZombieCard("28 Pengins", 100, 100, Element.EARTH, false),
				makeZombieCard("I am Pengin", 100, 100, Element.EARTH, true),
				makeZombieCard("PenginLand", 100, 100, Element.EARTH, false),
				makeZombieCard("The Walking Pengin", 100, 100, Element.EARTH, true),
				makeZombieCard("Dawn of the Pengin", 100, 100, Element.EARTH, true),
				makeZombieCard("Night of the Living Pengin", 100, 100, Element.EARTH, true),
				makeZombieCard("Penginetheus", 100, 100, Element.EARTH, true),
				makeZombieCard("Grave Pengin", 100, 100, Element.EARTH, false),
				makeZombieCard("Jeus Peng", 100, 100, Element.EARTH, true),
				
				new EnhancementCard("Sword", new ArrayList<Effect>(), 15, 0),
				new EnhancementCard("Health Potion", new ArrayList<Effect>(), 0, 15),
				new EnhancementCard("EPIC Health Potion", new ArrayList<Effect>(), 3, 25),
				new EnhancementCard("Cursed Sword", new ArrayList<Effect>(), 30, -20),
				new EnhancementCard("A Hearty Meal at PJ's!", new ArrayList<Effect>(), -10, 45),
				new EnhancementCard("Staff of Wisdom", new ArrayList<Effect>(), 15, 15),
				new EnhancementCard("Boots of Hermes", new ArrayList<Effect>(), 5, 10),
				new EnhancementCard("Witch Doctor's Curse", new ArrayList<Effect>(), -20, -20),
				new EnhancementCard("One Million Blue M&M's", new ArrayList<Effect>(), 0, 100),
				new EnhancementCard("Programmer's Gift", new ArrayList<Effect>(), 1000000000, 1000000000)
				
			};
		
		for(Card card : presetCards)
		{
			eric.addCard(card);
			nick.addCard(card);
			brendan.addCard(card);
			olivia.addCard(card);
		}
		
		eric.serialize("Decks/Eric.ser");
		nick.serialize("Decks/Nick.ser");
		brendan.serialize("Decks/Brendan.ser");
		olivia.serialize("Decks/Olivia.ser");

	}
	
	public static CreatureCard makeZombieCard(String name, int attack, int health, Element element, boolean isRare)
	{
		CreatureCard zombie = new CreatureCard(name, attack, health, element, isRare);
		zombie.addEffect(new ZombieEffect(zombie));
		return zombie;
	}

}
