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

public class Cyndaquil extends ModelBase
{
    public final static String TAG = Cyndaquil.class.getSimpleName();

    public Cyndaquil()
    {
        super();
        mObjectResource = R.raw.cynd_obj;
        mScaleX = mScaleY = mScaleZ = .3f;
    }

    /***
     * This function executes the current animation
     *
     * @return
     */
    @Override public boolean setAnimation()
    {
        switch (mCurrentAnimation)
        {
            case ROTATE_LEFT:
                mAnimation = new RotateOnAxisAnimation(Vector3.Axis.Y, -360);
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
                MD3Logger.LogWarn(TAG, "setAnimation", "Animation not implemented: " + mCurrentAnimation);
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

            Texture text1 = new Texture("Earth", R.drawable.hinoarashi_0_0);
            Texture text2 = new Texture("Earth", R.drawable.hinoarashi_0_1);
            Texture text3 = new Texture("Earth", R.drawable.hinoarashi_0_2);
            Texture text4 = new Texture("Earth", R.drawable.hinoarashi_0_3);
            Texture text5 = new Texture("Earth", R.drawable.hinoarashi_0_4);
            Texture text6 = new Texture("Earth", R.drawable.hinoarashi_0_5);
            Texture text7 = new Texture("Earth", R.drawable.hinoarashi_0_6);
            Texture text8 = new Texture("Earth", R.drawable.hinoarashi_0_7);

            mMaterial.addTexture(text1);
            mMaterial.addTexture(text2);
            mMaterial.addTexture(text3);
            mMaterial.addTexture(text4);
            mMaterial.addTexture(text5);
            mMaterial.addTexture(text6);
            mMaterial.addTexture(text7);
            mMaterial.addTexture(text8);

        }
        catch (Exception aEx)
        {
            aEx.printStackTrace();
        }

        mObject.setMaterial(mMaterial);
    }
}
