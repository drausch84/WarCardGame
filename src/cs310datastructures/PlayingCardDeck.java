/*
 * CS310 Assignment 8 - Queues and Simulation
 */
package cs310datastructures;

import java.util.Collections;
import java.util.LinkedList;

/**
 * This class represents a deck of playing cards. These are the 
 * operations available in the limited deck:<br><br>
 * - Building a new deck in a consistent pattern<br>
 * - Getting the number of cards remaining in the deck<br>
 * - Shuffling the cards in the deck<br>
 * - Dealing a card from the top of the deck<br>
 * - Adding a card to the bottom of the deck<br>
 * - Displaying the contents of the deck<br>
 * 
 * @author Jeffrey LaMarche
 * @version 0.9 2020-09-11 - Template Version
 * 
 * @author David Rausch
 * @version 1.0 Oct-11-2020
 */
public final class PlayingCardDeck
{
    /*
    The rank of an ace card
    */
    private int ACE_RANK = 1;
    
    /*
    The point value for Ace to be higher than a King
    */
    private int ACE_SCORE = 15;
    
    /*
    The number of cards in a suit
    */
    private int CARDS_IN_SUIT = 13;
    
    /*
    The maximum number of cards in a new deck
    */
    private int CARDS_IN_DECK = 52;
    
    /*
    A linked list that holds the cards. 
    */
    private LinkedList<PlayingCard> deck;

    /*
    Default Constructor
    */
    public PlayingCardDeck()
    {
        PlayingCard tempCard;
        deck = new LinkedList<>();
        int suit;
        int rank;
        int points;
        
        //Loop for the total number of cards in the deck
        for(int idx = 0; idx < CARDS_IN_DECK; idx++) {
            //Determine the suit, rank, and points
            suit = (idx / CARDS_IN_SUIT) + 1;
            rank = (idx % CARDS_IN_SUIT) + 1;
            points = (rank == ACE_RANK) ? ACE_SCORE : rank;
           
            //Make a new PlayingCard with the suit, rank, and points 
            tempCard = new PlayingCard(suit, rank, points);
            
            //Add the new card by calling the addCardToBottom method
            this.addCardToBottom( tempCard );
        }
    } 

    /**
     * Gets the number of cards in the deck
     * 
     * @return the size of the LinkedList object
     */
    public int getNumberOfCards()
    {
        return deck.size();
    }
    
    /**
     * Method to determine whether the deck is empty
     * 
     * @return true if empty, false otherwise
     */
    public boolean isDeckEmpty()
    {
        return( deck == null || deck.isEmpty() );
    }
    
    /**
     * A method to shuffle the deck of cards using the Collections shuffle method
     */
    public void shuffleDeck()
    {
        Collections.shuffle(this.deck);
    }

    /**
     * A method that will deal the first card from the deck
     * @return top card from deck or null if deck is empty
     */
    public PlayingCard dealCard()
    {
        return ( this.isDeckEmpty() ) ? null : deck.removeFirst();
    }

    /**
     * Method to add a card to the bottom of the deck
     * @param card
     */
    public void addCardToBottom(PlayingCard card)
    {   
        if(card == null) {
            System.out.println("ERROR: Card Value is null!");
        } else {
            deck.addLast(card);
        }
    }

    /**
     * A method to display the contents of the deck
     * @param displayFaceUp 
     */
    public void displayDeck(boolean displayFaceUp)
    {
        System.out.println("Displaying Deck Contents: ");
        if( this.isDeckEmpty() ) {
            System.out.println("The deck is empty");
            return;
        }
        for ( PlayingCard tempCard : deck ) {
            if( displayFaceUp ) {
                if( tempCard.isFaceUp() ) {
                    System.out.println( tempCard.toString() );
                } else {
                    tempCard.setFaceUp(true);
                    System.out.println(tempCard.toString());
                    tempCard.setFaceUp(false);
                } 
            } else {
                System.out.println( tempCard.toString() );
               
            } 
        }
    }
}
