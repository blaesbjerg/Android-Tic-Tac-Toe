package no.woact.blemar17.ultimatetictactoe.model;

import android.os.SystemClock;

public class GameTimer {

    long mMillisecondTime = 0L;
    long mStartTime = 0L;
    int mSeconds = 0;
    int mMinutes = 0;
    int mMilliseconds = 0;

    public void initTimer() {
        mStartTime = SystemClock.uptimeMillis();
    }

    public void run() {
        mMillisecondTime = SystemClock.uptimeMillis() - mStartTime;
        mSeconds = (int) (mMillisecondTime / 1000);
        mMinutes = mSeconds / 60;
        mSeconds = mSeconds % 60;
        mMilliseconds = (int) (mMillisecondTime % 10);
    }

    public int getSeconds() {
        return mSeconds;
    }

    public int getMinutes() {
        return mMinutes;
    }

    public int getMilliseconds() {
        return mMilliseconds;
    }



}
