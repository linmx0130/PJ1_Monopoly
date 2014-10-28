/** LogManager
 *  Manage log from all modules.
 */
package monopoly.kernel;

public class LogManager
{
	public final static int PANIC=1;
	public final static int ERROR=2;
	public final static int WARNING=3;
	public final static int LOG=4;
	//
	public static void log(int level, String source, String message)
	{
		String buffer="["+source+"]["+System.currentTimeMillis()+"]";
		switch (level)
		{
			case PANIC:
				buffer+="!!!PANIC!!! -> ";
				break;
			case ERROR:
				buffer+="ERROR! ->";
				break;
			case WARNING:
				buffer+="#WARNING# ->";
				break;
			case LOG:
				break;
			default:
				log(ERROR,"LogManager","Illegel log level.");
		}
		buffer+=message;
		System.out.println(buffer);
	}
}

