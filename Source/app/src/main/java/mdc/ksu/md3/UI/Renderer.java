package mdc.ksu.md3.UI;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import org.rajawali3d.Object3D;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.loader.ParsingException;
import org.rajawali3d.renderer.RajawaliRenderer;
import org.rajawali3d.util.ObjectColorPicker;
import org.rajawali3d.util.OnFPSUpdateListener;
import org.rajawali3d.util.OnObjectPickedListener;

import java.lang.reflect.Constructor;

import mdc.ksu.md3.Enums.eAnimations;
import mdc.ksu.md3.Enums.ePokemon;
import mdc.ksu.md3.ModelBase;
import mdc.ksu.md3.Models.Cubone;
import mdc.ksu.md3.R;
import mdc.ksu.md3.Utils.MD3Logger;

public class Renderer extends RajawaliRenderer implements OnFPSUpdateListener, OnObjectPickedListener
{
    public final static String TAG = Renderer.class.getSimpleName();

    private ModelBase mPokemon;
    private Context mContext;
    private eAnimations mRecentAnimation = null;
    private ObjectColorPicker mPicker;

    public Renderer(Context context)
    {
        super(context);
        this.mContext = context;
        setFrameRate(60);
        setFPSUpdateListener(this);

        mPokemon = new Cubone(); //Default model
        mPokemon.mCurrentAnimation = eAnimations.MANUAL;

    }

    public void onOffsetsChanged(float x, float y, float z, float w, int i, int j)
    {
        MD3Logger.LogInfo("Renderer", "onTouchEvent", "Offsets were changed");
    }

    @Override
    public void onTouchEvent(MotionEvent event)
    {
        MD3Logger.LogInfo("Renderer", "onTouchEvent", "Event: " + event.toString());
    }

    /***
     * This function intiailizes the current scene
     */
    @Override
    protected void initScene()
    {
        //Reset scene
        getCurrentScene().clearChildren();
        getCurrentScene().clearLights();
        getCurrentScene().clearAnimations();
        getCurrentScene().clearCameras();

        try
        {
            // Build model
            LoaderOBJ lParser = new LoaderOBJ(getContext().getResources(), mTextureManager, mPokemon.mObjectResource);
            lParser.parse();
            mPokemon.mObject = lParser.getParsedObject();
            mPokemon.mObject.setName(mPokemon.getClass().getName());
            mPokemon.setMaterial();
            mPokemon.setCurrentValues();
            mPokemon.mCurrentAnimation = mRecentAnimation;

            // init picker
            mPicker = new ObjectColorPicker(this);
            mPicker.setOnObjectPickedListener(this);
            mPicker.registerObject(mPokemon.mObject);

            // Arc Cam init
            ArcballCamera lArc = new ArcballCamera(mContext, ((Activity) mContext).findViewById(R.id.model_object));
            lArc.setPosition(0, 0, 3);
            getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), lArc);

            // Set/start animation if one is set
            if (mPokemon.mAnimation != null)
            {
                mPokemon.setAnimation();
                getCurrentScene().registerAnimation(mPokemon.mAnimation);
                mPokemon.mAnimation.play();
            }
        }
        catch (ParsingException e)
        {
            MD3Logger.LogWarn(TAG, "initScene", e.getMessage());
        }

        if (mPokemon != null) getCurrentScene().addChild(mPokemon.mObject);
        getCurrentCamera().setZ(8.2f);
    }


    /***
     * This function initializes a new model and re-initializes the scene with the new model
     *
     * @param aPokemon
     * @param aAnim
     */
    public void createNewModel(ePokemon aPokemon, eAnimations aAnim)
    {
        createNewModel(aPokemon.name(), aAnim.name());
    }

    /***
     * This function initializes a new model and re-initializes the scene with the new model
     *
     * @param aModel
     * @param aAnim
     */
    public void createNewModel(String aModel, String aAnim)
    {
        try
        {
            Class<?> clazz = Class.forName(mContext.getPackageName() + ".Models." + aModel);
            Constructor<?> constructor = clazz.getConstructor();
            mPokemon = (ModelBase) constructor.newInstance();
            mPokemon.mCurrentAnimation = eAnimations.valueOf(aAnim);
            mPokemon.setAnimation();
            MD3Logger.LogInfo("Renderer", "initScene", "selected class was: " + clazz.getSimpleName());

            initScene(); //re-inits scene with new model

        }
        catch (Exception e)
        {
            MD3Logger.LogInfo("Renderer", "initScene", "failed to intialize model class: " + aModel);
        }
    }

    /***
     * This function changes the animation of a model and adds it to the current scene
     *
     * @param aAnimation
     */
    public void changeAnimation(String aAnimation)
    {
        mPokemon.mCurrentAnimation = eAnimations.valueOf(aAnimation);
        mRecentAnimation = mPokemon.mCurrentAnimation;

        if (mPokemon.mAnimation != null)
        {
            mPokemon.mAnimation.pause();
        }

        getCurrentScene().clearAnimations();
        mPokemon.setAnimation();

        if (mPokemon.mAnimation != null)
        {
            getCurrentScene().registerAnimation(mPokemon.mAnimation);
            mPokemon.mAnimation.play();
        }
        MD3Logger.LogInfo("Renderer", "changeAnimation", "num children###### : " + getCurrentScene().getNumChildren());
        MD3Logger.LogInfo("Renderer", "changeAnimation", "Changing animation to: " + aAnimation);
    }

    /***
     * This function will scale the model up to appear like its zooming in
     */
    public void zoomIn()
    {
        mPokemon.mObject.setScale(mPokemon.mObject.getScaleX() + .005f);
        MD3Logger.LogInfo("Renderer", "zoomIn", "");
    }

    /***
     * This function will scale the model down to appear like its are zooming out
     */
    public void zoomOut()
    {
        mPokemon.mObject.setScale(mPokemon.mObject.getScaleX() - .005f);
        MD3Logger.LogInfo("Renderer", "zoomOut", "");
    }

    /***
     * This function sends an update of the current Frames Per Second (FPS) of the 3D view
     *
     * @param fps
     */
    @Override
    public void onFPSUpdate(double fps)
    {
        //Might be useful later
    }

    @Override public void onObjectPicked(Object3D object)
    {
        Log.e(TAG, object.getName());
    }
}
