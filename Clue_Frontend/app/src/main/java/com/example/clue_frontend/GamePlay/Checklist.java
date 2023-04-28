package com.example.clue_frontend.GamePlay;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clue_frontend.MyApplication;
import com.example.clue_frontend.R;

public class Checklist extends AppCompatActivity {

    CheckBox mustard;
    CheckBox plum;
    CheckBox green;
    CheckBox peacock;
    CheckBox scarlet;
    CheckBox white;

    CheckBox knife;
    CheckBox candlestick;
    CheckBox revolver;
    CheckBox rope;
    CheckBox leadPipe;
    CheckBox wrench;

    CheckBox hall;
    CheckBox lounge;
    CheckBox diningRoom;
    CheckBox kitchen;
    CheckBox ballroom;
    CheckBox conservatory;
    CheckBox billiardRoom;
    CheckBox library;
    CheckBox study;

    Button saveList;

    TextView exit;
    MyApplication app = (MyApplication) getApplication();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (MyApplication) getApplication();
        setContentView(R.layout.activity_checklist);

        mustard = (CheckBox) findViewById(R.id.mustard);
        mustard.setChecked(app.isMustard());
        plum = (CheckBox) findViewById(R.id.plum);
        plum.setChecked(app.isPlum());
        green = (CheckBox) findViewById(R.id.green);
        green.setChecked(app.isGreen());
        peacock = (CheckBox) findViewById(R.id.peacock);
        peacock.setChecked(app.isPeacock());
        scarlet = (CheckBox) findViewById(R.id.scarlet);
        scarlet.setChecked(app.isScarlet());
        white = (CheckBox) findViewById(R.id.white);
        white.setChecked(app.isWhite());

        knife = (CheckBox) findViewById(R.id.knife);
        knife.setChecked(app.isKnife());
        candlestick = (CheckBox) findViewById(R.id.candlestick);
        candlestick.setChecked(app.isCandlestick());
        revolver = (CheckBox) findViewById(R.id.revolver);
        revolver.setChecked(app.isRevolver());
        rope = (CheckBox) findViewById(R.id.rope);
        rope.setChecked(app.isRope());
        leadPipe = (CheckBox) findViewById(R.id.pipe);
        leadPipe.setChecked(app.isLeadpipe());
        wrench = (CheckBox) findViewById(R.id.wrench);
        wrench.setChecked(app.isWrench());

        hall = (CheckBox) findViewById(R.id.hall);
        hall.setChecked(app.isHall());
        lounge = (CheckBox) findViewById(R.id.lounge);
        lounge.setChecked(app.isLounge());
        diningRoom = (CheckBox) findViewById(R.id.dining);
        diningRoom.setChecked(app.isDining());
        kitchen = (CheckBox) findViewById(R.id.kitchen);
        kitchen.setChecked(app.isKitchen());
        ballroom = (CheckBox) findViewById(R.id.ballroom);
        ballroom.setChecked(app.isBallroom());
        conservatory = (CheckBox) findViewById(R.id.conservatory);
        conservatory.setChecked(app.isConservatory());
        billiardRoom = (CheckBox) findViewById(R.id.billiard);
        billiardRoom.setChecked(app.isBilliard());
        library = (CheckBox) findViewById(R.id.library);
        library.setChecked(app.isLibrary());
        study = (CheckBox) findViewById(R.id.study);
        study.setChecked(app.isStudy());

        exit = findViewById(R.id.exit);
        saveList = findViewById(R.id.saveChecklist);

        final boolean[] checklistSaved = {false};
        saveList.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checklistSaved[0] = true;
                app = (MyApplication) getApplication();
                //suspects
                if (mustard.isChecked()) {
                    app.setMustard(true);
                }
                else if (!mustard.isChecked()){
                    app.setMustard(false);
                }
                if (plum.isChecked()) {
                    app.setPlum(true);
                }
                else if (!plum.isChecked()){
                    app.setPlum(false);
                }
                if (green.isChecked()) {
                    app.setGreen(true);
                }
                else if (!green.isChecked()){
                    app.setGreen(false);
                }
                if (peacock.isChecked()) {
                    app.setPeacock(true);
                }
                else if (!peacock.isChecked()){
                    app.setPeacock(false);
                }
                if (scarlet.isChecked()) {
                    app.setScarlet(true);
                }
                else if (!scarlet.isChecked()){
                    app.setScarlet(false);
                }
                if (white.isChecked()) {
                    app.setWhite(true);
                }
                else if (!white.isChecked()){
                    app.setWhite(false);
                }

                //weapons
                if (knife.isChecked()) {
                    app.setKnife(true);
                }
                else if (!knife.isChecked()){
                    app.setKnife(false);
                }
                if (candlestick.isChecked()) {
                    app.setCandlestick(true);
                }
                else if (!candlestick.isChecked()){
                    app.setCandlestick(false);
                }
                if (revolver.isChecked()) {
                    app.setRevolver(true);
                }
                else if (!revolver.isChecked()){
                    app.setRevolver(false);
                }
                if (rope.isChecked()) {
                    app.setRope(true);
                }
                else if (!rope.isChecked()){
                    app.setRope(false);
                }
                if (leadPipe.isChecked()) {
                    app.setLeadpipe(true);
                }
                else if (!leadPipe.isChecked()){
                    app.setLeadpipe(false);
                }
                if (wrench.isChecked()) {
                    app.setWrench(true);
                }
                else if (!wrench.isChecked()){
                    app.setWrench(false);
                }

                //rooms
                if (hall.isChecked()) {
                    app.setHall(true);
                }
                else if (!hall.isChecked()){
                    app.setHall(false);
                }
                if (lounge.isChecked()) {
                    app.setLounge(true);
                }
                else if (!lounge.isChecked()){
                    app.setLounge(false);
                }
                if (diningRoom.isChecked()) {
                    app.setDining(true);
                }
                else if (!diningRoom.isChecked()){
                    app.setDining(false);
                }
                if (kitchen.isChecked()) {
                    app.setKitchen(true);
                }
                else if (!kitchen.isChecked()){
                    app.setKitchen(false);
                }
                if (ballroom.isChecked()) {
                    app.setBallroom(true);
                }
                else if (!ballroom.isChecked()){
                    app.setBallroom(false);
                }
                if (conservatory.isChecked()) {
                    app.setConservatory(true);
                }
                else if (!conservatory.isChecked()){
                    app.setConservatory(false);
                }
                if (billiardRoom.isChecked()) {
                    app.setBilliard(true);
                }
                else if (!billiardRoom.isChecked()){
                    app.setBilliard(false);
                }
                if (library.isChecked()) {
                    app.setLibrary(true);
                }
                else if (!library.isChecked()){
                    app.setLibrary(false);
                }
                if (study.isChecked()) {
                    app.setStudy(true);
                }
                else if (!study.isChecked()){
                    app.setStudy(false);
                }
                Toast.makeText(Checklist.this, "You have saved your checklist!", Toast.LENGTH_SHORT).show();
            }
        });

        exit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Checklist.this, CardLayout.class); //cardlayout -> game
                if (checklistSaved[0] == true) {
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Checklist.this, "SAVE CHECKLIST!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}