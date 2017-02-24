package mdc.ksu.md3.Models;

import android.content.res.Resources;

import mdc.ksu.md3.Model;
import mdc.ksu.md3.Utils.MD3Logger;

/**
 * Created by amgregoi on 2/23/17.
 */

public class Cyndaquil extends Model
{
    public Cyndaquil(Resources aRes, String aFile)
    {
        super(aRes, aFile);
    }

    public Cyndaquil(Resources aRes, String aFile, float aPosX, float aPosY, float aPosZ)
    {
        super(aRes, aFile, aPosX, aPosY, aPosZ);
    }

    public Cyndaquil(Resources aRes, String aFile, float aPosX, float aPosY, float aPosZ, float aScaleX, float aScaleY, float aScaleZ)
    {
        super(aRes, aFile, aPosX, aPosY, aPosZ, aScaleX, aScaleY, aScaleZ);
    }

    /***
     * This function executes the current animation
     *
     * @return
     */
    @Override public boolean doAnimation()
    {
        switch (mCurrentAnimation)
        {
            case ROTATE_LEFT:
                rotateLeft();
                break;
            case ROTATE_RIGHT:
                rotateRight();
                break;
            case TACKLE:
                tackle();
                break;
            case FREEZE:
                break;
            default:
                MD3Logger.LogWarn("Cyndaquil", "doAnimation", "Animation not implemented: " + mCurrentAnimation);
                break;
        }
        return false;
    }

    /***
     * This function is used to verify if the class has implemented a specific animation
     *
     * @return
     */
    @Override
    public boolean hasAnimation()
    {
        switch (mCurrentAnimation)
        {
            case ROTATE_LEFT:
            case ROTATE_RIGHT:
            case TACKLE:
            case FREEZE:
                return true;
            default:
                return false;
        }
    }

    /***
     * this function rotates the model to the left
     */
    private void rotateLeft()
    {
        mObject.rotation().z += -1.5;

    }

    /***
     * this function rotates the model to the right
     */
    private void rotateRight()
    {
        mObject.rotation().z += 1.5;
    }

    //Variable to track tackle timing
    int count = 0;

    /***
     * This function simulates a simple 'Tackle' animation
     */
    public void tackle()
    {
        count++;
        if (count == 1)
        {
            mObject.position().x += .5;
        }
        else if (count == 50)
        {
            mObject.position().x += -.5;
        }
        else if (count == 100)
        {
            count = 0;
        }
    }
}
