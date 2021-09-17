package com.BodyPlanner.bodyplanner;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Date;

public class OneDayDecorator implements DayViewDecorator {
    private CalendarDay date;
    public OneDayDecorator(){
        date=CalendarDay.today();
    }
    @Override
    public boolean shouldDecorate(CalendarDay day){
        return day.equals(date);
    }
    @Override
    public void decorate(DayViewFacade view){
        view.addSpan(new StyleSpan(Typeface.NORMAL));
        view.addSpan(new RelativeSizeSpan(1.0f));
        view.addSpan(new ForegroundColorSpan(Color.rgb(58, 180, 197)));
    }

    public void setDate(Date date) {
        this.date=CalendarDay.from(date);
    }
}