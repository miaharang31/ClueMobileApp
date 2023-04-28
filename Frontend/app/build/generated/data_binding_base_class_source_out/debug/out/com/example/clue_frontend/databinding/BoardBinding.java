// Generated by view binder compiler. Do not edit!
package com.example.clue_frontend.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.clue_frontend.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class BoardBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RelativeLayout relativeLayout;

  private BoardBinding(@NonNull RelativeLayout rootView, @NonNull RelativeLayout relativeLayout) {
    this.rootView = rootView;
    this.relativeLayout = relativeLayout;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static BoardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static BoardBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.board, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static BoardBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    RelativeLayout relativeLayout = (RelativeLayout) rootView;

    return new BoardBinding((RelativeLayout) rootView, relativeLayout);
  }
}
