package mdc.ksu.md3;

import org.rajawali3d.Object3D;

import org.rajawali3d.animation.Animation3D;
import org.rajawali3d.materials.Material;

import mdc.ksu.md3.Enums.eAnimations;
import mdc.ksu.md3.Utils.MD3Logger;

public abstract class ModelBase
{
    public final static String TAG = ModelBase.class.getSimpleName();

    public Object3D mObject;
    public Material mMaterial;
    public int mObjectResource;
    public Animation3D mAnimation = null;

    public eAnimations mCurrentAnimation = eAnimations.MANUAL;

    protected float mPositionX, mPositionY, mPositionZ;
    protected float mScaleX, mScaleY, mScaleZ;

    public ModelBase()
    {
        //Default values
        mPositionX = 0;
        mPositionZ = -2;
        mPositionY = -1.2f; // bottom of screen
        mScaleX = mScaleY = mScaleZ = 0.3f;
    }

    /***
     * This function applies the current position and scale values of our model wrapper
     * to the object
     */
    public void setCurrentValues()
    {
        setPosition(mPositionX, mPositionY, mPositionZ);
        setScale(mScaleX, mScaleY, mScaleZ);
    }

    /***
     * This function sets the XYZ position of the model
     *
     * @param aX
     * @param aY
     * @param aZ
     */
    public void setPosition(double aX, double aY, double aZ)
    {
        try
        {
            mObject.setPosition(aX, aY, aZ);
        }
        catch (NullPointerException aEx)
        {
            MD3Logger.LogWarn(TAG, "setPosition", "mObject is null: " + aEx.toString());
        }
    }

    /***
     * This function sets the XYZ scale of the model
     *
     * @param aX
     * @param aY
     * @param aZ
     */
    public void setScale(double aX, double aY, double aZ)
    {
        try
        {
            mObject.setScale(aX, aY, aZ);
        }
        catch (NullPointerException aEx)
        {
            MD3Logger.LogWarn(TAG, "setScale", "mObject is null: " + aEx.toString());
        }
    }

    /***
     * This function sets the XYZ scale of the model
     *
     * @param aScale
     */
    public void setScale(double aScale)
    {
        mObject.setScale(aScale);
    }

    /***
     * This function executes the current animation
     *
     * @return
     */
    abstract public boolean setAnimation();

    /***
     * This function is used to verify if the class has implemented a specific animation
     *
     * @return
     */
    abstract public boolean hasAnimation();

    /***
     * This function will build and set the object material
     */
    abstract public void setMaterial();

}
