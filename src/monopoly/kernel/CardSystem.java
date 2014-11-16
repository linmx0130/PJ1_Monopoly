/** CardSystem
 *  support everything about card
 */
package monopoly.kernel;
import java.util.ArrayList;
import monopoly.kernel.card.*;
public class CardSystem
{
	public final static int CARD_TOTAL=9;
	public static ArrayList<AbstractCard> actionList;
	// cardProperty[i][j] -> the number of j card of the user i
	public static int[][] cardProperty;
	// card name
	public static String[] cardName=
	{
		"滞留卡",
		"龟速卡",
		"转向卡",
		"遥控骰子卡",
		"路障卡",
		"购地卡",
		"查税卡",
		"掠夺卡",
		"均富卡"
	};
	//needObject will be check by CardForm
	public static boolean[] needObject=
	{
		false,true,true,false,false,false,true,true,false
	};
	public static void Init()
	{
		actionList=new ArrayList<AbstractCard>();
	}
	/** useCard
	 *  return false if the user doesn't have this card or the card can't be used
	 *  objectId==-1 means no object
	 */
	public static boolean useCard(int userId,int objectId,int cardId)
	{
		if (cardId>=CARD_TOTAL) return false;
		if (cardProperty[userId][cardId]==0) return false;
		AbstractCard c;
		switch (cardId)
		{
			case 0: // StayANightCard
				c=new StayANightCard(userId);
				c.effectAction();
				break;
			case 1: //TurtleCard
				if ( objectId == -1 ) return false;
				if ( MainController.map.getDistance(userId,objectId) > 5) return false;
				c=new TurtleCard(userId,objectId);
				c.effectAction();
				actionList.add(c);
				break;
			case 2: // TurnAroundCard
				if ( objectId == -1 ) return false;
				if ( MainController.map.getDistance(userId,objectId) > 5) return false;
				c=new TurnAroundCard(userId,objectId);
				c.effectAction();
				break;
			case 3: //DiceControlCard
				c=new StayANightCard(userId);
				c.effectAction();
				break;
			case 4: //BarrierCard
				c=new BarrierCard(userId);
				if (!c.cardQuestion()) return false;
				c.effectAction();
				break;
			case 5: //BuyBuyBuyCard
				c=new BuyBuyBuyCard(userId);
				if (!c.cardQuestion()) return false;
				c.effectAction();
				break;
			case 6: //TaxCard
				if ( objectId == -1 ) return false;
				if ( MainController.map.getDistance(userId,objectId) > 5) return false;
				c=new TaxCard(userId,objectId);
				c.effectAction();
				break;
			case 7: //RobberyCard
				if ( objectId == -1 ) return false;
				if ( MainController.map.getDistance(userId,objectId) > 5) return false;
				c=new TaxCard(userId,objectId);
				c.effectAction();
				break;
			case 8: //ReDistributionCard
				c=new ReDistributionCard(userId);
				c.effectAction();
				break;
		};
		cardProperty[userId][cardId]--;
		return true;
	}
	/** maintainCardEffection
	 *  maintain the lasting card
	 *  it should be called by TimeManager
	 */
	static void maintainCardEffection()
	{
		for (int i=actionList.size()-1;i>=0;--i)
		{
			AbstractCard c=actionList.get(i);
			if (--c.lastingTurns == 0)
			{
				c.endEffectAction();
				actionList.remove(i);
			}
		}
	}
};
