package com.example.android.inventoryapp.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.android.inventoryapp.R;
import com.example.android.inventoryapp.view.adapters.CheckAdapter;
import com.example.android.inventoryapp.model.contact.InventoryContract;
import com.google.android.material.transition.Hold;

import java.util.List;

public class CheckDialogFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    TextView mNameTextView;
    TextView mPriceTextView;
    TextView mTotalTextView;
    EditText mCountEditText;
    Button mCheckoutButton;
    Button mCancelButton;
    ImageButton mPlusButton;
    ImageButton mMinusButton;
    CheckAdapter mAdapter;
    ListView mListView;
    List<Long> mId;
    View header;
    View footer;
    private final Hold holdTransition = new Hold();

    private static final String KEY_TRANSITION_NAME = "KEY_TRANSITION_NAME";

    TextView countAllTextView;


    public CheckDialogFragment(List<Long> id) {

        mId = id;

    }

    public static CheckDialogFragment newInstance(
            @LayoutRes int layoutResId, @Nullable String transitionName, List<Long> id) {

        return newInstance(layoutResId, transitionName, View.NO_ID, id);

    }

    public static CheckDialogFragment newInstance(
            @LayoutRes int layoutResId,
            @Nullable String transitionName,
            @IdRes int transitionNameViewId, List<Long> id) {
        CheckDialogFragment fragment = new CheckDialogFragment(id);
        Bundle args = new Bundle();

        args.putString(KEY_TRANSITION_NAME, transitionName);

        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.dialog_home_check, container, false);



    }
    //  TODo: Object bar = foo.isSelected() ? foo : baz;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        mListView.setAdapter(mAdapter);
        int aa = 2;

        /**
         * start dialog with slide down
         */

        //   getDialog().getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        addTransitionAbleTarget(view, R.id.btn_homecheck_cancel);


    }

    private void showStartFragment(View view) {
      /*  TransitionSimpleLayoutFragment fragment =
                TransitionSimpleLayoutFragment.newInstance(
                        R.layout.fragment_home,
                        "shared_element_fab",
                        R.id.efab_home_add);
*/
        String transitionName = "shared_element_end_root";

        HomeFragment fragment = new HomeFragment();
        // Add root view as target for the Hold so that the entire view hierarchy is held in place as
        // one instead of each child view individually. Helps keep shadows during the transition.
        holdTransition.addTarget(R.id.btn_homecheck_cancel);
        fragment.setExitTransition(holdTransition);
        holdTransition.setDuration(20000);
        getFragmentManager()
                .beginTransaction()
                .addSharedElement(view , transitionName)
                .replace(R.id.fr_main_container, fragment)
                .addToBackStack("ContainerTransformFragment::start")
                .commit();
    }
    private void addTransitionAbleTarget(View view, @IdRes int id) {
        View target = view.findViewById(id);
        if (target != null) {

            ViewCompat.setTransitionName(target, String.valueOf(id));
            target.setOnClickListener(this::showStartFragment);
            holdTransition.setDuration(5450);

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(1, null, this);
    }

    public void init(View view) {
        mNameTextView = view.findViewById(R.id.tv_check_name);
        mPriceTextView = view.findViewById(R.id.tv_check_price);
        mTotalTextView = view.findViewById(R.id.tv_check_total);
        mCountEditText = view.findViewById(R.id.et_check_count);
        mPlusButton = view.findViewById(R.id.btn_check_plus);
        mMinusButton = view.findViewById(R.id.btn_check_minuse);
        mCheckoutButton = view.findViewById(R.id.btn_homecheck_checkout);
        mCancelButton = view.findViewById(R.id.btn_homecheck_cancel);
        mAdapter = new CheckAdapter(getActivity(), null);
        mListView = view.findViewById(R.id.lst_Homecheck);
        header = view.inflate(getContext(), R.layout.list_header_check, null);

        footer = view.inflate(getContext(), R.layout.list_footer_check, null);
        countAllTextView = footer.findViewById(R.id.tv_footer_check_countall);

        mListView.addHeaderView(header);

        mListView.addFooterView(footer);

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {


        String[] projection = {InventoryContract.InventoryEntry._ID,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_NAME,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_QUANTITY
        };

        String selection = InventoryContract.InventoryEntry._ID + "=?";
        ;

        String[] selectionArgs = new String[mId.size()];
        for (int i = 0; i < mId.size(); i++) {
            selectionArgs[i] = String.valueOf(mId.get(i));

            if (i > 0) selection += " OR " + InventoryContract.InventoryEntry._ID + "=?";
        }

        return new CursorLoader(this.getActivity(),
                InventoryContract.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null);

    }


    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (mAdapter != null && data == null && data.getCount() > 0) {
            return;
        }
        //  mAdapter.getItem(1);
        mAdapter.swapCursor(data);
        TextView t = getActivity().findViewById(R.id.tv_check_total);
        /*mAdapter.getView()
        for (int i =0; i<data.getCount(); i++) {
            mAdapter.getItemId(i);
            Log.e("adapter", ":"+mAdapter.getItemId(i) );
*/


        //   countAllTextView.setText( getString(R.string.count_element_check_out)+String.valueOf());


    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }


}
