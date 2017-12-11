package com.example.hansung.anroidproject;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hansung.anroidproject.deprecated.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {

  private BookAdapter bookAdapter;
  private List<Book> BookList;
  private ViewGroup rootView;
  private RecyclerView recyclerView;


  public static final String TITLE = "예약 현황";

  public static Fragment3 newInstance() {

    return new Fragment3();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    rootView = (ViewGroup) inflater.inflate(R.layout.activity_fragment3, container, false);

    recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

    BookList = new ArrayList<>();
    bookAdapter = new BookAdapter(getContext(), BookList);

    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(bookAdapter);

    prepareAlbums();

    return rootView;
  }

  private void prepareAlbums() {


    Book a = new Book("12월 24일 1시");
    BookList.add(a);


    bookAdapter.notifyDataSetChanged();
  }


  public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
      this.spanCount = spanCount;
      this.spacing = spacing;
      this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
      int position = parent.getChildAdapterPosition(view); // item position
      int column = position % spanCount; // item column

      if (includeEdge) {
        outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
        outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

        if (position < spanCount) { // top edge
          outRect.top = spacing;
        }
        outRect.bottom = spacing; // item bottom
      } else {
        outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
        outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
        if (position >= spanCount) {
          outRect.top = spacing; // item top
        }
      }
    }
  }

  private int dpToPx(int dp) {
    Resources r = getResources();
    return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
  }

}