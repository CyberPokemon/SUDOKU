package com.example.sudoku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    String[]  item = {"EASY","MEDIUM","HARD"};
    String[]  item2 = {"CLASSIC SUDOKU","DIAGONAL SUDOKU"};
    AutoCompleteTextView autoCompleteTextView, autoCompleteTextView2;
    ArrayAdapter<String> adapteritems,adapteritems2;
    int select1=-1,select2=-1,fselect1=-1,fselect2=-1;
    Button B[][]= new Button[9][9];
    Button generate, check, start;
    TextView remarks;
    String errormessage="", message="";
    ForegroundColorSpan fcsred = new ForegroundColorSpan(Color.RED);
    ForegroundColorSpan fcsgreen = new ForegroundColorSpan(Color.GREEN);
    int flag1=0, flag2=0, flag3=0,matchstarted=0;
    double t1=0,t2=0;
    
    int ques1[][]= new int[9][9];
    int ques12[][]= new int[9][9];
    int ques2[][]= new int[9][9];
    int ques22[][]= new int[9][9];
    int N=9,SRN = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setupUIviews();

        adapteritems=new ArrayAdapter<String>(this,R.layout.list_item,item);
        adapteritems2=new ArrayAdapter<String>(this,R.layout.list_item,item2);

        autoCompleteTextView.setAdapter(adapteritems);
        autoCompleteTextView2.setAdapter(adapteritems2);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                select1=i;
            }
        });

        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                select2=i;
            }
        });
        
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select1!=-1)
                {
                    if(select2==0)
                    {
                        if(flag1==1 && flag2==1 && flag3!=1)
                        {
                            AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity3.this);
                            alertdialog.setTitle("SHOW ANSWER");
                            alertdialog.setMessage("Do you want see the answer of sudoku?");
                            alertdialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    message = "ANSWER DISPLAYED";
                                    SpannableString ss = new SpannableString(message);
                                    ss.setSpan(fcsgreen, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    remarks.setText(ss);
                                    display2(ques1);//will be changed letter
                                    flag2=-1;
                                    matchstarted=0;
                                    generate.setText("GENERATE");
                                    start.setText("START");
                                }
                            });

                            alertdialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });

                            alertdialog.show();
                        }
                        else
                        {
                            if((flag1==1 && flag2==-1) || flag3==1)
                            {
                                reset();
                            }
                            generate1();
                            message = "SUCCESSFULLY GENERATED";
                            SpannableString ss = new SpannableString(message);
                            ss.setSpan(fcsgreen, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            remarks.setText(ss);
                            flag1 = 1;
                        }

                        //display1();
                    }

                    else if (select2==1)
                    {
                        if(flag1==1 && flag2==1 && flag3!=1)
                        {
                            AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity3.this);
                            alertdialog.setTitle("SHOW ANSWER");
                            alertdialog.setMessage("Do you want see the answer of sudoku?");
                            alertdialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    message = "ANSWER DISPLAYED";
                                    SpannableString ss = new SpannableString(message);
                                    ss.setSpan(fcsgreen, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    remarks.setText(ss);
                                    display2(ques1);//will be changed letter
                                    flag2=-1;
                                    matchstarted=0;
                                    generate.setText("GENERATE");
                                    start.setText("START");
                                }
                            });

                            alertdialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });

                            alertdialog.show();
                        }
                        else
                        {
                            if((flag1==1 && flag2==-1) || flag3==1)
                            {
                                reset();
                            }
                            generate2();
                            message = "SUCCESSFULLY GENERATED";
                            SpannableString ss = new SpannableString(message);
                            ss.setSpan(fcsgreen, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            remarks.setText(ss);
                            flag1 = 1;
                        }

                    }


                    else
                    {
                        errormessage="SUDOKU TYPE NOT SELECTED";
                        SpannableString ss = new SpannableString(errormessage);
                        ss.setSpan(fcsred, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        remarks.setText(ss);
                        Toast.makeText(MainActivity3.this, "OPTION NOT AVAILABLE", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    errormessage="DIFFICULTY NOT SELECTED";
                    SpannableString ss = new SpannableString(errormessage);
                    ss.setSpan(fcsred, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    remarks.setText(ss);
                }

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag1==1 && flag2==0 && flag3==0)
                {
                    if(select1==0)
                        showquestion(30);
                    else if(select1==1)
                        showquestion(45);
                    else if(select1==2)
                        showquestion(60);
                    copysudoku(ques12,ques2);
                    message="GOOD LUCK!! YOUR TIME STARTS NOW";
                    SpannableString ss = new SpannableString(message);
                    ss.setSpan(fcsgreen, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    remarks.setText(ss);
                    display2(ques12);
                    start.setText("RESET");
                    generate.setText("ANSWER");
                    flag2=1;
                    matchstarted=1;
                    t1=System.currentTimeMillis();
                }
                else if(flag1==1 && flag2==1 && flag3!=1)
                {
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity3.this);
                    alertdialog.setTitle("RESET SUDOKU");
                    alertdialog.setMessage("Do you want to RESET sudoku? All your progress will be lost.");
                    alertdialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            display2(ques12);
                            copysudoku(ques12,ques2);
                            message = "RESET SUCCESSFUL";
                            SpannableString ss = new SpannableString(message);
                            ss.setSpan(fcsgreen, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            remarks.setText(ss);

                        }
                    });

                    alertdialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    alertdialog.show();
                }
                else if(flag2==-1 || flag3==1)
                {
                    Toast.makeText(MainActivity3.this, "PRESS GENERATE TO GENERATE A NEW SUDOKU", Toast.LENGTH_SHORT).show();
                }
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag1==1 && flag2==1 && flag3!=1)
                {
                    t2=System.currentTimeMillis();
                    if(select2==0)
                    {
                        if (isempty(ques2)) {
                            errormessage = "ALL THE CELLS ARE NOT FILLED";
                            SpannableString ss = new SpannableString(errormessage);
                            ss.setSpan(fcsred, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            remarks.setText(ss);
                        } else if (isempty(ques2) == false && check2() == true) {
                            message = "CONGRATULATIONS!! SUDOKU IS SOLVED";
                            String time = Double.toString((t2 - t1) / 1000);
                            message = message + "\n Time Taken =" + time + "s";
                            SpannableString ss = new SpannableString(message);
                            ss.setSpan(fcsgreen, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            remarks.setText(ss);
                            start.setText("START");
                            generate.setText("GENERATE");
                            matchstarted = 0;
                            flag3 = 1;
                        } else if (isempty(ques2) == false && check2() == false) {
                            errormessage = "SUDOKU IS NOT SOLVED";
                            SpannableString ss = new SpannableString(errormessage);
                            ss.setSpan(fcsred, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            remarks.setText(ss);
                        }
                    }
                    else if (select2==1)
                    {
                        if (isempty(ques2))
                        {
                            errormessage = "ALL THE CELLS ARE NOT FILLED";
                            SpannableString ss = new SpannableString(errormessage);
                            ss.setSpan(fcsred, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            remarks.setText(ss);
                        }
                        else if (isempty(ques2) == false && check2() == true && diagonalcheck(ques2)==true)
                        {
                            message = "CONGRATULATIONS!! SUDOKU IS SOLVED";
                            String time = Double.toString((t2 - t1) / 1000);
                            message = message + "\n Time Taken =" + time + "s";
                            SpannableString ss = new SpannableString(message);
                            ss.setSpan(fcsgreen, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            remarks.setText(ss);
                            start.setText("START");
                            generate.setText("GENERATE");
                            matchstarted = 0;
                            flag3 = 1;
                        }
                        else if(isempty(ques2) == false && check2() == true)
                        {
                            errormessage = "SUDOKU IS SOLVED BUT NOT DIAGONALLY";
                            SpannableString ss = new SpannableString(errormessage);
                            ss.setSpan(fcsred, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            remarks.setText(ss);
                        }
                        else if (isempty(ques2) == false && check2() == false)
                        {
                            errormessage = "SUDOKU IS NOT SOLVED";
                            SpannableString ss = new SpannableString(errormessage);
                            ss.setSpan(fcsred, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            remarks.setText(ss);
                        }
                    }
                }
            }
        });

        B[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1)
                {
                    if (ques12[0][0] == 0) {
                        ques2[0][0] = (ques2[0][0] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1)
                {

                    if (ques12[0][1] == 0) {
                        ques2[0][1] = (ques2[0][1] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[0][2] == 0) {
                        ques2[0][2] = (ques2[0][2] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[0][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[0][3] == 0) {
                        ques2[0][3] = (ques2[0][3] + 1) % 10;
                        display2(ques2);

                    }
                    remarks.setText("");
                }
            }
        });

        B[0][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[0][4] == 0) {
                        ques2[0][4] = (ques2[0][4] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[0][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[0][5] == 0) {
                        ques2[0][5] = (ques2[0][5] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[0][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[0][6] == 0) {
                        ques2[0][6] = (ques2[0][6] + 1) % 10;
                        display2(ques2);

                    }
                    remarks.setText("");
                }
            }
        });

        B[0][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[0][7] == 0) {
                        ques2[0][7] = (ques2[0][7] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[0][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[0][8] == 0) {
                        ques2[0][8] = (ques2[0][8] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[1][0] == 0) {
                        ques2[1][0] = (ques2[1][0] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[1][1] == 0) {
                        ques2[1][1] = (ques2[1][1] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[1][2] == 0) {
                        ques2[1][2] = (ques2[1][2] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[1][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[1][3] == 0) {
                        ques2[1][3] = (ques2[1][3] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[1][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[1][4] == 0) {
                        ques2[1][4] = (ques2[1][4] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[1][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[1][5] == 0) {
                        ques2[1][5] = (ques2[1][5] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[1][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[1][6] == 0) {
                        ques2[1][6] = (ques2[1][6] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[1][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[1][7] == 0) {
                        ques2[1][7] = (ques2[1][7] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[1][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[1][8] == 0) {
                        ques2[1][8] = (ques2[1][8] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[2][0] == 0) {
                        ques2[2][0] = (ques2[2][0] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1)
                {
                    if (ques12[2][1] == 0) {
                        ques2[2][1] = (ques2[2][1] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[2][2] == 0) {
                        ques2[2][2] = (ques2[2][2] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[2][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[2][3] == 0) {
                        ques2[2][3] = (ques2[2][3] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[2][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[2][4] == 0) {
                        ques2[2][4] = (ques2[2][4] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[2][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(matchstarted==1) {
                    if (ques12[2][5] == 0) {
                        ques2[2][5] = (ques2[2][5] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[2][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[2][6] == 0) {
                        ques2[2][6] = (ques2[2][6] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[2][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[2][7] == 0) {
                        ques2[2][7] = (ques2[2][7] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[2][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[2][8] == 0) {
                        ques2[2][8] = (ques2[2][8] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[3][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[3][0] == 0) {
                        ques2[3][0] = (ques2[3][0] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[3][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[3][1] == 0) {
                        ques2[3][1] = (ques2[3][1] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[3][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[3][2] == 0) {
                        ques2[3][2] = (ques2[3][2] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[3][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[3][3] == 0) {
                        ques2[3][3] = (ques2[3][3] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[3][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[3][4] == 0) {
                        ques2[3][4] = (ques2[3][4] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[3][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[3][5] == 0) {
                        ques2[3][5] = (ques2[3][5] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[3][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[3][6] == 0) {
                        ques2[3][6] = (ques2[3][6] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[3][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[3][7] == 0) {
                        ques2[3][7] = (ques2[3][7] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[3][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[3][8] == 0) {
                        ques2[3][8] = (ques2[3][8] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[4][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[4][0] == 0) {
                        ques2[4][0] = (ques2[4][0] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[4][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[4][1] == 0) {
                        ques2[4][1] = (ques2[4][1] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[4][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[4][2] == 0) {
                        ques2[4][2] = (ques2[4][2] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[4][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[4][3] == 0) {
                        ques2[4][3] = (ques2[4][3] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[4][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[4][4] == 0) {
                        ques2[4][4] = (ques2[4][4] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[4][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[4][5] == 0) {
                        ques2[4][5] = (ques2[4][5] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[4][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[4][6] == 0) {
                        ques2[4][6] = (ques2[4][6] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[4][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[4][7] == 0) {
                        ques2[4][7] = (ques2[4][7] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[4][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[4][8] == 0) {
                        ques2[4][8] = (ques2[4][8] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[5][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[5][0] == 0) {
                        ques2[5][0] = (ques2[5][0] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[5][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[5][1] == 0) {
                        ques2[5][1] = (ques2[5][1] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[5][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[5][2] == 0) {
                        ques2[5][2] = (ques2[5][2] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[5][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[5][3] == 0) {
                        ques2[5][3] = (ques2[5][3] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }

            }
        });

        B[5][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[5][4] == 0) {
                        ques2[5][4] = (ques2[5][4] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[5][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[5][5] == 0) {
                        ques2[5][5] = (ques2[5][5] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[5][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[5][6] == 0) {
                        ques2[5][6] = (ques2[5][6] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[5][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[5][7] == 0) {
                        ques2[5][7] = (ques2[5][7] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[5][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[5][8] == 0) {
                        ques2[5][8] = (ques2[5][8] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[6][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[6][0] == 0) {
                        ques2[6][0] = (ques2[6][0] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[6][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[6][1] == 0) {
                        ques2[6][1] = (ques2[6][1] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[6][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[6][2] == 0) {
                        ques2[6][2] = (ques2[6][2] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[6][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[6][3] == 0) {
                        ques2[6][3] = (ques2[6][3] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[6][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[6][4] == 0) {
                        ques2[6][4] = (ques2[6][4] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[6][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[6][5] == 0) {
                        ques2[6][5] = (ques2[6][5] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[6][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[6][6] == 0) {
                        ques2[6][6] = (ques2[6][6] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[6][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[6][7] == 0) {
                        ques2[6][7] = (ques2[6][7] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[6][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[6][8] == 0) {
                        ques2[6][8] = (ques2[6][8] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[7][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[7][0] == 0) {
                        ques2[7][0] = (ques2[7][0] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[7][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[7][1] == 0) {
                        ques2[7][1] = (ques2[7][1] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[7][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[7][2] == 0) {
                        ques2[7][2] = (ques2[7][2] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[7][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[7][3] == 0) {
                        ques2[7][3] = (ques2[7][3] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[7][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[7][4] == 0) {
                        ques2[7][4] = (ques2[7][4] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[7][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[7][5] == 0) {
                        ques2[7][5] = (ques2[7][5] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[7][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[7][6] == 0) {
                        ques2[7][6] = (ques2[7][6] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[7][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[7][7] == 0) {
                        ques2[7][7] = (ques2[7][7] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[7][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[7][8] == 0) {
                        ques2[7][8] = (ques2[7][8] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[8][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[8][0] == 0) {
                        ques2[8][0] = (ques2[8][0] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[8][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[8][1] == 0) {
                        ques2[8][1] = (ques2[8][1] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[8][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[8][2] == 0) {
                        ques2[8][2] = (ques2[8][2] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
        B[8][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[8][3] == 0) {
                        ques2[8][3] = (ques2[8][3] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[8][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[8][4] == 0) {
                        ques2[8][4] = (ques2[8][4] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[8][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[8][5] == 0) {
                        ques2[8][5] = (ques2[8][5] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[8][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[8][6] == 0) {
                        ques2[8][6] = (ques2[8][6] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[8][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[8][7] == 0) {
                        ques2[8][7] = (ques2[8][7] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });

        B[8][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted==1) {
                    if (ques12[8][8] == 0) {
                        ques2[8][8] = (ques2[8][8] + 1) % 10;
                        display2(ques2);
                    }
                    remarks.setText("");
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(matchstarted==0)
            super.onBackPressed();
        else
        {
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity3.this);
            alertdialog.setTitle("EXIT SESSION");
            alertdialog.setMessage("Do you want to exit in between the match?");
            alertdialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    //execution=0;
                    //onBackPressed();
                    MainActivity3.super.onBackPressed();
                }
            });

            alertdialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            alertdialog.show();
        }
    }
    void reset()
    {
        ques1=new int[9][9];
        ques2=new int[9][9];
        ques12=new int[9][9];
        display2(ques1);
        matchstarted=0;
        flag2=0;
        flag3=0;
        t1=0;
        t2=0;

    }
    void copysudoku(int arr1[][], int arr2[][])
    {
        int i,j;
        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                arr2[i][j]=arr1[i][j];
            }
        }
    }
    void showquestion(int n)
    {
        int i,j,k=0;
        int arrtemp[]=new int[81];
        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                arrtemp[k]=ques1[i][j];
                k++;
            }
        }

        for(i=1;i<=n;i++)
        {
            k=(int)(0+(Math.random()*81));
            if(arrtemp[k]==0)
            {
                i--;
            }
            else
            {
                arrtemp[k]=0;
            }

        }
        k=0;
        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                ques12[i][j]=arrtemp[k];
                k++;
            }
        }

    }

    void generate1()
    {
        ques1= new int[9][9];
        fillValues();
    }

    void fillDiagonal2()
    {
        int i;
        for(i=0;i<9;i++)
        {
            if(i==4)
                continue;
            fillBox(i,9-i-1);
        }
    }

    public void fillValues()
    {
        fillDiagonal();
        fillRemaining(0, SRN);
    }
    void fillDiagonal()
    {

        for (int i = 0; i<N; i=i+SRN)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }
    boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i<SRN; i++)
            for (int j = 0; j<SRN; j++)
                if (ques1[rowStart+i][colStart+j]==num)
                    return false;
        return true;
    }
    void fillBox(int row,int col)
    {
        int num;
        for (int i=0; i<SRN; i++)
        {
            for (int j=0; j<SRN; j++)
            {
                do
                {
                    num = (int) Math.floor((Math.random()*N+1));
                }
                while (!unUsedInBox(row, col, num));
                ques1[row+i][col+j] = num;
            }
        }
    }
    boolean CheckIfSafe(int i,int j,int num)
    {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i-i%SRN, j-j%SRN, num));
    }
    boolean unUsedInRow(int i,int num)
    {
        for (int j = 0; j<N; j++)
            if (ques1[i][j] == num)
                return false;
        return true;
    }
    boolean unUsedInCol(int j,int num)
    {
        for (int i = 0; i<N; i++)
            if (ques1[i][j] == num)
                return false;
        return true;
    }
    boolean fillRemaining(int i, int j)
    {
        //  System.out.println(i+" "+j);
        if (j>=N && i<N-1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>=N && j>=N)
            return true;
        if (i < SRN)
        {
            if (j < SRN)
                j = SRN;
        }
        else if (i < N-SRN)
        {
            if (j==(int)(i/SRN)*SRN)
                j =  j + SRN;
        }
        else
        {
            if (j == N-SRN)
            {
                i = i + 1;
                j = 0;
                if (i>=N)
                    return true;
            }
        }
        for (int num = 1; num<=N; num++)
        {
            if (CheckIfSafe(i, j, num))
            {
                ques1[i][j] = num;
                if (fillRemaining(i, j+1))
                    return true;

                ques1[i][j] = 0;
            }
        }
        return false;
    }
    private boolean check()
    {
        int i,j;
        for(i=0;i<9;i++)
        {
            if(rowcheck(ques1[i])==false)
                return false;
        }

        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                ques12[j][i]=ques1[i][j];
            }
        }
        for(i=0;i<9;i++)
        {
            if(rowcheck(ques12[i])==false)
                return false;
        }

        int arr2[]= new int[9];
        int k=0;
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++,k++)
            {
                arr2[k]=ques1[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=0;i<3;i++)
        {
            for(j=3;j<6;j++,k++)
            {
                arr2[k]=ques1[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=0;i<3;i++)
        {
            for(j=6;j<9;j++,k++)
            {
                arr2[k]=ques1[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=3;i<6;i++)
        {
            for(j=0;j<3;j++,k++)
            {
                arr2[k]=ques1[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=3;i<6;i++)
        {
            for(j=3;j<6;j++,k++)
            {
                arr2[k]=ques1[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=3;i<6;i++)
        {
            for(j=6;j<9;j++,k++)
            {
                arr2[k]=ques1[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=6;i<9;i++)
        {
            for(j=0;j<3;j++,k++)
            {
                arr2[k]=ques1[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=6;i<9;i++)
        {
            for(j=3;j<6;j++,k++)
            {
                arr2[k]=ques1[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=6;i<9;i++)
        {
            for(j=6;j<9;j++,k++)
            {
                arr2[k]=ques1[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;

        return true;

    }
    private boolean check2()
    {
        int i,j;
        for(i=0;i<9;i++)
        {
            if(rowcheck(ques2[i])==false)
                return false;
        }

        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                ques22[j][i]=ques1[i][j];
            }
        }
        for(i=0;i<9;i++)
        {
            if(rowcheck(ques22[i])==false)
                return false;
        }

        int arr2[]= new int[9];
        int k=0;
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++,k++)
            {
                arr2[k]=ques2[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=0;i<3;i++)
        {
            for(j=3;j<6;j++,k++)
            {
                arr2[k]=ques2[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=0;i<3;i++)
        {
            for(j=6;j<9;j++,k++)
            {
                arr2[k]=ques2[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=3;i<6;i++)
        {
            for(j=0;j<3;j++,k++)
            {
                arr2[k]=ques2[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=3;i<6;i++)
        {
            for(j=3;j<6;j++,k++)
            {
                arr2[k]=ques2[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=3;i<6;i++)
        {
            for(j=6;j<9;j++,k++)
            {
                arr2[k]=ques2[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=6;i<9;i++)
        {
            for(j=0;j<3;j++,k++)
            {
                arr2[k]=ques2[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=6;i<9;i++)
        {
            for(j=3;j<6;j++,k++)
            {
                arr2[k]=ques2[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=6;i<9;i++)
        {
            for(j=6;j<9;j++,k++)
            {
                arr2[k]=ques2[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;

        return true;

    }
    private boolean isempty(int arr[][])
    {
        int i,j;
        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                if(arr[i][j]==0)
                {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean diagonalcheck(int arr[][])
    {
        int i,j,k=0;
        int a1[]= new int[9];
        int a2[] = new int[9];
        for(i=0;i<9;i++)
        {
            a1[k]=arr[i][i];
            a2[k]=arr[i][9-i-1];
            k++;
        }

        return rowcheck(a1) && rowcheck(a2);
    }
    private boolean rowcheck(int arr[])
    {
        int k,c=0, i;
        for(k=1;k<=9;k++)
        {
            c=0;
            for(i=0;i<9;i++)
            {
                if(arr[i]==k)
                    c++;

            }
            if(c!=1)
                return false;
        }
        return true;
    }
    private void display1()
    {
        int i,j;
        String disp="";
        //txtview.setText("");
        remarks.setText("SUCCESFULLY GENERATED");
        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                if(ques1[i][j]!=0)
                {
                    //disp=Integer.toString(grid[i][j]);
                    //SpannableString ss = new SpannableString(disp);
                    //ss.setSpan(fcsblack, 0, disp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //B[i][j].setText(ss);

                    B[i][j].setText(Integer.toString(ques1[i][j]));
                }

                else
                {
                    B[i][j].setText("");
                }

            }
        }
    }
    private void display2(int arr[][])
    {
        int i,j;
        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                if(arr[i][j]!=0)
                {
                    //disp=Integer.toString(grid[i][j]);
                    //SpannableString ss = new SpannableString(disp);
                    //ss.setSpan(fcsblack, 0, disp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //B[i][j].setText(ss);

                    B[i][j].setText(Integer.toString(arr[i][j]));
                }

                else
                {
                    B[i][j].setText("");
                }

            }
        }

    }
    private void setupUIviews()
    {
        autoCompleteTextView = findViewById(R.id.autocompletetextview);
        autoCompleteTextView2 = findViewById(R.id.autocompletetextview2);

        B[0][0]=findViewById(R.id.b1);
        B[0][1]=findViewById(R.id.b2);
        B[0][2]=findViewById(R.id.b3);
        B[0][3]=findViewById(R.id.b4);
        B[0][4]=findViewById(R.id.b5);
        B[0][5]=findViewById(R.id.b6);
        B[0][6]=findViewById(R.id.b7);
        B[0][7]=findViewById(R.id.b8);
        B[0][8]=findViewById(R.id.b9);

        B[1][0]=findViewById(R.id.b10);
        B[1][1]=findViewById(R.id.b11);
        B[1][2]=findViewById(R.id.b12);
        B[1][3]=findViewById(R.id.b13);
        B[1][4]=findViewById(R.id.b14);
        B[1][5]=findViewById(R.id.b15);
        B[1][6]=findViewById(R.id.b16);
        B[1][7]=findViewById(R.id.b17);
        B[1][8]=findViewById(R.id.b18);

        B[2][0]=findViewById(R.id.b19);
        B[2][1]=findViewById(R.id.b20);
        B[2][2]=findViewById(R.id.b21);
        B[2][3]=findViewById(R.id.b22);
        B[2][4]=findViewById(R.id.b23);
        B[2][5]=findViewById(R.id.b24);
        B[2][6]=findViewById(R.id.b25);
        B[2][7]=findViewById(R.id.b26);
        B[2][8]=findViewById(R.id.b27);

        B[3][0]=findViewById(R.id.b28);
        B[3][1]=findViewById(R.id.b29);
        B[3][2]=findViewById(R.id.b30);
        B[3][3]=findViewById(R.id.b31);
        B[3][4]=findViewById(R.id.b32);
        B[3][5]=findViewById(R.id.b33);
        B[3][6]=findViewById(R.id.b34);
        B[3][7]=findViewById(R.id.b35);
        B[3][8]=findViewById(R.id.b36);

        B[4][0]=findViewById(R.id.b37);
        B[4][1]=findViewById(R.id.b38);
        B[4][2]=findViewById(R.id.b39);
        B[4][3]=findViewById(R.id.b40);
        B[4][4]=findViewById(R.id.b41);
        B[4][5]=findViewById(R.id.b42);
        B[4][6]=findViewById(R.id.b43);
        B[4][7]=findViewById(R.id.b44);
        B[4][8]=findViewById(R.id.b45);

        B[5][0]=findViewById(R.id.b46);
        B[5][1]=findViewById(R.id.b47);
        B[5][2]=findViewById(R.id.b48);
        B[5][3]=findViewById(R.id.b49);
        B[5][4]=findViewById(R.id.b50);
        B[5][5]=findViewById(R.id.b51);
        B[5][6]=findViewById(R.id.b52);
        B[5][7]=findViewById(R.id.b53);
        B[5][8]=findViewById(R.id.b54);

        B[6][0]=findViewById(R.id.b55);
        B[6][1]=findViewById(R.id.b56);
        B[6][2]=findViewById(R.id.b57);
        B[6][3]=findViewById(R.id.b58);
        B[6][4]=findViewById(R.id.b59);
        B[6][5]=findViewById(R.id.b60);
        B[6][6]=findViewById(R.id.b61);
        B[6][7]=findViewById(R.id.b62);
        B[6][8]=findViewById(R.id.b63);

        B[7][0]=findViewById(R.id.b64);
        B[7][1]=findViewById(R.id.b65);
        B[7][2]=findViewById(R.id.b66);
        B[7][3]=findViewById(R.id.b67);
        B[7][4]=findViewById(R.id.b68);
        B[7][5]=findViewById(R.id.b69);
        B[7][6]=findViewById(R.id.b70);
        B[7][7]=findViewById(R.id.b71);
        B[7][8]=findViewById(R.id.b72);

        B[8][0]=findViewById(R.id.b73);
        B[8][1]=findViewById(R.id.b74);
        B[8][2]=findViewById(R.id.b75);
        B[8][3]=findViewById(R.id.b76);
        B[8][4]=findViewById(R.id.b77);
        B[8][5]=findViewById(R.id.b78);
        B[8][6]=findViewById(R.id.b79);
        B[8][7]=findViewById(R.id.b80);
        B[8][8]=findViewById(R.id.b81);

        generate=findViewById(R.id.button2);
        start=findViewById(R.id.button3);
        check=findViewById(R.id.button4);
        
        remarks=findViewById(R.id.textView3);
    }

    //generate diagonal
    void generate2()
    {
        ques1=new int[9][9];
        fillDiagonals2();
        solution2(ques1,9);
        //printSudoku();

    }
    void fillDiagonals2()
    {
        int arr1[]= new int[9];
        int arr2[]= new int[9];

        int i,j;
        while(true)
        {
            //System.out.println("ARRAY 1");
            for(i=0;i<9;i++)
            {
                arr1[i]=(int)(Math.random()*10);
                if(i==8)
                {
                    if(rowcheck2(arr1)==false)
                    {
                        i=-1;
                    }
                    else
                        break;
                }
            }
            /*
            for(i=0;i<9;i++)
            {
                System.out.println(arr1[i]);
            }
            System.out.println("ARRAY 2");
            */
            for(i=0;i<9;i++)
            {
                arr2[i]=(int)(Math.random()*10);
                if(i==8)
                {
                    if(rowcheck2(arr2)==false)
                    {
                        i=-1;
                    }
                    else
                        break;

                }
            }

            /*
            for(i=0;i<9;i++)
            {
            System.out.println(arr2[i]);
            }
            */


            if(checkdiagonalsmatrix2(arr1,arr2))
            {
                break;
            }
        }
        /*
        System.out.println("array1");
        for(i=0;i<9;i++)
            {
                System.out.println(arr1[i]);
            }
            System.out.println("array2");
            for(i=0;i<9;i++)
            {
            System.out.println(arr2[i]);
            }
            */
        for(i=0;i<9;i++)
        {
            ques1[i][i]=arr1[i];
            ques1[i][9-i-1]=arr2[i];
        }

        //printSudoku();

    }
    boolean checkdiagonalsmatrix2(int arr1[], int arr2[])
    {
        int i,j;
        if(arr1[4]!=arr2[4])
            return false;
        for(i=0;i<9;i++)
        {
            if(i==4)
            {
                if(arr1[4]!=arr2[4])
                    return false;
            }
            else if((arr1[i]!=arr2[i])&& (arr1[9-i-1]!=arr2[i]))
            {

            }
            else
                return false;
        }
        return true;
    }
    private boolean rowcheck2(int arr[])
    {
        int k,c=0, i;
        for(k=1;k<=9;k++)
        {
            c=0;
            for(i=0;i<9;i++)
            {
                if(arr[i]==k)
                    c++;

            }
            if(c!=1)
                return false;
        }
        return true;
    }
    public void printSudoku()
    {
        for (int i = 0; i<9; i++)
        {
            System.out.print("[");
            for (int j = 0; j<9; j++)
                System.out.print(ques1[i][j] + " ");
            System.out.print("]");
            System.out.println();
        }
        System.out.println();
    }
    boolean solution2(int[][] puzzle, int n)
    {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (puzzle[i][j] == 0)
                {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }
        // No empty space left
        if (isEmpty)
        {
            return true;
        }
        // Else backtrack for each-row
        for (int number = 1; number <= n; number++)
        {
            if (isValid2(puzzle, row, col, number))
            {
                puzzle[row][col] = number;
                if (solution2(puzzle, n))
                {
                    // print(puzzle, n);
                    return true;
                }
                else
                {
                    // replace it
                    puzzle[row][col] = 0;
                }
            }
        }
        return false;
    }
    boolean isValid2(int[][] puzzle,int row, int col,int number)
    {
        // Row has the unique
        for (int d = 0; d < puzzle.length; d++)
        {

			/* If the number we are trying to
			 place is already present in
			 that row, then return false;*/
            if (puzzle[row][d] == number) {
                return false;
            }
        }
        // Column has the unique numberbers
        for (int r = 0; r < puzzle.length; r++)
        {
			 /* If the number we are trying to
			 place is already present in
			 that column, then return false;*/
            if (puzzle[r][col] == number)
            {
                return false;
            }
        }

        //Corresponding square has unique number
        int sqrt = (int)Math.sqrt(puzzle.length);
        int bRow = row - row % sqrt;
        int bCol = col - col % sqrt;
        for (int r = bRow;
             r < bRow + sqrt; r++)
        {
            for (int d = bCol;
                 d < bCol + sqrt; d++)
            {
                if (puzzle[r][d] == number)
                {
                    return false;
                }
            }
        }
        // if there is no clash, it's safe
        return true;
    }
}