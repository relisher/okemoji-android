package nickrout.lenslauncher.background;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.VectorDrawable;
import android.os.AsyncTask;

import java.util.ArrayList;

import nickrout.lenslauncher.model.App;
import nickrout.lenslauncher.util.AppUtil;
import nickrout.lenslauncher.AppsSingleton;
import nickrout.lenslauncher.util.Settings;

/**
 * Created by rish on 26/5/16.
 */
public class UpdateAppsTask extends AsyncTask<Void, Void, Void> {

    private PackageManager mPackageManager;
    private Context mContext;
    private Application mApplication;
    private Settings mSettings;

    private ArrayList<App> mApps;
    private ArrayList<VectorDrawable> mAppIcons;

    public UpdateAppsTask(PackageManager packageManager,
                           Context context,
                           Application application) {
        this.mPackageManager = packageManager;
        this.mContext = context;
        this.mApplication = application;
        this.mSettings = new Settings(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        ArrayList<App> apps = AppUtil.getApps(
                mPackageManager,
                mContext,
                mApplication,
                mSettings.getString(Settings.KEY_ICON_PACK_LABEL_NAME),
                mSettings.getSortType());
        mApps = new ArrayList<>();
        mAppIcons = new ArrayList<>();
        for (int i = 0; i < apps.size(); i++) {
            App app = apps.get(i);
            VectorDrawable appIcon = app.getIcon();
            if (appIcon != null) {
                mApps.add(app);
                mAppIcons.add(appIcon);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        AppsSingleton.getInstance().setApps(mApps);
        AppsSingleton.getInstance().setAppIcons(mAppIcons);
        Intent appsLoadedIntent = new Intent(mApplication, BroadcastReceivers.AppsLoadedReceiver.class);
        mApplication.sendBroadcast(appsLoadedIntent);
        super.onPostExecute(result);
    }
}