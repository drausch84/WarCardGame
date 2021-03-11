/*
 * CS310 Assignment 8 - Queues and Simulation
 */
package cs310datastructures;

import java.util.Scanner;

/**
 * This class runs simulations of the card game War and also tests the
 * PlayingCardDeck class.
 *
 * @author Jeffrey LaMarche
 * @version 1.0 2020-09-11 - Initial Template Version
 *
 * @author David Rausch
 * @version 1.0 Oct-11-2020
 */
public class CS310Assignment8
{

    // defines constants for menu item choices, would probably be better as
    //  an enumerated type
    private static final int RUN_WAR_SIM_STATIC = 1;
    private static final int RUN_WAR_SIM_SHUFFLE = 2;
    private static final int TEST_DECK = 3;
    private static final int QUIT_PROGRAM = 4;

    // private constant values
    private static final int LOW_MENU_CHOICE = 1;
    private static final int HIGH_MENU_CHOICE = QUIT_PROGRAM;

    /**
     * The main method for the entire program. This makes everything work. In
     * this case this displays the main menu, gets user input, and runs
     * different parts of the program based on what the user selected.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int userChoice = -1;
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Game of War Simulator 1.0!");

        System.out.println();

        // keep running until the user decides to quit
        while (userChoice != QUIT_PROGRAM)
        {
            displayMenu();

            userChoice = getUserMenuInput(input);

            System.out.println();

            switch (userChoice)
            {
                case RUN_WAR_SIM_STATIC:
                    simulateGameOfWar(false);

                    break;
                case RUN_WAR_SIM_SHUFFLE:
                    simulateGameOfWar(true);

                    break;
                case TEST_DECK:
                    testPlayingCardDeck();

                    break;
                case QUIT_PROGRAM:
                    System.out.print("Thanks for using the Game of War");
                    System.out.println(" Simulator 1.0!");
                    System.exit(0);

                    break;
                default:
                    System.out.println("ERROR: This should not be possible!");
                    System.out.println("Why do humans always break things?");
                    System.out.println("The robot overlords are displeased...");

                    break;
            }

            System.out.println();
        }

    }
    /**
     * The method that will simulate a modified version of the War card game
     * @param shuffleDeck 
     */
    private static void simulateGameOfWar(boolean shuffleDeck)
    {
        //Create a new PlayingCardDeck
        PlayingCardDeck warDeck = new PlayingCardDeck();
        //Create a new PlayingCardQueue for player 1 cards
        PlayingCardQueue playerOneQueue = new PlayingCardQueue();
        //Create a new PlayingCardQueue for player 2 cards
        PlayingCardQueue playerTwoQueue = new PlayingCardQueue();
        //If shuffleDeck is true
        if ( shuffleDeck ) {
            //Shuffle the deck
            warDeck.shuffleDeck();
        }
        //While the deck is not empty
        PlayingCard nextCard;
        while ( ! warDeck.isDeckEmpty() ) {
             //Give player 1 a card and put it in their queue
             nextCard = warDeck.dealCard();
             playerOneQueue.enqueueCard(nextCard);
            //Give player 2 a card and put it in their queue
            nextCard = warDeck.dealCard();
            playerTwoQueue.enqueueCard(nextCard);
        }
        //While neither queue is empty
        int roundNum = 1;
        int playerOneWins = 0;
        int playerTwoWins = 0;
        int ties = 0;
        PlayingCard playerOneCard;
        PlayingCard playerTwoCard;
        while ( ! playerOneQueue.isQueueEmpty() && ! playerTwoQueue.isQueueEmpty() ) {
             //Display the round header with player card counts
            System.out.print( "Round " + roundNum );
            System.out.printf(" : Player 1 (%s)     Player 2 (%s)\n", playerOneQueue.getNumberOfCards(), playerTwoQueue.getNumberOfCards() );
            //Get a card from player 1
            playerOneCard = playerOneQueue.dequeueCard();
            //Get a card from player 2
            playerTwoCard = playerTwoQueue.dequeueCard();
             //Flip both cards to face up
            playerOneCard.flipCard();
            playerTwoCard.flipCard();
            //Display the cards(toString is fine)
            System.out.println("------------------------------------------");
            System.out.println("Player 1 Card           Player 2 Card");
            System.out.printf("%-24s", playerOneCard.toString() );
            System.out.printf("%-24s\n", playerTwoCard.toString());
            System.out.println("------------------------------------------");
            //Flip both cards back to face down
            playerOneCard.flipCard();
            playerTwoCard.flipCard();
             //If the card point values are equal
             if( playerOneCard.getPoints() == playerTwoCard.getPoints() ) {
                 //Display the message: "WAR! Both players loose!"
                 System.out.println("WAR! Both players lose!");
                 ties++;
             //Else player 1 card pointsgreater than player 2 card points
             } else if( playerOneCard.getPoints() > playerTwoCard.getPoints() ){
                 //Display the message: "Player 1 won the round!"
                 System.out.println("Player 1 won the round!");
                 playerOneWins++;
                //Put player 1 card back into player 1 queue
                //Put player 2 card back into player 1 queue
                 playerOneQueue.enqueueCard(playerOneCard);
                 playerOneQueue.enqueueCard(playerTwoCard);
             } else {
                 //Display the message: "Player 2 won the round!"
                 System.out.println("Player 2 won the round!");
                 playerTwoWins++;
                //Put player 1 card back into player 2 queue
                //Put player 2 card back into player 2 queue
                 playerTwoQueue.enqueueCard(playerTwoCard);
                 playerTwoQueue.enqueueCard(playerOneCard);
             }
            //Output a blank line
            //Output a blank line
             System.out.println(" ");
             System.out.println(" ");
             roundNum++;
             
            
             
        }
        if( playerTwoQueue.isQueueEmpty() && playerOneQueue.isQueueEmpty() ) {
            //Display the message: "** Player 1 and Player 2 Tied!! **"
            System.out.println("** Player 1 and Player 2 Tied!!  **");
        } else if( playerTwoQueue.isQueueEmpty()) {
            //If player 1 won the game
                //Display the message: "** Player 1 Won the Game!! **"
            System.out.println("** Player 1 Won the Game!!  **" );
        } else if( playerOneQueue.isQueueEmpty()) {
            //Else if player 2 won the game
                //Display the message: "** Player 2 Won the Game!! **"
            System.out.println("** Player 2 Won the Game!!  **" );
        } else {
            System.out.println("ERROR");
        }
        //Output a blank line
        System.out.println(" ");
        //Display the remaining card statistics for both players
        System.out.println("Remaining Cards: ");
        System.out.println("----------------");
        System.out.printf("Player 1 (%d)\n" , playerOneQueue.getNumberOfCards());
        System.out.printf("Player 2 (%d)\n" , playerTwoQueue.getNumberOfCards() );
        System.out.println("----------------");
        //Output a blank line
        System.out.println(" ");
        Double tempDoub;
        System.out.println("Game Statistics: ");
        System.out.println("--------------------------------------");
        System.out.printf("Total Rounds:        %4d\n" , roundNum );
        tempDoub = (100.0 * ties) / (1.0 * roundNum);
        System.out.printf("Total Wars (Ties):   %4d  ( %6.2f%% )\n", ties, tempDoub );
        tempDoub = (100.0 * playerOneWins) / (1.0 * roundNum);
        System.out.printf("Player 1 Wins:       %4d  ( %6.2f%% )\n", playerOneWins, tempDoub );
        tempDoub = (100.0 * playerTwoWins) / (1.0 * roundNum);
        System.out.printf("Player 1 Losses:     %4d  ( %6.2f%% )\n", playerTwoWins, tempDoub );
        tempDoub = (100.0 * playerTwoWins) / (1.0 * roundNum);
        System.out.printf("Player 2 Wins:       %4d  ( %6.2f%% )\n", playerTwoWins, tempDoub );
        tempDoub = (100.0 * playerOneWins) / (1.0 * roundNum);
        System.out.printf("Player 2 Losses:     %4d  ( %6.2f%% )\n", playerOneWins, tempDoub );
        System.out.println("--------------------------------------");
    }

