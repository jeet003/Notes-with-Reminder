


package com.jeet.remindme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import android.view.View.OnClickListener;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;


import java.util.Calendar;




public class ReminderAddActivity extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener{
    Uri ringtone;
    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> list = new ArrayList<String>();

    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;




    private Toolbar mToolbar;
    ScrollView scroll;
    ScrollView scroll1;
    //public static Switch switc;
    private EditText mTitleText;
    private TextView mDateText, mTimeText, mRepeatText, mRepeatNoText, mRepeatTypeText;
    private FloatingActionButton mFAB1;
    private FloatingActionButton mFAB2;
    private FloatingActionButton mFAB11;
    private FloatingActionButton mFAB22;

    private TextView NoBullet;

    private Calendar mCalendar;
    private int mYear, mMonth, mHour, mMinute, mDay;
    private long mRepeatTime;
    private String mTitle;
    private String mTime;
    private String mDate;
    private String mRepeat;
    private RelativeLayout selsound_button;
    private Button addbullet;
    private PopupWindow pwindo1;
    private PopupWindow pwindo2;

    private String mRepeatNo;
    private Button cancelbutton;
    private Button sharebutton;
    private Button sharebutton1;
    private String mRepeatType;
    private String mActive;
    private String mActive1;

    private FloatingActionButton addbulletbutton;

    // Values for orientation change
    private static final String KEY_TITLE = "title_key";
    private static final String KEY_TIME = "time_key";
    private static final String KEY_DATE = "date_key";
    private static final String KEY_REPEAT = "repeat_key";
    private static final String KEY_REPEAT_NO = "repeat_no_key";
    private static final String KEY_REPEAT_TYPE = "repeat_type_key";
    private static final String KEY_ACTIVE = "active_key";

    // Constant values in milliseconds
    private static final long milMinute = 60000L;
    private static final long milHour = 3600000L;
    private static final long milDay = 86400000L;
    private static final long milWeek = 604800000L;
    private static final long milMonth = 2592000000L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        final RelativeLayout rl = (RelativeLayout)findViewById(R.id.popupbulleti);


