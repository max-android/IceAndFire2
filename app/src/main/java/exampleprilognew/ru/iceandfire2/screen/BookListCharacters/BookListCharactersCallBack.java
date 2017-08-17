package exampleprilognew.ru.iceandfire2.screen.BookListCharacters;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import exampleprilognew.ru.iceandfire2.screen.ProgressManager;


/**
 * Created by Максим on 13.08.2017.
 */

public interface BookListCharactersCallBack {

    RecyclerView getRecyclerView();
    List<String> getUrlCharacters();
    ProgressManager getProgressDialog();
    int getNumberOfcharacters();

}
