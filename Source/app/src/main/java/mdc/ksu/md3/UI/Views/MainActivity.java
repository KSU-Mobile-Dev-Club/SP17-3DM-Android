package mdc.ksu.md3.UI.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.parse.ParseUser;

import org.rajawali3d.surface.IRajawaliSurface;
import org.rajawali3d.surface.RajawaliSurfaceView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mdc.ksu.md3.Enums.eAnimations;
import mdc.ksu.md3.Enums.ePokemon;
import mdc.ksu.md3.R;
import mdc.ksu.md3.UI.Renderer;
import mdc.ksu.md3.Utils.MD3Logger;

public class MainActivity extends AppCompatActivity
{
    public final static String TAG = MainActivity.class.getSimpleName();
    private Renderer mRenderer;
    private boolean mInit = false;

    @BindView(R.id.model_object) RelativeLayout mModelArea;
    @BindView(R.id.model_spinner) Spinner mModelSpinner;
    @BindView(R.id.animation_spinner) Spinner mAnimationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initSpinners();

        /***
         * Render stuffs
         */
        final RajawaliSurfaceView surface = new RajawaliSurfaceView(this);
        surface.setFrameRate(60.0);
        surface.setRenderMode(IRajawaliSurface.RENDERMODE_WHEN_DIRTY);

        mRenderer = new Renderer(this);
        surface.setSurfaceRenderer(mRenderer);
        mModelArea.addView(surface);

    }

    @OnClick(R.id.plus)
    public void zoomin()
    {
        mRenderer.zoomIn();
    }

    @OnClick(R.id.minus)
    public void zoomout()
    {
        mRenderer.zoomOut();
    }

    /***
     * This function initializes the drop down spinners listing the models and animations
     */
    public void initSpinners()
    {

        // Create an ArrayAdapter using the string array and a default spinner
        String[] enumNames = ePokemon.getStringArray();

        final ArrayAdapter<String> lModelAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, enumNames);
        lModelAdapter.setDropDownViewResource(R.layout.spinner_item_drop);
        mModelSpinner.setAdapter(lModelAdapter);
        mModelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (mInit)
                {
                    String lModelType = (String) parent.getItemAtPosition(position);
                    MD3Logger.LogInfo(TAG, "ModelSpinner.setOnItemSelected", lModelType + " selected");
                    mRenderer.createNewModel(lModelType, mAnimationSpinner.getSelectedItem().toString());
                }
                else
                {
                    mInit = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        enumNames = eAnimations.getStringArray();

        ArrayAdapter<String> lAnimationAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, enumNames);
        lAnimationAdapter.setDropDownViewResource(R.layout.spinner_item_drop);
        mAnimationSpinner.setAdapter(lAnimationAdapter);
        mAnimationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String lAnimation = (String) parent.getItemAtPosition(position);
                MD3Logger.LogInfo(TAG, "ModelSpinner.setOnItemSelected", lAnimation + " selected");
                mRenderer.changeAnimation(lAnimation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /***
     * TODO..
     *
     * @param aMenu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu aMenu)
    {

        getMenuInflater().inflate(R.menu.menu, aMenu);
        return true;
    }

    /***
     * TODO..
     *
     * @param aMenuItem
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem aMenuItem)
    {
        int lId = aMenuItem.getItemId();
        if (lId == R.id.logout)
        {
            ParseUser.logOut();

            Intent lIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(lIntent);
        }

        return super.onOptionsItemSelected(aMenuItem);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        boolean lSuccess = true;
        if (item.getTitle() == "Logout")
        {
            ParseUser.getCurrentUser().logOut();
        }
        else
        {
            lSuccess = false;
        }
        return lSuccess;
    }

}
