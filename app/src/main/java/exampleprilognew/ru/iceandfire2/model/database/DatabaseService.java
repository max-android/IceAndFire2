package exampleprilognew.ru.iceandfire2.model.database;

import android.database.Cursor;
import android.os.AsyncTask;

import exampleprilognew.ru.iceandfire2.model.BookService;
import exampleprilognew.ru.iceandfire2.screen.BooksList.BookAdapter;
import exampleprilognew.ru.iceandfire2.screen.BooksList.BookCallBack;


public class DatabaseService extends AsyncTask<Void,Cursor,Cursor> {

    private BookCallBack bookCallBack;

    public DatabaseService(BookCallBack bookCallBack) {

        this.bookCallBack=bookCallBack;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        bookCallBack.getProgressDialog().showDialog(BookService.TITLEBOOK,BookService.NUMBERBOOKS);

    }

    @Override
    protected Cursor doInBackground(Void... params) {

        return bookCallBack.getDB().getCursor();
    }

    @Override
    protected void onProgressUpdate(Cursor... cursor) {
        super.onProgressUpdate(cursor);

        bookCallBack.getProgressDialog().showProgress(cursor.toString());
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);

        bookCallBack.getRecyclerView().setAdapter(new BookAdapter(bookCallBack.getDB().getDataFromBD(cursor),bookCallBack.getBF()));
        bookCallBack.getProgressDialog().dismissDialog();
        cursor.close();

    }

}
