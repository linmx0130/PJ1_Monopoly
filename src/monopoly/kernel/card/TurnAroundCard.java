/** StayANightCard
 *  stay at the place a night
 */
package monopoly.kernel.card;
import monopoly.kernel.MainController;
public class TurnAroundCard extends AbstractCard
{
	public void endEffectAction(){};
	public void effectAction()
	{
		MainController.map.userDirection[objectUserId]*=-1;
	}
	public TurnAroundCard (int subjectUserId,int objectUserId)
	{
		super("转向卡",2,0,subjectUserId,objectUserId);
	}
}
