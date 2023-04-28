// Generated by view binder compiler. Do not edit!
package com.example.clue_frontend.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.clue_frontend.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityEndGameOtherPlayersBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView playerName;

  @NonNull
  public final ImageView room;

  @NonNull
  public final ImageView suspect;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final ImageView weapon;

  @NonNull
  public final TextView winLose;

  @NonNull
  public final TextView winOrLose;

  @NonNull
  public final ConstraintLayout winnerPage;

  private ActivityEndGameOtherPlayersBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView playerName, @NonNull ImageView room, @NonNull ImageView suspect,
      @NonNull TextView textView6, @NonNull ImageView weapon, @NonNull TextView winLose,
      @NonNull TextView winOrLose, @NonNull ConstraintLayout winnerPage) {
    this.rootView = rootView;
    this.playerName = playerName;
    this.room = room;
    this.suspect = suspect;
    this.textView6 = textView6;
    this.weapon = weapon;
    this.winLose = winLose;
    this.winOrLose = winOrLose;
    this.winnerPage = winnerPage;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEndGameOtherPlayersBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEndGameOtherPlayersBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_end_game_other_players, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEndGameOtherPlayersBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.playerName;
      TextView playerName = ViewBindings.findChildViewById(rootView, id);
      if (playerName == null) {
        break missingId;
      }

      id = R.id.room;
      ImageView room = ViewBindings.findChildViewById(rootView, id);
      if (room == null) {
        break missingId;
      }

      id = R.id.suspect;
      ImageView suspect = ViewBindings.findChildViewById(rootView, id);
      if (suspect == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.weapon;
      ImageView weapon = ViewBindings.findChildViewById(rootView, id);
      if (weapon == null) {
        break missingId;
      }

      id = R.id.winLose;
      TextView winLose = ViewBindings.findChildViewById(rootView, id);
      if (winLose == null) {
        break missingId;
      }

      id = R.id.winOrLose;
      TextView winOrLose = ViewBindings.findChildViewById(rootView, id);
      if (winOrLose == null) {
        break missingId;
      }

      ConstraintLayout winnerPage = (ConstraintLayout) rootView;

      return new ActivityEndGameOtherPlayersBinding((ConstraintLayout) rootView, playerName, room,
          suspect, textView6, weapon, winLose, winOrLose, winnerPage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
