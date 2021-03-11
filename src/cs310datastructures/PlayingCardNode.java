/*
 * CS310 Assignment 8 - Queues and Simulation
 */
package cs310datastructures;

/**
 * A node that can be used to build a singly linked list that contains
 * PlayingCard objects as the data.
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-09-09 - Initial Glorious Version
 */
public class PlayingCardNode
{
    private PlayingCard card;
    private PlayingCardNode nextNode;

    /**
     * The default constructor for a PlayingCardNode. A default node is setup
     * such that the data and next link are both null values.
     */
    public PlayingCardNode()
    {
        card = null;
        nextNode = null;
    }

    /**
     * A constructor that allows setting the card value for a newly created
     * node. The next link will be set to null.
     * 
     * @param card a PlayingCard object reference, which could be null
     */
    public PlayingCardNode(PlayingCard card)
    {
        this.card = card;
        nextNode = null;
    }

    /**
     * Allows access to the PlayingCard object reference stored in a node
     * 
     * @return a reference to a PlayingCard object, which could be null
     */
    public PlayingCard getCard()
    {
        return card;
    }

    /**
     * Allows access to the next link reference stored in a node
     * 
     * @return a reference to a PlayingCardNode object, which could be null
     */
    public PlayingCardNode getNextNode()
    {
        return nextNode;
    }

    /**
     * Allows setting the PlayingCard object reference to a new value.
     * 
     * @param card the PlayingCard object reference, which could be null
     */
    public void setCard(PlayingCard card)
    {
        this.card = card;
    }

    /**
     * Allows setting the next link in the PlayingCardNode
     * 
     * @param nextNode a PlayingCardNode object reference, which could be null
     */
    public void setNextNode(PlayingCardNode nextNode)
    {
        this.nextNode = nextNode;
    }
    
}
