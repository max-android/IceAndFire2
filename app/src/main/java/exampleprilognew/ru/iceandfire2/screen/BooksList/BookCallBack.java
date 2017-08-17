package exampleprilognew.ru.iceandfire2.screen.BooksList;

import android.support.v7.widget.RecyclerView;

import exampleprilognew.ru.iceandfire2.model.database.DatabaseManager;
import exampleprilognew.ru.iceandfire2.screen.ProgressManager;

/**
 * Created by Максим on 31.07.2017.
 */



public interface BookCallBack {

    RecyclerView getRecyclerView();

    ProgressManager getProgressDialog();

    DatabaseManager getDB();

    BookFragment getBF();

}
