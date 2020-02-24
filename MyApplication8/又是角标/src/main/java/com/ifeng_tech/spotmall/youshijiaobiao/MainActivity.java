package com.ifeng_tech.spotmall.youshijiaobiao;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSetBadge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int badgeCount = 0;
                try {
                    badgeCount = Integer.parseInt(30+"");
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Error input", Toast.LENGTH_SHORT).show();
                }

                boolean success = ShortcutBadger.applyCount(MainActivity.this, badgeCount);

                Toast.makeText(getApplicationContext(), "Set count=" + badgeCount + ", success=" + success, Toast.LENGTH_SHORT).show();
            }
        });


        findViewById(R.id.btnSetBadgeByNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int badgeCount = 0;
                try {
                    badgeCount = Integer.parseInt(30+"");
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Error input", Toast.LENGTH_SHORT).show();
                }

                finish();
                startService(
                        new Intent(MainActivity.this, BadgeIntentService.class).putExtra("badgeCount", badgeCount)
                );
            }
        });

        findViewById(R.id.btnRemoveBadge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean success = ShortcutBadger.removeCount(MainActivity.this);

                Toast.makeText(getApplicationContext(), "success=" + success, Toast.LENGTH_SHORT).show();
            }
        });

        //find the home launcher Package
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        String currentHomePackage = resolveInfo.activityInfo.packageName;
    }
}
