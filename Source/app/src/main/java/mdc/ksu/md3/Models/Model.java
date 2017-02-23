package mdc.ksu.md3.Models;

import android.app.Application;
import android.content.res.Resources;

import min3d.core.Object3dContainer;
import min3d.parser.IParser;
import min3d.parser.Parser;

public abstract class Model
{
    public String mObjectName;
    public Object3dContainer mObject;
    public eAnimations mCurrentAnimation = eAnimations.ROTATE_LEFT;
    public eAnimations mLastAnimation;

    protected String mObjectFile;

    protected float mPositionX;
    protected float mPositionY;
    protected float mPositionZ;

    protected float mScaleX;
    protected float mScaleY;
    protected float mScaleZ;

    public Model(Resources aRes, String aFile)
    {
        this(aRes, aFile, 0, 0, 0);
    }

    public Model(Resources aRes, String aFile, float aPosX, float aPosY, float aPosZ)
    {
        this(aRes, aFile, aPosX, aPosY, aPosZ, 0.03f, 0.03f, 0.03f);
    }

    public Model(Resources aRes, String aFile, float aPosX, float aPosY, float aPosZ, float aScaleX, float aScaleY, float aScaleZ)
    {
        mObjectFile = aFile;

        mPositionX = aPosX;
        mPositionY = aPosY;
        mPositionZ = aPosZ;

        mScaleX = aScaleX;
        mScaleY = aScaleY;
        mScaleZ = aScaleZ;

        createModel(aRes);
    }

    /***
     * This function parses the OBJ file and builds the model
     *
     * @param aRes
     */
    private void createModel(Resources aRes)
    {
        String mPackageName = "mdc.ksu.md3:raw/";
        IParser myParser = Parser.createParser(Parser.Type.OBJ, aRes, mPackageName + mObjectFile, true);
        myParser.parse();

        mObject = myParser.getParsedObject();

        //Set Position
        mObject.position().x = mPositionX;
        mObject.position().y = mPositionY;
        mObject.position().z = mPositionZ;

        //Set scale of model
        mObject.scale().x = mScaleX;
        mObject.scale().y = mScaleY;
        mObject.scale().z = mScaleZ;
    }

    /***
     * This function sets the current animation to something else
     *
     * @param aAnimation The animation that mCurrentAnimation will be set to
     */
    public void setAnimation(eAnimations aAnimation)
    {

        mLastAnimation = mCurrentAnimation;
        mCurrentAnimation = aAnimation;

        // This verifies the model has this animation
        // if not, it will go to the next
        if (!hasAnimation())
        {
            setNextAnimation();
        }
    }

    /***
     * This function cycles to the next implemented animation for the model
     */
    public void setNextAnimation()
    {
        eAnimations[] lAnimations = eAnimations.values();

        for (int iIndex = 0; iIndex < lAnimations.length - 1; iIndex++)
        {
            if (mCurrentAnimation == lAnimations[iIndex])
            {
                if (iIndex + 1 == lAnimations.length - 1)
                {
                    setAnimation(lAnimations[0]);
                }
                else
                {
                    setAnimation(lAnimations[iIndex + 1]);
                }
                break;
            }
        }
    }

    /***
     * This function executes the current animation
     *
     * @return
     */
    abstract public boolean doAnimation();

    /***
     * This function is used to verify if the class has implemented a specific animation
     *
     * @return
     */
    abstract public boolean hasAnimation();

}
