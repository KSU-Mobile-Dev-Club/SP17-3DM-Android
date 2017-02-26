package mdc.ksu.md3.Models;

import org.rajawali3d.animation.Animation;
import org.rajawali3d.animation.RotateAnimation3D;
import org.rajawali3d.animation.RotateOnAxisAnimation;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;

import mdc.ksu.md3.ModelBase;
import mdc.ksu.md3.R;
import mdc.ksu.md3.Utils.MD3Logger;

public class Cubone extends ModelBase
{
    public final static String TAG = Cubone.class.getSimpleName();

    public Cubone()
    {
        super();
        mObjectResource = R.raw.cubone_obj;
        mScaleX = mScaleY = mScaleZ = .03f;
        MD3Logger.LogInfo(TAG, TAG, TAG + " class instantiated");
    }

    /***
     * This function executes the current animation
     *
     * @return
     */
    @Override
    public boolean setAnimation()
    {
        switch (mCurrentAnimation)
        {
            case ROTATE_LEFT:
                mAnimation = new RotateOnAxisAnimation(Vector3.Axis.Y, 0, -360);
                mAnimation.setDurationMilliseconds(4000);
                mAnimation.setTransformable3D(mObject);
                mAnimation.setRepeatMode(Animation.RepeatMode.INFINITE);
                break;
            case ROTATE_RIGHT:
                mAnimation = new RotateOnAxisAnimation(Vector3.Axis.Y, 360);
                mAnimation.setDurationMilliseconds(4000);
                mAnimation.setTransformable3D(mObject);
                mAnimation.setRepeatMode(Animation.RepeatMode.INFINITE);
                break;
            case FEINT:
                mAnimation = new RotateAnimation3D(30, 60, 90);
                mAnimation.setDurationMilliseconds(1300);
                mAnimation.setDelayDelta(1);
                mAnimation.setTransformable3D(mObject);
                mAnimation.setRepeatMode(Animation.RepeatMode.INFINITE);
                break;
            case MANUAL:
            case FREEZE:
                setCurrentValues();
                mAnimation = null;
                break;
            default:
                MD3Logger.LogWarn(TAG, "setAnimation", "Animation not implemented");
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
            case FEINT:
                return true;
            default:
                return false;
        }
    }

    /***
     * This function sets the objects material
     */
    @Override
    public void setMaterial()
    {
        try
        {
            mMaterial = new Material();
            mMaterial.enableLighting(true);
            mMaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
            mMaterial.setColor(0);

            Texture text1 = new Texture("Earth", R.drawable.pm0104_00_body1);
            Texture text2 = new Texture("Earth", R.drawable.pm0104_00_eye1);

            mMaterial.addTexture(text1);
            mMaterial.addTexture(text2);
        }
        catch (Exception aEx)
        {
            MD3Logger.LogWarn(TAG, "setMaterial", aEx.getMessage());
        }

        mObject.setMaterial(mMaterial);
    }

}
