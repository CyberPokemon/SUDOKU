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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    Button B[][]= new Button[9][9];
    Button CHECK, RESET,SOLVE;
    TextView txtview;
    String errormessage="";
    int matchstarted=0;

    int grid[][]= new int[9][9];
    int grid2[][]= new int[9][9];
    int grid3[][]= new int[9][9];
    ForegroundColorSpan fcsred = new ForegroundColorSpan(Color.RED);
    ForegroundColorSpan fcsgreen = new ForegroundColorSpan(Color.GREEN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setupUIviews();
        initialize();

        RESET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchstarted!=0)
                {
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity4.this);
                    alertdialog.setTitle("RESET SUDOKU");
                    alertdialog.setMessage("Do you want to RESET sudoku? All your progress will be lost.");
                    alertdialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            initialize();
                            display();
                            matchstarted=0;

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

                //alertdialog box
            }
        });
        SOLVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (matchstarted > 0)
                {
                    copysudoku(grid, grid3);
                    if(filled()==true)
                    {
                        errormessage = "ALL CELLS ARE FILLED. PRESS CHECK BUTTON";
                        SpannableString ss = new SpannableString(errormessage);
                        ss.setSpan(fcsred, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        txtview.setText(ss);
                    }
                    else if(check2()==false)
                    {
                        errormessage = "INVALID SUDOKU";
                        SpannableString ss = new SpannableString(errormessage);
                        ss.setSpan(fcsred, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        txtview.setText(ss);
                    }
                    else if (solution2(grid3, 9)) {
                        display2();
                        errormessage = "SOLVED SUDOKU DISPLAYED";
                        SpannableString ss = new SpannableString(errormessage);
                        ss.setSpan(fcsgreen, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        txtview.setText(ss);
                        matchstarted = -1;
                        copysudoku(grid3,grid);
                    } else {
                        errormessage = "INVALID SUDOKU";
                        SpannableString ss = new SpannableString(errormessage);
                        ss.setSpan(fcsred, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        txtview.setText(ss);
                    }

                }
                else
                {
                    Toast.makeText(MainActivity4.this, "INPUT YOUR SUDOKU", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CHECK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (matchstarted > 0) {
                    if (filled() == false) {
                        errormessage = "ALL CELLS NOT FILLED";
                        SpannableString ss = new SpannableString(errormessage);
                        ss.setSpan(fcsred, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        txtview.setText(ss);
                    } else {
                        if (check()) {
                            errormessage = "SUDOKU IS SOLVED";
                            SpannableString ss = new SpannableString(errormessage);
                            ss.setSpan(fcsgreen, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            txtview.setText(ss);
                            matchstarted = 0;
                        } else {
                            errormessage = "SUDOKU IS NOT SOLVED";
                            SpannableString ss = new SpannableString(errormessage);
                            ss.setSpan(fcsred, 0, errormessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            txtview.setText(ss);
                        }

                    }
                }
                else
                {
                    Toast.makeText(MainActivity4.this, "INPUT YOUR SUDOKU", Toast.LENGTH_SHORT).show();
                }
            }
        });


        B[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                grid[0][0]=(grid[0][0]+1)%10;
                //Toast.makeText(MainActivity4.this, "confirmed", Toast.LENGTH_SHORT).show();
                display();
                matchstarted=1;
            }
        });
        B[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[0][1]=(grid[0][1]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[0][2]=(grid[0][2]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[0][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[0][3]=(grid[0][3]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[0][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[0][4]=(grid[0][4]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[0][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[0][5]=(grid[0][5]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[0][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[0][6]=(grid[0][6]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[0][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[0][7]=(grid[0][7]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[0][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[0][8]=(grid[0][8]+1)%10;
                display();
                matchstarted=1;
            }
        });


        B[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[1][0]=(grid[1][0]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[1][1]=(grid[1][1]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[1][2]=(grid[1][2]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[1][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[1][3]=(grid[1][3]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[1][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[1][4]=(grid[1][4]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[1][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[1][5]=(grid[1][5]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[1][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[1][6]=(grid[1][6]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[1][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[1][7]=(grid[1][7]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[1][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[1][8]=(grid[1][8]+1)%10;
                display();
                matchstarted=1;
            }
        });

        B[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[2][0]=(grid[2][0]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[2][1]=(grid[2][1]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[2][2]=(grid[2][2]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[2][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[2][3]=(grid[2][3]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[2][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[2][4]=(grid[2][4]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[2][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[2][5]=(grid[2][5]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[2][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[2][6]=(grid[2][6]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[2][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[2][7]=(grid[2][7]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[2][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[2][8]=(grid[2][8]+1)%10;
                display();
                matchstarted=1;
            }
        });

        B[3][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[3][0]=(grid[3][0]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[3][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[3][1]=(grid[3][1]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[3][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[3][2]=(grid[3][2]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[3][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[3][3]=(grid[3][3]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[3][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[3][4]=(grid[3][4]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[3][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[3][5]=(grid[3][5]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[3][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[3][6]=(grid[3][6]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[3][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[3][7]=(grid[3][7]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[3][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[3][8]=(grid[3][8]+1)%10;
                display();
                matchstarted=1;
            }
        });

        B[4][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[4][0]=(grid[4][0]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[4][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[4][1]=(grid[4][1]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[4][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[4][2]=(grid[4][2]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[4][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[4][3]=(grid[4][3]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[4][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[4][4]=(grid[4][4]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[4][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[4][5]=(grid[4][5]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[4][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[4][6]=(grid[4][6]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[4][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[4][7]=(grid[4][7]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[4][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[4][8]=(grid[4][8]+1)%10;
                display();
                matchstarted=1;
            }
        });

        B[5][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[5][0]=(grid[5][0]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[5][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[5][1]=(grid[5][1]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[5][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[5][2]=(grid[5][2]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[5][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[5][3]=(grid[5][3]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[5][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[5][4]=(grid[5][4]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[5][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[5][5]=(grid[5][5]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[5][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[5][6]=(grid[5][6]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[5][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[5][7]=(grid[5][7]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[5][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[5][8]=(grid[5][8]+1)%10;
                display();
                matchstarted=1;
            }
        });

        B[6][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[6][0]=(grid[6][0]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[6][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[6][1]=(grid[6][1]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[6][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[6][2]=(grid[6][2]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[6][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[6][3]=(grid[6][3]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[6][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[6][4]=(grid[6][4]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[6][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[6][5]=(grid[6][5]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[6][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[6][6]=(grid[6][6]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[6][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[6][7]=(grid[6][7]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[6][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[6][8]=(grid[6][8]+1)%10;
                display();
                matchstarted=1;
            }
        });

        B[7][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[7][0]=(grid[7][0]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[7][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[7][1]=(grid[7][1]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[7][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[7][2]=(grid[7][2]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[7][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[7][3]=(grid[7][3]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[7][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[7][4]=(grid[7][4]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[7][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[7][5]=(grid[7][5]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[7][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[7][6]=(grid[7][6]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[7][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[7][7]=(grid[7][7]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[7][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[7][8]=(grid[7][8]+1)%10;
                display();
                matchstarted=1;
            }
        });

        B[8][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[8][0]=(grid[8][0]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[8][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[8][1]=(grid[8][1]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[8][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[8][2]=(grid[8][2]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[8][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[8][3]=(grid[8][3]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[8][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[8][4]=(grid[8][4]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[8][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[8][5]=(grid[8][5]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[8][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[8][6]=(grid[8][6]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[8][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[8][7]=(grid[8][7]+1)%10;
                display();
                matchstarted=1;
            }
        });
        B[8][8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid[8][8]=(grid[8][8]+1)%10;
                display();
                matchstarted=1;
            }
        });
    }
    public void onBackPressed() {
        if(matchstarted==0)
            super.onBackPressed();
        else
        {
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity4.this);
            alertdialog.setTitle("EXIT SESSION");
            alertdialog.setMessage("Do you want to exit the session?");
            alertdialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    //execution=0;
                    //onBackPressed();
                    MainActivity4.super.onBackPressed();
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

    private void initialize()
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                grid[i][j]=0;
            }
        }
    }

    private void display()
    {
        int i,j;
        String disp="";
        txtview.setText("");
        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                if(grid[i][j]!=0)
                {
                    //disp=Integer.toString(grid[i][j]);
                    //SpannableString ss = new SpannableString(disp);
                    //ss.setSpan(fcsblack, 0, disp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //B[i][j].setText(ss);

                    B[i][j].setText(Integer.toString(grid[i][j]));
                }

                else
                {
                    B[i][j].setText("");
                }

            }
        }
    }
    private void display2()
    {
        int i,j;
        String disp="";
        txtview.setText("");
        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                if(grid3[i][j]!=0)
                {
                    //disp=Integer.toString(grid[i][j]);
                    //SpannableString ss = new SpannableString(disp);
                    //ss.setSpan(fcsblack, 0, disp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //B[i][j].setText(ss);

                    B[i][j].setText(Integer.toString(grid3[i][j]));
                }

                else
                {
                    B[i][j].setText("");
                }

            }
        }
    }

    private boolean filled()
    {
        int i,j;
        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                if(grid[i][j]==0)
                    return false;
            }
        }
        return true;
    }

    private boolean check()
    {
        int i,j;
        for(i=0;i<9;i++)
        {
            if(rowcheck(grid[i])==false)
                return false;
        }

        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                grid2[j][i]=grid[i][j];
            }
        }
        for(i=0;i<9;i++)
        {
            if(rowcheck(grid2[i])==false)
                return false;
        }

        int arr2[]= new int[9];
        int k=0;
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++,k++)
            {
                arr2[k]=grid[i][j];
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
                arr2[k]=grid[i][j];
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
                arr2[k]=grid[i][j];
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
                arr2[k]=grid[i][j];
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
                arr2[k]=grid[i][j];
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
                arr2[k]=grid[i][j];
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
                arr2[k]=grid[i][j];
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
                arr2[k]=grid[i][j];
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
                arr2[k]=grid[i][j];
            }
        }
        if(rowcheck(arr2)==false)
        {
            return false;
        }
        k=0;

        return true;

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
            if(c>1)
                return false;
        }
        return true;
    }
    private boolean check2()
    {
        int i,j;
        for(i=0;i<9;i++)
        {
            if(rowcheck2(grid[i])==false)
                return false;
        }

        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                grid2[j][i]=grid[i][j];
            }
        }
        for(i=0;i<9;i++)
        {
            if(rowcheck2(grid2[i])==false)
                return false;
        }

        int arr2[]= new int[9];
        int k=0;
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++,k++)
            {
                arr2[k]=grid[i][j];
            }
        }
        if(rowcheck2(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=0;i<3;i++)
        {
            for(j=3;j<6;j++,k++)
            {
                arr2[k]=grid[i][j];
            }
        }
        if(rowcheck2(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=0;i<3;i++)
        {
            for(j=6;j<9;j++,k++)
            {
                arr2[k]=grid[i][j];
            }
        }
        if(rowcheck2(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=3;i<6;i++)
        {
            for(j=0;j<3;j++,k++)
            {
                arr2[k]=grid[i][j];
            }
        }
        if(rowcheck2(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=3;i<6;i++)
        {
            for(j=3;j<6;j++,k++)
            {
                arr2[k]=grid[i][j];
            }
        }
        if(rowcheck2(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=3;i<6;i++)
        {
            for(j=6;j<9;j++,k++)
            {
                arr2[k]=grid[i][j];
            }
        }
        if(rowcheck2(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=6;i<9;i++)
        {
            for(j=0;j<3;j++,k++)
            {
                arr2[k]=grid[i][j];
            }
        }
        if(rowcheck2(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=6;i<9;i++)
        {
            for(j=3;j<6;j++,k++)
            {
                arr2[k]=grid[i][j];
            }
        }
        if(rowcheck2(arr2)==false)
        {
            return false;
        }
        k=0;
        for(i=6;i<9;i++)
        {
            for(j=6;j<9;j++,k++)
            {
                arr2[k]=grid[i][j];
            }
        }
        if(rowcheck2(arr2)==false)
        {
            return false;
        }
        k=0;

        return true;

    }



    private void setupUIviews()
    {
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

        CHECK=findViewById(R.id.check);
        RESET= findViewById(R.id.reset);
        SOLVE=findViewById(R.id.solve);
        txtview=findViewById(R.id.textView3);

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
}