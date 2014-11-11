/** AbstractCard
 *  the basic of all cards
 */
package monopoly.kernel.card;

public abstract class AbstractCard
{
	// the name of the card
	public String name;
	// the id of the card
	public int typeId;
	// lastingTurns: how many turns will the effect lasting
	// 0 is call it right now
	public int lastingTurns;
	// who use the card and who is the object
	// -1 means no special user
	public int subjectUserId,objectUserId;
	//the effect aciton when it is called
	public abstract void effectAction();
	//the method to end the effect
	public abstract void endEffectAction();
	// card's special question
	// !!!ATTENTION!!! it's part of UI
	// it will be called by CardForm
	// return false means give up action
	public abstract boolean cardQuestion();
	// construction method
	public AbstractCard(String name,int id, int lastingTurns,int subjectUserId,int objectUserId)
	{
		this.name=name;
		this.typeId=id;
		this.lastingTurns=lastingTurns;
		this.subjectUserId=subjectUserId;
		this.objectUserId=objectUserId;
	}
}
