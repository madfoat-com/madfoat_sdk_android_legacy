package com.madfoat.madfoatsampleapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


import com.madfoat.sdklib.activity.WebviewActivity;
import com.madfoat.sdklib.entity.request.payment.Address;
import com.madfoat.sdklib.entity.request.payment.App;
import com.madfoat.sdklib.entity.request.payment.Billing;
import com.madfoat.sdklib.entity.request.payment.MobileRequest;
import com.madfoat.sdklib.entity.request.payment.Name;
import com.madfoat.sdklib.entity.request.payment.Tran;
import com.madfoat.sdklib.entity.response.payment.MobileResponse;
import com.madfoat.sdklib.entity.response.status.StatusResponse;

import java.math.BigInteger;
import java.util.Random;

@Keep
public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;
    EditText text_language,text_currency;
    EditText phone,email;
    private String amount = "1000"; // Just for testing

    public static final String KEY ="pQ6nP-7rHt@5WRFv";// "pQ6nP-7rHt@5WRFv";//"pcL58-59R5k~4pjn";//"pQ6nP-7rHt@5WRFv";//"kCB5C#cg9MK@Njqp";//"pQ6nP-7rHt@5WRFv";//"s66Mx#BMN5-djBWj"; //"BwxtF~dq9L#xgWZb";//pQ6nP-7rHt@5WRFv        // TODO: Insert your Key here
    public static final String STORE_ID ="15996";//"15996";//"15164";//"15996"; //"20880";// "15996";//"18503";//"21941";   //15996           // TODO: Insert your Store ID here
    public static final boolean isSecurityEnabled = true;      // Mark false to test on simulator, True to test on actual device and Production
    public static final int REQUEST_CODE = 100;
    private StringBuilder log;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_language=findViewById(R.id.text_language);
        text_currency=findViewById(R.id.text_currency);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
       // Logger.addLogAdapter(new AndroidLogAdapter());



    }
//}