    /**
     * Displays the image manipulation menu interface to standard output
     */
    private static void displayMenu()
    {
        System.out.println("Game of War Main Menu: ");
        System.out.println("--------------------------------------");
        System.out.println(" " + RUN_WAR_SIM_STATIC
                + ". Simulate a Game of War (Static)");
        System.out.println(" " + RUN_WAR_SIM_SHUFFLE
                + ". Simulate a Game of War (Random)");
        System.out.println(" " + TEST_DECK + ". Test the Playing Card Deck");
        System.out.println(" " + QUIT_PROGRAM + ". Quit Program");
        System.out.println("--------------------------------------");
    }

    /**
     * Obtains the user input for the menu selection. Ensures that the user
     * choice is a valid value based on the current menu options.
     *
     * @param input the scanner object used for input
     *
     * @return an integer corresponding to the user selection
     */
    private static int getUserMenuInput(Scanner input)
    {
        String userChoice = "";
        int intChoice = -1;

        // keep looping until the choice is valid
        while (intChoice < LOW_MENU_CHOICE || intChoice > HIGH_MENU_CHOICE)
        {
            System.out.print("Enter selection (" + LOW_MENU_CHOICE + " - "
                    + HIGH_MENU_CHOICE + "): ");
            userChoice = input.nextLine();

            // if the choice is not an positive integer, ask again
            while (!isPositiveInteger(userChoice))
            {
                System.out.println("ERROR: Invalid choice!");
                System.out.print("Enter selection (" + LOW_MENU_CHOICE + " - "
                        + HIGH_MENU_CHOICE + "): ");
                userChoice = input.nextLine();
            }

            // parse the integer when it is safe to do so
            intChoice = Integer.parseInt(userChoice);

            // display an error if the choice is not valid
            if (intChoice < LOW_MENU_CHOICE || intChoice > HIGH_MENU_CHOICE)
            {
                System.out.println("ERROR: Invalid choice!");
            }
        }

        return intChoice;
    }

