package com.coinomi.wallet.tasks;

import android.os.AsyncTask;

import com.coinomi.wallet.Constants;
import com.google.common.base.Charsets;
import com.coinomi.wallet.BuildConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

/**
 * @author John L. Jegutanis
 */
public class CheckUpdateTask extends AsyncTask<Void, Void, Integer> {
    private static final Logger log = LoggerFactory.getLogger(CheckUpdateTask.class);
    DefaultArtifactVersion version = new DefaultArtifactVersion(BuildConfig.VERSION_NAME.replaceAll("[\\p{Alpha}]", ""));

    @Override
    protected Integer doInBackground(Void... params) {
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(Constants.VERSION_URL).openConnection();
            connection.setInstanceFollowRedirects(true);
            connection.setConnectTimeout(Constants.NETWORK_TIMEOUT_MS);
            connection.setReadTimeout(Constants.NETWORK_TIMEOUT_MS);
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charsets.UTF_8), 64);
                String line = reader.readLine().trim();
                reader.close();
                JSONObject jsonObj = new JSONObject(line);
                String git_ver = jsonObj.get("tag_name").toString().replaceAll("[\\p{Alpha}]", "");
                return version.compareTo(new DefaultArtifactVersion(git_ver));
            }
        } catch (final Exception e) {
            log.info("Could not check for update: {}", e.getMessage());
        } finally {
            if (connection != null)
                connection.disconnect();
        }

        return null;
    }
}
