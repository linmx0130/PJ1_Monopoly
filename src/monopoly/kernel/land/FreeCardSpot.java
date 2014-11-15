/** FreeCardSpot
 *  the spot will give user card free!
 */
package monopoly.kernel.land;

import java.util.Scanner;
import monopoly.kernel.*;
import monopoly.ui.MessageManager;
public class FreeCardSpot extends AbstractLand
{
	// the sum of cardAppearRate should be 1
	public final double[] cardAppearRate=
	{0.2,0.2,0.1,0.19,0.19,0.01,0.01,0.10};
	// the card to give
	public final int[] cardId=
	{0,1,2,3,4,5,6,7};
	private FreeCardSpot(MapManager mapM,int landId)
	{
		super(mapM,6,landId);
	}
	public void arrivedAction(int userId) 
	{
		double chooser=Math.random();
		int chooseCard;
		for (chooseCard=0;chooseCard<cardAppearRate.length;++chooseCard)
		{
			if (cardAppearRate[chooseCard]<chooser)
			{
				chooser-=cardAppearRate[chooseCard];
			}else break;
		}

		chooseCard=cardId[chooseCard];
		CardSystem.cardProperty[userId][chooseCard]++;
		MessageManager.showMessage(MessageManager.MESSAGE,"FreeCardSpot",
			"玩家"+MainController.userList[userId].getName()+"幸运的得到了"+
			CardSystem.cardName[chooseCard]	+"一张，其他玩家可要小心咯~");
	};
	public void passingAction(int userId) {};
	public static AbstractLand loader(MapManager mapM, Scanner fin,int landId)
	{
		FreeCardSpot ret=new FreeCardSpot(mapM,landId);
		return ret;
	}
};
