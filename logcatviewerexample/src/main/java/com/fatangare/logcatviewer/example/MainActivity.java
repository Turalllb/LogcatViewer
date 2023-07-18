/**
 * Copyright (C) 2016  Sandeep Fatangare <sandeep@fatangare.info>
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.fatangare.logcatviewer.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

import com.fatangare.logcatviewer.utils.LogcatViewer;

public class MainActivity extends AppCompatActivity {

    public final static int REQUEST_CODE = 1234;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkDrawOverlayPermission();

        LogcatViewer.showLogcatLoggerView(this);
    }

    public void checkDrawOverlayPermission() {
        /* check if we already  have permission to draw over other apps */
        if (android.os.Build.VERSION.SDK_INT > 24) {
            if (!Settings.canDrawOverlays(this)) {
                /* if not construct intent to request permission */
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                /* request permission via start activity for result */
                startActivityForResult(intent, REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        LogcatViewer.closeLogcatLoggerView(this);
        super.onDestroy();
    }
}
