package com.BodyPlanner.bodyplanner;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

public class MySelectorDecorator implements DayViewDecorator {
    private final Drawable drawable;
    private final Calendar calendar = Calendar.getInstance();
    public MySelectorDecorator(Activity context){
        drawable = context.getResources().getDrawable(R.drawable.my_selector);
    }
    @Override
    public boolean shouldDecorate(CalendarDay day){
        return true;
    }
    @Override
    public void decorate(DayViewFacade view){
        view.setSelectionDrawable(drawable);
        view.addSpan(new ForegroundColorSpan(Color.BLACK));
    }
}