/*
 * CS310 Assignment 8 - Queues and Simulation
 */
package cs310datastructures;

/**
 * A queue structure that holds PlayingCards. The available operations are:
 * <br><br>
 * - Checking if the queue is empty<br>
 * - Getting the number of items in the queue (non-standard)<br>
 * - Enqueue a card (adding it to the queue)<br>
 * - Dequeue a card (removing it from the queue)<br>
 * <br>
 *
 * @author Jeffrey LaMarche
 * @version 0.9 2020-09-11 - Template Version
 * 
 * @author David Rausch 
 * @version 1.0 Oct-11-2020
 */
public class PlayingCardQueue
{

    /*
    The head of the singly linked list
    */
    private PlayingCardNode queueFront;
    
    /*
    The tail of the singly linked list
    */
    private PlayingCardNode queueBack;
    
    /*
    The number of nodes stored in the singly linked list
    */
    private int numberOfCards;

    /*
    The constructor which creates an empty queue
    */
    public PlayingCardQueue()
    {
        queueFront = null;
        queueBack = null;
        numberOfCards = 0;
    }
    
    /**
     * A method that returns the number of cards contained in the queue
     * @return an integer representing number of cards in the queue
     */
    public int getNumberOfCards()
    {
        return numberOfCards;
    }
    
    /**
     * A method that returns whether or not the queue is empty
     * @return the queue contents
     */
    public boolean isQueueEmpty()
    {
        return ( numberOfCards == 0 );
    }

    /**
     * A method that removes the first item in the queue and returns it
     * @return first item in queue or null if queue is empty
     */
    public PlayingCard dequeueCard()
    {
        if( queueFront == null ) {
            return null;
        }
        PlayingCard rVal = queueFront.getCard();
        if( queueFront.getNextNode() == null ) {
            queueFront = null;
            queueBack = null;
            numberOfCards = 0;
        } else {
            queueFront = queueFront.getNextNode();
            numberOfCards--;
        }
        return rVal;
    }

    /**
     * A method that adds a new non-null PlayingCard object to back of the queue
     * @param card
     * @return true if card was added, false otherwise
     */
    public boolean enqueueCard(PlayingCard card)
    {
        if ( card == null ) {
            return false;
        }
        PlayingCardNode newNode = new PlayingCardNode(card);
        if( queueBack == null ) {
            queueFront = newNode;
            queueBack = newNode;
            numberOfCards = 1;
        } else {
            queueBack.setNextNode(newNode);
            queueBack = newNode;
            numberOfCards++;
        }
        return true;
    }
}
