package mdc.ksu.md3.Models;

import android.content.res.Resources;
import android.graphics.PorterDuff;

import mdc.ksu.md3.Utils.MD3Logger;

/**
 * Created by amgregoi on 2/23/17.
 */

public class Cubone extends Model
{

    public Cubone(Resources aRes, String aFile)
    {
        super(aRes, aFile);
    }

    public Cubone(Resources aRes, String aFile, float aPosX, float aPosY, float aPosZ)
    {
        super(aRes, aFile, aPosX, aPosY, aPosZ);
    }

    public Cubone(Resources aRes, String aFile, float aPosX, float aPosY, float aPosZ, float aScaleX, float aScaleY, float aScaleZ)
    {
        super(aRes, aFile, aPosX, aPosY, aPosZ, aScaleX, aScaleY, aScaleZ);
    }

    /***
     * This function executes the current animation
     *
     * @return
     */
    @Override
    public boolean doAnimation()
    {
        switch (mCurrentAnimation)
        {
            case ROTATE_LEFT:
                rotateLeft();
                break;
            case ROTATE_RIGHT:
                rotateRight();
                break;
            case FREEZE:
                break;
            default:
                MD3Logger.LogWarn("Cubone", "doAnimation", "Animation not implemented");
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
            case FREEZE:
                return true;
            case TACKLE:
            default:
                return false;
        }
    }

    /***
     * this function rotates the model to the left
     */
    private void rotateLeft()
    {
        mObject.rotation().y += 1.5;

    }

    /***
     * this function rotates the model to the right
     */
    private void rotateRight()
    {
        mObject.rotation().y += -1.5;
    }
}
