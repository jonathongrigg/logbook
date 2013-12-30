package com.jonathongrigg.logbook;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class PieProgressView extends View {

    private float mPercent = 0.7f;
    private RectF mAreaArc = new RectF();
    private RectF mAreaStroke = new RectF();
    private Paint mPaintArc = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float mWidth;
    private float mHeight;

    private int mPieStrokePadding = 8;

    public PieProgressView(Context context) {
        super(context);
    }

    public PieProgressView(Context context, AttributeSet args) {
        super(context, args);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float startAngle = 0f;
        float drawTo = startAngle + (mPercent * 360);

        float radius = (mWidth >= mHeight ? mHeight/2 : mWidth/2);
        mAreaArc.set(mWidth/2 - radius, mHeight/2 - radius, mWidth/2 + radius, mHeight/2 + radius);
        mAreaStroke.set(mWidth/2 - radius + mPieStrokePadding, mHeight/2 - radius + mPieStrokePadding, mWidth/2 + radius - mPieStrokePadding, mHeight/2 + radius - mPieStrokePadding);

        mPaintArc.setColor(0xFF0099CC);
        mPaintStroke.setColor(0xFF0099CC);
        mPaintStroke.setStyle(Paint.Style.STROKE);
        mPaintStroke.setStrokeWidth(mPieStrokePadding * 2);
        //mPaintText.setColor(0xFFE6E8FA);
        mPaintText.setColor(0x99000000);
        mPaintText.setTextSize(200);
        mPaintText.setTextAlign(Paint.Align.CENTER);

        canvas.rotate(-90f, mAreaArc.centerX(), mAreaArc.centerY());
        canvas.drawArc(mAreaArc, startAngle, drawTo, true, mPaintArc);
        canvas.drawOval(mAreaStroke, mPaintStroke);
        canvas.rotate(90f, mAreaArc.centerX(), mAreaArc.centerY());
        canvas.drawText(String.valueOf((int) (mPercent * 100)) + '%', mAreaArc.centerX(), mAreaArc.centerY() - ((mPaintText.descent() + mPaintText.ascent()) / 2), mPaintText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        mHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension((int) mWidth, (int) mHeight);
    }

    public float getPercent() {
        return mPercent;
    }

    public void setPercent(float percent) {
        mPercent = percent;
        invalidate();
    }

}
