// Generated by view binder compiler. Do not edit!
package com.example.clue_frontend.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.clue_frontend.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class SixCardsBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final LinearLayout layoutCards;

  @NonNull
  public final ImageView openCards;

  private SixCardsBinding(@NonNull RelativeLayout rootView, @NonNull LinearLayout layoutCards,
      @NonNull ImageView openCards) {
    this.rootView = rootView;
    this.layoutCards = layoutCards;
    this.openCards = openCards;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static SixCardsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SixCardsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.six_cards, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SixCardsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.layout_cards;
      LinearLayout layoutCards = ViewBindings.findChildViewById(rootView, id);
      if (layoutCards == null) {
        break missingId;
      }

      id = R.id.open_cards;
      ImageView openCards = ViewBindings.findChildViewById(rootView, id);
      if (openCards == null) {
        break missingId;
      }

      return new SixCardsBinding((RelativeLayout) rootView, layoutCards, openCards);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
