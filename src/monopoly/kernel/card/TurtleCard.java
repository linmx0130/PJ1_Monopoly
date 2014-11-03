/** TurtleCard
 *  make a user slow down
 */
package monopoly.kernel.card;
import monopoly.kernel.MainController;
public class TurtleCard extends AbstractCard
{
	public void endEffectAction()
	{
		MainController.userList[this.objectUserId].beTurtle=false;
	};
	public void effectAction()
	{
		MainController.userList[this.objectUserId].beTurtle=true;
	}
	public TurtleCard (int subjectUserId,int objectUserId)
	{
		// because the action of subject user in this turn has passed, lastingTurns=4
		super("龟速卡",1,((subjectUserId>=objectUserId) ? 4 : 3),subjectUserId,objectUserId);
	}
}