        LayoutInflater inflater = (LayoutInflater) ReminderAddActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.popupbullet,
                (ViewGroup) findViewById(R.id.popupbulleti));

        LayoutInflater inflater1 = (LayoutInflater) ReminderAddActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout1 = inflater1.inflate(R.layout.popupbulletsmall,
                (ViewGroup) findViewById(R.id.popupbulletii));



        Display display=getWindowManager().getDefaultDisplay();
        final int height1=display.getHeight();
        final int width1=display.getWidth();
        /*
        addbullet=(Button) findViewById(R.id.subbullet);
        addbullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
// We need to get the instance of the LayoutInflater


                    pwindo1 = new PopupWindow(layout, 600, 1000, true);
                    pwindo1.showAtLocation(layout, Gravity.CENTER, 0, 0);


                    sharebutton = (Button) layout.findViewById(R.id.submiti);
                    sharebutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pwindo1.dismiss();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        */
        // Initialize Views



        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitleText = (EditText) findViewById(R.id.reminder_title);
        mDateText = (TextView) layout.findViewById(R.id.set_date);
        mTimeText = (TextView) layout.findViewById(R.id.set_time);
        mRepeatText = (TextView) layout.findViewById(R.id.set_repeat);
        mRepeatNoText = (TextView) layout.findViewById(R.id.set_repeat_no);
        mRepeatTypeText = (TextView) layout.findViewById(R.id.set_repeat_type);
        mFAB1 = (FloatingActionButton) findViewById(R.id.starred1);
        mFAB2 = (FloatingActionButton) findViewById(R.id.starred2);
        mFAB11 = (FloatingActionButton) layout1.findViewById(R.id.starred11);
        mFAB22 = (FloatingActionButton) layout1.findViewById(R.id.starred22);
       // switc=(Switch)findViewById(R.id.switc);
        scroll=(ScrollView)layout.findViewById(R.id.scroll);

        scroll1=(ScrollView)layout1.findViewById(R.id.scrolli);
        addbulletbutton=(FloatingActionButton) findViewById(R.id.add_reminder_bullet);

       // switc.setChecked(false);
        /*
        switc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    scroll.setVisibility(View.VISIBLE);
                }
                else if(!isChecked)
                {
                    scroll.setVisibility(View.INVISIBLE);
                }
            }
        });
        */
        // Setup Toolbar
        setSupportActionBar(mToolbar);
       // getSupportActionBar().setTitle(R.string.title_activity_add_reminder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        // Initialize default values
        mActive = "false";
        mRepeat = "false";
        mRepeatNo = Integer.toString(1);
        mRepeatType = "Hour";

        mCalendar = Calendar.getInstance();
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mDay = mCalendar.get(Calendar.DATE);

        mDate = mDay + "/" + mMonth + "/" + mYear;
        mTime = mHour + ":" + mMinute;

        mActive1="false";

        // Setup Reminder Title EditText
        mTitleText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTitle = s.toString().trim();
                mTitleText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Setup TextViews using reminder values
        mDateText.setText(mDate);
        mTimeText.setText(mTime);
        mRepeatNoText.setText(mRepeatNo);
        mRepeatTypeText.setText(mRepeatType);
        mRepeatText.setText("Every " + mRepeatNo + " " + mRepeatType + "(s)");

        // To save state on device rotation
        if (savedInstanceState != null) {
            String savedTitle = savedInstanceState.getString(KEY_TITLE);
            mTitleText.setText(savedTitle);
            mTitle = savedTitle;

            String savedTime = savedInstanceState.getString(KEY_TIME);
            mTimeText.setText(savedTime);
            mTime = savedTime;

            String savedDate = savedInstanceState.getString(KEY_DATE);
            mDateText.setText(savedDate);
            mDate = savedDate;

            String saveRepeat = savedInstanceState.getString(KEY_REPEAT);
            mRepeatText.setText(saveRepeat);
            mRepeat = saveRepeat;

            String savedRepeatNo = savedInstanceState.getString(KEY_REPEAT_NO);
            mRepeatNoText.setText(savedRepeatNo);
            mRepeatNo = savedRepeatNo;

            String savedRepeatType = savedInstanceState.getString(KEY_REPEAT_TYPE);
            mRepeatTypeText.setText(savedRepeatType);
            mRepeatType = savedRepeatType;

            mActive = savedInstanceState.getString(KEY_ACTIVE);
        }

        // Setup up active buttons
        if (mActive.equals("false")) {
            mFAB1.setVisibility(View.VISIBLE);
            mFAB2.setVisibility(View.GONE);
            //scroll.setVisibility(View.INVISIBLE);

        } else if (mActive.equals("true")) {
            mFAB1.setVisibility(View.GONE);
            mFAB2.setVisibility(View.VISIBLE);
            //scroll.setVisibility(View.VISIBLE);
        }
        if (mActive1.equals("false")) {
            mFAB11.setVisibility(View.VISIBLE);
            mFAB22.setVisibility(View.GONE);
            scroll1.setVisibility(View.GONE);

        } else if (mActive1.equals("true")) {
            mFAB11.setVisibility(View.GONE);
            mFAB22.setVisibility(View.VISIBLE);
            scroll1.setVisibility(View.VISIBLE);

        }


        selsound_button=(RelativeLayout) layout.findViewById(R.id.select);
        selsound_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0)
            {

                Intent intent=new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, ringtone);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI, ringtone);
                startActivityForResult(intent , 1);
                /*
                final Uri currentTone= RingtoneManager.getActualDefaultRingtoneUri(ReminderAddActivity.this, RingtoneManager.TYPE_ALARM);
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, currentTone);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
                startActivityForResult(intent, TONE_PICKER);
                */
            }
        });

        Button btn = (Button) layout1.findViewById(R.id.btnAdd);

        /** Reference to the delete button of the layout main.xml */
      //  Button btnDel = (Button) findViewById(R.id.btnDel);



        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, list){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setBackgroundResource(R.drawable.roundcorner);

                //view.setBackgroundColor(Color.parseColor("#ec407a"));
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.WHITE);
                return view;
            }
        };

        addbulletbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
// We need to get the instance of the LayoutInflater


                    pwindo2 = new PopupWindow(layout1, 0, 0, true);
                  //pwindo2.setWidth(width1-150);
                  //pwindo2.setHeight(height1-400);
                   pwindo2.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
                    pwindo2.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                    pwindo2.showAtLocation(layout1, Gravity.CENTER, 0, 0);


                    sharebutton1 = (Button) layout1.findViewById(R.id.submitii);
                    sharebutton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pwindo2.dismiss();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        NoBullet=(TextView)findViewById(R.id.no_bullet_text);



        /** Defining a click event listener for the button "Add" */
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) layout1.findViewById(R.id.txtItem);
                if(!(edit.getText().toString().equalsIgnoreCase("")))
                list.add(edit.getText().toString());
                edit.setText("");
                adapter.notifyDataSetChanged();
                pwindo2.dismiss();
            }
        };
        final ListView lv = (ListView) findViewById(R.id.list);

        /** Defining a click event listener for the button "Delete" */
        OnClickListener listenerDel = new OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Getting the checked items from the listview */
                SparseBooleanArray checkedItemPositions = lv.getCheckedItemPositions();
                int itemCount = lv.getCount();

                for(int i=itemCount-1; i >= 0; i--){
                    if(checkedItemPositions.get(i)){
                        adapter.remove(list.get(i));
                    }
                }
                checkedItemPositions.clear();
                adapter.notifyDataSetChanged();
            }
        };

        /** Setting the event listener for the add button */
        btn.setOnClickListener(listener);

        /** Setting the event listener for the delete button */
       // btnDel.setOnClickListener(listenerDel);
