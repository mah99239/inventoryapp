package com.example.android.inventoryapp.view;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.android.inventoryapp.ContainerTransformConfigurationHelper;
import com.example.android.inventoryapp.R;
import com.example.android.inventoryapp.controller.OnBackPressedHandler;
import com.example.android.inventoryapp.model.contact.InventoryContract;
import com.example.android.inventoryapp.view.adapters.InventoryAdapter;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.transition.Hold;
import com.google.android.material.transition.MaterialArcMotion;
import com.google.android.material.transition.MaterialContainerTransform;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, OnBackPressedHandler {

   
    private static final String KEY_LAYOUT_RES_ID = "KEY_LAYOUT_RES_ID";
    private static final String KEY_TRANSITION_NAME = "KEY_TRANSITION_NAME";
    private static final String KEY_TRANSITION_NAME_VIEW_ID = "KEY_TRANSITION_NAME_VIEW_ID";
    private static final String END_FRAGMENT_TAG = "END_FRAGMENT_TAG";
    static int green;
    static int white;
    private final Hold holdTransition = new Hold();
    TextInputEditText mTextInputEditTextSearch;
    ListView mListView;
    InventoryAdapter mAdapter;
    ExtendedFloatingActionButton mAddExtendedFloatingActionButton;
    List<Long> mId;
    List<Uri> mList;
    private int mCount = 0;
    private int layoutResId;
    @Nullable
    private String transitionName;
    private int transitionNameViewId;
    private ContainerTransformConfigurationHelper configurationHelper;

    private static MaterialContainerTransform createMusicPlayerTransform(
            Context context, boolean entering, View startView, View endView) {
        MaterialContainerTransform musicPlayerTransform =
                new MaterialContainerTransform();

        musicPlayerTransform.setPathMotion(new MaterialArcMotion());
        musicPlayerTransform.setScrimColor(Color.TRANSPARENT);
        musicPlayerTransform.setStartView(startView);
        musicPlayerTransform.setEndView(endView);
        musicPlayerTransform.addTarget(endView);
        return musicPlayerTransform;
    }

    public void replaceFragmentWithAnimation(Fragment fragment, String tag) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.fr_main_container, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void replaceFragmentWithTransform(Fragment fragment, String tag) {
        fragment.setSharedElementEnterTransition(new MaterialContainerTransform());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fr_main_container, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        configurationHelper = new ContainerTransformConfigurationHelper();
    }

/*

    public static HomeFragment newInstance(@LayoutRes int layoutResId) {
        return newInstance(layoutResId, null);
    }

    public static HomeFragment newInstance(
            @LayoutRes int layoutResId, @Nullable String transitionName) {
        return newInstance(layoutResId, transitionName, View.NO_ID);
    }

    public static HomeFragment newInstance(
            @LayoutRes int layoutResId,
            @Nullable String transitionName,
            @IdRes int transitionNameViewId) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_LAYOUT_RES_ID, layoutResId);
        args.putString(KEY_TRANSITION_NAME, transitionName);
        args.putInt(KEY_TRANSITION_NAME_VIEW_ID, transitionNameViewId);
        fragment.setArguments(args);
        return fragment;
    }
*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            layoutResId = args.getInt(KEY_LAYOUT_RES_ID);
            transitionName = args.getString(KEY_TRANSITION_NAME);
            transitionNameViewId = args.getInt(KEY_TRANSITION_NAME_VIEW_ID);
        }
        //  addTransitionAbleTarget(R.id.efab_home_add);

    }

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        // View da = view.findViewById(R.id.dialog_chec);
        //  CheckDialogFragment aa = new CheckDialogFragment(mId);
        mAddExtendedFloatingActionButton.setVisibility(View.INVISIBLE);
        mAddExtendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hold hold = (Hold) getExitTransition();
                hold.setDuration(2000);
                replaceFragmentWithTransform(new CheckDialogFragment(mId), "ss");
            }
        });
/*

        // Set up check sells  transitions
        Context context = requireContext();
        MaterialContainerTransform musicPlayerEnterTransform =
                createMusicPlayerTransform(context,
                        *//* entering= *//* true, mAddExtendedFloatingActionButton, da);

        mAddExtendedFloatingActionButton.setOnClickListener(v -> {
            androidx.transition.TransitionManager.beginDelayedTransition(containers, musicPlayerEnterTransform);
            mAddExtendedFloatingActionButton.setVisibility(View.GONE);
            da.setVisibility(View.VISIBLE);

        });

        MaterialContainerTransform musicPlayerExitTransform =
                createMusicPlayerTransform(context, *//* entering= *//* false, da, mAddExtendedFloatingActionButton);
        da.setOnClickListener(
                v -> {
                    androidx.transition.TransitionManager.beginDelayedTransition(container, musicPlayerExitTransform);
                    da.setVisibility(View.GONE);
                    mAddExtendedFloatingActionButton.setVisibility(View.VISIBLE);
                });*/
        mListView.setAdapter(mAdapter);


        //  addTransitionAbleTarget(view, R.id.efab_home_add);
//        getActivity().getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

// Set an exit transition
        //  getActivity().getWindow().setExitTransition(new Explode());


        // getWindow().setSharedElementsUseOverlay(false);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @SuppressLint("NewApi")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    counter(id);
                }

                boolean isVeggie = ((ColorDrawable) view.getBackground()) != null && ((ColorDrawable) view.getBackground()).getColor() == green;


                Log.e("ara", "" + isVeggie);

                TransitionManager.beginDelayedTransition((ViewGroup) view);

                int finalRadius = (int) Math.hypot(view.getWidth() / 2, view.getHeight() / 2);

                if (isVeggie) {

                    view.setBackgroundColor(white);
                } else {
                    Animator anim = null;
                    anim = ViewAnimationUtils.createCircularReveal(view
                            , (int) view.getWidth() / 2
                            , (int) view.getHeight() / 2
                            , 0, finalRadius);

                    view.setBackgroundColor(green);


                    anim.start();
                }
                if (green == 0)
                    green = view.getContext().getResources().getColor(R.color.colorAccentLight);
                if (white == 0)
                    white = view.getContext().getResources().getColor(R.color.design_default_color_background);
            }
        });
    /*    mAddExtendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTransitionAbleTarget(view,R.id.efab_home_add);
                Log.e("a","adsdfs");
                view.setEnabled(false);
               // view.setVisibility(View.INVISIBLE);
            }
        });*/
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getChildFragmentManager()
                .registerFragmentLifecycleCallbacks(
                        new FragmentManager.FragmentLifecycleCallbacks() {
                            @Override
                            public void onFragmentViewCreated(
                                    @NonNull FragmentManager fragmentManager,
                                    @NonNull Fragment fragment,
                                    @NonNull View view,
                                    @Nullable Bundle bundle) {
                                addTransitionAbleTarget(view, R.id.efab_home_add);

                            }
                        },
                        true);
        Snackbar.make(view.findViewById(R.id.root),
                "das", Snackbar.LENGTH_SHORT).show();
        //    showStartFragment();


    }

    private void addTransitionAbleTarget(View view, @IdRes int id) {
        View target = view.findViewById(id);
        if (target != null) {

            ViewCompat.setTransitionName(target, String.valueOf(id));
            target.setOnClickListener(this::showEndFragment);
        }
    }

    private void showStartFragment() {
      /*  TransitionSimpleLayoutFragment fragment =
                TransitionSimpleLayoutFragment.newInstance(
                        R.layout.fragment_home,
                        "shared_element_fab",
                        R.id.efab_home_add);
*/
        HomeFragment fragment = new HomeFragment();
        // Add root view as target for the Hold so that the entire view hierarchy is held in place as
        // one instead of each child view individually. Helps keep shadows during the transition.
        holdTransition.addTarget(R.id.root);
        fragment.setExitTransition(holdTransition);
        holdTransition.setDuration(20000);
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_main_container, fragment)
                .addToBackStack("ContainerTransformFragment::start")
                .commit();

    }

    private void showEndFragment(View sharedElement) {
        String transitionName = "shared_element_end_root";

        Fragment fragment = new CheckDialogFragment(mId);

        configureTransitions(fragment);

        getFragmentManager()
                .beginTransaction()
                .addSharedElement(sharedElement, transitionName)
                .replace(R.id.fr_main_container, fragment, END_FRAGMENT_TAG)
                .addToBackStack("ContainerTransformFragment::end")
                .commit();


    }

    private void configureTransitions(Fragment fragment) {
        // For all 3 container layer colors, use colorSurface since this transform can be configured
        // using any fade mode and some of the start views don't have a background and the end view
        // doesn't have a background.
        int colorSurface = MaterialColors.getColor(requireView(), R.attr.colorSurface);

        MaterialContainerTransform enterContainerTransform = buildContainerTransform(true);
        enterContainerTransform.setAllContainerColors(colorSurface);
        fragment.setSharedElementEnterTransition(enterContainerTransform);
        holdTransition.setDuration(enterContainerTransform.getDuration());

        MaterialContainerTransform returnContainerTransform = buildContainerTransform(false);
        returnContainerTransform.setAllContainerColors(colorSurface);
        fragment.setSharedElementReturnTransition(returnContainerTransform);
    }

    /* private void clearStack() {
         if (getChildFragmentManager().getBackStackEntryCount() > 0) {
             for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); i++) {
                 getFragmentManager().popBackStack();
             }
         }

         if (getChildFragmentManager().getFragments().size() > 0) {
             for (int i = 0; i < getFragmentManager().getFragments().size(); i++) {
                 Fragment mFragment = getFragmentManager().getFragments().get(i);
                 if (mFragment != null) {
                     getFragmentManager().beginTransaction().remove(mFragment).commit();
                 }
             }
         }
     }*/
    @SuppressLint("NewApi")
    private MaterialContainerTransform buildContainerTransform(boolean entering) {
        Context context = requireContext();
        MaterialContainerTransform transform;
        transform = new MaterialContainerTransform();
        transform.setDuration(20000);

        transform.setDrawingViewId(entering ? R.id.end_root : R.id.root);
        configurationHelper.configure(transform, entering);
        return transform;
    }

    @Override
    public boolean onBackPressed() {
        if (getView().findViewById(R.id.end_root) != null) {
            getChildFragmentManager().popBackStack();
            return true;
        }
        return false;
    }

    /*    @SuppressLint("NewApi")
        private void toggleFabVisibilityWithTransition() {
            ViewGroup sceneRoot = (ViewGroup) requireView();
            boolean entering =true |false;

            MaterialFade materialFade = new MaterialFade();
            materialFade.setDuration(entering ? DURATION_ENTER : DURATION_RETURN);
          //  TransitionManager.beginDelayedTransition(sceneRoot, materialFade);
        }*/
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void counter(long id) {
        //  Uri currentUri = ContentUris.withAppendedId(InventoryContract.CONTENT_URI, id);

        //  boolean check =mList.removeIf(n -> Objects.equals(n, currentUri));
        boolean check1 = mId.removeIf(n -> Objects.equals(n, id));
        if (!check1) {
            mId.add(id);
            //  mList.add(currentUri);
        }
        mCount = mId.size();


        if (mId.size() == 0) {
            mAddExtendedFloatingActionButton.setVisibility(View.INVISIBLE);
        } else {
            mAddExtendedFloatingActionButton.setVisibility(View.VISIBLE);

        }
        mAddExtendedFloatingActionButton.setText("+" + mCount);

    }

    private void init(View view) {
        mTextInputEditTextSearch = view.findViewById(R.id.et_home_search);
        mListView = view.findViewById(R.id.lst_home);
        mAdapter = new InventoryAdapter(getActivity(), null);
        mAddExtendedFloatingActionButton = view.findViewById(R.id.efab_home_add);
        mList = new ArrayList<>();
        mId = new ArrayList<>();

    }

    @SuppressLint("NewApi")
    private void startEndActivity(View sharedElement) {
        //  Intent intent = new Intent(getContext(),CheckDialogFragment.this);

        Fragment fragment = new CheckDialogFragment(mId);

        getFragmentManager().
                beginTransaction().
                replace(R.id.fr_main_container, fragment).commit();

    }
}