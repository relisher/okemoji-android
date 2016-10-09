package nickrout.lenslauncher.util;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.VectorDrawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import nickrout.lenslauncher.R;
import nickrout.lenslauncher.background.BroadcastReceivers;
import nickrout.lenslauncher.model.App;
import nickrout.lenslauncher.model.AppPersistent;
import nickrout.lenslauncher.ui.BaseActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by nickrout on 2016/04/02.
 */

public class AppUtil {



    private static final String TAG = AppUtil.class.getSimpleName();


    public static int lol(int i)
    {
        switch (i)
        {
            case 1: return R.drawable.ic_ahhh;
            case 2: return R.drawable.ic_angry;
            case 3: return R.drawable.ic_crying;
            case 4: return R.drawable.ic_ded;
            case 5: return R.drawable.ic_ehh;
            case 6: return R.drawable.ic_embarassed;
            case 7: return R.drawable.ic_glasses;

            case 8: return R.drawable.ic_halo;
            case 9: return R.drawable.ic_happy;
            case 10: return R.drawable.ic_happyblush;
            case 11: return R.drawable.ic_kiss;
            case 12: return R.drawable.ic_laughingcrying;
            case 13: return R.drawable.ic_love;
            case 14: return R.drawable.ic_lowkey;

            case 15: return R.drawable.ic_nocomment;
            case 16: return R.drawable.ic_ohyou;
            case 17: return R.drawable.ic_saddissapointed;
            case 18: return R.drawable.ic_sadfrustrated;
            case 19: return R.drawable.ic_yikes;
            case 20: return R.drawable.ic_sick;
            case 21: return R.drawable.ic_sideeye;

            case 22: return R.drawable.ic_sleep;
            case 23: return R.drawable.ic_steaming;
            case 24: return R.drawable.ic_suggestive;
            case 25: return R.drawable.ic_tongue;
            case 26: return R.drawable.ic_unhappy;
            case 27: return R.drawable.ic_vhappy;
            case 28: return R.drawable.ic_wow;

        }
         return -1;
    }
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
            for (int i = 1; i <= 28; i++) {

                App app = new App();

                VectorDrawable drawable = (VectorDrawable) context.getResources().getDrawable(lol(i), null);
                app.setIcon(drawable);
                app.setId(i);
				app.setPaletteColor(ColorUtil.getPaletteColorFromApp(app));
                apps.add(app);
            }

        // AppSorter.sort(apps, sortType);
        return apps;
    }

    // Launch apps, for launcher :-P
    public static void launchComponent(String emojiName, Date date, Context context) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.push().setValue(new Emoji(emojiName, date));
        Toast.makeText(context, "Your voice has been heard!", Toast.LENGTH_SHORT).show();

    }
}
