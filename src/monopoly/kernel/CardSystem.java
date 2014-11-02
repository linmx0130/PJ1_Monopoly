/** CardSystem
 *  support everything about card
 */
package monopoly.kernel;
import java.util.LinkedList;
import monopoly.kernel.card.*;
public class CardSystem
{
	public final static int CARD_TOTAL=1;
	public static LinkedList<AbstractCard> actionList;
	// cardProperty[i][j] -> the number of j card of the user i
	public static int[][] cardProperty;
	public static void Init()
	{
		actionList=new LinkedList<AbstractCard>();
	}
	/** useCard
	 *  return false if the user doesn't have this card.
	 */
	public static boolean useCard(int userId,int cardId)
	{
		if (cardId>=CARD_TOTAL) return false;
		if (cardProperty[userId][cardId]==0) return false;
		cardProperty[userId][cardId]--;
		switch (cardId)
		{
			case 0: // StayANightCard
				StayANightCard c=new StayANightCard(userId);
				c.effectAction();
				break;
		};
		return true;
	}
};
