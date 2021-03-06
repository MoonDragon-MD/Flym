/**
 * Flym
 * <p/>
 * Copyright (c) 2012-2015 Frederic Julian
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.yanus171.feedexfork.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import ru.yanus171.feedexfork.MainApplication;

public class FileUtils {

    public static void copy(File src, File dst) throws IOException {
        FileInputStream inStream = new FileInputStream(src);
        FileOutputStream outStream = new FileOutputStream(dst);
        FileChannel inChannel = inStream.getChannel();
        FileChannel outChannel = outStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inStream.close();
        outStream.close();
    }

    public static File GetFolder() {
        File result = new File(Environment.getExternalStorageDirectory(), "feedex/");
        if ( !result.exists() )
            if ( !result.mkdirs() ) {
                /*FetcherService.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainApplication.getContext(), "Cannot create dir " + result.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    }
                });*/
                result = new File(MainApplication.getContext().getCacheDir(), "feedex/");
                result.mkdirs();
            }

        return result;
    }

    private static File mGetImagesFolder = null;
    public static File GetImagesFolder() {
        if ( mGetImagesFolder == null ) {
            mGetImagesFolder = new File(GetFolder(), "images/");
            if (!mGetImagesFolder.exists())
                mGetImagesFolder.mkdirs();
        }
                //Toast.makeText(MainApplication.getContext(), "Cannot create dir " + result.getAbsolutePath(), Toast.LENGTH_LONG ).show();
        return mGetImagesFolder;
    }

}
