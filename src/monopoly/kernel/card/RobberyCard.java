/** RobberyCard
 *  get other's cards
 */
package monopoly.kernel.card;
import monopoly.kernel.MainController;
import monopoly.kernel.CardSystem;
public class RobberyCard extends AbstractCard
{
	public void endEffectAction(){};
	public boolean cardQuestion()
	{
		for (int i=0;i<CardSystem.CARD_TOTAL;++i)
		{
			if (CardSystem.cardProperty[objectUserId][i]>0) return true;
		}
		return false;
	};
	public void effectAction()
	{
		double tmpRate=Math.random();
		int total=0;
		for (int i=0;i<CardSystem.CARD_TOTAL;++i)
		{
			total+=CardSystem.cardProperty[objectUserId][i];
		}
		double ratePerCard=1.0/total;
		for (int i=0;i<CardSystem.CARD_TOTAL;++i)
		{
			if (tmpRate>=CardSystem.cardProperty[objectUserId][i]*ratePerCard)
			{
				tmpRate-=CardSystem.cardProperty[objectUserId][i]*ratePerCard;
			}else
			{
				CardSystem.cardProperty[objectUserId][i]--;
				CardSystem.cardProperty[subjectUserId][i]++;
				break;
			}
		}	
	}
	public RobberyCard (int subjectUserId,int objectUserId)
	{
		super("掠夺卡",7,0,subjectUserId,objectUserId);
	}
}