//    }



    public void sendMessage(View view) {
        Intent intent = new Intent(MainActivity.this, WebviewActivity.class);

//        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
//         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        EditText editText = (EditText) findViewById(R.id.text_amount);
        amount = editText.getText().toString();

        intent.putExtra(WebviewActivity.EXTRA_MESSAGE, getMobileRequest());
        intent.putExtra(WebviewActivity.SUCCESS_ACTIVTY_CLASS_NAME, "com.telr.SuccessTransationActivity");
        intent.putExtra(WebviewActivity.FAILED_ACTIVTY_CLASS_NAME, "com.telr.FailedTransationActivity");
        intent.putExtra(WebviewActivity.IS_SECURITY_ENABLED, isSecurityEnabled);
//        startActivity(intent);
        startActivityForResult(intent,REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CODE &&
                resultCode == RESULT_OK) {
            String paymentMethod = intent.getStringExtra("auth");
            if(paymentMethod.equalsIgnoreCase("yes")){
                MobileResponse status = (MobileResponse) intent.getParcelableExtra(WebviewActivity.PAYMENT_RESPONSE);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Thank you! The transaction is "+status.getAuth().getMessage());
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


                AlertDialog alert11 = builder1.create();
                alert11.show();
//                TextView textView = (TextView)findViewById(R.id.text_payment_result);
//                TextView txt_code=(TextView)findViewById(R.id.txt_code);
//                txt_code.setText("Code : "+intent.getStringExtra("Code"));
//                textView.setText(textView.getText() +" : " + status.getTrace());
                //  Log.e("CODEZZZ",":"+ TelrSharedPreference.getInstance(this).getDataFromPreference("Code"));

                if(status.getAuth()!= null) {

                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                    builder2.setMessage("Thank you! The transaction is "+status.getAuth().getMessage());
                    builder2.setCancelable(true);

                    builder2.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });


                    AlertDialog alert12 = builder2.create();
                    alert12.show();
//
                    status.getAuth().getStatus();   // Authorisation status. A indicates an authorised transaction. H also indicates an authorised transaction, but where the transaction has been placed on hold. Any other value indicates that the request could not be processed.
                    status.getAuth().getAvs();      /* Result of the AVS check:
                                            Y = AVS matched OK
                                            P = Partial match (for example, post-code only)
                                            N = AVS not matched
                                            X = AVS not checked
                                            E = Error, unable to check AVS */
                    status.getAuth().getCode();     // If the transaction was authorised, this contains the authorisation code from the card issuer. Otherwise it contains a code indicating why the transaction could not be processed.
                    status.getAuth().getMessage();  // The authorisation or processing error message.
                    status.getAuth().getCa_valid();
                    status.getAuth().getCardcode(); // Code to indicate the card type used in the transaction. See the code list at the end of the document for a list of card codes.
                    status.getAuth().getCardlast4();// The last 4 digits of the card number used in the transaction. This is supplied for all payment types (including the Hosted Payment Page method) except for PayPal.
                    status.getAuth().getCvv();      /* Result of the CVV check:
                                           Y = CVV matched OK
                                           N = CVV not matched
                                           X = CVV not checked
                                           E = Error, unable to check CVV */
                    status.getAuth().getTranref(); //The payment gateway transaction reference allocated to this request.
                    status.getAuth().getCard().getFirst6(); // The first 6 digits of the card number used in the transaction, only for version 2 is submitted in Tran -> Version
                    status.getAuth().getCard().getCountry();

                    status.getAuth().getCard().getExpiry().getMonth();
                    status.getAuth().getCard().getExpiry().getYear();
                }

            }else {
                StatusResponse status = (StatusResponse) intent.getParcelableExtra(WebviewActivity.PAYMENT_RESPONSE);

                AlertDialog.Builder builder11 = new AlertDialog.Builder(MainActivity.this);
                builder11.setMessage("Thank you! The transaction is "+status.getAuth().getMessage());
                builder11.setCancelable(true);

                builder11.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


                AlertDialog alert111 = builder11.create();
                alert111.show();
//
//                TextView textView = (TextView) findViewById(R.id.text_payment_result);
//                TextView txt_code = (TextView) findViewById(R.id.txt_code);
//                txt_code.setText("Code : " + intent.getStringExtra("Code"));
//                textView.setText(textView.getText() + " : " + status.getTrace());
                //  Log.e("CODEZZZ",":"+ TelrSharedPreference.getInstance(this).getDataFromPreference("Code"));

                if (status.getAuth() != null) {
                    status.getAuth().getStatus();   // Authorisation status. A indicates an authorised transaction. H also indicates an authorised transaction, but where the transaction has been placed on hold. Any other value indicates that the request could not be processed.
                    status.getAuth().getAvs();      /* Result of the AVS check:
                                            Y = AVS matched OK
                                            P = Partial match (for example, post-code only)
                                            N = AVS not matched
                                            X = AVS not checked
                                            E = Error, unable to check AVS */
                    status.getAuth().getCode();     // If the transaction was authorised, this contains the authorisation code from the card issuer. Otherwise it contains a code indicating why the transaction could not be processed.
                    status.getAuth().getMessage();  // The authorisation or processing error message.
                    status.getAuth().getCa_valid();
                    status.getAuth().getCardcode(); // Code to indicate the card type used in the transaction. See the code list at the end of the document for a list of card codes.
                    status.getAuth().getCardlast4();// The last 4 digits of the card number used in the transaction. This is supplied for all payment types (including the Hosted Payment Page method) except for PayPal.
                    status.getAuth().getCvv();      /* Result of the CVV check:
                                           Y = CVV matched OK
                                           N = CVV not matched
                                           X = CVV not checked
                                           E = Error, unable to check CVV */
                    status.getAuth().getTranref(); //The payment gateway transaction reference allocated to this request.
                    status.getAuth().getCard().getFirst6(); // The first 6 digits of the card number used in the transaction, only for version 2 is submitted in Tran -> Version
                    status.getAuth().getCard().getCountry();

                    status.getAuth().getCard().getExpiry().getMonth();
                    status.getAuth().getCard().getExpiry().getYear();
                }
            }
