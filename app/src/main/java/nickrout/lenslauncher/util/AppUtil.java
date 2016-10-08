package nickrout.lenslauncher.util;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.VectorDrawable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nickrout.lenslauncher.R;
import nickrout.lenslauncher.background.BroadcastReceivers;
import nickrout.lenslauncher.model.App;
import nickrout.lenslauncher.model.AppPersistent;
import nickrout.lenslauncher.ui.BaseActivity;

/**
 * Created by nickrout on 2016/04/02.
 */
public class AppUtil {

    private static final String TAG = AppUtil.class.getSimpleName();

    // Get all available apps for launcher
    public static ArrayList<App> getApps(
            PackageManager packageManager, Context context, Application application,
            String iconPackLabelName, AppSorter.SortType sortType) {
        ArrayList<App> apps = new ArrayList<>();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        /*List<ResolveInfo> availableActivities = null;
        try {
            availableActivities = packageManager.queryIntentActivities(intent, 0);
        } catch (RuntimeException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.error_too_many_apps, Toast.LENGTH_SHORT).show();
        }
        if (availableActivities != null) {
            IconPackManager.IconPack selectedIconPack = null;
            ArrayList<IconPackManager.IconPack> iconPacks = new IconPackManager().getAvailableIconPacksWithIcons(true, application);

            for (IconPackManager.IconPack iconPack : iconPacks) {
                if (iconPack.mName.equals(iconPackLabelName))
                    selectedIconPack = iconPack;
            }
*/
            for (int i = 0; i < 28; i++) {
                /*ResolveInfo resolveInfo = availableActivities.get(i);

                app.setId(i);
                try {
                    app.setInstallDate(packageManager.getPackageInfo(resolveInfo.activityInfo.packageName, 0).firstInstallTime);
                } catch (PackageManager.NameNotFoundException e) {
                    app.setInstallDate(0);
                }
                app.setLabel(resolveInfo.loadLabel(packageManager));
                app.setPackageName(resolveInfo.activityInfo.packageName);
                app.setName(resolveInfo.activityInfo.name);
                app.setIconResId(resolveInfo.activityInfo.getIconResource());
                //VectorDrawable defaultBitmap = BitmapUtil.packageNameToBitmap(packageManager, resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.getIconResource());
*/              App app = new App();
                app.setIcon();

				app.setPaletteColor(ColorUtil.getPaletteColorFromApp(app));
                apps.add(app);
            }
        }
        // AppSorter.sort(apps, sortType);
        return apps;
    }

    // Launch apps, for launcher :-P
    public static void launchComponent(String packageName, String name, Context context) {
        if (packageName != null && name != null) {
            Intent componentIntent = new Intent();
            componentIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            componentIntent.setComponent(new ComponentName(packageName, name));
            if (!packageName.equals("nickrout.lenslauncher")) {
                componentIntent.setAction(Intent.ACTION_MAIN);
            }
            componentIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            try {
                // Launch Component
                context.startActivity(componentIntent);
                if (context instanceof BaseActivity) {
                    ((BaseActivity) context).overridePendingTransition(
                        R.anim.fade_in, R.anim.fade_out
                    );
                }
                // Increment app open count
                AppPersistent.incrementAppCount(packageName, name);
                // Resort apps (if open count selected)
                Settings settings = new Settings(context);
                if (settings.getSortType() == AppSorter.SortType.OPEN_COUNT_ASCENDING ||
                        settings.getSortType() == AppSorter.SortType.OPEN_COUNT_DESCENDING) {
                    Intent editAppsIntent = new Intent(context, BroadcastReceivers.AppsEditedReceiver.class);
                    context.sendBroadcast(editAppsIntent);
                }
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, R.string.error_app_not_found, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, R.string.error_app_not_found, Toast.LENGTH_SHORT).show();
        }
    }
}
