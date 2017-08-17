package exampleprilognew.ru.iceandfire2.screen.BookDetail;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import exampleprilognew.ru.iceandfire2.R;
import exampleprilognew.ru.iceandfire2.model.Book;
import exampleprilognew.ru.iceandfire2.screen.BooksList.BookFragment;
import exampleprilognew.ru.iceandfire2.screen.BookListCharacters.BookListCharacterActivity;

/**
 * Created by Максим on 08.08.2017.
 */

public class BookDetailFragment extends Fragment {

    private Button btnListOfCharacters;
    private Typeface typeBody;
    private Book detailBook;
    public static final String CHARACTERS="characters of book";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      return inflater.inflate(R.layout.fragment_detail_book, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadDetailDataForBook(view);

        launchListener(view);

    }



    private void launchListener(View view){
        btnListOfCharacters=(Button)view.findViewById(R.id.tvHerous);

        btnListOfCharacters.setOnClickListener(listHerous);
    }




    private void loadDetailDataForBook(View view) {

        Intent intentBook = getActivity().getIntent();

      detailBook = (Book)intentBook.getSerializableExtra(BookFragment.BOOK);

        typeBody = Typeface.createFromAsset(getResources().getAssets(),"RobotoTTF/Roboto-Medium.ttf");

TextView tvAuthors=(TextView)view.findViewById(R.id.tvAuthors);
TextView tvPublisher=(TextView)view.findViewById(R.id.tvPublisher);
TextView tvCountry=(TextView)view.findViewById(R.id.tvCountry);
TextView tvMediatype=(TextView)view.findViewById(R.id.tvMediatype);
        tvAuthors.setTypeface(typeBody);
        tvPublisher.setTypeface(typeBody);
                tvCountry.setTypeface(typeBody);
        tvMediatype.setTypeface(typeBody);

        tvAuthors.setText((detailBook.getAuthors()));
        tvPublisher.setText(detailBook.getPublisher());

        tvCountry.setText(detailBook.getCountry());
        tvMediatype.setText(detailBook.getMediaType());
    }

    private View.OnClickListener listHerous = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            launchListHerous();

        }
    };

   public void launchListHerous(){
       Intent intentListCharacters=new Intent(getContext(),BookListCharacterActivity.class);

       intentListCharacters.putExtra(CHARACTERS, detailBook.getCharactersList());

       startActivity(intentListCharacters);

   }

}
