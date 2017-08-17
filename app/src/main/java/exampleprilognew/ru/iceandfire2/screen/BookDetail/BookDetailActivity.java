package exampleprilognew.ru.iceandfire2.screen.BookDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import exampleprilognew.ru.iceandfire2.R;

/**
 * Created by Максим on 08.08.2017.
 */

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_book_detail);
        setSupportActionBar(toolbar);
    }

}
