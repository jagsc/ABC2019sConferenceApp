package com.example.abc2019sconferenceapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.abc2019sconferenceapp.R;

import org.jetbrains.annotations.NotNull;

public class SearchResultFragment extends Fragment {

  @Override
  public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    // 先ほどのレイアウトをここでViewとして作成します
    return inflater.inflate(R.layout.fragment_timeline, container, false);
  }
}