//            StatusResponse status = intent.getParcelableExtra(WebviewActivity.PAYMENT_RESPONSE);
//            if(status.getAuth()!= null) {
//                Log.d("DataVal:",  status.getAuth().getCard().getFirst6());
//                Log.d("Code", intent.getStringExtra("Code"));
//
//
//            }

        }
    }




    private MobileRequest getMobileRequest() {
        MobileRequest mobile = new MobileRequest();
        mobile.setStore(STORE_ID);                       // Store ID
        mobile.setKey(KEY);                              // Authentication Key : The Authentication Key will be supplied by Telr as part of the Mobile API setup process after you request that this integration type is enabled for your account. This should not be stored permanently within the App.
        mobile.setFramed("1");
        App app = new App();
        app.setId("123456789");                          // Application installation ID
        app.setName("TelrSDK");                          // Application name
        app.setUser("123456");                           // Application user ID : Your reference for the customer/user that is running the App. This should relate to their account within your systems.
        app.setVersion("0.0.1");                         // Application version
        app.setSdk("123");
        mobile.setApp(app);
        Tran tran = new Tran();
        tran.setTest("1");       //1                        // Test mode : Test mode of zero indicates a live transaction. If this is set to any other value the transaction will be treated as a test.
        tran.setType("Sale");                           /* Transaction type
                                                            'auth'   : Seek authorisation from the card issuer for the amount specified. If authorised, the funds will be reserved but will not be debited until such time as a corresponding capture command is made. This is sometimes known as pre-authorisation.
                                                            'sale'   : Immediate purchase request. This has the same effect as would be had by performing an auth transaction followed by a capture transaction for the full amount. No additional capture stage is required.
                                                            'verify' : Confirm that the card details given are valid. No funds are reserved or taken from the card.
                                                        */
      
        tran.setClazz("paypage");                       // Transaction class only 'paypage' is allowed on mobile, which means 'use the hosted payment page to capture and process the card details'
        tran.setCartid(String.valueOf(new BigInteger(128, new Random()))); //// Transaction cart ID : An example use of the cart ID field would be your own transaction or order reference.
        tran.setDescription("Test Mobile API");         // Transaction description
        tran.setLanguage(text_language.getText().toString());
        tran.setCurrency(text_currency.getText().toString());                        // Transaction currency : Currency must be sent as a 3 character ISO code. A list of currency codes can be found at the end of this document. For voids or refunds, this must match the currency of the original transaction.
        tran.setAmount(amount);                         // Transaction amount : The transaction amount must be sent in major units, for example 9 dollars 50 cents must be sent as 9.50 not 950. There must be no currency symbol, and no thousands separators. Thedecimal part must be separated using a dot.
      //  tran.setRef("030026794329");                                // (Optinal) Previous transaction reference : The previous transaction reference is required for any continuous authority transaction. It must contain the reference that was supplied in the response for the original transaction.

        //040023303844  //030023738912
       // tran.setFirstref("030023738912");             // (Optinal) Previous user transaction detail reference : The previous transaction reference is required for any continuous authority transaction. It must contain the reference that was supplied in the response for the original transaction.

        mobile.setTran(tran);
        Billing billing = new Billing();
        Address address = new Address();
        address.setCity("Dubai");                       // City : the minimum required details for a transaction to be processed
        address.setCountry("AE");                       // Country : Country must be sent as a 2 character ISO code. A list of country codes can be found at the end of this document. the minimum required details for a transaction to be processed
        address.setRegion("Dubai");                     // Region
        address.setLine1("SIT GTower");                 // Street address – line 1: the minimum required details for a transaction to be processed
        //address.setLine2("SIT G=Towe");               // (Optinal)
        //address.setLine3("SIT G=Towe");               // (Optinal)
        //address.setZip("SIT G=Towe");                 // (Optinal)
        billing.setAddress(address);
        Name name = new Name();
        name.setFirst("Divya");                          // Forename : .1the minimum required details for a transaction to be processed
        name.setLast("Thampi");                          // Surname : the minimum required details for a transaction to be processed
        name.setTitle("Mrs");                           // Title
        billing.setName(name);
        billing.setEmail("test@telr.com"); //Replace with email id//stackfortytwo@gmail.com : the minimum required details for a transaction to be processed.
        billing.setPhone("0551188269"); //Replace with phone number
        mobile.setBilling(billing);
        mobile.setCustref("231"); // new parameter added for saved card feature
        return mobile;

    }


}