    /**
     * Determines whether a given string is a positive integer value
     *
     * @param strNum string containing value to check for being a positive
     * integer
     *
     * @return true is the string contains a positive integer, false otherwise
     */
    private static boolean isPositiveInteger(String strNum)
    {
        final char LOW_INT_VALUE = '0';
        final char HIGH_INT_VALUE = '9';
        final String BAD_STRING = "";

        // if the string is null or empty, it is not valid
        if (strNum == null || strNum.equals(BAD_STRING))
        {
            return false;
        }

        // check each character, making sure it is between 0 and 9 inclusive
        for (int i = 0; i < strNum.length(); i++)
        {
            if (strNum.charAt(i) < LOW_INT_VALUE
                    || strNum.charAt(i) > HIGH_INT_VALUE)
            {
                return false;
            }
        }

        // all tests passed, so string contains a positive integer
        return true;
    }

    /**
     * Provides some minimal visual testing of the PlayingCardDeck class.
     *
     * This is a bit like unit testing.
     */
    private static void testPlayingCardDeck()
    {
        PlayingCardDeck deck = new PlayingCardDeck();

        System.out.println("-- Displaying Deck Face Down --");
        deck.displayDeck(false);
        System.out.println();

        // ensure cards are face up
        System.out.println("-- Displaying Deck Face Up --");
        deck.displayDeck(true);
        System.out.println();

        // ensure that cards return to face down
        System.out.println("-- Displaying Deck Face Down --");
        deck.displayDeck(false);
        System.out.println();

        PlayingCard card = deck.dealCard();

        if (card != null)
        {
            System.out.println("-- Displaying Dealt Card --");
            card.flipCard();
            System.out.println("Card dealt: " + card);
            card.flipCard();
            System.out.println();

            System.out.println("-- Displaying Deck With Front Card Removed --");
            deck.displayDeck(true);
            System.out.println();

            deck.addCardToBottom(card);

            System.out.println("-- Displaying Deck With Card Returned to Bottom --");
            deck.displayDeck(true);
            System.out.println();
        }
        else
        {
            System.out.println("ERROR: Card should not have been null!");
            System.out.println();
        }

        deck.shuffleDeck();

        System.out.println("-- Displaying a Shuffled Deck --");
        deck.displayDeck(true);
        System.out.println();

        // exhausting the deck 
        while (!deck.isDeckEmpty())
        {
            deck.dealCard();
        }

        System.out.println("-- Displaying an Empty Deck --");
        deck.displayDeck(true);
        System.out.println();

        System.out.println("-- Verifying Deck Size is Zero --");
        System.out.println("Deck Size: " + deck.getNumberOfCards());
        System.out.println();

        card = deck.dealCard();

        // make sure null is returned
        System.out.println("-- Card Value Returned From Empty Deck --");
        System.out.println(card);
        System.out.println();

        System.out.println("-- Attempting to Add a Null Card to the Deck --");
        // ensure error message is displayed
        deck.addCardToBottom(card);
        System.out.println();

        // ensure that the null value was not added to the deck
        System.out.println("-- Verifying Deck Size is Still Zero --");
        System.out.println("Deck Size: " + deck.getNumberOfCards());
    }
}
