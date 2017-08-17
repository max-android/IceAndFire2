package exampleprilognew.ru.iceandfire2.screen.BookListCharacters;

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

import java.util.ArrayList;
import java.util.List;

import exampleprilognew.ru.iceandfire2.R;
import exampleprilognew.ru.iceandfire2.model.BookCharctersService;
import exampleprilognew.ru.iceandfire2.screen.BookDetail.BookDetailFragment;
import exampleprilognew.ru.iceandfire2.screen.ProgressManager;

/**
 * Created by Максим on 11.08.2017.
 */


public class BookListCharacterFragment extends Fragment implements BookListCharactersCallBack {

    private RecyclerView recyclerViewChracters;
    private ProgressManager progressManager;
    private BookCharctersService bookCharctersService;
    private int totalItemCount;


    public BookListCharacterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_book_list_character, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createRecyclerViewChracters(view);

        createProgressDialog();

        launchBookListCharacters();
    }

    private void launchBookListCharacters(){

      bookCharctersService=new BookCharctersService(getContext(), BookListCharacterFragment.this);

        bookCharctersService.execute();

    }


    private List<String> loadCharacters() {

        Intent intentListCharacters = getActivity().getIntent();
        String characters = intentListCharacters.getStringExtra(BookDetailFragment.CHARACTERS);

        List<String> listCharacters = getListCharacters(characters);

        return listCharacters;
    }

    private List<String> getListCharacters(String characters) {

        String[] massDescription = characters.split(" ");

        List<String> listDescriptionBook = new ArrayList<>();

        for (int i = 0; i < massDescription.length; i++) {

            listDescriptionBook.add(massDescription[i]);
        }

        return listDescriptionBook;
    }


    private void createRecyclerViewChracters(View view) {
        recyclerViewChracters = (RecyclerView) view.findViewById(R.id.recyclerCharacters);
        recyclerViewChracters.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewChracters.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerViewChracters;
    }

    @Override
    public List<String> getUrlCharacters() {
        return loadCharacters();
    }

    @Override
    public ProgressManager getProgressDialog() {
        return progressManager;
    }

    @Override
    public int getNumberOfcharacters() {
        return loadCharacters().size();
    }



    private void createProgressDialog(){
        progressManager = new ProgressManager(getContext());

    }
    @Override
    public void onPause() {
        super.onPause();

        if (bookCharctersService!=null && bookCharctersService.getStatus()== AsyncTask.Status.RUNNING) {
            cancelBookCharctersService();
        }
    }

    private void cancelBookCharctersService() {

        bookCharctersService.cancel(true);

        bookCharctersService=null;
    }
}