/*
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub
                try {
// We need to get the instance of the LayoutInflater


                    pwindo2 = new PopupWindow(layout1, 600, 1000, true);
                    pwindo2.showAtLocation(layout1, Gravity.CENTER, 0, 0);


                    sharebutton1 = (Button) layout1.findViewById(R.id.submitii);
                    sharebutton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pwindo2.dismiss();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }


                return true;
            }
        });
*/
        /** Setting the adapter to the ListView */

        lv.setAdapter(adapter);


        mFAB1 = (FloatingActionButton) findViewById(R.id.starred1);
        mFAB2 = (FloatingActionButton) findViewById(R.id.starred2);
        mFAB1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mFAB1.setVisibility(View.GONE);
                mFAB2.setVisibility(View.VISIBLE);
                mActive = "true";
                try {
// We need to get the instance of the LayoutInflater


                    pwindo1 = new PopupWindow(layout, 0, 0, true);
                    pwindo1.setWidth(width1-150);
                    pwindo1.setHeight(height1-400);
                    pwindo1.showAtLocation(layout, Gravity.CENTER, 0, 0);


                    cancelbutton=(Button)layout.findViewById(R.id.canceli);
                    sharebutton = (Button) layout.findViewById(R.id.submiti);
                    sharebutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pwindo1.dismiss();
                        }
                    });
                    cancelbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                           // mFAB2 = (FloatingActionButton) findViewById(R.id.starred2);
                            mFAB2.setVisibility(View.GONE);
                           // mFAB1 = (FloatingActionButton) findViewById(R.id.starred1);
                            mFAB1.setVisibility(View.VISIBLE);
                            mActive = "false";
                            pwindo1.dismiss();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });



        mFAB11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                mFAB11.setVisibility(View.GONE);

                mFAB22.setVisibility(View.VISIBLE);
                 mActive1 = "true";
                scroll1.setVisibility(View.VISIBLE);


            }
        });
        mFAB22.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                mFAB22.setVisibility(View.GONE);

                mFAB11.setVisibility(View.VISIBLE);
                mActive1 = "false";
                scroll1.setVisibility(View.GONE);

            }
        });




}



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    ringtone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

                    // Toast.makeText(getBaseContext(),RingtoneManager.URI_COLUMN_INDEX,
                    // Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }
    }
    // To save state on device rotation
    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putCharSequence(KEY_TITLE, mTitleText.getText());
        outState.putCharSequence(KEY_TIME, mTimeText.getText());
        outState.putCharSequence(KEY_DATE, mDateText.getText());
        outState.putCharSequence(KEY_REPEAT, mRepeatText.getText());
        outState.putCharSequence(KEY_REPEAT_NO, mRepeatNoText.getText());
        outState.putCharSequence(KEY_REPEAT_TYPE, mRepeatTypeText.getText());
        outState.putCharSequence(KEY_ACTIVE, mActive);
    }

    // On clicking Time picker
    public void setTime(View v){
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                false
        );
        tpd.setThemeDark(false);
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    // On clicking Date picker
    public void setDate(View v){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    // Obtain time from time picker
    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        mHour = hourOfDay;
        mMinute = minute;
        if (minute < 10) {
            mTime = hourOfDay + ":" + "0" + minute;
        } else {
            mTime = hourOfDay + ":" + minute;
        }
        mTimeText.setText(mTime);
    }

    // Obtain date from date picker
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear ++;
        mDay = dayOfMonth;
        mMonth = monthOfYear;
        mYear = year;
        mDate = dayOfMonth + "/" + monthOfYear + "/" + year;
        mDateText.setText(mDate);
    }

    // On clicking the active button
    public void selectFab1(View v) {
        mFAB1 = (FloatingActionButton) findViewById(R.id.starred1);
        mFAB1.setVisibility(View.GONE);
        mFAB2 = (FloatingActionButton) findViewById(R.id.starred2);
        mFAB2.setVisibility(View.VISIBLE);
        mActive = "true";
        //scroll.setVisibility(View.VISIBLE);


    }

    // On clicking the inactive button
    public void selectFab2(View v) {
        mFAB2 = (FloatingActionButton) findViewById(R.id.starred2);
        mFAB2.setVisibility(View.GONE);
        mFAB1 = (FloatingActionButton) findViewById(R.id.starred1);
        mFAB1.setVisibility(View.VISIBLE);
        mActive = "false";
        //scroll.setVisibility(View.INVISIBLE);
    }


    // On clicking the active button
    /*
    public void selectFab11(View v) {
        mFAB11 = (FloatingActionButton) layout1.findViewById(R.id.starred11);
        mFAB11.setVisibility(View.GONE);
        mFAB22 = (FloatingActionButton) findViewById(R.id.starred22);
        mFAB22.setVisibility(View.VISIBLE);
      //  mActive = "true";
        scroll1.setVisibility(View.VISIBLE);
    }

    // On clicking the inactive button
    public void selectFab22(View v) {
        mFAB22 = (FloatingActionButton) findViewById(R.id.starred22);
        mFAB22.setVisibility(View.GONE);
        mFAB11 = (FloatingActionButton) findViewById(R.id.starred11);
        mFAB11.setVisibility(View.VISIBLE);
      //  mActive = "false";
        scroll1.setVisibility(View.INVISIBLE);
    }
    */

    // On clicking the repeat switch
    public void onSwitchRepeat(View view) {
        boolean on = ((Switch) view).isChecked();
        if (on) {
            mRepeat = "true";
            mRepeatText.setText("Every " + mRepeatNo + " " + mRepeatType + "(s)");
        } else {
            mRepeat = "false";
            mRepeatText.setText(R.string.repeat_off);
        }
    }

    // On clicking repeat type button
    public void selectRepeatType(View v){
        final String[] items = new String[5];

        items[0] = "Minute";
        items[1] = "Hour";
        items[2] = "Day";
        items[3] = "Week";
        items[4] = "Month";

        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Type");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                mRepeatType = items[item];
                mRepeatTypeText.setText(mRepeatType);
                mRepeatText.setText("Every " + mRepeatNo + " " + mRepeatType + "(s)");
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // On clicking repeat interval button
    public void setRepeatNo(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter Number");

        // Create EditText box to input repeat number
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        alert.setView(input);
        alert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        if (input.getText().toString().length() == 0) {
                            mRepeatNo = Integer.toString(1);
                            mRepeatNoText.setText(mRepeatNo);
                            mRepeatText.setText("Every " + mRepeatNo + " " + mRepeatType + "(s)");
                        }
                        else {
                            mRepeatNo = input.getText().toString().trim();
                            mRepeatNoText.setText(mRepeatNo);
                            mRepeatText.setText("Every " + mRepeatNo + " " + mRepeatType + "(s)");
                        }
                    }
                });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // do nothing
            }
        });
        alert.show();
    }

    // On clicking the save button
    public void saveReminder(){
        ReminderDatabase rb = new ReminderDatabase(this);

        // Creating Reminder
        int ID = rb.addReminder(new Reminder(mTitle, mDate, mTime, mRepeat, mRepeatNo, mRepeatType, mActive));

        // Set up calender for creating the notification
        mCalendar.set(Calendar.MONTH, --mMonth);
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, 0);

        // Check repeat type
        if (mRepeatType.equals("Minute")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milMinute;
        } else if (mRepeatType.equals("Hour")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milHour;
        } else if (mRepeatType.equals("Day")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milDay;
        } else if (mRepeatType.equals("Week")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milWeek;
        } else if (mRepeatType.equals("Month")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milMonth;
        }

        // Create a new notification
        if (mActive.equals("true")) {
            if (mRepeat.equals("true")) {
                new AlarmReceiver().setRepeatAlarm(getApplicationContext(), mCalendar, ID, mRepeatTime, ringtone);
            } else if (mRepeat.equals("false")) {
                new AlarmReceiver().setAlarm(getApplicationContext(), mCalendar, ID, ringtone);
            }
        }

        // Create toast to confirm new reminder
        Toast.makeText(getApplicationContext(), "Saved",
                Toast.LENGTH_SHORT).show();

        onBackPressed();
    }

    // On pressing the back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // Creating the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_reminder, menu);
        return true;
    }

    // On clicking menu buttons
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // On clicking the back arrow
            // Discard any changes
            case android.R.id.home:
                onBackPressed();
                return true;

            // On clicking save reminder button
            // Update reminder
            case R.id.save_reminder:
                mTitleText.setText(mTitle);

                if (mTitleText.getText().toString().length() == 0)
                    mTitleText.setError("Reminder Title cannot be blank!");

                else {
                    saveReminder();
                }
                return true;

            // On clicking discard reminder button
            // Discard any changes
            case R.id.discard_reminder:
                Toast.makeText(getApplicationContext(), "Discarded",
                        Toast.LENGTH_SHORT).show();

                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}