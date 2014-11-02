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
	// waitTurns: after how many turns the effectAction will be called
	// 0 is call it right now
	public int waitTurns;
	// who use the card and who is the object
	// -1 means no special user
	public int subjectUserId,objectUserId;
	//the effect aciton when it is called
	public abstract void effectAction();
	// construction method
	public AbstractCard(String name,int id, int waitTurns,int subjectUserId,int objectUserId)
	{
		this.name=name;
		this.typeId=id;
		this.waitTurns=waitTurns;
		this.subjectUserId=subjectUserId;
		this.objectUserId=objectUserId;
	}
}
