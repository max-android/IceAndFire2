package exampleprilognew.ru.iceandfire2.screen.BooksList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.MalformedURLException;
import java.net.URL;

import exampleprilognew.ru.iceandfire2.R;
import exampleprilognew.ru.iceandfire2.model.Book;
import exampleprilognew.ru.iceandfire2.model.BookService;
import exampleprilognew.ru.iceandfire2.model.database.DatabaseManager;
import exampleprilognew.ru.iceandfire2.model.database.DatabaseService;
import exampleprilognew.ru.iceandfire2.screen.BookDetail.BookDetailActivity;
import exampleprilognew.ru.iceandfire2.screen.ProgressManager;


public class BookFragment extends Fragment implements BookCallBack,BookAdapter.BookClickListener {

    public static final String URL="https://anapioficeandfire.com/api/books";
    public static final String BOOK="detail of book";

    private RecyclerView recyclerViewBook;
    private DatabaseManager databaseManager;
    private ProgressManager progressManager;
    private BookService bookService;

    public BookFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


            createRecyclerView(view);
            createProgressDialog();
            createDB();

        launchBookList();
    }

    private void launchBookList(){

        if(databaseManager.getCursor().getCount()==0){

                bookService = new BookService(getContext(),BookFragment.this);

                try {
                bookService.execute(new URL(URL));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }else{
            //databaseManager.deleteBD();
            DatabaseService databaseService=new DatabaseService(BookFragment.this);
            databaseService.execute();
        }
    }

    private void cancelBookService() {

        bookService.cancel(true);

        bookService=null;

    }

    private void createRecyclerView(View view) {
        recyclerViewBook = (RecyclerView)view.findViewById(R.id.recyclerViewBook);

        recyclerViewBook.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewBook.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }

    private void createProgressDialog(){
        progressManager = new ProgressManager(getContext());

    }

    private void createDB(){
        databaseManager = new DatabaseManager(getContext());

    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerViewBook;
    }

    @Override
    public ProgressManager getProgressDialog() {
        return  progressManager;
    }

    @Override
    public DatabaseManager getDB() {
        return databaseManager;
    }

    @Override
    public BookFragment getBF() {
        return this;
    }


    @Override
    public void onPause() {
        super.onPause();

        if (bookService!=null && bookService.getStatus()== AsyncTask.Status.RUNNING) {
            cancelBookService();
        }

    }



    @Override
    public void onBookClick(Book book) {

           launchDetailData(book);

    }

    private void launchDetailData(Book book) {

        Intent intentDetailBook=new Intent(getContext(),BookDetailActivity.class);

        intentDetailBook.putExtra(BOOK,book);

        startActivity(intentDetailBook);

    }


}
