package com.example.android.inventoryapp;

import androidx.fragment.app.FragmentTransaction;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryAdapter;
import com.example.android.inventoryapp.data.InventoryContract;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    TextInputEditText mTextInputEditTextSearch;
    ListView mListView;
    InventoryAdapter mAdapter;
    ExtendedFloatingActionButton mAddExtendedFloatingActionButton;
    private int mCount = 0;
    List<Long> mId;
    List<Uri> mList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);

        mAddExtendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckDialogFragment dialog = new CheckDialogFragment(mId);
                Bundle bundle = new Bundle();
                bundle.putBoolean("notAlertDialog", true);
                dialog.setArguments(bundle);

                dialog.show(getFragmentManager(), "show");
            }
        });
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < mId.size(); i++) {
                    if (mId.get(i) == id) {
                        Log.e("myid", ":" + id);
                        Toast.makeText(getActivity(), "Done add item to check!!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                mId.add(id);

                counter(id);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(1, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {InventoryContract.InventoryEntry._ID,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_NAME,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_QUANTITY
        };
        CursorLoader c = new CursorLoader(this.getActivity(),
                InventoryContract.CONTENT_URI,
                projection,
                null,
                null,
                null);
        return c;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    public void counter(long id) {
        mAddExtendedFloatingActionButton.setText(mCount + getString(R.string.home_check));
        Uri currentUri = ContentUris.withAppendedId(InventoryContract.CONTENT_URI, id);
        mCount = mCount + 1;
        mList.add(currentUri);
    }

    private void init(View view) {
        mTextInputEditTextSearch = view.findViewById(R.id.et_home_search);
        mListView = view.findViewById(R.id.lst_home);
        mAdapter = new InventoryAdapter(getActivity(), null);
        mAddExtendedFloatingActionButton = view.findViewById(R.id.efab_home_add);
        mList = new ArrayList<>();
        mId = new ArrayList<>();

    }

}