package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements FirstFragment.FirstFragmentListener, SecondFragment.SecondFragmentListener {

    private int previousNumber = 0;
    private final RandomGenerator randomGenerator = RandomGenerator.INSTANCE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(randomGenerator.getValidRange());
    }

    private void openFirstFragment(Range validRange) {
        final Fragment firstFragment = FirstFragment.newInstance(validRange);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment).commit();
    }

    private void openSecondFragment(Range range) {
        final Fragment secondFragment = SecondFragment.newInstance(range);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null).replace(R.id.container, secondFragment).commit();
    }

    @Override
    public void onGenerateButtonClicked(@NotNull String min, @NotNull String max) {
        try {
            Range range = randomGenerator.getRequiredRange(min, max);
            openSecondFragment(randomGenerator.validateRange(range));
        } catch (IllegalArgumentException e) {
            makeAlert(e.getMessage()).show();
        }
    }

    private AlertDialog makeAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.invalid_range_value)
                .setMessage(message)
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setPositiveButton("OK", (dialog, i) -> dialog.cancel());
        return builder.create();
    }

    @Override
    public void onBackButtonClicked() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void passRandomNumber(int num) {
        this.previousNumber = num;
    }

    @Override
    public int getPreviousResult() {
        return this.previousNumber;
    }
}
