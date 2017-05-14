package id.sch.smktelkom_mlg.privateassignment.xirpl302.appnews;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Beranda extends Fragment {

    private static final String URL_DATA = "https://api.nytimes.com/svc/movies/v2/reviews/search.json?api-key=43af31f8712746fbb0e735d4ee552244";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<News> newslist;


    public Beranda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        LinearLayoutManager layoutManagera
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);


        newslist = new ArrayList<>();


        tampil();

        return view;
    }

    private void tampil() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Tunggu yaa...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("results");

                            //JSONArray array = jsonObject.getJSONObject("data").getJSONArray("results");
                            //JSONArray array2 = jsonObject.getJSONArray("multimedia");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                News news = new News(
                                        o.getJSONObject("multimedia").getString("src"),
                                        o.getString("display_title"),
                                        o.getString("summary_short"),
                                        o.getString("byline")

                                );
                                newslist.add(news);
                            }
                            adapter = new NewsAdapter(newslist, getActivity().getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), "dada", Toast.LENGTH_LONG).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

}