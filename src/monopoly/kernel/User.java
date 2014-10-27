/** User class
 * Record the data and manage the actions of user.
 */
package monopoly.kernel;

public class User
{
	public static userCount=0;
	public String name;
	public int id;
	public User(String name)
	{
		this.name=name;
		id=userCount++;
	}
}
