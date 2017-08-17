package exampleprilognew.ru.iceandfire2.screen.BookListCharacters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import exampleprilognew.ru.iceandfire2.R;
import exampleprilognew.ru.iceandfire2.screen.BookDetail.BookDetailFragment;

/**
 * Created by Максим on 11.08.2017.
 */

public class BookListCharacterActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_character);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_list_character);
        setSupportActionBar(toolbar);

        toolbar.setSubtitle(getKolCharacters());

    }

private String getKolCharacters(){
    Intent intentListCharacters = getIntent();
    String characters = intentListCharacters.getStringExtra(BookDetailFragment.CHARACTERS);
    String[] massDescription = characters.split(" ");

    String titleToolbar = getResources().getQuantityString(R.plurals.name_toolbar,massDescription.length,massDescription.length);

    return titleToolbar;

}

}