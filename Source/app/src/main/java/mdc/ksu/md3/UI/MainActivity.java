package mdc.ksu.md3.UI;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mdc.ksu.md3.Models.Cubone;
import mdc.ksu.md3.Models.Cyndaquil;
import mdc.ksu.md3.Models.eAnimations;
import mdc.ksu.md3.Utils.MD3Logger;
import mdc.ksu.md3.Models.Model;
import mdc.ksu.md3.R;
import min3d.core.RendererActivity;
import min3d.vos.Light;

public class MainActivity extends RendererActivity
{
    public final static String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.model_object) RelativeLayout mModelArea;
    @BindView(R.id.freeze_models) Button mFreezeButton;

    private List<Model> mModelList;

    /***
     * This function is called when the activity is created
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mModelList = new ArrayList<>();
        mModelArea.addView(_glSurfaceView);
    }

    /***
     * This function intializes the scene of your RenderActivity
     */
    @Override
    public void initScene()
    {
        MD3Logger.LogInfo(TAG, "initScene", "starting initScene()");

        //Add light to the 'scene'
        scene.lights().add(new Light());
        scene.lights().add(new Light());

        /**
         * Build Cubone(1) Model
         */
        Model mCubone1 = new Cubone(getResources(), "xy_cubone_obj", -.6f, 0f, 0f);
        mModelList.add(mCubone1);

        /**
         * Build Cubone(2) Model
         */
        Model mCubone2 = new Cubone(getResources(), "xy_cubone_obj", .6f, 0f, 0f);
        mModelList.add(mCubone2);

        /**
         * Build Cyndaquil Model
         */
        Model mCyndaquil = new Cyndaquil(getResources(), "br_cynd_obj", 0f, -2f, -.5f, .3f, .3f, .3f);
        mCyndaquil.mObject.rotation().x = 100f;         //Change initial x orientation
        mCyndaquil.mObject.rotation().z = 70f;          //Change initial z orientation
        mCyndaquil.setAnimation(eAnimations.TACKLE);    //Changes initial animation
        mModelList.add(mCyndaquil);

        /**
         * Add Models to scene
         */
        scene.addChild(mCubone1.mObject);
        scene.addChild(mCubone2.mObject);
        scene.addChild(mCyndaquil.mObject);

        MD3Logger.LogInfo(TAG, "initScene", "ending initScene()");
    }

    /**
     * Updates the current scene
     * Currently rotates the model around the y-axis
     */
    int count = 0;

    @Override
    public void updateScene()
    {
        for (Model iObject : mModelList)
        {
            iObject.doAnimation();
        }
        MD3Logger.LogDebug(TAG, "updateScene", "updated scene");

    }

    /***
     * This function sets the models to rotate left
     */
    @OnClick(R.id.switch_animations)
    public void switchAnimations()
    {
        MD3Logger.LogInfo(TAG, "switchAnimations", "switching model animations");
        for (Model iObject : mModelList)
        {
            iObject.setNextAnimation();
        }
    }

    /***
     * This function sets the models to rotate right
     */
    @OnClick(R.id.freeze_models)
    public void freezeModels()
    {
        MD3Logger.LogInfo(TAG, "freezeModels", "Halting rotation of models");
        for (Model iObject : mModelList)
        {
            if (iObject.mCurrentAnimation == eAnimations.FREEZE)
            {
                iObject.setAnimation(iObject.mLastAnimation);
                mFreezeButton.setText("Freeze");
            }
            else
            {
                iObject.setAnimation(eAnimations.FREEZE);
                mFreezeButton.setText("Resume");
            }
        }
    }
}

