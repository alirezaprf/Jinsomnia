package Data;

import java.io.InputStream;
/**
 * Stopabble thread 
 * manual stop by using false alive
 * or 
 * closing stream
 */
public class SThread extends Thread
{
    public boolean Alive=true;
    public InputStream Streamer;

    public SThread(Runnable runnable)
    {
        super(runnable);
    }
    public SThread()
    {
        super();
    }
    public boolean ShouldContinue()
    {
        return Alive;
    }
}