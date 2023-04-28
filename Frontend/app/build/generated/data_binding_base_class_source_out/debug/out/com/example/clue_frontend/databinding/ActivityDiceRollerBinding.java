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

public final class ActivityDiceRollerBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView die1;

  @NonNull
  public final ImageView die2;

  @NonNull
  public final ConstraintLayout roll;

  @NonNull
  public final TextView textView4;

  private ActivityDiceRollerBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView die1,
      @NonNull ImageView die2, @NonNull ConstraintLayout roll, @NonNull TextView textView4) {
    this.rootView = rootView;
    this.die1 = die1;
    this.die2 = die2;
    this.roll = roll;
    this.textView4 = textView4;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDiceRollerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDiceRollerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_dice_roller, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDiceRollerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.die1;
      ImageView die1 = ViewBindings.findChildViewById(rootView, id);
      if (die1 == null) {
        break missingId;
      }

      id = R.id.die2;
      ImageView die2 = ViewBindings.findChildViewById(rootView, id);
      if (die2 == null) {
        break missingId;
      }

      ConstraintLayout roll = (ConstraintLayout) rootView;

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      return new ActivityDiceRollerBinding((ConstraintLayout) rootView, die1, die2, roll,
          textView4);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
