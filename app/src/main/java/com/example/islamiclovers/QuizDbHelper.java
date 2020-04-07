package com.example.islamiclovers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.islamiclovers.QuizContract.QuestionTable;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "GoQuiz.db";
   private static final int DATBASE_VERSION = 1;

    private SQLiteDatabase db;


    QuizDbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATBASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);

    }


    private void fillQuestionsTable()
    {

        Questions q1 = new Questions(" سوتے وقت کی دعا ہے","اَللّٰھُمَّ بِاسْمِكَ أَمُوتُ وَأَحْيَا",
                "اَللّٰهُمَّ اِنِّيْ اَسْاَلُکَ مِنْ فَضْلِکَ وَرَحْمَتِک","اَللّٰھُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْخُبُثِ وَالْخَبَائِث",
                "لَا إِلٰهَ إِلَّا أَنْتَ سُبْحَانَكَ إِنِّي كُنْتُ مِنَ الظَّالِمِين",1);
        addQuestions(q1);


        Questions q2 = new Questions(" وضو کرتے وقت مسواک کرنا کیا ہے?","فرض",
                "واجب","سنت","ان میں سے کوئی بھی نہیں",3);
        addQuestions(q2);

        Questions q3 = new Questions("سر میں تیل لگانے سے پہلے کیا پڑھنا چاییے",
                "تعوذ","تسمیہ","پہلا کلمہ","سورہ اخلاص",2);
        addQuestions(q3);


        Questions q4 = new Questions("کس اُنگلی سے ناخن تراشنے شروع کرنے چاہیے ?",
                "انگوٹھے سے","درمیانی انگلی سے","چھوٹی اُنگلی سے","شہادت والی اُنگلی سے",4);
        addQuestions(q4);


    Questions q5 = new Questions("ایک آنکھ میں سرمہ کی کتنی سلائی لگانی چاہیے  ",
            "ایک","دو","تین","چار",3);
        addQuestions(q5);


        Questions q6 = new Questions("بیت الخلا سے باہر آنے کی دعا ہے",
                "اَللّٰھُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْخُبُثِ وَالْخَبَائِث","اَلْحَمْدُ لِلّٰہِ الَّذِي أَذْهَبَ عَنِّي الْأَذَى وَعَافَانِي",
                "إِنَّا لِلهِ وَ إِنَّا إِلَيْهِ رَاجِعُوْن","ان میں سے کوئی بھی نہیں",2);
        addQuestions(q6);
    }

    private void addQuestions(Questions question){

        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        db.insert(QuestionTable.TABLE_NAME,null,cv);

    }

    public ArrayList<Questions> getAllQuestions() {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR
        };



            Cursor c = db.query(QuestionTable.TABLE_NAME,
                    Projection,
                    null,
                    null,
                    null,
                    null,
                    null);


            if (c.moveToFirst()) {
                do {

                    Questions question = new Questions();
                    question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                    question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));

                    questionList.add(question);

                } while (c.moveToNext());

            }
            c.close();
            return questionList;

        }

    }


