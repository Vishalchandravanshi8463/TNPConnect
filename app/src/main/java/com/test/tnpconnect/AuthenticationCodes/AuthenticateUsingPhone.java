package com.test.tnpconnect.AuthenticationCodes;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.test.tnpconnect.NameUpdation;
import com.test.tnpconnect.Util.AndroidUtil;
import java.util.concurrent.TimeUnit;

public class AuthenticateUsingPhone {
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static long timeoutSec = 60L;
    private static String verificationCode;
    private static PhoneAuthProvider.ForceResendingToken resendingToken;
    private static String phone;

    public static void sendOTP(String phone, boolean isResend, ProgressBar pb, Button verifyOTP, Activity act) {
        stateChangeOfPb(true, pb, verifyOTP);
        AuthenticateUsingPhone.phone = phone;
        PhoneAuthOptions.Builder builder = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phone)
                .setTimeout(timeoutSec, TimeUnit.SECONDS)
                .setActivity(act)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signIn(phoneAuthCredential, act, pb, verifyOTP);
                        stateChangeOfPb(false, pb, verifyOTP);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        AndroidUtil.printMessage(act, "Failed to verify OTP");
                        stateChangeOfPb(false, pb, verifyOTP);
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationCode = s;
                        resendingToken = forceResendingToken;
                        AndroidUtil.printMessage(act, "OTP sent sucessfully...");
                        stateChangeOfPb(false, pb, verifyOTP);
                    }
                });

        if(isResend) {
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
        } else {
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }
    }

    public static void signIn(PhoneAuthCredential phoneAuthCredential, Activity act, ProgressBar pb, Button verifyOTP) {
        stateChangeOfPb(true, pb, verifyOTP);
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                stateChangeOfPb(false, pb, verifyOTP);
                if(task.isSuccessful()) {
                    Intent intent = new Intent(act, NameUpdation.class);
                    intent.putExtra("number", phone);
                    act.startActivity(intent);
                } else {
                    AndroidUtil.printMessage(act, "OTP Verification Failed!!");
                }
            }
        });
    }

    public static void verifyOTP(String otp, Activity act, ProgressBar pb, Button verifyOTP) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
        signIn(credential, act, pb, verifyOTP);
    }

    private static void stateChangeOfPb(boolean inProgress, ProgressBar pb, Button verifyOTP) {
        if(inProgress) {
            pb.setVisibility(View.VISIBLE);
            verifyOTP.setVisibility(View.GONE);
        } else {
            pb.setVisibility(View.GONE);
            verifyOTP.setVisibility(View.VISIBLE);
        }
    }
}
