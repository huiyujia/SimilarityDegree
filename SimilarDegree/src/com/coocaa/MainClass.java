
package com.coocaa;


import com.coocaa.algorithm.Hit;
import com.coocaa.algorithm.Hits;
import com.coocaa.movie.MovieModel;
import com.coocaa.movie.MovieUtil;

import java.sql.ResultSet;
import java.util.List;
//import java.util.List;

import static com.coocaa.movie.MovieUtil.getMovieItems;
import static com.coocaa.movie.MovieUtil.caculateSimiaryValue;
import static com.coocaa.movie.MovieUtil.movieSize;

public class MainClass {
    public static void main(String[] args) {;

        MovieModel movies= getMovieItems();
        int moviesize=movieSize();
        // TODO get()函数咋写？？？？
        for (int i = 0; i < moviesize; i++) {
             MovieModel sourceItem = movies.get(i);
            for (int j = i + 1; j < moviesize; j++) {
                MovieModel compareItem = movies.get(i);
                double simiaryValue =caculateSimiaryValue(sourceItem, compareItem);
                MovieUtil.saveMovieSimiaryValue(sourceItem, compareItem, simiaryValue);
            }
        }
        Hits ranks=new Hits();



    }
}
