package com.zerafix.catresolution;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    private int dp(float v) { return (int)(v * getResources().getDisplayMetrics().density + 0.5f); }
    private TextView text(String s, int size, boolean bold) {
        TextView t = new TextView(this); t.setText(s); t.setTextSize(size); t.setTextColor(Color.WHITE);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD); return t;
    }
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        ScrollView scroll = new ScrollView(this); scroll.setBackgroundColor(Color.rgb(12,16,25));
        LinearLayout root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(dp(22),dp(28),dp(22),dp(24)); scroll.addView(root);
        TextView title=text("CAT RESOLUTION",24,true); title.setGravity(Gravity.CENTER); root.addView(title);
        TextView sub=text("Y9 PRIME • ANDROID 10",13,false); sub.setTextColor(Color.LTGRAY); sub.setGravity(Gravity.CENTER); root.addView(sub,new LinearLayout.LayoutParams(-1,dp(34)));
        TextView note=text("Configura tu pantalla para jugar",18,true); note.setPadding(0,dp(24),0,dp(12)); root.addView(note);
        addLabel(root,"Resolución"); Spinner sizes=new Spinner(this); String[] choices={"Predeterminada del dispositivo","720 × 1600","1080 × 2400","Personalizada (próximamente)"}; sizes.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,choices)); root.addView(sizes);
        addLabel(root,"Relación de aspecto"); Spinner ratios=new Spinner(this); ratios.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,new String[]{"Original","16:9","18:9","20:9","Personalizada"})); root.addView(ratios);
        addLabel(root,"Estiramiento"); SeekBar stretch=new SeekBar(this); stretch.setMax(100); stretch.setProgress(0); root.addView(stretch);
        TextView percent=text("0%",14,false); percent.setGravity(Gravity.RIGHT); root.addView(percent); stretch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){public void onProgressChanged(SeekBar b,int p,boolean u){percent.setText(p+"%");}public void onStartTrackingTouch(SeekBar b){}public void onStopTrackingTouch(SeekBar b){}});
        Button apply=new Button(this); apply.setText("APLICAR CONFIGURACIÓN"); root.addView(apply,new LinearLayout.LayoutParams(-1,dp(54)));
        TextView status=text("La interfaz está lista. Para cambiar realmente la resolución se necesita integrar un método autorizado mediante Shizuku o root; este botón aún no modifica el sistema.",13,false); status.setTextColor(Color.LTGRAY); status.setPadding(0,dp(18),0,0); root.addView(status);
        apply.setOnClickListener(v->new AlertDialog.Builder(this).setTitle("Permiso requerido").setMessage("La modificación real de la resolución aún no está implementada. Android requiere permisos especiales para aplicar cambios globales.").setPositiveButton("Entendido",(d,w)->{}).show());
        setContentView(scroll);
    }
    private void addLabel(LinearLayout root,String s){TextView t=text(s,15,true);t.setPadding(0,dp(18),0,dp(6));root.addView(t);}
}
